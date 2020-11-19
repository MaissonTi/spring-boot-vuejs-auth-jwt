import Vue from 'vue'
import VueRouter from 'vue-router'
import { AUTH_TOKEN } from '@/modules/auth/services/auth-service'

import auth from '@/modules/auth/router'
import dashboard from '@/modules/dashboard/router'

Vue.use(VueRouter)

const routes = [
  ...auth,
  ...dashboard,
  { path: '', redirect: '/login' }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach(async (to, from, next) => {
  if (to.matched.some(route => route.meta.requiresAuth)) {
    const token = window.localStorage.getItem(AUTH_TOKEN)
    const loginRoute = {
      path: '/login',
      query: { redirect: to.fullPath }
    }
    if (token) {
      try {
        return next()
      } catch (error) {
        console.log('Auto Login Error: ', error)
        return next(loginRoute)
      }
    }
    return next(loginRoute)
  }
  next()
})

export default router
