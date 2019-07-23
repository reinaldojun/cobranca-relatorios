package com.trusthub.cobranca.application.validation;

import org.springframework.http.HttpStatus;

import com.trusthub.cobranca.configuration.validation.generic.domain.TrustHubError;
import com.trusthub.cobranca.configuration.validation.generic.layers.TrustHubBusinessException;

/**
 * Exception Gerneric Sorted Exception 
 * @author alan.franco
 */
public class GenericSortedException extends TrustHubBusinessException{

	private static final long serialVersionUID = 1L;

	public GenericSortedException(String msg, TrustHubError trustHubError, HttpStatus httpStatus,
			Throwable cause) {
		super(msg, trustHubError, httpStatus, cause);
	}

	public GenericSortedException(String msg, TrustHubError trustHubError, HttpStatus httpStatus) {
		super(msg, trustHubError, httpStatus);
	}

	public GenericSortedException(String msg, TrustHubError trustHubError, Throwable cause) {
		super(msg, trustHubError, cause);
	}

	public GenericSortedException(String msg, TrustHubError trustHubError) {
		super(msg, trustHubError);
	}

}
