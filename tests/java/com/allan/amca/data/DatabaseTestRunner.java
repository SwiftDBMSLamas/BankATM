package com.allan.amca.data;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "html:target/Destination"},
        features = {"src/cucumber/feature/"})
public class DatabaseTestRunner {

}
