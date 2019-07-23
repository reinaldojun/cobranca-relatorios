package com.trusthub.cobranca.application.business.titulos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trusthub.cobranca.application.service.titulos.TitulosService;
import com.trusthub.cobranca.domain.dto.FiltrosRelatorioDTO;
import com.trusthub.cobranca.domain.dto.titulos.TitulosLiquidadosDTO;
import com.trusthub.cobranca.domain.dto.titulos.TitulosOperacaoDTO;
import com.trusthub.cobranca.domain.dto.titulos.TitulosVencidosVincendosDTO;

/**
 * Camada de negocio que centraliza as regras de titulo. 
 * @author jose.viana
 */
@Component
public class TituloBusiness{

	@Autowired
	private TitulosService titulosService;

	/**
	 * Consulta todos titulos liquidados
	 * @return List<TitulosDTO>  - Lista de Titulos Liquidados
	 */
	public List<TitulosLiquidadosDTO>  consultarTitulosLiquidados(FiltrosRelatorioDTO filtrosRelatorioDTO) {
		return titulosService.consultarTitulosLiquidados(filtrosRelatorioDTO);
	}
	
	/**
	 * Consulta todos titulos vencidos e a vencer
	 * @return List<TitulosDTO>  - Lista de titulos vencidos e a vencer
	 */
	public List<TitulosVencidosVincendosDTO>  consultarTitulosVencidosVincendos(FiltrosRelatorioDTO filtrosRelatorioDTO) {
		return titulosService.consultarTitulosVencidosVincendos(filtrosRelatorioDTO);
	}
	
	/**
	 * Consulta todos titulos operacao
	 * @return List<TitulosDTO>  - Lista de Titulos Operacao
	 */
	public List<TitulosOperacaoDTO>  consultarTitulosOperacao(FiltrosRelatorioDTO filtrosRelatorioDTO) {
		return titulosService.consultarTitulosOperacao(filtrosRelatorioDTO);
	}
	
	
	
	
}
