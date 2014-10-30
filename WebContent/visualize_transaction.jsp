<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movimentações Financeiras - Campanhas-ON</title>
<script src="script/sorttable.js"></script>
<link rel="shortcut icon" href=img/favicon.ico type="image/x-icon" />
<link href="css/layout.css" rel="stylesheet" type="text/css" media="all">
<link href="css/menu.css" rel="stylesheet" type="text/css" media="all">
<link href="css/rodape.css" rel="stylesheet" type="text/css" media="all">
<link href="css/conteudoInformacoes.css" rel="stylesheet"
	type="text/css" media="all">
<link href="css/tabelas.css" rel="stylesheet" type="text/css"
	media="all">
</head>

<body>

	<%@include file="imports/header_candidate.jsp"%>

	<div id="pagina">
		<div class="titulo_topo" id="tt_movimentacao">
			<h3>Movimentação</h3>
		</div>
		<div id="conteudo_informacoes">
			<div class="informacoes">

				<div id="perfilCandidatoAno">
					<div id="fotoPerfil">
						<img src="img/perfil.jpg" width="140" height="150" />
					</div>
					<h1>
						<c:out value="${campaign.campaignNameOfUrn}" />
					</h1>

					<!-- Mostrar na tela os dados do Candidato -->
					<br>
					<table>
						<tr>
							<td width="200"><b>Nome do Candidato:</b></td>
							<td><c:out value="${campaign.campaignCandidate.candidateName}" /></td>
						</tr>
						<tr>
							<td><b>Número do Candidato:</b></td>
							<td><c:out value="${campaign.campaignCandidateNumber}" /></td>
						</tr>
						<tr>
							<td><b>Cargo Pleiteado:</b></td>
							<td><c:out value="${campaign.campaignPosition.positionDescription}" /></td>
						</tr>
						<tr>
							<td><b>Ano:</b></td>
							<td><c:out value="${campaign.campaignYear}" /></td>
						</tr>
						<tr>
							<td><b>Resultado:</b></td>
							<td><c:out value="${campaign.campaignResult.resultDescription}" /></td>
						</tr>
						<tr>
							<td><b>Despesa Máxima Declarada:</b></td>
							<td><c:out value="${totalExpense}" /></td>
						</tr>
						<tr>
							<td><b>Saldo da Campanha:</b></td>
							<td><c:out value="${totalRevenueCalculatedValue - totalExpenseCalculatedValue}" /></td>
						</tr>
					</table>
				</div>


				<!-- Tabela de receitas -->
				<center>
					<h4>
						<br> <br> <br>Receitas:
					</h4>

					<c:choose>
						<c:when test="${empty revenueList}">
							<p>Não há receitas declaradas.</p>
						</c:when>
						<c:otherwise>
							<table class="sortable" id="gradient-style-movimentacaor"
								summary="Meeting Results" cellpadding="10" align="center">
								<tr>
									<th width="100">Data</th>
									<th width="600">Detalhamento da Receita</th>
									<th width="100">Valor (R$)</th>
								</tr>

								<c:forEach var="revenue" items="${revenueList}"
									begin="${firstRevenue}" end="${firstRevenue+(quantityRevenuePerPage-1)}"
									varStatus="listagemR">
									<tr>
										<td>${revenue.financialTransactionDate}</td>
										<td><details> <summary>${revenue.financialTransactionType}</summary>
											<table border="0">
												<tr>
													<td colspan="2">Nome do Doador: <c:out
															value="${revenue.revenueDonor.donorName}" />
													</td>
												</tr>
												<tr>
													<td colspan="1">CPJ/CNPJ do Doador: <c:out
															value="${revenue.revenueDonor.donorPersonRegister}" />
													</td>
													<td colspan="1">UF do Doador: <c:out
															value="${revenue.revenueDonor.donorCountryState}" />
													</td>
												</tr>
												<tr>
													<td colspan="1">Número do Documento: <c:out
															value="${revenue.financialTransactionDocumentNumber}" />
													</td>
													<td colspan="1">Situação Cadastral do Doador: <c:out
															value="${revenue.revenueDonor.donorRegisterSituation}" />
													</td>
												</tr>
												<tr>
													<td colspan="1">Forma de Pagamento: <c:out
															value="${revenue.financialTransactionPaymentType}" />
													</td>
													<td colspan="1">Recibo Eleitoral: <c:out
															value="${revenue.revenueElectoralReceipt}" />
													</td>
												</tr>
												<tr>
													<td colspan="2" width="600" align="justify">Descrição:
														<c:out value="${revenue.financialTransactionDescription}" />
													</td>
												</tr>
											</table>
											</details></td>
										<td>${revenue.financialTransactionPrice}</td>
									</tr>
								</c:forEach>
								<tr>
									<td colspan="2" align="right">Receita Total Calculada:</td>
									<td>${totalRevenueCalculatedValue}</td>
								</tr>

								<tfoot>
									<tr>
										<td colspan="4"><center>
												Páginas:
												<c:url var="url_pagInicialR" value="/mvc">
													<c:param name="logic"
														value="RequestFinancialTransactionsOfCandidate" />
													<c:param name="totalExpenseCalculatedValue" value="${totalExpenseCalculatedValue}" />
													<c:param name="totalRevenueCalculatedValue" value="${totalRevenueCalculatedValue}" />
													<c:param name="campaignCandidateNumber"
														value="${campaign.campaignCandidateNumber}" />
													<c:param name="campaignYear" value="${campaign.campaignYear}" />
													<c:param name="campaignPosition" value="${campaign.campaignPosition.positionCode}" />
													<c:param name="campaignCountryState" value="${campaign.campaignCountryState}" />
													<c:param name="firstRevenue" value="${0}" />
													<c:param name="quantityRevenuePerPage" value="${quantityRevenuePerPage}" />
													<c:param name="totalRevenueCalculatedValue" value="${false}" />
													<c:param name="centerOfRevenue" value="${1}" />
													<c:param name="firstExpense" value="${firstExpense}" />
													<c:param name="quantityExpensePerPage" value="${quantityExpensePerPage}" />
													<c:param name="totalExpenseCalculatedValue" value="${totalExpenseCalculatedValue}" />
													<c:param name="centerOfExpense" value="${centerOfExpense}" />
												</c:url>
												<a href="${url_pagInicialR}"><c:out value="primeira... " /></a>
												<c:forEach var="i" begin="${minimumRadiusRevenue}" end="${maximumRadiusRevenue}">
													<c:url var="url_pagR" value="/mvc">
														<c:param name="logic"
															value="RequestFinancialTransactionOfCandidate" />
														<c:param name="totalExpenseCalculatedValue" value="${totalExpenseCalculatedValue}" />
														<c:param name="totalRevenueCalculatedValue" value="${totalRevenueCalculatedValue}" />
														<c:param name="campaignCandidateNumber"
															value="${campaign.campaignCandidateNumber}" />
														<c:param name="campaignYear" value="${campaign.campaignYear}" />
														<c:param name="campaignPosition" value="${campaign.campaignPosition.positionCode}" />
														<c:param name="campaignCountryState" value="${campaign.campaignCountryState}" />
														<c:param name="firstRevenue" value="${(i-1)*quantityRevenuePerPage}" />
														<c:param name="quantityRevenuePerPage" value="${quantityRevenuePerPage}" />
														<c:param name="totalRevenueCalculatedValue" value="${false}" />
														<c:param name="centerOfRevenue" value="${i}" />
														<c:param name="firstExpense" value="${firstExpense}" />
														<c:param name="quantityExpensePerPage" value="${quantityExpensePerPage}" />
														<c:param name="totalExpenseCalculatedValue" value="${totalExpenseCalculatedValue}" />
														<c:param name="centerOfExpense" value="${centerOfExpense}" />
													</c:url>
													<c:choose>
														<c:when test="${i == centerOfRevenue}">[ ${i} ]</c:when>
														<c:otherwise>
															<a href="${url_pagR}"><c:out value="${i}" /></a>
														</c:otherwise>
													</c:choose>
												</c:forEach>
												<c:url var="url_pagFinalR" value="/mvc">
													<c:param name="logic"
														value="RequestFinancialTransactionOfCandidate" />
													<c:param name="totalExpenseCalculatedValue" value="${totalExpenseCalculatedValue}" />
													<c:param name="totalRevenueCalculatedValue" value="${totalRevenueCalculatedValue}" />
													<c:param name="campaignCandidateNumber"
														value="${campaign.campaignCandidateNumber}" />
													<c:param name="campaignYear" value="${campaign.campaignYear}" />
													<c:param name="campaignPosition" value="${campaign.campaignPosition.positionCode}" />
													<c:param name="campaignCountryState" value="${campaign.campaignCountryState}" />
													<c:param name="firstRevenue"
														value="${(revenueIndex-1)*quantityRevenuePerPage}" />
													<c:param name="quantityRevenuePerPage" value="${quantityRevenuePerPage}" />
													<c:param name="totalRevenueCalculatedValue" value="${false}" />
													<c:param name="centerOfRevenue" value="${revenueIndex}" />
													<c:param name="firstExpense" value="${firstExpense}" />
													<c:param name="quantityExpensePerPage" value="${quantityExpensePerPage}" />
													<c:param name="totalExpenseCalculatedValue" value="${totalExpenseCalculatedValue}" />
													<c:param name="centerOfExpense" value="${centerOfExpense}" />
												</c:url>
												<a href="${url_pagFinalR}"><c:out value=" ...última" /></a>
												<br> Receitas por Página:
												<c:url var="url_tamanhoOriginalR" value="/mvc">
													<c:param name="logic"
														value="RequestFinancialTransactionOfCandidate" />
													<c:param name="totalExpenseCalculatedValue" value="${totalExpenseCalculatedValue}" />
													<c:param name="totalRevenueCalculatedValue" value="${totalRevenueCalculatedValue}" />
													<c:param name="campaignCandidateNumber"
														value="${campaign.campaignCandidateNumber}" />
													<c:param name="campaignYear" value="${campaign.campaignYear}" />
													<c:param name="campaignPosition" value="${campaign.campaignPosition.positionCode}" />
													<c:param name="campaignCountryState" value="${campaign.campaignCountryState}" />
													<c:param name="firstRevenue" value="${0}" />
													<c:param name="quantityRevenuePerPage" value="${10}" />
													<c:param name="totalRevenueCalculatedValue" value="${false}" />
													<c:param name="centerOfRevenue" value="${1}" />
													<c:param name="firstExpense" value="${firstExpense}" />
													<c:param name="quantityExpensePerPage" value="${quantityExpensePerPage}" />
													<c:param name="totalExpenseCalculatedValue" value="${totalExpenseCalculatedValue}" />
													<c:param name="centerOfExpense" value="${centerOfExpense}" />
												</c:url>
												<a href="${url_tamanhoOriginalR}"> ${10}</a>
												<c:forEach var="i" begin="1" end="${quantityOfPPR}">
													<c:url var="url_tamanhosR" value="/mvc">
														<c:param name="logic"
															value="RequestFinancialTransactionOfCandidate" />
														<c:param name="totalExpenseCalculatedValue" value="${totalExpenseCalculatedValue}" />
														<c:param name="totalRevenueCalculatedValue" value="${totalRevenueCalculatedValue}" />
														<c:param name="campaignCandidateNumber"
															value="${campaign.campaignCandidateNumber}" />
														<c:param name="campaignYear" value="${campaign.campaignYear}" />
														<c:param name="campaignPosition" value="${campaign.campaignPosition.positionCode}" />
														<c:param name="campaignCountryState" value="${campaign.campaignCountryState}" />
														<c:param name="firstRevenue" value="${0}" />
														<c:choose>
															<c:when test="${i == 5}">
																<c:param name="quantityRevenuePerPage" value="${250}" />
															</c:when>
															<c:when test="${i == 6 }">
																<c:param name="quantityRevenuePerPage" value="${500}" />
															</c:when>
															<c:when test="${i == 7 }">
																<c:param name="quantityRevenuePerPage" value="${1000}" />
															</c:when>
															<c:when test="${i == 8 }">
																<c:param name="quantityRevenuePerPage" value="${2000}" />
															</c:when>
															<c:otherwise>
																<c:param name="quantityRevenuePerPage" value="${i*25}" />
															</c:otherwise>
														</c:choose>
														<c:param name="verTodosR" value="${false}" />
														<c:param name="centroR" value="${1}" />
														<c:param name="firstExpense" value="${firstExpense}" />
														<c:param name="quantityExpensePerPage" value="${quantityExpensePerPage}" />
														<c:param name="totalExpenseCalculatedValue" value="${totalExpenseCalculatedValue}" />
														<c:param name="centerOfExpense" value="${centerOfExpense}" />
													</c:url>
													<a href="${url_tamanhosR}"> <c:choose>
															<c:when test="${i == 5}">
																${250}
															</c:when>
															<c:when test="${i == 6 }">
																${500}
															</c:when>
															<c:when test="${i == 7 }">
																${1000}
															</c:when>
															<c:when test="${i == 8 }">
																${2000}
															</c:when>
															<c:otherwise>
																${i*25}
															</c:otherwise>
														</c:choose></a>
												</c:forEach>
												<c:url var="url_todosR" value="/mvc">
													<c:param name="logica"
														value="RequisitarMovimentacoesDeCandidato" />
													<c:param name="totalExpenseCalculatedValue" value="${totalExpenseCalculatedValue}" />
													<c:param name="totalRevenueCalculatedValue" value="${totalRevenueCalculatedValue}" />
													<c:param name="campaignCandidateNumber"
														value="${campaign.campaignCandidateNumber}" />
													<c:param name="campaignYear" value="${campaign.campaignYear}" />
													<c:param name="campaignPosition" value="${campaign.campaignPosition.positionCode}" />
													<c:param name="campaignCountryState" value="${campaign.campaignCountryState}" />
													<c:param name="firstRevenue" value="${0}" />
													<c:param name="quantityRevenuePerPage" value="${0}" />
													<c:param name="totalRevenueCalculatedValue" value="${true}" />
													<c:param name="centerOfRevenue" value="${1}" />
													<c:param name="firstExpense" value="${firstExpense}" />
													<c:param name="quantityExpensePerPage" value="${quantityExpensePerPage}" />
													<c:param name="totalExpenseCalculatedValue" value="${totalExpenseCalculatedValue}" />
													<c:param name="centerOfExpense" value="${centerOfExpense}" />
												</c:url>
												<a href="${url_todosR}"> Ver Todos</a>
											</center></td>
									</tr>
									<tr>
										<td colspan="4">Dados de acordo com os arquivos
											disponíveis no site de <a
											href="http://dados.gov.br/dataset/prestacao-de-contas-das-campanhas-eleitorais"
											target="_blank"> Dados Abertos do Governo Federal</a>
										</td>
									</tr>
								</tfoot>
							</table>
						</c:otherwise>
					</c:choose>
				</center>

				<!-- Tabela de despesas -->
				<center>
					<h4>
						<br> <br>Despesas:
					</h4>

					<c:choose>
						<c:when test="${empty expenseList}">
							<p>Não há despesas declaradas.</p>
						</c:when>
						<c:otherwise>
							<table class="sortable" id="gradient-style-movimentacaod"
								summary="Meeting Results" cellpadding="10" align="center">
								<tr>
									<th width="100">Data</th>
									<th width="600">Tipo de Despesa</th>
									<th width="100">Valor (R$)</th>
								</tr>

								<!-- Elementos da tabela -->
								<c:forEach var="expense" items="${expenseList}"
									begin="${firstExpense}" end="${firstExpense+(quantityExpensePerPage-1)}"
									varStatus="listagemD">
									<tr>
										<td>${expense.financialTransactionDate}</td>
										<td><details> <summary>${expense.financialTransactionType}</summary>
											<table border="0">
												<tr>
													<td colspan="2">Nome do Fornecedor: <c:out
															value="${expense.expenseSupplier.supplierName}" />
													</td>
												</tr>
												<tr>
													<td colspan="1">CPJ/CNPJ do Fornecedor: <c:out
															value="${expense.expenseSupplier.supplierPersonRegister}" />
													</td>
													<td colspan="1">UF do Fornecedor: <c:out
															value="${expense.expenseSupplier.supplierCountryState}" />
													</td>
												</tr>
												<tr>
													<td colspan="1">Número do Documento: <c:out
															value="${expense.financialTransactionDocumentNumber}" />
													</td>
													<td colspan="1">Situação Cadastral do Fornecedor: <c:out
															value="${expense.expenseSupplier.supplierRegisterSituation}" />
													</td>
												</tr>
												<tr>
													<td colspan="2">Forma de Pagamento: <c:out
															value="${expense.financialTransactionPaymentType}" />
													</td>
												</tr>
												<tr>
													<td colspan="2" width="600" align="justify">Descrição:
														<c:out value="${expense.financialTransactionDescription}" />
													</td>
												</tr>
											</table>
											</details></td>
										<td>${expense.financialTransactionPrice}</td>
									</tr>
								</c:forEach>
								<tr>
									<td colspan="2" align="right">Despesa Total Calculada:</td>
									<td>${totalExpenseCalculatedValue}</td>
								</tr>
								<tfoot>
									<tr>
										<td colspan="4"><center>
												Páginas:
												<c:url var="url_pagInicialD" value="/mvc">
													<c:param name="logic"
														value="RequestFinancialTransactionOfCandidate" />
													<c:param name="totalExpenseCalculatedValue" value="${totalExpenseCalculatedValue}" />
													<c:param name="totalRevenueCalculatedValue" value="${totalRevenueCalculatedValue}" />
													<c:param name="campaignCandidateNumber"
														value="${campaign.campaignCandidateNumber}" />
													<c:param name="campaignYear" value="${campaign.campaignYear}" />
													<c:param name="campaignPosition" value="${campaign.campaignPosition.positionCode}" />
													<c:param name="campaignCountryState" value="${campaign.campaignCountryState}" />
													<c:param name="firstExpense" value="${0}" />
													<c:param name="quantityExpensePerPage" value="${quantityExpensePerPage}" />
													<c:param name="totalExpenseCalculatedValue" value="${false}" />
													<c:param name="centerOfExpense" value="${1}" />
													<c:param name="firstRevenue" value="${firstRevenue}" />
													<c:param name="quantityRevenuePerPage" value="${quantityRevenuePerPage}" />
													<c:param name="totalRevenueCalculatedValue" value="${totalRevenueCalculatedValue}" />
													<c:param name="centerOfRevenue" value="${centerOfRevenue}" />
												</c:url>
												<a href="${url_pagInicialD}"><c:out value="primeira... " /></a>
												<c:forEach var="i" begin="${minimumRadiusExpense}" end="${maximumRadiusExpense}">
													<c:url var="url_pagD" value="/mvc">
														<c:param name="logic"
															value="RequestFinancialTransactionOfCandidate" />
														<c:param name="totalExpenseCalculatedValue" value="${totalExpenseCalculatedValue}" />
														<c:param name="totalRevenueCalculatedValue" value="${totalRevenueCalculatedValue}" />
														<c:param name="campaignCandidateNumber"
															value="${campaign.campaignCandidateNumber}" />
														<c:param name="campaignYear" value="${campaign.campaignYear}" />
														<c:param name="campaignPosition" value="${campaign.campaignPosition.positionCode}" />
														<c:param name="campaignCountryState" value="${campaign.campaignCountryState}" />
														<c:param name="firstExpense" value="${(i-1)*quantityExpensePerPage}" />
														<c:param name="quantityExpensePerPage" value="${quantityExpensePerPage}" />
														<c:param name="totalExpenseCalculatedValue" value="${false}" />
														<c:param name="centerOfExpense" value="${i}" />
														<c:param name="firstRevenue" value="${firstRevenue}" />
														<c:param name="quantityRevenuePerPage" value="${quantityRevenuePerPage}" />
														<c:param name="totalRevenueCalculatedValue" value="${totalRevenueCalculatedValue}" />
														<c:param name="centerOfRevenue" value="${centerOfRevenue}" />
													</c:url>
													<c:choose>
														<c:when test="${i == centerOfExpense}">[ ${i} ]</c:when>
														<c:otherwise>
															<a href="${url_pagD}"><c:out value="${i}" /></a>
														</c:otherwise>
													</c:choose>
												</c:forEach>
												<c:url var="url_pagFinalD" value="/mvc">
													<c:param name="logic"
														value="RequestFinancialTransactionsOfCandidate" />
													<c:param name="totalExpenseCalculatedValue" value="${totalExpenseCalculatedValue}" />
													<c:param name="totalRevenueCalculatedValue" value="${totalRevenueCalculatedValue}" />
													<c:param name="campaignCandidateNumber"
														value="${campaign.campaignCandidateNumber}" />
													<c:param name="campaignYear" value="${campaign.campaignYear}" />
													<c:param name="campaignPosition" value="${campaign.campaignPosition.positionCode}" />
													<c:param name="campaignCountryState" value="${campaign.campaignCountryState}" />
													<c:param name="firstExpense"
														value="${(expenseIndex-1)*quantityExpensePerPage}" />
													<c:param name="quantityExpensePerPage" value="${quantityExpensePerPage}" />
													<c:param name="totalExpenseCalculatedValue" value="${false}" />
													<c:param name="centerOfExpense" value="${expenseIndex}" />
													<c:param name="firstRevenue" value="${firstRevenue}" />
													<c:param name="quantityRevenuePerPage" value="${quantityRevenuePerPage}" />
													<c:param name="totalRevenueCalculatedValue" value="${totalRevenueCalculatedValue}" />
													<c:param name="centerOfRevenue" value="${centerOfRevenue}" />
												</c:url>
												<a href="${url_pagFinalD}"><c:out value=" ...última" /></a>
												<br> Despesas por Página:
												<c:url var="url_tamanhoOriginalD" value="/mvc">
													<c:param name="logic"
														value="RequestFinancialTransactionOfCandidate" />
													<c:param name="totalExpenseCalculatedValue" value="${totalExpenseCalculatedValue}" />
													<c:param name="totalRevenueCalculatedValue" value="${totalRevenueCalculatedValue}" />
													<c:param name="campaignCandidateNumber"
														value="${campaign.campaignCandidateNumber}" />
													<c:param name="campaignYear" value="${campaign.campaignYear}" />
													<c:param name="campaignPosition" value="${campaign.campaignPosition.positionCode}" />
													<c:param name="campaignCountryState" value="${campaign.campaignCountryState}" />
													<c:param name="firstExpense" value="${0}" />
													<c:param name="quantityExpensePerPage" value="${10}" />
													<c:param name="totalExpenseCalculatedValue" value="${false}" />
													<c:param name="centerOfExpense" value="${centerOfExpense}" />
													<c:param name="firstRevenue" value="${firstRevenue}" />
													<c:param name="quantityRevenuePerPage" value="${quantityRevenuePerPage}" />
													<c:param name="totalRevenueCalculatedValue" value="${totalRevenueCalculatedValue}" />
													<c:param name="centerOfRevenue" value="${centerOfRevenue}" />
												</c:url>
												<a href="${url_tamanhoOriginalD}"> ${10}</a>
												<c:forEach var="i" begin="1" end="${quantityOfPPD}">
													<c:url var="url_tamanhosD" value="/mvc">
														<c:param name="logic"
															value="RequestFinancialTransactionOfCandidate" />
														<c:param name="totalExpenseCalculatedValue" value="${totalExpenseCalculatedValue}" />
														<c:param name="totalRevenueCalculatedValue" value="${totalRevenueCalculatedValue}" />
														<c:param name="campaignCandidateNumber"
															value="${campaign.campaignCandidateNumber}" />
														<c:param name="campaignYear" value="${campaign.campaignYear}" />
														<c:param name="campaignPosition" value="${campaign.campaignPosition.positionCode}" />
														<c:param name="campaignCountryState" value="${campaign.campaignCountryState}" />
														<c:param name="firstExpense" value="${0}" />
														<c:choose>
															<c:when test="${i == 5}">
																<c:param name="quantityExpensePerPage" value="${250}" />
															</c:when>
															<c:when test="${i == 6 }">
																<c:param name="quantityExpensePerPage" value="${500}" />
															</c:when>
															<c:when test="${i == 7 }">
																<c:param name="quantityExpensePerPage" value="${1000}" />
															</c:when>
															<c:when test="${i == 8 }">
																<c:param name="quantityExpensePerPage" value="${2000}" />
															</c:when>
															<c:otherwise>
																<c:param name="quantityExpensePerPage" value="${i*25}" />
															</c:otherwise>
														</c:choose>
														<c:param name="totalExpenseCalculatedValue" value="${false}" />
														<c:param name="centerOfExpense" value="${1}" />
														<c:param name="firstRevenue" value="${firstRevenue}" />
														<c:param name="quantityRevenuePerPage" value="${quantityRevenuePerPage}" />
														<c:param name="totalRevenueCalculatedValue" value="${totalRevenueCalculatedValue}" />
														<c:param name="centerOfRevenue" value="${centerOfRevenue}" />
													</c:url>
													<a href="${url_tamanhosD}"> <c:choose>
															<c:when test="${i == 5}">
																${250}
															</c:when>
															<c:when test="${i == 6 }">
																${500}
															</c:when>
															<c:when test="${i == 7 }">
																${1000}
															</c:when>
															<c:when test="${i == 8 }">
																${2000}
															</c:when>
															<c:otherwise>
																${i*25}
															</c:otherwise>
														</c:choose></a>
												</c:forEach>
												<c:url var="url_todosD" value="/mvc">
													<c:param name="logic"
														value="RequestFinancialTransactionOfCandidate" />
													<c:param name="totalExpenseCalculatedValue" value="${totalExpenseCalculatedValue}" />
													<c:param name="totalRevenueCalculatedValue" value="${totalRevenueCalculatedValue}" />
													<c:param name="campaignCandidateNumber"
														value="${campaign.campaignCandidateNumber}" />
													<c:param name="campaignYear" value="${campaign.campaignYear}" />
													<c:param name="campaignPosition" value="${campaign.campaignPosition.positionCode}" />
													<c:param name="campaignCountryState" value="${campaign.campaignCountryState}" />
													<c:param name="firstExpense" value="${0}" />
													<c:param name="quantityExpensePerPage" value="${0}" />
													<c:param name="totalRevenueCalculatedValue" value="${true}" />
													<c:param name="centerOfExpense" value="${1}" />
													<c:param name="firstRevenue" value="${firstRevenue}" />
													<c:param name="quantityRevenuePerPage" value="${quantityRevenuePerPage}" />
													<c:param name="totalRevenueCalculatedValue" value="${totalRevenueCalculatedValue}" />
													<c:param name="centerOfRevenue" value="${centerOfRevenue}" />
												</c:url>
												<a href="${url_todosD}"> Ver Todos</a>
											</center></td>
									</tr>
									<tr>
										<td colspan="4">Dados de acordo com os arquivos
											disponíveis no site de <a
											href="http://dados.gov.br/dataset/prestacao-de-contas-das-campanhas-eleitorais"
											target="_blank"> Dados Abertos do Governo Federal</a>
										</td>
									</tr>
								</tfoot>
							</table>
						</c:otherwise>
					</c:choose>
				</center>
			</div>
		</div>
	</div>
	<!-- FIM CONTEUDO-->

	<%@include file="imports/rodape.jsp"%>
</body>
</html>