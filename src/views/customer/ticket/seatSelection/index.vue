<template>
  <div class="seat-selection" v-if="loaded">
    <div class="main">
      <div class="seat-show">
        <div class="seat-example">
          <div class="selectable-example example">
            <span>可选座位</span>
          </div>
          <div class="sold-example example">
            <span>已售座位</span>
          </div>
          <div class="selected-example example">
            <span>已选座位</span>
          </div>
        </div>
        <div class="seats-block">
          <div class="row-id-container">
            <span class="row-id">1</span>
            <span class="row-id">2</span>
            <span class="row-id">3</span>
            <span class="row-id">4</span>
            <span class="row-id">5</span>
            <span class="row-id">6</span>
            <span class="row-id">7</span>
            <span class="row-id">8</span>
            <span class="row-id">9</span>
          </div>
          <div class="seats-container">
            <div class="screen-container" style="left: 0px">
              <div class="screen">银幕中央</div>
              <div class="c-screen-line"></div>
            </div>
          </div>
          <div class="seats-wrapper">
            <div class="row" v-for="item in row" :key="item">
              <span
                class="seat"
                v-for="item1 in column"
                :class="{
                  sold: soldSeats.indexOf(item + '排' + item1 + '座') != -1,
                  selected:
                    selectedSeats.indexOf(item + '排' + item1 + '座') != -1,
                }"
                @click="
                  soldSeats.indexOf(item + '排' + item1 + '座') == -1 &&
                    seatSelection(item + '排' + item1 + '座')
                "
                :key="item1"
              ></span>
            </div>
          </div>
        </div>
      </div>
      <div class="movie-show">
        <div class="movie-info clearfix">
          <div class="poster">
            <el-image
              style="width: 100%; height: 100%"
              :src="movieInfo.moviePicture"
              fit="cover"
            ></el-image>
          </div>
          <div class="content">
            <p class="name text-ellipsis">{{ movieInfo.movieName }}</p>
            <div class="info-item">
              <span>类型 :</span>
              <span class="value">{{ movieInfo.movieType }}</span>
            </div>
            <div class="info-item">
              <span>时长 :</span>
              <span class="value">{{ movieInfo.movieTime }}</span>
            </div>
          </div>
        </div>

        <div class="show-info">
          <div class="info-item">
            <span>影厅 :</span>
            <span class="value text-ellipsis"
              >{{ arrangementInfo.arrangementPlace }}号厅</span
            >
          </div>
          <div class="info-item">
            <span>场次 :</span>
            <span class="value text-ellipsis screen">
              {{ getArrangementTime(arrangementInfo.arrangementTime) }}</span
            >
          </div>
          <div class="info-item">
            <span>票价 :</span>
            <span class="value text-ellipsis"
              >￥{{ arrangementInfo.arrangementPrice }}/张</span
            >
          </div>
        </div>

        <div class="ticket-info">
          <div class="no-ticket" v-if="isEmpty">
            <p class="buy-limit">座位：一次最多选6个座位</p>
            <p class="no-selected">请<span>点击左侧</span>座位图选择座位</p>
          </div>
          <div class="has-ticket" style="" v-else>
            <span class="text">座位：</span>
            <div class="ticket-container">
              <span
                class="ticket"
                v-for="(item, index) in selectedSeats"
                @click="seatSelection(item)"
                :key="index"
                >{{ item }}</span
              >
            </div>
          </div>

          <div class="total-price">
            <span>总价 :</span>
            <span class="price">{{
              selectedSeats.length * arrangementInfo.arrangementPrice
            }}</span>
          </div>
        </div>

        <div class="confirm-order">
          <button
            v-if="this.accessToken"
            class="iLoginComp-login-btn-wrapper"
            :class="{ disable: selectedSeats.length == 0 }"
            id="iloginBtn"
            data-act="confirm-click"
            data-bid="b_0a0ep6pp"
            @click="selectedSeats.length > 0 && placeOrder()"
          >
            确认选座
          </button>
          <button
            v-else
            class="iLoginComp-login-btn-wrapper"
            id="iloginBtn"
            data-act="confirm-click"
            data-bid="b_0a0ep6pp"
            @click="login"
          >
            登陆
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { arrangementInfo } from "@/api/Arrangements";
import { generateOrder } from "@/api/order";
import { mapGetters } from "vuex";

export default {
  name: "SeatSelection",
  data() {
    return {
      loaded: false,
      row: [1, 2, 3, 4, 5, 6, 7, 8, 9],
      column: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
      soldSeats: [],
      selectedSeats: [],
      movieInfo: {},
      arrangementInfo: {},
      queryForm: {
        arrangementId: this.$route.query.arrangementId,
      },
      generateForm: {
        sqlOrder: {},
        selectedSeat: [],
      },
    };
  },
  created() {
    this.fetchArrangementInfo();
  },
  watch: {
    $route: "fetchArrangementInfo",
  },
  mounted() {},
  computed: {
    ...mapGetters({
      username: "user/username",
      accessToken: "user/accessToken",
      permissions: "user/permissions",
    }),
    isEmpty: function () {
      return this.selectedSeats.length == 0;
    },
  },
  methods: {
    seatSelection(data) {
      if (this.selectedSeats.indexOf(data) == -1) {
        if (this.selectedSeats.length < 6) {
          this.selectedSeats.push(data);
        } else {
          this.$message({
            message: "一次最多购买6张票",
            type: "warning",
          });
        }
      } else {
        const index = this.selectedSeats.indexOf(data);
        this.selectedSeats.splice(index, 1);
      }
    },
    async fetchArrangementInfo() {
      console.log(this.queryForm);
      console.log(this.$router.arrangementId);
      const data = await arrangementInfo(this.queryForm);
      this.arrangementInfo = data.arrangementInfo;
      this.movieInfo = data.movieInfo;
      this.soldSeats = data.soldSeats;
      this.loaded = true;
    },
    getArrangementTime(data) {
      const date = new Date(data);
      return (
        date.getMonth() +
        1 +
        "月" +
        date.getDate() +
        "日 " +
        date.getHours() +
        ":" +
        date.getMinutes()
      );
    },
    async placeOrder() {
      const nowDate = new Date();
      const arrDate = new Date(this.arrangementInfo.arrangementTime);
      nowDate.setMinutes(nowDate.getMinutes() + 15);
      if (nowDate - arrDate < 0) {
        this.generateForm.sqlOrder.arrangementId = this.$route.query.arrangementId;
        this.generateForm.sqlOrder.movieId = this.movieInfo.movieId;
        this.generateForm.sqlOrder.customerId = this.accessToken.split("-")[0];
        this.generateForm.sqlOrder.orderPrice =
          this.selectedSeats.length * this.arrangementInfo.arrangementPrice;
        this.generateForm.selectedSeat = this.selectedSeats;
        console.log(this.generateForm);
        const { message, orderId } = await generateOrder(this.generateForm);
        if (message == "success") {
          this.$router.push({
            path: "/ticket/orderGenerated",
            query: {
              orderId: orderId,
            },
          });
        } else {
          this.$message({
            message: "订票失败,请重新选择座位!",
            type: "warning",
          });
          // setTimeout(() => {
          //   this.$router.go(0);
          // }, 1000);
        }
      } else {
        this.loaded = false;
        this.$message({
          message: "本场次暂未开放售票",
          type: "warning",
        });
        setTimeout(() => {
          this.$router.go(-1);
        }, 2000);
      }
    },
    login() {
      this.$router.push({
        path:
          "/login?redirect=movies/seatSelection?arrangementId=1&&type=customer",
      });
    },
  },
};
</script>
<style>
.seat-selection {
  width: 1202px;
  margin: auto;
  margin-top: 40px;
}

.seat-selection .alert {
  position: fixed;
  width: 600px;
  height: 30px;
  left: 0px;
  top: 0px;
  right: 0px;
  bottom: 0px;
  margin: auto;
}

.seat-selection .main {
  width: 100%;
  border: 1px solid #e5e5e5;
  font-size: 0;
  padding-bottom: 100px;
}
.seat-selection .main .seat-show {
  width: 820px;
  display: inline-block;
  vertical-align: top;
}
.seat-show .seat-example {
  font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: subpixel-antialiased;
  font-size: 0;
  margin: 30px 0 30px 118px;
}
.seat-example .example {
  display: inline-block;
  font-size: 16px;
  color: #666;
  height: 26px;
  line-height: 26px;
  padding-left: 38px;
  background-repeat: no-repeat;
  margin-right: 50px;
}

.seat-show .selectable-example {
  background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAaCAYAAACgoey0AAAAAXNSR0IArs4c6QAAAY9JREFUSA3tVs1qg0AQ3l1/i5eCFC8+QSC0+BQ999QeC32t9lLovac8RQwk6BOUXLzVitFE0/mEDVIiJsbkUgeM7jjzfd/OjpvlrGZhGL6uVqvnsix5zX3yoxBia5rm22g0etkL5vt+WRTFtm8DJrDrpKI+wExJXd3VyzMw/1axf5YDpe6IqRSPUJZlGdQdmN4eBixgAps4nmRGRUyOCTneXddlnHOWJEkv5CAFFjCBTeOP2Wz2CXI+nU7vqZkmnudVAXBuNptKpWVZGHa2OI4ZdTPTNK3CIB42n89TarYHQWpuHcfJoEqaqqqViFNKDhKUV5ICGxy2bQt6d4cfU1EUQ5LKO4KQ3NWQW5+MxAEXCbraNZd8can7QHypSrOh1EOpz1YBtQkZOw+2zK67V1teI7FhGGy9XjfpavWDWNf1xrhGYtpTGa5z2f/6jmkZFPwff0VR1H0xj1yL5XKJhv3hQRDoaZriILClKz8S5+hwIk3ptHNTHTtowBeLxXWe5/2d8vZIoi4X4/H4myZY/AJMWAAq2pF/7QAAAABJRU5ErkJggg==);
}

.sold-example {
  background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAaCAYAAACgoey0AAAAAXNSR0IArs4c6QAAAeRJREFUSA3tlr9Lw0AUx99dmyZtpRbBX6AOoiAG1KF1EXFUcBMEHRX/LV0EFyfBUhwFJwWdXC2CIKKIttU0TXLPd6kJaaFQz3bSB0kud+99P3ePl7swiNjZ4uz+h+PueAgs0v3rZowBpjXtYPXydi8QiwcN+ZTQlbEBFmNd5YKHyM4fXncJEYJ5FCxX2m2o1JearVlsAkcn0et2CD7NmVt6nMPdu0Up97rGrdY9X1NqFxbN7UDYBxfzs0XO4HCqPwVxzuH6uQIVxw18lJ9VWsDNSwU0EpfaQoijYt48kYKskDfXBIri0mgWaNy3Z8uBUtmC3FAm7GuMdH5HBLh4fIPpbBJGUrofKKjv6qlsuQgbHBnMj/cZdgCVHoNJzS8IyxWdk1o8bSFAi/EQKoclYzitySwvcCbQoKJrTCkSnCAvh4JVzaXlGdHVfAtxYDoCJsPiUgWoxv2DVTP347j/VP84ZaoBfy/VTT8C0bQ5KKDmefBiqe1etB+D3JvbWVvwZCYFj582xan9jSCdEhMZox0X2oKzehzk1Sv7Y8WFLMaR48PTZ93pVUpbde8rNaBjuMpuN83EXUnYVEJIx2e91bHb7/SNWOn08KBfslSBrLA8l9WNhNq30+Hs7Fqdr4/OlNnxsfcFxEaatII5mN4AAAAASUVORK5CYII=);
}
.selected-example {
  background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAaCAYAAACgoey0AAAAAXNSR0IArs4c6QAAAoxJREFUSA3Fls9LFGEYx78z7rq7WuEiQqEVi1SHwjrVSU9aghURJYa2UUgd+nEuKAg6dK7cQ6FSCYrdosK/oFupFF46CLkFBZm7s+K4P2benmfGHRx3t5nZXdln2Zl5n3me5/O+z/vMMyNhk0RfY0zL4qrQIW1SV3wpyRA+P8ZfRTGcD+bLX/CZoeF9kMiwqiIEpJXvuEZBLbANwSutNpRXIFH+tmbRBq7qMh2CWeDLoxjgnV1bNlLu4Ob+di5txuTYQ+O4lPc0wEOjmNGAicZmUpMmEQfYoVLhGMmflOo6gGPrGiYHx/CW40oE7dUFZpojPDJR6RTNcgVo2mvuj6n1flxeJGALENy54SuAv0tQJR3nZR042hBGOg9lkwAZcpFRlXuSPY1H4JODho+eoxj0zFhQ1tLCgrsgU5Ufk2kQpIkEthJkSg9VomvparuDeye+4vjuqOFDaYVsVZAtTICYoeK3bHbOg66227hw8AmWlE+Y/T3t7EAWrsFXDk+he//dgqCdrbcI+tSAxuZ7sK4lC2yKKVyD6+UGnG1/jL7IIytOZ+tNXDz0jKCfEZs/CTWXsO45Xdha5v+MXy4M4HrHO5yK3Iegn5L5RdARxFOzBO0hKD0GHsQ1OKurePHlDG50vEdv5IGBiKfmMDLX7RnKzq5TzcYMf07whT8fsJj4iFiZUI7lesVszJLV1wh+2hxUcPS04go4Ba41A5dMNXceLUONnVpfWUJdb6P1F3UvCea3ybpS1MedkvpwKFzatCTYHwL4v11Ssz2uFbiOwT8yCjy+ecvfAJU7q8Cq9PAN6r8ljQ8BQbOgOt5eoZpTD7Sjxah4/u4dnETTDj88vPq9T3A1C/mcH0p/P7R/ODnAZaZCuGEAAAAASUVORK5CYII=);
}

.seats-block {
  font-size: 0;
  overflow: hidden;
  margin-left: 20px;
  white-space: nowrap;
}
.seats-block .row-id-container {
  width: 20px;
  float: left;
  margin-top: 112px;
  white-space: normal;
}
.seats-block .row-id {
  font-size: 16px;
  color: #999;
  margin-right: 40px;
  margin-bottom: 10px;
  display: inline-block;
  width: 20px;
  height: 26px;
  line-height: 29px;
  text-align: center;
}

.seats-block .seats-container {
  margin-left: 50px;
  overflow: auto;
  position: relative;
  padding-top: 112px;
}
.seats-block .screen-container {
  display: inline-block;
  position: absolute;
  top: 0;
  bottom: 0;
  z-index: -1;
  left: 0;
}
.seats-block .screen-container .screen {
  width: 550px;
  padding-top: 50px;
  text-align: center;
  font-size: 16px;
  color: #666;
  background: url(data:image/png;base64,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)
    no-repeat;
  background-position-x: center;
  position: relative;
  margin-bottom: 40px;
}

.seats-block .screen-container .c-screen-line {
  width: 0;
  border-left: 1px dashed #e5e5e5;
  position: absolute;
  top: 90px;
  bottom: 0;
  left: 50%;
}

.seats-block .seats-wrapper {
  display: inline-block;
  min-width: 550px;
  margin-left: 30px;
}
.seats-block .row {
  white-space: nowrap;
  margin-bottom: 10px;
  text-align: center;
}

.seats-block .seat {
  display: inline-block;
  font-size: 0;
  width: 30px;
  height: 26px;
  margin: 0 5px;
  background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAaCAYAAACgoey0AAAAAXNSR0IArs4c6QAAAY9JREFUSA3tVs1qg0AQ3l1/i5eCFC8+QSC0+BQ999QeC32t9lLovac8RQwk6BOUXLzVitFE0/mEDVIiJsbkUgeM7jjzfd/OjpvlrGZhGL6uVqvnsix5zX3yoxBia5rm22g0etkL5vt+WRTFtm8DJrDrpKI+wExJXd3VyzMw/1axf5YDpe6IqRSPUJZlGdQdmN4eBixgAps4nmRGRUyOCTneXddlnHOWJEkv5CAFFjCBTeOP2Wz2CXI+nU7vqZkmnudVAXBuNptKpWVZGHa2OI4ZdTPTNK3CIB42n89TarYHQWpuHcfJoEqaqqqViFNKDhKUV5ICGxy2bQt6d4cfU1EUQ5LKO4KQ3NWQW5+MxAEXCbraNZd8can7QHypSrOh1EOpz1YBtQkZOw+2zK67V1teI7FhGGy9XjfpavWDWNf1xrhGYtpTGa5z2f/6jmkZFPwff0VR1H0xj1yL5XKJhv3hQRDoaZriILClKz8S5+hwIk3ptHNTHTtowBeLxXWe5/2d8vZIoi4X4/H4myZY/AJMWAAq2pF/7QAAAABJRU5ErkJggg==)
    no-repeat;
  background-position: 0 1px;
}
.seats-block .seat.sold {
  background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAaCAYAAACgoey0AAAAAXNSR0IArs4c6QAAAeRJREFUSA3tlr9Lw0AUx99dmyZtpRbBX6AOoiAG1KF1EXFUcBMEHRX/LV0EFyfBUhwFJwWdXC2CIKKIttU0TXLPd6kJaaFQz3bSB0kud+99P3ePl7swiNjZ4uz+h+PueAgs0v3rZowBpjXtYPXydi8QiwcN+ZTQlbEBFmNd5YKHyM4fXncJEYJ5FCxX2m2o1JearVlsAkcn0et2CD7NmVt6nMPdu0Up97rGrdY9X1NqFxbN7UDYBxfzs0XO4HCqPwVxzuH6uQIVxw18lJ9VWsDNSwU0EpfaQoijYt48kYKskDfXBIri0mgWaNy3Z8uBUtmC3FAm7GuMdH5HBLh4fIPpbBJGUrofKKjv6qlsuQgbHBnMj/cZdgCVHoNJzS8IyxWdk1o8bSFAi/EQKoclYzitySwvcCbQoKJrTCkSnCAvh4JVzaXlGdHVfAtxYDoCJsPiUgWoxv2DVTP347j/VP84ZaoBfy/VTT8C0bQ5KKDmefBiqe1etB+D3JvbWVvwZCYFj582xan9jSCdEhMZox0X2oKzehzk1Sv7Y8WFLMaR48PTZ93pVUpbde8rNaBjuMpuN83EXUnYVEJIx2e91bHb7/SNWOn08KBfslSBrLA8l9WNhNq30+Hs7Fqdr4/OlNnxsfcFxEaatII5mN4AAAAASUVORK5CYII=);
}
.seats-block .seat.selected {
  background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAaCAYAAACgoey0AAAAAXNSR0IArs4c6QAAAoxJREFUSA3Fls9LFGEYx78z7rq7WuEiQqEVi1SHwjrVSU9aghURJYa2UUgd+nEuKAg6dK7cQ6FSCYrdosK/oFupFF46CLkFBZm7s+K4P2benmfGHRx3t5nZXdln2Zl5n3me5/O+z/vMMyNhk0RfY0zL4qrQIW1SV3wpyRA+P8ZfRTGcD+bLX/CZoeF9kMiwqiIEpJXvuEZBLbANwSutNpRXIFH+tmbRBq7qMh2CWeDLoxjgnV1bNlLu4Ob+di5txuTYQ+O4lPc0wEOjmNGAicZmUpMmEQfYoVLhGMmflOo6gGPrGiYHx/CW40oE7dUFZpojPDJR6RTNcgVo2mvuj6n1flxeJGALENy54SuAv0tQJR3nZR042hBGOg9lkwAZcpFRlXuSPY1H4JODho+eoxj0zFhQ1tLCgrsgU5Ufk2kQpIkEthJkSg9VomvparuDeye+4vjuqOFDaYVsVZAtTICYoeK3bHbOg66227hw8AmWlE+Y/T3t7EAWrsFXDk+he//dgqCdrbcI+tSAxuZ7sK4lC2yKKVyD6+UGnG1/jL7IIytOZ+tNXDz0jKCfEZs/CTWXsO45Xdha5v+MXy4M4HrHO5yK3Iegn5L5RdARxFOzBO0hKD0GHsQ1OKurePHlDG50vEdv5IGBiKfmMDLX7RnKzq5TzcYMf07whT8fsJj4iFiZUI7lesVszJLV1wh+2hxUcPS04go4Ba41A5dMNXceLUONnVpfWUJdb6P1F3UvCea3ybpS1MedkvpwKFzatCTYHwL4v11Ssz2uFbiOwT8yCjy+ecvfAJU7q8Cq9PAN6r8ljQ8BQbOgOt5eoZpTD7Sjxah4/u4dnETTDj88vPq9T3A1C/mcH0p/P7R/ODnAZaZCuGEAAAAASUVORK5CYII=);
}

.seat-selection .main .movie-show {
  width: 340px;
  background-color: #f9f9f9;
  padding: 20px;
  display: inline-block;
}

.movie-show .movie-info .poster {
  width: 115px;
  height: 158px;
  border: 2px solid #fff;
  -webkit-box-shadow: 0 2px 7px 0 hsla(0, 0%, 53%, 0.5);
  box-shadow: 0 2px 7px 0 hsla(0, 0%, 53%, 0.5);
  float: left;
}

.movie-show .movie-info .content {
  margin-left: 140px;
}
.movie-show .movie-info .content .name {
  font-size: 20px;
  font-weight: 700;
  color: #333;
  margin: 0 0 14px;
}
.movie-show .info-item {
  font-size: 12px;
  color: #999;
  margin-bottom: 4px;
}
.movie-show .info-item .value {
  color: #151515;
  margin-left: 2px;
}

.movie-show .show-info {
  margin-top: 110px;
}

.movie-show .show-info .info-item {
  margin-bottom: 9px;
  clear: both;
}

.movie-show .show-info .info-item > span {
  display: inline-block;
  vertical-align: top;
  font-size: 14px;
}

.movie-show .show-info .info-item .value {
  width: 85%;
}

.movie-show .ticket-info {
  padding: 20px 0 10px;
  border-top: 1px dashed #e5e5e5;
  border-bottom: 1px dashed #e5e5e5;
}

.movie-show .ticket-info .buy-limit {
  font-size: 14px;
  color: #999;
  margin: 0;
}

.movie-show .ticket-info .no-selected {
  font-size: 14px;
  color: #333;
  text-align: center;
  margin: 28px 0 39px;
}

.movie-show .ticket-info .no-selected span {
  color: #f03d37;
}

.movie-show .ticket-info .text {
  font-size: 14px;
  color: #999;
  float: left;
}
.movie-show .ticket-info .ticket-container {
  margin-left: 42px;
  margin-bottom: 20px;
  position: relative;
  top: -5px;
}

.movie-show .ticket-info .ticket {
  cursor: default;
  position: relative;
  font-size: 12px;
  color: #f03d37;
  display: inline-block;
  width: 60px;
  height: 30px;
  line-height: 30px;
  text-align: center;
  margin: 0 12px 10px 0;
  background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADwAAAAeCAYAAABwmH1PAAAAAXNSR0IArs4c6QAAAXlJREFUWAlj/Oho7cXAzLTg/89fogzDGDCys71m+PsvgeGji+2r38eP/h/u4Pexo/9BfmUCxSyLhdUwjluI11gsrRhAfmUa9j5F8+Coh9ECZNhxR2N42EUpmodGZgx/TYz9+nvzhn9ogTEsuUysDGxS/548jP4+qe/0l6zUL//fvh2WHoV5ihHGALa0GD872rQxamrm8EybzQMTH070RzsLBngeZmRk/M+7/0jVv9s3rw7n5A33MCgmQZ4GRnn7r3Vrvw+nmEX2C4qHQRLMPxlO/X38kA1Z0XBiY3h4OHkOm18wPPyXncGMWVb+FzbFw0EMxcOgkvo/A0MlW1Aw53DwHDY/wD0Mq5aYVNW1WX0D4OLYNA1lMZavtraSoGT8ydWukklTU5u7uWNY1sGwSGL5zfDrGYuM6ldQMh7OMQv3MIjBPX8xN0xguNPDNq/iirhRD+MKmeEiPhrDwyUmcflj5MUwaM7lz/FjuAJk2IiD/AjyK+NIm0wDAACxUs8MaULTAAAAAElFTkSuQmCC)
    no-repeat;
}

.movie-show .ticket-info .total-price {
  font-size: 14px;
  color: #333;
}

.movie-show .ticket-info .total-price .price {
  color: #f03d37;
  font-size: 24px;
}

.movie-show .ticket-info .total-price .price:before {
  content: "\FFE5";
  font-size: 14px;
}

.confirm-order {
  text-align: center;
  margin-top: 20px;
}

.confirm-order .iLoginComp-login-btn-wrapper {
  -webkit-font-smoothing: subpixel-antialiased;
  visibility: visible;
  font: inherit;
  margin: 0;
  overflow: visible;
  text-transform: none;
  text-align: center;
  background: #e5e5e5;
  height: 2.8em;
  border: none;
  outline: none;
  cursor: pointer;
  user-select: none;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
  width: 260px;
  line-height: 42px;
  border-radius: 21px;
  font-size: 16px;
  color: #fff;
  box-shadow: 0 2px 10px -2px #f03d37;
  background-color: #f03d37;
}

.movie-show .confirm-order .iLoginComp-login-btn-wrapper.disable {
  cursor: default;
  background-color: #dedede;
  -webkit-box-shadow: none;
  box-shadow: none;
}
</style>