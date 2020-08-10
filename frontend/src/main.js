import '@babel/polyfill'
import 'mutationobserver-shim'
import Vue from 'vue'
// import './plugins/bootstrap-vue'
import App from './App.vue'
import router from './router/index'
import store from './store/index'
import axios from 'axios'
// import vuetify from './plugins/vuetify'
<<<<<<< HEAD
//import { BootstrapVue, BootstrapVueIcons } from 'bootstrap-vue'
import VueSimpleAlert from 'vue-simple-alert';
// import 'bootstrap/dist/css/bootstrap.css'
// import 'bootstrap-vue/dist/bootstrap-vue.css'
Vue.use(VueSimpleAlert);
=======
// import { BootstrapVue, BootstrapVueIcons } from 'bootstrap-vue'

// import 'bootstrap/dist/css/bootstrap.css'
// import 'bootstrap-vue/dist/bootstrap-vue.css'

>>>>>>> 8bcdc55788b3d48045796a9ef7684be15f2183f1
// Vue.use(BootstrapVue)
// Vue.use(BootstrapVueIcons)

Vue.config.productionTip = false

axios.defaults.baseURL = process.env.VUE_APP_REMOTE_API;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
