const account_items = document.getElementsByClassName("list-item")

// var length = account_items.length;
for (var i = 0; i < account_items.length; i++){
    account_items[i].addEventListener("click", btn_account_onclick);
}

function btn_account_onclick(){
    var button = this;
    var accountName = button.id;
    console.log(accountName);
    alert("element clicked");
    fetch("/api/v1/account/get", {
        //todo: implement fetch method
    });
    //todo: complete onclick method
}