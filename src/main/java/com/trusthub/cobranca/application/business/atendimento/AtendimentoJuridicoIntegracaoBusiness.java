package com.trusthub.cobranca.application.business.atendimento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trusthub.cobranca.application.exceptions.atendimento.AtendimentoCobrancaIntegracaoBusinessException;
import com.trusthub.cobranca.application.exceptions.atendimento.AtendimentoCobrancaIntegracaoCobrancaOperacaoException;
import com.trusthub.cobranca.application.service.atendimento.AtendimentoJuridicoService;
import com.trusthub.cobranca.application.util.Mensagens;
import com.trusthub.cobranca.application.validation.CobrancaRelatoriosError;
import com.trusthub.cobranca.configuration.validation.generic.layers.TrustHubServiceException;
import com.trusthub.cobranca.domain.dto.FiltrosRelatorioDTO;
import com.trusthub.cobranca.domain.dto.atendimento.AtendimentoJuridicoIntegracaoDTO;
import com.trusthub.cobranca.security.CobrancaRelatoriosSecurityException;

/**
 * Classe que irá controlar toda regra do atendimento integracao cobranca 
 * @author alan.franco/jose.viana
 */
@Component
public class AtendimentoJuridicoIntegracaoBusiness {

	@Autowired
	private AtendimentoJuridicoService atendimentoJuridicoService;
	
	/**
	 * Consultar historico cobranca por filtro,
	 * consulta lista de atendimento por filtro 
	 * @param FiltrosRelatorioDTO - dados do filtro
	 * @return List<AtendimentoCobrancaIntegracaoDTO> - Lista de Atendimento Juridico
	 */
	public List<AtendimentoJuridicoIntegracaoDTO> consultarHistoricoAtendimentoJuridico(FiltrosRelatorioDTO filtro) {
		List<AtendimentoJuridicoIntegracaoDTO> listaAtendimentoCobranca = new ArrayList<>();
		try {
			listaAtendimentoCobranca = atendimentoJuridicoService.consultarHistoricoAtendimentoJuridico(filtro);

		} catch (TrustHubServiceException | AtendimentoCobrancaIntegracaoCobrancaOperacaoException | CobrancaRelatoriosSecurityException ea) {
			throw ea;
		} catch (Exception e) {
			throw new AtendimentoCobrancaIntegracaoBusinessException(new StringBuilder(Mensagens.BUSINESS_ERRO_CONSULTAR_HISTORICO_JURIDICO)
					.append(e.getMessage()).toString(), CobrancaRelatoriosError.ERROR_COBRANCA_BUSINESS);
		}			
		return listaAtendimentoCobranca;
	}
	
	

}
