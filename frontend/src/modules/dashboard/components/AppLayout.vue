<template>
  <v-app id="inspire">
    <AppToolbar v-model="expandOnHover"/>
    <AppMenu :expand-on-hover.sync="expandOnHover"/>
    <v-content>
      <v-container >
        <router-view />
      </v-container>
    </v-content>

    <Footer></Footer>
  </v-app>
</template>

<script>
import AppToolbar from './AppToolbar.vue'
import AppMenu from './AppMenu.vue'
import { mapState, mapMutations } from 'vuex'
export default {
  name: 'AppLayout',
  components: {
    AppToolbar,
    AppMenu
  },

  props: {
    source: String
  },
  data: () => ({
    expandOnHover: false
  }),
  computed: {
    ...mapState(['Customizer_drawer'])
  },

  methods: {
    ...mapMutations({
      setCustomizerDrawer: 'SET_CUSTOMIZER_DRAWER'
    })
  }
}
</script>
