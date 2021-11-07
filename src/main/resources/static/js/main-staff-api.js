
let taskList = [];

// class task {
//     constructor(id, name, email, phone, idCard, math, physics, chemistry) {
//       this.MaSV = id;
//       this.HoTen = name;
//       this.Email = email;
//       this.SoDT = idCard;
//       this.CMND = idCard;
//       this.DiemToan = math;
//       this.DiemLy = physics;
//       this.DiemHoa = chemistry;
//     }
// }

const renderTasks = () => {
    let htmlContent = "";
  //  for(let task of taskList) {
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
    axios({
        url: 'http://localhost:8080/api/task/findAll',
        method: 'GET',
    })
    .then((res) => {
        taskList = res.data.content;
		console.log(taskList);
        renderTasks();
    })
    .catch((err) => {
        console.log(err);
    });
};

console.log(htmlContent);

//fetchTasks(); 

//document.getElementsByClassName("tutor__info__button__giao-lop")[0].addEventListener("click", fetchTasks());
