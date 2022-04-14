import App from './App'

import store from './store'


import uView from "uview-ui";
Vue.use(uView)
uni.$u.config.unit = 'rpx'
Vue.prototype.$store=store
Vue.config.productionTip = false
Vue.prototype.baseUrl="http://172.20.10.2:8081"
Vue.prototype.appid="wxcd5e2987ed498169"



import Vue from 'vue'
Vue.config.productionTip = false
App.mpType = 'app'


const app = new Vue({
    ...App,
	store
})

// http拦截器，此为需要加入的内容，如果不是写在common目录，请自行修改引入路径
import httpInterceptor from '@/common/http.interceptor.js'
// 这里需要写在最后，是为了等Vue创建对象完成，引入"app"对象(也即页面的"this"实例)
Vue.use(httpInterceptor, app)

// http接口API集中管理引入部分
import httpApi from '@/common/http.api.js'
Vue.use(httpApi, app)

app.$mount()


