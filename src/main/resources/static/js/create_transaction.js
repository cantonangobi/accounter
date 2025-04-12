
const api_url = "/api/v1/transaction/create";
const success_url = "/transaction-list";
const form_id = "form-create-transaction"

initializeForm(form_id);

// let form = document.getElementById("form-create-transaction");
// console.log(form);

// form.addEventListener("submit", submitForm);

// function submitForm(){
//     event.preventDefault();
//     let data = parseForm(this);
//     send_to_backend("api/v1/transaction/create", data, "/transaction-list");
// }

// function parseForm(form){
//     let formData = new FormData(form);
//     console.log(formData);
//     let data = Object.fromEntries(formData);
//     console.log(data);
//     let json_data = JSON.stringify(data);
//     console.log(json_data);
//     return json_data;
// }

// function send_to_backend(url, data){
//     fetch(url, {
//         method: "POST",
//         headers: {"content-type" : "application/json"},
//         body: data
//     })
//     .then(response => response.text())
//     .then(message => {
//         if (message == "Success"){
//             console.log(message);
//             alert(message);
//             location.href = "/transaction-list";
//         }
//         else{
//             console.log(message);
//             alert(message);
//         }
//     })
//     .catch(error => console.log(error));
// }