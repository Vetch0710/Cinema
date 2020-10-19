/**
 * @author chuzhixin 1204505056@qq.com （不想保留author可删除）
 * @description 路由守卫，目前两种模式：all模式与intelligence模式
 */
import router from "@/router";
import store from "@/store";
import VabProgress from "nprogress";
import "nprogress/nprogress.css";
import getPageTitle from "@/utils/pageTitle";
import {
  authentication,
  loginInterception,
  progressBar,
  recordRoute,
  routesWhiteList,
} from "./settings";
import Vue from "vue";

VabProgress.configure({
  easing: "ease",
  speed: 500,
  trickleSpeed: 200,
  showSpinner: false,
});

//路由跳转之前执行该方法
router.beforeResolve(async (to, from, next) => {
  console.log(
    from.path + "----------------路由解析跳转之前---------------" + to.path
  );

  if (progressBar) VabProgress.start(); //如果进度条设置为true  启动进度条
  let hasToken = store.getters["user/accessToken"]; //查看是否有token：获取token  未登录为null
  if (!loginInterception) hasToken = true; //   loginInterception为 是否开启登录拦截

  if (hasToken) {
    //有token：已经登陆
    console.log("已登录");
    if (to.path === "/login") {
      //如果目标路由是login
      next({ path: "/" }); //跳转到首页
      if (progressBar) VabProgress.done(); //进度条结束
    } else {
      const hasPermissions = //判断登陆用户 权限属性是否为空
        store.getters["user/permissions"] &&
        store.getters["user/permissions"].length > 0;
      if (hasPermissions) {
        //不为空：进入
        console.log("有权限，进入");
        next();
      } else {
        //为空
        console.log("没有权限");
        try {
          let permissions; //获取用户权限
          if (!loginInterception) {
            //settings.js loginInterception为false时，创建虚拟权限
            await store.dispatch("user/setPermissions", ["customer"]);
            // "admin","customer","boss"
            permissions = "customer";
          } else {
            //有登陆拦截时  获取用户权限
            permissions = await store.dispatch("user/getUserInfo");
          }

          let accessRoutes = []; //获取用户可进入的页面路由
          if (authentication === "intelligence") {
            accessRoutes = await store.dispatch(
              "routes/setRoutes",
              permissions
            );
          } else if (authentication === "all") {
            accessRoutes = await store.dispatch("routes/setAllRoutes");
          }
          console.log("以获得permissions+++++" + permissions);
          router.addRoutes(accessRoutes);
          // console.log(router)
          // if (
          //   permissions.indexOf("manager") !== -1 ||
          //   permissions.indexOf("boss") !== -1
          // ) {
          //   next({ path: "/manager" });
          // } else {
            next({ ...to, replace: true });
          // }
        } catch {
          await store.dispatch("user/resetAccessToken");
          if (progressBar) VabProgress.done();
        }
      }
    }
  } else {
    //未登录

    if (routesWhiteList.indexOf(to.path) !== -1) {
      //如果是不经过token校验的路由 则跳转
      /* if (to.path === "/login") {
        console.log("66666666666666666666");
        to.query.type = from.query.type;
        console.log(from.query.type);
        console.log(from.params.type);
      }*/
      next();
    } else {
      console.log("to.path++++++++++++++++" + to.path);

      if (recordRoute) {
        //token失效回退到登录页时是否记录本次的路由
        if (to.path === "/manager") {
          next(`/login?redirect=${to.path}&&type=manager`);
        } else {
          next(`/login?redirect=${to.path}&&type=customer`);
        }
      } else {
        next("/login");
      }

      if (progressBar) VabProgress.done();
    }
  }
  document.title = getPageTitle(to.meta.title);
});//路由跳转之前执行该方法





router.afterEach(() => {
  if (progressBar) VabProgress.done();
});
