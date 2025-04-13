
function initializeForm(form_id){
    const form = document.getElementById(form_id);
    form.addEventListener("submit", submitForm);
}

function submitForm(){
    event.preventDefault();
    let data = parse_form(this);
    console.log(data);
    send_to_backend(api_url, data, success_url);
}

function parse_form(form){
    const formData = new FormData(form);
    var data = Object.fromEntries(formData);
    var json_data = JSON.stringify(data);
    console.log(json_data);
    return json_data;
}

function send_to_backend(api_url, data, success_url){
    fetch(api_url, {
        method: 'POST',
        headers:{ 'Content-Type': 'application/json' },
        body: data
    })
    .then(response => response.text())
    .then(message =>{ 
        console.log(message); 
        if (message == "Success"){
            alert(message);
            window.location.href = success_url;
        }
        else{
            alert(message);
        }
    })
    .catch(error => console.log(error));
}
