<template>
<v-card  elevation="12">
<v-flex xs12 justify-center align-center>
  <v-data-table
    :headers="headers"
    :items="items"
    :loading="loading"
    name="list"
  >
    <template v-slot:top>
      <v-toolbar flat>
        <v-toolbar-title>Students </v-toolbar-title>

        <v-divider class="mx-4" inset vertical></v-divider>
        <v-text-field
          v-model="inputSearch"
          label="Enter student name"
          single-line
          hide-details
        ></v-text-field>
        <v-btn color="primary" class="ma-2" @click="search">
          <v-icon dark> search </v-icon>
        </v-btn>
        <v-divider class="mx-4" inset vertical></v-divider>
        <v-spacer></v-spacer>
        <v-btn color="primary" class="ma-2" @click="$router.push('form')">
          New Student
        </v-btn>

      </v-toolbar>
    </template>
    <template v-slot:item.actions="{ item }">
      <v-icon class="mr-2" color="primary" @click="editItem(item)">
        edit
      </v-icon>
      <v-icon color="pink" @click="deleteItem(item)"> delete </v-icon>
    </template>
    <template v-slot:no-data>
      <v-btn color="primary" @click="() => initialize()"> Reset </v-btn>
    </template>
  </v-data-table>
</v-flex >
  </v-card >

</template>

<script>

export default {
  name: 'StudentList',
  data: () => ({
    headers: [
      { text: 'Register', align: 'start', sortable: false, value: 'id' },
      { text: 'Student', value: 'name' },
      { text: 'CPF', value: 'cpf' },
      { text: 'Actions', value: 'actions', sortable: false }
    ],
    inputSearch: '',
    loading: false,
    items: []
  }),
  mounted () {
    this.initialize()
  },
  methods: {
    async initialize (search = '') {
      try {
        this.loading = true

        const params = {}

        if (search && search.trim()) params.name = search

        const response = await this.$http.get('/student', { params })

        this.items = response.data.content
      } catch (error) {

      } finally {
        this.inputSearch = ''
        this.loading = false
      }
    },
    async search () {
      await this.initialize(this.inputSearch)
    },
    editItem ({ id }) {
      this.$router.push(`form/${id}`)
    },
    async deleteItem ({ id }) {
      this.$toasted.clear()
      this.$toasted.show('Are you sure you want to remove this student? </br>', {
        duration: null,
        closeOnSwipe: false,
        action: [
          {
            text: 'Yes',
            onClick: async (e, toastObject) => {
              try {
                this.loading = true
                await this.$http.delete(`/student/${id}`)
                await this.initialize()

                this.$toasted.success('Student removed', { icon: 'check_circle' })
              } catch (error) {
                this.$toasted.error('Error while deleting!', { icon: 'error_outline' })
              } finally {
                this.loading = false
              }
              toastObject.goAway(0)
            }
          },
          {
            text: 'No',
            onClick: (e, toastObject) => {
              toastObject.goAway(0)
            }
          }
        ]
      })
    }
  }
}
</script>
