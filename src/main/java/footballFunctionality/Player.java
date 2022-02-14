package footballFunctionality;

public class Player {
    private String userName;
    private String firstName;
    private String lastName;
    private String phoneCode;
    private String phoneNumber;
    private String email;
    private String ageGroup;
    private String team;
    private String position;
    private String address;
    private String pinCode;
    private String country;
    private String state;
    private String city;
    private String status;
    private boolean userCheck = false;


    public void setUserCheck(boolean userCheck) {
        this.userCheck = userCheck;
    }

    public boolean isUserCheck() {
        return userCheck;
    }



    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public String getTeam() {
        return team;
    }

    public String getPosition() {
        return position;
    }

    public String getAddress() {
        return address;
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }
}
