# Application Template
This is a template for you to start working within a java environment. _Substitute this description for a description of your application_.
**_Please feel free to add new sections or delete not used sections_**.

## 1) What is this repository for?

### 1.1) Quick summary
Version: `0.1-SNAPSHOT`

Detailed description of your app.

### 1.2) Disclosure
Any required disclosures?

## 2) How do I get set up? ###

### 2.1) Summary of set up
The technologies the project is using.

#### 2.1.1) Development environment
- DataBase:			H2 (Previously using OracleDB)
- Java version: 	`1.17`
- Maven

#### 2.1.2) Project Dependencies
This library uses:
- **lombok** to log errors and general logs.
    - **Slf4j**
- **PropertiesManager** to obtain the default properties values.
- **Spring boot**
- **Selenium-Jupiter**


### 2.2) Configuration Steps
#### 2.2.1) Database configuration

You don't need to set up a DataBase for the project to connect to.
There is an H2 database already embedded in the project but if you want to
you can configure a separate one and switch the database connection, 
you will need to specify the connection information in the `application.properties` file:

	...\src\main\resources\application.properties

	url=		jdbc:oracle:oci:@//localhost:1521/mydb
	username=	sa
	password=	sa

## 3) How to Deploy?
use [maven](https://spring.io/guides/gs/maven/) to compile and run the project.

You can simply use the command to (_clean, build, test and_) run the project with a single command: 


	mvn package

## 4) What are the Contribution guidelines?

#### 4.1) Writing tests.

_There is a sample of Unit testing, Integration testing, Functional testing and a non-spring Testing._

#### 4.2) Code review.

_Request if needed._

#### 4.3) Other guidelines.

_Please ask for the code standard to use as guideline and reflect it in the project._

## 5) Who do I talk to?

<table>
<thead><tr><th><b>Role</b></th> <th><b>Contact</b></th></tr></thead>
<tr><td>Owner/admin</td><td>current main developer: <a href='mailto:obed.vazquez@gmail.com'>obed.vazquez@gmail.com</a></td></tr>
<tr><td>Supporters</td><td>we have supporters with knowledge on the setup process of the project only</td></tr>
<tr><td>Community</td><td> send us a message in <a href='http://discord.whiteweb.tech'> our Discord Server</a></td></tr>
</table>

>Please contact me if you want to help, I'm developing and maintaining and supporting in general this project 
on my own with no help or support of anyone and any tip, comment, change or help in general is well-received.

