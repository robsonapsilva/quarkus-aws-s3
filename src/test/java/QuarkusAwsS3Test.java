import io.quarkiverse.cucumber.CucumberOptions;
import io.quarkiverse.cucumber.CucumberQuarkusTest;

@CucumberOptions(features = "classpath:features",
        plugin = {"pretty", "json:target/cucumber-report.json"})
public class QuarkusAwsS3Test extends CucumberQuarkusTest {
}
