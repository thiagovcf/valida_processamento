package br.com.marketpay.validaapp.core.pages.cardsprivate.contas;

import static br.com.marketpay.validaapp.core.util.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.marketpay.validaapp.core.pages.common.CardsPage;

public class PageVisualizarProduto extends CardsPage<PageVisualizarProduto>{

	public PageVisualizarProduto(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "input[value='Filtrar']")
	private WebElement botaoFiltrar;
	
	@FindBy(css = "input[value='Restrições']")
	private WebElement restricoes;
	
	@FindBy(id = "titulo_new")
	private WebElement labelTitle;
	
	public void listarProdutosComSucesso(){
		filtrarProdutos();
	}
	
	public void verificaProdutos(){
		By visualizarProdutoPersonalizado = By.xpath(".//*[@id='idRelatorio']/table/tbody/tr[not(contains(.,'-1'))]/td[3][contains(.,'ADMINISTRADORA')]/../*//img[@title='Visualizar']");
	    By visualizarProdutoConvenio      = By.xpath(".//*[@id='idRelatorio']/table/tbody/tr[contains(.,'CONVÊNIO')]/td/a/img[@title='Visualizar']");
	    By visualizarProdutoPadrao        = By.xpath(".//*[@id='idRelatorio']/table/tbody/tr[contains(.,'-1') and contains(.,'ADMINISTRADORA')]/td/a/img[@title='Visualizar']");
	    boolean existsProdutoConvenio     = isElementVisibility(visualizarProdutoConvenio);
	    
	    if (isElementVisibility(visualizarProdutoPersonalizado)){
	    	WebElement produto = driver.findElement(visualizarProdutoPersonalizado);
	    	produto.click();
	    	assertEquals("Visualizar Produto", labelTitle.getText());
	    	visualizaAbaRestricoes();
	    	if (existsProdutoConvenio) {
	    		voltarPagina();
	    		voltarPagina();	
	    	}
	    }else
	    	if (isElementVisibility(visualizarProdutoPadrao)) {
	    		WebElement produto = driver.findElement(visualizarProdutoPadrao);
	    		produto.click();
	    		assertEquals("Visualizar Produto", labelTitle.getText());
	    		if (existsProdutoConvenio) {
	    			voltarPagina();
	    		}
	    	}
	    
	    if (existsProdutoConvenio) {
	    	WebElement produto = driver.findElement(visualizarProdutoConvenio);
	    	produto.click();
	    	assertEquals("Visualizar Produto", labelTitle.getText());
		}
	}
	
	public void filtrarProdutos(){
		click(botaoFiltrar);
	}
	
	public void visualizaAbaRestricoes(){
		click(restricoes);
		assertEquals("Visualizar Restrições do Produto", labelTitle.getText());
	}
}
