// HIỆU ỨNG MŨI TÊN CHEVRON KHI RÊ CHUỘT VÀO NÚT "GIAO LỚP"
let giaoLopButton = document.getElementsByClassName("tutor__info__button__giao-lop");
for(let i = 0; i<giaoLopButton.length; i++){   
    giaoLopButton[i].addEventListener("mouseover", () => {
        giaoLopButton[i].getElementsByClassName("chevrons")[0].style.display = "block";
    });
    giaoLopButton[i].addEventListener("mouseleave", () => {
        giaoLopButton[i].getElementsByClassName("chevrons")[0].style.display = "none";
    });
}


// $(document).ready(function(){
//     $("i").hide();
// })





