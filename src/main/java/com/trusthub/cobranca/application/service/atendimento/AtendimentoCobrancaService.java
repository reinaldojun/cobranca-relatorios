package com.trusthub.cobranca.application.service.atendimento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.trusthub.cobranca.application.exceptions.atendimento.AtendimentoCobrancaIntegracaoCobrancaOperacaoException;
import com.trusthub.cobranca.application.exceptions.atendimento.AtendimentoCobrancaIntegracaoServiceException;
import com.trusthub.cobranca.application.util.ApiConstantes;
import com.trusthub.cobranca.application.util.Constantes;
import com.trusthub.cobranca.application.util.Mensagens;
import com.trusthub.cobranca.application.validation.CobrancaRelatoriosError;
import com.trusthub.cobranca.configuration.api.ClientDiscoveyServiceFactory;
import com.trusthub.cobranca.domain.dto.FiltrosRelatorioDTO;
import com.trusthub.cobranca.domain.dto.atendimento.AtendimentoCobrancaIntegracaoDTO;
import com.trusthub.cobranca.security.CobrancaRelatoriosSecurityUtil;


/**
 * Classe de servico que contem médodos para acessar api cobranca operacao (atendimento cobranca)
 * @author alan.franco/jose.viana
 */
@Component
public class AtendimentoCobrancaService {
	
	@Autowired
	protected RestTemplate restTemplate;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private ClientDiscoveyServiceFactory discovey;

	/**
	 * Consultar historico cobranca por cedente e sacado acessando api cobranca operacao.
	 * @param idCedente - Id Cedente
	 * @param idSacado - Id Sacado 
	 * @return List<AtendimentoCobrancaIntegracaoDTO> - Lista com dados do historico do atendimento cobranca
	 */
	public List<AtendimentoCobrancaIntegracaoDTO> consultarHistoricoAtendimentoCobranca(FiltrosRelatorioDTO filtro){
		ResponseEntity<AtendimentoCobrancaIntegracaoDTO[]> response = null;
		List<AtendimentoCobrancaIntegracaoDTO> listaAtendimentoCobrancaIntegracaoDTO = new ArrayList<>();
		try {
			String uri = new StringBuilder(ApiConstantes.API_BAR).append(ApiConstantes.API_ATENDIMENTO_CONSULTAR_COBRANCA).toString();
			
			response = restTemplate.exchange(discovey.url(discovey.OPERACAO_COBRANCA, discovey.CONTEXTO_OPERACAO_COBRANCA, uri),  
					HttpMethod.POST, CobrancaRelatoriosSecurityUtil.atribuirBodyAndHeader(filtro, request.getHeader(Constantes.AUTHORIZATION)),
						AtendimentoCobrancaIntegracaoDTO[].class);

			listaAtendimentoCobrancaIntegracaoDTO = validarRespostaListAtendimentoCobranca(response);
		}catch (AtendimentoCobrancaIntegracaoServiceException ei) {
			throw ei;
		}catch (HttpClientErrorException ei) {
			throw new AtendimentoCobrancaIntegracaoCobrancaOperacaoException(new StringBuilder(Mensagens.SERVICE_ERRO_CONSULTAR_HISTORICO_COBRANCA)
					.append(ei.getResponseBodyAsString()).toString(), CobrancaRelatoriosError.ERROR_COBRANCA_SERVICE, HttpStatus.FORBIDDEN);
		}catch (HttpServerErrorException ei) {
			throw new AtendimentoCobrancaIntegracaoCobrancaOperacaoException(new StringBuilder(Mensagens.SERVICE_ERRO_CONSULTAR_HISTORICO_COBRANCA)
					.append(ei.getResponseBodyAsString()).toString(), CobrancaRelatoriosError.ERROR_COBRANCA_SERVICE);
		}catch (Exception e) {
			throw new AtendimentoCobrancaIntegracaoCobrancaOperacaoException(new StringBuilder(Mensagens.SERVICE_ERRO_CONSULTAR_HISTORICO_COBRANCA)
				.append(e.getMessage()).toString(), CobrancaRelatoriosError.ERROR_COBRANCA_SERVICE);
		}
		return listaAtendimentoCobrancaIntegracaoDTO;
	}
	
	/**
	 * Validar resposta do consultar historico cobranca por cedente e sacado
	 * @param response - resposta da api cobranca operacao
	 * @return - List<AtendimentoCobrancaIntegracaoDTO> - lista com os dados do atendimento cobranca
	 */
	public List<AtendimentoCobrancaIntegracaoDTO> validarRespostaListAtendimentoCobranca(ResponseEntity<AtendimentoCobrancaIntegracaoDTO[]> response) {
		List<AtendimentoCobrancaIntegracaoDTO> listaAtendimentoCobrancaIntegracaoDTO = new ArrayList<>();
		try {
			if(response.getStatusCodeValue() != HttpStatus.OK.value()) {
				throw new AtendimentoCobrancaIntegracaoServiceException(new StringBuilder(Mensagens.SERVICE_ERRO_VALIDAR_RESPOSTA_CONSULTAR_ATENDIMENTO_COBRANCA)
						.append(Constantes.CODIGO_ERRO).append(response.getStatusCodeValue()).toString(), 
						CobrancaRelatoriosError.ERROR_COBRANCA_SERVICE);
			}else if (response.getBody() != null && response.getBody().length > 0) {
				listaAtendimentoCobrancaIntegracaoDTO = Arrays.asList(response.getBody());
			}
		}catch (Exception e) {
			 throw new AtendimentoCobrancaIntegracaoServiceException(new StringBuilder(Mensagens.SERVICE_ERRO_VALIDAR_RESPOSTA_CONSULTAR_ATENDIMENTO_COBRANCA)
					 .append(e.getMessage()).toString(), CobrancaRelatoriosError.ERROR_COBRANCA_SERVICE);	
		}				
		return listaAtendimentoCobrancaIntegracaoDTO;
	}

}
