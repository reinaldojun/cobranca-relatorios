package com.trusthub.cobranca.application.validation;

import com.trusthub.cobranca.configuration.validation.generic.domain.TrustHubError;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Classe que representa o codigo e descricao das camadas ou integracoes
 * @author alan.franco
 */
@Getter
@AllArgsConstructor
public enum CobrancaRelatoriosError implements TrustHubError {
	
	ERROR_COBRANCA_BUSINESS(1000, "ERRO NA API (COBRANCA RELATORIO) NA CAMADA BUSINESS"), 
	ERROR_COBRANCA_SERVICE(2000, "ERRO (COBRANCA RELATORIO) NA CAMADA SERVICE"),
	ERROR_COBRANCA_REPOSITORY(3000, "ERRO (COBRANCA RELATORIO) NA CAMADA DE ACESSO A DADOS"),
	ERROR_COBRANCA_INTEGRACAO_COBRANCA_OPERACAO(3, "ERRO NA API (COBRANCA RELATORIOS) NA INTEGRACAO COM O COBRANCA OPERACAO"),
	ERROR_COBRANCA_SECURITY(6, "ERRO NA API (COBRANCA RELATORIO) ERRO - COBRANCA ACESSO");
	 
	Integer errorCode;
	String errorDescription;

	@Override
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	@Override
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
}
