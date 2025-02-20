package com.veeam.assessment.dimitris.nikolaidis.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@ComponentScan( basePackages = {
        "com.veeam.assessment.dimitris.nikolaidis.utils",
        "com.veeam.assessment.dimitris.nikolaidis.models",
        "com.veeam.assessment.dimitris.nikolaidis.configuration"
})
public class SpringContext {

}
