package com.trusthub.cobranca.application.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.trusthub.cobranca.application.exceptions.CobrancaRelatoriosSrmBusinessException;
import com.trusthub.cobranca.application.validation.CobrancaRelatoriosError;
import com.trusthub.cobranca.application.validation.GenericSortedException;
import com.trusthub.cobranca.application.validation.UtilExcelException;
import com.trusthub.cobranca.domain.dto.FiltrosRelatorioDTO;
import com.trusthub.cobranca.domain.dto.excel.Celulas;
import com.trusthub.cobranca.domain.dto.excel.Colunas;


public class UtilExcel {
	
	/**
	 * Processar do DTO para objeto do Excel
	 * @param elements
	 * @param nomeAtributo
	 * @param orientacao
	 * @return
	 */
	public List<Celulas> processaElementos(List<?> elements, FiltrosRelatorioDTO filtrosRelatorioDTO){
		List<Celulas> lista = new ArrayList<>();
		try {
			AtomicInteger row = new AtomicInteger(4);
			elements.parallelStream()
							 .sorted((p1, p2) ->  {
							  	 return GenericSorted.order(p1, p2, filtrosRelatorioDTO.getAtributo(), filtrosRelatorioDTO.getOrientacao());
							 })
							.forEachOrdered(element -> {
								List<Colunas> colunas = new ArrayList<>();
								Celulas celula = new Celulas();
								celula.setLinha(row.getAndIncrement());
								AtomicInteger colun = new AtomicInteger(0);
								Arrays.asList(element.getClass().getDeclaredFields()).parallelStream()
									.forEachOrdered(fields -> {
										 try {
										    	Colunas coluna = new Colunas();
										    	fields.setAccessible(true);
											    coluna.setValor(fields.get(element));
											    coluna.setNumero(colun.getAndIncrement());
											    colunas.add(coluna);
											} catch (IllegalArgumentException | IllegalAccessException e) {
												e.getMessage();
											}
									});
								celula.setColunas(colunas);
							   	lista.add(celula);
							});
		} catch (GenericSortedException g) {
			throw g;
		} catch (Exception e2) {
			throw new UtilExcelException(new StringBuilder(Mensagens.BUSINESS_ERRO_UTIL_EXCEL)
					.append(e2.getMessage()).toString(), CobrancaRelatoriosError.ERROR_COBRANCA_BUSINESS);
		}
		return lista;
	}
	
	
	/**
	 * Gerar arquivo
	 * @param celulas
	 * @param nomeTemplate
	 * @return
	 */
	public byte[] gerarArquivo(List<Celulas> celulas, String nomeTemplate) {
		XSSFWorkbook wb;
		byte[] byteArray = null;
		try {
            wb = removeOtherSheets(nomeTemplate, carregarTemplate());
           	ByteArrayOutputStream outStream = new ByteArrayOutputStream();
   		    wb =  gerarExcel(wb,celulas);
   		    wb.write(outStream);
   		    byteArray = outStream.toByteArray();
		} catch (UtilExcelException u) {
			throw u;
		} catch (Exception e) {
			throw new UtilExcelException(new StringBuilder(Mensagens.BUSINESS_ERRO_GERAR_ARQUIVO)
					.append(e.getMessage()).toString(), CobrancaRelatoriosError.ERROR_COBRANCA_BUSINESS);
		}
		return byteArray;
	}
	
	/**
	 * Gerar o Excel
	 * @param workbook
	 * @param celulas
	 * @return
	 */
	public XSSFWorkbook gerarExcel(XSSFWorkbook workbook, List<Celulas> celulas) {
		try {
			//Formatos
			DataFormat format = workbook.createDataFormat();
			//Style Date
			CellStyle styleDate = workbook.createCellStyle();
			styleDate.setDataFormat(format.getFormat(Constantes.DATE_FORMADO_DD_MM_YYYY));
			styleDate.setAlignment(HorizontalAlignment.CENTER);
			//Style Money
			CellStyle styleMoney = workbook.createCellStyle();
			styleMoney.setDataFormat(format.getFormat(Constantes.FORMADO_MONEY));
			celulas.stream().forEach(celula ->  {
				XSSFRow row = workbook.getSheetAt(0).createRow(celula.getLinha());
				celula.getColunas().stream().forEach(coluna -> {
					 Object valor = coluna.getValor();
					 if (valor instanceof Date) {
						 row.createCell(coluna.getNumero()).setCellValue((Date) valor);
						 row.getCell(coluna.getNumero()).setCellStyle(styleDate);
					 } else if (valor instanceof String) {
						 row.createCell(coluna.getNumero()).setCellValue((String) valor);
					 } else if (valor instanceof Long) {
						 row.createCell(coluna.getNumero()).setCellValue((Long) valor);
					 } else if (valor instanceof Integer) {
						 row.createCell(coluna.getNumero()).setCellValue((Integer) valor);
					 } else if (valor instanceof Double) {
						 row.createCell(coluna.getNumero()).setCellValue((Double) valor);
						 row.getCell(coluna.getNumero()).setCellStyle(styleMoney);
					 } else if (valor instanceof Float) {
						 row.createCell(coluna.getNumero()).setCellValue((Float) valor);
						 row.getCell(coluna.getNumero()).setCellStyle(styleMoney);
					 }
				});
				
			});
		} catch (Exception e) {
			throw new UtilExcelException(new StringBuilder(Mensagens.BUSINESS_ERRO_GERAR_EXCEL)
					.append(e.getMessage()).toString(), CobrancaRelatoriosError.ERROR_COBRANCA_BUSINESS);
		}
        return workbook;
	}
	
	
	
	/**
	 * Carregar template
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public XSSFWorkbook carregarTemplate() throws IOException {
		InputStream input = null;
		try {
			String encode = TemplateExcel.RELATORIOS;
			byte[] orderTemplate = encode.getBytes();
			byte[] fileIn  = Base64.getDecoder().decode(orderTemplate);
			input = new ByteArrayInputStream(fileIn);
		} catch (Exception e) {
			throw new CobrancaRelatoriosSrmBusinessException(new StringBuilder(Mensagens.BUSINESS_ERRO_CARREGAR_TEMPLATE)
					.append(e.getMessage()).toString(), CobrancaRelatoriosError.ERROR_COBRANCA_BUSINESS);
		}
		return new XSSFWorkbook(input);
	}
	
	/**
	 * Criar txt
	 */
	public void criarTxtBase64() {
		try {
			InputStream input = new FileInputStream("src/main/resources/template_excel/template_excel.xlsx");
			byte[] arquivo = Util.toByteArray(input);
	        String encode = Base64.getEncoder().encodeToString(arquivo);
			FileOutputStream output = new FileOutputStream("C:/templateExcel.txt");
			InputStream targetStream = new ByteArrayInputStream(encode.getBytes());
			output.write(Util.toByteArray(targetStream));
			output.close();
		} catch (Exception e) {
			throw new CobrancaRelatoriosSrmBusinessException(new StringBuilder(Mensagens.BUSINESS_ERRO_CARREGAR_TEMPLATE)
					.append(e.getMessage()).toString(), CobrancaRelatoriosError.ERROR_COBRANCA_BUSINESS);
		}
		
	}
	
	
	/**
	 * Remover abas.
	 * @param sheetName
	 * @param book
	 * @return
	 */
	private XSSFWorkbook removeOtherSheets(String sheetName, XSSFWorkbook book) {     
        for(int i=book.getNumberOfSheets()-1; i >= 0; i--){
            XSSFSheet tmpSheet =book.getSheetAt(i);
            if(!tmpSheet.getSheetName().equals(sheetName)){
                book.removeSheetAt(i);
            }
        }     
        return book;
   }

}
