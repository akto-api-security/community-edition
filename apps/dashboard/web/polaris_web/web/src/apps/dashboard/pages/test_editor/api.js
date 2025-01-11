import request from "@/util/request"

const testEditorRequests = {
    fetchSampleData(collectionId, apiEndpointUrl, apiEndpointMethod) {
        return request({
            url: '/api/fetchSampleData',
            method: 'post',
            data: {
                apiCollectionId: collectionId, 
                url: apiEndpointUrl, 
                method: apiEndpointMethod
            }
        })
    },
    fetchSampleDataForTestEditor(collectionId, apiEndpointUrl, apiEndpointMethod) {
        return request({
            url: '/api/fetchSampleDataForTestEditor',
            method: 'post',
            data: {
                apiCollectionId: collectionId, 
                url: apiEndpointUrl, 
                method: apiEndpointMethod
            }
        })
    },

    fetchVulnerableRequests(skip, limit) {
        return request({
            url: 'api/fetchVulnerableRequests',
            method: 'post',
            data: { skip, limit }
        })
    },
    
    runTestForTemplate(content, apiInfoKey, sampleDataList) {
        return request({
            url: '/api/runTestForGivenTemplate',
            method: 'post',
            data:{content, apiInfoKey, sampleDataList}
        })
    },

    addTestTemplate(content,originalTestId) {
        return request({
            url: '/api/saveTestEditorFile',
            method: 'post',
            data:{content, originalTestId}
        }).then((resp) => {
            return resp
        })
    },

    setTestInactive(testId, inactive) {
        return request({
            url: '/api/setTestInactive',
            method: 'post',
            data: {
                originalTestId: testId,
                inactive: inactive
            }
        })
    },

}

export default testEditorRequests