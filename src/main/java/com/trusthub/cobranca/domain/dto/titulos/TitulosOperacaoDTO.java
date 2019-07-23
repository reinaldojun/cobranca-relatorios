package com.trusthub.cobranca.domain.dto.titulos;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class TitulosOperacaoDTO implements Serializable{
	
	@JsonProperty("identificador")
	private String identificador;
	
	@JsonProperty("nome")
	private String nome;
	
	@JsonProperty("codigoOperacao")
	private Long codigoOperacao;
	
	@JsonProperty("dataOperacao")  
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private Date dataOperacao;
	
	@JsonProperty("dataVencimento")  
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private Date dataVencimento;
	
	@JsonProperty("dataLiquidacao")  
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private Date dataLiquidacao;
	
	@JsonProperty("siglaProduto")
	private String siglaProduto;

	@JsonProperty("totalAberto")
	private double totalAberto;
	
	@JsonProperty("totalLiquidado")
	private double totalLiquidado;
	
	@JsonProperty("parcelas")
	private Integer parcelas;
	
}
