package org.automation;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(dryRun=true, glue= "org/automation/steps", features = "src/test/features/test1.feature")
public class TestRunner {

}
