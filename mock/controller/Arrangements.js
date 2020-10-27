const arrangement = {
  movie: {
    movieId: 1,
    movieName: "夺冠",
    movieScore: 4.6,
    movieActor: "巩俐,黄渤,吴刚,彭彦昌",
    movieType: "剧情",
    movieTime: "中国大陆/135分钟",
  },
  arrangementList: [{
      arrangementData: '2020-10-23',
      theDayArrangement: [{
          arrangementId: 1,
          arrangementTime: '2020-10-23 12:12',
          movieTime: '138分钟',
          arrangementPrice: 23,
          arrangementPlace: 1,
        },
        {
          arrangementId: 2,
          arrangementTime: '2020-10-23 12:13',
          movieTime: '138分钟',
          arrangementPrice: 24,
          arrangementPlace: 2,
        },
        {
          arrangementId: 3,
          arrangementTime: '2020-10-29 12:14',
          movieTime: '138分钟',
          arrangementPrice: 25,
          arrangementPlace: 3,
        },
      ]
    },
    {
      arrangementData: '2020-10-24',
      theDayArrangement: [{
          arrangementId: 4,
          arrangementTime: '2020-10-24 12:15',
          movieTime: '138分钟',
          arrangementPrice: 23,
          arrangementPlace: 4,
        },
        {
          arrangementId: 5,
          arrangementTime: '2020-10-24 12:16',
          movieTime: '138分钟',
          arrangementPrice: 24,
          arrangementPlace: 5,
        },
        {
          arrangementId: 6,
          arrangementTime: '2020-10-24 12:17',
          movieTime: '138分钟',
          arrangementPrice: 25,
          arrangementPlace: 6,
        },
      ]
    },
    {

      arrangementData: '2020-10-25',
      theDayArrangement: [{
          arrangementId: 7,
          arrangementTime: '2020-10-25 12:18',
          movieTime: '138分钟',
          arrangementPrice: 23,
          arrangementPlace: 7,
        },
        {
          arrangementId: 8,
          arrangementTime: '2020-10-25 12:12',
          movieTime: '138分钟',
          arrangementPrice: 24,
          arrangementPlace: 8,
        },
        {
          arrangementId: 9,
          arrangementTime: '2020-10-25 12:19',
          movieTime: '138分钟',
          arrangementPrice: 25,
          arrangementPlace: 9,
        },
      ]
    },
  ]
};

const arrangementInfo = {
  arrangementId: 9,
  arrangementTime: '2020-10-25 19:23',
  arrangementPrice: 25,
  arrangementPlace: 9,
  movieInfo: {
    movieId: 1,
    movieName: "夺冠",
    moviePicture: "https://p0.meituan.net/movie/e7185e149d79e72c4ef0d70f27fc76da1642784.jpg@464w_644h_1e_1c",
    movieType: "剧情",
    movieTime: "中国大陆/135分钟",
  }
}

module.exports = [{
  url: "/arrangement/selectSession",
  movieType: "get",
  response(config) {
    return {
      code: 200,
      msg: "success",
      data: arrangement,
    };
  },
}, {
  url: "/arrangement/arrangementInfo",
  movieType: "get",
  response(config) {
    return {
      code: 200,
      msg: "success",
      data: arrangementInfo,
    };
  },
}];
