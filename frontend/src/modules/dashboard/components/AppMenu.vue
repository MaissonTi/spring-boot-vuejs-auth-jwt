<template>
  <v-navigation-drawer
    v-model="Sidebar_drawer"
    :dark="SidebarColor !== 'white'"
    :color="SidebarColor"
    mobile-break-point="960"
    clipped
    :right="$vuetify.rtl"
    mini-variant-width="70"
    app
    id="main-sidebar"
  >
    <v-list dense nav>
      <v-list-item two-line class="px-0">
        <v-list-item-avatar>
          <img
            src="https://www.flaticon.com/svg/static/icons/svg/149/149071.svg"
          />
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title>{{ User.name }}</v-list-item-title>
          <v-list-item-subtitle class="caption"
            >Dev Full Stack</v-list-item-subtitle
          >
        </v-list-item-content>
      </v-list-item>
      <v-list-item
        v-for="item in items"
        :key="item.title"
        :to="item.to"
        :active-class="`success white--text`"
        link
      >
        <v-list-item-icon>
          <v-icon>{{ item.icon }}</v-icon>
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title>{{ item.title }}</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

    </v-list>
  </v-navigation-drawer>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: 'Sidebar',
  props: {
    expandOnHover: {
      type: Boolean,
      default: false
    }
  },
  data: () => ({
    items: [
      {
        title: 'Dashboard',
        icon: 'dashboard',
        to: '/'
      },
      {
        title: 'Student',
        icon: 'person',
        to: '/student/'
      }

    ]
  }),
  computed: {
    ...mapState(['SidebarColor', 'SidebarBg', 'User']),
    Sidebar_drawer: {
      get () {
        return this.$store.state.Sidebar_drawer
      },
      set (val) {
        this.$store.commit('SET_SIDEBAR_DRAWER', val)
      }
    }
  },
  watch: {
    '$vuetify.breakpoint.smAndDown' (val) {
      this.$emit('update:expandOnHover', !val)
    }
  },

  methods: {}
}
</script>
<style lang="scss">
#main-sidebar {
  box-shadow: 1px 0 20px rgba(0, 0, 0, 0.08);
  -webkit-box-shadow: 1px 0 20px rgba(0, 0, 0, 0.08);
  .v-navigation-drawer__border {
    display: none;
  }
  .v-list {
    padding: 8px 15px;
  }
  .v-list-item {
    &__icon--text,
    &__icon:first-child {
      justify-content: center;
      text-align: center;
      width: 20px;
    }
  }
}
</style>
