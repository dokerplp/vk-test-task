import { createRouter, createWebHistory } from 'vue-router'
import Auth from '@/views/Auth'
import Main from '@/views/Main'
import store from '@/store'
import Profile from '@/components/Profile'
import Friends from '@/components/Friends'
import Messages from '@/components/Messages'

const routes = [
  {
    path: '/login',
    name: 'login',
    component: Auth
  },
  {
    path: '/home',
    name: 'home',
    component: Main,
    children: [
      { path: '', component: Profile },
      { path: 'friends', component: Friends },
      { path: 'messages', component: Messages }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  if (!store.getters.AUTH && to.name !== 'login') {
    next({ name: 'login' })
  } else {
    next()
  }
})

export default router
