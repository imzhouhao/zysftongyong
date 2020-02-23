import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './registerServiceWorker'
import iview from 'iview'
import 'iview/dist/styles/iview.css'
/* eslint-disable */
import BaiduMap from 'vue-baidu-map'

Vue.use(BaiduMap, {
  // ak 是在百度地图开发者平台申请的密钥 详见 http://lbsyun.baidu.com/apiconsole/key */
  ak: 'h144y6GIT6NM3iOpRsghRQVUt5f4vtGQ'
})

require('echarts/extension/bmap/bmap');

Vue.config.productionTip = false
Vue.use(iview)
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
