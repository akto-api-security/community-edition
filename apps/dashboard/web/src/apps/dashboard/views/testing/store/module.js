import Vue from 'vue'
import Vuex from 'vuex'
import api from '../api'


Vue.use(Vuex)

var state = {
    loading: false,
    testingRuns: [],
    authMechanism: null,
    testingRunResults: []
}

const testing = {
    namespaced: true,
    state: state,
    mutations: {
        EMPTY_STATE (state) {
            state.loading = false
            state.testingRuns = []
            state.authMechanism = null
            state.testingRunResults = []
        },
        SAVE_DETAILS (state, {authMechanism, testingRuns}) {
            state.authMechanism = authMechanism
            state.testingRuns = testingRuns
        },
        SAVE_TESTING_RUNS (state, {testingRuns}) {
            state.testingRuns = testingRuns
        },
        SAVE_AUTH_MECHANISM (state, {authMechanism}) {
            state.authMechanism = authMechanism
        },
        SAVE_TESTING_RUN_RESULTS(state, {testingRunResults}) {
            state.testingRunResults = testingRunResults
        }
    },
    actions: {
        emptyState({commit}, payload, options) {
            commit('EMPTY_STATE', payload, options)
        },
        loadTestingDetails({commit}, options) {
            commit('EMPTY_STATE')
            state.loading = true
            return api.fetchTestingDetails().then((resp) => {
                commit('SAVE_DETAILS', resp)
                api.fetchTestingRunResults().then(resp => {
                    commit('SAVE_TESTING_RUN_RESULTS', resp)
                })
                state.loading = false
            }).catch(() => {
                state.loading = false
            })
        },
        startTestForCollection({commit}, apiCollectionId) {
            return api.startTestForCollection(apiCollectionId).then((resp) => {
                commit('SAVE_TESTING_RUNS', resp)
            })
        },
        stopTestForCollection({commit}, apiCollectionId) {
            return api.stopTestForCollection(apiCollectionId).then((resp) => {
                commit('SAVE_TESTING_RUNS', resp)
            })
        },
        addAuthMechanism({commit}, {key, value, location}) {
            return api.addAuthMechanism(key, value, location).then(resp => {
                commit('SAVE_AUTH_MECHANISM', resp)
            })
        }
    },
    getters: {
        getLoading: (state) => state.loading,
        getTestingRuns: (state) => state.testingRuns,
        getAuthMechanism: (state) => state.authMechanism
    }
}

export default testing