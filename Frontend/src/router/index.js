import Vue from "vue";
import Router from "vue-router";

// import * as CONFIG from '../config/index';

//frontend
import HomePage from "@/views/frontend/HomePage";


Vue.use(Router);

export default new Router({
  // scroll to top
  mode: 'history',
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      return { x: 0, y: 0 }
    }
  },
  routes: [
    {
      path: "/",
      name: "HomePage",
      component: HomePage,
      props: {
        showSelectSpace: true
      }
    },
    {
      path: '/login',
      name: 'Login',
      component: () => import('@/views/frontend/pages/Login'),
    },
    //admin
    {
      path: '/admin',
      name: 'dashboards',
      meta: {
        authRequired: true,
      },
      component: () => import('@/views/admin/dashboards'),
    },
    {
      path: '/admin/role',
      name: 'role',
      meta: {
        authRequired: true,
      },
      component: () => import('@/views/admin/role'),
    },

    //=============== router For tools =======
    {
      path: "/diagram",
      name: "Diagram",
      component: () => import('@/views/frontend/tools/diagram'),
      props: {
        showSelectSpace: false
      }
    },   
  ]
});