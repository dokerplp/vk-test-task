import { createRouter, createWebHistory } from 'vue-router'
import Start from '@/views/Auth'

const routes = [
  {
    path: '/',
    name: 'home',
    component: Start
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Main.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router