const form_id = "form-update-transaction"
const success_url = "/transaction-list";
const btn_save = document.getElementById("btn-save");
const transaction_id = btn_save.getAttribute("value");
const api_url = "/api/v1/transaction/update/" + transaction_id;

processForm(form_id);
