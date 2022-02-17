import Vue from 'vue';
import App from './App';
import router from './router';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'
// 引入全局样式
import './assets/css/global.css'
// 引入icon
import './assets/font/iconfont.css'
import './axios'

import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

import './permission.js' // 路由拦截
import store from './store'

// use
Vue.use(mavonEditor)

// 开发提示
Vue.config.productionTip = false


Vue.prototype.$axios = axios
Vue.use(ElementUI)
new Vue({
  el: '#app',
  store,
  router,
  render: h => h(App)
})
