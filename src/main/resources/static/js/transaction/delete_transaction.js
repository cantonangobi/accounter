// let btn_delete_id = ;
// let delete_api_url = "/api/v1/transaction/delete/" + transaction_id;
let delete_successful_url = "/transaction-list";

let delete_buttons = document.getElementsByName("delete");

delete_buttons.forEach(element => {
    element.addEventListener("click", deleteTransaction);
    // console.log("tx ID: " + element.getAttribute("value"));
});

// let btn_delete = document.getElementById("btn-delete");
// let transaction_id = btn_delete.getAttribute("value");


// console.log(delete_api_url);

// btn_delete.addEventListener("click", deleteTransaction);

function deleteTransaction(){
    let transaction_id = this.getAttribute("value");
    let delete_api_url = "/api/v1/transaction/delete/" + transaction_id;
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