package com.trusthub.cobranca.configuration.api;

import org.springframework.beans.factory.annotation.Value;

/**
 * Interface que fica o contexto e uri das aplicacoes acessadas 
 * @author alan.franco
 */
public class Integracao {
	
	
	@Value("${app.cobranca.relatorios.cobranca.acesso.contexto}")
	public String CONTEXTO_INTEGRACAO_COBRANCA_ACESSO;
	
	@Value("${app.cobranca.relatorios.cobranca.acesso.uri}")
	public String INTEGRACAO_COBRANCA_ACESSO;
	
	@Value("${app.cobranca.integracao.operacao.cobranca.contexto}")
	public String CONTEXTO_OPERACAO_COBRANCA;
	
	@Value("${app.cobranca.integracao.operacao.cobranca.uri}")
	public String OPERACAO_COBRANCA;
	
}
