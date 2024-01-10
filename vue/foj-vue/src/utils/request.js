import axios from "axios";
import ElementUI from 'element-ui';

const http= axios.create({
    //通用请求的地址前缀
    baseURL:'http://localhost:8080/',
    timeout:10000,
    withCredentials:true,
})
// 添加请求拦截器
http.interceptors.request.use(function (config) {
  console.log('请求拦截器： ', config);
    // 在发送请求之前做些什么
    return config;
  }, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
  });

// 添加响应拦截器
http.interceptors.response.use(function (response) {
    // 2xx 范围内的状态码都会触发该函数。
    // 对响应数据做点什么
    if(response.data.code!=200){
      ElementUI.Message.error(response.data.msg);
    }
    return response;
  }, function (error) {
    // 超出 2xx 范围的状态码都会触发该函数。
    // 对响应错误做点什么
    return Promise.reject(error);
  });

export default http;