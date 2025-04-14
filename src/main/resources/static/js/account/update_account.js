const form_id = "form-update-account";
const success_url = "/account-list";
const btn_submit = document.getElementById("btn-submit");
const account_id = btn_submit.getAttribute("value");
const api_url = "/api/v1/account/update/" + account_id;


processForm(form_id);
