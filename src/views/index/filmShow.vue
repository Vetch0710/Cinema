<template>
  <div>
    <a>
      <div
        class="image"
        :style="{ background: 'url(' + imgurl + ')'}"
      >
      <div class="curtain"></div>
        <div class="score">
          {{score}}
        </div>
      </div>
      <div class="movie-info">
        <h4 class="movie-title font-bold">{{ filmname }}</h4>
        <p class="movie-price">
          <span class="movie-iniprice font-reaular">
            ￥{{ price }}
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
      return {
        p:this.score
      };
    },
    props: {
      score: { type: Number, required: true },
      imgurl: { type: String, required: true },
      price: { type: Number, required: true },
      filmname: { type: String, required: true },
    },
    methods: {},
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
  }

  .image {
    width: 100%;
    height: 320px;
    position: relative;
    background-size:cover !important;
    background-repeat: no-repeat;
  }

  .score {
    position: absolute;
    bottom: 0;
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

  .movie-info {
    margin-top: 15px;
    margin-bottom: 40px;
    text-align: center;
  }

  .movie-title {
    width: 230px;
    line-height: 28px;
    padding: 0;
    margin-top: 15px;
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

  .movie-price {
    margin-top: -18px;
    font-family: JDZhengHei-01-Regular;
  }

  .movie-iniprice {
    display: inline-block;
    position: relative;
    font-size: 20px;
    font-weight: bolder;
    color: #fff;
    line-height: 24px;
    color: #ff5228;
    vertical-align: middle;
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

  .curtain{
    position: absolute;
    width: 100%;
    height: 100%;
    background-color: white;
    opacity:0;
    z-index: 2;
  }

  .curtain:hover{
    opacity: 0.5;
  }
</style>
