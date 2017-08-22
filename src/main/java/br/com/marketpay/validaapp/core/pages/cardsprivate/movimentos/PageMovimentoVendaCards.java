package br.com.marketpay.validaapp.core.pages.cardsprivate.movimentos;

import lombok.extern.log4j.Log4j;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.marketpay.validaapp.core.pages.common.CardsPage;

@Log4j
public class PageMovimentoVendaCards extends CardsPage<PageMovimentoVendaCards>{

	public PageMovimentoVendaCards(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "botao2")
	WebElement filtro;
	
	@FindBy(id = "dataVendaFiltro")
	WebElement inputDataIncial;
	
	@FindBy(id = "data2VendaFiltro")
	WebElement inputDataFinal;
	
	@FindBy(id = "documentoFiltro")
	WebElement inputNumeroOrcamento;
	
	@FindBy(id = "codigoFiltro")
	WebElement campoFiltro;
	                    
	@FindBy(css = "img[title='Selecionar']")
	WebElement selecionarConta;
	
	@FindBy(css = "input[value='Filtrar']")
	WebElement botaoFiltrar;

	@FindBy(css = "img[title='Visualizar']")
	WebElement visualizarConta;
	
	@FindBy(css = ".msgErro")
	WebElement msgErro;
	
	public void filtrarVendaNumeroOrcamento(CharSequence numeroDocumento){
		preencherCampo(inputNumeroOrcamento, numeroDocumento);
		click(botaoFiltrar);
	}
	
	public void verificaRetornoFiltroVenda(String codigoPessoaFisica){
		if (isElementoDisplayed(msgErro)) {
			log.error("Movimentos >> Vendas >> Listar >> Conta: "+codigoPessoaFisica);
		}else
		if (!isElementoDisplayed(visualizarConta)) {
			log.info("Conta ["+codigoPessoaFisica+"] sem compras realizadas nos últimos 365 dias.");
		}
	}
}
