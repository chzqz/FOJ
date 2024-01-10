import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '../views/Main.vue'
import Home from '../views/Home.vue'
import User from '../views/User.vue'
import Login from  '../views/Login'
import Answer from '../views/Answer'
import FullScreen from '../views/FullScreen'


Vue.use(VueRouter)
const routes = [    
    {
      path:'/',
      component: Main,
      
      children: [
        {
          // 当 /user/:id/posts 匹配成功
          // UserPosts 将被渲染到 User 的 <router-view> 内部
          path: 'home',
          name:'home',
          component: Home,
        },
        {
          // 当 /user/:id/posts 匹配成功
          // UserPosts 将被渲染到 User 的 <router-view> 内部
          path: 'user',
          component: User,
        },
        {
          path:'login',
          component:Login,
        }
      ],

    },
 
    {
      path:'/',
      component: FullScreen,
      
      children: [
      
        {
          // 当 /user/:id/posts 匹配成功
          // UserPosts 将被渲染到 User 的 <router-view> 内部
          path: 'answer',
          component: Answer,
        },
     ],

    },
    
  ]
  const router = new VueRouter({
    routes // (缩写) 相当于 routes: routes
  })

  export default router