package com.trusthub.cobranca.application.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.trusthub.cobranca.application.exceptions.CobrancaRelatoriosSrmBusinessException;
import com.trusthub.cobranca.application.validation.CobrancaRelatoriosError;

public class Util {
	
	private Util() {}
	
	public static byte[] toByteArray(InputStream in) throws IOException {
 	    ByteArrayOutputStream os = new ByteArrayOutputStream();
 	    try {
 			byte[] buffer = new byte[1024];
 			int len;
 			// read bytes from the input stream and store them in buffer
 			while ((len = in.read(buffer)) != -1) {
 				// write bytes from the buffer into output stream
 				os.write(buffer, 0, len);
 			}
		} catch (Exception e) {
			throw new CobrancaRelatoriosSrmBusinessException(new StringBuilder(Mensagens.BUSINESS_ERRO_COVERTER_INPUTSTREAM_BYTES)
					.append(e.getMessage()).toString(), CobrancaRelatoriosError.ERROR_COBRANCA_BUSINESS);
		}
		return os.toByteArray();
	}

}
