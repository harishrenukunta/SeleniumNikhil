package com.testsuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.SeleniumBasic.DriverMethodsTest;
import com.webelements.WebElementMethodsTest;

@RunWith(Suite.class)
@SuiteClasses({ DriverMethodsTest.class, WebElementMethodsTest.class })
public class AllTests {

}
