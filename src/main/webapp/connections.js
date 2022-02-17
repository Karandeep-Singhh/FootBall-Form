//---Get which team is selected---
function getTeam()
{
    for(var i = 0; i < teams.length ; i++)
    {
        if(teams[i].checked === true)
        {
            return i+1;
        }
    }
    return 0;
}

//----gets which positions are selected
function getPosition()
{
    var choices = [];
    for(var i = 0; i < positions.length; i++)
    {
        if(positions[i].checked === true)
        {
            choices.push(i+1);
        }
    }
    if(choices.length > 0)
    {
        return choices[Math.floor(Math.random() * choices.length)];
    }
    return 0;
}

//----Sets up the status for whatever response is received from the servelet----
function responseObj(object)
{
    object = JSON.parse(object);
  
    if(object.status === "success")
    {
        saveStatus.innerHTML = "Saved!!";
    }

    if(object.status === "failed")
    {
        findWrong();
        saveStatus.innerHTML = "Failed!";
    }

    if(object.status === "updated")
    {
        saveStatus.innerHTML = "Updated!";
    }

    if(object.status === "userexists")
    {
        usrError.innerHTML = "User Already Exists!";
        saveStatus.innerHTML = "Failed!";
    }
}

//-------Submit Request--------
function submitRequest(ev)
{

    ev.preventDefault();

    //check if all fields are filled in required manner before sending request
    if(findWrong())
    {
        var jsonObject = {
            "userName" : usr.value,
            "firstName" : fname.value,
            "lastName" : lname.value,
            "phoneCode" : phoneCode.selectedIndex,
            "phoneNumber" : phone.value,
            "email" : email.value.toLowerCase(),
            "ageGroup" : ages.selectedIndex,
            "team" : getTeam(),
            "position" : getPosition(),
            "address" : address.value,
            "pinCode" : pin.value,
            "country" : country.selectedIndex,
            "state" : state.selectedIndex,
            "city" : city.selectedIndex
        };

        var request = new XMLHttpRequest();
        var url = "/goal";
        request.open('post', url);
        request.setRequestHeader('Content-Type', 'application/json;charset=UTF=8');

        request.onreadystatechange = function ()
        {
            if(request.readyState === XMLHttpRequest.DONE)
            {
                var status = request.status;
                if (status === 0 || (status >= 200 && status < 400))
                {
                    responseObj(request.responseText);
                }
            }
        };
        request.send(JSON.stringify(jsonObject));
    }
}


//------Put Request-------
function updateRequest(ev)
{
    ev.preventDefault();

    //check if all fields are filled in required manner before sending request
    if(findWrong())
    {
        var jsonObject = {
            "userName" : usr.value,
            "firstName" : fname.value,
            "lastName" : lname.value,
            "phoneCode" : phoneCode.selectedIndex,
            "phoneNumber" : phone.value,
            "email" : email.value.toLowerCase(),
            "ageGroup" : ages.selectedIndex,
            "team" : getTeam(),
            "position" : getPosition(),
            "address" : address.value,
            "pinCode" : pin.value,
            "country" : country.selectedIndex,
            "state" : state.selectedIndex,
            "city" : city.selectedIndex
        };
        
        var request = new XMLHttpRequest();
        var url = "/goal";
        request.open('put', url);
        request.setRequestHeader('Content-Type', 'application/json;charset=UTF=8');

        request.onreadystatechange = function ()
        {
            if(request.readyState === XMLHttpRequest.DONE)
            {
                var status = request.status;
                if (status === 0 || (status >= 200 && status < 400))
                {
                    responseObj(request.responseText);
                    
                } 
            }
        };

        request.send(JSON.stringify(jsonObject));
    }
}

//----Get request-----
retrievalButton.addEventListener('click', function(event) 
{
    event.preventDefault();

    var request = new XMLHttpRequest();
    var url = "/goal?userName="+usr.value;
    request.open('get', url);
    request.setRequestHeader('Content-Type', 'application/json;charset=UTF=8');

    request.onreadystatechange = function () 
    {
        if(request.readyState === XMLHttpRequest.DONE) 
        {
            var status = request.status;
            
            if (status === 0 || (status >= 200 && status < 400)) 
            {
                fillFields(request.responseText);

            }
        }

    };
    request.send();

})