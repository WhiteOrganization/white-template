package org.white_sdev.templates.white_template;

import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@lombok.extern.slf4j.Slf4j
@SelectPackages("org.white_sdev.templates.white_template")
//@RunWith(JUnitPlatform.class)
@Suite
@IncludeClassNamePatterns({"^.*IT?$","^.*Test[s]?$"})
public class WhiteTemplateSuite {

}