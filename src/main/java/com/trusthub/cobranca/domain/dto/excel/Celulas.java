package com.trusthub.cobranca.domain.dto.excel;

import java.util.List;

import lombok.Data;

@Data
public class Celulas {
	
	private Integer linha;
	
	private List<Colunas> colunas;

}
