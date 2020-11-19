<template>
  <v-container>
    <v-row dense>
      <v-col cols="3">
        <v-card class="blue white--text">
          <v-card-title class="headline">
            {{ total }}
          </v-card-title>

          <v-card-subtitle class="white--text">Total students</v-card-subtitle>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>

export default {
  name: 'Home',
  data () {
    return {
      total: 0
    }
  },
  mounted () {
    this.initialize()
  },
  methods: {
    async initialize (search = '') {
      try {
        const response = await this.$http.get('/student')

        this.total = response.data.totalElements || 0
      } catch (error) {
        console.log(error)
      } finally {
        this.inputSearch = ''
        this.loading = false
      }
    }
  }
}
</script>
