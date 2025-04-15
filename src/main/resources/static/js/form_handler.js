
function processForm(form_id){
    const form = document.getElementById(form_id);
    form.addEventListener("submit", submitForm);
}

function submitForm(){
    event.preventDefault();
    const formData = new FormData(this);
    var data = JSON.stringify(Object.fromEntries(formData));
    // var json_data = JSON.stringify(data);
    console.log(data);
    // let data = parse_form(this);
    // console.log(data);
    post_request(api_url, data, success_url);
}

function parse_form(form){
    const formData = new FormData(form);
    var data = Object.fromEntries(formData);
    var json_data = JSON.stringify(data);
    console.log(json_data);
    return json_data;
}

function post_request(api_url, data, success_url){
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

function delete_request(){
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