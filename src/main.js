import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue'
import router from './router';
import Cookie from 'js-cookie'
import axios from 'axios'
Vue.prototype.$axios = axios
Vue.config.productionTip = false


Vue.use(ElementUI)
/* //按需引入
Vue.use(Row)
Vue.use(Button) */


new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
