const account_items = document.getElementsByClassName("list-item")
// const element = document.getElementById("id");

// element.addEventListener("click")
// element.add
// console.log(account_items);
var length = account_items.length;
for (var i = 0; i < length; i++){
    // console.log(i);
    // console.log(account_items[i]);
    account_items[i].addEventListener("click", btn_account_onclick);
}

function btn_account_onclick(){
    var button = this;
    var accountName = button.id;
    console.log(accountName);
    // console.log("clicked");
    alert("element clicked");
    fetch("api/v1/account/get")
    
}