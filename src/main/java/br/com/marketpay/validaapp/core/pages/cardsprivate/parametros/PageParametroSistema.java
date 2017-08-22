package br.com.marketpay.validaapp.core.pages.cardsprivate.parametros;

import static br.com.marketpay.validaapp.core.util.Assert.assertEquals;
import lombok.extern.log4j.Log4j;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.marketpay.validaapp.core.pages.common.CardsPage;

@Log4j
public class PageParametroSistema extends CardsPage<PageParametroSistema>{

	public PageParametroSistema(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id = "titulo_new")
	WebElement labelTitle;
	
	@FindBy(css = "img[title='Página Inicial']")
	WebElement tituloInicial;
	
	@FindBy(id = "parametros_mais")
	WebElement paramSistema;
	
	@FindBy(id = "parametrosSistema_mais")
	WebElement sistema;
	
	@FindBy(xpath = ".//*[@id='parametrosSistema']/a")
	WebElement visualizarSistema;
	
	@FindBy(id = "parametrosCredito_mais")
	WebElement credito;
	
	@FindBy(xpath = ".//*[@id='parametrosCredito']/a")
	WebElement visualizarCredito;
	
	@FindBy(id = "parametrosCobranca_mais")
	WebElement cobranca;
	
	@FindBy(id = "parametrosCobrancaParametroGeral_mais")
	WebElement cobrancaParamGerais;
	
	@FindBy(xpath = ".//*[@id='parametrosCobrancaParametroGeral']/a")
	WebElement visualizarCobrancaParamGerais;
	
	@FindBy(id = "parametrosCobrancaParametroAcordo_mais")
	WebElement cobrancaParamAcordo;
	
	@FindBy(xpath = ".//*[@id='parametrosCobrancaParametroAcordo']/a")
	WebElement visualizarCobrancaParamAcordo;
	
	@FindBy(id = "tipoContratoSPC_mais")
	WebElement cobrancaParamTipoContrato;
	
	@FindBy(xpath = ".//*[@id='tipoContratoSPC']/a[1]")
	WebElement listarCobrancaParamTipoContrato;
	
	@FindBy(id = "tipoPerda_mais")
	WebElement cobrancaParamTipoPerda;
	
	@FindBy(xpath = ".//*[@id='tipoPerda']/a[1]")
	WebElement listarCobrancaParamTipoPerda;
	
	@FindBy(id = "parametrosTaxaValor_mais")
	WebElement taxasValores;
	
	@FindBy(xpath = ".//*[@id='parametrosTaxaValor']/a")
	WebElement visualizarTaxasValores;
	
	@FindBy(id = "parametrosMensagemFatura_mais")
	WebElement mensagensFaturas;
	
	@FindBy(xpath = ".//*[@id='parametrosMensagemFatura']/a")
	WebElement listarMensagensFaturas;
	
	@FindBy(id = "parametrosMensagem_mais")
	WebElement mensagens;
	
	@FindBy(xpath = ".//*[@id='parametrosMensagem']/a")
	WebElement listarMensagens;
	
	@FindBy(id = "parametrosContas_mais")
	WebElement contas;
	
	@FindBy(xpath = ".//*[@id='parametrosContas']/a")
	WebElement visualizarContas;
	
	@FindBy(id = "parametrosProposta_mais")
	WebElement proposta;
	
	@FindBy(xpath = ".//*[@id='parametrosProposta']/a")
	WebElement visualizarProposta;
	
	@FindBy(id = "parametrosCartoes_mais")
	WebElement cartoes;
	
	@FindBy(xpath = ".//*[@id='parametrosCartoes']/a[1]")
	WebElement visualizarCartoes;
	
	@FindBy(xpath = ".//*[@id='parametrosConvenio']/a")
	WebElement visualizarConvenio;
	
	@FindBy(id = "parametrosRedeCredenciada_mais")
	WebElement rede;
	
	@FindBy(xpath = ".//*[@id='parametrosRedeCredenciada']/a")
	WebElement visualizarRede;
	
	@FindBy(id = "parametrosFaturamento_mais")
	WebElement faturamento;
	
	@FindBy(xpath = ".//*[@id='parametrosFaturamento']/a")
	WebElement visualizarFaturamento;
	
	@FindBy(id = "parametrosGeracaoArquivo_mais")
	WebElement geracaoArquivo;
	
	@FindBy(xpath = ".//*[@id='parametrosGeracaoArquivo']/a")
	WebElement visualizarGeracaoArquivo;
	
	@FindBy(id = "parametrosGerais_mais")
	WebElement gerais;
	
	@FindBy(xpath = ".//*[@id='parametrosGerais']/a")
	WebElement visualizarGerais;
	
	@FindBy(id = "parametrosParcelamento_mais")
	WebElement parcelamento;
	
	@FindBy(xpath = ".//*[@id='parametrosParcelamento']/a")
	WebElement visualizarParcelamento;
	
	@FindBy(id = "botao2")
	WebElement botaoFiltrar;
	
	@FindBy(css = ".tabelalistar_new")
	WebElement registroEncontrado;
	
	public void validarParametros(){
		validarParametroSistema();
		validarParametroCredito();
		validarParametroCobranca();
		validarParametroTaxasValores();
		validarParametroMensagensFaturas();
		validarParametroMensagens();
		validarParametroContas();
		validarParametroPropostaAdesaoCliente();
		validarParametroCartoes();
		validarParametroRedeCredenciada();
		validarParametroFaturamento();
		validarParametroGeracaoArquivo();
		validarParametroGerais();
		validarParametroParcelamentoFatura();
	}
	public void validarParametroSistema(){
		abrirMenuParamSistema();
		aguardarElementoVisivel(sistema);
		click(sistema);
		aguardarElementoVisivel(visualizarSistema);
		click(visualizarSistema);
		aguardarElementoVisivel(labelTitle);
		assertEquals("Parâmetros de Sistema", labelTitle.getText());
	}
	public void validarParametroCredito(){
		abrirMenuParamSistema();
		click(credito);
		click(visualizarCredito);
		assertEquals("Visualizar Parâmetros de Créditos", labelTitle.getText());
	}
	public void validarParametroCobranca(){
		validarParametroCobrancaParamGerais();
		validarParametroCobrancaParamAcordo();
		validarParametroCobrancaParamTipoContrato();
		if (isElementoDisplayed(cobrancaParamTipoPerda)) {
			validarParametroCobrancaParamTipoPerda();
		}
	}
	public void validarParametroTaxasValores(){
		abrirMenuParamSistema();
		aguardarElementoVisivel(taxasValores);
		click(taxasValores);
		aguardarElementoVisivel(visualizarTaxasValores);
		click(visualizarTaxasValores);
		assertEquals("Visualizar Parâmetros de Taxas e Valores", labelTitle.getText());
	}
	public void validarParametroMensagensFaturas(){
		abrirMenuParamSistema();
		aguardarElementoVisivel(mensagensFaturas);
		click(mensagensFaturas);
		aguardarElementoVisivel(listarMensagensFaturas);
		click(listarMensagensFaturas);
		assertEquals("Listar Parâmetros de Mensagens das Faturas", labelTitle.getText());
		if(!isElementoDisplayed(registroEncontrado)){
			log.error("Erro no filtro: Param >> Mensagens das Faturas >> Filtrar");
		}
	}
	public void validarParametroMensagens(){
		abrirMenuParamSistema();
		click(mensagens);
		click(listarMensagens);
		
		assertEquals("Listar Mensagem", labelTitle.getText());
		click(botaoFiltrar);
		if(!isElementoDisplayed(registroEncontrado)){
			log.info("Erro no filtro: Param >> Mensagens >> Filtrar");
		}
	}
	
	public void validarParametroContas(){
		abrirMenuParamSistema();
		aguardarElementoVisivel(contas);
		click(contas);
		click(visualizarContas);
		
		assertEquals("Visualizar Parâmetros de Contas", labelTitle.getText());
	}
	public void validarParametroPropostaAdesaoCliente(){
		if (isElementoDisplayed(proposta)) {
			abrirMenuParamSistema();
			click(proposta);
			click(visualizarProposta);
			
			assertEquals("Visualizar Parâmetros Proposta Adesão Cliente", labelTitle.getText());
		}
	}
	public void validarParametroCartoes(){
		abrirMenuParamSistema();
		click(cartoes);
		click(visualizarCartoes);
		
		assertEquals("Visualizar Parâmetros de Cartões", labelTitle.getText());
	}
	public void validarParametroRedeCredenciada(){
		abrirMenuParamSistema();
		click(rede);
		click(visualizarRede);
		assertEquals("Visualizar Parâmetros Rede Credenciada", labelTitle.getText());
	}
	public void validarParametroFaturamento(){
		abrirMenuParamSistema();
		click(faturamento);
		click(visualizarFaturamento);
		
		assertEquals("Visualizar Parâmetros de Faturamento", labelTitle.getText());
	}
	public void validarParametroGeracaoArquivo(){
		abrirMenuParamSistema();
		click(geracaoArquivo);
		click(visualizarGeracaoArquivo);
		assertEquals( "Visualizar Parâmetros de Geração de Arquivo", labelTitle.getText());
	}
	public void validarParametroGerais(){
		abrirMenuParamSistema();
		click(gerais);
		click(visualizarGerais);
		assertEquals( "Visualizar Parâmetros Gerais", labelTitle.getText());
	}
	public void validarParametroParcelamentoFatura(){
		abrirMenuParamSistema();
		click(parcelamento);
		click(visualizarParcelamento);
		assertEquals( "Parâmetros de Parcelamento", labelTitle.getText());
	}
	
	public void abrirMenuParamSistema(){
		click(tituloInicial);
		click(paramSistema);
	}
	public void validarParametroCobrancaParamGerais(){
		abrirMenuParamSistema();
		click(cobranca);
		click(cobrancaParamGerais);
		click(visualizarCobrancaParamGerais);
		assertEquals("Visualizar Parâmetros Gerais de Cobrança", labelTitle.getText());
	}
	public void validarParametroCobrancaParamAcordo(){
		abrirMenuParamSistema();
		click(cobranca);
		click(cobrancaParamAcordo);
		click(visualizarCobrancaParamAcordo);
		assertEquals("Visualizar Parâmetros de Cobrança em Acordos", labelTitle.getText());
	}
	public void validarParametroCobrancaParamTipoContrato(){
		abrirMenuParamSistema();
		click(cobranca);
		click(cobrancaParamTipoContrato);
		click(listarCobrancaParamTipoContrato);
		
		assertEquals("Listar Tipo Contrato SPC", labelTitle.getText());
		click(botaoFiltrar);
		if(!isElementoDisplayed(registroEncontrado)){
			log.info("Erro no filtro: Param >> Cobranca >> Tipo de Contrato >> Filtrar");
		}
	}
	public void validarParametroCobrancaParamTipoPerda(){
		abrirMenuParamSistema();
		click(cobranca);
		click(cobrancaParamTipoPerda);
		click(listarCobrancaParamTipoPerda);
		assertEquals("Listar Tipos de Perda", labelTitle.getText());
		
		click(botaoFiltrar);
		if(!isElementoDisplayed(registroEncontrado)){
			log.error("Erro no filtro: Param >> Cobranca >> Tipo de Perda >> Filtrar");
		}
	}
	public boolean isValidaApenasParametros(String aplicacao){
		if (aplicacao.equals("avantisys")              || aplicacao.equals("cartaoconfianca") ||
           (aplicacao.equals("cartaoconfiancacliente") || aplicacao.equals("cartaonalin"))){
			return true;
		}
		return false;
	}

}
