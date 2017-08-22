package br.com.marketpay.validaapp.core.pages.cardsprivate.contas;

import static br.com.marketpay.validaapp.core.util.Assert.assertEquals;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.marketpay.validaapp.core.pages.common.CardsPage;


public class PageVisualizarContaRetaguarda extends CardsPage<PageVisualizarContaRetaguarda> {

	public PageVisualizarContaRetaguarda(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input[value='Inform. Pessoais']")
	WebElement infPessoais;
	
	@FindBy(css = "input[value='Endereço']")
	WebElement endereco;
	
	@FindBy(css = "input[value='Cônjuge/Depend.']")
	WebElement depend;

	@FindBy(css = "input[value='Compl. de Renda']")
	WebElement renda;
	
	@FindBy(css = "input[value='Cart. Adicionais']")
	WebElement cartoesAdicionais;
	
	@FindBy(css = "input[value='Ocorrências']")
	WebElement ocorrencias;
	
	@FindBy(css = "input[value='Referências']")
	WebElement referencias;
	
	@FindBy(css = "input[value='Outras Inform.']")
	WebElement outraInf;
	
	@FindBy(css = "input[value='Créd. e Bloqueios']")
	WebElement creditosBloqueios;
	
	@FindBy(css = "input[value='Cartões']")
	WebElement cartao;
	
	@FindBy(css = "input[value='Fat. e Movimentos']")
	WebElement fatura;
	
	@FindBy(css = "input[value='Taxas e Seguros']")
	WebElement taxaSeguros;
	
	@FindBy(css = "input[value='Taxas']")
	WebElement taxas;
	
	@FindBy(css = "input[value='Seguros']")
	WebElement seguros;
	
	@FindBy(css = "input[value='Info. Proposta']")
	WebElement infoProposta;
	
	@FindBy(css = "input[value='Serviços de SMS']")
	WebElement sms;
	
	@FindBy(css = "img[src='images/botao/botaoLogoff.gif']")
	WebElement botaoSair;
	
	@FindBy(css = ".textTitle")
	WebElement labelTitle;
	
	public void validarCliente() {
		click(infPessoais);
		assertEquals("Informações Pessoais", labelTitle.getText());
		click(endereco);
		assertEquals("Endereços", labelTitle.getText());
		click(depend);
		assertEquals("Cônjuge e Dependentes", labelTitle.getText());
		click(renda);
		assertEquals("Complemento de Renda", labelTitle.getText());
		click(cartoesAdicionais);
		assertEquals("Cartões Adicionais", labelTitle.getText());
		click(outraInf);
		assertEquals("Cartões Outras Informações", labelTitle.getText());
		click(creditosBloqueios);
		assertEquals("Crédito e Bloqueio", labelTitle.getText());
		click(cartao);
		assertEquals("Cartões", labelTitle.getText());
		
		if(isElementoDisplayed(taxaSeguros)){
			click(taxaSeguros);
			assertEquals("Taxas e Seguros", labelTitle.getText());
		} else 
			if (isElementoDisplayed(taxas)){
				click(taxas);
				assertEquals("Taxas e Seguros", labelTitle.getText());
				click(seguros);
				assertEquals("Seguros", labelTitle.getText());
			}
		if (isElementoDisplayed(infoProposta)){
			click(infoProposta);
			assertEquals("Propostas", labelTitle.getText());
		}
		if (isElementoDisplayed(sms)){
			click(infoProposta);
			assertEquals("Serviços de SMS", labelTitle.getText());
		}
	}
	
	public void validaDetalheFatura(){
		click(fatura);
		assertEquals("Faturas e Movimentos", labelTitle.getText());
		selecionarFaturaAtiva();
	}
	
	public void validaDetalheOcorrencia() {
		click(ocorrencias);
		assertEquals("Ocorrêcias", labelTitle.getText());
	}

	public void validaDetalheReferencia() {
		click(referencias);
		assertEquals("Referências", labelTitle.getText());
	}

	public void sair() {
		aguardarElementoVisivel(botaoSair);
		click(botaoSair);
		acceptAlert();
	}
	public void acceptAlert(){
		try {
	        Alert alert = driver.switchTo().alert();
	        alert.getText();
	        alert.accept();
	    } catch (Exception e) {
	    }
	}
	
	public void selecionarFaturaAtiva() {
		
		int indice = 1;
		String PREFIX_FATURA_ATIVA  = ".//*[@id='listaFaturas']/tbody/tr[";
	    String SUFIX_FATURA_ATIVA   = "]/td[6]/img";
	    String SUFIX_VISUALIZAR     = "]/td[7]/a/img";
	    By PATH_FATURA_ATIVA        = By.xpath(PREFIX_FATURA_ATIVA + indice + SUFIX_FATURA_ATIVA);
	    WebElement VISUALIZAR_2_VIA = null;
        
        while(!isElementVisibility(PATH_FATURA_ATIVA)){
        	indice++;
        	PATH_FATURA_ATIVA = By.xpath(PREFIX_FATURA_ATIVA + indice + SUFIX_FATURA_ATIVA);
        }

        VISUALIZAR_2_VIA = driver.findElement(By.xpath(PREFIX_FATURA_ATIVA + indice + SUFIX_VISUALIZAR));
		click(VISUALIZAR_2_VIA);
	}
}	
