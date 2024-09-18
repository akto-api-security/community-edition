import React, { useEffect, useReducer, useState } from 'react'
import api from './api';
import func from '@/util/func';
import observeFunc from "../observe/transform"
import SummaryCardInfo from '../../components/shared/SummaryCardInfo';
import PageWithMultipleCards from "../../components/layouts/PageWithMultipleCards"
import { Box, Card, Checkbox, DataTable, HorizontalGrid, HorizontalStack, Icon, Scrollable, Text, VerticalStack } from '@shopify/polaris';
import observeApi from "../observe/api"
import transform from './transform';
import testingTransform from "../testing/transform"
import StackedChart from '../../components/charts/StackedChart';
import ChartypeComponent from '../testing/TestRunsPage/ChartypeComponent';
import testingApi from "../testing/api"
import testingFunc from "../testing/transform"
import InitialSteps from './components/InitialSteps';
import CoverageCard from './components/CoverageCard';
import PersistStore from '../../../main/PersistStore';
import Pipeline from './components/Pipeline';
import ActivityTracker from './components/ActivityTracker';
import NullData from './components/NullData';
import {DashboardBanner} from './components/DashboardBanner';
import RiskScoreTrend from './components/RiskScoreTrend';
import TitleWithInfo from '@/apps/dashboard/components/shared/TitleWithInfo';
import SummaryCard from './new_components/SummaryCard';
import { ArrowUpMinor, ArrowDownMinor} from '@shopify/polaris-icons';
import TestSummaryCardsList from './new_components/TestSummaryCardsList';
import InfoCard from './new_components/InfoCard';
import DonutChart from '../../components/shared/DonutChart';
import ProgressBarChart from './new_components/ProgressBarChart';
import SpinnerCentered from '../../components/progress/SpinnerCentered';
import SmoothAreaChart from './new_components/SmoothChart'
import DateRangeFilter from '../../components/layouts/DateRangeFilter';
import values from "@/util/values";
import { produce } from 'immer';
import EmptyCard from './new_components/EmptyCard';

function HomeDashboard() {

    const [riskScoreRangeMap, setRiskScoreRangeMap] = useState({});
    const [issuesTrendMap, setIssuesTrendMap] = useState({trend: [], allSubCategories: []});
    const [loading, setLoading] = useState(true) ;
    const [countInfo, setCountInfo] = useState({totalUrls: 0, coverage: '0%'})
    const [riskScoreObj, setRiskScoreObj]= useState({}) ;
    const [sensitiveCount, setSensitiveCount]= useState([]) ;
    const [sensitiveData, setSensitiveData] = useState({request: {}, response: {}})
    const [subCategoryInfo, setSubCategoryInfo] = useState({});
    const [coverageObj, setCoverageObj] = useState({})
    const [recentActivities, setRecentActivities] = useState([])
    const [totalActivities, setTotalActivities] = useState(0)
    const [skip, setSkip] = useState(0)
    const [criticalUrls, setCriticalUrls] = useState(0);
    const [initialSteps, setInitialSteps] = useState({}) ;
    const [showBannerComponent, setShowBannerComponent] = useState(false)
    const [testSummaryInfo, setTestSummaryInfo] = useState([])

    const allCollections = PersistStore(state => state.allCollections)
    const coverageMap = PersistStore(state => state.coverageMap)
    const collectionsMap = PersistStore(state => state.collectionsMap)
    const [authMap, setAuthMap] = useState({})
    const [apiTypesData, setApiTypesData] = useState([{"data": [], "color": "#D6BBFB"}])
    const [riskScoreData, setRiskScoreData] = useState([])
    const [newDomains, setNewDomains] = useState([])
    const [criticalFindingsData, setCriticalFindingsData] = useState([])
    const [severityMap, setSeverityMap] = useState({})
    const [unsecuredAPIs, setUnsecuredAPIs] = useState([])
    const [totalIssuesCount, setTotalIssuesCount] = useState(0)
    const [oldIssueCount, setOldIssueCount] = useState(0)
    const [apiRiskScore, setApiRiskScore] = useState(0)
    const [testCoverage, setTestCoverage] = useState(0)
    const [totalAPIs, setTotalAPIs] = useState(0)
    const [oldTotalApis, setOldTotalApis] = useState(0)
    const [oldTestCoverage, setOldTestCoverage] = useState(0)
    const [oldRiskScore, setOldRiskScore] = useState(0)
    const [startTimestamp, setStartTimestamp] = useState(1726573131)
    const [endTimestamp, setEndTimestamp] = useState(1726574978)
    const [showTestingComponents, setShowTestingComponents] = useState(false)

    const initialVal = values.ranges[1]
    const [currDateRange, dispatchCurrDateRange] = useReducer(produce((draft, action) => func.dateRangeReducer(draft, action)), initialVal);

    const [accessTypeMap, setAccessTypeMap] = useState({
        "Partner": {
          "text": 0,
          "color": "#147CF6",
          "filterKey": "Partner",
          "component": (<div>hi</div>)
        },
        "Internal": {
          "text": 0,
          "color": "#FDB33D",
          "filterKey": "Internal"
        },
        "External": {
          "text": 0,
          "color": "#658EE2",
          "filterKey": "External"
        },
        "Third Party": {
          "text": 0,
          "color": "#68B3D0",
          "filterKey": "Third Party"
        }
      });


    const initialHistoricalData = []
    const finalHistoricalData = []

    const defaultChartOptions = {
        "legend": {
            enabled: false
        },
    }

    const convertSeverityArrToMap = (arr) => {
        return Object.fromEntries(arr.map(item => [item.split(' ')[1].toLowerCase(), +item.split(' ')[0]]));
    }

    const testSummaryData = async () => {
        const endTimestamp = func.timeNow()
        await testingApi.fetchTestingDetails(
            0, endTimestamp, "endTimestamp", "-1", 0, 10, null, null, ""
          ).then(({ testingRuns, testingRunsCount, latestTestingRunResultSummaries }) => {
            const result = testingTransform.prepareTestRuns(testingRuns, latestTestingRunResultSummaries);
            const finalResult = []
            result.forEach((x) => {
                const severity = x["severity"]
                const severityMap = convertSeverityArrToMap(severity)
                finalResult.push({
                    "testName": x["name"],
                    "time": func.prettifyEpoch(x["run_time_epoch"]),
                    "highCount": severityMap["high"] ? severityMap["high"] : "0" ,
                    "mediumCount": severityMap["medium"] ? severityMap["medium"] : "0" ,
                    "lowCount": severityMap["low"] ? severityMap["low"] : "0",
                    "totalApis": x["total_apis"],
                    "link": x["nextUrl"]
                })
            })

            setTestSummaryInfo(finalResult)
            setShowTestingComponents(finalResult && finalResult.length > 0)
          });
    }

    const fetchData = async() =>{
        setLoading(true)
        // all apis 
        let apiPromises = [
            observeApi.getCoverageInfoForCollections(),
            api.getRiskScoreRangeMap(),
            api.getIssuesTrend((func.timeNow() - func.recencyPeriod), func.timeNow()),
            api.fetchSubTypeCountMap(0 , func.timeNow()),
            testingApi.getSummaryInfo(0 , func.timeNow()),
            api.fetchRecentFeed(skip),
            api.getIntegratedConnections(),
            observeApi.getUserEndpoints(),
            api.fetchCriticalIssuesTrend(), // todo:
            api.findTotalIssues(startTimestamp, endTimestamp),
            api.fetchApiStats(startTimestamp, endTimestamp),
            api.fetchEndpointsCount(startTimestamp, endTimestamp)
        ];
        
        let results = await Promise.allSettled(apiPromises);

        let coverageInfo = results[0].status === 'fulfilled' ? results[0].value : {};
        let riskScoreRangeMap = results[1].status === 'fulfilled' ? results[1].value : {};
        let issuesTrendResp = results[2].status === 'fulfilled' ? results[2].value : {};
        let sensitiveDataResp = results[3].status === 'fulfilled' ? results[3].value : {} ;
        let subcategoryDataResp = results[4].status === 'fulfilled' ? results[4].value : {} ;
        let recentActivitiesResp = results[5].status === 'fulfilled' ? results[5].value : {} ;
        let connectionsInfo = results[6].status === 'fulfilled' ? results[6].value : {} ;
        let userEndpoints = results[7].status === 'fulfilled' ? results[7].value : true ;
        let criticalIssuesTrendResp = results[8].status === 'fulfilled' ? results[8].value : {}
        let findTotalIssuesResp = results[9].status === 'fulfilled' ? results[9].value : {}
        let apisStatsResp = results[10].status === 'fulfilled' ? results[10].value : {}
        let fetchEndpointsCountResp = results[11].status === 'fulfilled' ? results[11].value : {}

        setShowBannerComponent(!userEndpoints)

        buildUnsecuredAPIs(criticalIssuesTrendResp)

        setCountInfo(transform.getCountInfo((allCollections || []), coverageInfo))
        setCoverageObj(coverageInfo)
        setRiskScoreRangeMap(riskScoreRangeMap);
        setIssuesTrendMap(transform.formatTrendData(issuesTrendResp));
        setSensitiveData(transform.getFormattedSensitiveData(sensitiveDataResp.response))
        const tempResult =  testingFunc.convertSubIntoSubcategory(subcategoryDataResp)
        setSubCategoryInfo(tempResult.subCategoryMap)
        setRecentActivities(recentActivitiesResp.recentActivities)
        setTotalActivities(recentActivitiesResp.totalActivities)
        setInitialSteps(connectionsInfo)

        const riskScoreObj = (await observeFunc.fetchRiskScoreInfo()).riskScoreObj
        const riskScoreMap = riskScoreObj.riskScoreMap || {};
        const endpoints = riskScoreObj.criticalUrls;
        setCriticalUrls(endpoints)

        const sensitiveInfo = await observeFunc.fetchSensitiveInfo() ;
        setRiskScoreObj(riskScoreMap) ;
        setSensitiveCount(sensitiveInfo.sensitiveUrls) ;

        buildMetrics(apisStatsResp.apiStatsEnd)
        testSummaryData()
        mapAccessTypes(apisStatsResp)
        mapAuthTypes(apisStatsResp)
        buildAuthTypesData(apisStatsResp.apiStatsEnd)
        buildSetRiskScoreData(apisStatsResp.apiStatsEnd) //todo
        getCollectionsWithCoverage()
        convertSubCategoryInfo(tempResult.subCategoryMap)
        buildSeverityMap(apisStatsResp.apiStatsEnd)
        buildIssuesSummary(findTotalIssuesResp)
        
        const fetchHistoricalDataResp = {"finalHistoricalData": finalHistoricalData, "initialHistoricalData": initialHistoricalData}
        buildDeltaInfo(fetchHistoricalDataResp)

        buildEndpointsCount(fetchEndpointsCountResp)

        setLoading(false)
    }

    useEffect(()=>{
        fetchData()
    },[])

    function buildIssuesSummary(findTotalIssuesResp) {
        setTotalIssuesCount(findTotalIssuesResp.totalIssuesCount)
        setOldIssueCount(findTotalIssuesResp.oldOpenCount)
    }

    function buildEndpointsCount(fetchEndpointsCountResp) {
        let newCount = fetchEndpointsCountResp.newCount
        let oldCount = fetchEndpointsCountResp.oldCount

        setTotalAPIs(newCount)
        setOldTotalApis(oldCount)
    }

    function buildMetrics(apiStats) {
        const totalRiskScore = apiStats.totalRiskScore
        const totalAPIs = apiStats.totalAPIs

        const apisTestedInLookBackPeriod = apiStats.apisTestedInLookBackPeriod

        const tempRiskScore = totalRiskScore / totalAPIs
        setApiRiskScore(parseFloat(tempRiskScore.toFixed(2)))

        const testCoverage = 100 * apisTestedInLookBackPeriod / totalAPIs
        setTestCoverage(parseFloat(testCoverage.toFixed(2)))
    }

    function buildDeltaInfo(deltaInfo) {
        const initialHistoricalData = deltaInfo.initialHistoricalData
    
        let totalApis = 0
        let totalRiskScore = 0
        let totalTestedApis = 0
        initialHistoricalData.forEach((x) => {
            totalApis += x.totalApis
            totalRiskScore += x.riskScore
            totalTestedApis += x.apisTested
        })

        const tempRiskScore = totalAPIs ? (totalRiskScore / totalApis).toFixed(2) : 0
        setOldRiskScore(parseFloat(tempRiskScore))
        const tempTestCoverate = totalAPIs ? (100 * totalTestedApis / totalApis).toFixed(2) : 0
        setOldTestCoverage(parseFloat(tempTestCoverate))
    }

    const generateByLineComponent = (val, time) => {
        const source = val > 0 ? ArrowUpMinor : ArrowDownMinor
        return (
            <HorizontalStack gap={1}>
                <Box>
                    <Icon source={source} color='subdued'/>
                </Box>
                <Text color='subdued' fontWeight='medium'>{Math.abs(val)}</Text>
                <Text color='subdued' fontWeight='semibold'>{time}</Text>
            </HorizontalStack>
        )
    }

    function generateChangeComponent(val, invertColor) {
        const source = val > 0 ? ArrowUpMinor : ArrowDownMinor
        if (val === 0) return null
        const color = !invertColor && val > 0 ? "success" : "critical"
        return (
            <HorizontalStack>
                <Icon source={source} color={color}/>
                <div className='custom-color'>
                    <Text color={color}>{Math.abs(val)}</Text>
                </div>
            </HorizontalStack>
        )
    }

    function mapAccessTypes(apiStats) {
        const apiStatsEnd = apiStats.apiStatsEnd
        const apiStatsStart = apiStats.apiStatsStart

        const accessTypeMapping = {
            "PUBLIC": "External",
            "PRIVATE": "Internal",
            "PARTNER": "Partner",
            "THIRD_PARTY": "Third Party"
        };

        for (const [key, value] of Object.entries(apiStatsEnd.accessTypeMap)) {
            const mappedKey = accessTypeMapping[key];
            if (mappedKey && accessTypeMap[mappedKey]) {
                accessTypeMap[mappedKey].text = value;
                accessTypeMap[mappedKey].dataTableComponent = generateChangeComponent((value - apiStatsStart.accessTypeMap[key]), false);
            }
        }
        setAccessTypeMap(accessTypeMap)
    }


    function mapAuthTypes(apiStats) {
        const apiStatsEnd = apiStats.apiStatsEnd
        const apiStatsStart = apiStats.apiStatsStart
        const convertKey = (key) => {
            return key
                .toLowerCase()
                .replace(/(^|\s)\S/g, (letter) => letter.toUpperCase()) // Capitalize the first character after any space
                .replace(/_/g, ' '); // Replace underscores with spaces
        };

        // Initialize colors list
        const colors = ["#7F56D9", "#8C66E1", "#9E77ED", "#AB88F1", "#B692F6", "#D6BBFB", "#E9D7FE", "#F4EBFF"];

        // Convert and sort the authTypeMap entries by value (count) in descending order
        const sortedAuthTypes = Object.entries(apiStatsEnd.authTypeMap)
            .map(([key, value]) => ({ key: key, text: value }))
            .filter(item => item.text > 0) // Filter out entries with a count of 0
            .sort((a, b) => b.text - a.text); // Sort by count descending

        // Initialize the output authMap
        const authMap = {};


        // Fill in the authMap with sorted entries and corresponding colors
        sortedAuthTypes.forEach((item, index) => {
            authMap[convertKey(item.key)] = {
                "text": item.text,
                "color": colors[index] || "#F4EBFF", // Assign color; default to last color if out of range
                "filterKey": convertKey(item.key),
                "dataTableComponent": apiStatsStart && apiStatsStart.authTypeMap && apiStatsStart.authTypeMap[item.key] ? generateChangeComponent((item.text - apiStatsStart.authTypeMap[item.key]), item.key === "UNAUTHENTICATED") : null
            };
        });

        setAuthMap(authMap)
    }


    function buildAuthTypesData(apiStats) {
        // Initialize the data with default values for all API types
        const data = [
            ["REST", apiStats.apiTypeMap.REST || 0], // Use the value from apiTypeMap or 0 if not available
            ["GraphQL", apiStats.apiTypeMap.GRAPHQL || 0],
            ["gRPC", apiStats.apiTypeMap.GRPC || 0],
            ["SOAP", apiStats.apiTypeMap.SOAP || 0]
        ];
    
        setApiTypesData([{data: data,color: "#D6BBFB"}])
    }

    function buildSetRiskScoreData(apiStats) {
        const totalApisCount = apiStats.totalAPIs

        const sumOfRiskScores = Object.values(apiStats.riskScoreMap).reduce((acc, value) => acc + value, 0);

        // Calculate the additional APIs that should be added to risk score "0"
        const additionalAPIsForZero = totalApisCount - sumOfRiskScores;
        apiStats.riskScoreMap["0"] = apiStats.riskScoreMap["0"] ? apiStats.riskScoreMap["0"] : 0
        if (additionalAPIsForZero > 0) apiStats.riskScoreMap["0"] += additionalAPIsForZero;

        // Prepare the result array with values from 5 to 0
        const result = [
            { "badgeValue": 5, "progressValue": "0%", "text": 0, "topColor": "#E45357", "backgroundColor": "#FFDCDD", "badgeColor": "critical" },
            { "badgeValue": 4, "progressValue": "0%", "text": 0, "topColor": "#EF864C", "backgroundColor": "#FFD9C4", "badgeColor": "attention" },
            { "badgeValue": 3, "progressValue": "0%", "text": 0, "topColor": "#F6C564", "backgroundColor": "#FFF1D4", "badgeColor": "warning"  },
            { "badgeValue": 2, "progressValue": "0%", "text": 0, "topColor": "#F5D8A1", "backgroundColor": "#FFF6E6", "badgeColor": "info"  },
            { "badgeValue": 1, "progressValue": "0%", "text": 0, "topColor": "#A4E8F2", "backgroundColor": "#EBFCFF", "badgeColor": "new"  },
            { "badgeValue": 0, "progressValue": "0%", "text": 0, "topColor": "#6FD1A6", "backgroundColor": "#E0FFF1", "badgeColor": "success" }
        ];

        // Update progress and text based on riskScoreMap values
        Object.keys(apiStats.riskScoreMap).forEach((key) => {
            const badgeIndex = 5 - parseInt(key, 10);
            const value = apiStats.riskScoreMap[key];
            result[badgeIndex].text = value;
            result[badgeIndex].progressValue = `${((value / totalApisCount) * 100).toFixed(2)}%`;
        });

        setRiskScoreData(result)
    }

    function getCollectionsWithCoverage() {
        const validCollections = allCollections.filter(collection => collection.hostName !== null && collection.hostName !== undefined);
    
        const sortedCollections = validCollections.sort((a, b) => b.startTs - a.startTs);
    
        const result = sortedCollections.slice(0, 10).map(collection => {
            const apisTested = coverageMap[collection.id] || 0;
            return {
                name: collection.hostName,
                apisTested: apisTested,
                totalApis: collection.urlsCount
            };
        });

        setNewDomains(result)
    }

    const summaryInfo = [
        {
            title: 'Total APIs',
            data: totalAPIs,
            variant: 'heading2xl',
            byLineComponent: generateByLineComponent((totalAPIs - oldTotalApis), "Yesterday"),
            smoothChartComponent: (<SmoothAreaChart tickPositions={[oldTotalApis, totalAPIs]}/>)
        },
        {
            title: 'Issues',
            data: observeFunc.formatNumberWithCommas(totalIssuesCount),
            variant: 'heading2xl',
            color: 'critical',
            byLineComponent: generateByLineComponent( (totalIssuesCount - oldIssueCount), "Yesterday"),
            smoothChartComponent: (<SmoothAreaChart tickPositions={[oldIssueCount, totalIssuesCount]}/>)
            
        },
        {
            title: 'API Risk Score',
            data: apiRiskScore,
            variant: 'heading2xl',
            color: apiRiskScore > 2.5 ? 'critical' : 'warning',
            byLineComponent: generateByLineComponent((apiRiskScore - oldRiskScore).toFixed(2), "Yesterday"),
            smoothChartComponent: (<SmoothAreaChart tickPositions={[oldRiskScore, apiRiskScore]}/>)
        },
        {
            title: 'Test Coverage',
            data: testCoverage + "%",
            variant: 'heading2xl',
            color: testCoverage > 80 ? 'success' : 'warning',
            byLineComponent: generateByLineComponent((testCoverage-oldTestCoverage).toFixed(2), "Yesterday"),
            smoothChartComponent: (<SmoothAreaChart tickPositions={[oldTestCoverage, testCoverage]}/>)
        }
    ]

    const summaryComp = (
        <SummaryCard summaryItems={summaryInfo}/>
    )


    const testSummaryCardsList = showTestingComponents ? (
        <InfoCard 
            component={<TestSummaryCardsList summaryItems={ testSummaryInfo}/>}
            title="Recent Tests"
            titleToolTip="Tests runs since last 7 days"
            linkText="Increase test coverage"
            linkUrl="/dashboard/testing"
        />
    ) : null

    function buildSeverityMap(apiStats) {
        const countMap = apiStats.criticalMap;

        const result = {
            "Critical": {
                "text": countMap.CRITICAL || 0,
                "color": "#E45357",
                "filterKey": "Critical",
            },
            "High": {
                "text": countMap.HIGH || 0,
                "color": "#EF864C",
                "filterKey": "High"
            },
            "Medium": {
                "text": countMap.MEDIUM || 0,
                "color": "#F6C564",
                "filterKey": "Medium"
            },
            "Low": {
                "text": countMap.LOW || 0,
                "color": "#6FD1A6",
                "filterKey": "Low"
            }
        };

        setSeverityMap(result)
    }

    function buildUnsecuredAPIs(input) {
        const CRITICAL_COLOR = "#E45357";
        const transformed = [];

        // Initialize objects for CRITICAL and HIGH data
        const criticalData = { data: [], color: CRITICAL_COLOR };

        // Iterate through the input to populate criticalData and highData
        for (const epoch in input) {
            const epochMillis = Number(epoch) * 86400000; // Convert days to milliseconds
            criticalData.data.push([epochMillis, input[epoch]]);
        }

        // Push the results to the transformed array
        transformed.push(criticalData);

        setUnsecuredAPIs(transformed)
    }


    const genreateDataTableRows = (collections) => {
        return collections.map((collection, index) => ([
            <HorizontalStack align='space-between'>
                <HorizontalStack gap={2}>
                    {/* <Checkbox
                        key={index}
                        label={collection.name}
                        checked={false}
                        ariaDescribedBy={`checkbox-${index}`}
                        onChange={() => {}}
                    /> */}
                    <Text>{collection.name}</Text>
                    <Text color='subdued'>{Math.floor(100.0 * collection.apisTested / collection.totalApis)}% test coverage</Text>
                </HorizontalStack>
                <Text>{collection.totalApis}</Text>
            </HorizontalStack>
            ]
          ));
    }

    function convertSubCategoryInfo(tempSubCategoryMap) {
        const entries = Object.values(tempSubCategoryMap);
        entries.sort((a, b) => b.text - a.text);
        const topEntries = entries.slice(0, 5);
        const data = topEntries.map(entry => [entry.filterKey, entry.text]);
        setCriticalFindingsData([{"data": data, "color": "#E45357"}])
    }


    function extractCategoryNames(data) {
        if (!data || !Array.isArray(data) || data.length === 0) {
            return [];
        }
        
        const findings = data[0]?.data || [];
        return findings.map(([category]) => category);
    }

    const vulnerableApisBySeverityComponent = !showTestingComponents? <EmptyCard title="Vulnerable APIs by Severity"/> : <InfoCard 
        component={
            <div style={{marginTop: "20px"}}>
                <ChartypeComponent 
                    data={severityMap}
                    navUrl={"/dashboard/issues/"} title={""} isNormal={true} boxHeight={'250px'} chartOnLeft={true} dataTableWidth="250px" boxPadding={0}
                />
            </div>
        }
        title="Vulnerable APIs by Severity"
        titleToolTip="Vulnerable APIs by Severity"
        linkText="Fix critical issues"
        linkUrl="/dashboard/issues"
    />

    const criticalUnsecuredAPIsOverTime = !showTestingComponents ? <EmptyCard title="Critical Unsecured APIs Over Time" /> : <InfoCard 
        component={
            <StackedChart 
                type='column'
                color='#6200EA'
                areaFillHex="true"
                height="280"
                background-color="#ffffff"
                data={unsecuredAPIs}
                defaultChartOptions={defaultChartOptions}
                text="true"
                yAxisTitle="Number of issues"
                width={40}
                gap={10}
                showGridLines={true}
                exportingDisabled={true}
            />
        }
        title="Critical Unsecured APIs Over Time"
        titleToolTip="Critical Unsecured APIs Over Time"
        linkText="Fix critical issues"
        linkUrl="/dashboard/issues"
    />

    const criticalFindings = !showTestingComponents ? <EmptyCard title="Critical Findings"/> :
            <InfoCard 
                component={
                    <StackedChart 
                        type='column'
                        color='#6200EA'
                        areaFillHex="true"
                        height="280"
                        background-color="#ffffff"
                        data={criticalFindingsData}
                        defaultChartOptions={defaultChartOptions}
                        text="true"
                        yAxisTitle="Number of issues"
                        width={40}
                        gap={10}
                        showGridLines={true}
                        customXaxis={
                            {
                                categories: extractCategoryNames(criticalFindingsData),
                                crosshair: true
                            }
                        }
                        exportingDisabled={true}
                    />
                }
                title="Critical Findings"
                titleToolTip="Critical Findings"
                linkText="Fix critical issues"
                linkUrl="/dashboard/issues"
            />

    const apisByRiskscoreComponent = <InfoCard 
        component={
            <div style={{marginTop: "20px"}}>
                <ProgressBarChart data={riskScoreData}/>
            </div>
        }
        title="APIs by Risk score"
        titleToolTip="APIs by Risk score"
        linkText="Check out"
        linkUrl="/dashboard/observe/inventory"
    />

    const apisByAccessTypeComponent = <InfoCard 
        component={
            <ChartypeComponent data={accessTypeMap} navUrl={"/dashboard/observe/inventory"} title={""} isNormal={true} boxHeight={'250px'} chartOnLeft={true} dataTableWidth="250px" boxPadding={0}/>
        }
        title="APIs by Access type"
        titleToolTip="APIs by Access type"
        linkText="Check out"
        linkUrl="/dashboard/observe/inventory"
    />

    const apisByAuthTypeComponent = 
            <InfoCard 
                component={
                    <ChartypeComponent data={authMap} navUrl={"/dashboard/observe/inventory"} title={""} isNormal={true} boxHeight={'250px'} chartOnLeft={true} dataTableWidth="250px" boxPadding={0}/>
                }
                title="APIs by Authentication"
                titleToolTip="APIs by Authentication"
                linkText="Check out"
                linkUrl="/dashboard/observe/inventory"
            />
    
    const apisByTypeComponent = <InfoCard 
        component={
            <StackedChart 
                type='column'
                color='#6200EA'
                areaFillHex="true"
                height="280"
                background-color="#ffffff"
                data={apiTypesData}
                defaultChartOptions={defaultChartOptions}
                text="true"
                yAxisTitle="Number of APIs"
                width={40}
                gap={10}
                showGridLines={true}
                customXaxis={
                    {
                        categories: extractCategoryNames(apiTypesData),
                        crosshair: true
                    }
                }
                exportingDisabled={true}
            />
        }
        title="API Type"
        titleToolTip="API Type"
        linkText="Check out"
        linkUrl="/dashboard/observe/inventory"
    />

    const newDomainsComponent = <InfoCard 
        component={
            <DataTable
                columnContentTypes={[
                    'text',
                ]}
                headings={[]}
                rows={genreateDataTableRows(newDomains)}
                hoverable={false}
                increasedTableDensity
            />
        }
        title="New Domains"
        titleToolTip="New Domains"
        linkText="Increase test coverage"
        linkUrl="/dashboard/observe/inventory"
        minHeight="344px"
    />

    const gridComponents = showTestingComponents ? 
        [criticalUnsecuredAPIsOverTime, vulnerableApisBySeverityComponent, criticalFindings, apisByRiskscoreComponent, apisByAccessTypeComponent, apisByAuthTypeComponent, apisByTypeComponent, newDomainsComponent] :
        [apisByRiskscoreComponent, apisByAccessTypeComponent, apisByAuthTypeComponent, apisByTypeComponent, newDomainsComponent, criticalUnsecuredAPIsOverTime, vulnerableApisBySeverityComponent, criticalFindings]

    const gridComponent = (
        <HorizontalGrid gap={5} columns={2}>
            {gridComponents}
        </HorizontalGrid>
    )

    const subcategoryInfoComp = (
        Object.keys(subCategoryInfo).length > 0 ? 
            <Card key="subcategoryTrend">
                <VerticalStack gap={5}>
                    <TitleWithInfo
                        titleText={"Issues by category"}
                        tooltipContent={"Testing run issues present in dashboard categorised by subcategory of tests."}
                        textProps={{variant: "headingMd"}}
                    />
                    <ChartypeComponent navUrl={"/dashboard/issues/"} data={subCategoryInfo} title={"Categories"} isNormal={true} boxHeight={'200px'}/>
                </VerticalStack>
            </Card>

        : <NullData text={"Issues by category"} url={"/dashboard/observe/inventory"} urlText={"to run a test on a collection."} description={"No test categories found."} key={"subcategoryTrend"}/>
     )

    const riskScoreRanges = [
        {
            text: "High risk",
            range: '4-5',
            status: "critical",
            apiCollectionId: 111_111_150
        },
        {
            text: 'Medium risk',
            range: '3-4',
            status: 'warning',
            apiCollectionId: 111_111_149
        },
        {
            text: "Low risk",
            range: '0-3',
            status: 'new',
            apiCollectionId: 111_111_148
        }
    ]
    const riskScoreTrendComp = (
        <RiskScoreTrend  key={"risk-score-trend"} riskScoreRangeMap={riskScoreRangeMap} riskScoreRanges={riskScoreRanges} />
    )

    const sensitiveDataTrendComp = (
        (!sensitiveData || (!(sensitiveData.request) && !(sensitiveData.response))) ? 
        <NullData text={"Sensitive Data"} url={"/dashboard/observe/inventory"} urlText={"to create a collection and upload traffic in it."} description={"No sensitive data found."} key={"sensitiveNullTrend"}/>
        :
        <Card key="sensitiveTrend">
            <VerticalStack gap={5}>
                <TitleWithInfo
                    titleText={"Sensitive data"}
                    tooltipContent={"Count of endpoints per data type."}
                    textProps={{variant: "headingMd"}}
                    docsUrl={"https://docs.akto.io/api-inventory/concepts/sensitive-data"}
                />
                <HorizontalGrid gap={5} columns={2}>
                    <ChartypeComponent navUrl={"/dashboard/observe/sensitive/"} isRequest={true} data={sensitiveData.request} title={"Request"} isNormal={true} boxHeight={'100px'}/>
                    <ChartypeComponent navUrl={"/dashboard/observe/sensitive/"} data={sensitiveData.response} title={"Response"} isNormal={true} boxHeight={'100px'}/>
                </HorizontalGrid>
            </VerticalStack>
        </Card>
    )

    const issuesTrendComp = (
        (issuesTrendMap.allSubCategories.length > 0 && issuesTrendMap.trend.length > 0) ? 
        <Card key="issuesTrend">
            <VerticalStack gap={5}>
                <TitleWithInfo
                    titleText={"Issues timeline"}
                    tooltipContent={"Count of issues per category against the time they were last seen"}
                    textProps={{variant: "headingMd"}}
                />
                <VerticalStack gap={3}>
                    <HorizontalStack align="end">
                        <Scrollable style={{ width: '350px' }} shadow>
                            <Box maxWidth="50%">
                                    <div style={{display: 'flex', gap: '12px', cursor:'pointer'}}>
                                        {issuesTrendMap.allSubCategories.map((x,index)=>{
                                            return(
                                                <div style={{display: 'flex', gap: '8px', alignItems: 'center'}} key={index}>
                                                    <div style={{background: func.getColorForCharts(x), borderRadius: '50%', height:'8px', width: '8px'}}/>
                                                    <Text variant="bodySm">{x}</Text>
                                                </div>
                                                )
                                            })}
                                    </div>
                                
                            </Box>
                        </Scrollable>
                    </HorizontalStack>
                    <StackedChart 
                        type='column'
                        color='#6200EA'
                        areaFillHex="true"
                        height="280"
                        background-color="#ffffff"
                        data={issuesTrendMap.trend}
                        defaultChartOptions={defaultChartOptions}
                        text="true"
                        yAxisTitle="Number of issues"
                        width={30}
                        gap={10}
                        showGridLines={true}
                        exportingDisabled={true}
                    />
                </VerticalStack>
            </VerticalStack>
        </Card>
        :  <NullData text={"Issues timeline."} url={"/dashboard/observe/inventory"} urlText={"to run a test on a collection."} description={"No issues found."} key={"issuesTrend"}/>
    )

    const checkLoadMore = () => {
        const calledActivitiesYet = recentActivities.length;
        return calledActivitiesYet < totalActivities;
    }

    const handleLoadMore = async() => {
        if(checkLoadMore()){
            await api.fetchRecentFeed(skip + 1).then((resp) => {
                setRecentActivities([...recentActivities,...resp.recentActivities])
            })
            setSkip(skip + 1);
        }
    }

    const components = [summaryComp, testSummaryCardsList, gridComponent]

    const dashboardComp = (
        <div style={{display: 'flex', gap: '32px'}} key={"dashboardComp"}>
            <div style={{flex: 7}}>
                <VerticalStack gap={4}>
                    {components.map((component) => {
                        return component
                    })}
                </VerticalStack>
            </div>
        </div>
    )

    const pageComponents = [showBannerComponent ? <DashboardBanner key="dashboardBanner" />: null, dashboardComp]

    return (
        <Box>
            {loading ? <SpinnerCentered /> :
                <PageWithMultipleCards
                    title={
                        <Text variant='headingLg'>
                            API Security Posture
                        </Text>
                    }
                    isFirstPage={true}
                    components={pageComponents}
                    primaryAction={<DateRangeFilter initialDispatch = {currDateRange} dispatch={(dateObj) => dispatchCurrDateRange({type: "update", period: dateObj.period, title: dateObj.title, alias: dateObj.alias})}/>}
                />
            }

        </Box>

    )
}

export default HomeDashboard