package util;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;

/**
 * Classe utilizada para recupera��o de beans na sess�o.
 * 
 * @author LAPIS - FCRS
 * @version 1.0	
 */
public class ManagedBeanHelper {

	/**
	 * M�todo gen�rico que recupera um bean atrav�s de algumas caracter�scas.
	 * 
	 * @return <T> T
	 * @param String
	 * @param Class
	 *            <T>
	 */
	@SuppressWarnings("el-syntax")
	public static <T> T recuperaBean(String nomeDoBean, Class<T> targetClass) {
		FacesContext context = FacesContext.getCurrentInstance();
		return (T) context.getApplication().evaluateExpressionGet(context,
				"#{" + nomeDoBean + "}", targetClass);
	}
	
	

	/**
	 * M�todo gen�rico que destr�i um bean que se encontra em sess�o.
	 * 
	 * @param String
	 * @param Class
	 *            <T>
	 */
	@SuppressWarnings("el-syntax")
	public static <T> void destroiBean(String nomeDoBean, Class<T> targetClass) {
		ValueExpression valueExpression = FacesContext
				.getCurrentInstance()
				.getApplication()
				.getExpressionFactory()
				.createValueExpression(
						FacesContext.getCurrentInstance().getELContext(),
						"#{" + nomeDoBean + "}", targetClass);

		valueExpression.setValue(FacesContext.getCurrentInstance()
				.getELContext(), null);

	}

}
