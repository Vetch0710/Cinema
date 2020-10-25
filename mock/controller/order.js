const orderInfo = [{
    orderId: '1',
    orderImg: 'https://p0.meituan.net/movie/202ea88abd2abf2aa1964487d61edab64556414.jpg@90w_90h_1e_1c',
    orderSeat: ["32"],
    orderTime: "2020-6-6 17:00",
    orderPrice: "100",
    orderStatus: "已完成",
    movieName: "我和我的祖国",
    arrangementPlace: "1"
  },
  {
    orderId: '2',
    orderImg: 'https://p0.meituan.net/movie/202ea88abd2abf2aa1964487d61edab64556414.jpg@90w_90h_1e_1c',
    orderSeat: ["3排2座", "1排1座"],
    orderTime: "2020-10-24 19:40:23",
    orderPrice: "100",
    orderStatus: "已完成",
    movieId: 1,
    movieName: "我和我的祖国",
    arrangementPlace: "1",
    arrangementTime: "2020-10-24 15:38"
  },
  {
    orderId: '3',
    orderImg: 'https://p0.meituan.net/movie/202ea88abd2abf2aa1964487d61edab64556414.jpg@90w_90h_1e_1c',
    orderSeat: ["32", "22", "11"],
    orderTime: "2020-6-6 17:00",
    orderPrice: "100",
    orderStatus: "已完成",
    movieName: "我和我的祖国",
    arrangementPlace: "1"
  },
  {
    orderId: '4',
    orderImg: 'https://p0.meituan.net/movie/202ea88abd2abf2aa1964487d61edab64556414.jpg@90w_90h_1e_1c',
    orderSeat: ["32"],
    orderTime: "2020-6-6 17:00",
    orderPrice: "100",
    orderStatus: "已完成",
    movieName: "我和我的祖国",
    arrangementPlace: "1"
  },
  {
    orderId: '5',
    orderImg: 'https://p0.meituan.net/movie/202ea88abd2abf2aa1964487d61edab64556414.jpg@90w_90h_1e_1c',
    orderSeat: ["32", "22", "11"],
    orderTime: "456",
    orderPrice: "100",
    orderStatus: "待评价",
    movieName: "123",
    arrangementPlace: "1"
  },
  {
    orderId: '6',
    orderImg: 'https://p0.meituan.net/moviemachine/ff399baffe786445b96e4e3d24fe1eeb675719.jpg@90w_90h_1e_1c',
    orderSeat: ["32", "22", "11", "65"],
    orderTime: "2020-6-6 17:00",
    orderPrice: "100",
    orderStatus: "待评价",
    movieName: "我和我的祖国",
    arrangementPlace: "1"
  },
  {
    orderId: '7',
    orderImg: 'https://p0.meituan.net/moviemachine/b2c5c74d33e45745fd3462e44b3698e18336620.jpg@90w_90h_1e_1c',
    orderSeat: ["32", "22", "11"],
    orderTime: "2020-6-6 17:08:23",
    orderPrice: "100",
    orderStatus: "已完成",
    movieName: "我和我的祖国",
    arrangementTime: "2020-6-6 14:43",
    arrangementPlace: "1"
  },
  {
    orderId: '8',
    orderImg: 'https://p0.meituan.net/movie/202ea88abd2abf2aa1964487d61edab64556414.jpg@90w_90h_1e_1c',
    orderSeat: ["3排2座", "1排1座"],
    orderTime: "2020-10-24 20:55:23",
    orderPrice: "100",
    orderStatus: "待支付",
    movieId: 1,
    movieName: "我和我的祖国",
    arrangementPlace: "1",
    arrangementTime: "2020-10-24 15:38"
  },
  {
    orderId: '9',
    orderImg: 'https://p0.meituan.net/movie/363e3a7e614d29b2847ff4e62afcd3f42168651.jpg@90w_90h_1e_1c',
    orderSeat: ["1", "2", "3"],
    orderTime: "2020-6-6 17:00",
    orderPrice: "100",
    orderStatus: "已完成",
    movieName: "我和我的祖国",
    arrangementPlace: "1"
  }
];


module.exports = [{
  url: "/Order/getOrderList",
  type: "post",
  response(config) {
    let type = config.body.type;
    let list = orderInfo.filter((item) => {
      if (type === '全部订单') {
        return item;
      } else {
        return item.orderStatus === type

      }
    })
    return {
      code: 200,
      msg: "success",
      data: {
        orderInfo: list
      },
    };
  },
}, {
  url: "/Order/getOrder",
  type: "post",
  response(config) {
    const orderId = config.body.orderId;
    console.log(orderId);
    let mockList = orderInfo.filter((item) => {
      if (item.orderId == orderId) return true;
      return false;
    });
    return {
      code: 200,
      msg: "success",
      data: mockList,
    };
  },
}];
