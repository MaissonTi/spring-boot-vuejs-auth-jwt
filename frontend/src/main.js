import Vue from 'vue'
import App from '@/App.vue'
import router from '@/router'
import store from '@/store'
import vuetify from '@/plugins/vuetify'
import vuelidate from '@/plugins/vuelidate'
import api from '@/plugins/axios'
import toasted from '@/plugins/toasted'

Vue.config.productionTip = false
Vue.config.ignoredElements = [
  'v-icon', 'v-card', 'v-card-text'
]

new Vue({
  router,
  store,
  vuetify,
  vuelidate,
  toasted,
  api,
  render: h => h(App)
}).$mount('#app')
