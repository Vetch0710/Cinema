<template>
    <div>
        <p>这是客服界面</p>
        <input type="text" v-model="inaaa"></input>
        <p>{{res}}-----{{time}}</p>
        <el-button @click="send"></el-button>

    </div>
</template>

<script>
    import {sendSock, initWebSocket} from "@/api/websocket.js";
    import {mapGetters} from "vuex";


    export default {
        name: "index",
        data() {
            return {
                res: '空',
                inaaa:'',
                time:''
            }
        },
        created() {
            this.fetchData();
        },
        computed: {
            ...mapGetters({
                accessToken: "user/accessToken",
            }),
        },
        methods: {
            async fetchData() {
                console.log(this.accessToken,this.result)
                // await sendSock({type: "Hello"},this.result);
                await initWebSocket(this.accessToken,this.result)
            },
            result(data) {
                console.log("返回的数据是" + data)
                this.res = data.messageText;
                this.time = data.messageDate;
            },
            send() {
                var data = {};
                // 发送者ID
                // data['fromId'] = '754406394';
                // 发送者Name
                // data['fromName'] = 'Vetch';
                // 接收者ID - userSocketSessionMap - key
                // 注意 - NZ1 代表是第一个连接人的key - 测试
                // 消息发送的也是给第一个连接者
                data['toId'] = '12345';
                // 发送的消息
                data['messageText'] =this.inaaa ;
                sendSock(data, this.result);
            },
            //     initWebSocket(){//初始化weosocket
            //         const wsuri = `ws:${imgURL}/web-socket/get-message-count`;
            //         this.websock = new WebSocket(wsuri);
            //         // 判断是否websocket是否已经打开，如果已经打开则直接发送数据  readyState
            //         if( this.websock.readyState !== WebSocket.OPEN ){
            //             this.websock.onopen = this.websocketonopen;
            //         }else{
            //             this.websocketsend(userid);
            //         }
            //         // this.websock.onopen = this.websocketonopen;
            //         this.websock.onerror = this.websocketonerror;
            //         this.websock.onmessage = this.websocketonmessage;
            //         this.websock.onclose = this.websocketclose;
            //     },
            //     //  连接建立之后执行send方法发送数据
            //     websocketonopen(){
            //         this.websocketsend();
            //     },
            //     websocket(){//连接建立失败重连
            //         this.initWebSocket();
            //     },
            //     //  数据接收
            //     websocketonmessage(e){
            //         const redata = JSON.parse(e.data);
            //         this.infoNum = redata.count
            //         console.log(`数据接收`+ JSON.parse(e.data).count)
            //     },
            //     //  数据发送
            //     websocketsend(Data){
            //         this.websock.send(Data);
            //         console.log(`数据发送`+Data)
            //     },
            //     //  关闭
            //     websocketclose(e){
            //         console.log('断开连接',e);
            //     }
        },
    }
</script>

<style scoped>

</style>