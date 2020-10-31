<template>
  <div class="filmShow" v-if="movieLoaded">
    <a @click="selectMovie">
      <div class="image" :style="{ background: 'url(' + moviePicture + ')' }">
        <div class="curtain"></div>
        <div class="movieScore">
          <el-rate
            v-if="movieScore > 0"
            class="filmshow-movie-movieScore"
            v-model="movieScore"
            disabled
            show-score
            text-color="#ff9900"
          >
          </el-rate>
          <span style="font-weight: 500" v-else>暂无评分</span>
        </div>
      </div>
      <div class="movie-info">
        <h4 class="movie-title font-bold" :title="movieName">
          {{ movieName }}
        </h4>
        <p class="movie-arrangementPrice">
          <span class="movie-iniprice font-reaular" v-if="lowestPrice">
            ￥<span class="num">{{ lowestPrice }}</span>起
          </span>
          <span class="movie-iniprice font-reaular" v-else>
            <span class="num">{{ wantsNum }}</span>人想看
          </span>
          <span class="movie-tag font-bold">
            <label></label>
          </span>
        </p>
      </div>
    </a>
  </div>
</template>

<script>
export default {
  name: "filmShow",
  data() {
    return {};
  },
  props: {
    movieLoaded: { type: Boolean, required: true, default: false },
    movieId: { type: Number, required: true, default: 1 },
    movieScore: { type: Number, required: true },
    moviePicture: { type: String, required: true },
    lowestPrice: { type: Number },
    wantsNum: { type: Number },
    movieName: { type: String, required: true },
  },
  methods: {
    selectMovie() {
      this.$router.push({
        path: "/movies/movieDetail",
        query: { movieId: this.movieId },
      });
    },
  },
  components: {},
};
</script>

<style>
a {
  outline: 0 none;
  text-decoration: none;
  color: #666;
  margin: 0;
  padding: 0;
  cursor: pointer;
}

.image {
  width: 100%;
  height: 320px;
  position: relative;
  background-size: cover !important;
  background-repeat: no-repeat;
}

.movieScore {
  position: absolute;
  bottom: 0;
  right: 0;
  display: inline-block;
  width: 100%;
  height: 32px;
  line-height: 32px;
  background: rgba(0, 0, 0, 0.65);
  font-weight: 700;
  color: #fff;
  font-size: 16px;
  text-align: center;
  z-index: 1;
}

.filmshow-movie-movieScore {
  margin-top: 5px;
}

.filmShow .movie-info {
  margin-top: 15px;
  margin-bottom: 40px;
  text-align: center;
}

.movie-title {
  width: 230px;
  line-height: 28px;
  padding: 0;
  margin-top: 15px;
  margin-left: 8px;
  font-size: 18px;
  font-weight: 700;
  color: #252525;
  white-space: nowrap;
  -o-text-overflow: ellipsis;
  text-overflow: ellipsis;
  overflow: hidden;
}

.font-bold {
  font-family: JDZHENGHEI-01-BOLD;
}

.movie-arrangementPrice {
  margin-top: -18px;
  font-family: JDZhengHei-01-Regular;
}

.movie-iniprice {
  display: inline-block;
  position: relative;
  font-size: 16px;
  font-weight: bolder;
  margin-top: 18px;
  color: #fff;
  line-height: 24px;
  color: #ff5228;
  vertical-align: middle;
}

.movie-iniprice .num{
  font-size: 24px;
}

.movie-tag {
  display: inline-block;
  height: 24px;
  font-size: 14px;
  font-weight: 700;
  vertical-align: middle;
  color: #fff;
  text-align: center;
}

.curtain {
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: white;
  opacity: 0;
  z-index: 2;
}

.curtain:hover {
  opacity: 0.5;
}
</style>
