package com.trusthub.cobranca.domain.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * DTO - Filtros do relatorio  
 * @author alan.franco
 */
@Data
public class FiltrosRelatorioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "Identificador do relátorio")
	@JsonProperty("id")
	@NotNull
	private Integer id;
	
	@ApiModelProperty(value = "Data Inicial")
	@JsonProperty("dataInicial")  
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private Date dataInicial; 

	@ApiModelProperty(value = "Data Final")
	@JsonProperty("dataFinal")
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private Date dataFinal; 
	
	@ApiModelProperty(value = "Atributo (nome da coluna da tabela para ordenação)")
	@JsonProperty("atributo")  
	private String atributo;

	@ApiModelProperty(value = "Orientação (ASC ou DESC)")
	@JsonProperty("orientacao")  
	private String orientacao;
	
}
