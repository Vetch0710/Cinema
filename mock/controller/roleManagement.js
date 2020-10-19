const totalCount = 2;
const List = [
  {
    id: "@id",
    name: "@cname",
    permission: "老板",
  },
  {
    id: "@id",
    name: "@cname",
    permission: "管理员",
  },
  {
    id: "@id",
    name: "@cname",
    permission: "管理员",
  },
  {
    id: "@id",
    name: "@cname",
    permission: "管理员",
  },
  {
    id: "@id",
    name: "@cname",
    permission: "管理员",
  },
  {
    id: "@id",
    name: "@cname",
    permission: "管理员",
  },
  {
    id: "@id",
    name: "@cname",
    permission: "管理员",
  },
];
module.exports = [
  {
    url: "/roleManagement/getList",
    type: "post",
    response(config) {
      const { title = "", pageNo = 1, pageSize = 20 } = config.body;
      let mockList = List.filter((item) => {
        return !(title && item.title.indexOf(title) < 0);
      });
      const pageList = mockList.filter(
        (item, index) =>
          index < pageSize * pageNo && index >= pageSize * (pageNo - 1)
      );
      return {
        code: 200,
        msg: "success",
        totalCount: mockList.length,
        data: pageList,
      };
    },
  },
  {
    url: "/roleManagement/doEdit",
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
    url: "/roleManagement/doDelete",
    type: "post",
    response(config) {
      console.log(config.body);
      return {
        code: 200,
        msg: "模拟删除成功",
      };
    },
  },
  {
    url: "/roleManagement/getMaxNum",
    type: "post",
    response() {
      return {
        code: 200,
        msg: "success",
        num: "555565516",
      };
    },
  },
];
