package com.veeam.assessment.dimitris.nikolaidis.stepDefinitions;

import com.veeam.assessment.dimitris.nikolaidis.configuration.SpringContext;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = {SpringContext.class})
public class TestContextConfiguration {
}
