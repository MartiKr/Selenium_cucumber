package CucumberOptions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/",
        glue = "StepDefinitions",
        plugin = ("pretty")
)
public class TestRunner {
}
