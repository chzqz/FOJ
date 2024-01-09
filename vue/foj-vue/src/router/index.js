import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '../views/Main.vue'
import Home from '../views/Home.vue'
import User from '../views/User.vue'
import Login from  '../views/Login'
import NewLogin from '../views/NewLogin'
import Answer from '../views/Answer'


Vue.use(VueRouter)
const routes = [    
    {
      path:'/',
      component: Main,
      
      children: [
      
        {
          // 当 /user/:id/posts 匹配成功
          // UserPosts 将被渲染到 User 的 <router-view> 内部
          path: 'Home',
          name:'Home',
          component: Home,
        },
        {
          // 当 /user/:id/posts 匹配成功
          // UserPosts 将被渲染到 User 的 <router-view> 内部
          path: 'User',
          component: User,
        },
        {
          // 当 /user/:id/posts 匹配成功
          // UserPosts 将被渲染到 User 的 <router-view> 内部
          path: 'Answer',
          component: Answer,
        },
          {
            path: 'Login',
            component: Login,
          },
         
        
      


      ],

    },

    
  ]
  const router = new VueRouter({
    routes // (缩写) 相当于 routes: routes
  })

  export default router