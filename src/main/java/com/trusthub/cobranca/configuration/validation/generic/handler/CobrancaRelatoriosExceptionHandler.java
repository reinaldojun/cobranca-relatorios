package com.trusthub.cobranca.configuration.validation.generic.handler;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.trusthub.cobranca.configuration.validation.generic.TrustHubException;
import com.trusthub.cobranca.configuration.validation.generic.domain.StandardError;

/**
 * Generic class that will intercept the error thrown in the access layers or integrations.
 * @author alan.franco
 */
@ControllerAdvice
public class CobrancaRelatoriosExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(CobrancaRelatoriosExceptionHandler.class);

	@ExceptionHandler(TrustHubException.class)
	public ResponseEntity<StandardError> trustHubException(TrustHubException e, HttpServletRequest request) {
		StandardError err = new StandardError(new Timestamp(System.currentTimeMillis()), e.getTrustHubError().getErrorCode(), e.getTrustHubError().getErrorDescription(), e.getMessage(), request.getRequestURI());		
		if(e != null && e.getMessage() != null) {
			if(e.getMessage().contains("timestamp")	&& e.getMessage().contains("errorCode")	&& e.getMessage().contains("errorDescription") && e.getMessage().contains("message") && e.getMessage().contains("path")){
				String erro =e.getMessage().substring(e.getMessage().indexOf("{"), e.getMessage().lastIndexOf("}")+1);
				err = StandardError.getStandardError(erro);
			}
		}
		String msgErro = new StringBuilder("HEADER: ").append(e.getHttpStatus()).append(" BODY: ").append(err.getMessage()).toString();
		log.error(msgErro, e);   
		return ResponseEntity.status(e.getHttpStatus()).body(err);
	}
}
