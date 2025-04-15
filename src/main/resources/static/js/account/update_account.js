const form_id = "form-update-account";
const success_url = "/account-list";
const account_id = document.getElementById("btn-submit").getAttribute("value");
const api_url = "/api/v1/account/update/" + account_id;


processForm(form_id);
