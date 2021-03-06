////////////////////////////////////////////////////////////////////////////////
// checkstyle: Checks Java source code for adherence to a set of rules.
// Copyright (C) 2001-2015 the original author or authors.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
////////////////////////////////////////////////////////////////////////////////

package com.puppycrawl.tools.checkstyle.checks.coding;

import java.io.File;

import org.junit.Test;

import com.puppycrawl.tools.checkstyle.BaseCheckTestSupport;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;

import static com.puppycrawl.tools.checkstyle.checks.coding.IllegalCatchCheck.MSG_KEY;

public class IllegalCatchCheckTest extends BaseCheckTestSupport
{
    @Test
    public void testDefault() throws Exception
    {
        DefaultConfiguration checkConfig = createCheckConfig(IllegalCatchCheck.class);

        String[] expected = {
            "6:11: " + getCheckMessage(MSG_KEY, "RuntimeException"),
            "7:11: " + getCheckMessage(MSG_KEY, "Exception"),
            "8:11: " + getCheckMessage(MSG_KEY, "Throwable"),
            "14:11: " + getCheckMessage(MSG_KEY, "java.lang.RuntimeException"),
            "15:11: " + getCheckMessage(MSG_KEY, "java.lang.Exception"),
            "16:11: " + getCheckMessage(MSG_KEY, "java.lang.Throwable"),
        };

        verify(checkConfig, getPath("coding" + File.separator + "InputIllegalCatchCheck.java"), expected);
    }

    @Test
    public void testIllegalClassNames() throws Exception
    {
        DefaultConfiguration checkConfig = createCheckConfig(IllegalCatchCheck.class);
        checkConfig.addAttribute("illegalClassNames",
                                 "java.lang.Error, java.lang.Exception, java.lang.Throwable");

        String[] expected = {
            "7:11: " + getCheckMessage(MSG_KEY, "Exception"),
            "8:11: " + getCheckMessage(MSG_KEY, "Throwable"),
            "15:11: " + getCheckMessage(MSG_KEY, "java.lang.Exception"),
            "16:11: " + getCheckMessage(MSG_KEY, "java.lang.Throwable"),
        };

        verify(checkConfig, getPath("coding" + File.separator + "InputIllegalCatchCheck.java"), expected);
    }

    @Test
    public void testMultipleTypes() throws Exception
    {
        DefaultConfiguration checkConfig = createCheckConfig(IllegalCatchCheck.class);

        String[] expected = {
            "7:11: " + getCheckMessage(MSG_KEY, "RuntimeException"),
            "10:11: " + getCheckMessage(MSG_KEY, "RuntimeException"),
            "13:11: " + getCheckMessage(MSG_KEY, "RuntimeException"),
            "16:11: " + getCheckMessage(MSG_KEY, "RuntimeException"),
        };

        verify(checkConfig, getPath("coding" + File.separator + "InputIllegalCatchCheck2.java"), expected);
    }
}
