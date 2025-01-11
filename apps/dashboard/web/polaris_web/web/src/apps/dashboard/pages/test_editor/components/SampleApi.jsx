import { Box, Button, Divider, Frame, HorizontalStack, LegacyTabs, Modal, Text, Tooltip} from "@shopify/polaris"
import {ChevronUpMinor } from "@shopify/polaris-icons"

import { useEffect, useState } from "react";
import DropdownSearch from "../../../components/shared/DropdownSearch";
import api from "../../testing/api"
import testEditorRequests from "../api";
import func from "@/util/func";
import TestEditorStore from "../testEditorStore"
import "../TestEditor.css"
import TestRunResultPage from "../../testing/TestRunResultPage/TestRunResultPage";
import PersistStore from "../../../../main/PersistStore";
import editorSetup from "./editor_config/editorSetup";
import SampleData from "../../../components/shared/SampleData";
import transform from "../../../components/shared/customDiffEditor";
import EmptySampleApi from "./EmptySampleApi";

const SampleApi = () => {

    const allCollections = PersistStore(state => state.allCollections);
    const [selected, setSelected] = useState(0);
    const [selectApiActive, setSelectApiActive] = useState(false)
    const [selectedCollectionId, setSelectedCollectionId] = useState(null)
    const [apiEndpoints, setApiEndpoints] = useState([])
    const [selectedApiEndpoint, setSelectedApiEndpoint] = useState(null)
    const [sampleData, setSampleData] = useState(null)
    const [copyCollectionId, setCopyCollectionId] = useState(null)
    const [copySelectedApiEndpoint, setCopySelectedApiEndpoint] = useState(null)
    const [sampleDataList, setSampleDataList] = useState(null)
    const [loading, setLoading] = useState(false)
    const [testResult,setTestResult] = useState(null)
    const [showTestResult, setShowTestResult] = useState(false);
    const [editorData, setEditorData] = useState({message: ''})
    const [showEmptyLayout, setShowEmptyLayout] = useState(false)

    const currentContent = TestEditorStore(state => state.currentContent)
    const selectedTest = TestEditorStore(state => state.selectedTest)
    const vulnerableRequestsObj = TestEditorStore(state => state.vulnerableRequestsMap)
    const defaultRequest = TestEditorStore(state => state.defaultRequest)
    const selectedSampleApi = PersistStore(state => state.selectedSampleApi)
    const setSelectedSampleApi = PersistStore(state => state.setSelectedSampleApi)

    const [isCustomAPI, setIsCustomAPI] = useState(Object.keys(selectedSampleApi)?.length > 0 || false)
    
    const tabs = [{ id: 'request', content: 'Request' }, { id: 'response', content: 'Response'}];
    const mapCollectionIdToName = func.mapCollectionIdToName(allCollections)

    useEffect(()=>{
        if(showEmptyLayout) return
        let testId = selectedTest.value
        let selectedUrl = Object.keys(selectedSampleApi).length > 0 ? selectedSampleApi : vulnerableRequestsObj?.[testId]
        setSelectedCollectionId(null)
        setCopyCollectionId(null)
        setTestResult(null)
        if(!selectedUrl){
            selectedUrl = defaultRequest
        }
        setTimeout(()=> {
            setSelectedCollectionId(selectedUrl.apiCollectionId)
            setCopyCollectionId(selectedUrl.apiCollectionId)
            setSelectedApiEndpoint(func.toMethodUrlString({method:selectedUrl.method._name, url:selectedUrl.url}))
        },0)
        setTimeout(() => {
            setCopySelectedApiEndpoint(func.toMethodUrlString({method:selectedUrl.method._name, url:selectedUrl.url}))
        }, 300)
        
    },[selectedTest])

    useEffect(() => {
        let testId = selectedTest.value
        const mappedEndpoint = vulnerableRequestsObj?.[testId]
        if(sampleDataList == null) {
            return
        }

        if(sampleDataList.length === 0) {
            setTimeout(()=> {
                setShowEmptyLayout(true)
                setSelectedCollectionId(0)
                setCopyCollectionId(0)
                setSelectedApiEndpoint('No endpoint found!')
            },0)
            setTimeout(() => {
                setCopySelectedApiEndpoint('No endpoint found!')
            }, 300)
            return
        }

        setShowEmptyLayout(false)

        const sampleDataId = sampleDataList[0].id

        const collectionId = sampleDataId.apiCollectionId
        const endpoint = func.toMethodUrlString({method: sampleDataId.method, url: sampleDataId.url})

        if(mappedEndpoint?.apiCollectionId === collectionId && mappedEndpoint?.method?._name === sampleDataId.method && mappedEndpoint?.url === sampleDataId.url) {
            return
        }
        
        setTimeout(()=> {
            setSelectedCollectionId(collectionId)
            setCopyCollectionId(collectionId)
            setSelectedApiEndpoint(endpoint)
        },0)
        setTimeout(() => {
            setCopySelectedApiEndpoint(endpoint)
        }, 300)

    }, [sampleDataList])

    useEffect(() => {
        fetchApiEndpoints(copyCollectionId)
        setCopySelectedApiEndpoint(null);
        setTestResult(null)
    }, [copyCollectionId])

    useEffect(() => {
        if (selectedCollectionId && selectedApiEndpoint) {
            fetchSampleData(selectedCollectionId, func.toMethodUrlObject(selectedApiEndpoint).url, func.toMethodUrlObject(selectedApiEndpoint).method)
        }
        setTestResult(null)
    }, [selectedApiEndpoint])

    useEffect(()=> {
        if(testResult){
            setShowTestResult(true);
        } else {
            setShowTestResult(false);
        }
    }, [testResult])


    const handleTabChange = (selectedTabIndex) => {
        setSelected(selectedTabIndex)
        if (sampleData) {
            let localEditorData = ""
            if (selectedTabIndex === 0) {
                localEditorData = transform.formatData(sampleData?.requestJson, "http")
            } else {
                localEditorData = transform.formatData(sampleData?.responseJson, "http")
            }
            setEditorData({message: localEditorData})
        }else{
            setEditorData({message: ''})
        }
    }


    const activatedCollections = allCollections.filter(collection => collection.deactivated === false)
    const allCollectionsOptions = activatedCollections.map(collection => {
        return {
            label: collection.displayName,
            value: collection.id
        }
    })

    const fetchApiEndpoints = async (collectionId) => {
        if(!collectionId) return
        const apiEndpointsResponse = await api.fetchCollectionWiseApiEndpoints(collectionId)
        if (apiEndpointsResponse) {
            setApiEndpoints(apiEndpointsResponse.listOfEndpointsInCollection)
        }
    }

    const apiEndpointsOptions = apiEndpoints.map(apiEndpoint => {
        let str = func.toMethodUrlString(apiEndpoint);
        return {
            id: str,
            label: str,
            value: str
        }
    })

    const fetchSampleData = async (collectionId, apiEndpointUrl, apiEndpointMethod) => {
        setShowEmptyLayout(false)
        let sampleDataResponse
        if(isCustomAPI) {
            sampleDataResponse = await testEditorRequests.fetchSampleData(collectionId, apiEndpointUrl, apiEndpointMethod)
        } else {
            sampleDataResponse = await testEditorRequests.fetchSampleDataForTestEditor(collectionId, apiEndpointUrl, apiEndpointMethod)
        }
        if (sampleDataResponse) {
            if (sampleDataResponse.sampleDataList.length > 0 && sampleDataResponse.sampleDataList[0].samples && sampleDataResponse.sampleDataList[0].samples.length > 0) {
                const sampleDataJson = JSON.parse(sampleDataResponse.sampleDataList[0].samples[sampleDataResponse.sampleDataList[0].samples.length - 1])
                const requestJson = func.requestJson(sampleDataJson, [])
                const responseJson = func.responseJson(sampleDataJson, [])
                setSampleData({ requestJson, responseJson })
                setEditorData({message: transform.formatData(requestJson, "http")})
                setTimeout(()=> {
                    setSampleDataList(sampleDataResponse.sampleDataList)
                },0)

                setSelected(0)
            }else{
                setSampleDataList(sampleDataResponse.sampleDataList)
                setEditorData({message: ''})
                setSampleData({})
            }
        }else{
            setSampleDataList([])
            setSampleData({})
            setEditorData({message: ''})
        }
    }

    const toggleSelectApiActive = () => setSelectApiActive(prev => !prev)
    const saveFunc = () =>{
        setIsCustomAPI(true)
        setSelectedApiEndpoint(copySelectedApiEndpoint)
        const urlObj = func.toMethodUrlObject(copySelectedApiEndpoint)
        const sampleApi = {
            apiCollectionId :copyCollectionId, 
            url: urlObj.url,
            method:{
                "_name": urlObj.method
            }
        }
        setSelectedSampleApi(sampleApi)
        setSelectedCollectionId(copyCollectionId)
        toggleSelectApiActive()
    }

    const runTest = async()=>{
        setLoading(true)
        const apiKeyInfo = {
            ...func.toMethodUrlObject(selectedApiEndpoint),
            apiCollectionId: selectedCollectionId
        }

        try {
            let resp = await testEditorRequests.runTestForTemplate(currentContent,apiKeyInfo,sampleDataList)
            setTestResult(resp)
        } catch (err){
        }
        setLoading(false)
    }

    const showResults = () => {
        setShowTestResult(!showTestResult);
    }

    function getColor(){
        if(testResult){
            if(testResult.testingRunResult.vulnerable){
                let status = func.getRunResultSeverity(testResult.testingRunResult, testResult.subCategoryMap)
                status = status.toUpperCase();
                switch(status){
                    case "HIGH" : return "bg-critical";
                    case "MEDIUM": return "bg-caution";
                    case "LOW": return "bg-info";
                    default:
                        return "bg";
                }
            } else {
                return "bg-success"
            }
        } else {
            return "bg";
        }
    }

    function getResultDescription() {
        if (testResult) {
            if(testResult.testingRunResult.vulnerable){
                let status = func.getRunResultSeverity(testResult.testingRunResult, testResult.subCategoryMap)
                return func.toSentenceCase(status) + " vulnerability found";
            } else {
                return "No vulnerability found"
            }
        } else {
            return "Run test to see Results"
        }
    }

    const closeModal = () => {
        setShowTestResult(!showTestResult)
        editorSetup.setEditorTheme();
    }

    const resultComponent = (
        <Box background={getColor()} width="100%" padding={"2"}>
            <Button id={"test-results"} removeUnderline monochrome plain 
            onClick={testResult ? showResults : () => {}}
            icon={testResult ? ChevronUpMinor : undefined}>
                {getResultDescription()}
            </Button>
        </Box>

    )

    return (
        <div>
            <div className="editor-header">
                <div className="req-resp-tabs">
                    <LegacyTabs tabs={tabs} selected={selected} onSelect={handleTabChange} fitted />
                </div>
                <HorizontalStack gap={2}>
                    <Button id={"select-sample-api"} onClick={toggleSelectApiActive} size="slim">
                        <Box maxWidth="200px">
                            <Tooltip content={copySelectedApiEndpoint} hoverDelay={"100"}>
                                <Text variant="bodyMd" truncate>{copySelectedApiEndpoint}</Text>
                            </Tooltip>
                        </Box>
                    </Button>
                    <Button id={"run-test"} disabled={showEmptyLayout || editorData?.message?.length === 0} loading={loading} primary onClick={runTest} size="slim">Run Test</Button>
                </HorizontalStack>
            </div>

            <Divider />
            {
                showEmptyLayout ?
                <Box minHeight="84.4vh">
                    <EmptySampleApi
                        iconSrc={"/public/file_plus.svg"}
                        headingText={"Discover APIs to get started"}
                        description={"You have an inactive API collection or one with no data. Create or populate a collection now to get started."}
                        buttonText={"Create new a API collection"}
                        redirectUrl={"/dashboard/observe/inventory"}
                    />
                </Box> :
                <>{
                    editorData?.message?.length === 0 ?
                    <Box padding={3} minHeight="84.4vh"><Text>Sample data is not available for this API endpoint. Please choose a different endpoint.</Text></Box>
                        : <><SampleData data={editorData} minHeight="80.4vh"  editorLanguage="custom_http" />
                        {resultComponent}</>
                }</>
            }
            <Modal
                open={showTestResult}
                onClose={() => closeModal()}
                title="Results"
                large
            >
                <Frame >
                <Box paddingBlockEnd={"8"}>
                <TestRunResultPage
                    testingRunResult={testResult?.testingRunResult}
                    runIssues={testResult?.testingRunIssues}
                    testSubCategoryMap={testResult?.subCategoryMap}
                    testId={selectedTest.value}
                    source="editor"
                />
                </Box>
                </Frame>
            </Modal>
            <Modal
                open={selectApiActive}
                onClose={toggleSelectApiActive}
                title="Select sample API"
                primaryAction={{
                    id:"save",
                    content: 'Save',
                    onAction: saveFunc,
                }}
                secondaryActions={[
                    {
                        id:"cancel",
                        content: 'Cancel',
                        onAction: toggleSelectApiActive,
                    },
                ]}
            >
                <Modal.Section>
                    <DropdownSearch
                        id={"select-api-collection"}
                        label="Select collection"
                        placeholder="Select API collection"
                        optionsList={allCollectionsOptions}
                        setSelected={setCopyCollectionId}
                        value={mapCollectionIdToName?.[copyCollectionId]}
                        preSelected={[copyCollectionId]}
                    />

                    <br />

                    <DropdownSearch
                        id={"select-api-endpoint"}
                        disabled={apiEndpointsOptions.length === 0}
                        label="API"
                        placeholder="Select API endpoint"
                        optionsList={apiEndpointsOptions}
                        setSelected={setCopySelectedApiEndpoint}
                        value={copySelectedApiEndpoint==null ? "No endpoints selected" : copySelectedApiEndpoint}
                        preSelected={[copySelectedApiEndpoint]}
                    />

                </Modal.Section>
            </Modal>
        </div>
    )
}

export default SampleApi