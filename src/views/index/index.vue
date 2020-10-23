<template>
  <div>
    <div class="rotation">
      <el-carousel trigger="click" height="400px">
        <el-carousel-item v-for="item in rotationImgs" :key="item">
          <img class="small" :src="item" />
        </el-carousel-item>
      </el-carousel>
    </div>
    <div class="tabnav">
      <div
        class="tab-nav-item tab-hot font-bold"
        :class="{ hotbg: tabhotflag }"
        @click="checkhot"
      >
        正在热映
      </div>
      <div
        class="tab-nav-item tab-up font-bold"
        :class="{ hotbg: !tabhotflag }"
        @click="checkup"
      >
        即将上映
      </div>
    </div>
    <div class="nut-radiogroup">
      <label class="inline" for="byhot">
        <span>
          <input
            type="radio"
            name="checkbytype"
            id="byhot"
            value="byhot"
            style="height: 14px; width: 14px"
            v-model="queryForm.data.selecttype"
            @click="fetchData"
          />
          <!--          <label for="radio" class="radio-beauty"></label>-->
        </span>
        热门推荐
      </label>
      <label class="inline" for="bytime">
        <span>
          <input
            type="radio"
            name="checkbytype"
            id="bytime"
            value="bytime"
            style="height: 14px; width: 14px"
            v-model="queryForm.data.selecttype"
            @click="fetchData"
          />
          <!--          <label for="radio" class="radio-beauty"></label>-->
        </span>
        按时间排序
      </label>
      <label class="inline" v-if="tabhotflag" for="byscore">
        <span>
          <input
            type="radio"
            name="checkbytype"
            id="byscore"
            value="byscore"
            style="height: 14px; width: 14px"
            v-model="queryForm.data.selecttype"
            @click="fetchData"
          />
          <!--          <label for="byscore" class="radio-beauty"></label>-->
        </span>
        按评分排序
      </label>
      <label class="inline" v-else-if="tabupflag" for="bywantnum">
        <span>
          <input
            type="radio"
            name="checkbytype"
            id="bywantnum"
            value="bywantnum"
            style="height: 14px; width: 14px"
            v-model="queryForm.data.selecttype"
            @click="fetchData"
          />
          <!--          <label for="radio" class="radio-beauty"></label>-->
        </span>
        按想看人数排序
      </label>
    </div>
    <div class="filmshow">
      <div v-if="listLoading" class="listLoading">
        <img
          src="@/assets/index_img/Loading.png"
          class="loading-img"
        /><br /><br />
        <span class="loading-span">{{ elementLoadingText }}</span>
      </div>
      <ul>
        <li v-for="(item, index) in list" :key="index">
          <film-show
            :movieId="item.movieId"
            :movieName="item.movieName"
            :movieScore="item.movieScore"
            :arrangementPrice="item.arrangementPrice"
            :moviePicture="item.moviePicture"
          ></film-show>
        </li>
      </ul>
    </div>
    <el-pagination
      class="film-page"
      background
      :current-page="queryForm.pageNo"
      :page-size="queryForm.pageSize"
      :layout="layout"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    ></el-pagination>
  </div>
</template>
<script>
import filmShow from "./filmShow";
import { getList, doDelete } from "@/api/FilmList";

export default {
  name: "Index",
  data() {
    return {
      rotationImgs: [
        "https://img14.360buyimg.com/itcomm/jfs/t1/146985/21/9644/178592/5f745e8fece315ab1/c5cf861c0d367493.jpg",
        "https://img14.360buyimg.com/itcomm/jfs/t1/140494/15/9683/197269/5f744b82e8e0d8275/e3f36a76fe496d04.jpg",
        "https://liangcang-material.alicdn.com/prod/upload/1ba76953241c4ee284985755da7c987d.jpg?x-oss-process=image/resize,w_2074/interlace,1/quality,Q_80/sharpen,100",
        "https://liangcang-material.alicdn.com/prod/upload/04d003edc37f4c6caf2e5291c6a2f0aa.jpg?x-oss-process=image/resize,w_2074/interlace,1/quality,Q_80/sharpen,100",
        "https://liangcang-material.alicdn.com/prod/upload/807a85bb8c474adeab92f73527826c75.jpg?x-oss-process=image/resize,w_2074/interlace,1/quality,Q_80/sharpen,100",
      ],
      tabhotflag: true,
      tabupflag: false,
      list: null,
      listLoading: true,
      layout: "total, sizes, prev, pager, next, jumper",
      total: 0,
      selectRows: "",
      elementLoadingText: "正在加载......",
      queryForm: {
        pageNo: 1,
        pageSize: 20,
        username: "",
        data: {
          timetype: "zzry",
          selecttype: "byhot",
        },
      },
    };
  },
  created() {
    this.fetchData();
  },
  mounted() {},
  methods: {
    checkhot() {
      this.tabhotflag = true;
      this.tabupflag = false;
      this.queryForm.data.timetype = "zzry";
      this.fetchData();
    },
    checkup() {
      this.tabhotflag = false;
      this.tabupflag = true;
      this.queryForm.data.timetype = "jjsy";
      this.fetchData();
    },
    handleSizeChange(val) {
      this.queryForm.pageSize = val;
      this.fetchData();
    },
    handleCurrentChange(val) {
      this.queryForm.pageNo = val;
      this.fetchData();
    },
    queryData() {
      this.queryForm.pageNo = 1;
      this.fetchData();
    },
    async fetchData() {
      this.list = null;
      this.listLoading = true;
      const { data, totalCount } = await getList(this.queryForm);
      setTimeout(() => {
        this.list = data;
        this.total = totalCount;
        this.listLoading = false;
      }, 3000);
    },
  },
  components: {
    "film-show": filmShow,
  },
};
</script>
<style>
ul {
  list-style-type: none;
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.rotation {
  margin-top: 30px;
}

.small {
  width: 100%;
  height: 100%;
}

.tabnav {
  width: 100%;
  height: 80px;
  line-height: 80px;
  text-align: center;
  background-image: url("http://misc.360buyimg.com/virtuals/movie/1.0.0/img/tab.png");
}

.tab-nav-item {
  display: inline-block;
  width: 270px;
  position: relative;
  text-align: center;
  font-size: 20px;
  font-weight: 700;
  color: hsla(0, 0%, 100%, 0.45);
  cursor: pointer;
}

.tab-nav-item.tab-hot:before {
  content: " ";
  position: absolute;
  width: 24px;
  height: 24px;
  top: 27px;
  left: 60px;
  background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAABm0lEQVRIS72WvSuFcRTHP19k8LYYbFISNiWjt2SRLBajEhn9KUYlZTRLFsnbrGxISjaDxXUNwtG5nUeP6764ePzqGZ57fs/5nN/3nN85V1RYZtYG9AKdQDvQFNufgHvgFriU9FDOjUoZzKwVGA7ndZWCAN4cApxIyhXv/QIwsz5gEmgEXoCreO6AfDhoBjqAnngagGdgT9JFGvIJYGaDwCjgv3tUh5Ieq8jYAozFaQ04knSafPMBiMinAN90IOmsijSfzGY2AIxHcLvJSQqA0Hw+ZNmv1XlCCshEyLXpOUkAHnl/VMROQLsjF/7q2l6nQzazknYzmw65ziXtKkpxIaphI9HczJYBT6avvKS1IkBJu5l5TtyfV9+GA4aAEaBATB33R4A4faLIsQNmgS5gW5KXZGGVk+A7djPz8p0BbhywBPjFWq90I2usKO8Ai0DOAStAPbAq6bVKzc+5XdJWlX3uz/2+/gsgc4kyT3LmZeoZz+6iFV0MHx5/2yr+pdkFxAdNNu06df2zGzgpSHYjMwXJbugX9fxf/215BwLWQuIOk3udAAAAAElFTkSuQmCC)
    no-repeat;
}
.tab-nav-item.tab-hot.hotbg:before {
  background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAABpUlEQVRIS73Wu2pVURDG8d8o2tgJXtDWMg9gYaURLwQfQCsLW0GiKAgRA4JigmAvVvoAEqLiBYQU9lraKl7AziaiIyPryM7xeA6J7Oxyz9rzn/Wtb2btMObJzP04iYM4gJ1t+Ve8w2ssR8T7f6WJUYHM3IdZnMDWcUXgBx5jMSI+DK/9C5CZM5jHDqziCZ7jLT63BLsxhWkcx3Z8w1xELHUhawCZeRaXUe+XcTMiPk2QcQ+uNCkTtyLi/uCbP4BW+QJ+4kZEPJggzZpwZp7BVWzBxcFOfgOa5rW1kmV+vckHpAaZa3LN1JkMAIso7csRFxr0CK63j69FxItuyZk5Mp6Zd5pcSxExG82Kz5obpgeaZ+YKdrWkXyLi0BBgZDwz60zKFOW+owU4V5rhUURc6mx3Q4C2+9s4hYUC3ENVdz4innYAG5KoAY7hLlYK8Ap7cXhcR67TUTUBXuJjAd60RpmKiO8TPP+w4hFxesK6ba0xVzcF0LtEvR9y7zatE++v0Zpv+xsVmzLsGqSGXT/jujMe+rtwOpD+rswOpL9Lf2jm//dvyy88z0VsScKOqwAAAABJRU5ErkJggg==)
    no-repeat;
}

.tab-nav-item.tab-up:before {
  content: " ";
  position: absolute;
  width: 24px;
  height: 24px;
  top: 27px;
  left: 60px;
  background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAACK0lEQVRIS7WWzYtOcRTHP18jjfc0CgtSkreUSETeEkqSsmOjxMbGn2JvpuyNjTSbSQZhY0o2yAYLRWyM94yvznTudO99nnufKT237uY5557P+Z3zPef3iJbH9jJgE7AOGAIWpft34DPwDngl6UtTGHUz2F4KHMjg89qSAP4GBHgoaaru2wGwvRk4BiwA/gCv8/0AfMsAi4FVwMZ85wO/gXFJL8uQCsD2LuAQEL9HVhOSvvYo4xLgcJ7WwH1Jk8U3s4DM/CQQTvckPetRmorZ9g7gSCY3VpxkBpA1v5BludsW3PaApOmG3gXkaJbrRvSkAETmW1IRd5oytx1qWivpUYvPqSzXC0ljSileTDWMtNXc9rmU67CkHw2niJ5EvFDfSAB2AweBGWJLZhuAM2mflDTR4ltU5EEAzgLrgduSQpKzj+2FwMrMeiewIo0h38c5bJ/qg2Y75HsaeBOAy0AM1vWyo+39wN45Kikm+pakGLoQTWyAS8BUAK4CA8C1ujpsHwe294DEAN6U9KvwC6UBEXe6FyBUdgLY1gD5mMF/1kpbAXQtUSmbQeBKA6Cjb91K1NjkdF4NnG8AxFp4WrfVm9wqU9sxgCG7eN4CsZq3Zt+eSxrvAqjINDreOGi29wFrgCeS3pdKsAdYLmm0Vv/qoOUHjavC9qCkShPL/anbbFdXRQJiDua07Nokmxu1c9klJC6a/qzr0pH7d+GUIP27MkuQ/l36Ncn999+Wf8+ROOKEeeveAAAAAElFTkSuQmCC)
    no-repeat;
}
.tab-nav-item.tab-up.hotbg:before {
  background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAACSUlEQVRIS7WWS4iOYRTHf3/CwoIsECkLdqPsUFaMXBqk7JRLkixGNETRLKaR20yE7MTGBitN45pSlGwskAXbcVsIZWFc/jrTmen9Lu87U/qe5Xee5/md93/+5zyfqFi25wMbgOXAImBWbv8CvAOeAYOShsquUbOA7XlAF7AemFyVBPAHuAP0S3pfv7cBYLsD6AGmA8PAXeAh8Ar4nBfMBtqAdmAdMBX4AXRLGihCagC2dwFHgPh9EDgl6dM4Ms4BjqaUBk5Lujp6ZgyQmfcBf4ETkq6PI01N2PY24BgwCTg0+iUjgNQ8Pi1k6am63PY0ST9LaheQ7pSrI2oyCugHQvtwxMGyzG2vAJZJOl+x51zKNSCpS2nFB+mG9irNbd9Iu8a+sGrDsh01CVOE+9YEYE9oBtyWdLgis9XA5Yxfk3SyYu9ZYBPQF4ArwEpgv6R7xUO2o7EWZ9bbgYUZjxpcBN5mww1JCgeNLNtrgQvAkwA8BuYCq4odafsAsG+CToqO3i3pdwJiAjwCPgbgZTZKm6RfhSzCAL3A1nEgr4Gdkr4Xzk7JxhwuBWQmAQmtt5RA3gA7JH2rk7YG0FSiQjYzgOclgE5J9+tj6cwxiUqLnF+xBLhVAjgjKc7XrPoiV9rU9sawW97wFPgAbAZChpuSjjcB1Ng0Kl7aaLY7gaXAJUkvCi7ZCyyQFANybDU0Wh4oHRW2Z0r62kyiZjHbtaMiAfHATGjYVVk2J2rjsEtIDLvWjOuCJVv34BQgrXsyC5DWPfp1lvvvvy3/AIbQPGxFSeT8AAAAAElFTkSuQmCC)
    no-repeat;
}

.hotbg {
  color: #fff;
}

.hotbg:after {
  content: " ";
  position: absolute;
  width: 80px;
  height: 15px;
  bottom: -5px;
  left: 95px;
  background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFMAAAAKCAYAAADIM2I/AAABVElEQVRIS9XWMUtWURgH8N9TgVGUgRVEUyEtYXODmzSHIH2AglraAqkP0NLUYkNzs9LcYoODDeLQkEEtUVMS2FBR+cQjR2gRCd7X9/TAnztc7r3P+d3DOSd0Xpl5HVcj4kHnrYqeG8zMGTzHMdyPiEc999stZmbexBOM/QW4gHsR8aNH1O4wM/McHuPGHmDruBMRr3oD7QYzM8dxF/M4uQ/UNp7hYUS87QV15JiZeQm3cQun/hHmd1tTn+JFRBTyyGokmJl5AbOYq52agWyEH7CIJaxExK+DVj0QzMy82NCmcQ2TQx7oFl5iGatYi4hvQ/7mQGbETo+ZWT/mfIMqrMuYwhWcGfZA9nn/T7zB65YNvMP7iCj4gVQ0hEOoHMYRHG2pY8lxnGipjWGi5TTONsBCrNRz/1MlvuAjPrV8RmWz3fuK3dTs/o46mtW11uxKrdXbfwC+8FcJLMWA+gAAAABJRU5ErkJggg==)
    no-repeat;
}

.font-bold {
  font-family: JDZHENGHEI-01-BOLD;
}

.nut-radiogroup {
  margin-right: 40px;
  margin-top: 38px;
  margin-bottom: 24px;
  font-size: 12px;
  color: #252525;
}

.inline {
  margin-right: 40px;
  font-size: 15px;
}

.inline input[type="radio"] {
  vertical-align: middle;
}

.filmshow {
  width: 100%;
  min-height: 700px;
  margin: 0 auto;
  margin-top: 30px;
}

.filmshow ul {
  font-size: 0;
  text-align: center;
}

.filmshow ul li {
  display: inline-block;
  width: 18%;
  margin-right: 15px;
  text-align: center;
}

.filmshow ul li a {
  display: inline-block;
  width: 100%;
}

.el-pagination .is-background {
  margin-bottom: 10px;
}

.listLoading {
  width: 100%;
  height: 100%;
  /*background-color: #F6F8F9;*/
  text-align: center;
  margin: auto;
  position: relative;
  z-index: 1;
}

.loading-img {
  margin-top: 20%;
  border: 0;
  vertical-align: bottom;
  width: 82px;
  height: 82px;
  -webkit-animation: loading-rotate 3s linear infinite;
  animation: loading-rotate 3s linear infinite;
}

.loading-span {
  font-size: 20px;
  color: #ff5c5c;
  font-weight: bold;
}

@keyframes loading-rotate {
  0% {
    -webkit-transform: rotate(0deg);
    transform: rotate(0deg);
  }
  100% {
    -webkit-transform: rotate(359deg);
    transform: rotate(359deg);
  }
}

.film-page {
  text-align: center;
}

.film-page .el-pager li:not(.disabled).active {
  background-color: #f80f01 !important;
}
</style>
