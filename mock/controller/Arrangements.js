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
      arrangementData: '10月23',
      theDayArrangement: [{
          arrangementId: 1,
          startTime: '12:12',
          endTime: '14:25',
          arrangementPrice: 23,
          arrangementPlace: 1,
        },
        {
          arrangementId: 2,
          startTime: '13:13',
          endTime: '14:25',
          arrangementPrice: 24,
          arrangementPlace: 2,
        },
        {
          arrangementId: 3,
          startTime: '14:14',
          endTime: '14:25',
          arrangementPrice: 25,
          arrangementPlace: 3,
        },
      ]
    },
    {
      arrangementData: '10月24',
      theDayArrangement: [{
          arrangementId: 4,
          startTime: '12:12',
          endTime: '14:25',
          arrangementPrice: 23,
          arrangementPlace: 4,
        },
        {
          arrangementId: 5,
          startTime: '13:13',
          endTime: '14:25',
          arrangementPrice: 24,
          arrangementPlace: 5,
        },
        {
          arrangementId: 6,
          startTime: '14:14',
          endTime: '14:25',
          arrangementPrice: 25,
          arrangementPlace: 6,
        },
      ]
    },
    {

      arrangementData: '10月25',
      theDayArrangement: [{
          arrangementId: 7,
          startTime: '12:12',
          endTime: '14:25',
          arrangementPrice: 23,
          arrangementPlace: 7,
        },
        {
          arrangementId: 8,
          startTime: '13:13',
          endTime: '14:25',
          arrangementPrice: 24,
          arrangementPlace: 8,
        },
        {
          arrangementId: 9,
          startTime: '10:14',
          endTime: '14:25',
          arrangementPrice: 25,
          arrangementPlace: 9,
        },
      ]
    },
  ]
};

const arrangementInfo = {
  arrangementId: 9,
  startTime: '10:14',
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
