/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云linux
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : 47.93.137.95:3306
 Source Schema         : Cinema_Managerment_System

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 05/11/2020 15:34:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for Cinema_arrangement
-- ----------------------------
DROP TABLE IF EXISTS `Cinema_arrangement`;
CREATE TABLE `Cinema_arrangement` (
  `arrangement_id` int NOT NULL AUTO_INCREMENT COMMENT '场次编号',
  `arrangement_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '场次时间',
  `arrangement_place` int NOT NULL COMMENT '影厅',
  `arrangement_price` float(10,1) NOT NULL COMMENT '票价',
  `movie_id` int NOT NULL COMMENT '电影编号',
  PRIMARY KEY (`arrangement_id`) USING BTREE,
  KEY `amId` (`movie_id`) USING BTREE,
  CONSTRAINT `amId` FOREIGN KEY (`movie_id`) REFERENCES `Cinema_movie` (`movie_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Cinema_arrangement
-- ----------------------------
BEGIN;
INSERT INTO `Cinema_arrangement` VALUES (1, '2020-10-15 14:00:00', 1, 46.0, 2);
INSERT INTO `Cinema_arrangement` VALUES (2, '2020-10-16 21:00:00', 2, 48.0, 1);
INSERT INTO `Cinema_arrangement` VALUES (3, '2020-10-16 22:00:00', 1, 45.0, 2);
INSERT INTO `Cinema_arrangement` VALUES (4, '2020-10-16 23:00:00', 2, 48.0, 1);
INSERT INTO `Cinema_arrangement` VALUES (5, '2020-10-17 13:00:00', 1, 50.0, 4);
INSERT INTO `Cinema_arrangement` VALUES (6, '2020-10-17 14:00:00', 2, 40.0, 5);
INSERT INTO `Cinema_arrangement` VALUES (7, '2020-10-17 15:00:00', 3, 45.0, 10);
INSERT INTO `Cinema_arrangement` VALUES (8, '2020-10-18 15:00:00', 1, 46.0, 3);
INSERT INTO `Cinema_arrangement` VALUES (9, '2020-10-18 17:00:00', 2, 44.0, 6);
INSERT INTO `Cinema_arrangement` VALUES (10, '2020-10-21 16:30:00', 1, 45.0, 2);
INSERT INTO `Cinema_arrangement` VALUES (11, '2020-10-21 19:00:00', 2, 38.0, 8);
INSERT INTO `Cinema_arrangement` VALUES (12, '2020-10-21 21:00:00', 3, 45.0, 10);
INSERT INTO `Cinema_arrangement` VALUES (13, '2020-10-28 16:00:00', 1, 50.0, 4);
INSERT INTO `Cinema_arrangement` VALUES (14, '2020-10-28 19:30:00', 1, 48.0, 1);
INSERT INTO `Cinema_arrangement` VALUES (15, '2020-10-28 12:30:00', 1, 50.0, 9);
INSERT INTO `Cinema_arrangement` VALUES (16, '2020-10-28 18:00:00', 2, 44.0, 6);
INSERT INTO `Cinema_arrangement` VALUES (17, '2020-10-29 22:20:12', 1, 30.0, 4);
INSERT INTO `Cinema_arrangement` VALUES (18, '2020-10-31 22:44:00', 2, 31.2, 1);
INSERT INTO `Cinema_arrangement` VALUES (20, '2020-10-31 20:37:42', 3, 20.0, 4);
INSERT INTO `Cinema_arrangement` VALUES (21, '2020-10-30 22:21:00', 2, 11.0, 1);
INSERT INTO `Cinema_arrangement` VALUES (22, '2020-11-02 21:26:00', 3, 23.0, 2);
INSERT INTO `Cinema_arrangement` VALUES (23, '2020-11-19 22:03:00', 2, 100.0, 3);
INSERT INTO `Cinema_arrangement` VALUES (25, '2020-11-19 14:53:00', 2, 44.0, 1);
INSERT INTO `Cinema_arrangement` VALUES (26, '2020-11-27 14:51:00', 44, 45.0, 10);
INSERT INTO `Cinema_arrangement` VALUES (27, '2020-11-28 21:06:00', 5, 33.0, 10);
INSERT INTO `Cinema_arrangement` VALUES (28, '2020-11-26 20:06:00', 3, 65.0, 10);
INSERT INTO `Cinema_arrangement` VALUES (29, '2020-11-27 05:01:00', 2, 12.0, 10);
COMMIT;

-- ----------------------------
-- Table structure for Cinema_customer
-- ----------------------------
DROP TABLE IF EXISTS `Cinema_customer`;
CREATE TABLE `Cinema_customer` (
  `customer_id` int NOT NULL AUTO_INCREMENT COMMENT '顾客id',
  `customer_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `customer_picture` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `customer_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名字',
  `customer_sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别',
  `customer_phone` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  PRIMARY KEY (`customer_id`) USING BTREE,
  UNIQUE KEY `customer_name` (`customer_name`) USING BTREE,
  KEY `customer_password` (`customer_password`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=754406399 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Cinema_customer
-- ----------------------------
BEGIN;
INSERT INTO `Cinema_customer` VALUES (5160, '111111', '202011051022480081rDgfgy1gc7ij0wpjdj30fr0jlkip.jpg', '李知恩', '女', '15122544381');
INSERT INTO `Cinema_customer` VALUES (5161, '111111', '202011031418077413d00499cd0f410a03bc77341010a77cf7c728.jpg@518w_1e_1c.jpg', '李知金', '女', '15122544382');
INSERT INTO `Cinema_customer` VALUES (5162, '111111', '20201103142252395703a131a68a641a90fe4f29a36f7.jpg', '李知铜', '女', '15122544383');
INSERT INTO `Cinema_customer` VALUES (5163, '111111', 'roses_are_rosie_20200707_213618_1.jpg', '李纯信', '女', '15122544384');
INSERT INTO `Cinema_customer` VALUES (5167, '111111', 'img-2c43babe4fc690c1401a517c67c85001(1).jpg', '朴彩英', '女', '13752114812');
INSERT INTO `Cinema_customer` VALUES (754406396, 'WANfxy406411', NULL, 'wsj', NULL, '18920033018');
INSERT INTO `Cinema_customer` VALUES (754406398, '111111', NULL, '大宝贝', NULL, '17778193164');
COMMIT;

-- ----------------------------
-- Table structure for Cinema_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `Cinema_evaluation`;
CREATE TABLE `Cinema_evaluation` (
  `evaluation_id` int NOT NULL AUTO_INCREMENT COMMENT '评价编号',
  `evaluation_score` float DEFAULT NULL COMMENT '评价得分',
  `evaluation_content` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '评价内容',
  `evaluation_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '评价时间',
  `order_id` bigint NOT NULL COMMENT '订单编号',
  PRIMARY KEY (`evaluation_id`) USING BTREE,
  KEY `orderid` (`order_id`) USING BTREE,
  CONSTRAINT `orderid` FOREIGN KEY (`order_id`) REFERENCES `Cinema_order` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Cinema_evaluation
-- ----------------------------
BEGIN;
INSERT INTO `Cinema_evaluation` VALUES (1, 5, '我也喜欢我的家乡', '2020-10-29 13:02:42', 1);
INSERT INTO `Cinema_evaluation` VALUES (2, 5, '代入感很强，感觉已经在为国争光了', '2020-10-18 15:02:47', 2);
INSERT INTO `Cinema_evaluation` VALUES (3, 4, '电影画面效果酷炫', '2020-10-18 22:08:03', 3);
INSERT INTO `Cinema_evaluation` VALUES (4, 5, '真好', '2020-10-31 18:29:53', 4);
INSERT INTO `Cinema_evaluation` VALUES (5, 5, 'sss', '2020-11-05 09:54:22', 5);
INSERT INTO `Cinema_evaluation` VALUES (6, NULL, NULL, NULL, 6);
INSERT INTO `Cinema_evaluation` VALUES (7, 4, '真不错', '2020-10-31 18:35:08', 7);
INSERT INTO `Cinema_evaluation` VALUES (8, 4, '好', '2020-10-31 18:34:15', 8);
INSERT INTO `Cinema_evaluation` VALUES (9, NULL, NULL, NULL, 9);
INSERT INTO `Cinema_evaluation` VALUES (10, NULL, NULL, NULL, 10);
INSERT INTO `Cinema_evaluation` VALUES (11, NULL, NULL, NULL, 11);
INSERT INTO `Cinema_evaluation` VALUES (12, NULL, NULL, NULL, 12);
INSERT INTO `Cinema_evaluation` VALUES (13, NULL, NULL, NULL, 13);
INSERT INTO `Cinema_evaluation` VALUES (16, 5, '好，真的不错', '2020-11-01 23:27:32', 1604243415627);
INSERT INTO `Cinema_evaluation` VALUES (17, NULL, NULL, NULL, 1604293690452);
INSERT INTO `Cinema_evaluation` VALUES (18, NULL, NULL, NULL, 1604295257032);
INSERT INTO `Cinema_evaluation` VALUES (19, NULL, NULL, NULL, 1604394884890);
COMMIT;

-- ----------------------------
-- Table structure for Cinema_inform
-- ----------------------------
DROP TABLE IF EXISTS `Cinema_inform`;
CREATE TABLE `Cinema_inform` (
  `info_id` int NOT NULL AUTO_INCREMENT COMMENT '消息编号',
  `info_content` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '通知内容',
  `info_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '通知时间',
  `info_status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '通知状态',
  `info_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '通知类型',
  `customer_id` int NOT NULL COMMENT '顾客id',
  PRIMARY KEY (`info_id`) USING BTREE,
  KEY `info-cusid` (`customer_id`) USING BTREE,
  KEY `info-mid` (`info_type`) USING BTREE,
  CONSTRAINT `fcid` FOREIGN KEY (`customer_id`) REFERENCES `Cinema_customer` (`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Cinema_inform
-- ----------------------------
BEGIN;
INSERT INTO `Cinema_inform` VALUES (1, '你好，客服', '2020-10-28 09:42:57', '已读', 'customer', 5167);
INSERT INTO `Cinema_inform` VALUES (2, '请问这个电影票可以退吗', '2020-10-28 09:42:59', '已读', 'customer', 5167);
INSERT INTO `Cinema_inform` VALUES (3, '不可以的', '2020-10-26 12:49:33', '已读', 'manager', 5167);
INSERT INTO `Cinema_inform` VALUES (6, '真的不能退吗', '2020-10-28 08:50:17', '已读', 'customer', 5167);
INSERT INTO `Cinema_inform` VALUES (7, '你好，在吗', '2020-10-28 08:50:27', '已读', 'customer', 5167);
INSERT INTO `Cinema_inform` VALUES (18, '在吗在吗', '2020-10-31 13:23:52', '已读', 'customer', 5167);
INSERT INTO `Cinema_inform` VALUES (19, '在的怎么了', '2020-10-31 13:23:56', '已读', 'manager', 5167);
INSERT INTO `Cinema_inform` VALUES (20, '这票可以退吗', '2020-10-31 13:24:03', '已读', 'customer', 5167);
INSERT INTO `Cinema_inform` VALUES (21, '不可以的', '2020-10-31 13:24:08', '已读', 'manager', 5167);
INSERT INTO `Cinema_inform` VALUES (22, '好的', '2020-10-31 13:24:23', '已读', 'customer', 5167);
INSERT INTO `Cinema_inform` VALUES (23, '喂喂喂', '2020-11-03 11:50:25', '已读', 'customer', 5167);
INSERT INTO `Cinema_inform` VALUES (24, '海洋生物', '2020-11-03 11:50:43', '已读', 'manager', 5167);
INSERT INTO `Cinema_inform` VALUES (25, '你爸爸妈妈身体好吗', '2020-11-03 11:50:52', '已读', 'customer', 5167);
INSERT INTO `Cinema_inform` VALUES (26, '我叫柴颖', '2020-11-03 11:51:08', '已读', 'manager', 5167);
INSERT INTO `Cinema_inform` VALUES (27, '歪！', '2020-11-03 14:49:15', '已读', 'customer', 5161);
INSERT INTO `Cinema_inform` VALUES (28, 'hello', '2020-11-04 19:43:46', '已读', 'customer', 754406396);
COMMIT;

-- ----------------------------
-- Table structure for Cinema_manager
-- ----------------------------
DROP TABLE IF EXISTS `Cinema_manager`;
CREATE TABLE `Cinema_manager` (
  `manager_id` int NOT NULL COMMENT '管理员ID',
  `manager_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `manager_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `manager_sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `manager_phone` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `manager_is_boss` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否老板',
  PRIMARY KEY (`manager_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Cinema_manager
-- ----------------------------
BEGIN;
INSERT INTO `Cinema_manager` VALUES (1001, '123456', '李栋旭', '男', '13188786799', b'0');
INSERT INTO `Cinema_manager` VALUES (1005, '1005', '赵宝儿', '女', '13345739906', b'0');
INSERT INTO `Cinema_manager` VALUES (1006, '111111', '张博', '男', '13752114812', b'1');
INSERT INTO `Cinema_manager` VALUES (1007, '111111', '1007', '男', NULL, b'0');
COMMIT;

-- ----------------------------
-- Table structure for Cinema_movie
-- ----------------------------
DROP TABLE IF EXISTS `Cinema_movie`;
CREATE TABLE `Cinema_movie` (
  `movie_id` int NOT NULL AUTO_INCREMENT COMMENT '影片id',
  `movie_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `movie_picture` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '海报',
  `movie_trailer` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '预告',
  `movie_des` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '简介',
  `movie_director` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '导演',
  `movie_actor` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '演员',
  `movie_time` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '时长',
  `movie_release_time` date NOT NULL COMMENT '上映日期',
  `movie_score` float(2,1) DEFAULT NULL COMMENT '评分',
  `movie_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型',
  `wants_num` int NOT NULL DEFAULT '0' COMMENT '想看人数',
  PRIMARY KEY (`movie_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Cinema_movie
-- ----------------------------
BEGIN;
INSERT INTO `Cinema_movie` VALUES (1, '我和我的家乡', 'http://img.movie.iecity.com/Upload/Movie/202009/07/20200907164301794.jpg', NULL, '电影《我和我的家乡》定档2020年国庆，延续《我和我的祖国》集体创作的方式，由张艺谋担当总监制，宁浩担任总导演，张一白担任总策划，宁浩、徐峥、陈思诚、闫非&彭大魔、邓超&俞白眉分别执导五个故事。', '宁浩/徐峥', '葛优/黄渤/范伟', '153分钟', '2020-10-01', 4.3, '剧情/喜剧', 2);
INSERT INTO `Cinema_movie` VALUES (2, '夺冠', 'http://img.movie.iecity.com/Upload/Movie/202009/25/20200925090741709.jpg', NULL, '2008年8月15日，北京奥运会女排比赛，中国VS美国。戴着金丝框眼镜的郎平（巩俐 饰）坐在美国队教练席上，大气沉稳，目光如电；中国队教练陈忠和（黄渤 饰）站在场边，全神贯注，面带笑容。陈忠和望向郎平，目光充满深意，不断经过的人影遮蔽了他的视线，中国女排三十余年的沉浮图景被缓缓打开……', '陈可辛', '巩俐/黄渤/吴刚/彭昱畅', '135分钟', '2020-09-25', 4.1, '剧情', 3);
INSERT INTO `Cinema_movie` VALUES (3, '一点就到家', 'http://img.movie.iecity.com/Upload/Movie/202010/09/20201009175709779.jpg', NULL, '魏晋北（刘昊然 饰）、彭秀兵（彭昱畅 饰）、李绍群（尹昉 饰）三个性格迥异的年轻人从大城市回到云南千年古寨，机缘巧合下共同创业，与古寨“格格不入”的他们用真诚改变了所有人，开启了一段非常疯狂、纯真的梦想之旅。', '许宏宇', '刘昊然/彭昱畅/尹昉/谭卓', '97分钟', '2020-10-04', 4.2, '青春', 4);
INSERT INTO `Cinema_movie` VALUES (4, '姜子牙', 'http://img.movie.iecity.com/Upload/Movie/202009/29/20200929150602976.jpg', NULL, '动画电影《姜子牙》的故事发生于封神大战后。姜子牙率领众神伐纣，赢得封神大战胜利，以为可以唤回世间安宁。然而，一切并未结束。一个偶然，姜子牙发现了原来“封神大战”之下酝酿着更大的阴谋。姜子牙开始踏上探寻真相的旅途......', '程腾/李炜', '郑希/杨凝/图特哈蒙', '110分钟', '2020-10-01', 3.3, '动作/冒险', 1);
INSERT INTO `Cinema_movie` VALUES (5, '喜宝', 'http://img.movie.iecity.com/Upload/Movie/202010/16/20201016111751928.jpg', NULL, '喜宝（郭采洁 饰）因家庭困境中止了英国学业返回国内，在飞机上结识了单纯可爱的富家千金勖聪慧（李彦漫 饰），回国后先后遭遇了母亲过世、从未见过的父亲上门索要钱财，被房东赶出无家可归。勖家帮助喜宝摆脱了困境，同时喜宝也深陷勖家男人的感情旋涡。曾经想要很多很多爱的喜宝，忽然拥有了选择很多很多钱的机会，年轻的喜宝能否明白爱情的真谛……', '王丹阳', '郭采洁/张国柱/高仁/李彦漫', '112分钟', '2020-10-16', 1.9, '爱情', 1);
INSERT INTO `Cinema_movie` VALUES (6, '7号房的礼物', 'http://img.movie.iecity.com/Upload/Movie/202009/30/20200930101423193.jpg', NULL, '智残父亲梅莫含冤入狱被判死刑，关进七号牢房。在这里聚集着犯下各种罪孽的“恶人”，梅莫孩子般纯洁的心渐渐感动了这几个“大坏蛋”，他们甚至不惜冒险将女儿奥娃带入牢房与梅莫团聚。黑暗冰冷的监狱中，七号牢房阳光满满地迎来了一份暖化人心的礼物。', '梅米特·艾达·厄兹泰金', '阿拉斯·布鲁特·伊涅姆利/妮莎·索菲娅·阿克松古尔/妮莎·索菲娅·阿克松古尔/梅苏特·阿库斯塔', '132分钟', '2020-10-15', 4.4, '剧情', 1);
INSERT INTO `Cinema_movie` VALUES (7, '急先锋', 'http://img.movie.iecity.com/Upload/Movie/202009/29/20200929150525032.jpg', NULL, '中国商人秦国立在英国惨遭雇佣兵组织“北极狼”绑架，秦的女儿Fareeda（徐若晗 饰）也卷入其中，遭到追杀。千钧一发之际，急先锋国际安保团队成为他们唯一的希望，由总指挥唐焕庭（成龙饰）带领雷震宇（杨洋饰）、张凯旋（艾伦饰）、弥雅（母其弥雅饰）、神雕（朱正廷饰）等组成的急先锋行动小组，上天入地，辗转全球各地施展惊险营救。解救人质的过程中，竟发现“北极狼”背后的犯罪集团还策划了一场惊天密谋……', '唐季礼', '成龙/杨洋/艾伦/徐若晗', '108分钟', '2020-09-30', 3.3, '动作/冒险/喜剧', 1);
INSERT INTO `Cinema_movie` VALUES (8, '八佰', 'http://img.movie.iecity.com/Upload/Movie/202008/22/20200822184538250.jpg', NULL, '淞沪会战后期，中国军队第88师524团团附谢晋元临危受命，率领400余名官兵（外界称“八百壮士”），坚守闸北四行仓库，掩护主力部队撤退。“八百壮士”抱定为国捐躯的决心，以弹丸之地抗击侵略者，激战四昼夜，打退敌人十余次疯狂进攻。其战斗事迹之英勇，爱国气节之豪壮，振奋国人，震惊世界。', '管虎', '王千源/张译/姜武/黄志忠', '147分钟', '2020-08-21', 4.2, '剧情/战争/历史', 3);
INSERT INTO `Cinema_movie` VALUES (9, '信条', 'http://img.movie.iecity.com/Upload/Movie/202008/28/20200828092128646.jpg', NULL, '世界存亡危在旦夕，“信条”一词是惟一的线索与武器。主人公穿梭于全球各地，开展特工活动，力求揭示“信条”之谜，并完成一项超越了真实时间的神秘任务。这项任务并非时间之旅，而是【时空逆转】。', '克里斯托弗·诺兰', '约翰·大卫·华盛顿/罗伯特·帕丁森/伊丽莎白·德比茨基/肯尼思·布拉纳', '151分钟', '2020-09-04', 3.3, '科幻/动作/剧情', 1);
INSERT INTO `Cinema_movie` VALUES (10, '花木兰', 'http://img.movie.iecity.com/Upload/Movie/202009/04/20200904145725485.jpg', NULL, '中国的皇帝颁布法令，要求每家出一名男子服兵役，抵御北方侵略者。作为一名受人尊敬的战士的长女，花木兰（刘亦菲 饰）站出来替生病的父亲应征入伍。她装扮成男人，化名花军，经受了种种考验，同时必须利用内在的力量，接纳自己真正的潜能。这是一场史诗般的旅程，让木兰蜕变为一名受人尊敬的战士，也赢得来自国家与骄傲的父亲的尊重。', '妮琪·卡罗', '刘亦菲/甄子丹/杰森·斯考特·李', '115分钟', '2020-09-11', 2.5, '剧情/冒险', 1);
INSERT INTO `Cinema_movie` VALUES (11, '再见吧！少年', 'https://p1.meituan.net/movie/828d68173a64a721d54a61be167a9c864358691.jpg@464w_644h_1e_1c', NULL, '少年王新阳（荣梓杉 饰）的生活因突患白血病而彻底改变。面对未知的明天，新阳依然乐观坚强，努力实现青春梦想，做不被打败的自己。', '林子平', '刘敏涛/荣梓杉/谭凯/顾语涵', '119分钟', '2020-10-05', 3.6, '剧情', 0);
INSERT INTO `Cinema_movie` VALUES (18, '123', 'http://47.93.137.95:8080/Cinema/img/20201102142951513B4006-CAC5-4EC1-B167-7B6894674CA5.png', 'http://47.93.137.95:8080/Cinema/video/20201102143016513B4006-CAC5-4EC1-B167-7B6894674CA5 2.mp4', '123', '123', '123', '123分钟', '2020-11-11', 0.0, '恐怖/喜剧', 0);
INSERT INTO `Cinema_movie` VALUES (19, '111', 'http://47.93.137.95:8080/Cinema/img/20201102215140roses_are_rosie_20200707_213948_0.jpg', 'http://47.93.137.95:8080/Cinema/video/20201102215150??.mp4', '111', '11', '111', '111分钟', '2020-11-11', 0.0, '冒险/奇幻', 0);
COMMIT;

-- ----------------------------
-- Table structure for Cinema_order
-- ----------------------------
DROP TABLE IF EXISTS `Cinema_order`;
CREATE TABLE `Cinema_order` (
  `order_id` bigint NOT NULL COMMENT '订单编号',
  `order_seat` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '座位号',
  `order_status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单状态',
  `order_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '订单时间',
  `movie_id` int NOT NULL COMMENT '电影编号',
  `customer_id` int NOT NULL COMMENT '顾客id',
  `arrangement_id` int NOT NULL COMMENT '场次编号',
  `order_price` float(10,1) NOT NULL COMMENT '订单实付',
  PRIMARY KEY (`order_id`) USING BTREE,
  KEY `seat` (`order_seat`) USING BTREE,
  KEY `moveid` (`movie_id`) USING BTREE,
  KEY `cusid` (`customer_id`) USING BTREE,
  KEY `ccbh` (`arrangement_id`) USING BTREE,
  CONSTRAINT `ccbh` FOREIGN KEY (`arrangement_id`) REFERENCES `Cinema_arrangement` (`arrangement_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `moveid` FOREIGN KEY (`movie_id`) REFERENCES `Cinema_movie` (`movie_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Cinema_order
-- ----------------------------
BEGIN;
INSERT INTO `Cinema_order` VALUES (1, '1排1座,2排1座,3排1座', '已完成', '2020-10-31 13:57:26', 1, 5160, 2, 100.1);
INSERT INTO `Cinema_order` VALUES (2, '4排6座,8排1座,5排4座', '已完成', '2020-10-30 14:44:53', 2, 5162, 3, 78.4);
INSERT INTO `Cinema_order` VALUES (3, '2排3座,5排5座,2排4座', '待支付', '2020-10-30 14:44:58', 4, 5165, 5, 66.0);
INSERT INTO `Cinema_order` VALUES (4, '1排9座,2排9座,3排9座', '已完成', '2020-10-31 18:29:52', 1, 5160, 2, 100.0);
INSERT INTO `Cinema_order` VALUES (5, '1排8座,2排8座,3排8座', '已完成', '2020-11-05 09:54:21', 1, 5160, 2, 100.0);
INSERT INTO `Cinema_order` VALUES (6, '4排8座,5排8座,6排8座', '待支付', '2020-09-13 13:58:02', 1, 5160, 2, 100.0);
INSERT INTO `Cinema_order` VALUES (7, '7排8座,8排8座,9排8座,9排10座', '已完成', '2020-09-11 15:38:23', 1, 5160, 2, 100.0);
INSERT INTO `Cinema_order` VALUES (8, '7排8座,8排8座,9排8座,9排10座', '已完成', '2020-10-04 15:38:21', 2, 5160, 2, 100.0);
INSERT INTO `Cinema_order` VALUES (9, '2排1座,2排2座,2排3座,2排4座', '待评价', '2020-10-02 15:36:55', 2, 5160, 2, 100.0);
INSERT INTO `Cinema_order` VALUES (10, '3排3座,3排4座,3排5座,3排6座', '待评价', '2020-10-18 15:37:06', 4, 5160, 2, 100.0);
INSERT INTO `Cinema_order` VALUES (11, '7排8座,8排8座,9排8座,9排10座', '待支付', '2020-10-28 15:36:04', 4, 5160, 2, 100.0);
INSERT INTO `Cinema_order` VALUES (12, '7排9座,7排7座,7排6座,7排5座', '待支付', '2020-09-16 15:37:39', 4, 5160, 2, 100.0);
INSERT INTO `Cinema_order` VALUES (13, '7排9座,7排7座,7排6座,7排5座', '待支付', '2020-09-10 15:37:39', 6, 5160, 2, 89.0);
INSERT INTO `Cinema_order` VALUES (1604243415627, '1排1座,1排3座,1排2座,1排4座', '已完成', '2020-11-01 23:27:31', 3, 5167, 23, 138.4);
INSERT INTO `Cinema_order` VALUES (1604293690452, '3排9座', '待评价', '2020-11-02 13:10:03', 3, 5160, 23, 34.6);
INSERT INTO `Cinema_order` VALUES (1604295257032, '5排4座', '待评价', '2020-11-02 13:34:52', 3, 5160, 23, 34.6);
INSERT INTO `Cinema_order` VALUES (1604394884890, '5排6座,5排5座', '待评价', '2020-11-03 17:15:49', 3, 754406398, 23, 200.0);
COMMIT;

-- ----------------------------
-- Table structure for Cinema_seat
-- ----------------------------
DROP TABLE IF EXISTS `Cinema_seat`;
CREATE TABLE `Cinema_seat` (
  `seat_id` int NOT NULL AUTO_INCREMENT COMMENT '座位编号',
  `seat_num` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '座位号',
  `arrangement_id` int NOT NULL COMMENT '场次编号',
  PRIMARY KEY (`seat_id`) USING BTREE,
  KEY `ccid` (`arrangement_id`) USING BTREE,
  CONSTRAINT `ccid` FOREIGN KEY (`arrangement_id`) REFERENCES `Cinema_arrangement` (`arrangement_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Cinema_seat
-- ----------------------------
BEGIN;
INSERT INTO `Cinema_seat` VALUES (1, '1排1座,3排10座,2排6座', 2);
INSERT INTO `Cinema_seat` VALUES (2, '4排6座,8排1座,5排4座', 3);
INSERT INTO `Cinema_seat` VALUES (3, '2排3座,5排5座,2排4座', 5);
INSERT INTO `Cinema_seat` VALUES (5, ',2排6座,7排7座,1排1座,1排3座,1排2座,1排4座,3排9座,5排4座,5排6座,5排5座', 23);
INSERT INTO `Cinema_seat` VALUES (7, '', 25);
INSERT INTO `Cinema_seat` VALUES (8, ',1排2座', 26);
INSERT INTO `Cinema_seat` VALUES (9, '', 27);
INSERT INTO `Cinema_seat` VALUES (10, '', 28);
INSERT INTO `Cinema_seat` VALUES (11, '', 29);
COMMIT;

-- ----------------------------
-- Table structure for movie_wants
-- ----------------------------
DROP TABLE IF EXISTS `movie_wants`;
CREATE TABLE `movie_wants` (
  `customer_id` int NOT NULL,
  `movie_id` int NOT NULL,
  PRIMARY KEY (`customer_id`,`movie_id`) USING BTREE,
  KEY `wmId` (`movie_id`) USING BTREE,
  CONSTRAINT `wmId` FOREIGN KEY (`movie_id`) REFERENCES `Cinema_movie` (`movie_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of movie_wants
-- ----------------------------
BEGIN;
INSERT INTO `movie_wants` VALUES (5161, 1);
INSERT INTO `movie_wants` VALUES (5162, 1);
INSERT INTO `movie_wants` VALUES (5161, 2);
INSERT INTO `movie_wants` VALUES (5162, 3);
INSERT INTO `movie_wants` VALUES (5163, 3);
INSERT INTO `movie_wants` VALUES (5163, 6);
INSERT INTO `movie_wants` VALUES (5162, 8);
INSERT INTO `movie_wants` VALUES (5163, 8);
INSERT INTO `movie_wants` VALUES (5161, 9);
COMMIT;

-- ----------------------------
-- Triggers structure for table Cinema_arrangement
-- ----------------------------
DROP TRIGGER IF EXISTS `addSeats`;
delimiter ;;
CREATE TRIGGER `addSeats` AFTER INSERT ON `Cinema_arrangement` FOR EACH ROW INSERT INTO Cinema_seat(arrangement_id,seat_num) VALUE(new.arrangement_id,'')
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table Cinema_arrangement
-- ----------------------------
DROP TRIGGER IF EXISTS `delSeats`;
delimiter ;;
CREATE TRIGGER `delSeats` AFTER DELETE ON `Cinema_arrangement` FOR EACH ROW DELETE FROM Cinema_seat
WHERE arrangement_id = old.arrangement_id
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table Cinema_order
-- ----------------------------
DROP TRIGGER IF EXISTS `addSeat`;
delimiter ;;
CREATE TRIGGER `addSeat` AFTER INSERT ON `Cinema_order` FOR EACH ROW UPDATE Cinema_seat
SET seat_num =  CONCAT(seat_num,CONCAT(',',new.order_seat))
WHERE arrangement_id = new.arrangement_id
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table Cinema_order
-- ----------------------------
DROP TRIGGER IF EXISTS `delSeat`;
delimiter ;;
CREATE TRIGGER `delSeat` AFTER DELETE ON `Cinema_order` FOR EACH ROW UPDATE Cinema_seat
SET seat_num = REPLACE(seat_num,CONCAT(',',old.order_seat),'')
WHERE arrangement_id = old.arrangement_id
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table movie_wants
-- ----------------------------
DROP TRIGGER IF EXISTS `add_wants_num`;
delimiter ;;
CREATE TRIGGER `add_wants_num` AFTER INSERT ON `movie_wants` FOR EACH ROW UPDATE Cinema_movie
SET wants_num = wants_num + 1
WHERE movie_id = new.movie_id
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table movie_wants
-- ----------------------------
DROP TRIGGER IF EXISTS `del_wants_num`;
delimiter ;;
CREATE TRIGGER `del_wants_num` AFTER DELETE ON `movie_wants` FOR EACH ROW UPDATE Cinema_movie
SET wants_num = wants_num - 1
WHERE movie_id = old.movie_id
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
