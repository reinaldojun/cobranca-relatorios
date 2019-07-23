package com.trusthub.cobranca.application.util;


/**
 * Inteface que centraliza as as mensagens utilizada no cobranca relatorio
 * @author alan.franco/jose.viana
 */
public interface Mensagens {
	
	
	//BUSINESS
	public static final String  BUSINESS_ERRO_CONSULTAR_DOCUMENTO_CLIENTE =	"Erro ao consultar documento cliente no método (DocumentoClientelntegracaoBusiness.consultarDocumentoCliente): ";
	public static final String  BUSINESS_ERRO_CONSULTAR_HISTORICO_COBRANCA =	"Erro ao consultar historico cobranca por filtro no método (AtendimentoCobrancaIntegracaoBusiness.consultarHistoricoCobranca): ";
	public static final String  BUSINESS_ERRO_CONSULTAR_HISTORICO_JURIDICO =	"Erro ao consultar historico juridico por filtro no método (AtendimentoJuridicoIntegracaoBusiness.consultarHistoricoJuridico): ";
	public static final String  BUSINESS_ERRO_ORDER =	"Erro ao ordernar no método (genericSorted.order): ";
	public static final String  BUSINESS_ERRO_PROCESSA_GERAR =	"Erro ao processar e gerar arquivo no método (RelatorioExcelBusiness.processaGeraArquivo): ";
	public static final String  BUSINESS_ERRO_UTIL_EXCEL =	"Erro ao processar no método (UtilExcel.processaElementos): ";
	public static final String  BUSINESS_ERRO_GERAR_EXCEL =	"Erro ao gerar execel no método (UtilExcel.gerarExcel): ";
	public static final String  BUSINESS_ERRO_GERAR_ARQUIVO =	"Erro ao gerar arquivo no método (UtilExcel.gerarArquivo): ";
	public static final String  BUSINESS_ERRO_CARREGAR_TEMPLATE =	"Erro ao gerar arquivo no método (UtilExcel.carregarTemplate): ";
	public static final String  BUSINESS_ERRO_COVERTER_INPUTSTREAM_BYTES ="Erro ao converter inputstream para bytes no método (Util.toByteArray): ";
	
	//SERVICE
	public static final String SERVICE_ERRO_CONSULTAR_TITULOS_LIQUIDADOS = "Erro consultar titulos liquiddos no método (TitulosService.consultarTitulosLiquidados): ";
	public static final String SERVICE_ERRO_CONSULTAR_TITULOS_VENCIDOS_VINCENDOS = "Erro consultar titulos vencidos e a vencer no método (TitulosService.consultarTitulosVencidosVincendos): ";
	public static final String SERVICE_ERRO_CONSULTAR_TITULOS_OPERACAO = "Erro consultar titulos operacao no método (TitulosService.consultarTitulosOperacao): ";
	public static final String SERVICE_ERRO_VALIDAR_RESPOSTA_CONSULTAR_ATENDIMENTO_COBRANCA = "Erro ao validar resposta consultar atendimentos cobranca no método (AtendimentoCobrancaService.validarRespostaListAtendimentoCobranca); ";
	public static final String SERVICE_ERRO_VALIDAR_RESPOSTA_CONSULTAR_ATENDIMENTO_JURIDICO = "Erro ao validar resposta consultar atendimentos cobranca no método (AtendimentoJuridicoService.validarRespostaListAtendimentoJuridico); ";
	public static final String SERVICE_ERRO_CONSULTAR_HISTORICO_COBRANCA = "Erro ao consultar historico cobranca por filtro no método (AtendimentoCobrancaService.consultarHistoricoAtendimentoCobranca): ";
	public static final String SERVICE_ERRO_CONSULTAR_HISTORICO_JURIDICO = "Erro ao consultar historico juridico por filtro no método (AtendimentoJuridicoService.consultarHistoricoAtendimentoJuridico): ";
	
	
	
	//REPOSITORY
	public static final String REPOSITORY_ERRO_CONSULTAR_TITULOS_LIQUIDADOS = "Erro ao consultar titulos liquidados no método (TitulosRepository.consultarTitulosLiquidaddos)";
	public static final String REPOSITORY_ERRO_CONSULTAR_TITULOS_VENCIDOS_VINCENDOS = "Erro ao consultar titulos vencidos e a vencer no método (TitulosRepository.consultarTitulosVencidosVincendos)";
	public static final String REPOSITORY_ERRO_CONSULTAR_TITULOS_OPERACAO = "Erro ao consultar titulos operacao no método (TitulosRepository.consultarTitulosOperacao)";
	
	//SECURITY
	public static final String  SECURITY_NO_AUTHORIZATION = "Authorization nulo ou vazio.";
	public static final String  SECURITY_TOKEN_INVALID = "Token Invalido.";
	public static final String  SECURITY_PERFIL_NO_AUTHORIZATION = "Perfil não autorizado.";
	public static final String  SECURITY_VALIDAR_TOKEN = "Erro no na validação do token no método: (CobrancaRelatoriosSecurityService.validarToken)";
	public static final String  SECURITY_ERRO_VALIDAR_RESPOSTA_VALIDAR_TOKEN = "Erro ao validar resposta da validacao do token no metodo: (CobrancaRelatoriosSecurityService.validarRespostaValidarToken)";

}