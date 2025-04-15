const delete_successful_url = "/account-delete-confirmation";
const btn_delete = document.getElementById("btn-delete");
const account_id = btn_delete.getAttribute("value");
const delete_api_url = "/api/v1/account/delete/" + account_id;

console.log("account ID:" + account_id );

btn_delete.addEventListener("click", delete_request);

// delete_request()

// function deleteAccount(){
//     let is_confirmed = confirm("Are you Sure?\nThis action cannot be undone.");
//     if (is_confirmed){
//         fetch(delete_api_url, {method : "DELETE"})
//         .then(response => response.text())
//         .then(message => {
//             if (message == "Success"){
//                 console.log(message);
//                 window.location.href = delete_successful_url;
//             }
//             else{
//                 console.log(message);
//             }
//         })
//     }
// }