const arrangement = {
  movie: {
    movieId: 1,
    movieTime: 128,
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
    movieTime: 128,
    movieName: "夺冠",
    moviePicture: "https://p0.meituan.net/movie/e7185e149d79e72c4ef0d70f27fc76da1642784.jpg@464w_644h_1e_1c",
    movieType: "剧情",
    movieTime: "中国大陆/135分钟",
  }
}

const arrangementList = [{
    arrangementId: 1,
    movieId: 1,
    movieTime: 128,
    movieName: "夺冠",
    arrangementDate: "2020-10-25",
    arrangementTime: "14:25",
    arrangementPlace: 8,
    arrangementPrice: 23,
  },
  {
    arrangementId: 2,
    movieId: 1,
    movieTime: 128,
    movieName: "夺冠",
    arrangementDate: "2020-10-26",
    arrangementTime: "14:25",
    arrangementPlace: 8,
    arrangementPrice: 23,
  },
  {
    arrangementId: 3,
    movieId: 1,
    movieTime: 128,
    movieName: "夺冠",
    arrangementDate: "2020-10-25",
    arrangementTime: "14:25",
    arrangementPlace: 8,
    arrangementPrice: 23,
  },
  {
    arrangementId: 4,
    movieId: 1,
    movieTime: 128,
    movieName: "夺冠",
    arrangementDate: "2020-10-25",
    arrangementTime: "14:25",
    arrangementPlace: 8,
    arrangementPrice: 23,
  },
  {
    arrangementId: 5,
    movieId: 1,
    movieTime: 128,
    movieName: "夺冠",
    arrangementDate: "2020-10-25",
    arrangementTime: "14:25",
    arrangementPlace: 8,
    arrangementPrice: 23,
  },
  {
    arrangementId: 6,
    movieId: 1,
    movieTime: 128,
    movieName: "夺冠",
    arrangementDate: "2020-10-25",
    arrangementTime: "14:25",
    arrangementPlace: 8,
    arrangementPrice: 23,
  },
  {
    arrangementId: 7,
    movieId: 1,
    movieTime: 128,
    movieName: "夺冠",
    arrangementDate: "2020-10-25",
    arrangementTime: "14:25",
    arrangementPlace: 8,
    arrangementPrice: 23,
  },
  {
    arrangementId: 8,
    movieId: 1,
    movieTime: 128,
    movieName: "夺冠",
    arrangementDate: "2020-10-25",
    arrangementTime: "14:25",
    arrangementPlace: 8,
    arrangementPrice: 23,
  },
  {
    arrangementId: 9,
    movieId: 1,
    movieTime: 128,
    movieName: "夺冠",
    arrangementDate: "2020-10-25",
    arrangementTime: "14:25",
    arrangementPlace: 8,
    arrangementPrice: 23,
  },
  {
    arrangementId: 10,
    movieId: 1,
    movieTime: 128,
    movieName: "夺冠",
    arrangementDate: "2020-10-25",
    arrangementTime: "14:25",
    arrangementPlace: 8,
    arrangementPrice: 23,
  },
  {
    arrangementId: 11,
    movieId: 1,
    movieTime: 128,
    movieName: "夺冠",
    arrangementDate: "2020-10-25",
    arrangementTime: "14:25",
    arrangementPlace: 8,
    arrangementPrice: 23,
  },
  {
    arrangementId: 12,
    movieId: 1,
    movieTime: 128,
    movieName: "夺冠",
    arrangementDate: "2020-10-25",
    arrangementTime: "14:25",
    arrangementPlace: 8,
    arrangementPrice: 23,
  },
]

module.exports = [{
    url: "/arrangement/selectSession",
    type: "post",
    response(config) {
      return {
        code: 200,
        msg: "success",
        data: arrangement,
      };
    },
  },
  {
    url: "/arrangement/arrangementInfo",
    type: "post",
    response(config) {
      return {
        code: 200,
        msg: "success",
        data: arrangementInfo,
      };
    },
  },
  {
    url: "/arrangement/getList",
    type: "post",
    response(config) {
      const {
        title = "", pageNo = 1, pageSize = 20
      } = config.body;
      let mockList = arrangementList.filter((item) => {
        if (title && item.title.indexOf(title) < 0) return false;
        return true;
      });
      const pageList = mockList.filter(
        (item, index) =>
        index < pageSize * pageNo && index >= pageSize * (pageNo - 1)
      );
      console.log(pageList.length);
      return {
        code: 200,
        msg: "success",
        totalCount: mockList.length,
        data: pageList,
      };
    },
  },
];
