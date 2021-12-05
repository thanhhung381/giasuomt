// HIỆU ỨNG CLICK MSL Ở SIDEBAR và PREST KEY DOWN/UP ĐỂ DUYỆT LỚP
const clickTaskList = (taskList) => {
	let mslList = document.getElementsByClassName("list-task-staff__list-msl__msl");
	for(let i = 0; i<mslList.length; i++){   
	    mslList[i].addEventListener("click", () => {
	      for(let j = 0; j < mslList.length; j++){
	          mslList[j].classList.remove("list-task-staff__list-msl__msl-change-style");
	      }
	      mslList[i].classList.add("list-task-staff__list-msl__msl-change-style");
		  renderTaskInfo(mslList[i].id, taskList);
	
	      //   PREST KEY DOWN/UP ĐỂ DUYỆT LỚP
	      //   window.addEventListener("keydown", event => keydownSideBarPress(event));
	      //   document.addEventListener("keydown", event => keydownSideBarPress(event));
	      document.onkeydown = e => {
	        let selectedElm = document.getElementsByClassName("list-task-staff__list-msl__msl-change-style")[0];
	        if (e.key === "ArrowDown") {
	            selectedElm.classList.remove("list-task-staff__list-msl__msl-change-style");
	            nextElm = selectedElm.nextElementSibling.classList.add("list-task-staff__list-msl__msl-change-style");
	        }
	        else if (e.key === "ArrowUp") {
	            selectedElm.classList.remove("list-task-staff__list-msl__msl-change-style");
	            nextElm = selectedElm.previousElementSibling.classList.add("list-task-staff__list-msl__msl-change-style");
	        }
	      };
	   });
	}
	let sideBarList = document.getElementsByClassName("list-task-staff__list-msl")[0];
	sideBarList.addEventListener("mouseleave", () => {
	    document.onkeydown = null;
	});
};

const renderTaskInfo = (msl, taskList) => {
	for(let i = 0; i<taskList.length; i++){  
		if(taskList[i].taskCode == msl){
			document.getElementById("msl").innerHTML = taskList[i].taskCode;
			document.getElementById("yeu-cau").innerHTML = taskList[i].requireApperance;
		}
	}
}

clickTaskList();