package com.trusthub.cobranca.application.exceptions;

import org.springframework.http.HttpStatus;

import com.trusthub.cobranca.configuration.validation.generic.domain.TrustHubError;
import com.trusthub.cobranca.configuration.validation.generic.layers.TrustHubServiceException;

/**
 * Exception que representa Integracao Service
 * @author jose.viana
 */
public class CobrancaRelatoriosSrmServiceException extends TrustHubServiceException {

	private static final long serialVersionUID = 1L;

	public CobrancaRelatoriosSrmServiceException(String msg, TrustHubError trustHubError, HttpStatus httpStatus,
			Throwable cause) {
		super(msg, trustHubError, httpStatus, cause);
	}

	public CobrancaRelatoriosSrmServiceException(String msg, TrustHubError trustHubError, HttpStatus httpStatus) {
		super(msg, trustHubError, httpStatus);
	}

	public CobrancaRelatoriosSrmServiceException(String msg, TrustHubError trustHubError, Throwable cause) {
		super(msg, trustHubError, cause);
	}

	public CobrancaRelatoriosSrmServiceException(String msg, TrustHubError trustHubError) {
		super(msg, trustHubError);
	}

}
