package br.com.marketpay.validaapp.core.pages.common;

import lombok.extern.log4j.Log4j;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Log4j
public class PageMenu extends CardsPage<PageMenu> {
	
	public PageMenu(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	//==========================================================
	// Botao principal Cards 
	//==========================================================
	@FindBy(css = "img[title='Página Inicial']")
	WebElement botaoPaginaInicial;
	
	
	//==========================================================
	// Menu Contas Cards Retaguarda
	//==========================================================
	@FindBy(xpath = ".//*[@id='contas_mais']/div[2]")
	public WebElement menuContas;

	@FindBy(linkText = "Listar")
	WebElement contasListar;
	
	@FindBy(xpath = ".//*[@id='contasTipoBloqueio_mais']")
	public WebElement tiposBloqueios;
	
	@FindBy(xpath = ".//*[@id='contasBloqueio_mais']")
	public WebElement contasBloqueios;
	
	@FindBy(linkText = "Incluir")
	public WebElement incluir;
	
	@FindBy(linkText = "Bloquear Contas")
	public WebElement contasBloqueiosBloquearContas;
	
	@FindBy(id = "produto_mais")
	public WebElement produto;
	
	@FindBy(xpath = ".//*[@id='produto']/a[1]")
	public WebElement produtoListar;
	
	//==========================================================
	// Menu Cartão Cards Retaguarda
	//==========================================================
	@FindBy(xpath = ".//*[@id='cartao_mais']/div[2]")
	public WebElement menuCartao;

	@FindBy(xpath = ".//*[@id='cartaoBloqueio_mais']")
	public WebElement cartaoBloqueios;
	
	@FindBy(linkText = "Bloquear Cartão")
	public WebElement incluirBloqueioCartao;
	
	@FindBy(linkText = "Consultar Cartão")
	public WebElement consultarCartao;
	
	@FindBy(id = "cartaoCliente_mais")
	public WebElement cartaoCliente;
	
	@FindBy(linkText = "Incluir no Lote")
	public WebElement incluirNoLote;

	@FindBy(linkText = "Imprimir")
	public WebElement imprimirCartao;
	
	
	//==========================================================
	// Menu Movimentos Cards Retaguarda 
	//==========================================================
	@FindBy(id = "movimentos_mais")
	public WebElement menuMovimentos;
	
	@FindBy(id = "movimentosVendas_mais")
	public WebElement movimentoVendas;

	@FindBy(linkText = "Incluir pelo Retaguarda")
	public WebElement incluirVendaPeloRetaguarda;
	
	@FindBy(linkText = "Listar")
	public WebElement listarVenda;
	
	@FindBy(linkText = "Listar")
	public WebElement listarPagamento;
	
	@FindBy(id = "movimentosPagamentos_mais")
	public WebElement movimentoPagamentos;
	
	@FindBy(linkText = "Incluir pelo retaguarda")
	public WebElement incluirPagamentoPeloRetaguarda;
	
	//==========================================================
	// Menu Faturamento Cards Retaguarda
	//==========================================================
	@FindBy(xpath = ".//*[@id='faturamento_mais']/div[2]")
	public WebElement menuFaturamento;
		
	@FindBy(xpath = ".//*[@id='parcelamentoFatura_mais']")
	public WebElement menuFaturamentoParcelamentoFatura;
	
	@FindBy(linkText = "Simular")
	public WebElement simular;
	
	@FindBy(linkText = "Listar")
	public WebElement listarParcelamentoFatura;
	
	//==========================================================
	// Menu Cobrança Cards Retaguarda
	//==========================================================

	//==========================================================
	// Menu Rede Credenciada Cards Retaguarda
	//==========================================================

	//==========================================================
	// Menu Outros cadastros Cards Retaguarda
	//==========================================================

	//==========================================================
	// Menu Parametros do Sistema Cards Retaguarda
	//==========================================================
	
	@FindBy(xpath = ".//*[@id='parametros_mais']/div[2]")
	public WebElement menuParamSistema;
	
	@FindBy(xpath = ".//*[@id='parametrosParcelamento_mais']")
	public WebElement ParamSistemaParcelamentoFatura;
	
	@FindBy(linkText = "Alterar")
	public WebElement alterarParametrosSistema;
	
	//==========================================================
	// Menu Relatórios Cards Retaguarda
	//==========================================================

	@FindBy(id = "relatorio_mais")
	public WebElement relatorios;

	@FindBy(id = "relatorioDinamico_mais")
	public WebElement menuRelatoriosDinamicos;
	
	@FindBy(css = ".itemMenu[href='Controle?manipulador=SqlRelatorio&acao=listar']")
	public WebElement menuRelatoriosDinamicosListar;
	
	@FindBy(xpath = ".//*[@id='relatorio_mais']/div[2]")
	public WebElement menuRelatorio;
	
	@FindBy(linkText = "Histórico de Job")
	public WebElement historicoJob;
	
	//==========================================================
	// Menu Usuario Cards Retaguarda
	//==========================================================
	@FindBy(id = "usuarios_mais")
	WebElement abrirDropdownUsuarios;

	@FindBy(id = "usuarios_menos")
	WebElement fecharDropdownUsuarios;

	@FindBy(id = "usuariosRetaguarda_mais")
	WebElement abrirSubmenuRetaguarda;

	@FindBy(id = "usuariosRetaguarda_menos")
	WebElement fecharSubmenuRetaguarda;

	@FindBy(id = "usuariosRetGrupo_mais")
	WebElement abrirSubmenuRetaguardaGrupos;

	@FindBy(id = "usuariosRetGrupo_menos")
	WebElement fecharSubmenuRetaguardaGrupos;

	@FindBy(xpath = "/html/body/div[1]/div[1]/div[2]/div[20]/div[2]/div[2]/a[2]")
	WebElement botaoIncluirGruposRetaguarda;

	@FindBy(id = "usuSAC_mais")
	WebElement abrirSubmenuModuloSAC;

	@FindBy(id = "usuSAC_menos")
	WebElement fecharSubmenuModuloSAC;

	@FindBy(id = "usuSacGrupo_mais")
	WebElement abrirSubmenuModuloSACGrupos;

	@FindBy(id = "usuSacGrupo_menos")
	WebElement fecharSubmenuModuloSACGrupos;

	@FindBy(xpath = "//*[@id='usuSacGrupo']/a[2]")
	WebElement botaoIncluirGruposModuloSAC;
	
	@FindBy(id = "usuariosRetUsuario_mais")
	WebElement botaoUsuariosRetaguarda;

	@FindBy(xpath = "//*[@id='usuariosRetUsuario']/a[2]")
	WebElement botaoIncluirUsuariosRetaguarda;

	@FindBy(xpath = ".//*[@id='usuariosRetUsuario']/a[1]")
	WebElement botaoListarUsuariosRetaguarda;

	@FindBy(id = "usuSacUsuario_mais")
	WebElement botaoUsuariosSAC;

	@FindBy(xpath = "//*[@id='usuSacUsuario']/a[2]")
	WebElement botaoIncluirUsuariosSAC;
	
	//==========================================================
	// Menu Funções Adm. Cards Retaguarda
	//==========================================================
	
	@FindBy(xpath = ".//*[@id='funcoesAdm_mais']/div[2]")
	WebElement abrirFuncoesAdmin;

	@FindBy(xpath = ".//*[@id='funcoesAdm_menos']/div[2]")
	WebElement fecharFuncoesAdmin;

	@FindBy(xpath = ".//*[@id='funcoesAdm']/a[contains(.,'Limpar Cache')]")
	WebElement limparCache;
	
	@FindBy(xpath = ".//*[@id='funcoesAdm']/a[contains(.,'Executar Job')]")
	WebElement executarJob;

	//==========================================================
	// Sair
	//==========================================================
	@FindBy(xpath = "//*[@id='logoffRodape']/a/img")
	WebElement botaoSair;
	
	//=======================FIM MAPEAMENTO=======================
	
	public void acessarMenu(WebElement element){
		click(botaoPaginaInicial);//incluido para sempre voltar aos ids default
		click(element);
		log.info("Acessando menu : " +element.getText());
	}
	public void acessarSubMenu(WebElement element){
		aguardarElementoVisivel(element);
		click(element);
		log.info("Acessando menu : " +element.getText());
	}
	public void acessarSubItemMenu(WebElement element){
		aguardarElementoVisivel(element);
		click(element);
		log.info("Acessando menu : " +element.getText());
	}
	public void acessarMenu(WebElement... elements){
		try {
			click(botaoPaginaInicial);
		} catch (Exception e) {
			log.error("Erro ao acessar menu. Elementos "+elements);
		}
		for(WebElement element: elements){
			aguardarElementoVisivel(element);
			click(element);
		}
	}
	
	public void acessarFaturamentoParcelamentodefaturaSimular() {
		acessarMenu(menuFaturamento, menuFaturamentoParcelamentoFatura,simular);
	}

	public void acessarFaturamentoParcelamentodefaturaListar() {
		acessarMenu(menuFaturamento, menuFaturamentoParcelamentoFatura, listarParcelamentoFatura);
	}

	public void acessarFaturamento() {
		acessarMenu(menuFaturamento);
	}

	public void acessarContasListar() {
		acessarMenu(menuContas, contasListar);
	}

	public void acessarMovimentosPagamentosIncluirpeloretaguarda() {
		acessarMenu(menuMovimentos, movimentoPagamentos, incluirPagamentoPeloRetaguarda);
	}

	public void acessarUsuariosRetaguardaGruposIncluir() {
		acessarMenu(abrirDropdownUsuarios, abrirSubmenuRetaguarda, abrirSubmenuRetaguardaGrupos, botaoIncluirGruposRetaguarda);
	}

	public void acessarUsuariosRetaguardaUsuarioIncluir() {
		acessarMenu(abrirDropdownUsuarios, abrirSubmenuRetaguarda, botaoUsuariosRetaguarda, botaoIncluirUsuariosRetaguarda);
	}

	public void acessarUsuariosRetaguardaUsuarioListar() {
		acessarMenu(abrirDropdownUsuarios, abrirSubmenuRetaguarda, botaoUsuariosRetaguarda, botaoListarUsuariosRetaguarda);
	}

	public void acessarUsuariosModulosacGruposIncluir() {
		acessarMenu(abrirDropdownUsuarios, abrirSubmenuModuloSAC, abrirSubmenuModuloSACGrupos, botaoIncluirGruposModuloSAC);
	}

	public void acessarUsuariosModulosacUsuarioIncluir() {
		acessarMenu(abrirDropdownUsuarios, abrirSubmenuModuloSAC, botaoUsuariosSAC, botaoIncluirUsuariosSAC);
	}


	public void acessarParametroSistemaParcelamentoFaturaAlterar(){
		acessarMenu(menuParamSistema, ParamSistemaParcelamentoFatura, alterarParametrosSistema);
	}

	public boolean existeOpcaoParcelamento() {
		return isElementoDisplayed(menuFaturamentoParcelamentoFatura);
	}

	public void limparCacheRetaguarda() {
		acessarMenu(abrirFuncoesAdmin, limparCache, fecharFuncoesAdmin);
	}

	public void clicarBotaoSair() {
		click(botaoSair);
		alertaSaidaDoSistema();
	}
	
	public void abrirTelaExecutarJob(){
		acessarMenu(abrirFuncoesAdmin, executarJob, fecharFuncoesAdmin);
	}
	public void acessarCartaoBloqueiosBloquearCartao() {
		acessarMenu(menuCartao, cartaoBloqueios, incluirBloqueioCartao);
	}
	//==========================================================
	// MENU MOVIMENTOS
	//==========================================================
	public void acessarMovimentosVendasIncluirPeloRetaguarda() {
		acessarMenu(menuMovimentos, movimentoVendas, incluirVendaPeloRetaguarda);
	}
	public void acessarMovimentosVendasListar(){
		acessarMenu(menuMovimentos, movimentoVendas, listarVenda);
	}
	public void acessarMovimentosPagamentosListar(){
		acessarMenu(menuMovimentos, movimentoPagamentos, listarPagamento);
	}
	
	
	
	
	public void acessarCartaoBloqueiosConsultarCartao() {
		acessarMenu(menuCartao, cartaoBloqueios, consultarCartao);
	}
	public void acessarCartaoCartaoClienteIncluirNoLote() {
		acessarMenu(menuCartao, cartaoCliente, incluirNoLote);
	}
	public void acessarCartaoCartaoClienteImprimir() {
		acessarMenu(menuCartao, cartaoCliente, imprimirCartao);
	}
	
	//==========================================================
	// MENU RELATÓRIOS
	//==========================================================
	public void acessarRelatorioHistoricoJob(){
		acessarMenu(menuRelatorio, historicoJob);
	}
	
	public void acessarRelatoriosDinamicosListar(){
		acessarMenu(relatorios, menuRelatoriosDinamicos, menuRelatoriosDinamicosListar);
	}
	
	//==========================================================
	// MENU PRODUTOS
	//==========================================================
	public void acessarProdutosListar(){
		acessarMenu(menuContas, produto, produtoListar);
	}

}
