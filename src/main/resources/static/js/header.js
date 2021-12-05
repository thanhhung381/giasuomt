//HIỆU ỨNG CHUYỂN MENU
let li = document.getElementsByClassName("header__nav-li");
for(let i = 0; i < li.length; i++) {
    li[i].addEventListener("click", () => {
        for(let j = 0; j < li.length; j++){
            li[j].classList.remove("header__nav-li-clicked");
        }
        li[i].style.transition = "0.4s ease";
        li[i].classList.add("header__nav-li-clicked");
    });
}