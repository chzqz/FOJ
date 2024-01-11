import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '../views/Main.vue'
import Home from '../views/Home.vue'
import User from '../views/User.vue'
import Login from  '../views/Login'
import Answer from '../views/Answer'
import FullScreen from '../views/FullScreen'
import Question from '../views/Question'
import Text from '../views/Text'

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
          meta: {
            title: 'Home'
          }
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
        },
        {
          // 当 /user/:id/posts 匹配成功
          // UserPosts 将被渲染到 User 的 <router-view> 内部
          path: 'question',
          name:'question',
          component: Question,
        },
        {
          // 当 /user/:id/posts 匹配成功
          // UserPosts 将被渲染到 User 的 <router-view> 内部
          path: 'text',
          name:'text',
          component: Text,
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