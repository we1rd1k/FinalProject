package ru.innopolis.at.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "ru/innopolis/at/stepdef",
        plugin = "pretty"
)
public class TestRunner {
}
