<template>
    <div class="UserCenter-container">
        <div class="UserCenter-info-box">
            <div class="UserCenter-info">
                <div class="UserCenter-info-img">
                    <el-image :src="'http://8.131.77.164:8089/images/'+avatar" class="UserCenter-avatar"
                              :fit="'cover'"></el-image>
                </div>
                <div class="UserCenter-info-nickName">
                    <p style="font-size: 26px;margin-bottom: 5px;color: #222222;">{{username}}</p>
                    <p style="font-size: 13px;font-weight: 400;margin-left: 2px;">普通用户</p>
                </div>
                <div class="UserCenter-info-index">
                    <router-link to="/customerInfo">个人信息设置></router-link>
                </div>
            </div>
            <div class="UserCenter-fun">
                <ul class="UserCenter-fun-ul clearfix">
                    <li>
                        <el-card shadow="hover"
                                 style="width: 140px;height: 70px;text-align: center;float: left;margin: 0 15px 0 15px ;background-color: rgba(0, 0, 0, 0);padding: 0;"

                                 slot="reference">
                            <router-link to="/customerOrder/totalOrder">
                                <i class="UserCenter-icon iconfont icon-dingdan"></i>
                                <span class="UserCenter-icon-title">全部订单</span>
                            </router-link>
                        </el-card>

                    </li>
                    <li>
                        <el-card shadow="hover"
                                 style="width: 140px;height: 70px;text-align: center;float: left;margin: 0 15px 0 15px ;background-color: rgba(0, 0, 0, 0);padding: 0;"

                                 slot="reference">
                            <router-link to="/customerOrder/finish">
                                <i class="UserCenter-icon iconfont icon-yiwancheng"></i>
                                <span class="UserCenter-icon-title">已完成</span>
                            </router-link>
                        </el-card>

                    </li>
                    <li>
                        <el-card shadow="hover"
                                 style="width: 140px;height: 70px;text-align: center;float: left;margin: 0 15px 0 15px ;background-color: rgba(0, 0, 0, 0);padding: 0;"

                                 slot="reference">
                            <router-link to="/customerOrder/waitingPay">
                                <i class="UserCenter-icon iconfont icon-daifukuan"></i>
                                <span class="UserCenter-icon-title">待付款</span>
                            </router-link>
                        </el-card>

                    </li>
                    <li>
                        <el-card shadow="hover"
                                 style="width: 140px;height: 70px;text-align: center;float: left;margin: 0 15px 0 15px ;background-color: rgba(0, 0, 0, 0);padding: 0;"

                                 slot="reference">
                            <router-link to="/customerOrder/waitingEvaluation">
                                <i class="UserCenter-icon iconfont icon-daipingjia"></i>
                                <span class="UserCenter-icon-title">待评价</span>
                            </router-link>
                        </el-card>

                    </li>
                    <li>
                        <el-card shadow="hover"
                                 style="width: 140px;height: 70px;text-align: center;float: left;margin: 0 15px 0 15px ;background-color: rgba(0, 0, 0, 0);padding: 0;"

                                 slot="reference">
                            <router-link to="/agent/index">
                                <i class="UserCenter-icon iconfont icon-lianxikefu"></i>
                                <span class="UserCenter-icon-title">联系客服</span>
                            </router-link>
                        </el-card>

                    </li>
                </ul>
            </div>
        </div>
        <div class="UserCenter-recommend-list">
            <div class="UserCenter-recommend-list-header">
                推荐电影
            </div>
            <div>
                <div class="UserCenter-recommend-list-relativeMovies">
                    <dl>
                        <dd
                                v-for="(item, index) in recommendMovie"
                                :key="index"
                                style="width: 170px;float: left;margin-left: 20px;margin-bottom: 22px;">
                            <a @click="showMovie(item.movieId)">
                                <el-image
                                        style="width: 170px; height: 230px; cursor: pointer"
                                        :src="item.moviePicture"
                                        fit="cover"
                                >
                                    <div slot="error" class="image-slot">
                                        <i class="el-icon-picture-outline"></i>
                                    </div>
                                </el-image>
                                <p style="cursor: pointer;overflow: hidden; text-overflow: ellipsis;white-space: nowrap;">
                                    {{ item.movieName }}</p>
                            </a>
                            <p style="color: #ffc861" v-if="item.movieScore > 0">
                                {{ item.movieScore }}
                            </p>
                            <p style="color: #ffc861" v-else>暂无评分</p>
                        </dd>
                    </dl>
                </div>

            </div>
        </div>
    </div>

</template>

<script>
    import filmShow from "@/views/index/filmShow";
    import {mapGetters} from "vuex";
    import {getRecommendMovie} from "@/api/FilmInfo";

    export default {
        name: "UserCenter",
        components: {filmShow},
        data() {
            return {
                url: "http://39.97.217.243:8089/images/20201021101424%E5%9B%BE%E7%89%87.jpeg",
                movieInfo: '',
                recommendMovie: [],
            };
        },
        computed: {
            ...mapGetters({
                username: "user/username",
                avatar: "user/avatar",
            }),
        },
        created() {
            this.fetchData();
        },
        mounted() {
        },
        methods: {
            showMovie(movieId) {
                this.$router.push({
                    path: "/movies/movieDetail",
                    query: {
                        movieId: movieId,
                    },
                });
            },
            async fetchData() {
                this.movieInfo = null;
                const result = await getRecommendMovie();
                this.recommendMovie = result;
            },
        },
    };
</script>
<style>
    .UserCenter-container {
        margin-top: 20px;
        margin-left: 20px;
        width: 980px;
        /*height: 800px;*/
        float: left;
        /*border: 1px blue solid;*/
    }

    .UserCenter-info-box {
        background: #FFFFFF;
        border: 1px solid #E5E5E5;
        box-shadow: 0 5px 14px 0 rgba(0, 0, 0, 0.10);
        border-radius: 4px
    }

    .UserCenter-info {
        width: 100%;
        height: 209px;
        background: rgba(238,87,87,.9);
        /*background-image: linear-gradient(to bottom right, rgba(230, 110, 141, 0.5), rgba(230, 90, 141, 0.5));*/
        border-radius: 4px 4px 0 0;
    }

    .UserCenter-info-img {
        /*width: 110px;*/
        /*height: 110px;*/
        border: 10px solid rgba(248, 248, 248, 0.5);
        border-radius: 100%;
        position: relative;
        float: left;
        top: 38px;
        left: 30px;
        display: flex;
        align-content: center;
        align-items: center;
        justify-content: center;
        justify-items: center;
        height: 110px;
        padding: 0;
    }

    .UserCenter-avatar {
        width: 110px;
        height: 110px;
        cursor: pointer;
        border-radius: 50%;
    }

    .UserCenter-info-nickName {
        float: left;
        color: #222222;
        position: relative;
        left: 51px;
        top: 70px;
        font-weight: 500;
    }

    .UserCenter-info-index {
        color: #222222;
        float: right;
        font-size: 14px;
        position: relative;
        left: -30px;
        top: 90px;
    }

    .UserCenter-info-index a {
        color: #222222;
        height: 40px;
        line-height: 40px;

    }

    .UserCenter-fun {
        width: 100%;
        height: 110px;
        background: #fff;
        border-bottom-right-radius: 2px;
        border-bottom-left-radius: 2px;
    }

    .UserCenter-fun-ul {
        position: relative;
        top: 15px;
        width: 850px;
        margin-left: 60px;
    }

    .clearfix:after {
        content: " ";
        visibility: hidden;
        display: block;
        height: 0;
        clear: both;
    }

    .UserCenter-fun-ul li {
        float: left;
        /*width: 170px;*/
        position: relative;
        list-style: none;
    }

    /*.UserCenter-fun-ul li a {*/
    /*    height: 40px;*/
    /*    display: inline-block;*/
    /*    line-height: 40px;*/
    /*}*/

    .UserCenter-recommend-list {
        float: left;
        width: 100%;
        /*height: 450px;*/
        background: #fff;
        margin-top: 20px;
        /*padding: 20px 10px;*/
        border-radius: 4px;
        border: 1px solid #e5e5e5;
    }

    .UserCenter-recommend-list-header {
        margin: 15px 0 15px 40px;
        font-weight: bolder;
        font-size: 16px;
    }


    .UserCenter-recommend-list-relativeMovies dd {
        text-align: center;
        color: black;
        line-height: 28px;
    }

    .UserCenter-recommend-list-relativeMovies dd a {
        color: black;
        line-height: 26px;
    }

    .UserCenter-icon {
        font-size: 30px !important;
        /*margin-top: 10px;*/
        vertical-align: middle;
    }

    .UserCenter-icon-title {
        display: inline-block;
        /*height: 40px;*/
        /*line-height: 40px;*/
        margin-left: 10px;
    }

    .UserCenter-container .el-card__body {
        padding: 20px 0 0 0;
    }

    .UserCenter-container .el-card {
        border: none;
    }
</style>