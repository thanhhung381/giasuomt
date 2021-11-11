
let taskList = [];


const renderTasks = () => {
    let htmlContent = "";
	for(let i = 0; i < taskList.length; i++) {
       htmlContent += `
          <div id="${taskList[i].taskCode}" class="list-task-staff__list-msl__msl">
            <div class="ma-so-lop">${taskList[i].taskCode}</div>
            <div class="so-ung-vien">(4)</div>
            <div class="phu-huynh-ok">OK!</div>
            <div class="ung-vien-ok"><i class="far fa-thumbs-up"></i></div>
            <div class="can-chu-y"><i class="fas fa-exclamation-triangle"></i></div>
            <div class="xu-li-gap"><i class="fas fa-bolt"></i></div>
          </div>
       `;
    }
    document.getElementById("task-list").innerHTML = htmlContent; 
};

const fetchTasks = () => {
//    axios({
//        url: 'http://localhost:8080/api/task/findAll',
//        method: 'GET',
//    })
//    .then((res) => {
//        taskList = res.data.content;
//        renderTasks();
//    })
//    .catch((err) => {
//        console.log(err);
//    });

axios.get('https://giasuomtdemo.herokuapp.com/api/task/findAll')
  .then(function (res) {       
        taskList = res.data.content;
        renderTasks();
		clickTaskList(taskList);
  })
  .catch(function (error) {   
    // on error      
    console.log(error);
  });

};



fetchTasks(); 

//document.getElementsByClassName("tutor__info__button__giao-lop")[0].addEventListener("click", fetchTasks());



    var socket = new SockJS('/our-websocket'); //Mở 1 kết nối websocket đến backend
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        //setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/tasks', function (message) { //Đăng kí một desination để backend đẩy data về fe
            //showMessage(JSON.parse(message.body).content);
			console.log("xxx");
			console.log(JSON.parse(message.body).content);
			fetchTasks(); 
        });
    });


