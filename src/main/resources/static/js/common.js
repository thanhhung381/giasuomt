//*** SELECT DROPDOWN ***//
//JS cho Dropdown Select để có thể CSS được các option trong nó:
var x, i, j, l, ll, selElmnt, a, b, c;
/* Look for any elements with the class "custom-select": */
x = document.getElementsByClassName("custom-select");
l = x.length;
for (i = 0; i < l; i++) {
  selElmnt = x[i].getElementsByTagName("select")[0];
  ll = selElmnt.length;
  /* For each element, create a new DIV that will act as the selected item: */
  a = document.createElement("DIV");
  a.setAttribute("class", "select-selected");
  a.innerHTML = selElmnt.options[selElmnt.selectedIndex].innerHTML;
  x[i].appendChild(a);
  /* For each element, create a new DIV that will contain the option list: */
  b = document.createElement("DIV");
  b.setAttribute("class", "select-items select-hide");
  for (j = 1; j < ll; j++) {
    /* For each option in the original select element,
    create a new DIV that will act as an option item: */
    c = document.createElement("DIV");
    c.innerHTML = selElmnt.options[j].innerHTML;
    c.addEventListener("click", function(e) {
        /* When an item is clicked, update the original select box,
        and the selected item: */
        var y, i, k, s, h, sl, yl;
        s = this.parentNode.parentNode.getElementsByTagName("select")[0];
        sl = s.length;
        h = this.parentNode.previousSibling;
        for (i = 0; i < sl; i++) {
          if (s.options[i].innerHTML == this.innerHTML) {
            s.selectedIndex = i;
            h.innerHTML = this.innerHTML;
            y = this.parentNode.getElementsByClassName("same-as-selected");
            yl = y.length;
            for (k = 0; k < yl; k++) {
              y[k].removeAttribute("class");
            }
            this.setAttribute("class", "same-as-selected");
            break;
          }
        }
        h.click();
    });
    b.appendChild(c);
  }
  x[i].appendChild(b);
  a.addEventListener("click", function(e) {
    /* When the select box is clicked, close any other select boxes,
    and open/close the current select box: */
    e.stopPropagation();
    closeAllSelect(this);
    this.nextSibling.classList.toggle("select-hide");
    this.classList.toggle("select-arrow-active");
  });
}

function closeAllSelect(elmnt) {
  /* A function that will close all select boxes in the document,
  except the current select box: */
  var x, y, i, xl, yl, arrNo = [];
  x = document.getElementsByClassName("select-items");
  y = document.getElementsByClassName("select-selected");
  xl = x.length;
  yl = y.length;
  for (i = 0; i < yl; i++) {
    if (elmnt == y[i]) {
      arrNo.push(i)
    } else {
      y[i].classList.remove("select-arrow-active");
    }
  }
  for (i = 0; i < xl; i++) {
    if (arrNo.indexOf(i)) {
      x[i].classList.add("select-hide");
    }
  }
}
/* If the user clicks anywhere outside the select box,
then close all select boxes: */
document.addEventListener("click", closeAllSelect);










//*** SELECT CUSTOM ***//  
// (Dùng ở 4 selection: Nơi học, Môn học, Yêu cầu, Thời gian - ở add-new-task.html)
//Click vào select-head -> Show và Hide phần options select__dropdown
let selectHead = document.getElementsByClassName("select__head");
for (let i = 0; i < selectHead.length; i++) {
  selectDropdown = selectHead[i].parentNode.getElementsByClassName("select__dropdown");
  selectHead[i].addEventListener("click", () => {
    if(selectDropdown[0].style.display === "block"){
      selectDropdown[0].style.display = "none";
    }
    else{
      selectDropdown[0].style.display = "block";
    }
  });
}
//Rê chuột vào select__dropdown__option -> Show phân select__dropdown__sub
let selectOption = document.getElementsByClassName("select__dropdown__option");
for (let i = 0; i < selectOption.length; i++) {
  selectOption[i].addEventListener("mouseover", () => {
    //Ẩn đi các select-dropdown-sub khác
    let selectDropdownSubs = document.getElementsByClassName("select__dropdown__sub")
    for (let i = 0; i < selectDropdownSubs.length; i++) {
      selectDropdownSubs[i].style.display = "none";
    }
    //Hiển thị ra select__dropdown__sub là con của select__dropdown__option hiện đang mouseover
    selectOption[i].getElementsByClassName("select__dropdown__sub")[0].style.display = "block";
  });
  selectOption[i].addEventListener("mouseout", () => {
    //Ẩn đi select__dropdown__sub là con của select__dropdown__option hiện đang mouseover
    selectOption[i].getElementsByClassName("select__dropdown__sub")[0].style.display = "none";
  });
}
//Rê chuột vào select__dropdown__sub__option -> Show phân select__dropdown__sub__sub
let selectSubOption = document.getElementsByClassName("select__dropdown__sub__option");
for (let i = 0; i < selectSubOption.length; i++) {
  selectSubOption[i].addEventListener("mouseover", () => {
    //Ẩn đi các select-dropdown-sub khác
    let selectDropdownSubs = document.getElementsByClassName("select__dropdown__sub__sub")
    for (let i = 0; i < selectDropdownSubs.length; i++) {
      selectDropdownSubs[i].style.display = "none";
    }
    //Hiển thị ra select__dropdown__sub__sub là con của select__dropdown__sub__option hiện đang mouseover
    selectSubOption[i].getElementsByClassName("select__dropdown__sub__sub")[0].style.display = "block";
  });
  selectSubOption[i].addEventListener("mouseout", () => {
    //Ẩn đi select__dropdown__sub__sub là con của select__dropdown__sub__option hiện đang mouseover
    selectSubOption[i].getElementsByClassName("select__dropdown__sub__sub")[0].style.display = "none";
  });
}









// COMMENTS - Kết hợp với css bên common.css
let com = document.getElementsByClassName("com");
for(let i = 0; i < com.length; i++) {
  // Con sẽ thụt vô: Nếu com là con, thì sẽ margin-left = n*(b+c)
  // Con sẽ sinh ra đoạn cong: Nếu com là con, thì sẽ sinh ra đoạn cong bằng cách thêm class child
  let parent = com[i].parentNode;
  if (parent.classList.contains("com")) {
    com[i].classList.add("mar-lef");
    com[i].classList.add("child");
  };

  // Cha sẽ sinh ra vertical line cho con gần nhất: Nếu com có con (ở đây là có com khác, chứ ko tính com_cont), thì com_cont sẽ sinh ra border-left bằng cách thêm class have-child vào com_cont
  let children = com[i].children;
  if(children.length > 1) {
    children[0].classList.add("has-child");
    
  };

  // Con có nextElementSibling sẽ sinh ra vertical line bổ sung cho cha: Nếu com có nextElementSibling, thì com sẽ sinh ra border-left bằng cách thêm class has-next-sibling
  let nextSibling = com[i].nextElementSibling;
  if(nextSibling != null) {
    com[i].classList.add("has-next-sibling");
  }
}


