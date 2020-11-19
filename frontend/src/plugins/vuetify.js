import Vue from 'vue'
import Vuetify from 'vuetify'
import 'roboto-fontface/css/roboto/roboto-fontface.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'

Vue.use(Vuetify)

const theme = {
  primary: '#563dea',
  secundary: '#1e88e5',
  info: '#1e88e5',
  success: '#563dea',
  accent: '#fc4b6c',
  default: '#563dea'
}

export default new Vuetify({
  icons: {
    iconfont: 'md'
  },
  theme: {
    themes: {
      dark: theme,
      light: theme
    }
  }
})
