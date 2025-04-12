const form = document.getElementById('form-signup');
form.addEventListener('submit', on_click_submit);

function on_click_submit(){
    event.preventDefault();
    var data = parse_form(form);
    send_to_backend('/api/v1/registration', data);
}

function parse_form(form){
    const formData = new FormData(form);
    var data = Object.fromEntries(formData);
    var json_data = JSON.stringify(data);
    console.log(json_data);
    return json_data;
}

function send_to_backend(url, data){
    var response;
    fetch(url, {
        method: 'POST',
        headers:{ 'Content-Type': 'application/json' },
        body: data
    })
    .then(res => res.text())
    .then(message =>{ 
        console.log(message); 
        if (message == "Success"){
            window.location.href = "/login";
        }
        else{
            alert(message);
        }
        response = message;
    })
    .catch(error => console.log(error));
    return response;
}
