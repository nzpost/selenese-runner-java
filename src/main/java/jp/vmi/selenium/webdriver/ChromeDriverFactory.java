package jp.vmi.selenium.webdriver;

import java.io.File;
import java.util.List;

import org.apache.commons.exec.OS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import jp.vmi.selenium.selenese.utils.PathUtils;

import static jp.vmi.selenium.webdriver.DriverOptions.DriverOption.*;

/**
 * Factory of {@link ChromeDriver}.
 *
 * @see <a href="http://code.google.com/p/chromedriver/">chromedriver - WebDriver for Google Chrome</a>
 */
public class ChromeDriverFactory extends WebDriverFactory {

    @Override
    public WebDriver newInstance(DriverOptions driverOptions) {
        ChromeDriverService.Builder builder = new ChromeDriverService.Builder();
        if (driverOptions.has(CHROMEDRIVER)) {
            builder = builder.usingDriverExecutable(new File(driverOptions.get(CHROMEDRIVER)));
        } else {
            List<File> drivers;
            if (OS.isFamilyWindows()) {
                drivers = PathUtils.searchExecutableFile("chromedriver.exe");
            } else {
                drivers = PathUtils.searchExecutableFile("chromedriver");
            }

            if (drivers.isEmpty()) {
                throw new IllegalStateException("No chromedriver");
            }

            builder = builder.usingDriverExecutable(drivers.get(0));
        }
        builder = builder.usingAnyFreePort().withEnvironment(getEnvironmentVariables());
        ChromeDriverService service = builder.build();

        // new ChromeDriver(Capabilities) is deprecated...
        ChromeOptions options = new ChromeOptions();
        if (driverOptions.has(PROXY))
            options.addArguments("--proxy-server=http://" + driverOptions.get(PROXY));
        return new ChromeDriver(service, options);
    }
}
