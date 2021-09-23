import axios from 'axios'

export default {
  namespaced: true,
  state: {
    country: null,
  },
  getters: {
    getCities: s => s.country,
  },
  mutations: {
    setCities: (s, country) => s.country = country
  },
  actions: {
    async apiCities({ commit } ) {
      await axios({
        url: `platform/cities`,
        method: 'GET'
      }).then(response => {
        console.log(response.data.data)
        commit('setCities', response.data.data)
      }).catch(error => {console.log(error)})
    },
  }
}
