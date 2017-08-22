package br.com.marketpay.validaapp.web;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.com.marketpay.validaapp.web.util.Flash;

public abstract class BeanAbstract {

	public static final String MESSAGE_TIPO_SUCESS = "Success";
	public static final String MESSAGE_TIPO_ALERTA = "Warning";
	public static final String MESSAGE_TIPO_ERRO = "Error";
	public static final String MESSAGE_TIPO_INFO = "Information";
	
	public static final String SIM = "S";
	public static final String NAO = "N";
	
	public Object getRequestParam(String name) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get(name);
	}

	public void setRequestParameter(String key, Object value) {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        req.setAttribute(key, value);
   }
	
	public Integer getRequestIntParam(String name, Integer defaultValue) {
		try {
			return Integer.parseInt((String)getRequestParam(name));
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public String contextPathApplication(){
		ExternalContext Externalcontext = FacesContext.getCurrentInstance().getExternalContext();  
		HttpServletRequest request = (HttpServletRequest)Externalcontext.getRequest();  

		return request.getContextPath();
	}
	
	public void redirect(String url) throws IOException {
	    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    externalContext.redirect(url);
	}
	
	public ServletContext getServletContext(){
		ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext(); 
		return servletContext;
	}

	public ServletResponse getServletResponse(){
		ServletResponse response = (ServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse(); 
		return response;
	}
	
	public void executJavaScript(String comando){
		RequestContext.getCurrentInstance().execute(comando);
	}
	
	public void checkedRadio(String idComponent){
		RequestContext.getCurrentInstance().execute("$('#" + idComponent + "').prop('checked', true );");
	}
	
	public void setValueCampo(String idComponent, String value){
		RequestContext.getCurrentInstance().execute("$('#" + idComponent + "').val('" + value + "');");
	}
	
	public void addMessageValidacao(String form, String campo, String mensagem){
		RequestContext.getCurrentInstance().execute("validacaoPersonalizada('" + form + "','" + campo + "','" + mensagem + "')");
	}
	
	public HttpServletRequest getHttpServletRequest(){
		return (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	
	public HttpSession getHttpSession(){
		return getHttpServletRequest().getSession();
	}
	
	public void addMensageRedirect(Flash flash){
		javax.faces.context.Flash flashMessage = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flashMessage.setKeepMessages(true);
		flashMessage.putNow("flash", flash);
	}
	
	public void removerFlashMessage(){
		javax.faces.context.Flash flashMessage = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flashMessage.setKeepMessages(true);
		flashMessage.remove("flash");
	}
	
	public static void click(String codigo){
		RequestContext.getCurrentInstance().execute("document.getElementById('" + codigo + "').click();");
	}
	
	public static void addAlertaSuccess(String mensagem){
		RequestContext.getCurrentInstance().execute("document.getElementById('mensagem').value = '" + mensagem + "'");
		RequestContext.getCurrentInstance().execute("document.getElementById('tipo').value = '" + MESSAGE_TIPO_SUCESS + "'");
		click("botaoAlerta");
	}
	
	public static void addAlertaAlerta(String mensagem){
		RequestContext.getCurrentInstance().execute("document.getElementById('mensagem').value = '" + mensagem + "'");
		RequestContext.getCurrentInstance().execute("document.getElementById('tipo').value = '" + MESSAGE_TIPO_ALERTA + "'");
		click("botaoAlerta");
	}
	
	public static void addAlertaErro(String mensagem){
		RequestContext.getCurrentInstance().execute("document.getElementById('mensagem').value = '" + mensagem + "'");
		RequestContext.getCurrentInstance().execute("document.getElementById('tipo').value = '" + MESSAGE_TIPO_ERRO + "'");
		click("botaoAlerta");
	}
	
	public static void addAlertaInfo(String mensagem){
		RequestContext.getCurrentInstance().execute("document.getElementById('mensagem').value = '" + mensagem + "'");
		RequestContext.getCurrentInstance().execute("document.getElementById('tipo').value = '" + MESSAGE_TIPO_INFO + "'");
		click("botaoAlerta");
	}
	
	public void invalidateSession() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}

	public static void download(byte[] arquivo, String filename) {   
        HttpServletResponse response;  
        try {  
            response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();  
            response.setContentType("application/download");  
            response.addHeader("Content-Disposition", "attachment; filename=" + filename);  
            response.setContentLength(arquivo.length);  
            response.getOutputStream().write(arquivo);  
            response.getOutputStream().flush();  
            response.getOutputStream().close();  
  
            FacesContext.getCurrentInstance().responseComplete();  
        }catch (Exception e) {  
            e.printStackTrace();
        }  
    }
}
