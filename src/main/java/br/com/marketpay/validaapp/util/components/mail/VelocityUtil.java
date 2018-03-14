package br.com.marketpay.validaapp.util.components.mail;

import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

public class VelocityUtil {

	private static VelocityUtil instancia;

	private VelocityEngine velocityEngine;

	public static void init(String dirTemplates) throws VelocityUtilException {
		try {
			Properties properties = new Properties();
			properties.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, dirTemplates);

			getInstancia().velocityEngine = new VelocityEngine();
			getInstancia().velocityEngine.init(properties);
		} catch(Exception erro) {
			erro.printStackTrace();
			throw new VelocityUtilException();
		}
	}

	private VelocityUtil() throws VelocityUtilException {
	}

	public static VelocityUtil getInstancia() throws VelocityUtilException {
		if(instancia == null) {
			instancia = new VelocityUtil();
		}

		return instancia;
	}

	private Template getTemplateCaminhoRelativoTemplates(String caminho) throws VelocityUtilException {
		try {
			return velocityEngine.getTemplate(caminho);
		} catch(Exception erro) {
			erro.printStackTrace();
			throw new VelocityUtilException();
		}
	}

	public String merge(String caminhoTemplate, VelocityContext context) throws VelocityUtilException {
		try {
			StringWriter writer = new StringWriter();
			Template template = getTemplateCaminhoRelativoTemplates(caminhoTemplate);
			template.merge(context, writer);
			return writer.toString();
		} catch(Exception erro) {
			erro.printStackTrace();
			throw new VelocityUtilException();
		}
	}

}
