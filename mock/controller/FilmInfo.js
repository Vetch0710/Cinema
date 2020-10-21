const film = {
  movie: {
    filmname: "夺冠",
    score: 4.5,
    des: "2008年8月15日，北京奥运会女排比赛，中国VS美国。戴着金丝框眼镜的郎平（巩俐 饰）坐在美国队教练席上，大气沉稳，目光如电；中国队教练陈忠和（黄渤 饰）站在场边，全神贯注，面带笑容。陈忠和望向郎平，目光充满深意，不断经过的人影遮蔽了他的视线，中国女排三十余年的沉浮图景被缓缓打开……",
    imgurl: "https://p0.meituan.net/movie/e7185e149d79e72c4ef0d70f27fc76da1642784.jpg@464w_644h_1e_1c",
    director: "陈可辛",
    actor: "巩俐,黄渤,吴刚,彭彦昌",
    type: "剧情",
    time: "中国大陆/135分钟",
    wantsnum: "204000",
    realese_time: "2020-09-25 18:00中国大陆上映",
    yugao:"https://vod.pipi.cn/fec9203cvodtransbj1251246104/aa215f715285890808273188253/v.f42906.mp4"
  },
  wantflag: true,
  myScore: 4.2,
  evaluations: [{
      customer: {
        customerName: "张博",
        customerPicture: "https://img.meituan.net/maoyanuser/82cc5ef259348d9bf9acc182e72223a43813.jpg@100w_100h_1e_1c"
      },
      evaluationScore: 4.8,
      evaluationContent: "这真是太好了1",
      evaluationTime: "2020-10-08 12:05:27",
    },
    {
      customer: {
        customerName: "张博",
        customerPicture: "https://img.meituan.net/maoyanuser/82cc5ef259348d9bf9acc182e72223a43813.jpg@100w_100h_1e_1c"
      },
      evaluationScore: 4.8,
      evaluationContent: "这真是太好了1",
      evaluationTime: "2020-10-08 12:05:27",
    },
    {
      customer: {
        customerName: "张博2",
        customerPicture: "https://img.meituan.net/maoyanuser/82cc5ef259348d9bf9acc182e72223a43813.jpg@100w_100h_1e_1c"
      },
      evaluationScore: 4.8,
      evaluationContent: "这真是太好了2",
      evaluationTime: "2020-10-08 12:05:27",
    },
    {
      customer: {
        customerName: "张博3",
        customerPicture: "https://img.meituan.net/maoyanuser/82cc5ef259348d9bf9acc182e72223a43813.jpg@100w_100h_1e_1c"
      },
      evaluationScore: 4.8,
      evaluationContent: "这真是太好了3",
      evaluationTime: "2020-10-08 12:05:27",
    },
    {
      customer: {
        customerName: "张博4",
        customerPicture: "https://img.meituan.net/maoyanuser/82cc5ef259348d9bf9acc182e72223a43813.jpg@100w_100h_1e_1c"
      },
      evaluationScore: 4.8,
      evaluationContent: "这真是太好了4",
      evaluationTime: "2020-10-08 12:05:27",
    },
    {
      customer: {
        customerName: "张博5",
        customerPicture: "https://img.meituan.net/maoyanuser/82cc5ef259348d9bf9acc182e72223a43813.jpg@100w_100h_1e_1c"
      },
      evaluationScore: 4.8,
      evaluationContent: "这真是太好了5",
      evaluationTime: "2020-10-08 12:05:27",
    },
    {
      customer: {
        customerName: "张博6",
        customerPicture: "https://img.meituan.net/maoyanuser/82cc5ef259348d9bf9acc182e72223a43813.jpg@100w_100h_1e_1c"
      },
      evaluationScore: 4.8,
      evaluationContent: "这真是太好了6",
      evaluationTime: "2020-10-08 12:05:27",
    },
    {
      customer: {
        customerName: "张博7",
        customerPicture: "https://img.meituan.net/maoyanuser/82cc5ef259348d9bf9acc182e72223a43813.jpg@100w_100h_1e_1c"
      },
      evaluationScore: 4.8,
      evaluationContent: "这真是太好了7",
      evaluationTime: "2020-10-08 12:05:27",
    },
    {
      customer: {
        customerName: "张博8",
        customerPicture: "https://img.meituan.net/maoyanuser/82cc5ef259348d9bf9acc182e72223a43813.jpg@100w_100h_1e_1c"
      },
      evaluationScore: 4.8,
      evaluationContent: "这真是太好了8",
      evaluationTime: "2020-10-08 12:05:27",
    },
    {
      customer: {
        customerName: "张博9",
        customerPicture: "https://img.meituan.net/maoyanuser/82cc5ef259348d9bf9acc182e72223a43813.jpg@100w_100h_1e_1c"
      },
      evaluationScore: 4.8,
      evaluationContent: "这真是太好了9",
      evaluationTime: "2020-10-08 12:05:27",
    },
  ]
};

module.exports = [{
  url: "/film/getInfo",
  type: "post",
  response(config) {
    return {
      code: 200,
      msg: "success",
      data: film,
    };
  },
}, ];
