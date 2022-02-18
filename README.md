# FootBall-Registration-Form
A form for Football recruitment registration.
## Technology
- HTML
- CSS
- JavaScript
- Java
- Java Servlets

## Requirements

- Java JDK v11.0.2 or higher
- Microsoft SQL Server 2017 or higher

## SETUP

### Setup by IntelliJ
- Clone the repository
````
git clone https://github.com/Karandeep-Singhh/FootBall-Form.git
````
- Open the repo or cloned folder in IntelliJ as a project
- Let the Dependencies resolve
- You are good to go!!


### Setup using Build

- Clone the repository
- Make sure you have <a href="https://maven.apache.org/download.cgi">Maven</a>
- Add Maven's ```bin``` folder path to environment variables
- Check Maven installation
````
mvn -v
````
![mvn-v](/assets/mvn-version.png)


- Open the command prompt inside the cloned repository and type following commands
````
mvn package
````
- Upon successful build goto ```target\bin``` and run the batch file
- Now, goto a web browser and type in url _localhost:8080/FootballRegistrationForm.html_

## Flow of Application
![image3](/assets/3.png "flow")

This include Two scripts ```script.js``` and ```validation.js```
- ```script.js```

This script sets up basic initalizations that are required like, making ```Country-State-City``` logic


![image1](/assets/1.png "CSC")


- ```validation.js```

This checks some required validations such as if 'Name' is filled or not

![image2](/assets/2.png "validation")
