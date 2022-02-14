
var stateObjs = {
    "India": {
        "Delhi" : ["New Delhi", "North Delhi"],
        "Punjab" : ["Mohali","Patiala"]
    },
    "Norway" : {
        "Viken" : ["Drammen", "Fredrikstad", "Halden"],
        "Vestfold" : ["Notodden", "Porsgrunn", "Sandefjord"]
    },
    "Denmark" : {
        "North Denmark" : ["Aras", "Skagen"],
        "Zealand" : ["Faxe","Greve"]
    }
};
var cntry = document.querySelector('#cntry'),
    states = document.querySelector('#st'),
    cities = document.querySelector('#ct');

var isMail = document.getElementById('mailEnable');
var mailField = document.getElementById('mail');

function fillStates(){
    // states.options[0].selected = true;
    // cities.options[0].selected = true;
    states.length = 1;
    cities.length = 1;

    if(cntry.selectedIndex < 1) return;



    for(var state in stateObjs[cntry.value]){
        states.options[states.options.length] = new Option(state,state);
    }

    checkFilled();
}

function fillCities() {
    cities.length = 1;
    if(states.selectedIndex < 1) return;
    cities[0].selected = true;
    var city = stateObjs[cntry.value][states.value];
    for(var i = 0; i < city.length; i++){
        cities.options[cities.options.length] = new Option(city[i],city[i]);
    }
    checkFilled();
}

function emailToggle() {
    if(isMail.checked === false) {
        mailField.disabled = true;
    }
    else{
        mailField.disabled = false;
    }
}

window.onload = function() {
    const name = document.getElementById('fname');
    name.focus();
    const btn = document.querySelector('.submit');
    saveButton.disabled=true;
    retrievalButton.disabled=true;
    
    var cn = document.querySelector('#cntry'),
        st = document.querySelector('#st'),
        ct = document.querySelector('#ct');

    for( var country in stateObjs){
        cntry.options[cntry.options.length] = new Option(country, country);
    }
    cn.addEventListener('change', fillStates);
    st.addEventListener('change', fillCities);
    

    var phoneCode = document.querySelector("#ccode");
    const imgDiv = document.querySelector(".iconPhoto");
    phoneCode.onchange = function () {
        
        imgDiv.innerHTML = ``;
        if(phoneCode.value == "ind"){
            imgDiv.innerHTML = `<img src = "icons/india.png" width = "30" height="25">`;
        }
        // console.log("changed");
        if(phoneCode.value == "nor"){
            imgDiv.innerHTML = `<img src = "icons/norway.png" width = "30" height="25">`;
        }
        if(phoneCode.value == "den"){
            imgDiv.innerHTML = `<img src = "icons/denmark.png" width = "30" height="25">`;
        }
        
    }
    if(phoneCode.value == "ind"){
        imgDiv.innerHTML = `<img src = "icons/india.png" width = "30" height="25">`;
    }
    var isMail = document.getElementById('mailEnable');
    // var mailField = document.getElementById('mail');

    isMail.addEventListener('change', emailToggle)
    // isMail.onchange = emailToggle();
// states.onchange();
saveButton.addEventListener('click', submitRequest);
}

// export states,city;
