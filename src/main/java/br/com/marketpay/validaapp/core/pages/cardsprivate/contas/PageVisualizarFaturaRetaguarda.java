package br.com.marketpay.validaapp.core.pages.cardsprivate.contas;

import lombok.extern.log4j.Log4j;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.marketpay.validaapp.core.pages.common.CardsPage;

@Log4j
public class PageVisualizarFaturaRetaguarda extends CardsPage<PageVisualizarFaturaRetaguarda>{

	public PageVisualizarFaturaRetaguarda(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".msgErro")
	WebElement erroSistema;
	
	@FindBy(linkText = ">Total Fatura Anterior:")
	WebElement totalFaturaAnterior;
	
	@FindBy(id = "mudaClass3")
	WebElement detalheValorFatura;
	
	@FindBy(linkText = ">Rolado:")
	WebElement rolado;
	
	@FindBy(id = "mudaClass4")
	WebElement detalheSaldoCartao;
	
	@FindBy(linkText = ">Saldo Disponível:")
	WebElement saldoDisponivel;
	
	@FindBy(id = "mudaClass5")
	WebElement detalheEstorno;
	
	@FindBy(css = "input[title='Clique aqui para confirmar']")
	WebElement botaoConfirmarEstorno;
	
	public void verificaDetalhamentoFatura(){
		verificaCamposResumoFatura();
		verificaCamposDetalheFatura();
		verificarCampoSaldoCartao();
		if (isElementoDisplayed(detalheEstorno)) {
			verificaCamposEstorno();
		}
		voltarPagina();
	}
	
	public void verificaCamposResumoFatura(){
		try {
			isElementoDisplayed(totalFaturaAnterior);
		} catch (Exception e) {
			log.error("Campo \"Total Fatura Anterior\" não foi encontrado.");
		}
	}
	
	public void verificaCamposDetalheFatura(){
		detalheValorFatura.click();
		try {
			isElementoDisplayed(rolado);
		} catch (Exception e) {
			log.error("Novo campo \"Rolado\" não foi encontrado ou não contém valor.");
		}
	}
	
	public void verificarCampoSaldoCartao(){
		click(detalheSaldoCartao);
		try {
			isElementoDisplayed(saldoDisponivel);
		} catch (Exception e) {
			log.error("Campo \"Saldo Disponível\" não foi encontrado.");
		}
	}
	
	public void verificaCamposEstorno(){
		click(detalheEstorno);
		try {
			isElementoDisplayed(botaoConfirmarEstorno);
		} catch (Exception e) {
			log.error("Botão para confirmação de estorno não foi encontrado.");
		}
	}
	
}
