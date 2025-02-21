package com.veeam.assignment.dimitris.nikolaidis.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@ComponentScan( basePackages = {
        "com.veeam.assignment.dimitris.nikolaidis.utils",
        "com.veeam.assignment.dimitris.nikolaidis.models",
        "com.veeam.assignment.dimitris.nikolaidis.configuration"
})
public class SpringContext {

}
