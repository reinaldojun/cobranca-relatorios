package com.trusthub.cobranca.domain.dto.titulos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.trusthub.cobranca.domain.dto.atendimento.AtendimentoCobrancaIntegracaoDTO;
import com.trusthub.cobranca.domain.dto.atendimento.AtendimentoJuridicoIntegracaoDTO;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class TitulosAtendimentosDTO implements Serializable{
	
	@JsonProperty("sigla")
	private String sigla;
	
	@JsonProperty("identificador")
	private String identificador;
	
	@JsonProperty("nome")
	private String nome;
	
	@JsonProperty("codigoOperacao")
	private Long codigoOperacao;
	
	@JsonProperty("dataOperacao")  
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private Date dataOperacao;
	
	@JsonProperty("totalBruto")
	private double totalBruto;
	
	@JsonProperty("totalLiquido")
	private double totalLiquido;
	
	@JsonProperty("codigoTitulo")
	private Integer codigoTitulo;
	
	@JsonProperty("valorFace")
	private double valorFace;
	
	@JsonProperty("dataVencimento")  
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private Date dataVencimento;
	
	@JsonProperty("statusLiquidacao")
	private String statusLiquidacao;
	
	@JsonProperty("atendimentosCobranca")
	private List<AtendimentoCobrancaIntegracaoDTO> listAtendimentoCobranca;
	
	@JsonProperty("atendimentosJuridico")
	private List<AtendimentoJuridicoIntegracaoDTO> listAtendimentoJuridico;

}
