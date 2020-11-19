import Vue from 'vue'
import querystring from 'querystring'
const AUTH_TOKEN = 'aeducation-token'
const CLIENT = 'aeducation'
const SECRET = '@education'

const setToken = async token => {
  if (typeof window.localStorage !== 'undefined' && token) {
    window.localStorage.setItem(AUTH_TOKEN, token)
  }
}

const login = async ({ username, password }) => {
  const options = {
    params: {},
    withCredentials: true,
    auth: {
      username: CLIENT,
      password: SECRET
    },
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
  }

  const form = querystring.stringify({ grant_type: 'password', username, password })

  const response = await this.$http.post('/oauth/token', form, options)

  const { data } = response

  // this.$http.defaults.headers.common.Authorization = `Bearer ${data.access_token}`
  // Vue.prototype.$http.defaults.headers.common.Authorization = `Bearer ${data.access_token}`

  await setToken(data.access_token)
  return data
}

export {
  AUTH_TOKEN,
  login
}
