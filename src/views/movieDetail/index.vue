<template>
  <div class="movieDetail-container">
    <el-divider content-position="left">
      movieDetail---{{ movieName }}
    </el-divider>
  </div>
</template>
<script>
  export default {
    name: "MovieDetail",
    data() {
      return {
        movieName: "",
      };
    },
    created() {
      this.movieName = this.$route.query.movieName;
    },
    mounted() {},
    methods: {},
  };
</script>
