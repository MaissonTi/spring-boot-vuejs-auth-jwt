<template>
  <v-container fluid class="down-top-padding">
    <v-layout justify-center align-center>
      <v-flex xs12>
        <v-alert v-model="showError" type="error">
          {{ error }}
        </v-alert>

        <v-card class="elevation-12">
          <v-toolbar class="success white--text elevation-0">
            <v-toolbar-title> Student Form </v-toolbar-title>
            <v-spacer />
            <v-progress-circular
              v-show="loading"
              indeterminate
              color="white"
              width="2"
            />
          </v-toolbar>

          <v-card-text>
            <v-form>
              <v-text-field
                name="name"
                label="Name"
                :error-messages="nameErrors"
                :success="!$v.student.name.$invalid"
                v-model.trim="$v.student.name.$model"
              />
              <v-text-field
                name="email"
                label="E-mail"
                type="email"
                :error-messages="emailErrors"
                :success="!$v.student.email.$invalid"
                v-model.trim="$v.student.email.$model"
              />
              <v-text-field
                name="ra"
                label="RA"
                :error-messages="raErrors"
                :success="!$v.student.ra.$invalid"
                v-model.trim="$v.student.ra.$model"
                :disabled="id && id > 0"
              />

              <v-text-field
                name="cpf"
                label="CPF"
                :error-messages="cpfErrors"
                :success="!$v.student.cpf.$invalid"
                v-model.trim="$v.student.cpf.$model"
                :disabled="id && id > 0"
              />
            </v-form>
          </v-card-text>

          <v-card-actions>
            <v-spacer />
            <v-btn @click="$router.push('/student/')"  large>
              Cancel
            </v-btn>
            <v-btn
              :disabled="$v.$invalid"
              color="primary"
              large
              @click="submit"
            >
              {{buttonName}}
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { required, email, minLength } from 'vuelidate/lib/validators'
import { validaterCPF } from '@/utils'
export default {
  name: 'StudentForm',
  props: {
    id: undefined
  },
  data: () => ({
    error: undefined,
    showError: false,
    loading: false,
    student: {
      name: '',
      email: '',
      ra: '',
      cpf: ''
    }
  }),
  mounted () {
    if (this.id) this.initialize()
  },
  validations () {
    const validations = {
      student: {
        name: {
          required,
          minLength: minLength(3)
        },
        email: {
          required,
          email
        },
        ra: {
          required
        },
        cpf: {
          required,
          validaterCPF
        }
      }
    }
    return validations
  },
  computed: {
    buttonName () {
      return this.id ? 'Edit' : 'Save'
    },
    nameErrors () {
      const errors = []
      const name = this.$v.student.name
      if (!name.$dirty) { return errors }
      !name.required && errors.push('name is required!!')
      return errors
    },
    emailErrors () {
      const errors = []
      const email = this.$v.student.email
      if (!email.$dirty) { return errors }
      !email.required && errors.push('Email is required!!')
      !email.email && errors.push('Enter a valid email address!')
      return errors
    },
    raErrors () {
      const errors = []
      const ra = this.$v.student.ra
      if (!ra.$dirty) { return errors }
      !ra.required && errors.push('ra is required!')

      return errors
    },
    cpfErrors () {
      const errors = []
      const cpf = this.$v.student.cpf
      if (!cpf.$dirty) { return errors }
      !cpf.required && errors.push('cpf is required!')
      !cpf.validaterCPF && errors.push('cpf is invalid!')

      return errors
    }
  },
  methods: {
    async submit () {
      this.isLoading = true

      try {
        if (this.id) {
          await this.$http.put(`/student/${this.id}`, this.student)
        } else {
          const { data } = await this.$http.post('/student', this.student)
          this.$router.push(`form/${data.id}`)
        }

        this.$toasted.success(`Success in ${this.id ? 'student editing' : 'saving the student'}`, { icon: 'check_circle' })

        this.error = undefined
        this.showError = false
      } catch (error) {
        console.log(error)
        if (error.response.status === 400) {
          this.error = error.response.data.split(':')[3]
        }
        this.showError = true
      } finally {
        this.isLoading = false
      }
    },
    async initialize () {
      try {
        this.loading = true

        const response = await this.$http.get(`/student/${this.id}`)

        this.student = response.data
      } catch (error) {
        console.log(error.response)
        if (error.response.status === 404) {
          this.$toasted.error('Student not found!', { icon: 'error_outline' })
          this.$router.push('/student/')
        }
      } finally {
        this.loading = false
      }
    }
  }
}
</script>
