<template>
    <div class="agent-contain">
        <div style=" overflow: hidden;">
            <div style="margin: 20px 0 0 30px;font-weight: bold;font-size: 16px">客服服务</div>
            <el-row>
                <el-col :span="18" :offset="3">
                    <div
                            id="dataShow"
                            onmouseover="this.style.overflow='overlay'"
                            onmouseout="this.style.overflow='hidden'"
                            class="dataShow"
                    >
                        <div
                                v-for="(item,index) in historyList"
                                :key="item.infoId"
                                style=" padding:0 20px;"
                        >
                            <el-row v-if="item.infoType==='customer'">
                                <el-col :span="20" :offset="2">
                          <span class="aa">
                            {{ item.infoContent }}
                          </span>
                                </el-col>
                                <el-col :span="1">

                          <el-image
                                  style="
                width: 60px;
                height: 60px;
                vertical-align: middle;
                display: inline-block;
                margin-right: 10px;
              "
                                  :src="'http://39.97.217.243:8089/images/'+avatar"
                                  :fit="'contain'"
                          ></el-image>

                                </el-col>
                            </el-row>
                            <br>
                            <el-row v-if="item.infoType!=='customer'">
                                <el-col :span="1">
                          <span>
                              <el-image
                                      style="
                width: 50px;
                height: 50px;
                vertical-align: middle;
                display: inline-block;
              "
                                      :src="'http://39.97.217.243:8089/images/20201021101424%E5%9B%BE%E7%89%87.jpeg'"
                                      :fit="'contain'"
                              ></el-image>
                          </span>
                                </el-col>
                                <el-col :span="20">
                          <span class="bb">
                            {{ item.infoContent }}
                          </span>
                                </el-col>
                            </el-row>
                        </div>
                        <el-row style="margin-bottom: 5px">
                            <el-col :span="10" :offset="10">
                      <span style="font-size: 16px;color: #f5f5f5;background-color: #dadada;margin-bottom: 10px">
                        以上为历史记录
                      </span><br>
                            </el-col>
                        </el-row>
                        <div
                                v-for="(item,index) in nowList"
                                :key="item.infoId"
                                style=" padding:0 20px;"
                        >
                            <el-row v-if="item.infoType==='customer'">
                                <el-col :span="20" :offset="2">
                          <span class="aa">
                            {{ item.infoContent }}
                          </span>
                                </el-col>
                                <el-col :span="1">

                                    <el-image
                                            style="
                width: 60px;
                height: 60px;
                vertical-align: middle;
                display: inline-block;
                margin-right: 10px;
              "
                                            :src="'http://39.97.217.243:8089/images/'+avatar"
                                            :fit="'contain'"
                                    ></el-image>

                                </el-col>
                            </el-row>
                            <br>
                            <el-row v-if="item.infoType!=='customer'">
                                <el-col :span="1">
                          <span>
                              <el-image
                                      style="
                width: 50px;
                height: 50px;
                vertical-align: middle;
                display: inline-block;
              "
                                      :src="'http://39.97.217.243:8089/images/20201021101424%E5%9B%BE%E7%89%87.jpeg'"
                                      :fit="'contain'"
                              ></el-image>
                          </span>
                                </el-col>
                                <el-col :span="20">
                          <span class="bb">
                            {{ item.infoContent }}
                          </span>
                                </el-col>
                            </el-row>
                        </div>
                    </div>
                    <el-row style="width: 800px;margin: 0 auto;">
                        <el-col :span="22" :offset="1" style="width: 800px;margin: 0 auto;">
                            <el-input
                                    id="condition"
                                    v-model="condition"
                                    type="textarea"
                                    autofocus
                                    clearable
                                    maxlength="100"
                                    show-word-limit
                                    :autosize="{minRows:3,maxRows:6}"
                                    resize="none"
                                    placeholder="在这里输入..."
                                    @keyup.enter.native="sendMessage"
                            />
                        </el-col>
                    </el-row>
                    <el-row style="width: 800px;margin: 20px auto;">
                        <el-button
                                style="float:right;"
                                id="submit"
                                onmouseover="this.style.backgroundColor='#129611';this.style.color='#f5f5f5'"
                                onmouseout="this.style.backgroundColor='#f5f5f5';this.style.color='#606060'"
                                @click="sendMessage"
                        >
                            发送
                        </el-button>
                    </el-row>
                </el-col>
            </el-row>
        </div>
    </div>
</template>

<script>
    import {sendSock, initWebSocket} from "@/api/websocket.js";
    import {mapGetters} from "vuex";


    export default {
        name: "index",
        data() {
            return {
                condition: '',
                historyList: [

                ],
                nowList:[],
                visible: false,

            }
        },
        created() {
            this.fetchData();
        },
        computed: {
            ...mapGetters({
                username: "user/username",
                avatar: "user/avatar",
                accessToken: "user/accessToken",
            }),
        },
        methods: {
            // 发送消息后滚动到底部
            scrollToBottom() {
                this.$nextTick(() => {
                    const div = document.getElementById('dataShow')
                    div.scrollTop = div.scrollHeight
                })
            },
            // 去掉回车换行符
            clearBr(key) {
                key = key.replace(/<\/?.+?>/g, '')
                key = key.replace(/[\r\n]/g, '')
                return key
            },
            async fetchData() {
                console.log(this.history)
                await initWebSocket(this.accessToken, this.history,this.result)
            },
            history(data){
                console.log("历史记录")
                this.historyList = data;
            },
            result(data) {
                console.log("返回的消息")
                if (data){
                    let mid2={
                        infoId:data.infoId,
                        infoContent:data.infoContent,
                        infoTime:data.infoTime,
                        infoStatus:data.infoStatus,
                        infoType:data.infoType,
                        customerId:data.customerId,
                    }
                    this.nowList.push(mid2)
                }else {
                    this.$message.error('发送失败')
                }

            },
            sendMessage() {

                this.condition = this.clearBr(this.condition)
                console.log('对话内容' + this.condition)
                if (this.condition === '' || this.condition === undefined || this.condition === null) {
                    this.$message.error('当前没有输入。。。')

                }else {
                    var data = {};
               data['infoType'] = "customer";
               data['infoContent'] = this.condition;
               sendSock(data);
               this.condition=''
                }
                this.scrollToBottom()

            },
        },
    }
</script>

<style>

    .font {
        font-size: 16px;
        font-weight: bold;
        font-family: "微软雅黑 Light";
    }

    .dataShow {
        width: 800px;
        overflow-y: hidden;
        overflow-x: hidden;
        /*margin-bottom: -24px;*/
        /*margin-top: -24px;*/
        height: 500px;
        margin: 20px auto;
        /*margin-bottom: 5%;*/
        /*margin-top: 5%;*/
        background-color: #f5f5f5;
    }

    .aa {
        float: right;
        position: relative;
        display: inline-block;
        max-width: calc(40%);
        min-height: 35px;
        line-height: 2.1;
        font-size: 15px;
        padding: 6px 10px;
        text-align: left;
        word-break: break-all;
        background-color: #9eea6a;
        color: #000;
        border-radius: 4px;
        box-shadow: 0px 1px 7px -5px #000;
        border: 0px solid #000;
        /*margin-top: 0;*/
        /*margin-right: 10px;*/
        right: 20px;
        top: 10px;
    }

    .aa:after {
        content: "";
        border-top: solid 5px #00800000;
        border-left: solid 10px #9eea6a;
        border-right: solid 10px #00800000;
        border-bottom: solid 5px #00800000;
        position: absolute;
        top: 10px;
        left: 100%;
    }

    .bb {
        display: inline-block;
        line-height: 30px;
        font-style: normal;
        background-color: white;
        letter-spacing: 2px;
        position: relative;
        max-width: calc(40%);
        min-height: 35px;
        font-size: 15px;
        padding: 6px 10px;
        text-align: left;
        word-break: break-all;
        border-radius: 4px;
        color: #000;
        box-shadow: 0px 1px 7px -5px #000;
        border: 0px solid #000;
        margin-top: 0;
        /*margin-left: 10px;*/
        left: 35px;
        top: 10px;
    }

    .bb:after {
        content: "";
        border-top: solid 5px #00800000;
        border-left: solid 10px #00800000;
        border-right: solid 10px white;
        border-bottom: solid 5px #00800000;
        position: absolute;
        top: 10px;
        right: 100%;

    }
</style>