import Vue from "vue";
import VueRouter from "vue-router";
import CusLayout from "@/components/NavSearch/index";
import Layout from "@/components/managerlayouts";
// import Layout from "@/components/managerlayouts/test/index";
import LeftNav from "@/components/customerLeftNav/customerleftNav";
// import EmptyLayout from "@/layouts/EmptyLayout";
// import { publicPath, routerMode } from "@/config/settings";

Vue.use(VueRouter);
export const constantRoutes = [
  //网站首页
  {
    path: "",
    redirect: "index",
    hidden: true,
    component: CusLayout,
    children: [{
      path: "index",
      name: "Index",
      component: () => import("@/views/index/index"),
    }, ],
  },
  //登陆界面
  {
    path: "/login",
    component: () => import("@/views/login/index"),
    hidden: true,
  },
  //注册界面
  {
    path: "/register",
    component: () => import("@/views/register/index"),
    hidden: true,
  },

  //忘记密码界面
  {
    path: "/resetPassword",
    component: () => import("@/views/resetPassword/index"),
    hidden: true,
  },
  {
    path: "/movies",
    hidden: true,
    component: CusLayout,
    children: [
      //电影详情界面
      {
        path: "movieDetail",
        name: "movieDetail",
        component: () => import("@/views/movieDetail/index"),
      },
      {
        path: "selectSession",
        name: "SelectSession",
        components: {
          default: () => import("@/views/customer/ticket/selectSession/index"),
          steps: () => import("@/views/customer/ticket/index")
        },
        meta: {
          title: "选择场次",
        },
      },
      {
        path: "seatSelection",
        name: "SeatSelection",
        components: {
          default: () => import("@/views/customer/ticket/seatSelection/index"),
          steps: () => import("@/views/customer/ticket/index"),
        },
        meta: {
          title: "座位选择",
        },
      },
    ],
  },

  {
    path: "/401",
    name: "401",
    component: () => import("@/views/401"),
    hidden: true,
  },
  {
    path: "/404",
    name: "404",
    component: () => import("@/views/404"),
    hidden: true,
  },
];

export const asyncRoutes = [
  //顾客订单生成界面
  {
    path: "/ticket",
    redirect: "noRedirect",
    component: CusLayout,
    meta: {
      permissions: ["customer"],
    },
    children: [
      //选择场次界面
      {
        path: "/order",
        component: () => import('@/views/customer/ticket/index'),
        children: [
          //选择场次界面
          {
            path: "orderGenerated",
            name: "OrderGenerated",
            components: {
              default: () => import("@/views/customer/ticket/orderGenerated/index"),
              steps: () => import("@/views/customer/ticket/index")
            },
            meta: {
              title: "订单生成",
              permissions: ["customer"],
            },
          },

          {
            path: "orderFinished",
            name: "orderFinished",
            components: {
              default: () => import("@/views/customer/ticket/orderFinished/index"),
              steps: () => import("@/views/customer/ticket/index")
            },
            meta: {
              title: "购票成功",
              permissions: ["customer"],
            },
          },
        ]
      }
    ],
  },

  //用户个人中心首页
  {
    path: "/customerCenter",
    component: CusLayout,
    redirect: "customerCenter/index",
    meta: {
      permissions: ["customer"],
    },
    children: [{
      path: "index",
      name: "UserCenterIndex",
      components: {
        left: LeftNav,
        default: () => import("@/views/customer/UserCenter/index"),
      },
      meta: {
        title: "个人中心",
        permissions: ["customer"],
        affix: true,
      },
    }, ],
  },
  //用户评价详情
  {
    path: "/customerEvaluation",
    component: CusLayout,
    redirect: "customerEvaluation/index",
    meta: {
      permissions: ["customer"],
    },
    children: [{
      path: "index",
      name: "customerEvaluation",
      components: {
        left: LeftNav,
        default: () =>
          import("@/views/customer/UserCenter/customerEvaluation/index"),
      },
      meta: {
        title: "我的评价",
        permissions: ["customer"],
      },
    }, ],
  },
  //用户个人信息
  {
    path: "/customerInfo",
    component: CusLayout,
    redirect: "customerInfo/index",
    meta: {
      permissions: ["customer"],
    },
    children: [{
      path: "index",
      name: "Information",
      components: {
        left: LeftNav,
        default: () =>
          import("@/views/customer/UserCenter/Information/index"),
      },
      meta: {
        title: "个人信息",
        permissions: ["customer"],
      },
    }, ],
  },
  //用户订单详情
  {
    path: "/customerOrder",
    component: CusLayout,
    redirect: "noRedirect",
    meta: {
      title: "我的订单",
      permissions: ["customer"],
    },
    children: [{
        path: "totalOrder",
        name: "Order",
        components: {
          left: LeftNav,
          default: () => import("@/views/customer/UserCenter/Order/index"),
        },
        beforeEnter: (to, from, next) => {
          to.params.type = "全部订单";
          next();
        },
        meta: {
          title: "全部订单",
          permissions: ["customer"],
        },
      },
      {
        path: "finish",
        name: "finish",
        components: {
          left: LeftNav,
          default: () => import("@/views/customer/UserCenter/Order/index"),
        },
        beforeEnter: (to, from, next) => {
          to.params.type = "已完成";
          next();
        },
        meta: {
          title: "已完成",
          permissions: ["customer"],
        },
      },
      {
        path: "waitingPay",
        name: "waitingPay",
        components: {
          left: LeftNav,
          default: () => import("@/views/customer/UserCenter/Order/index"),
        },
        beforeEnter: (to, from, next) => {
          to.params.type = "待支付";
          next();
        },
        meta: {
          title: "待支付",
          permissions: ["customer"],
        },
      },
      {
        path: "waitingEvaluation",
        name: "waitingEvaluation",
        components: {
          left: LeftNav,
          default: () => import("@/views/customer/UserCenter/Order/index"),
        },
        beforeEnter: (to, from, next) => {
          to.params.type = "待评价";
          next();
        },
        meta: {
          title: "待评价",
          permissions: ["customer"],
        },
      },
    ],
  },

  //后台  个人中心
  {
    path: "/manager",
    component: Layout,
    redirect: "manager/index",
    children: [{
      path: "index",
      name: "managerIndex",
      component: () => import("@/views/manager/index"),
      meta: {
        title: "首页",
        icon: "el-icon-location",
        permissions: ["manager", "boss"],
        affix: true,
      },
    }, ],
  },
  //后台  用户管理
  {
    path: "/userManagement",
    component: Layout,
    redirect: "noRedirect",
    name: "managerIndex",
    meta: {
      icon: "el-icon-location",
      title: "用户管理",
      permissions: ["manager", "boss"],
    },
    children: [{
        path: "customerInfo",
        name: "customerInfo",
        component: () => import("@/views/manager/userManagement/customerInfo"),
        meta: {
          title: "顾客信息管理",
          permissions: ["manager", "boss"],
        },
      },
      {
        path: "ManagerInfo",
        name: "ManagerInfo",
        component: () => import("@/views/manager/userManagement/ManagerInfo"),
        meta: {
          title: "管理员信息管理",
          permissions: ["boss"],
        },
      },
      {
        path: "personalInfo",
        name: "personalInfo",
        component: () => import("@/views/manager/userManagement/personalInfo"),
        meta: {
          title: "个人信息管理",
          permissions: ["manager", "boss"],
        },
      },
    ],
  },
  //后台  影库管理
  {
    path: "/movieManagement",
    component: Layout,
    redirect: "movieManagement/index",
    meta: {
      icon: "el-icon-location",
      title: "影库管理",
      permissions: ["manager", "boss"],
    },
    children: [{
        path: "index",
        name: "MovieManagement",
        component: () => import("@/views/manager/MovieManagement/index"),
        meta: {
          title: "已有电影",
          permissions: ["manager", "boss"],
        },
      },
      {
        path: "addMovie",
        name: "addMovie",
        component: () =>
          import("@/views/manager/MovieManagement/addMovie/index"),
        meta: {
          title: "添加电影",
          permissions: ["manager", "boss"],
        },
      },
      {
        path: "shelvesMovie",
        name: "shelvesMovie",
        component: () =>
          import("@/views/manager/MovieManagement/shelvesMovie/index"),
        meta: {
          title: "已下架电影",
          permissions: ["manager", "boss"],
        },
      },
    ],
  },
  //后台  评价管理
  {
    path: "/evaluationManagement",
    component: Layout,
    redirect: "evaluationManagement/index",
    children: [{
      path: "index",
      name: "EvaluationManagement",
      component: () => import("@/views/manager/EvaluationManagement/index"),
      meta: {
        icon: "el-icon-location",
        title: "评价管理",
        permissions: ["manager", "boss"],
      },
    }, ],
  },
  //后台  订单管理
  {
    path: "/orderManagement",
    component: Layout,
    redirect: "orderManagement/index",
    children: [{
      path: "index",
      name: "orderManagement",
      component: () => import("@/views/manager/orderManagement/index"),
      meta: {
        icon: "el-icon-location",
        title: "订单管理",
        permissions: ["manager", "boss"],
      },
    }, ],
  },
  //后台  销售统计
  {
    path: "/salesStatistics",
    component: Layout,
    redirect: "salesStatistics/index",
    children: [{
      path: "index",
      name: "salesStatistics",
      component: () => import("@/views/manager/salesStatistics/index"),
      meta: {
        icon: "el-icon-location",
        title: "销售统计",
        permissions: ["manager", "boss"],
      },
    }, ],
  },
  //后台  用户提问
  {
    path: "/customerProblems",
    component: Layout,
    redirect: "customerProblems/index",
    children: [{
      path: "index",
      name: "customerProblems",
      component: () => import("@/views/manager/customerProblems/index"),
      meta: {
        icon: "el-icon-location",
        title: "顾客问题",
        permissions: ["manager", "boss"],
      },
    }, ],
  },
  /* {
    path: "/test",
    component: Layout,
    redirect: "noRedirect",
    children: [
      {
        path: "test",
        name: "Test",
        component: () => import("@/views/test/index"),
        meta: {
          title: "test",
          icon: "marker",
          permissions: ["admin"],
        },
      },
    ],
  }, */

  {
    path: "*",
    redirect: "/404",
    hidden: true,
  },
];

const router = new VueRouter({
  // base: "/dist/",
  scrollBehavior: () => ({
    y: 0,
  }),
  routes: constantRoutes,
});
//注释的地方是允许路由重复点击，如果你觉得框架路由跳转规范太过严格可选择放开
const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location, onResolve, onReject) {
  if (onResolve || onReject)
    return originalPush.call(this, location, onResolve, onReject);
  return originalPush.call(this, location).catch((err) => err);
};

export function resetRouter() {
  router.matcher = new VueRouter({
    // base: publicPath,
    // mode: routerMode,
    scrollBehavior: () => ({
      y: 0,
    }),
    routes: constantRoutes,
  }).matcher;
}

export default router;
