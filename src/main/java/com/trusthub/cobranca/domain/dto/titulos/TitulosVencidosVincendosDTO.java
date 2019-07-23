package com.trusthub.cobranca.domain.dto.titulos;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class TitulosVencidosVincendosDTO implements Serializable{
	
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
	
	@JsonProperty("totalDesagio")
	private double totalDesagio;
	
	@JsonProperty("totalTarifaCobranca")
	private double totalTarifaCobranca;
	
	@JsonProperty("totalAdValores")
	private double totalAdValores;
	

	@JsonProperty("totalTaxaServico")
	private double totalTaxaServico;
	
	@JsonProperty("totalIof")
	private double totalIof;
	
	@JsonProperty("totalImpostos")
	private double totalImpostos;
	
	@JsonProperty("totalPendencia")
	private double totalPendencia;

	@JsonProperty("prazoMedio")
	private Integer prazoMedio;
	
	@JsonProperty("taxaEfetiva")
	private double taxaEfetiva;
	
	@JsonProperty("quantidadeTitulos")
	private Integer quantidadeTitulos;
	
	@JsonProperty("totalTac")
	private double totalTac;
	
	@JsonProperty("prazoMedioDeMais")
	private double prazoMedioDeMais;
	
	@JsonProperty("codigoTitulo")
	private Integer codigoTitulo;
	
	@JsonProperty("valorFace")
	private double valorFace;
	
	@JsonProperty("dataVencimento")  
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private Date dataVencimento;
	
	@JsonProperty("statusLiquidacao")
	private String statusLiquidacao;

}
