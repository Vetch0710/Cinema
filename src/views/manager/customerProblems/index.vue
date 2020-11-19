<template>
    <div class="problems-contain">
        <div class="problems-list">
            <div style="height: 40px;font-weight: bold;font-size: 16px;line-height: 40px;padding-left: 20px;border-bottom:1px solid #E5E5E5 ">客服服务</div>
            <ul>
                <li v-for="(item , index ) in problemList" :key="item.infoId" @click="activeSession(item,index)">
                    <el-card shadow="hover"
                     style="width: 330px;height: 90px;text-align: center;float: left;padding: 0;background: #fcfcfc;"

                      slot="reference">
                        <div style="width: 100%;height: 25px;">
                            <span style="display: inline-block;float:left;font-size: 18px">{{item.customerName}}</span>
                            <span style="display: inline-block;float:right;font-size: 14px"> {{item.infoTime}}</span>
                        </div>
                        <div style="width: 100%;height: 25px;">
                            <span style="display: inline-block;float:left;font-size: 14px;margin-top: 10px;margin-left: 20px;">{{item.infoContent}}</span>
                            <span class="problems-count" v-if="item.messageCount!==0"> {{item.messageCount}}</span>
                        </div>
                    </el-card>

                </li>
            </ul>
        </div>
        <div class="problems-main">
            <div v-if="!flag" class="problems-first">
                请选择一个会话
            </div>
            <div v-else style=" overflow: hidden;">
                <el-row>
                    <el-col>
                        <div
                                id="problems-chat"
                                onmouseover="this.style.overflow='overlay'"
                                onmouseout="this.style.overflow='hidden'"
                                class="problems-chat"
                        >
                            <div
                                    v-for="(item) in historyList"
                                    :key="item.infoId"
                                    style=" padding:0 20px;"
                            >
                                <el-row v-if="item.infoType!=='customer'">
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
                                                :src="'http://8.131.77.164:8089/CinemaData/img/manager.jpeg'"
                                                :fit="'contain'"
                                        ></el-image>

                                    </el-col>
                                </el-row>
                                <br>
                                <el-row v-if="item.infoType==='customer'">
                                    <el-col :span="1">
                          <span>
                              <el-image
                                      style="
                width: 50px;
                height: 50px;
                vertical-align: middle;
                display: inline-block;
              "
                                      :src="'http://8.131.77.164:8089/CinemaData/img/'+avatar"

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
                                    v-for="(item) in nowList"
                                    :key="item.infoId"
                                    style=" padding:0 20px;"
                            >
                                <el-row v-if="item.infoType!=='customer'">
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
                                                :src="'http://8.131.77.164:8089/CinemaData/img/manager.jpeg'"
                                                :fit="'contain'"
                                        ></el-image>

                                    </el-col>
                                </el-row>
                                <br>
                                <el-row v-if="item.infoType==='customer'">
                                    <el-col :span="1">
                          <span>
                              <el-image
                                      style="
                width: 50px;
                height: 50px;
                vertical-align: middle;
                display: inline-block;
              "
                                      :src="'http://8.131.77.164:8089/CinemaData/img/'+avatar"
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
                        <div style="width: 800px;position: relative;height: 100px;" class="problems-chat-input">

                            <el-input
                                    style="height: 120px;"
                                    v-model="condition"
                                    type="textarea"
                                    autofocus
                                    clearable
                                    maxlength="100"
                                    :autosize="{minRows:5,maxRows:6}"
                                    resize="none"
                                    placeholder="在这里输入..."
                                    @keyup.enter.native="sendMessage"
                            />
                            <el-button
                                    style="bottom: 10px;right: 10px;position: absolute"
                                    onmouseover="this.style.backgroundColor='#129611';this.style.color='#f5f5f5'"
                                    onmouseout="this.style.backgroundColor='#f5f5f5';this.style.color='#606060'"
                                    @click="sendMessage"
                            >
                                发送
                            </el-button>
                        </div>


                    </el-col>
                </el-row>
            </div>
        </div>

    </div>
</template>

<script>
    import {sendSock, initWebSocket, gethistoryList, changeStatus} from "@/api/agent.js";
    import {mapGetters} from "vuex";


    export default {
        name: "index",
        data() {
            return {
                problemList: [
                    // {
                    //     infoId: '1',
                    //     infoContent: '这是吗啊',
                    //     infoTime: '2020-10-26 12:47:35',
                    //     infoStatus: '未读',
                    //     infoType: 'customer',
                    //     customerId: '754406394',
                    //     customerName: 'eee',
                    // },

                ],
                historyList: [],
                nowList: [],
                flag: false,
                condition: '',
                avatar:'',
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
                    const div = document.getElementById('problems-chat')
                    div.scrollTop = div.scrollHeight
                })
            },
            async fetchData() {
                console.log(this.history)
                await initWebSocket(this.accessToken, this.problemLists,this.results)
            },
            async activeSession(item, index) {
                this.flag = item;
                let data = await gethistoryList({customerId: item.customerId});
                console.log(data.history)
                console.log(data.avatar)
                this.historyList = data.history;
                this.avatar = data.avatar;
                this.problemList[index].messageCount = 0;
                this.nowList = [];
                console.log(this.flag)
            },
            problemLists(data) {
                console.log("消息列表")
                this.problemList = data;
            },
            historyLists(data) {
                console.log("消息列表")
                this.historyList = data;
            },
         async   results(data) {
                console.log("返回的消息")
                if (data) {
                    let mid2 = {
                        infoId: data.infoId,
                        infoContent: data.infoContent,
                        infoTime: data.infoTime,
                        infoStatus: data.infoStatus,
                        infoType: data.infoType,
                        customerId: data.customerId,
                        messageCount:0,
                        customerName:data.customerName
                    }
                    if (this.flag.customerId === mid2.customerId) {
                        this.nowList.push(mid2)
                        await changeStatus({infoId: mid2.infoId});

                    }
                    let isExist = false;
                    for (let item of this.problemList) {
                        if (item.customerId === mid2.customerId) {
                            item.infoContent = mid2.infoContent;
                            item.infoTime = mid2.infoTime;
                            if (this.flag.customerId !== mid2.customerId) {
                                item.messageCount++;
                            }
                            isExist = true;
                            break;
                        }
                    }
                    console.log(this.problemList)
                    if (!isExist) {
                        mid2.messageCount=1;
                        this.problemList.push(mid2);
                    }

                } else {
                    this.$message.error('发送失败')
                }

            },
            // 去掉回车换行符
            clearBr(key) {
                key = key.replace(/<\/?.+?>/g, '')
                key = key.replace(/[\r\n]/g, '')
                return key
            },
            sendMessage() {

                this.condition = this.clearBr(this.condition)
                console.log('对话内容' + this.condition)
                if (this.condition === '' || this.condition === undefined || this.condition === null) {
                    this.$message.error('当前没有输入。。。')

                } else {
                    var data = {};
                    data['infoType'] = "manager";
                    data['infoContent'] = this.condition;
                    data['customerId'] = this.flag.customerId;
                    sendSock(data, this.results);
                    this.condition = ''
                }
                this.scrollToBottom()

            },
        },
        beforeRouteLeave(to, from, next) {
            this.flag = false;
            this.problemList=[];
            next();
        }

    }
</script>

<style scoped>
    .problems-contain {
        width: 1200px !important;
        height: 600px;
        /*border: 1px solid #E5E5E5;*/
        /*width: 1101px;*/
        /*margin: 0 auto;*/
    }

    .problems-list {
        width: 330px;
        float: left;
        height: 100%;
        /*margin:0 20px;*/
        border: 1px solid #E5E5E5;
        background: #fafafa;

    }

    .problems-list li {
        list-style: none;
        /*height: 70px;*/
        /*padding: 10px 20px;*/
        /*border-top: 1px solid #E5E5E5;*/
    }

    .problems-list li:last-of-type {
        /*border-bottom: 1px solid #E5E5E5;*/

    }

    .problems-main {
        width: 800px;
        float: left;
        height: 100%;
    }

    .font {
        font-size: 16px;
        font-weight: bold;
        font-family: "微软雅黑 Light";
    }

    .problems-chat {
        width: 800px;
        overflow-y: hidden;
        overflow-x: hidden;

        height: 500px;

        background-color: #f5f5f5;
    }

    .el-textarea .el-input__count {
        color: #909399;
        background: #FFF;
        position: absolute;
        font-size: 12px;
        bottom: 50px !important;
        right: 10px;
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
    .problems-first{
        width: 150px;
        font-size: 16px;
        font-weight: bold;
        margin: 0 auto;
        line-height: 500px;
        text-align: center;
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

    .problems-count {
        display: inline-block;
        float: right;
        width: 20px;
        height: 20px;
        background: rgb(230, 82, 82);
        border-radius: 10px 10px;
        text-align: center;
        line-height: 20px;
        color: #fffdef;
        font-weight: bolder;
        font-size: 12px
    }
</style>