package com.xiaozhuge.springbootldap;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * @author liyinlong
 * @since 2022/8/22 9:45 上午
 */
public class SemTest {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","/Users/liyinlong/chromedriver");
        ChromeOptions options = new ChromeOptions();
        ChromeDriver chromeDriver = new ChromeDriver(options);
        chromeDriver.quit();

    }
}