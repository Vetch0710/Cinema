let websock = null;
let global_history = null;
let global_result = null;
const serverPort = '8089';	//webSocket连接端口
let count = 0;	//webSocket连接端口


function getWebIP(){
    const curIP = window.location.hostname;
    return curIP;
}

function initWebSocket(token,callback,callback2){ //初始化weosocket
    //ws地址
    // var wsuri = "ws://10.5.75.21:8089/CinemaData/ws?accessToken="+token ;
    count=0;
    global_history = callback;
    global_result = callback2;
    var wsuri = "ws://8.131.77.164:8089/CinemaData/ws";
    websock = new WebSocket(wsuri,[token]);
    websock.onmessage = function(e){
        websocketonmessage(e);
    }
    websock.onclose = function(e){
        websocketclose(e);
    }
    websock.onopen = function () {
        websocketOpen();
    }

    //连接发生错误的回调方法
    websock.onerror = function () {
        console.log("WebSocket连接发生错误");
    }
}

// 实际调用的方法
function sendSock(agentData){

    if (websock.readyState === websock.OPEN) {
        //若是ws开启状态
        websocketsend(agentData)
    }else if (websock.readyState === websock.CONNECTING) {
        // 若是 正在开启状态，则等待1s后重新调用
        setTimeout(function () {
            sendSock(agentData,callback);
        }, 1000);
    }else {
        // 若未开启 ，则等待1s后重新调用
        setTimeout(function () {
            sendSock(agentData,callback);
        }, 1000);
    }
}

//数据接收
function websocketonmessage(e){
    console.log(count)
    if (count===1){
        global_history(JSON.parse(e.data));
        count++;
    }else {
        global_result(JSON.parse(e.data));
    }

}

//数据发送
function websocketsend(agentData){
    websock.send(JSON.stringify(agentData));
}

//关闭
function websocketclose(e){
    console.log("connection closed (" + e.code + ")");
}

function websocketOpen(e){
    console.log("连接成功");
    count++;
}

// initWebSocket();

export{sendSock,initWebSocket}