<template>
  <div class="movieDetail" v-if="flag">
    <div class="movieDetail-top">
      <span style="display: none">{{ movieName }}</span>
      <div class="movie-info-movieshow">
        <div class="movie-info-moviepicture">
          <div class="movie-yugao-play">
            <a :href="movieInfo.movie.movieTrailer">
              <img
                src="data:image/png;base64,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"
              />
            </a>
          </div>

          <el-image
            style="
              width: 232px;
              height: 322px;
              border: solid 1px #c9bac0;
              box-shadow: 3px 3px 10px white;
            "
            :src="movieInfo.movie.moviePicture"
          >
            <div slot="placeholder" class="image-slot">
              加载中<span class="dot">...</span>
            </div>
            <div slot="error" class="image-slot">
              <i class="el-icon-picture-outline"></i>
            </div>
          </el-image>
        </div>
        <div class="movie-info-movieshow-right">
          <p style="font-size: 26px; font-weight: 800; color: #ffffff">
            {{ movieInfo.movie.movieName }}
          </p>
          <br /><br /><br />
          <ul class="movie-info-smallinfo">
            <li>{{ movieInfo.movie.movieType }}</li>
            <li>{{ movieInfo.movie.movieTime }}</li>
            <li>{{ movieInfo.movie.movieRealese_Time }}</li>
          </ul>
          <div class="movieshow-actionbuttom">
            <a class="wish" @click="addwant">
              <div>
                <i
                  class="icon wish-icon"
                  :class="{ wanted: movieInfo.wantflag }"
                ></i>
                <span
                  class="wish-msg"
                  data-act="wish-click"
                  v-if="!movieInfo.wantflag"
                  >想看</span
                >
                <span
                  class="wish-msg"
                  data-act="wish-click"
                  v-else-if="movieInfo.wantflag"
                  >已想看</span
                >
              </div>
            </a>
            <a class="movieScore-btn">
              <div>
                <i
                  class="icon movieScore-btn-icon"
                  :class="{ scored: scoreflag }"
                ></i>
                <span class="movieScore-btn-msg" v-if="!scoreflag">评分</span>
                <span
                  class="movieScore-btn-msg movieScore-num"
                  v-else-if="scoreflag"
                  >{{ movieInfo.myScore }}</span
                >
              </div>
            </a>
            <a
              class="btn buy"
              target="_blank"
              @click="jumpBuyTicket(movieInfo.movie.movieId)"
              >特惠购票</a
            >
          </div>
        </div>
        <div class="movie-movieScore-all" v-if="movieInfo.movie.movieScore > 0">
          <p style="font-size: 26px; font-weight: 500; color: white">
            影片评分
          </p>
          <br />
          <div class="movie-movieScore-all-part">
            <div
              style="
                width: 210px;
                float: left;
                background-color: rgba(0, 0, 0, 0.2);
                border-radius: 6px 0 0 6px;
                text-align: center;
              "
            >
              <el-rate
                style="height: 41px; margin-top: 4px"
                v-model="movieInfo.movie.movieScore"
                disabled
              ></el-rate>
            </div>
            <span class="movieScore-all-part-scorenum">{{
              movieInfo.movie.movieScore
            }}</span>
          </div>
        </div>
        <div class="movie-movieScore-all" v-else>
          <p style="font-size: 20px; font-weight: 500; color: white">
            暂无评分
          </p>
        </div>
      </div>
    </div>
    <hr style="color: #eeeeee; margin-top: 70px" />
    <div class="movieDetail-middle-right">
      <h2>相关电影</h2>
      <div class="relativeMovies">
        <dl>
          <dd
            v-for="(item, index) in movieInfo.relativeMovies"
            :key="index"
            style="
              width: 106px;
              float: left;
              margin-left: 20px;
              margin-bottom: 22px;
            "
          >
            <a @click="showMovie(item.movieId)">
              <el-image
                style="width: 106px; height: 145px; cursor: pointer"
                :src="item.moviePicture"
                fit="cover"
              >
                <div slot="error" class="image-slot">
                  <i class="el-icon-picture-outline"></i>
                </div>
              </el-image>
              <p style="cursor: pointer">{{ item.movieName }}</p>
            </a>
            <p style="color: #ffc861" v-if="item.movieScore > 0">
              {{ item.movieScore }}
            </p>
            <p style="color: #ffc861" v-else>暂无评分</p>
          </dd>
        </dl>
      </div>
    </div>
    <div class="movieDetail-middle">
      <h2>剧情简介</h2>
      <br />
      <span>{{ movieInfo.movie.movieDes }}</span
      ><br /><br /><br />
      <h2>演职人员</h2>
      <br />
      <h3>导演</h3>
      <br />
      <span style="margin-left: 20px"
        ><a
          :href="'https://baike.baidu.com/item/' + item"
          v-for="(item, index) in directors"
          :key="'d' + index"
          >{{ item }}</a
        ></span
      ><br /><br />
      <h3>演员</h3>
      <br />
      <span style="margin-left: 20px"
        ><a
          :href="'https://baike.baidu.com/item/' + item"
          v-for="(item, index) in actors"
          :key="'a' + index"
          >{{ item }}</a
        ></span
      ><br /><br /><br />
      <h2>影片评价</h2>
      <div class="evaluation-list" v-loading="listLoading">
        <ul>
          <li v-for="(item, index) in evaluationList" :key="index">
            <evaluation
              :customer="item.customer"
              :evaluationScore="item.evaluationScore"
              :evaluationContent="item.evaluationContent"
              :evaluationTime="item.evaluationTime"
            ></evaluation>
          </li>
        </ul>
      </div>
      <el-pagination
        class="evaluation-reloadmore"
        background
        :current-page="queryForm.pageNo"
        :page-size="queryForm.pageSize"
        :layout="layout"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      ></el-pagination>
    </div>
  </div>
</template>
<script>
import { getInfo } from "@/api/FilmInfo";
import { methods } from "@/components/customerLeftNav/customerleftNav.vue";
import Evaluation from "@/views/movieDetail/Evaluation";
import { getList } from "@/api/EvaluationList";
export default {
  name: "MovieDetail",
  data() {
    return {
      movieInfo: '',
      wantflag: true,
      scoreflag: false,
      actors: [],
      directors: [],
      evaluationList: null,
      listLoading: true,
      layout: "total,  prev, pager, next, jumper",
      total: 0,
      queryForm: {
        pageNo: 1,
        pageSize: 5,
        pageMovieId: this.$route.query.movieId,
      },
      flag:false,
    };
  },
  computed: {
    movieName: function () {
      // this.fetchData();
      return this.$route.query.movieName;
    },
  },
  mounted() {},
  created() {
    this.fetchData();
    this.fetchEvaluation();
    // this.flag=true;
  },
  methods: {
    async fetchData() {
      this.queryForm.pageMovieId = this.$route.query.movieId;
      this.movieInfo = null;
      const result = await getInfo(this.queryForm);
      this.movieInfo = result.data
      // console.log(this.movieInfo);
      // console.log(this.flag)
      if (this.movieInfo.myScore != null) {
        this.scoreflag = true;
      }
      this.actors = this.movieInfo.movie.movieActor.split(",");
      this.directors = this.movieInfo.movie.movieDirector.split(",");
      this.flag=true;

      // setTimeout(()=>{
      //   // this.flag=true;
      //
      // },2000)
    },
    addwant() {
      this.wantflag = !this.wantflag;
    },
    handleSizeChange(val) {
      this.queryForm.pageSize = val;
      this.fetchEvaluation();
    },
    handleCurrentChange(val) {
      this.queryForm.pageNo = val;
      this.fetchEvaluation();
    },
    queryData() {
      this.queryForm.pageNo = 1;
      this.fetchEvaluation();
    },
    async fetchEvaluation() {
      this.listLoading = true;
      this.queryForm.pageMovieId = this.movieId;
      const { data, totalCount } = await getList(this.queryForm);
      setTimeout(() => {
        this.evaluationList = data;
        this.total = totalCount;
        this.listLoading = false;
      }, 3000);
    },
    showMovie(movieId) {
      this.$router.push({
        path: "/movies/movieDetail",
        query: {
          movieId: movieId,
        },
      });
    },
    jumpBuyTicket(movieId) {
      this.$router.push({
        path: "/movies/selectSession",
        query: {
          movieId: movieId,
        },
        params: {
          ticketStep: 1,
        },
      });
    },
  },
  components: {
    Evaluation,
  },
};
</script>

<style>
.movieDetail-top {
  background-image: url("https://misc.360buyimg.com/virtuals/movie/1.0.0/img/d-top-detail.webp");
  width: 100%;
  height: 380px;
}

.movie-info-movieshow {
  margin-left: 30px;
}

.movie-yugao-play {
  display: block;
  width: 232px;
  height: 325px;
  line-height: 350px;
  background-color: black;
  opacity: 0;
  position: absolute;
  /* top: 147px;
  left: 249px; */
  z-index: 2;
  cursor: pointer;
  text-align: center;
}
.movie-yugao-play:hover {
  opacity: 1;
  background: rgba(0, 0, 0,0.4);
}

.movie-info-moviepicture {
  position: relative;
  margin-left: 60px;
  margin-top: 20px;
  float: left;
}

.movie-info-movieshow-right {
  float: left;
  margin-top: 20px;
  margin-left: 30px;
  color: white;
}

.movie-info-smallinfo {
  line-height: 30px;
  list-style: none;
  font-size: 14px;
}

.movieshow-actionbuttom {
  margin-top: 50px;
  width: 260px;
}

.wish {
  float: left;
  font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: subpixel-antialiased;
  text-decoration: none;
  cursor: pointer;
  float: left;
  display: block;
  width: 120px;
  height: 25px;
  background-color: #756189;
  margin-right: 10px;
  padding-top: 11px;
  text-align: center;
  font-size: 14px;
  line-height: 16px;
  color: #fff;
  border-radius: 2px;
}

.wish-icon {
  font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: subpixel-antialiased;
  cursor: pointer;
  text-align: center;
  font-size: 14px;
  line-height: 16px;
  color: #fff;
  display: inline-block;
  vertical-align: middle;
  margin-top: -2px;
  margin-right: 6px;
  width: 16px;
  height: 16px;
  background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyhpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDE0IDc5LjE1MTQ4MSwgMjAxMy8wMy8xMy0xMjowOToxNSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6MDc1OTZBMzUzQzIzMTFFNkI5NzY4NjMyQkI3QTVCMDAiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6MDc1OTZBMzQzQzIzMTFFNkI5NzY4NjMyQkI3QTVCMDAiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKE1hY2ludG9zaCkiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDozOTE3NDFCODM3NjcxMUU2QUY0Q0YzNTlBNkE3Q0U3MiIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDozOTE3NDFCOTM3NjcxMUU2QUY0Q0YzNTlBNkE3Q0U3MiIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/Ptb+IwoAAAC3SURBVHjaYvz//z8DJYAJiZ0CxMeB+BMQvwbi5UCsCcUg9huo3HGoWggAuQCIF//HDn5BMTawGOx6IJH2n3yQxggkrgMdokFmENwEGfADyGAn04C/TNCAIRd8ABmwmQIDNoACUQyIX5ERgCA9YrBoVALiyyRovgzVA08HIMwJxJuJ0LwZqpYB3QAY7sejuR9dPTYDQDgbi+ZsbGpxGQDC7kia3XGpYySQG/Og9CRcChgpzc4AAQYAYXrq/jrniJEAAAAASUVORK5CYII=);
}

.wish-icon.wanted {
  background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyhpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDE0IDc5LjE1MTQ4MSwgMjAxMy8wMy8xMy0xMjowOToxNSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6NTg3NUMzN0EzRjVEMTFFNjkxMDhGRThFNTBFREEyMzAiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6NTg3NUMzNzkzRjVEMTFFNjkxMDhGRThFNTBFREEyMzAiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKE1hY2ludG9zaCkiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDpDNkY0NkVEMTNGNDAxMUU2OUIwODkwOUI1MjBDMTJCMiIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDpDNkY0NkVEMjNGNDAxMUU2OUIwODkwOUI1MjBDMTJCMiIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PhC200UAAAD2SURBVHjaYvz//z8DJYDxS0UBjC0NxFFAbAHETEB8FoiXAvFDIA4AYm8gFgPip0C8CYi3gQ14Z6kPoh2giqXQLHgJxLeB2AaL5fOBOIOFkZNTC8qRwqJIHIqxgUQgfgByaj0QK5AZBIlMUL+RC2RBBvymwIB/IAPWUGDAfZABjUB8iUwDJoEMeAKN/6skam4C4ulMUA5IszMQ7yBC41doFNbDwgA50QQC8Uw8mkGu9QPiBTABJjQFP0CpC4hLgfgPmtxJIHYC4n0oeQGalLGBECCeA8T80JhKAeKP6IpYWC2scRmwBuotUD5pA+K/WHMjpdkZIMAAEvc0vOkwYeYAAAAASUVORK5CYII=);
}

.movieScore-btn {
  font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: subpixel-antialiased;
  text-decoration: none;
  outline-width: 0;
  cursor: pointer;
  float: left;
  display: block;
  width: 120px;
  height: 25px;
  background-color: #756189;
  margin-right: 10px;
  padding-top: 11px;
  text-align: center;
  font-size: 14px;
  line-height: 16px;
  color: #fff;
  border-radius: 2px;
}

.movieScore-btn-icon {
  font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: subpixel-antialiased;
  cursor: pointer;
  text-align: center;
  font-size: 14px;
  line-height: 16px;
  color: #fff;
  display: inline-block;
  vertical-align: middle;
  margin-top: -2px;
  margin-right: 6px;
  width: 16px;
  height: 16px;
  background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyhpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDE0IDc5LjE1MTQ4MSwgMjAxMy8wMy8xMy0xMjowOToxNSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6MDc1OTZBM0QzQzIzMTFFNkI5NzY4NjMyQkI3QTVCMDAiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6MDc1OTZBM0MzQzIzMTFFNkI5NzY4NjMyQkI3QTVCMDAiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKE1hY2ludG9zaCkiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo4QzQ3QTgzOTM3NjgxMUU2QUY0Q0YzNTlBNkE3Q0U3MiIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDo4QzQ3QTgzQTM3NjgxMUU2QUY0Q0YzNTlBNkE3Q0U3MiIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/Pi0cMmgAAADqSURBVHjaYvz//z8DHtAExAJAnIdLASMeA3iB+BOULQfEj7EpYsJjey8SuwunKpALsOCQ/5ggBJtadAEJIG75jxsUATEbsh5QGLgDHeIDxGZArAfEHAz4wTMg3g/EZ4F4N8iAY0CGJQN54CDIGXpAfPE/6eA5EHvB/CIKxG9J0PwRiBXQAzGRBANikQMR5h9mIH4DTXn4wAsglsSWkP4C8TUiAu4ovpTIQoQBv3AZwAPEWkQYoIjLRnaoISCwG4hXAPEZIOaCppNUINYEYnVcBnyEZqDPQDwTGlgwcAKI9wJxFRDfRzYAIMAAYsNIs6fSEWgAAAAASUVORK5CYII=);
}

.movieScore-btn-icon.scored {
  background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyhpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDE0IDc5LjE1MTQ4MSwgMjAxMy8wMy8xMy0xMjowOToxNSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6MDc1OTZBMzkzQzIzMTFFNkI5NzY4NjMyQkI3QTVCMDAiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6MDc1OTZBMzgzQzIzMTFFNkI5NzY4NjMyQkI3QTVCMDAiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKE1hY2ludG9zaCkiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo4QzQ3QTgzRDM3NjgxMUU2QUY0Q0YzNTlBNkE3Q0U3MiIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDo4QzQ3QTgzRTM3NjgxMUU2QUY0Q0YzNTlBNkE3Q0U3MiIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/Pr7Zy/cAAADuSURBVHjaYvx/jAEfaAJiASDOw6WAEY8BvED8CcqWA+LH2BQx4bG9F4ndhUsRLgNCgDgViR8BFSNogAQQtwDxaixqQWJFQMyGHgbuQNoHiM2AWA+IORjwg2dAvB+IzwLxbpABoGC0ZCAPHAR5IQOIL5Gh+QUocGHRKArEN4BYiEjNoOjVB+IHsEB8DcQlJNieA9KMnpCYgfgNNOURcroktmj8C8TXiLD9KL50wEKEAb9wGcADxFpEGKCIy0Z2qCEgsBuIVwDxGSDmgqYTUNLWBGJ1XAZ8hGagz0A8ExpYMHACiPcCcRUQ30c2ACDAABdZMN0CosYSAAAAAElFTkSuQmCC);
}

.movieScore-btn-msg {
  font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: subpixel-antialiased;
  cursor: pointer;
  text-align: center;
  font-size: 14px;
  line-height: 16px;
  color: #fff;
}

.movieScore-btn-msg.movieScore-num {
  color: #ffc605;
}

.btn.buy {
  font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: subpixel-antialiased;
  text-decoration: none;
  display: inline-block;
  color: #fff;
  background-color: #df2d2d;
  margin-top: 10px;
  width: 250px;
  height: 40px;
  font-size: 16px;
  line-height: 40px;
  text-align: center;
  border-radius: 2px;
  padding: 0;
  cursor: pointer;
}

.movie-movieScore-all {
  width: 300px;
  position: relative;
  bottom: 100px;
  left: 650px;
}

.movieScore-all-part-scorenum {
  font-size: 30px;
  background-color: #fed603;
  display: block;
  width: 65px;
  height: 45px;
  line-height: 45px;
  text-align: center;
  font-weight: bolder;
  float: left;
  border-radius: 0 6px 6px 0;
}

/* 星星大小 */
.movie-movieScore-all-part .el-icon-star-on:before {
  font-size: 35px;
}

.movieDetail-middle {
  margin-top: 30px;
  width: 720px;
  margin-left: 50px;
  float: left;
}

.movieDetail-middle a {
  color: black;
  margin-right: 20px;
}

.movieDetail h3 {
  font-weight: normal;
  font-size: 18px;
}

.movieDetail h2 {
  font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: subpixel-antialiased;
  display: inline-block;
  margin: 0;
  padding: 0;
  font-weight: 400;
  font-size: 18px;
  color: #333;
  line-height: 65px;
}

.movieDetail h2:before {
  content: "";
  display: inline-block;
  width: 4px;
  height: 22px;
  margin-right: 6px;
  background-color: #ef4238;
  vertical-align: middle;
}

.movieDetail-middle span {
  font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: subpixel-antialiased;
  color: #333;
  font-size: 14px;
  line-height: 26px;
}

.evaluation-list {
  width: 730px;
}
.evaluation-list ul {
  list-style: none;
}
.evaluation-list ul li {
  margin-top: 30px;
}

.evaluation-reloadmore {
  text-align: center;
  margin-top: 25px;
}

.evaluation-reloadmore .el-pager li:not(.disabled).active {
  background-color: #f80f01 !important;
}

.evaluation-list .el-loading-mask .el-loading-spinner .path {
  stroke: red;
}

.movieDetail-middle-right {
  width: 400px;
  float: right;
  margin-top: 30px;
  margin-right: 50px;
}

.relativeMovies dd {
  text-align: center;
  color: black;
  line-height: 28px;
}
.relativeMovies dd a {
  color: black;
  line-height: 26px;
}
</style>