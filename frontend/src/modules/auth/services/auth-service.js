import api from '@/plugins/axios'
import querystring from 'querystring'
const AUTH_TOKEN = 'aeducation-token'
const CLIENT = 'aeducation'
const SECRET = '@education'

const setToken = async token => {
  if (typeof window.localStorage !== 'undefined' && token) {
    window.localStorage.setItem(AUTH_TOKEN, token)
  }
}

const removeToken = async () => {
  if (typeof window.localStorage !== 'undefined') {
    window.localStorage.removeItem(AUTH_TOKEN)
  }
}

const logout = async () => {
  removeToken()
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

  const response = await api.post('/oauth/token', form, options)

  const { data } = response

  api.defaults.headers.Authorization = `Bearer ${data.access_token}`

  await setToken(data.access_token)
  return data
}

export {
  AUTH_TOKEN,
  login,
  logout
}
