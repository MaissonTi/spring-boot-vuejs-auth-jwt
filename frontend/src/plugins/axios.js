import Vue from 'vue'
import axios from 'axios'
import router from '@/router'
import { AUTH_TOKEN } from '@/modules/auth/services/auth-service'

const api = axios.create({
  baseURL: process.env.URL_API || 'http://localhost:8080'
})

api.interceptors.response.use(
  response => {
    return response
  },
  error => {
    if (error && error.response) {
      const { status } = error.response
      if (status === 401) {
        Vue.prototype.$toasted.error('Your expired page!!!', { icon: 'error_outline' })
        router.push('/login')
      }
    }

    return Promise.reject(error)
  }
)

api.interceptors.request.use(
  config => {
    config.headers.common.Authorization = `Bearer ${window.localStorage.getItem(AUTH_TOKEN)}`
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

Vue.prototype.$http = api

export default api
