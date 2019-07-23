package com.trusthub.cobranca.application.business.relatorios.excel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trusthub.cobranca.application.exceptions.CobrancaRelatoriosSrmBusinessException;
import com.trusthub.cobranca.application.exceptions.CobrancaRelatoriosSrmRepositoryException;
import com.trusthub.cobranca.application.exceptions.CobrancaRelatoriosSrmServiceException;
import com.trusthub.cobranca.application.service.titulos.TitulosService;
import com.trusthub.cobranca.application.util.Constantes;
import com.trusthub.cobranca.application.util.Mensagens;
import com.trusthub.cobranca.application.util.UtilExcel;
import com.trusthub.cobranca.application.validation.CobrancaRelatoriosError;
import com.trusthub.cobranca.configuration.validation.generic.layers.TrustHubBusinessException;
import com.trusthub.cobranca.domain.dto.FiltrosRelatorioDTO;
import com.trusthub.cobranca.domain.dto.RelatorioDTO;
import com.trusthub.cobranca.domain.dto.excel.Celulas;

/**
 * Camada de negocio que centraliza as regras de titulo. 
 * @author jose.viana
 */
@Component
public class RelatorioExcelBusiness{

	@Autowired
	private TitulosService titulosService;

	/**
	 * Consulta todos relatorios
	 * @return RelatorioDTO  - Carrega relatorio conforme ID
	 */
	public RelatorioDTO consultarProcessarGerarRelatorio(FiltrosRelatorioDTO filtrosRelatorioDTO) {
		RelatorioDTO dto = new RelatorioDTO();
		List<?> listaTitulosDTO = new ArrayList<>();
		String nomeTemplate = "";
		try {
			switch (filtrosRelatorioDTO.getId()) {
				case 1:
					listaTitulosDTO = titulosService.consultarTitulosLiquidados(filtrosRelatorioDTO);
					nomeTemplate = Constantes.SHEET_LIQUIDADOS;
					break;
				case 2:
					listaTitulosDTO = titulosService.consultarTitulosVencidosVincendos(filtrosRelatorioDTO);
					nomeTemplate = Constantes.SHEET_VENCIDOS_VINCENDOS;
					break;
				case 3:
					listaTitulosDTO = titulosService.consultarTitulosOperacao(filtrosRelatorioDTO);
					nomeTemplate = Constantes.SHEET_OPERACAO;
					break;
				case 4:
					listaTitulosDTO = titulosService.consultarTitulosAtendimentos(filtrosRelatorioDTO);
					nomeTemplate = Constantes.SHEET_OPERACAO;
					break;							
				default:
					break;
			}
			dto = this.processaGeraArquivo(listaTitulosDTO, nomeTemplate, filtrosRelatorioDTO);
		}catch (CobrancaRelatoriosSrmRepositoryException | CobrancaRelatoriosSrmServiceException | CobrancaRelatoriosSrmBusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new CobrancaRelatoriosSrmBusinessException(new StringBuilder(Mensagens.BUSINESS_ERRO_PROCESSA_GERAR)
					.append(e.getMessage()).toString(), CobrancaRelatoriosError.ERROR_COBRANCA_BUSINESS);
		}
		return dto;
	}
	
	
	/**
	 * Processa e gera o arquivo
	 * @param listaTitulosDTO
	 * @param nomeTemplate
	 * @param nomeAtributo
	 * @param orientacao
	 * @return
	 */
	private RelatorioDTO processaGeraArquivo(List<?> listaTitulosDTO, String nomeTemplate, FiltrosRelatorioDTO filtrosRelatorioDTO) {
		RelatorioDTO dto = new RelatorioDTO();
		try {
			if (!listaTitulosDTO.isEmpty()){
				UtilExcel util = new UtilExcel();
				if(filtrosRelatorioDTO.getAtributo() == null || filtrosRelatorioDTO.getAtributo().equals("")){
					filtrosRelatorioDTO.setAtributo(Constantes.ORDER_COLUNA_DATA_VENCIMENTO);
				}
				List<Celulas> lista = util.processaElementos(listaTitulosDTO, filtrosRelatorioDTO);
				byte[] fileContent = util.gerarArquivo(lista, nomeTemplate);
				dto.setFileContent(fileContent);
			}
		} catch (TrustHubBusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new CobrancaRelatoriosSrmBusinessException(new StringBuilder(Mensagens.BUSINESS_ERRO_PROCESSA_GERAR)
					.append(e.getMessage()).toString(), CobrancaRelatoriosError.ERROR_COBRANCA_BUSINESS);
		}
		return dto;
	}
	
}
