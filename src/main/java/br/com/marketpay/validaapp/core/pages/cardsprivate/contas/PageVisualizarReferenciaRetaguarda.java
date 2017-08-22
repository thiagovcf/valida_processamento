package br.com.marketpay.validaapp.core.pages.cardsprivate.contas;

import lombok.extern.log4j.Log4j;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.marketpay.validaapp.core.pages.common.CardsPage;

@Log4j
public class PageVisualizarReferenciaRetaguarda extends CardsPage<PageVisualizarReferenciaRetaguarda>{

	public PageVisualizarReferenciaRetaguarda(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".msgErro")
	WebElement erroSistema;
	
	@FindBy(xpath = ".//*[@id='idRelatorio']/table/tbody/tr[1]/td[7]/a/img")
	WebElement visualizarPreenchimentoReferencia;
	
	@FindBy(id = "descricaoOcorrenciaRead")
	WebElement campoPreenchimentoReferencia;
	
	@FindBy(xpath = ".//*[@id='idRelatorio']/table/tbody/tr[1]/td[1]")
	WebElement nomeReferenciaPreenchido;
	
	@FindBy(id = "nomeReferencia")
	WebElement nomeReferenciaConferido;
		
	public void verificaReferencia(){
		if(isElementoDisplayed(visualizarPreenchimentoReferencia)){
			visualizarPreenchimentoReferencia.click();
			verificaPreenchimentoReferencia(nomeReferenciaPreenchido.getText());
		}
	}
	
	public void verificaPreenchimentoReferencia(String nomeReferencia){
		aguardarElementoVisivel(nomeReferenciaConferido);
		if(!nomeReferenciaConferido.getAttribute("value").equals(nomeReferencia)){
			log.error("Visualizações do nome da referência não estão coincidindo.");
		}
	}
	
	
}
