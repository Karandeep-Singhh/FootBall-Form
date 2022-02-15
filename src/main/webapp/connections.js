const saveButton = document.getElementById('save');
var responseObject = {};


function getTeam(){
    for(var i = 0; i < teams.length ; i++){
        if(teams[i].checked === true){
            return i+1;
        }
    }
    return 0;
}

function getPosition(){
    var choices = [];
    for(var i = 0; i < positions.length; i++) {
        if(positions[i].checked === true){
            choices.push(i+1);
        }
    }
    if(choices.length > 0){
        return choices[Math.floor(Math.random() * choices.length)];
    }
    return 0;
}

function responseObj(object) {
    responseObject = JSON.parse(object);
    // console.log(responseObject);
    // console.log(responseObject.email);
    if(responseObject.status === "success"){
        saveStatus.innerHTML = "Saved!!";
    }
    if(responseObject.status === "failed"){
        findWrong();
    }
    if(responseObject.status === "updated"){
        saveStatus.innerHTML = "Updated!";
    }
    else if(responseObject.status === "userexists"){
        usrError.innerHTML = "User Already Exists!";
    }
    else{
        saveStatus.innerHTML = responseObject.status;
    }

}

//-------Submit Request--------

function submitRequest(ev){
    ev.preventDefault();
    if(findWrong()){
        var jsonObject = {
            "userName" : usr.value,
            "firstName" : fname.value,
            "lastName" : lname.value,
            "phoneCode" : phoneCode.selectedIndex,
            "phoneNumber" : phone.value,
            "email" : email.value,
            "ageGroup" : ages.selectedIndex,
            "team" : getTeam(),
            "position" : getPosition(),
            "address" : address.value,
            "pinCode" : pin.value,
            "country" : country.selectedIndex,
            "state" : state.selectedIndex,
            "city" : city.selectedIndex
        };
        //        "phoneCode" : "+"+phoneCode.options[phoneCode.selectedIndex].innerHTML.replace(/[^0-9]+/g,""),

        var request = new XMLHttpRequest();
        var url = "/goal";
        request.open('post', url);
        request.setRequestHeader('Content-Type', 'application/json;charset=UTF=8');
        var status = request.status;
        request.onreadystatechange = function () {
            if(request.readyState === XMLHttpRequest.DONE) {
                var status = request.status;
                if (status === 0 || (status >= 200 && status < 400)) {
                // console.log(request.responseText);
                responseObj(request.responseText);
                
                } else {
                console.log("request failed");
                }
            }
        };
        request.send(JSON.stringify(jsonObject));
    }
}


//------Put Request-------

function updateRequest(ev) {
    ev.preventDefault();

    if(findWrong()){
        var jsonObject = {
            "userName" : usr.value,
            "firstName" : fname.value,
            "lastName" : lname.value,
            "phoneCode" : phoneCode.selectedIndex,
            "phoneNumber" : phone.value,
            "email" : email.value,
            "ageGroup" : ages.selectedIndex,
            "team" : getTeam(),
            "position" : getPosition(),
            "address" : address.value,
            "pinCode" : pin.value,
            "country" : country.selectedIndex,
            "state" : state.selectedIndex,
            "city" : city.selectedIndex
        };
        //        "phoneCode" : "+"+phoneCode.options[phoneCode.selectedIndex].innerHTML.replace(/[^0-9]+/g,""),
        var request = new XMLHttpRequest();
        var url = "/goal";
        request.open('put', url);
        request.setRequestHeader('Content-Type', 'application/json;charset=UTF=8');
        var status = request.status;
        request.onreadystatechange = function () {
            if(request.readyState === XMLHttpRequest.DONE) {
            var status = request.status;
            if (status === 0 || (status >= 200 && status < 400)) {
                // console.log(request.responseText);
                responseObj(request.responseText);
                
            } else {
                console.log("request failed");
            }
            }
        };
        request.send(JSON.stringify(jsonObject));
    }
}

//----Get request-----

retrievalButton.addEventListener('click', function(event) {
    event.preventDefault();

    var request = new XMLHttpRequest();
    var url = "/goal?userName="+usr.value;
    request.open('get', url);
    request.setRequestHeader('Content-Type', 'application/json;charset=UTF=8');
    var status = request.status;
    request.onreadystatechange = function () {
        if(request.readyState === XMLHttpRequest.DONE) {
        var status = request.status;
        if (status === 0 || (status >= 200 && status < 400)) {

            fillFields(request.responseText);

        } else {
            console.log("request failed");
        }
        }
    };
    request.send();

})