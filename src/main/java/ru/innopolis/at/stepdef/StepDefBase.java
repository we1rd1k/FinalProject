package ru.innopolis.at.stepdef;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.innopolis.at.ui.Props;

import java.util.Map;

@Slf4j
public class StepDefBase {

    static Props props = ConfigFactory.create(Props.class);

    @Before
    public static void init() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true));
        if (!StringUtils.isBlank(props.selenoidUrl())) {
            log.info("Selenoid Url" + props.selenoidUrl());
            Configuration.remote = props.selenoidUrl();
        }
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "89.0");
        Map<String, Object> value = new java.util.HashMap<>();
        value.put("enableVNC", true);
        value.put("enableVideo", true);
        capabilities.setCapability("selenoid:options", value);
        Configuration.browserCapabilities = capabilities;
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.startMaximized = true;
    }

    @After
    public void afterTest() {
        Selenide.closeWebDriver();
    }
}
