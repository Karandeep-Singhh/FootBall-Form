
//JSON for country-state-cities
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


//Fills the States according to the selected country
function fillStates()
{
    states.length = 1;
    cities.length = 1;

    if(cntry.selectedIndex < 1) return;



    for(var state in stateObjs[cntry.value])
    {
        states.options[states.options.length] = new Option(state,state);
    }

    checkFilled();
}

//Fills the Cities according to the selected state
function fillCities() 
{
    cities.length = 1;
    if(states.selectedIndex < 1) return;
    cities[0].selected = true;
    var city = stateObjs[cntry.value][states.value];
    for(var i = 0; i < city.length; i++)
    {
        cities.options[cities.options.length] = new Option(city[i],city[i]);
    }
    checkFilled();
}

//toogles the Email field
function emailToggle() 
{
    if(isMail.checked === false) 
    {
        mailField.disabled = true;
    }
    else
    {
        mailField.disabled = false;
    }
}

//To set up couple of things Like some icons and some eventListener
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
    phoneCode.onchange = function () 
    {
        
        imgDiv.innerHTML = ``;
        if(phoneCode.value == "ind")
        {
            imgDiv.innerHTML = `<img src = "icons/india.png" width = "30" height="25">`;
        }
        if(phoneCode.value == "nor")
        {
            imgDiv.innerHTML = `<img src = "icons/norway.png" width = "30" height="25">`;
        }
        if(phoneCode.value == "den")
        {
            imgDiv.innerHTML = `<img src = "icons/denmark.png" width = "30" height="25">`;
        }
        
    }

    if(phoneCode.value == "ind")
    {
        imgDiv.innerHTML = `<img src = "icons/india.png" width = "30" height="25">`;
    }

    var isMail = document.getElementById('mailEnable');

    isMail.addEventListener('change', emailToggle);
    saveButton.addEventListener('click', submitRequest);
}