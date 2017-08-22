package br.com.marketpay.validaapp.core.pages.common;

import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.marketpay.validaapp.entity.Aplicacao;


/**
 * 
 * @author CONDUCTOR\thiago.freire
 * @param <T>
 */
public abstract class CardsPage<T> {

	private static final String BASE_URL = "http://teste.marketpay.com.br/tcartaoconfianca/";
	private static final int LOAD_TIMEOUT = 10;
	private String windowHandleJanelaInicial;
	
	protected WebDriver driver;
	protected Aplicacao aplicacao;
	
	
	public CardsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public T openPageRetaguarda(Class<T> clazz){
		T page = PageFactory.initElements(driver, clazz);
		driver.get(BASE_URL + getUrl());
		return page;
	}

	public String getUrl() {
		return "";
	}
	
	public void preencherCampo(WebElement element, CharSequence... keysToSend){
		try {
			aguardarElementoVisivel(element);
			element.clear();
			element.sendKeys(keysToSend);
		} catch (WebDriverException e) {
		}
	}
	public void click(WebElement element){
		try {
			aguardarElementoVisivel(element);
			element.click();
		} catch (Exception e) {
		}
	}
	
	public String getValorAtributo(WebElement element){
		return element.getAttribute("value");
	}
	
	public void selectElementByVisibleText(WebElement element, String textVisible){
		try{
			new Select(element).selectByVisibleText(textVisible);
		}catch(NoSuchElementException e){
			Assert.fail("Erro ao selecionar no elemento: ["+element.getTagName()+ "] com o o valor: "+textVisible);
		}
	}
	public void selectElementByVisibleValeu(WebElement element, String valueVisibel){
		try{
			new Select(element).selectByValue(valueVisibel);
		}catch(NoSuchElementException e){
			Assert.fail("Erro ao selecionar no elemento: ["+element.getTagName()+ "] com o o valor: "+valueVisibel);
		}
	}
	public void acceptAlert(){
		try {
	        Alert alert = driver.switchTo().alert();
	        alert.accept();
	    } catch (Exception e) {
	    	Assert.fail("Erro ao realizar a confirma��o do Alerta");
	    }
	}
	public String getAlert(){
		String alerta="";
		try {
			Alert alert = driver.switchTo().alert();
			alerta = alert.getText();
		} catch (Exception e) {
		}
		return alerta;
	}
	public void aguardarElementoVisivel(WebElement element){
		try {
			WebDriverWait driverWait = new WebDriverWait(driver, LOAD_TIMEOUT);
			driverWait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			Assert.fail("Tempo excedido para aguardar elemento: "+element);
		}
	}
	public void aguardarElementoVisivelComTexto(WebElement element, String text){
		try {
			WebDriverWait driverWait = new WebDriverWait(driver, LOAD_TIMEOUT);
			driverWait.until(ExpectedConditions.textToBePresentInElement(element, text));
		} catch (Exception e) {
			Assert.fail("Tempo excedido para aguardar elemento: "+element);
		}
	}
	public boolean isElementoDisplayed(WebElement elemento) {
		try{
			return elemento.isDisplayed();	
		}catch (NoSuchElementException e){
			return false;
		}
	}
	public boolean isElementVisibility(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
	public void selectComboValue(WebElement element, final String value) {
	    Select selectBox = new Select(element);
	    selectBox.selectByValue(value);
	}
	public void clicarBotaoDireito(WebElement elemento){
		Actions action = new Actions(driver);
		action.contextClick(elemento).build().perform();
	}
	
	public void moverCursorPara(WebElement elemento){
		Actions action = new Actions(driver);
		action.moveToElement(elemento).build().perform();
	}
	
	public boolean existText(WebElement elemento, String texto) {
		aguardarElementoVisivel(elemento);
		return elemento.getText().contains(texto);
	}
	
	public void voltarPagina(){
		driver.navigate().back();
	}
	
	/**
	 * Seta para nova janela aberta
	 */
	public void alternarJanela() {
		windowHandleJanelaInicial = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		for (String windowHandle : windowHandles) {
			if (!windowHandle.equals(windowHandleJanelaInicial)) {
				driver.switchTo().window(windowHandle);
			}
		}
		setWindowHandleJanelaInicial(windowHandleJanelaInicial);
	}

	public String getWindowHandleJanelaInicial() {
		return windowHandleJanelaInicial;
	}

	public void setWindowHandleJanelaInicial(String windowHandleJanelaInicial) {
		this.windowHandleJanelaInicial = windowHandleJanelaInicial;
	}
	
	public void alertaSaidaDoSistema() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	/**
	 * 
	 * @return Janela anterior
	 */
	public void retonarJanelaOriginal(){
		driver.switchTo().window(getWindowHandleJanelaInicial());
	}
	
	
	public WebElement getElement(By by){
		return driver.findElement(by);
	}
	
}