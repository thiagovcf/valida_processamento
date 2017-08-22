package br.com.marketpay.validaapp.core.pages.cardsprivate.contas;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.marketpay.validaapp.core.pages.common.CardsPage;

public class PageListarContas extends CardsPage<PageListarContas>{
	
	public PageListarContas(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "codigoFiltro")
	WebElement codigoPessoaFisicaPesquisar;
	
	@FindBy(xpath = ".//*[@value='Filtrar']")
	WebElement botaoFiltrar;
	
	@FindBy(xpath = ".//*[@id='idRelatorio']/table/tbody/tr/td[1]/span")
	WebElement codClienteListado;
	
	@FindBy(xpath = ".//*[@title='Visualizar']")
	WebElement botaoVisualizar;
	
	@FindBy(css = "img[title='Visualizar']")
	WebElement botaoSelencionaContaAtiva;
	
	public void listarContasCodigoPessoaFisica(String codigoPessoafisicaTeste){
		preencherCampo(codigoPessoaFisicaPesquisar, codigoPessoafisicaTeste);
		click(botaoFiltrar);
	}

	public void aguardarExibirContaListada(String codigoPessoafisica) {
		aguardarElementoVisivel(botaoVisualizar);
	}
	
	public void clickVisualizarDadosConta(){
		click(botaoVisualizar);
	}
	public void clickSelecionarContaAtiva(){
		click(botaoSelencionaContaAtiva);
	}

	public void listarContaComSucesso(String codigoPessoafisicaTeste){
		listarContasCodigoPessoaFisica(codigoPessoafisicaTeste);
		aguardarExibirContaListada(codigoPessoafisicaTeste);
		clickSelecionarContaAtiva();
	}
}
