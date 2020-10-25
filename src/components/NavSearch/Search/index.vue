<template>
  <div class="Search-container">
    <div class="logosearch">
      <div class="logosearchmain">
        <div class="Search-logo">
          <router-link to="/index">
            <el-image
              style="width: 180px; height: 100px"
              :src="require('@/assets/logoko.png')"
              :fit="'contain'"
            ></el-image>
          </router-link>
        </div>
        <div class="search">
          <div class="searchmain">
            <el-input
              id="search-input"
              v-model="inputMovie"
              placeholder="请输入关键词	"
              prefix-icon="el-icon-search"
              clearable
              style="
                width: 475px;
                float: left;
                margin-bottom: 10px;
                border-radius: 10px 10px;
              "
            ></el-input>
            <el-button
              style="
                position: relative;
                background-color: #e65252;
                margin-left: -14px;
                z-index: 999;
                height: 38px;
                border-radius: 8px 10px 10px 8px;
                border-color: #E65253;
              "
              type="primary"
              icon="el-icon-search"
              @click="selectMovie"

            ></el-button>
          </div>
        </div>
        <div class="connect">
          <div class="connectmain">
            <span class="el-icon-phone-outline"></span>
            400-889-8188
            <router-link to="/agent/index">
              <span class="el-icon-help"></span>
              在线客服
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>


  export default {
    name: "Search",
    data() {
      return {
        // nodeEnv: process.env.NODE_ENV,
        inputMovie: "",
      };
    },
    computed: {},

    created() {},
    mounted() {},
    methods: {
      selectMovie() {
        this.$router.push({
          path: "/movies/movieDetail",
          query: {
            movieName: this.inputMovie,
          },
        });
        this.inputMovie = "";
      },
    },
  };
</script>
<style>
  .logosearch {
    width: 1350px;
    height: 95px;
    border-bottom: 3px #eeeeee solid;
    position: relative;
    overflow: hidden;
    margin: 0 auto;
  }

  .logosearch .logosearchmain {
    width: 1300px;
    margin: 0 auto;
    height: 80px;
  }

  .logosearch .logosearchmain .Search-logo {
    width: 250px;

    margin-left: 20px;
    float: left;
  }

  .logosearch .logosearchmain .search {
    width: 570px;
    float: left;
    height: 80px;
    margin-left: 40px;
  }

  .logosearch .logosearchmain .search .searchmain {
    margin: 30px 0 10px 0;
    height: 80px;
  }

  .logosearch .logosearchmain .connect {
    width: 250px;
    float: right;
    height: 40px;
    margin: 23px 15px 0 0;
  }

  .logosearch .logosearchmain .connect .connectmain {
  }

  .logosearch .logosearchmain .connect .connectmain a {
    text-decoration: none;
    display: block;
    color: #6c6c98;
    margin-top: 10px;
  }
  #search-input {
    height: 38px;
    line-height: 38px;
    border-radius: 10px;
  }
  .el-icon-search:before{
    /*position: absolute;*/
    /*top: 10px*/
  }
</style>
