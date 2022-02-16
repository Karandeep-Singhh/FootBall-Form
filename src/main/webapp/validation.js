const saveButton = document.getElementById('save');

const usr = document.getElementById("usrname");
const fname = document.getElementById("fname");
const lname = document.getElementById('lname');

const ccode = document.getElementById("ccode");
const phone = document.getElementById("phone");
const email = document.getElementById("mail");
const ages = document.getElementById('ages');

const pin = document.getElementById('pin');
const address = document.getElementById('addr');

const saveStatus = document.getElementById('status');
const usrError = document.getElementById('invalidate');
const teams = document.querySelectorAll("input[name='sTeam']");
const phoneCode = document.getElementById('ccode');
const positions = [];
for(var i = 1; i <= 4; i++){
    positions.push(document.getElementById('cb'.concat(i.toString())));
}

const country = document.getElementById('cntry');
const state = document.getElementById('st');
const city = document.getElementById('ct');

const retrievalButton = document.querySelector('.retBtn');

const inputs = document.querySelectorAll("input");

    ///          /\     
    ///         /  \    
    ///        / /\ \   
    ///       / /\ \ \  
    ///      / /  \ \_\ 
    //     \/_/ __ \/_/
    //         /_/\  
    //         \ \ \ 
    //         / / / 
    //         \ \ \ 
    //         / / / 
    //         \ \ \ 
    //          \_\/  
    //     Field elements



//Perform checks on necessary fields in order to unlock the submit/update button
const unlockButton = function()
{
    var flag = true;
    var state1 = document.getElementById('st');
    var city1 = document.getElementById('ct');
    
    //---------need to bring flag as 'false'------------

    if(!checkUser())flag = false;
    if(!checkEmail())flag = false;
    if(!checkName())flag = false;;
    if(!checkPhone())flag = false;;
    if(!checkTeam())flag = false;;
    if(!checkPosition())flag = false;;
    if(!(ccode.selectedIndex >= 1))flag = false;;
    if(!(ages.selectedIndex >= 1))flag = false;;
    if(!(state1.selectedIndex >0))flag = false;;
    if(!(city1.selectedIndex >0))flag = false;;
    if(!(country.selectedIndex >0))flag = false;;

    
    return flag;
}

// Validate the "USER" field
function checkUser()
{
    usr.addEventListener('focusin',function()
    {
        usrError.innerHTML = '';
        this.classList.remove('wrong');
        this.style.border = '2px solid #aeaeb5'
    });

    if(usr.value === '') return false;

    if(/(\w+)/g.test(usr.value))
    {
        return true;
    }
    else
    {
        usrError.innerHTML = 'Invalid format';
        usr.classList.add('wrong');
        usr.style.border = '2px solid rgba(255, 0, 26, 0.77)';
        return false;
    }
}

/** Acts upon submit to check if any field data is wrong before sending the request
 * Also perform checks when server sends an invalidation in the data.
*/
function findWrong()
{
    
    var flag = true;

    if(!unlockButton())
    {
        flag = false;
    }
    if(!checkLastName())
    {
        flag = false;
    }
    if(!checkPin())
    {
        flag = false;
    }
    if(!checkEmail())
    {
        flag = false;
    }
    if(!addressCheck())
    {
        flag = false;
    }

    return flag;
}

// Validate the "EMAIL" field
function checkEmail() 
{
    var error = document.getElementById('emailError');
    email.addEventListener('focusin', function () {
        email.classList.remove('wrong');
        email.style.border = '2px solid #aeaeb5'
        error.innerHTML = ``;
    });

    if(email.value === '')
    {
        return false;
    }

    if (/^[A-Za-z0-9_\-\.]+\@(\[?)[\w\-\.]+\.([a-zA-Z]{2,8}|[0-9]{1,3})(\]?)\;{0,}$/.test(email.value))
    {    
        return (true);
    }

    else
    {
        error.innerHTML = `Invalid Email!`;
        email.classList.add('wrong');
        email.style.border = '2px solid rgba(255, 0, 26, 0.77)';
        return false;
    }
}

//Toggles the Submit Button
const checkFilled = () => {
    saveButton.disabled = !unlockButton();
};

// Validate the "PINCODE" field
function checkPin()
{
    var error = document.getElementById('pinError');    
    
    pin.addEventListener('focusin', function () {
        pin.classList.remove('wrong');
        pin.style.border = '2px solid #aeaeb5'
        error.innerHTML = ``;
    })

    if(pin.value === '' || pin.value.length === 6)
    {
        return true;
    }
    
    else
    {
        pin.classList.add('wrong');
        pin.style.border = '2px solid #aeaeb5'
        error.innerHTML = `Pin must contain '6' numbers!`;
        return false;
    }
}

//Regex to check if there are onlu alphabets in a particular field
function isAlphabatesOnly(val)
{

    if(val == undefined || val == "")
    {
        return false;
    }
    var re = new RegExp("^([a-zA-Z]{1,})$");

    return re.test(val);
}

// Validate the "FIRESTNAME" field
const checkName = function () 
{
    var error = document.getElementById('fnameError');
    error.innerHTML = ``;
    fname.classList.remove('wrong');
    fname.style.border = '2px solid #aeaeb5'
    var flag = true;

    fname.addEventListener('focusin',function () {
        fname.classList.remove('wrong');
        fname.style.border = '2px solid #aeaeb5'
        error.innerHTML = ``;
    });
    
    var expression = /[a-zA-Z]+/;

    if(!isAlphabatesOnly(fname.value))
    {
        flag = false;
        error.innerHTML = `Alphabets only!`;
        fname.classList.add('wrong');
        fname.style.border = '2px solid rgba(255, 0, 26, 0.77)';
        
        if(fname.value === '')
        {
            error.innerHTML = `Fill Your Name!!`;
            fname.classList.add('wrong');
            fname.style.border = '2px solid rgba(255, 0, 26, 0.77)';
        }

        if(fname.value.match(/\s+/))
        {
            error.innerHTML = error.innerHTML+`No spaces!`;
            fname.classList.add('wrong');
            fname.style.border = '2px solid rgba(255, 0, 26, 0.77)';
        }

        if(fname.value.match(/[0-9]+/))
        {
            error.innerHTML = `No Numbers!`;
            fname.classList.add('wrong');
            fname.style.border = '2px solid rgba(255, 0, 26, 0.77)';
        }
    }

    else if(fname.value.match(expression))
    {
        
        if(fname.value.match(/\s+/))
        {
            flag = false;
            error.innerHTML = error.innerHTML+` No spaces!`;
            fname.classList.add('wrong');
            fname.style.border = '2px solid rgba(255, 0, 26, 0.77)';
        }
    }
    if(!flag)
    {
        saveButton.disabled = true;
    }
    return flag;
};

// Validate the "LASTNAME" field
const checkLastName = function () 
{
    var error = document.getElementById('lnameError');
    var expression = /[a-zA-Z]+/;

    lname.addEventListener('focusin', function () {
        lname.classList.remove('wrong');
        lname.style.border = '2px solid #aeaeb5'
        error.innerHTML = ``;
    });

    if(!lname.value.match(expression) && lname.value != '' || lname.value.match(/[0-9]+/))
    {
        error.innerHTML = `Alphabets only!`;
        lname.classList.add('wrong');
        lname.style.border = '2px solid rgba(255, 0, 26, 0.77)';
        return false;
    }
    return true;
};


// Validate the "PHONE" field
const checkPhone = function ()
{
    var flag = true;
    var error = document.getElementById('phoneError');

    phone.addEventListener('focusin', function () {
        phone.classList.remove('wrong');
        phone.style.border = '2px solid #aeaeb5'
        error.innerHTML = ``;
    })

    if(!phone.value.match(/[0-9]+/) && phone.value != '')
    {
        flag = false;
        error.innerHTML = `Numerics Only!`;
        phone.classList.add('wrong');
        phone.style.border = '2px solid rgba(255, 0, 26, 0.77)';
    }

    if(phone.value.length != 10 && phone.value.match(/[0-9]+/))
    {
        flag = false;
        error.innerHTML = `Number length is wrong!`;
        phone.classList.add('wrong');
        phone.style.border = '2px solid rgba(255, 0, 26, 0.77)';
    }

    if(!flag)
    {
        saveButton.disabled = true;
    }

    return flag;
};

// Validate the "ADDRESS" field
const addressCheck = function () 
{
    var error = document.getElementById('addressError');
    address.addEventListener('focusin', function () 
    {
        address.classList.remove('wrong');
        address.style.border = '2px solid #aeaeb5'
        error.innerHTML = ``;
    })

    var pattern = /[@/=<>$]/
    if(address.value.match(pattern)) 
    {
        error.innerHTML = `No Special Characters!`;
        address.classList.add('wrong');
        address.style.border = '2px solid rgba(255, 0, 26, 0.77)';
        return false;
    }
    return true;
};

// Validate if "TEAM" is chosen
const checkTeam = () => {

    var toggle = false;
    for(var i = 0; i < teams.length ; i++)
    {
        if(teams[i].checked === true)
        {
            toggle = true;
    
        }
    }
    if(!toggle)
    {
        saveButton.disabled = true;
    }

    return toggle;
}

// Validate if "POSTION" is chosen
const checkPosition = () => {
    var isChecked = false;

    for(var i = 0; i < positions.length; i++) 
    {
        if(positions[i].checked === true)
        {
            isChecked = true;
        }
    }
    if(!isChecked)
    {
        saveButton.disabled = true;
    }

    return isChecked;
}




//Fill the fields after the retrival response is captured
function fillFields(player) 
{
    player = JSON.parse(player);
    
    //checks if response contains a key so that it can be filled to the fields
    if(player.userCheck && player.status === 'success') 
    {
        
        usrError.innerHTML = "";

        usr.value = player.userName;
        fname.value = player.firstName;
        lname.value = player.lastName;
        phoneCode.selectedIndex = parseInt(player.phoneCode);
        phone.value = player.phoneNumber;
        email.value = player.email;

        ages.selectedIndex = parseInt(player.ageGroup);
        teams[parseInt(player.team)-1].checked = true;

        for(var i = 0; i < positions.length; i++)
        {
            positions[i].checked = false
        }

        positions[parseInt(player.position)-1].checked = true;
        address.value = player.address;
        pin.value = player.pinCode;

        country.selectedIndex = parseInt(player.country);
        fillStates(country);
        state.selectedIndex = parseInt(player.state);
        fillCities(state);
        city.selectedIndex = parseInt(player.city);

        document.getElementById('mailEnable').checked = true;

        emailToggle();
        toggleButtonEvents(true);
        checkFilled();
    }
    else
    {
        usrError.innerHTML = "Failed/User doesn't exists";
        toggleButtonEvents(false);
        emptyFields();
    }

}

//Toggles the buttons events between 'submit' and 'update'
function toggleButtonEvents(stat) 
{
    
    if(stat)
    {
        saveButton.innerHTML = "Update";
        saveButton.removeEventListener('click',submitRequest);
        saveButton.addEventListener('click', updateRequest);
    }
    else
    {
        saveButton.innerHTML = "Phewww!! Let's Submit";
        saveButton.removeEventListener('click',updateRequest);
        saveButton.addEventListener('click', submitRequest)
    }
}


//Empty the fields when called
function emptyFields()
{
    fname.value = '';
    lname.value = '';
    phoneCode.selectedIndex = 1;
    phone.value = '';
    email.value = '';
    ages.selectedIndex = 0;
    
    for(var i = 0; i < teams.length ; i++)
    {
        teams[i].checked = false;
    }

    for(var i = 0; i < positions.length; i++) 
    {
        positions[i].checked = false
    }
    document.getElementById('mailEnable').checked = false;

    address.value = '';
    pin.value = '';
    country.selectedIndex = 0;
    fillStates();
    fillCities();

}

//Toggles the 'Retreive' button
function checkAvailability()
{
    checkFilled();
    usrError.innerHTML = "";
    if(usr.value === "")
    {
        retrievalButton.disabled = true;
    }
    else
    {
        retrievalButton.disabled = false;
    }
}

//        _               _   
//       /_/\ Event     /\_\ 
//       \ \ \Listeners/ / /    
//        \ \ \       / / /     
//         \ \ \     / / /      
//          \ \ \   / / /       
//           \ \ \ / / /        
//            \_\/ \/_/         
                          
ccode.addEventListener('change', checkFilled);
email.addEventListener('focusout', checkFilled);
ages.addEventListener('change', checkFilled);
city.addEventListener('change', checkFilled);
fname.addEventListener('focusout', checkFilled);
usr.addEventListener('focusout', checkAvailability);

for (var i = 0 ; i< teams.length ; i++)
{
    teams[i].addEventListener('change', checkFilled);
}

for(var i = 0 ; i< positions.length ; i++)
{
    positions[i].addEventListener('change', checkFilled);
}

lname.addEventListener('focusout', checkLastName);
phone.addEventListener('focusout', checkFilled);
address.addEventListener('focusout', addressCheck);
