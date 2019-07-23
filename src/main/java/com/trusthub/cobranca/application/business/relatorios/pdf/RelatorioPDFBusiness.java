	package com.trusthub.cobranca.application.business.relatorios.pdf;

import org.springframework.stereotype.Component;

import com.trusthub.cobranca.application.exceptions.CobrancaRelatoriosSrmBusinessException;
import com.trusthub.cobranca.application.exceptions.CobrancaRelatoriosSrmRepositoryException;
import com.trusthub.cobranca.application.exceptions.CobrancaRelatoriosSrmServiceException;
import com.trusthub.cobranca.application.util.Mensagens;
import com.trusthub.cobranca.application.validation.CobrancaRelatoriosError;
import com.trusthub.cobranca.domain.dto.RelatorioDTO;

/**
 * Camada de negocio que centraliza as regras de titulo. 
 * @author jose.viana
 */
@Component
public class RelatorioPDFBusiness{

	/**
	 * Consulta todos relatorios
	 * @return RelatorioDTO  - Carrega relatorio conforme ID
	 */
	public RelatorioDTO consultarProcessarGerarRelatorioId(Integer id, String nomeAtributo, String orientacao) {
		RelatorioDTO dto = new RelatorioDTO();
		
		try {
			
			System.out.println("");
			
		}catch (CobrancaRelatoriosSrmRepositoryException | CobrancaRelatoriosSrmServiceException | CobrancaRelatoriosSrmBusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new CobrancaRelatoriosSrmBusinessException(new StringBuilder(Mensagens.BUSINESS_ERRO_PROCESSA_GERAR)
					.append(e.getMessage()).toString(), CobrancaRelatoriosError.ERROR_COBRANCA_BUSINESS);
		}
		return dto;
	}
	
}
