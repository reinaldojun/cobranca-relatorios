package com.trusthub.cobranca.application.service.titulos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trusthub.cobranca.application.exceptions.CobrancaRelatoriosSrmRepositoryException;
import com.trusthub.cobranca.application.exceptions.CobrancaRelatoriosSrmServiceException;
import com.trusthub.cobranca.application.repository.TitulosRepository;
import com.trusthub.cobranca.application.service.atendimento.AtendimentoCobrancaService;
import com.trusthub.cobranca.application.service.atendimento.AtendimentoJuridicoService;
import com.trusthub.cobranca.application.util.Mensagens;
import com.trusthub.cobranca.application.validation.CobrancaRelatoriosError;
import com.trusthub.cobranca.domain.dto.FiltrosRelatorioDTO;
import com.trusthub.cobranca.domain.dto.atendimento.AtendimentoCobrancaIntegracaoDTO;
import com.trusthub.cobranca.domain.dto.atendimento.AtendimentoJuridicoIntegracaoDTO;
import com.trusthub.cobranca.domain.dto.titulos.TitulosAtendimentosDTO;
import com.trusthub.cobranca.domain.dto.titulos.TitulosLiquidadosDTO;
import com.trusthub.cobranca.domain.dto.titulos.TitulosOperacaoDTO;
import com.trusthub.cobranca.domain.dto.titulos.TitulosVencidosVincendosDTO;

/**
 * Camada service que irá controlar acesso ao repository de titulos
 * @author jose.viana
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TitulosService {

	@Autowired
	private TitulosRepository titulosRepository;
	
	@Autowired
	private AtendimentoCobrancaService atendimentoCobrancaService;
	
	@Autowired
	private AtendimentoJuridicoService atendimentoJuridicoService;
	
	

	/**
	 * Consulta todos titulos liquidados
	 * @return List<TitulosDTO>  - Lista de Titulos liquidados
	 */
	public List<TitulosLiquidadosDTO> consultarTitulosLiquidados(FiltrosRelatorioDTO filtrosRelatorioDTO) {
		List<TitulosLiquidadosDTO> listaTitulosDTO = new ArrayList<>();
		try {
			listaTitulosDTO = titulosRepository.consultarTitulosLiquidados(filtrosRelatorioDTO);
		}catch (CobrancaRelatoriosSrmRepositoryException e) {
			throw e;
		}catch (Exception e) {
			throw new CobrancaRelatoriosSrmServiceException(new StringBuilder(Mensagens.SERVICE_ERRO_CONSULTAR_TITULOS_LIQUIDADOS)
				.append(e.getMessage()).toString(), CobrancaRelatoriosError.ERROR_COBRANCA_SERVICE);
		}
		return listaTitulosDTO;
	}
	
	/**
	 * Consulta todos titulos vencidos e a vencer
	 * @return List<TitulosDTO>  - Lista de Titulos vencidos e a vencer
	 */
	public List<TitulosVencidosVincendosDTO> consultarTitulosVencidosVincendos(FiltrosRelatorioDTO filtrosRelatorioDTO) {
		List<TitulosVencidosVincendosDTO> listaTitulosDTO = new ArrayList<>();
		try {
			listaTitulosDTO = titulosRepository.consultarTitulosVencidosVincendos(filtrosRelatorioDTO);
		}catch (CobrancaRelatoriosSrmRepositoryException e) {
			throw e;
		}catch (Exception e) {
			throw new CobrancaRelatoriosSrmServiceException(new StringBuilder(Mensagens.SERVICE_ERRO_CONSULTAR_TITULOS_VENCIDOS_VINCENDOS)
				.append(e.getMessage()).toString(), CobrancaRelatoriosError.ERROR_COBRANCA_SERVICE);
		}
		return listaTitulosDTO;
	}
	
	/**
	 * Consulta todos titulos operacao
	 * @return List<TitulosDTO>  - Lista de Titulos operacao
	 */
	public List<TitulosOperacaoDTO> consultarTitulosOperacao(FiltrosRelatorioDTO filtrosRelatorioDTO) {
		List<TitulosOperacaoDTO> listaTitulosDTO = new ArrayList<>();
		try {
			listaTitulosDTO = titulosRepository.consultarTitulosOperacao(filtrosRelatorioDTO);
		}catch (CobrancaRelatoriosSrmRepositoryException e) {
			throw e;
		}catch (Exception e) {
			throw new CobrancaRelatoriosSrmServiceException(new StringBuilder(Mensagens.SERVICE_ERRO_CONSULTAR_TITULOS_OPERACAO)
				.append(e.getMessage()).toString(), CobrancaRelatoriosError.ERROR_COBRANCA_SERVICE);
		}
		return listaTitulosDTO;
	}
	
	/**
	 * Consulta todos titulos atendimentos
	 * @return List<TitulosDTO>  - Lista de Titulos atendimentos
	 */
	public List<TitulosAtendimentosDTO> consultarTitulosAtendimentos(FiltrosRelatorioDTO filtrosRelatorioDTO) {
		List<TitulosAtendimentosDTO> listaTitulosAtendimentosDTO = new ArrayList<>();
		try {
			listaTitulosAtendimentosDTO = converte(titulosRepository.consultarTitulosVencidosVincendos(filtrosRelatorioDTO));
			
			List<AtendimentoCobrancaIntegracaoDTO> listCobranca = atendimentoCobrancaService.consultarHistoricoAtendimentoCobranca(filtrosRelatorioDTO);
			List<AtendimentoJuridicoIntegracaoDTO> listJuridico = atendimentoJuridicoService.consultarHistoricoAtendimentoJuridico(filtrosRelatorioDTO);
			
			addCobranca(listaTitulosAtendimentosDTO, listCobranca);
			
		}catch (CobrancaRelatoriosSrmRepositoryException e) {
			throw e;
		}catch (Exception e) {
			throw new CobrancaRelatoriosSrmServiceException(new StringBuilder(Mensagens.SERVICE_ERRO_CONSULTAR_TITULOS_OPERACAO)
				.append(e.getMessage()).toString(), CobrancaRelatoriosError.ERROR_COBRANCA_SERVICE);
		}
		return listaTitulosAtendimentosDTO;
	}
	
	private List<TitulosAtendimentosDTO> converte(List<TitulosVencidosVincendosDTO> listaTitulosVencidosVincendosDTO){
		List<TitulosAtendimentosDTO> lista = new ArrayList<>();
		listaTitulosVencidosVincendosDTO.forEach(titulo -> {
			TitulosAtendimentosDTO dto = new TitulosAtendimentosDTO();
			BeanUtils.copyProperties(titulo, dto);
			lista.add(dto);
		});
		return lista;
	}
	
	private List<TitulosAtendimentosDTO> addCobranca(List<TitulosAtendimentosDTO> listaTitulosAtendimentosDTO,List<AtendimentoCobrancaIntegracaoDTO> listCobranca){
		
		listaTitulosAtendimentosDTO.forEach(atendimentos -> {
			
			List<AtendimentoCobrancaIntegracaoDTO> result = listCobranca.stream().filter(cobranca -> cobranca.getIdCedente().equals(atendimentos.getIdentificador()))
	                .collect(Collectors.toList());

			atendimentos.setListAtendimentoCobranca(result);
			
			
		});
		return listaTitulosAtendimentosDTO;
	}

	
}
