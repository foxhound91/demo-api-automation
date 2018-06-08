import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features"
        , glue = {"steps"}
        , monochrome = true
        , plugin = {"pretty", "html:target/html/", "json:target/reports.json"}
)
public class TestRunner {
}
