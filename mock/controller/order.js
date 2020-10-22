const orderInfo =
    [
        {
            orderId: '1',
            orderImg:'https://p0.meituan.net/movie/202ea88abd2abf2aa1964487d61edab64556414.jpg@90w_90h_1e_1c',
            orderSeat: ["32"],
            orderTime: "2020-6-6 17:00",
            orderPrice:"100",
            orderStatus: "已完成",
            movieName: "我和我的祖国",
            arrangementPlace:"1"
        },
        {
            orderId: '2',
            orderImg:'https://p0.meituan.net/movie/202ea88abd2abf2aa1964487d61edab64556414.jpg@90w_90h_1e_1c',
            orderSeat: ["32", "11"],
            orderTime: "2020-6-6 17:00",
            orderPrice:"100",
            orderStatus: "已完成",
            movieName: "我和我的祖国",
            arrangementPlace:"1"
        },
        {
            orderId: '3',
            orderImg:'https://p0.meituan.net/movie/202ea88abd2abf2aa1964487d61edab64556414.jpg@90w_90h_1e_1c',
            orderSeat: ["32", "22", "11"],
            orderTime: "2020-6-6 17:00",
            orderPrice:"100",
            orderStatus: "已完成",
            movieName: "我和我的祖国",
            arrangementPlace:"1"
        },
        {
            orderId: '4',
            orderImg:'https://p0.meituan.net/movie/202ea88abd2abf2aa1964487d61edab64556414.jpg@90w_90h_1e_1c',
            orderSeat: ["32"],
            orderTime: "2020-6-6 17:00",
            orderPrice:"100",
            orderStatus: "已完成",
            movieName: "我和我的祖国",
            arrangementPlace:"1"
        },
        {
            orderId: '5',
            orderImg:'https://p0.meituan.net/movie/202ea88abd2abf2aa1964487d61edab64556414.jpg@90w_90h_1e_1c',
            orderSeat: ["32", "22", "11"],
            orderTime: "2020-6-6 17:00",
            orderPrice:"100",
            orderStatus: "待评价",
            movieName: "我和我的祖国",
            arrangementPlace:"1"
        },
        {
            orderId: '6',
            orderImg:'https://p0.meituan.net/moviemachine/ff399baffe786445b96e4e3d24fe1eeb675719.jpg@90w_90h_1e_1c',
            orderSeat: ["32", "22", "11","65"],
            orderTime: "2020-6-6 17:00",
            orderPrice:"100",
            orderStatus: "待评价",
            movieName: "我和我的祖国",
            arrangementPlace:"1"
        },
        {
            orderId: '7',
            orderImg:'https://p0.meituan.net/moviemachine/b2c5c74d33e45745fd3462e44b3698e18336620.jpg@90w_90h_1e_1c',
            orderSeat: ["32", "22", "11"],
            orderTime: "2020-6-6 17:00",
            orderPrice:"100",
            orderStatus: "已完成",
            movieName: "我和我的祖国",
            arrangementPlace:"1"
        },
        {
            orderId: '8',
            orderImg:'https://p1.meituan.net/movie/a596474c1c29118d908d1eff0fd4293f1017066.jpg@90w_90h_1e_1c',
            orderSeat: ["4", "5", "6"],
            orderTime: "2020-6-6 17:00",
            orderPrice:"100",
            orderStatus: "待支付",
            movieName: "我和我的祖国",
            arrangementPlace:"1"
        },
        {
            orderId: '9',
            orderImg:'https://p0.meituan.net/movie/363e3a7e614d29b2847ff4e62afcd3f42168651.jpg@90w_90h_1e_1c',
            orderSeat: ["1", "2", "3"],
            orderTime: "2020-6-6 17:00",
            orderPrice:"100",
            orderStatus: "已完成",
            movieName: "我和我的祖国",
            arrangementPlace:"1"
        }
    ];


module.exports = [{
    url: "/Order/getOrderList",
    type: "post",
    response(config) {
        return {
            code: 200,
            msg: "success",
            data: {
                orderInfo
            },
        };
    },
},];
