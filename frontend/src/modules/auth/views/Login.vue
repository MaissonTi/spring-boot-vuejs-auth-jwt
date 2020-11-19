<template>
  <v-container fill-height>
    <v-layout justify-center align-center>
      <v-flex xs12 sm6 md4 lg3 xl3>
        <v-toolbar-title class="my-12 align-center d-flex justify-center">
          <h1 class="font-weight-black logo">Academy</h1>
        </v-toolbar-title>

        <v-alert v-model="showError" type="error">
          {{ error }}
        </v-alert>

        <v-card class="elevation-12">
          <v-toolbar color="primary" dark>
            <v-toolbar-title> Login </v-toolbar-title>
            <v-spacer />
            <v-progress-circular
              v-show="isLoading"
              indeterminate
              color="white"
              width="2"
            />
          </v-toolbar>

          <v-card-text>
            <v-form>
              <v-text-field
                prepend-icon="email"
                name="email"
                label="Email"
                type="email"
                :error-messages="emailErrors"
                :success="!$v.user.email.$invalid"
                v-model.trim="$v.user.email.$model"
              />
              <v-text-field
                prepend-icon="lock"
                name="password"
                label="Senha"
                type="password"
                :error-messages="passwordErrors"
                :success="!$v.user.password.$invalid"
                v-model.trim="$v.user.password.$model"
              />
            </v-form>
          </v-card-text>

          <v-card-actions>
            <v-spacer />
            <v-btn
              :disabled="$v.$invalid"
              color="primary"
              large
              @click="submit"
            >
              Login
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { required, email, minLength } from 'vuelidate/lib/validators'
import { mapMutations } from 'vuex'

import { login } from '../services/auth-service'
export default {
  name: 'Login',
  data: () => ({
    error: undefined,
    showError: false,
    isLogin: true,
    isLoading: false,
    user: {
      email: '',
      password: ''
    }
  }),
  validations () {
    const validations = {
      user: {
        email: {
          required,
          email
        },
        password: {
          required,
          minLength: minLength(6)
        }
      }
    }
    return validations
  },
  computed: {
    emailErrors () {
      const errors = []
      const email = this.$v.user.email
      if (!email.$dirty) { return errors }
      !email.required && errors.push('Email is required!!')
      !email.email && errors.push('Enter a valid email address!')
      return errors
    },
    passwordErrors () {
      const errors = []
      const password = this.$v.user.password
      if (!password.$dirty) { return errors }
      !password.required && errors.push('Password required!')
      !password.minLength && errors.push(`Enter at least ${password.$params.minLength.min} characters!`)
      return errors
    }
  },
  methods: {
    ...mapMutations({
      setUsetAuth: 'SET_USER_AUTH'
    }),
    async submit () {
      this.isLoading = true

      const { email, password } = this.user

      try {
        const response = await login({ username: email, password })

        this.setUsetAuth(response)

        this.$router.push(this.$route.query.redirect || '/')
      } catch (error) {
        console.log(error)
        this.error = 'Invalid email or password!'
        this.showError = true
      } finally {
        this.isLoading = false
      }
    }
  }
}
</script>
<style lang="scss">
.logo {
  letter-spacing: 10px;
  text-shadow: 5px 5px #563dea45;
}
</style>
