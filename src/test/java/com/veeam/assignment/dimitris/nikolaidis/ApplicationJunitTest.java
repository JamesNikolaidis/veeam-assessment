package com.veeam.assignment.dimitris.nikolaidis;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/main/resources/features",
		glue = {"com.veeam.assignment.dimitris.nikolaidis.stepDefinitions"},
		plugin = {
				"pretty",
				"html:target/reports/cucumber/cucumber.html",
				"json:target/report.json",
				"junit:target/junit.xml"
		}
)
class ApplicationJunitTest {

}
