<template>
  <v-app-bar app clipped-left clipped-right color="success" dark>
    <v-app-bar-nav-icon
      class="d-block d-md-none"
      @click="$vuetify.breakpoint.smAndDown ? setSidebarDrawer(!Sidebar_drawer) : $emit('input', !value)"
    />
    <v-toolbar-title class="align-center d-flex">
      <span class="font-weight-black"> AEducation </span>
      <span class="logo-text ml-2"> Academy </span>
    </v-toolbar-title>

    <v-spacer />

    <v-btn icon @click="showLogoutDialog = true">
      <v-icon>exit_to_app</v-icon>
    </v-btn>

    <v-dialog v-model="showLogoutDialog" max-width="250px">
      <v-card>
        <v-card-title>
          <h3 class="subheading">Do you really want to leave?</h3>
        </v-card-title>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="success" text @click="showLogoutDialog = false"
            >No</v-btn
          >
          <v-btn color="success" text @click="logout">Yes</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-app-bar>
</template>
<script>

import { mapState, mapMutations } from 'vuex'
import { logout } from '@/modules/auth/services/auth-service'
export default {
  name: 'Header',

  components: {},

  props: {
    value: {
      type: Boolean,
      default: false
    }
  },
  data: () => ({
    showLogoutDialog: false,
    userprofile: [
      { title: 'My Profile' }
    ],
    href () {
      return undefined
    }
  }),

  computed: {
    ...mapState(['Sidebar_drawer', 'Title', 'User'])
  },

  methods: {
    ...mapMutations({
      setSidebarDrawer: 'SET_SIDEBAR_DRAWER'
    }),
    logout (e) {
      this.$router.push('/login')
      logout()
    }
  }
}
</script>
