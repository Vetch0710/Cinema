import Vue from 'vue'
import App from './App.vue'
import store from "@/store";
import router from "@/router";
import ElementUI from 'element-ui'; //导入element组件
import 'element-ui/lib/theme-chalk/index.css';
import "@/config/permission";
import Vab from "@/utils/vab";  //全局的设置(弹窗，token等等)


//
// const { mockXHR } = require("@/utils/static");
// mockXHR()

Vue.use(Vab);
Vue.use(ElementUI);
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
