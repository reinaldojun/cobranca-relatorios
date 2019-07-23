package com.trusthub.cobranca.domain.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * DTO- Dados de resposta  
 * @author jose.viana
 */
@Data
public class RelatorioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "Conteúdo Arquivo")
	@JsonProperty("arquivo_conteudo")
	private byte[] fileContent;


}
