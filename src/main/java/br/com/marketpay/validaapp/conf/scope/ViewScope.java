package br.com.marketpay.validaapp.conf.scope;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.ObjectFactory;

public class ViewScope implements org.springframework.beans.factory.config.Scope {

	public Object get(String name, ObjectFactory<?> objectFactory) {
		if (FacesContext.getCurrentInstance().getViewRoot() != null) {
			Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
			if (viewMap.containsKey(name)) {
				return viewMap.get(name);
			} else {
				Object object = objectFactory.getObject();
				// da próxima vez que for requisitado, dentro do escopo de view,
				// ele não precisará ser construído.
				viewMap.put(name, object);
				return object;
			}
		} else {
			return null;
		}
	}

	public Object remove(String name) {
		if (FacesContext.getCurrentInstance().getViewRoot() != null) {
			// simplesmente, remove o bean Spring do ViewMap do JSF
			return FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove(name);
		} else {
			return null;
		}
	}

	@Override
	public String getConversationId() {
		return null;
	}

	@Override
	public void registerDestructionCallback(String arg0, Runnable arg1) {
	}

	@Override
	public Object resolveContextualObject(String arg0) {
		return null;
	}

}