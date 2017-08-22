//package br.com.marketpay.validaapp.core;
//
//import java.io.File;
//
//import lombok.extern.log4j.Log4j;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebDriverException;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriverService;
//import org.openqa.selenium.remote.DesiredCapabilities;
//
//@Log4j
//public class Selenium {
//
//	private WebDriver driver = null;
//	PhantomJSDriverService phantomservice = null;
//	String phantomPathDriver;
//
//	public WebDriver getDriver() {
//		if (driver == null) {
//			try {
//				phantomPathDriver = new File("").getAbsolutePath()+"/src/main/resources/driver/phantomjs";
//				DesiredCapabilities capabilities = new DesiredCapabilities();
//				capabilities.setJavascriptEnabled(true);
//				capabilities.setCapability("takesScreenshot", true);
//				capabilities.setCapability(
//								PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
//								phantomPathDriver);
//				
//				capabilities.setBrowserName("phantomjs");
//
//				phantomservice = new PhantomJSDriverService.Builder()
//						.usingPhantomJSExecutable(new File(phantomPathDriver))
//						.usingAnyFreePort().build();
//
//				driver = new PhantomJSDriver(phantomservice, capabilities);
////				System.setProperty("webdriver.gecko.driver", new File("").getAbsolutePath()+"/src/main/resources/driver/geckodriver");
////				driver = new FirefoxDriver();
//			} catch (WebDriverException e) {
//				log.error("Erro no carregamento do driver.");
//				e.printStackTrace();
//			}
//		}
//		return driver;
//	}
//	public void resetDriver() {
//		if(driver != null){
//			driver.close();
//		}
//		if(phantomservice != null){
//			phantomservice.stop();
//		}
//		driver = null;
//	}
//}
