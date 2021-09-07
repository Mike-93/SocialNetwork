import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import global from './global'
import auth from './auth'
import profile from './profile'
import users from './users'
import getRandomIntInclusive from '../utils/getRandomIntInclusive.js'

export default new Vuex.Store({
  namespaced: true,
  modules: {
    global,
    auth,
    profile,
    users
  },
  state: {
    code: getRandomIntInclusive,
  },
  getters: {
    getCode: s => s.code
  },
  actions: {},
  mutations: {},
  strict: process.env.NODE_ENV !== 'production'
})
