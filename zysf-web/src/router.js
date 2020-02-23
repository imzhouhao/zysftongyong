import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
    },
    {
      path: '/manage/company',
      name: 'manage-company',
      component: () => import(/* webpackChunkName: "about" */ './views/admin/company/index.vue')
    },
    {
      path: '/manage/industrial-category',
      name: 'manage-industrial-category',
      component: () => import(/* webpackChunkName: "about" */ './views/admin/industrial-category/index.vue')
    },
    {
      path: '/industrial-category',
      name: 'industrial-category-index',
      component: () => import(/* webpackChunkName: "about" */ './views/map/industrial-category/index.vue')
    },
    {
      path: '/industrial-category/tree',
      name: 'industrial-category-relation-chart',
      component: () => import(/* webpackChunkName: "about" */ './views/map/industrial-category/tree/index.vue')
    },
    {
      path: '/industrial-category/relation',
      name: 'industrial-category-relation-chart',
      component: () => import(/* webpackChunkName: "about" */ './views/map/industrial-category/relation-chart/index.vue')
    },
    {
      path: '/company',
      name: 'company-index',
      component: () => import(/* webpackChunkName: "about" */ './views/map/company/index.vue')
    },
    {
      path: '/company/heat-map',
      name: 'company-heat-map',
      component: () => import(/* webpackChunkName: "about" */ './views/map/company/heat-map')
    },
    {
      path: '/company/scatter-map',
      name: 'company-scatter-map',
      component: () => import(/* webpackChunkName: "about" */ './views/map/company/scatter-map')
    }
  ]
})
