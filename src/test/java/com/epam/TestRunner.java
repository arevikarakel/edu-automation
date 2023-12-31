package com.epam;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src\\test\\resources\\features\\4profiles\\upload_image.feature",
        glue = "com\\epam\\steps",
        publish = true)
public class TestRunner {
}
