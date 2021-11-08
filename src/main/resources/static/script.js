var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#messages").html("");
}

function connect() {
    var socket = new SockJS('/our-websocket'); //Mở 1 kết nối websocket đến backend
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', function (message) { //Đăng kí một desination để backend đẩy data về fe
            showMessage(JSON.parse(message.body).content);
        });
    });
}
function showMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>");
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
	console.log("sending message");
    stompClient.send("/ws/message", {}, JSON.stringify({'messageContent': $("#message").val()})); //fe gửi data đến API backend
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMessage(); });


});