package com.trusthub.cobranca.security;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.trusthub.cobranca.application.util.Constantes;

/**
 *  
 * @author alan.franco
 */
public class CobrancaRelatoriosSecurityUtil {
	
	public static HttpEntity<?> atribuirBodyAndHeader(Object obj, String authorization){
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	    headers.set(Constantes.AUTHORIZATION, authorization);
	    HttpEntity<?> entity = new HttpEntity<>(obj, headers);
	    return entity;
	}
	
}
