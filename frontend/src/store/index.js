
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    Sidebar_drawer: null,
    Customizer_drawer: false,
    SidebarColor: 'white',
    SidebarBg: '',
    Title: '',
    User: {
      name: 'Administrator'
    }
  },
  mutations: {
    SET_SIDEBAR_DRAWER (state, payload) {
      state.Sidebar_drawer = payload
    },
    SET_CUSTOMIZER_DRAWER (state, payload) {
      state.Customizer_drawer = payload
    },
    SET_SIDEBAR_COLOR (state, payload) {
      state.SidebarColor = payload
    },
    SET_TITLE (state, payload) {
      state.Title = payload
    },
    SET_USER_AUTH (state, payload) {
      state.User = payload
    }
  },
  actions: {

  }
})
