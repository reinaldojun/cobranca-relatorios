package com.trusthub.cobranca.application.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.trusthub.cobranca.application.exceptions.CobrancaRelatoriosSrmRepositoryException;
import com.trusthub.cobranca.application.util.Mensagens;
import com.trusthub.cobranca.application.validation.CobrancaRelatoriosError;
import com.trusthub.cobranca.configuration.repository.GenericJdbcTemplate;
import com.trusthub.cobranca.domain.dto.FiltrosRelatorioDTO;
import com.trusthub.cobranca.domain.dto.titulos.TitulosLiquidadosDTO;
import com.trusthub.cobranca.domain.dto.titulos.TitulosOperacaoDTO;
import com.trusthub.cobranca.domain.dto.titulos.TitulosVencidosVincendosDTO;

/**
 * Camada Repository que acessa os dados de titulos 
 * @author jose.viana
 */
@Repository
public class TitulosRepository {
	
	@Autowired
	private GenericJdbcTemplate genericJdbcTemplate;
	
	/**
	 * Consulta todos titulos liquidados
	 * @return List<TitulosDTO>  - Lista de Titulos Liquidadados 
	 */
	public List<TitulosLiquidadosDTO> consultarTitulosLiquidados(FiltrosRelatorioDTO filtrosRelatorioDTO) {
		List<TitulosLiquidadosDTO> retorno = new ArrayList<>();
		try {
			MapSqlParameterSource parametro = new MapSqlParameterSource();
			parametro.addValue("dataInicial", filtrosRelatorioDTO.getDataInicial());
			parametro.addValue("dataFinal", filtrosRelatorioDTO.getDataFinal());
			retorno = genericJdbcTemplate.queryForSQLName("buscar_titulos_liquidados_thdup_themp", parametro, BeanPropertyRowMapper.newInstance(TitulosLiquidadosDTO.class));
		}catch (Exception e) {
			throw new CobrancaRelatoriosSrmRepositoryException(new StringBuilder(Mensagens.REPOSITORY_ERRO_CONSULTAR_TITULOS_LIQUIDADOS)
					.append(e.getMessage()).toString(), CobrancaRelatoriosError.ERROR_COBRANCA_REPOSITORY);
		}
		return retorno;
	}
	

	/**
	 * Consulta todos titulos vencidos e a vencer
	 * @return List<TitulosDTO>  - Lista de Titulos vencidos e a vencer
	 */
	public List<TitulosVencidosVincendosDTO> consultarTitulosVencidosVincendos(FiltrosRelatorioDTO filtrosRelatorioDTO) {
		List<TitulosVencidosVincendosDTO> retorno = new ArrayList<>();
		try {
			MapSqlParameterSource parametro = new MapSqlParameterSource();
			parametro.addValue("dataInicial", filtrosRelatorioDTO.getDataInicial());
			parametro.addValue("dataFinal", filtrosRelatorioDTO.getDataFinal());
			retorno = genericJdbcTemplate.queryForSQLName("buscar_titulos_vencidos_vincendos_thdup_themp",parametro, BeanPropertyRowMapper.newInstance(TitulosVencidosVincendosDTO.class));
		}catch (Exception e) {
			throw new CobrancaRelatoriosSrmRepositoryException(new StringBuilder(Mensagens.REPOSITORY_ERRO_CONSULTAR_TITULOS_VENCIDOS_VINCENDOS)
					.append(e.getMessage()).toString(), CobrancaRelatoriosError.ERROR_COBRANCA_REPOSITORY);
		}
		return retorno;
	}
	
	/**
	 * Consulta todos titulos operacao
	 * @return List<TitulosDTO>  - Lista de Titulos operacao
	 */
	public List<TitulosOperacaoDTO> consultarTitulosOperacao(FiltrosRelatorioDTO filtrosRelatorioDTO) {
		List<TitulosOperacaoDTO> retorno = new ArrayList<>();
		try {
			MapSqlParameterSource parametro = new MapSqlParameterSource();
			parametro.addValue("dataInicial", filtrosRelatorioDTO.getDataInicial());
			parametro.addValue("dataFinal", filtrosRelatorioDTO.getDataFinal());
			retorno = genericJdbcTemplate.queryForSQLName("buscar_titulos_operacao_thdup_themp", parametro, BeanPropertyRowMapper.newInstance(TitulosOperacaoDTO.class));
		}catch (Exception e) {
			throw new CobrancaRelatoriosSrmRepositoryException(new StringBuilder(Mensagens.REPOSITORY_ERRO_CONSULTAR_TITULOS_OPERACAO)
					.append(e.getMessage()).toString(), CobrancaRelatoriosError.ERROR_COBRANCA_REPOSITORY);
		}
		return retorno;
	}	
	

}
