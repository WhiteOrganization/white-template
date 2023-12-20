# Application Template
This is a template for you to start working within a java environment. _Substitute this description for a description of your application_.
**_Please feel free to add new sections or delete not used sections_**.

## 1) What is this repository for?

### 1.1) Quick summary
Version: `0.1-SNAPSHOT`

Detailed description of your app.

### 1.2) Disclosure
_We hereby certify that, to the best of our knowledge,
neither we nor any individual or entity with whom or which I have a significant working
relationship have (has) received something of value from a commercial party related directly or
indirectly to the subject of this project..._

## 2) How do I get set up? ###

### 2.1) Summary of set up
The technologies the project is using.

#### 2.1.1) Development environment
- DataBase:			                    `H2` (_embedded_)
- [JDK](https://openjdk.org/) version: 	`1.17`
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/products/docker-desktop/)

#### 2.1.2) Project Dependencies
This library uses:
- **lombok** to log errors and general logs.
    - **Slf4j**
- **Spring boot**
  - **Spring 6**
    - **Reactive Spring Web-flux WebClient**. for Reactive REST Calls.
    - Spring > **Jackson**
    - **JKube** A Kubernetes Maven plugin to generate Docker Images.
  - **JUnit5**
    - **Selenium-Jupiter**
- **White_SeleniumFramework**. Utilities and Base Automation Scenario
- **Hibernate**
- **H2 DataBase**


### 2.2) Configuration Steps
#### 2.2.1) Environment Configuration
_Please execute the `main-protection-win.bat` file in the root directory of the project
to protect the main branch from being corrupted unintentionally._

You will require all the Development elements in your environment.

An IDE with maven support is suggested for you to make any modifications to the code.
#### 2.2.2) Database configuration

You don't need to set up a DataBase for the project to connect to.
There is an H2 database already embedded in the project, but if you want to
you can configure a separate one and switch the database connection, 
you will need to specify the connection information in the `application.properties` file:

	...\src\main\resources\application.properties

	url=		jdbc:oracle:oci:@//localhost:1521/mydb
	username=	sa
	password=	sa

## 3) How to Deploy?
use [maven](https://spring.io/guides/gs/maven/) to compile ~~and run~~(_you can configure the auto launch of a desktop app_) the project.

You can simply use this to (_clean, build, test and_) **`RUN`** the project with a single command: 
    
    mvn verify

Maven is configured to create the docker image on the package goal, so you can create the image using:

	mvn package k8s:build
After that, you can run the image (and project) by running the image in docker with

    docker compose --project-directory src\main\docker up
This will create a docker image and run the project.

    mvn clean package k8s:build -e
    docker compose --project-directory src\main\docker up
    .

Maven is also configured to run the Docker Image on its own on the **`VERIFY`** phase/goal 
after the creation of the image on the package goal. Use this for as a quick go-to command: 
    
    mvn clean verify -DskipTests

If the project is running correctly
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

