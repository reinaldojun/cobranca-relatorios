package com.trusthub.cobranca.controller.titulos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trusthub.cobranca.application.business.relatorios.excel.RelatorioExcelBusiness;
import com.trusthub.cobranca.domain.dto.FiltrosRelatorioDTO;
import com.trusthub.cobranca.domain.dto.RelatorioDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller que disponibiliza api de arquivo
 * @author jose.viana
 */
@RestController
@CrossOrigin(value = "*")
@RequestMapping("/trusthub-cobranca-relatorios/v1")
@Api(value = "API - Relatorios  titulos Excel - Serviço que realiza consulta e geração de relatórios.")
public class TitulosRelatoriosController {
	
	@Autowired
	private RelatorioExcelBusiness relatorioBusiness;
	
	@ApiOperation(value = "Consultar, Processar e Gerar relatório")
	@ApiResponses(
			value= {
					@ApiResponse(code = 200 , message = "Sucessfull"),
					@ApiResponse(code = 401 , message = "Unauthorized"),
					@ApiResponse(code = 403 , message = "Access denied"),
					@ApiResponse(code = 500, message = "Erro Internal Server Error: Contact your support \n"
														+ "Mensagem de erro:  { timestamp, errorCode, errorDescription, message, path  }  \n"							
														+ "  - errorCode: 1000 - Erro na api (Cobrança Relatórios) na camada de Business \n"
														+ "  - errorCode: 2000 - Erro na api (Cobrança Relatórios) na camada de Service \n"
														+ "  - errorCode: 3000 - Erro na api (Cobrança Relatórios) na camada de Acesso a dados \n"
														+ "  - errorCode: 6 - Erro na api (Cobrança Relatórios) com integração com o componente cobranca acesso \n"
							)
			}
	)
	@PostMapping("/titulos/download")
	public ResponseEntity<RelatorioDTO> consultarProcessarGerarRelatorio(@RequestBody FiltrosRelatorioDTO filtrosRelatorioDTO) {
		RelatorioDTO documentDto = relatorioBusiness.consultarProcessarGerarRelatorio(filtrosRelatorioDTO);
		return ResponseEntity.ok().body(documentDto);
	}
}

