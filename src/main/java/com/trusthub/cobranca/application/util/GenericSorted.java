package com.trusthub.cobranca.application.util;

import java.lang.reflect.InvocationTargetException;

import com.trusthub.cobranca.application.validation.CobrancaRelatoriosError;
import com.trusthub.cobranca.application.validation.GenericSortedException;

/**
 * Classe generica que faz ordenação. 
 * @author alan.franco
 */
public class GenericSorted {
	
	private static String ORIENTACAO_DESC = "DESC";
	private static String GET = "get";
	
	/**
	 * Método generico para ordernar qualquer DTO passado com o atributo
	 * @param obj1 - DTO (generico)
	 * @param obj2 - DTO (generico)
	 * @param nomeAtributo - Será montado através de reflection o nome do atributo
	 * @param orientacao - ASC - para acendente DESC 
	 *
	 * EX: Uma chamada através de uma lista que necessita de ordenacao
	 * 
	 * elements.parallelStream()
	 *                       .sorted((obj1, obj2) ->  {
	 *                              return GenericSorted.order(p1, p2, nomeAtributo, orientacao);
	 *                       })
	 * @return ira ordenar a lista
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int order(Object obj1, Object obj2, String nomeAtributo, String orientacao) {
		try {
			Comparable comp1 =  obj1.getClass().getMethod(buildGetMethodName(nomeAtributo), new Class[]{}).invoke(obj1,  new Object[]{}).toString();
			Comparable comp2 = obj2.getClass().getMethod(buildGetMethodName(nomeAtributo), new Class[]{}).invoke(obj2,  new Object[]{}).toString();
			if(orientacao == null || orientacao.equals(ORIENTACAO_DESC) ) {
				return comp2.compareTo(comp1);
			}else {
				return comp1.compareTo(comp2);
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e1) {
			throw new GenericSortedException(new StringBuilder(Mensagens.BUSINESS_ERRO_ORDER)
					.append(e1.getMessage()).toString(), CobrancaRelatoriosError.ERROR_COBRANCA_BUSINESS);
		}
	}
	
	/**
	 * Constroi o nome do método de acordo com o nome do atributo
	 * @param fieldName
	 * @return Método get do atributo
	 */
	public static String buildGetMethodName(String fieldName) {
		StringBuilder methodName = new StringBuilder(GET);
		methodName.append(fieldName.substring(0, 1).toUpperCase());
		methodName.append(fieldName.substring(1, fieldName.length()));
		return methodName.toString();
	}

}
