# Execution tutorial

### Run requirements 

<ul>
  <li>Linux , Windows or Mac OS</li>
  <li>Bash terminal which allows run-tests.sh to run ( available if download the git tool )</li>
  <li>Java 17 jdk installed</li>
  <li>Maven dependency management</li>
</ul>

### Project Structure
Since the implementation made using Spring boot , the project structure may be different that a Java - Junit project.
Starting, the java code is located in the path 

<code>https://github.com/JamesNikolaidis/veeam-assessment/tree/main/src/main/java/....</code>

The test scenarios are location under the main directory and specifically here, 

<a>https://github.com/JamesNikolaidis/veeam-assessment/tree/main/src/main/resources/features</a> 

along with the spring boot properties.
The test-suite runner is located in the test directory here, 

<a>https://github.com/JamesNikolaidis/veeam-assessment/tree/main/src/test/java/com/veeam/assessment/dimitris/nikolaidis</a> 

### Before test execution !IMPORTANT! 
Please, when you download this repo, run the following commands 

<code> mvn clean compile </code> 

or 

<code> mvn clean </code>

<code> mvn compile </code> 

Those steps are required because when downloading the project from github does not have the compiled files ready to run the test suite. 

### Test suite Configuration
The configuration for the test suite can be modified and found in the application.properties file under the resources folder.
It contains the endpoints and other configuration.

### Execute test framework through terminal using pre-defined configuration
Run the test suite with the following command

* mvn clean test


### Execute test framework with arguments through terminal
Run the test suite with the following command

<code> mvn clean test -Dname_of_property=value</code>

example

<code> mvn clean test -Dapplication.url=https://a_new_test_url.com</code>

The available properties can be found here <a>https://github.com/JamesNikolaidis/veeam-assessment/blob/main/src/main/resources/application.properties</a>


### Execute test framework using the run-test.sh file
Run the test suite by executing the run-test.sh file that exists in the root of this project.
This execution type does not provide the flexibility to run the tests using custom property configuration
The execution will use the default configuration found in the application.properties file. 

To run the run-test.sh script, run the following command 

<code> ./run-test.sh</code> 

### Execute test framework using the run-test.sh file and custom property configuration
Run the test suite by executing the run-test.sh file that exists in the root of this project.
We can add your own property configuration ( based on the existing properties in application.properties file).

to run the run-test.sh script with your own property configuration , run the following command

<code> ./run-test.sh "-DfirstProperty=test -DsecondProperty=test1 -D...."     </code> 

### Access Test Reports
The reports of the test suite exeuction is located , through different formats in the directories
<ul>
  <li>Cucumber report => target/reports/cucumber</li>
  <li>XML report => target/junit.xml</li>
  <li>JSON report => target/report.json</li>
</ul>


