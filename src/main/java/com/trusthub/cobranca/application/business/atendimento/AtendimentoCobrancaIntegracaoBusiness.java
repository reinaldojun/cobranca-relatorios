package com.trusthub.cobranca.application.business.atendimento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trusthub.cobranca.application.exceptions.atendimento.AtendimentoCobrancaIntegracaoBusinessException;
import com.trusthub.cobranca.application.exceptions.atendimento.AtendimentoCobrancaIntegracaoCobrancaOperacaoException;
import com.trusthub.cobranca.application.service.atendimento.AtendimentoCobrancaService;
import com.trusthub.cobranca.application.util.Mensagens;
import com.trusthub.cobranca.application.validation.CobrancaRelatoriosError;
import com.trusthub.cobranca.configuration.validation.generic.layers.TrustHubServiceException;
import com.trusthub.cobranca.domain.dto.FiltrosRelatorioDTO;
import com.trusthub.cobranca.domain.dto.atendimento.AtendimentoCobrancaIntegracaoDTO;
import com.trusthub.cobranca.security.CobrancaRelatoriosSecurityException;

/**
 * Classe que irá controlar toda regra do atendimento integracao cobranca 
 * @author alan.franco/jose.viana
 */
@Component
public class AtendimentoCobrancaIntegracaoBusiness {

	@Autowired
	private AtendimentoCobrancaService atendimentoCobrancaService;
	
	/**
	 * Consultar historico cobranca por filtro,
	 * consulta lista de atendimento por filtro 
	 * @param FiltrosRelatorioDTO - dados do filtro
	 * @return List<AtendimentoCobrancaIntegracaoDTO> - Lista de Atendimento Cobranca
	 */
	public List<AtendimentoCobrancaIntegracaoDTO> consultarHistoricoAtendimentoCobranca(FiltrosRelatorioDTO filtro) {
		List<AtendimentoCobrancaIntegracaoDTO> listaAtendimentoCobranca = new ArrayList<>();
		try {
			listaAtendimentoCobranca = atendimentoCobrancaService.consultarHistoricoAtendimentoCobranca(filtro);

		} catch (TrustHubServiceException | AtendimentoCobrancaIntegracaoCobrancaOperacaoException | CobrancaRelatoriosSecurityException ea) {
			throw ea;
		} catch (Exception e) {
			throw new AtendimentoCobrancaIntegracaoBusinessException(new StringBuilder(Mensagens.BUSINESS_ERRO_CONSULTAR_HISTORICO_COBRANCA)
					.append(e.getMessage()).toString(), CobrancaRelatoriosError.ERROR_COBRANCA_BUSINESS);
		}			
		return listaAtendimentoCobranca;
	}
	
	

}
