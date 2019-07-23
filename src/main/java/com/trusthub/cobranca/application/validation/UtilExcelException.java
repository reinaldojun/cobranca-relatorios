package com.trusthub.cobranca.application.validation;


import org.springframework.http.HttpStatus;

import com.trusthub.cobranca.configuration.validation.generic.domain.TrustHubError;
import com.trusthub.cobranca.configuration.validation.generic.layers.TrustHubBusinessException;

/**
 * Exception Util Excel Exception 
 * @author alan.franco
 */
public class UtilExcelException extends TrustHubBusinessException{

	private static final long serialVersionUID = 1L;

	public UtilExcelException(String msg, TrustHubError trustHubError, HttpStatus httpStatus,
			Throwable cause) {
		super(msg, trustHubError, httpStatus, cause);
	}

	public UtilExcelException(String msg, TrustHubError trustHubError, HttpStatus httpStatus) {
		super(msg, trustHubError, httpStatus);
	}

	public UtilExcelException(String msg, TrustHubError trustHubError, Throwable cause) {
		super(msg, trustHubError, cause);
	}

	public UtilExcelException(String msg, TrustHubError trustHubError) {
		super(msg, trustHubError);
	}

}
