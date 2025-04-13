
let button_id = "btn-delete";
let success_url = "/account_list";

let delete_button = document.getElementById("btn-delete");
let account_id = delete_button.getAttribute("value");
let api_url = "/api/v1/account/delete/" + account_id;

console.log(api_url);

delete_button.addEventListener("click", deleteAccount);

function deleteAccount(){
    let is_confirmed = confirm("Are you Sure?\nThis action cannot be undone.");
    if (is_confirmed){
        fetch(api_url, {method : "DELETE"})
        .then(response => response.text())
        .then(message => {
            if (message == "Success"){
                console.log(message);
                window.location.href = "/account-delete-confirmation";
            }
            else{
                console.log(message);
            }
        })
    }
}