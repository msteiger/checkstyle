package com.puppycrawl.tools.checkstyle.checks.coding;

import com.puppycrawl.tools.checkstyle.BaseCheckTestCase;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;

import java.io.File;
public class ReturnCountCheckTest extends BaseCheckTestCase
{
    public void testDefault() throws Exception
    {
        final DefaultConfiguration checkConfig =
            createCheckConfig(ReturnCountCheck.class);
        final String[] expected = {
            "18:5: Return count is 7 (max allowed is 2).",
            "35:17: Return count is 6 (max allowed is 2).",
        };
        verify(checkConfig, getPath("coding" + File.separator + "InputReturnCount.java"), expected);
    }
}