package jp.vmi.selenium.selenese;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * Test for {@link TestCaseParser}.
 */
public class TestCaseParserTest {

    /**
     * Test of empty file.
     *
     * @throws IOException exception.
     */
    @Test
    public void emptyFile() throws IOException {
        Runner runner = new Runner();
        runner.setDriver(new HtmlUnitDriver(true));
        String scriptFile = TestUtils.getScriptFile(CommandRunnerTest.class, "Simple");
        TestCase testCase = (TestCase) Parser.parse(new File(scriptFile), runner);
        testCase.execute(null);
    }
}
