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

// 添加请求拦截器
axios.interceptors.request.use(function (config) {
  // 在发送请求之前做些什么
  console.log(config);
  return config;
}, function (error) {
  // 对请求错误做些什么
  return Promise.reject(error);
});

// 添加响应拦截器
axios.interceptors.response.use(function (response) {
  // 2xx 范围内的状态码都会触发该函数。
  // 对响应数据做点什么
  return response;
}, function (error) {
  // 超出 2xx 范围的状态码都会触发该函数。
  // 对响应错误做点什么
  return Promise.reject(error);
});

axios.defaults.withCredentials = true

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
