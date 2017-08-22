package br.com.marketpay.validaapp.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.entity.Emissor;
import br.com.marketpay.validaapp.entity.FolderFpt;
import br.com.marketpay.validaapp.service.EmissorService;
import br.com.marketpay.validaapp.service.FolderFtpService;
import br.com.marketpay.validaapp.service.UtilService;
import br.com.marketpay.validaapp.web.util.Flash;
import br.com.marketpay.validaapp.web.util.Flash.FlashTipo;
import br.com.marketpay.validaapp.web.util.ValidaAppPages;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Service
@Data
@EqualsAndHashCode(callSuper = false)
@Scope("view")
public class FolderFtpBean extends BeanAbstract implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private FolderFpt folderFtp = new FolderFpt();
	
	private Emissor emissor = new Emissor();
	
	private List<FolderFpt> foldersFtp = new ArrayList<>();
	
	private boolean visualizar;
	
	private boolean alterar;
	
	private String pesquisaEmissor;
	
	private String pesquisaNomePasta;
	
	@Autowired
	private FolderFtpService folderFtpEmissorService;
	
	@Autowired
	private EmissorService emissorService;
	
	@Autowired
	private UtilService utilService;
	
	public void preencherAlterar(){
		alterar = true;
		visualizar = false;
		preecherObjeto();
	}
	
	public void preencherVisualizar(){
		alterar = false;
		visualizar = true;
		preecherObjeto();
	}
	
	private void preecherObjeto(){
		if (folderFtp != null && folderFtp.getId() != 0) {
			folderFtp = folderFtpEmissorService.findById(folderFtp.getId());
		}
	}
	
	public String salvar() throws IOException{
		folderFtpEmissorService.save(folderFtp); 
		
		addMensageRedirect(new Flash("Pasta salvo com sucesso", FlashTipo.success));
		
		return ValidaAppPages.PAGINA_VISUALIZAR_FOLDER;
	}
	
	public void pesquisar(){
		foldersFtp = null;
		
		if(emissor.getId()==0 || emissor == null){
			List<Emissor> listaEmissorAtivo = emissorService.listarEmissorAtivo(Emissor.STATUS_ATIVA);
			
			if(StringUtils.isBlank(pesquisaNomePasta.trim())) {
				foldersFtp = folderFtpEmissorService.findAllByEmissorIn(listaEmissorAtivo);
			}else {
				foldersFtp = folderFtpEmissorService.findByNomeAndEmissorIn(pesquisaNomePasta,listaEmissorAtivo);
			}
		}else {
			emissor = emissorService.findById(emissor.getId());

			if(StringUtils.isBlank(pesquisaNomePasta.trim())) {
				foldersFtp = folderFtpEmissorService.findAllByNomeIgnoreCaseContainingAndEmissor(pesquisaNomePasta, emissor);
			}else {
				foldersFtp = folderFtpEmissorService.findByEmissor(emissor);
			}
		}
	}
	
	public void testeConexao() throws IOException{
		Boolean statusConexao;
		statusConexao = utilService.getStatusConexao(folderFtp.getPorta(),folderFtp.getIp(), folderFtp.getLogin(), folderFtp.getSenha());
		if (statusConexao) {
			addMensageRedirect(new Flash("Conexão realizada com sucesso", FlashTipo.success));
		}else {
			addMensageRedirect(new Flash("Falha na conexão", FlashTipo.danger));
		}
	}
	
}
