
let btn_delete_id = "btn-delete";
let delete_successful_url = "/transaction-list";

let delete_button = document.getElementById(btn_delete_id);
let transaction_id = delete_button.getAttribute("value");
let delete_api_url = "/api/v1/transaction/delete/" + transaction_id;

console.log(delete_api_url);

delete_button.addEventListener("click", deleteTransaction);

function deleteTransaction(){
    let is_confirmed = confirm("Are you Sure?\nThis action cannot be undone.");
    if (is_confirmed){
        fetch(delete_api_url, {method : "DELETE"})
        .then(response => response.text())
        .then(message => {
            if (message == "Success"){
                console.log(message);
                window.location.href = delete_successful_url;
            }
            else{
                console.log(message);
            }
        })
    }
}