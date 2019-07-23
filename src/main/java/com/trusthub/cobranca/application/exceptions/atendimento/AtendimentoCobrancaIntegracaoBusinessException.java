package com.trusthub.cobranca.application.exceptions.atendimento;

import org.springframework.http.HttpStatus;

import com.trusthub.cobranca.configuration.validation.generic.domain.TrustHubError;
import com.trusthub.cobranca.configuration.validation.generic.layers.TrustHubBusinessException;

/**
 *  Classe que representa exception da integracao atendimento business
 *  @author alan.franco
 */
public class AtendimentoCobrancaIntegracaoBusinessException extends TrustHubBusinessException {

	private static final long serialVersionUID = 1L;

	public AtendimentoCobrancaIntegracaoBusinessException(String msg, TrustHubError trustHubError, HttpStatus httpStatus,
			Throwable cause) {
		super(msg, trustHubError, httpStatus, cause);
	}

	public AtendimentoCobrancaIntegracaoBusinessException(String msg, TrustHubError trustHubError,
			HttpStatus httpStatus) {
		super(msg, trustHubError, httpStatus);
	}

	public AtendimentoCobrancaIntegracaoBusinessException(String msg, TrustHubError trustHubError, Throwable cause) {
		super(msg, trustHubError, cause);
	}

	public AtendimentoCobrancaIntegracaoBusinessException(String msg, TrustHubError trustHubError) {
		super(msg, trustHubError);
	}
	
}
