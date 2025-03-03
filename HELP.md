# Execution tutorial

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


