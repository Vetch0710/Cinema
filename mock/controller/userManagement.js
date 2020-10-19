const List = [
  {
    id: "111",
    username: "@name",
    password: "123456",
    phone: "13752114812",
  },
  {
    id: "2222",
    username: "@name",
    password: "123456",
    phone: "13752114812",
  },
  {
    id: "3",
    username: "@name",
    password: "123456",
    phone: "13752114812",
  },
  {
    id: "4",
    username: "@name",
    password: "123456",
    phone: "13752114812",
  },
  {
    id: "5",
    username: "@name",
    password: "123456",
    phone: "13752114812",
  },
  {
    id: "6",
    username: "@name",
    password: "123456",
    phone: "13752114812",
  },
  {
    id: "7",
    username: "@name",
    password: "123456",
    phone: "13752114812",
  },
  {
    id: "8",
    username: "@name",
    password: "123456",
    phone: "13752114812",
  },
  {
    id: "9",
    username: "@name",
    password: "123456",
    phone: "13752114812",
  },
  {
    id: "10",
    username: "@name",
    password: "123456",
    phone: "13752114812",
  },
  {
    id: "11",
    username: "@name",
    password: "123456",
    phone: "13752114812",
  },
  {
    id: "12",
    username: "@name",
    password: "123456",
    phone: "13752114812",
  },
  {
    id: "13",
    username: "@name",
    password: "123456",
    phone: "13752114812",
  },
  {
    id: "14",
    username: "@name",
    password: "123456",
    phone: "13752114812",
  },
  {
    id: "15",
    username: "@name",
    password: "123456",
    phone: "13752114812",
  },
];
let totalCount = 3;
const Info = [
  { type: "工号", value: "754406394" },
  { type: "姓名", value: "张三" },
  { type: "密码", value: "123465" },
  { type: "手机号", value: "13752114812" },
  { type: "职位", value: "管理员" },
];
module.exports = [
  {
    url: "/userManagement/getList",
    type: "post",
    response(config) {
      const { title = "", pageNo = 1, pageSize = 20 } = config.body;
      let mockList = List.filter((item) => {
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
  {
    url: "/userManagement/doEdit",
    type: "post",
    response(config) {
      console.log(config.body);
      return {
        code: 200,
        msg: "模拟保存成功",
      };
    },
  },
  {
    url: "/userManagement/doDelete",
    type: "post",
    response(config) {
      console.log(config.body.ids);
      console.log(typeof config.body.ids);
      console.log(config.body.ids[0]);
      console.log(config.body.ids[1]);
      return {
        code: 200,
        msg: "模拟删除成功",
      };
    },
  },
  {
    url: "/userManagement/getInfo",
    type: "post",
    response() {
      return {
        code: 200,
        msg: "查询成功",
        data: Info,
      };
    },
  },
];
