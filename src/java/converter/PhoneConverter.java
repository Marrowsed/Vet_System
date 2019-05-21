package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("phoneConverter")
public class PhoneConverter implements Converter{

 @Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		 throw new UnsupportedOperationException("Ainda não implementado...");
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		String fone = (String) value; 
		
		if(!fone.isEmpty() && fone != null){ 
			String ddd = fone.substring(0, 2); 
			String numeroParte1 = ""; 
			String numeroParte2 = "";
			
			if(fone.length() == 11){ 
				numeroParte1 = fone.substring(2, 7); 
				numeroParte2 = fone.substring(7, 11); 
			} else {
				numeroParte1 = fone.substring(2, 6);  
				numeroParte2 = fone.substring(6, 10); 
			}
			return "(" + ddd + ") " + numeroParte1 + " - " + numeroParte2;
		}
		
		return "";
        }
}