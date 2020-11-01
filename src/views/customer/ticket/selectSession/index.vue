<template>
  <div class="select-session" v-if="loaded">
    <div class="movie-info">
      <div>
        <h2 class="movie-name">{{ movieInfo.movieName }}</h2>

        <span class="score sc">{{ movieInfo.movieScore }}</span>
      </div>

      <div class="movie-desc">
        <div>
          <span class="key">时长 :</span>
          <span class="value">{{ movieInfo.movieTime }}</span>
        </div>
        <div>
          <span class="key">类型 :</span>
          <span class="value">
            <span> {{ movieInfo.movieType }} </span>
          </span>
        </div>
        <div>
          <span class="key">主演 :</span>
          <a
            class="text-link"
            :href="'https://baike.baidu.com/item/' + item"
            target="_blank"
            one-link-mark="yes"
            v-for="(item, index) in actors"
            :key="index"
          >
            {{ item }}</a
          >
        </div>
      </div>
    </div>
    <div class="date">
      <span>观影时间 :</span>
      <span
        class="date-time"
        :class="{ active: item.arrangementDate == selectedDate }"
        v-for="(item, index) in arrangementList"
        @click="showArrangement(item.arrangementDate)"
        :key="index"
        >{{ getTagTime(item.arrangementDate) }}</span
      >
    </div>
    <div>
      <div
        class="plist-container"
        :class="{ active: item1.arrangementDate == selectedDate }"
        v-for="(item1, index1) in arrangementList"
        :key="'a' + index1"
      >
        <table class="plist">
          <thead>
            <tr>
              <th>放映时间</th>
              <th>放映厅</th>
              <th>售价（元）</th>
              <th>选座购票</th>
            </tr>
          </thead>

          <tbody>
            <tr
              class=""
              v-for="(item2, index2) in item1.theDayArrangement"
              :key="'b' + index2"
            >
              <td>
                <span class="begin-time">{{
                  getBeginTime(item2.arrangementTime)
                }}</span>
                <br />
                <span class="end-time"
                  >{{
                    getEndTime(item2.arrangementTime, movieInfo.movieTime)
                  }}散场</span
                >
              </td>
              <td>
                <span class="hall">{{ item2.arrangementPlace }}号厅</span>
              </td>
              <td>
                <span class="sell-price"
                  ><span class="stonefont">{{
                    item2.arrangementPrice
                  }}</span></span
                >
              </td>
              <td>
                <a
                  class="buy-btn normal"
                  one-link-mark="yes"
                  @click="
                    jumpSelectSeat(item2.arrangementId, item2.arrangementTime)
                  "
                  >选座购票</a
                >
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>
<script>
import { selectSession } from "@/api/Arrangements";
import path from "path";
import { log } from "util";
export default {
  name: "SelectSession",
  data() {
    return {
      loaded: false,
      arrangementList: [],
      movieInfo: [],
      actors: [],
      selectedDate: String,
      ticketStep: 1,
      queryForm: {
        movieId: this.$route.query.movieId,
      },
    };
  },
  computed: {
    movieId: function () {
      return this.$route.query.movieId;
    },
    endTime: function (arrangementTime, movieTime) {},
  },
  created() {
    this.fetchData();
  },
  mounted() {},
  methods: {
    getBeginTime(data) {
      const arrdate = new Date(data);
      return arrdate.getHours() + ":" + arrdate.getMinutes();
    },

    getEndTime(data, movieTime) {
      const startTime = new Date(data);
      startTime.setMinutes(
        startTime.getMinutes() + parseInt(movieTime.replace("分钟", ""))
      );
      return startTime.getHours() + ":" + startTime.getMinutes();
    },

    getTagTime(data) {
      const tagTime = new Date(data);
      return tagTime.getMonth() + 1 + "月" + tagTime.getDate() + "日";
    },

    async fetchData() {
      const result = await selectSession(this.queryForm);
      if (result.arrangementList.length > 0) {
        this.arrangementList = result.arrangementList;
        this.selectedDate = this.arrangementList[0].arrangementDate;
        console.log(this.arrangementList[0]);
      }
      this.movieInfo = result.movie;
      this.actors = this.movieInfo.movieActor.split("/");
      this.loaded = true;
    },

    showArrangement(data) {
      this.selectedDate = data;
    },

    jumpSelectSeat(arrangementId, arrangementTime) {
      const stopTime = new Date(arrangementTime);
      const nowTime = new Date();
      stopTime.setMinutes(stopTime.getMinutes() + 15);
      log(stopTime + "" + nowTime);
      if (stopTime - nowTime <= 0) {
        this.loaded = false;
        this.$message({
          message: "本场次暂未开放售票",
          type: "warning",
        });
        setTimeout(() => {
          window.location.reload();
        }, 2000);
      } else {
        this.$router.push({
          path: "/ticket/seatSelection",
          query: {
            arrangementId: arrangementId,
          },
        });
      }
    },
  },
};
</script>
<style>
.select-session {
  font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: subpixel-antialiased;
  width: 1200px;
  margin: auto;
  margin-top: 80px;
}
.select-session .date {
  padding: 30px 0;
  font-size: 14px;
  color: #999;
  border-top: solid 1px #e5e5e5;
}
.select-session .date .date-time {
  font-size: 14px;
  display: inline-block;
  background-color: transparent;
  border-radius: 100px;
  color: #333;
  padding: 2px 10px;
  margin-left: 12px;
  cursor: default;
}
.select-session .date .date-time.active {
  color: #fff;
  background-color: #f03d37;
}

.select-session .movie-info .movie-name {
  display: inline-block;
  margin: 0 20px 20px 0;
  font-size: 26px;
  font-weight: 400;
  color: #333;
}
.select-session .movie-info .score {
  display: inline-block;
  font-size: 24px;
  color: #faaf00;
}
.select-session .movie-info .sc:after {
  content: "\5206";
  font-size: 12px;
}
.select-session .movie-info .movie-desc .key {
  font-size: 14px;
  color: #999;
}

.select-session .movie-info .movie-desc > div {
  display: inline-block;
  font-size: 14px;
  color: #151515;
  margin-bottom: 20px;
  margin-right: 40px;
}
.select-session .movie-info a.text-link {
  color: inherit;
}

/* 表格属性 */
.select-session .plist-container {
  display: none;
}
.select-session .plist-container.active {
  display: block;
}
.select-session .plist {
  width: 100%;
  border: none;
  border-spacing: 0;
}
.select-session .plist thead {
  background-color: #f7f7f7;
  color: #333;
  font-size: 16px;
  padding: 18px 0;
}
tr {
  display: table-row;
  vertical-align: inherit;
  border-color: inherit;
}
th {
  display: table-cell;
  vertical-align: inherit;
  font-weight: bold;
  text-align: -internal-center;
}
tbody {
  display: table-row-group;
  vertical-align: middle;
}
.select-session .plist thead th {
  padding: 16px 0;
}
.select-session .plist tbody tr {
  height: 82px;
}
.select-session .plist tbody td {
  text-align: center;
  width: 20%;
}
.select-session .plist tbody .begin-time {
  font-size: 18px;
  color: #333;
  font-weight: 700;
}
.select-session .plist tbody .end-time {
  font-size: 12px;
  color: #999;
}
.select-session .plist tbody .hall,
.select-session .plist tbody .lang {
  font-size: 14px;
  color: #333;
}
.select-session .plist tbody .sell-price {
  font-size: 18px;
  color: #f03d37;
  font-weight: 700;
}
.select-session .plist tbody .sell-price:before {
  content: "\FFE5";
  font-size: 12px;
}
.stonefont {
  font-family: stonefont;
}
.select-session .plist tbody .buy-btn:hover {
  -webkit-box-shadow: 0 2px 10px -2px #f03d37;
  box-shadow: 0 2px 10px -2px #f03d37;
}
.select-session .plist tbody .buy-btn {
  display: inline-block;
  width: 80px;
  height: 30px;
  color: #fff;
  background-color: #f03d37;
  font-size: 14px;
  line-height: 30px;
  border-radius: 100px;
  text-align: center;
}
.select-session .plist tbody tr:nth-child(even) {
  background-color: #f9f9f9;
}
</style>