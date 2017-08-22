//package br.com.marketpay.validaapp.core.test;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.openqa.selenium.WebDriver;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import br.com.marketpay.validaapp.core.Selenium;
//import br.com.marketpay.validaapp.core.pages.cardsprivate.contas.PageListarContas;
//import br.com.marketpay.validaapp.core.pages.cardsprivate.contas.PageVisualizarContaRetaguarda;
//import br.com.marketpay.validaapp.core.pages.cardsprivate.contas.PageVisualizarFaturaRetaguarda;
//import br.com.marketpay.validaapp.core.pages.cardsprivate.contas.PageVisualizarOcorrenciaRetaguarda;
//import br.com.marketpay.validaapp.core.pages.cardsprivate.contas.PageVisualizarProduto;
//import br.com.marketpay.validaapp.core.pages.cardsprivate.contas.PageVisualizarReferenciaRetaguarda;
//import br.com.marketpay.validaapp.core.pages.cardsprivate.movimentos.PageMovimentoPagamentosCards;
//import br.com.marketpay.validaapp.core.pages.cardsprivate.movimentos.PageMovimentoVendaCards;
//import br.com.marketpay.validaapp.core.pages.cardsprivate.parametros.PageParametroSistema;
//import br.com.marketpay.validaapp.core.pages.common.PageLoginCardsRetaguarda;
//import br.com.marketpay.validaapp.core.pages.common.PageMenu;
//import br.com.marketpay.validaapp.entity.Aplicacao;
//
//public class TestGeraisCards {
//
//	Selenium selenium = new Selenium();
//	WebDriver driver = selenium.getDriver();
//	@Autowired
//	private Aplicacao aplicacao;
//	
//	@Before
//	public void before(){
//		PageLoginCardsRetaguarda loginCardsRetaguarda = new PageLoginCardsRetaguarda(driver);
//		loginCardsRetaguarda.openPageRetaguarda(PageLoginCardsRetaguarda.class);
//		loginCardsRetaguarda.acessarCardsRetaguardaLoginSistema();
//	}
//	@After
//	public void after(){
//		selenium.resetDriver();
//	}
//	@Test
//	public void validarContaCliente(){
//		PageMenu pageMenu = new PageMenu(driver);
//		pageMenu.acessarContasListar();
//		
//		PageListarContas contas = new PageListarContas(driver);
//		contas.listarContaComSucesso("12012312");
//		
//		PageVisualizarContaRetaguarda clientePage = new PageVisualizarContaRetaguarda(driver);
//		clientePage.validarCliente();
//		clientePage.validaDetalheFatura();
//		
//		PageVisualizarFaturaRetaguarda visualizarFaturaRetaguardaPage = new PageVisualizarFaturaRetaguarda(driver);
//		visualizarFaturaRetaguardaPage.verificaDetalhamentoFatura();
//		clientePage.validaDetalheOcorrencia();
//		
//		PageVisualizarOcorrenciaRetaguarda visualizarOcorrenciaRetaguardaPage = new PageVisualizarOcorrenciaRetaguarda(driver);
//		visualizarOcorrenciaRetaguardaPage.verificaOcorrencia();
//		clientePage.validaDetalheReferencia();
//
//		PageVisualizarReferenciaRetaguarda visualizarReferenciaRetaguardaPage = new PageVisualizarReferenciaRetaguarda(driver);
//		visualizarReferenciaRetaguardaPage.verificaReferencia();
//	}
//	
//	@Test
//	public void validarProduto(){
//		
//		PageMenu pageMenu = new PageMenu(driver);
//		pageMenu.acessarProdutosListar();
//		
//		PageVisualizarProduto pageProduto = new PageVisualizarProduto(driver);
//		pageProduto.listarProdutosComSucesso();
//		pageProduto.verificaProdutos();
//	}
//	
//	@Test
//	public void validarFiltragemDeVendas(){
//		PageMenu pageMenu = new PageMenu(driver);
//		pageMenu.acessarMovimentosVendasListar();
//		
//		PageMovimentoVendaCards vendaCards = new PageMovimentoVendaCards(driver);
//		vendaCards.filtrarVendaNumeroOrcamento("27710947");
//		vendaCards.verificaRetornoFiltroVenda("27710947");
//	}
//	@Test
//	public void validarFiltragemDePagamento(){
//		PageMenu pageMenu = new PageMenu(driver);
//		pageMenu.acessarMovimentosPagamentosListar();
//		
//		PageMovimentoPagamentosCards pagamentosCards = new PageMovimentoPagamentosCards(driver);
//		pagamentosCards.listarPagamentoComSucesso("0029032453");
//	}
//	@Test
//	public void validarParametros(){
//		PageParametroSistema pageParametro = new PageParametroSistema(driver);
//		pageParametro.validarParametros();
//	}
//}
