// DROPDOWN TOGGLE - mở ra thu gọn cho register và learner
let toggle = document.getElementsByClassName("dropdown-toggle");
for(let i = 0; i < toggle.length; i++) {
    toggle[i].addEventListener("click", () => {
        let children = toggle[i].parentNode.children;
        let a;
        if (children[1].tagName == "SELECT") {a = 2} else {a = 1}; //Để nếu là learner, thì sẽ ko ẩn đi select "Quan hệ với người đăng ký"
        if(children[a].style.display == "none"){           
            for(let j = a; j < children.length; j++) {
                    children[j].style.display = "block";
            }   
            toggle[i].getElementsByClassName("fas")[0].innerHTML = "&#xf13a";  
        }
        else {
            for(let j = a; j < children.length; j++) {
                children[j].style.display = "none";
            }  
            toggle[i].getElementsByClassName("fas")[0].innerHTML = "&#xf138";
        }   
    });
}


// GENDER ĐỔI MÀU SAU KHI CLICKED
let females = document.getElementsByClassName("female");
let males = document.getElementsByClassName("male");
for(let i = 0; i < females.length; i++) {
    let female = females[i];
    let male = males[i];
    female.addEventListener ("click", () => {
        if (female.classList.contains("female-clicked")){
            female.classList.remove("female-clicked");
        }
        else if (!female.classList.contains("female-clicked")){
            male.classList.remove("male-clicked");
            female.classList.add("female-clicked");
        }
    });
    male.addEventListener ("click", () => {
        if (male.classList.contains("male-clicked")){
            male.classList.remove("male-clicked");
        }
        else if (!male.classList.contains("male-clicked")){
            female.classList.remove("female-clicked");
            male.classList.add("male-clicked");
        }
    });
}





// SELECTION: 
// 1. sổ ra danh sách options khi click vào selection, và thay đổi arrow-icon
let selection_head = document.getElementsByClassName("selection_head");
for(let i = 0; i < selection_head.length; i++){
    let selection_area = selection_head[i].nextElementSibling;
    let selection_head_icon = selection_head[i].getElementsByClassName("arrow-icon")[0];
    selection_head[i].addEventListener("click", () => {       
        if(selection_area.style.display === "block"){
            selection_area.style.display = "none";
            selection_head_icon.innerHTML = "&#xf0d7";
        }
        else {
            selection_area.style.display = "block";
            selection_head_icon.innerHTML = "&#xf0d8";
        }
    });
    let selection = selection_head[i].parentNode;
    selection.addEventListener("mouseleave", () => {
        selection_area.style.display = "none";
        selection_head_icon.innerHTML = "&#xf0d7";
    });
}
// 2. sổ ra danh sách options khi rê chuột vào 1 option của selection ở trên 1
let selection_area_option = document.getElementsByClassName("selection_area_option");
for(let i = 0; i < selection_area_option.length; i++){
    let selection_area_option__area = selection_area_option[i].children[0].children[2];
    selection_area_option[i].addEventListener("mouseover", () => {        
        selection_area_option__area.style.display = "block";
    });
    selection_area_option[i].addEventListener("mouseleave", () => {
        selection_area_option__area.style.display = "none";
    });
}
// 3. sổ ra danh sách options khi rê chuột vào 1 option của selection ở trên 2
let selection_area_option__area_option = document.getElementsByClassName("selection_area_option__area_option");
for(let i = 0; i < selection_area_option__area_option.length; i++){
    let selection_area_option__area_option__area = selection_area_option__area_option[i].children[0].children[2];
    selection_area_option__area_option[i].addEventListener("mouseover", () => {        
        selection_area_option__area_option__area.style.display = "block";
    });
    selection_area_option__area_option[i].addEventListener("mouseleave", () => {
        selection_area_option__area_option__area.style.display = "none";
    });
}



/* RESULTS */
//Rê chuột vô hiện ra dấu Remove -> click vào sẽ xoá result đó đi
let result = document.getElementsByClassName("result");
for (let i = 0; i < result.length; i++) {
  let deleteIcon = result[i].getElementsByTagName("i")[0];
  result[i].addEventListener("mouseover", () => {
    deleteIcon.style.display = "block";
  });
  result[i].addEventListener("mouseout", () => {
    deleteIcon.style.display = "none";
  });
  deleteIcon.addEventListener("click", () => {
    deleteIcon.parentNode.remove();
  });
}
let resultInput = document.getElementsByClassName("result-input");
for (let i = 0; i < resultInput.length; i++) {
    let deleteIconResultInput = resultInput[i].getElementsByTagName("i")[0];
    console.log(deleteIconResultInput);
    resultInput[i].addEventListener("mouseover", () => {
        deleteIconResultInput.style.display = "block";
    });
    resultInput[i].addEventListener("mouseout", () => {
        deleteIconResultInput.style.display = "none";
    });
    deleteIconResultInput.addEventListener("click", () => {
        deleteIconResultInput.parentNode.remove();
    });
  }

// result-input resize theo độ dài value
for (let i = 0; i < resultInput.length; i++) {
    let input = resultInput[i].getElementsByTagName("textarea")[0];
    input.addEventListener("input", () => {
        resultsAreaWidth = parseInt(document.getElementsByClassName("results-area")[0].offsetWidth) - 70; //width của vùng chứa kết quả. Đơn vị offsetWidth trả về là px nên phải parse về Int. Trừ 70 là kích thước của padding và border.
        inputValueWidth = (input.value.length +5) * 8; //width của input theo value length
        if(inputValueWidth < resultsAreaWidth) {  //Nếu width của input (là input.value.length +5) * 8)) mà nhỏ hơn width của vùng chứa, thì sẽ dãn ra, còn ko thì sẽ ko dãn ra nữa để xuống dòng.
            input.style.width = inputValueWidth + 'px';
        }
        
        input.style.height = input.scrollHeight+'px'; //Chiều cao của khung textarea sẽ tự dãn ra
        
        // Chỉnh vị trí của remove Icon để ko bị lệch đi nhiều khi các kích thước thay đổi
        let removeIcon = input.nextElementSibling;
        if(input.value.length > 30) {
            removeIcon.style.left = "96%"; //Để cho cái icon x nó cách xa ra nữa chứ ko bị thụt vô khi text dài
        }
        if(input.scrollHeight > 56) {
            removeIcon.style.bottom = "85%";
        }
    });
};
