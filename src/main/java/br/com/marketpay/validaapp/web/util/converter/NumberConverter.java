package br.com.marketpay.validaapp.web.util.converter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "numberConverter")
public class NumberConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value!= null && !value.equals("")){
			return new BigDecimal(value.replace("R$ ", "").replace(".", "").replace(",", "."));
		}
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			BigDecimal v = (BigDecimal) value;

			DecimalFormat format = new DecimalFormat("##,###,###,##0.00",
					new DecimalFormatSymbols(new Locale("pt", "BR")));
			format.setMinimumFractionDigits(2);
			format.setParseBigDecimal(true);

			return "R$ " + format.format(v);

		}
		return null;
	}
}
