package br.com.marketpay.validaapp.core.pages.cardsprivate.movimentos;

import lombok.extern.log4j.Log4j;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.marketpay.validaapp.core.pages.common.CardsPage;

@Log4j
public class PageMovimentoPagamentosCards extends CardsPage<PageMovimentoPagamentosCards>{

	public PageMovimentoPagamentosCards(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "botao2")
	WebElement filtro;
	
	@FindBy(id = "faturaFiltro")
	WebElement inputNumeroFatura;
					  
	@FindBy(css = "img[title='Selecionar']")
	WebElement selecionarConta;
	
	@FindBy(id = "dataPagamentoFiltro")
	WebElement inputDataIncial;
	
	@FindBy(id = "data2PagamentoFiltro")
	WebElement inputDataFinal;
	
	@FindBy(css = ".dhtmlxcalendar_month_label_year")
	WebElement ano;
	
	@FindBy(xpath = "html/body/div[5]/div[6]/table/tbody/tr/td[2]/div/ul[1]/li[1]")
	WebElement ano2016;
	
	@FindBy(xpath = "html/body/div[5]/div[3]/ul[1]/li[3]")
	WebElement dia1;
	
	@FindBy(css = "input[value='Filtrar']")
	WebElement botaoFiltrar;
	
	@FindBy(css = "img[title='Visualizar']")
	WebElement visualizarConta;
	
	@FindBy(css = ".msgErro")
	WebElement msgErro;
	
	public void listarPagamentoComSucesso(String numeroFatura){
		preencherCampo(inputNumeroFatura, numeroFatura);
		click(botaoFiltrar);
		verificaRetornoFiltroPagamentos(numeroFatura);
	}
	
	public void verificaRetornoFiltroPagamentos(String botaoFiltrar){
		if (isElementoDisplayed(msgErro)) {
			log.error("Movimentos >> Pagamentos >> Listar >> Conta: "+botaoFiltrar);
		}else
		if (!isElementoDisplayed(visualizarConta)) {
			log.info("Conta ["+botaoFiltrar+"] sem pagamentos realizados nos últimos 365 dias.");
		}
	}
}
