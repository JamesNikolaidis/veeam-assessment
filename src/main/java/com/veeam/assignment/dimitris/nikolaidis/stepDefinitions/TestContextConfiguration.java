package com.veeam.assignment.dimitris.nikolaidis.stepDefinitions;

import com.veeam.assignment.dimitris.nikolaidis.configuration.SpringContext;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = {SpringContext.class})
public class TestContextConfiguration {
}
