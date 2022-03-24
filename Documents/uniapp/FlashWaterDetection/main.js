import App from './App'

import store from './store'




Vue.prototype.$store=store
Vue.config.productionTip = false
Vue.prototype.baseUrl="http://localhost:7777/citylife"
Vue.prototype.appid="wxd11371c8691f221d"


// #ifndef VUE3
import Vue from 'vue'
Vue.config.productionTip = false
App.mpType = 'app'


const app = new Vue({
    ...App,
	store
})
app.$mount()
// #endif

// #ifdef VUE3
import { createSSRApp } from 'vue'
export function createApp() {
  const app = createSSRApp(App)
  return {
    app
  }
}
// #endif