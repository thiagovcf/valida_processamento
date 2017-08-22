/**
 * 
 */
package br.com.marketpay.validaapp.core.pages.common;


import lombok.extern.log4j.Log4j;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author CONDUCTOR\Thiago.Freire
 */
@Log4j
public class PageLoginCardsRetaguarda extends CardsPage<PageLoginCardsRetaguarda> {

	public PageLoginCardsRetaguarda(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "login")
	WebElement inputLogin;
	
	@FindBy(name = "senha")
	WebElement inputSenha;
	
	@FindBy(name = "button")
	WebElement botaoEntrar;
	
	@FindBy(css = ".descVersao")
	WebElement versaoRetaguarda;

	@FindBy(css = "#direitosRodape")
	WebElement versaoRodape;
	
	@FindBy(css = "img[title='Página Inicial']")
	WebElement botaoPaginaPrincipal;
	
	
	public String getVersaoCardsPrivate(){
		return "Versão CARDS PRIVATE ";
	}
	public String getDadosRodapeCards(){
		String parteRodape = "Copyright @ 2016 - CONDUCTOR - Todos os direitos reservados. All rights reserved. - ";
		return parteRodape;
	}
	public void acessarCardsRetaguarda(String login, String senha, Boolean tirarPrint){
		preencherCampo(this.inputLogin, login);
		preencherCampo(this.inputSenha, senha);
		log.info(versaoRetaguarda.getText());
		click(botaoEntrar);
		log.info(versaoRodape.getText());
	}
	
	public void acessarCardsRetaguarda(String login, String senha){
		acessarCardsRetaguarda(login, senha, true);
		log.info("Acessando Cards Retaguarda: Login: "+login+ ", senha: "+senha);
	}
	
	public void acessarCardsRetaguardaLoginSistema() {
		aguardarElementoVisivel(inputLogin);
		preencherCampo(this.inputLogin, "sistema");
		preencherCampo(this.inputSenha, "sistema");
		aguardarElementoVisivel(botaoEntrar);
		click(botaoEntrar);
		aguardarElementoVisivel(botaoPaginaPrincipal);
	}
	
	public void openLoginRetaguarda(){
		openPageRetaguarda(PageLoginCardsRetaguarda.class);
	}
	
}
