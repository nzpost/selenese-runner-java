package jp.vmi.selenium.selenese.command;

import org.apache.commons.lang3.StringUtils;

import jp.vmi.selenium.selenese.TestCase;
import jp.vmi.selenium.selenese.result.Result;
import jp.vmi.selenium.selenese.result.Success;
import jp.vmi.selenium.selenese.result.Warning;

import static jp.vmi.selenium.selenese.result.Success.*;

/**
 * Command "captureEntirePageScreenshot".
 */
public class CaptureEntirePageScreenshot extends Command {

    private static final int FILENAME = 0;

    CaptureEntirePageScreenshot(int index, String name, String[] args, String realName, boolean andWait) {
        super(index, name, args, 1);
    }

    @Override
    public boolean canUpdate() {
        return false;
    }

    @Override
    public Result doCommand(TestCase testCase) {
        String filename = args[FILENAME];
        if (testCase.getRunner().isIgnoreScreenshotCommand())
            return new Success("captureEntirePageScreenshot is ignored");
        if (StringUtils.isBlank(filename))
            return new Warning("captureEntirePageScreenshot is ignored: empty filename.");
        try {
            testCase.getRunner().takeScreenshot(filename, testCase);
            return SUCCESS;
        } catch (UnsupportedOperationException e) {
            return new Warning(e.getMessage());
        }
    }
}
