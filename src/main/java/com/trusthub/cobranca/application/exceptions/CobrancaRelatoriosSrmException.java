package com.trusthub.cobranca.application.exceptions;

import org.springframework.http.HttpStatus;

import com.trusthub.cobranca.configuration.validation.generic.TrustHubException;
import com.trusthub.cobranca.configuration.validation.generic.domain.TrustHubError;

/**
 * Exception que representa Integracao SRM
 * @author jose.viana
 */
public class CobrancaRelatoriosSrmException extends TrustHubException {

	private static final long serialVersionUID = 1L;

	public CobrancaRelatoriosSrmException(String msg, TrustHubError trustHubError, HttpStatus httpStatus,
			Throwable cause) {
		super(msg, trustHubError, httpStatus, cause);
	}

	public CobrancaRelatoriosSrmException(String msg, TrustHubError trustHubError, HttpStatus httpStatus) {
		super(msg, trustHubError, httpStatus);
	}

	public CobrancaRelatoriosSrmException(String msg, TrustHubError trustHubError, Throwable cause) {
		super(msg, trustHubError, cause);
	}

	public CobrancaRelatoriosSrmException(String msg, TrustHubError trustHubError) {
		super(msg, trustHubError);
	}
}
