package br.com.marketpay.validaapp.web.util.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.inputtextarea.InputTextarea;

@FacesConverter(value = "stringConverter")
public class StringConverter implements Converter {

	private static int TAMANHOLABEL = 80;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String texto = (String) value;
		
		if(component.getClass().equals(InputTextarea.class)){
			texto = texto.replace("\n", "<!--- <br></br> ---> ");
		}else{
			texto = texto.replaceAll("<.*?>", "");
			if(texto.length() > TAMANHOLABEL){
				return texto.substring(0,TAMANHOLABEL) + " ...";
			}
		}
		return texto;
	}

}
