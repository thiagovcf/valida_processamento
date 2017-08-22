package br.com.marketpay.validaapp.core.pages.cardsprivate.contas;

import lombok.extern.log4j.Log4j;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.marketpay.validaapp.core.pages.common.CardsPage;

@Log4j
public class PageVisualizarOcorrenciaRetaguarda extends CardsPage<PageVisualizarOcorrenciaRetaguarda>{

	public PageVisualizarOcorrenciaRetaguarda(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".msgErro")
	WebElement erroSistema;
	
	@FindBy(name = "tipoOrigem")
	WebElement comboTipoOcorrencia;
	
	@FindBy(css = "input[title='Clique aqui para filtrar']")
	WebElement botaoFiltrar;
	
	@FindBy(css = "img[title='Visualizar']")
	WebElement visualizarPreenchimentoOcorrencia;
	
	@FindBy(css = "textarea[name='descricaoOcorrenciaRead']")
	WebElement campoPreenchimentoOcorrencia;
		
	public void verificaOcorrencia(){
		aguardarElementoVisivel(comboTipoOcorrencia);
		selectComboValue(comboTipoOcorrencia, "T");
		botaoFiltrar.click();
		verificaPreenchimentoOcorrencia();
	}
	
	public void verificaPreenchimentoOcorrencia(){
		if (isElementoDisplayed(visualizarPreenchimentoOcorrencia)) {
			visualizarPreenchimentoOcorrencia.click();
			if(campoPreenchimentoOcorrencia.getAttribute("value") == null || campoPreenchimentoOcorrencia.getAttribute("value").isEmpty()){
				log.error("Houve ocorrência e não há preenchimento.");
			} 
		}
	}
	
	
}
