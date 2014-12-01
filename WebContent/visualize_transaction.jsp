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
							<td><c:out
									value="${campaign.campaignCandidate.candidateName}" /></td>
						</tr>
						<tr>
							<td><b>Número do Candidato:</b></td>
							<td><c:out value="${campaign.campaignCandidateNumber}" /></td>
						</tr>
						<tr>
							<td><b>Cargo Pleiteado:</b></td>
							<td><c:out
									value="${campaign.campaignPosition.positionDescription}" /></td>
						</tr>
						<tr>
							<td><b>Ano:</b></td>
							<td><c:out value="${campaign.campaignYear}" /></td>
						</tr>
						<tr>
							<td><b>Resultado:</b></td>
							<td><c:out
									value="${campaign.campaignResult.resultDescription}" /></td>
						</tr>
						<tr>
							<td><b>Despesa Máxima Declarada:</b></td>
							<td><c:out value="${totalExpense}" /></td>
						</tr>
						<tr>
							<td><b>Saldo da Campanha:</b></td>
							<td><c:out
									value="${totalRevenueCalculatedValue - totalExpenseCalculatedValue}" /></td>
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
								<!-- PAGINATION REVENUE LOGIC -->
								<c:forEach var="revenue" items="${revenueList}"
									begin="${revenue_pagination.firstPageOfTheList}"
									end="${revenue_pagination.firstPageOfTheList+(revenue_pagination.quantityOfTermsPerPage-1)}"
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
												<!-- "FIRST" PAGE REVENUE PARAMETERS -->
												<c:url var="url_pagInicialR" value="/mvc">
													<c:param name="logic"
														value="RequestFinancialTransactionOfCandidate" />
													<c:param name="totalExpenseCalculatedValue"
														value="${totalExpenseCalculatedValue}" />
													<c:param name="totalRevenueCalculatedValue"
														value="${totalRevenueCalculatedValue}" />
													<c:param name="campaignCandidateNumber"
														value="${campaign.campaignCandidateNumber}" />
													<c:param name="campaignYear"
														value="${campaign.campaignYear}" />
													<c:param name="campaignPosition"
														value="${campaign.campaignPosition.positionCode}" />
													<c:param name="campaignCountryState"
														value="${campaign.campaignCountryState}" />
													<c:param name="revenue_pagination__first_page" value="${0}" />
													<c:param name="revenue_pagination__qtd_of_terms"
														value="${revenue_pagination.quantityOfTermsPerPage}" />
													<c:param name="revenue_pagination__see_all"
														value="${false}" />
													<c:param name="revenue_pagination__center_page"
														value="${1}" />
													<c:param name="expense_pagination__first_page"
														value="${expense_pagination.firstPageOfTheList}" />
													<c:param name="expense_pagination__qtd_of_terms"
														value="${expense_pagination.quantityOfTermsPerPage}" />
													<c:param name="expense_pagination_see_all" value="${false}" />
													<c:param name="expense_pagination__center_page"
														value="${expense_pagination.centerOfPagesListed}" />
												</c:url>
												<a href="${url_pagInicialR}"><c:out value="primeira... " /></a>
												<!-- END "FIRST" PAGE REVENUE PARAMETERS -->
												<!-- PAGES REVENUE PARAMETERS -->
												<c:forEach var="i"
													begin="${revenue_pagination.minimumRadiusOfPagesListed}"
													end="${revenue_pagination.maximumRadiusOfPagesListed}">
													<c:url var="url_pagR" value="/mvc">
														<c:param name="logic"
															value="RequestFinancialTransactionOfCandidate" />
														<c:param name="totalExpenseCalculatedValue"
															value="${totalExpenseCalculatedValue}" />
														<c:param name="totalRevenueCalculatedValue"
															value="${totalRevenueCalculatedValue}" />
														<c:param name="campaignCandidateNumber"
															value="${campaign.campaignCandidateNumber}" />
														<c:param name="campaignYear"
															value="${campaign.campaignYear}" />
														<c:param name="campaignPosition"
															value="${campaign.campaignPosition.positionCode}" />
														<c:param name="campaignCountryState"
															value="${campaign.campaignCountryState}" />
														<c:param name="revenue_pagination__first_page"
															value="${(i-1)*revenue_pagination.quantityOfTermsPerPage}" />
														<c:param name="revenue_pagination__qtd_of_terms"
															value="${revenue_pagination.quantityOfTermsPerPage}" />
														<c:param name="revenue_pagination__see_all"
															value="${false}" />
														<c:param name="revenue_pagination__center_page"
															value="${i}" />
														<c:param name="expense_pagination__first_page"
															value="${expense_pagination.firstPageOfTheList}" />
														<c:param name="expense_pagination__qtd_of_terms"
															value="${expense_pagination.quantityOfTermsPerPage}" />
														<c:param name="expense_pagination_see_all"
															value="${false}" />
														<c:param name="expense_pagination__center_page"
															value="${expense_pagination.centerOfPagesListed}" />
													</c:url>
													<c:choose>
														<c:when
															test="${i == revenue_pagination.centerOfPagesListed}">[ ${i} ]</c:when>
														<c:otherwise>
															<a href="${url_pagR}"><c:out value="${i}" /></a>
														</c:otherwise>
													</c:choose>
												</c:forEach>
												<!-- END PAGES REVENUE PARAMETERS -->
												<!-- "LAST" PAGE REVENUE PARAMETERS -->
												<c:url var="url_pagFinalR" value="/mvc">
													<c:param name="logic"
														value="RequestFinancialTransactionOfCandidate" />
													<c:param name="totalExpenseCalculatedValue"
														value="${totalExpenseCalculatedValue}" />
													<c:param name="totalRevenueCalculatedValue"
														value="${totalRevenueCalculatedValue}" />
													<c:param name="campaignCandidateNumber"
														value="${campaign.campaignCandidateNumber}" />
													<c:param name="campaignYear"
														value="${campaign.campaignYear}" />
													<c:param name="campaignPosition"
														value="${campaign.campaignPosition.positionCode}" />
													<c:param name="campaignCountryState"
														value="${campaign.campaignCountryState}" />
													<c:param name="revenue_pagination__first_page"
														value="${(revenue_pagination.indexOfPages-1)*revenue_pagination.quantityOfTermsPerPage}" />
													<c:param name="revenue_pagination__qtd_of_terms"
														value="${revenue_pagination.quantityOfTermsPerPage}" />
													<c:param name="revenue_pagination__see_all"
														value="${false}" />
													<c:param name="revenue_pagination__center_page"
														value="${revenue_pagination.indexOfPages}" />
													<c:param name="expense_pagination__first_page"
														value="${expense_pagination.firstPageOfTheList}" />
													<c:param name="expense_pagination__qtd_of_terms"
														value="${expense_pagination.quantityOfTermsPerPage}" />
													<c:param name="expense_pagination_see_all" value="${false}" />
													<c:param name="expense_pagination__center_page"
														value="${expense_pagination.centerOfPagesListed}" />
												</c:url>
												<a href="${url_pagFinalR}"><c:out value="...última" /></a>
												<!-- END "LAST" PAGE REVENUE PARAMETERS -->
												<br> Partidos por Página:
												<!-- "ORIGINAL" PAGINATION SIZE REVENUE PARAMETERS -->
												<c:url var="url_tamanhoOriginalR" value="/mvc">
													<c:param name="logic"
														value="RequestFinancialTransactionOfCandidate" />
													<c:param name="totalExpenseCalculatedValue"
														value="${totalExpenseCalculatedValue}" />
													<c:param name="totalRevenueCalculatedValue"
														value="${totalRevenueCalculatedValue}" />
													<c:param name="campaignCandidateNumber"
														value="${campaign.campaignCandidateNumber}" />
													<c:param name="campaignYear"
														value="${campaign.campaignYear}" />
													<c:param name="campaignPosition"
														value="${campaign.campaignPosition.positionCode}" />
													<c:param name="campaignCountryState"
														value="${campaign.campaignCountryState}" />
													<c:param name="revenue_pagination__first_page" value="${0}" />
													<c:param name="revenue_pagination__qtd_of_terms"
														value="${10}" />
													<c:param name="revenue_pagination__see_all"
														value="${false}" />
													<c:param name="revenue_pagination__center_page"
														value="${1}" />
													<c:param name="expense_pagination__first_page"
														value="${expense_pagination.firstPageOfTheList}" />
													<c:param name="expense_pagination__qtd_of_terms"
														value="${expense_pagination.quantityOfTermsPerPage}" />
													<c:param name="expense_pagination_see_all" value="${false}" />
													<c:param name="expense_pagination__center_page"
														value="${expense_pagination.centerOfPagesListed}" />
												</c:url>
												<a href="${url_tamanhoOriginalR}"> ${10}</a>
												<!-- END "ORIGINAL" PAGINATION SIZE REVENUE PARAMETERS -->
												<!-- PAGINATION SIZES REVENUE PARAMETERS -->
												<c:forEach var="i" begin="1"
													end="${revenue_pagination.quantityOfPagesListedInThePage}">
													<c:url var="url_tamanhosR" value="/mvc">
														<c:param name="logic"
															value="RequestFinancialTransactionOfCandidate" />
														<c:param name="totalExpenseCalculatedValue"
															value="${totalExpenseCalculatedValue}" />
														<c:param name="totalRevenueCalculatedValue"
															value="${totalRevenueCalculatedValue}" />
														<c:param name="campaignCandidateNumber"
															value="${campaign.campaignCandidateNumber}" />
														<c:param name="campaignYear"
															value="${campaign.campaignYear}" />
														<c:param name="campaignPosition"
															value="${campaign.campaignPosition.positionCode}" />
														<c:param name="campaignCountryState"
															value="${campaign.campaignCountryState}" />
														<c:param name="revenue_pagination__first_page"
															value="${0}" />
														<c:choose>
															<c:when test="${i == 5}">
																<c:param name="revenue_pagination__qtd_of_terms"
																	value="${250}" />
															</c:when>
															<c:when test="${i == 6 }">
																<c:param name="revenue_pagination__qtd_of_terms"
																	value="${500}" />
															</c:when>
															<c:when test="${i == 7 }">
																<c:param name="revenue_pagination__qtd_of_terms"
																	value="${1000}" />
															</c:when>
															<c:when test="${i == 8 }">
																<c:param name="revenue_pagination__qtd_of_terms"
																	value="${2000}" />
															</c:when>
															<c:otherwise>
																<c:param name="revenue_pagination__qtd_of_terms"
																	value="${i*25}" />
															</c:otherwise>
														</c:choose>
														<c:param name="revenue_pagination__see_all"
															value="${false}" />
														<c:param name="revenue_pagination__center_page"
															value="${1}" />
														<c:param name="expense_pagination__first_page"
															value="${expense_pagination.firstPageOfTheList}" />
														<c:param name="expense_pagination__qtd_of_terms"
															value="${expense_pagination.quantityOfTermsPerPage}" />
														<c:param name="expense_pagination_see_all"
															value="${false}" />
														<c:param name="expense_pagination__center_page"
															value="${expense_pagination.centerOfPagesListed}" />
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
												<!-- END PAGINATION SIZES REVENUE PARAMETERS -->
												<!-- "SEE ALL" PAGINATION SIZE REVENUE PARAMETERS -->
												<c:url var="url_todosR" value="/mvc">
													<c:param name="logic"
														value="RequestFinancialTransactionOfCandidate" />
													<c:param name="totalExpenseCalculatedValue"
														value="${totalExpenseCalculatedValue}" />
													<c:param name="totalRevenueCalculatedValue"
														value="${totalRevenueCalculatedValue}" />
													<c:param name="campaignCandidateNumber"
														value="${campaign.campaignCandidateNumber}" />
													<c:param name="campaignYear"
														value="${campaign.campaignYear}" />
													<c:param name="campaignPosition"
														value="${campaign.campaignPosition.positionCode}" />
													<c:param name="campaignCountryState"
														value="${campaign.campaignCountryState}" />
													<c:param name="revenue_pagination__first_page" value="${0}" />
													<c:param name="revenue_pagination__qtd_of_terms"
														value="${0}" />
													<c:param name="revenue_pagination__see_all" value="${true}" />
													<c:param name="revenue_pagination__center_page"
														value="${1}" />
													<c:param name="expense_pagination__first_page"
														value="${expense_pagination.firstPageOfTheList}" />
													<c:param name="expense_pagination__qtd_of_terms"
														value="${expense_pagination.quantityOfTermsPerPage}" />
													<c:param name="expense_pagination_see_all" value="${false}" />
													<c:param name="expense_pagination__center_page"
														value="${expense_pagination.centerOfPagesListed}" />
												</c:url>
												<a href="${url_todosR}"> Ver Todos</a>
												<!-- END "SEE ALL" PAGINATION SIZE REVENUE PARAMETERS -->
											</center></td>
									</tr>
									<!-- END PAGINATION REVENUE LOGIC -->
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

								<!-- PAGINATION EXPENSE LOGIC -->
								<c:forEach var="expense" items="${expenseList}"
									begin="${expense_pagination.firstPageOfTheList}"
									end="${expense_pagination.firstPageOfTheList+(expense_pagination.quantityOfTermsPerPage-1)}"
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
												<!-- "FIRST" PAGE EXPENSE PARAMETERS -->
												<c:url var="url_pagInicialE" value="/mvc">
													<c:param name="logic"
														value="RequestFinancialTransactionOfCandidate" />
													<c:param name="totalExpenseCalculatedValue"
														value="${totalExpenseCalculatedValue}" />
													<c:param name="totalRevenueCalculatedValue"
														value="${totalRevenueCalculatedValue}" />
													<c:param name="campaignCandidateNumber"
														value="${campaign.campaignCandidateNumber}" />
													<c:param name="campaignYear"
														value="${campaign.campaignYear}" />
													<c:param name="campaignPosition"
														value="${campaign.campaignPosition.positionCode}" />
													<c:param name="campaignCountryState"
														value="${campaign.campaignCountryState}" />
													<c:param name="expense_pagination__first_page" value="${0}" />
													<c:param name="expense_pagination__qtd_of_terms"
														value="${expense_pagination.quantityOfTermsPerPage}" />
													<c:param name="expense_pagination__see_all"
														value="${false}" />
													<c:param name="expense_pagination__center_page"
														value="${1}" />
													<c:param name="revenue_pagination__first_page"
														value="${revenue_pagination.firstPageOfTheList}" />
													<c:param name="revenue_pagination__qtd_of_terms"
														value="${revenue_pagination.quantityOfTermsPerPage}" />
													<c:param name="revenue_pagination_see_all" value="${false}" />
													<c:param name="revenue_pagination__center_page"
														value="${revenue_pagination.centerOfPagesListed}" />
												</c:url>
												<a href="${url_pagInicialE}"><c:out value="primeira... " /></a>
												<!-- END "FIRST" PAGE EXPENSE PARAMETERS -->
												<!-- PAGES EXPENSE PARAMETERS -->
												<c:forEach var="i"
													begin="${expense_pagination.minimumRadiusOfPagesListed}"
													end="${expense_pagination.maximumRadiusOfPagesListed}">
													<c:url var="url_pagE" value="/mvc">
														<c:param name="logic"
															value="RequestFinancialTransactionOfCandidate" />
														<c:param name="totalExpenseCalculatedValue"
															value="${totalExpenseCalculatedValue}" />
														<c:param name="totalRevenueCalculatedValue"
															value="${totalRevenueCalculatedValue}" />
														<c:param name="campaignCandidateNumber"
															value="${campaign.campaignCandidateNumber}" />
														<c:param name="campaignYear"
															value="${campaign.campaignYear}" />
														<c:param name="campaignPosition"
															value="${campaign.campaignPosition.positionCode}" />
														<c:param name="campaignCountryState"
															value="${campaign.campaignCountryState}" />
														<c:param name="expense_pagination__first_page"
															value="${(i-1)*expense_pagination.quantityOfTermsPerPage}" />
														<c:param name="expense_pagination__qtd_of_terms"
															value="${expense_pagination.quantityOfTermsPerPage}" />
														<c:param name="expense_pagination__see_all"
															value="${false}" />
														<c:param name="expense_pagination__center_page"
															value="${i}" />
														<c:param name="revenue_pagination__first_page"
															value="${revenue_pagination.firstPageOfTheList}" />
														<c:param name="revenue_pagination__qtd_of_terms"
															value="${revenue_pagination.quantityOfTermsPerPage}" />
														<c:param name="revenue_pagination_see_all"
															value="${false}" />
														<c:param name="revenue_pagination__center_page"
															value="${revenue_pagination.centerOfPagesListed}" />
													</c:url>
													<c:choose>
														<c:when
															test="${i == expense_pagination.centerOfPagesListed}">[ ${i} ]</c:when>
														<c:otherwise>
															<a href="${url_pagE}"><c:out value="${i}" /></a>
														</c:otherwise>
													</c:choose>
												</c:forEach>
												<!-- END PAGES EXPENSE PARAMETERS -->
												<!-- "LAST" PAGE EXPENSE PARAMETERS -->
												<c:url var="url_pagFinalE" value="/mvc">
													<c:param name="logic"
														value="RequestFinancialTransactionOfCandidate" />
													<c:param name="totalExpenseCalculatedValue"
														value="${totalExpenseCalculatedValue}" />
													<c:param name="totalRevenueCalculatedValue"
														value="${totalRevenueCalculatedValue}" />
													<c:param name="campaignCandidateNumber"
														value="${campaign.campaignCandidateNumber}" />
													<c:param name="campaignYear"
														value="${campaign.campaignYear}" />
													<c:param name="campaignPosition"
														value="${campaign.campaignPosition.positionCode}" />
													<c:param name="campaignCountryState"
														value="${campaign.campaignCountryState}" />
													<c:param name="expense_pagination__first_page"
														value="${(expense_pagination.indexOfPages-1)*expense_pagination.quantityOfTermsPerPage}" />
													<c:param name="expense_pagination__qtd_of_terms"
														value="${expense_pagination.quantityOfTermsPerPage}" />
													<c:param name="expense_pagination__see_all"
														value="${false}" />
													<c:param name="expense_pagination__center_page"
														value="${revenue_pagination.indexOfPages}" />
													<c:param name="revenue_pagination__first_page"
														value="${revenue_pagination.firstPageOfTheList}" />
													<c:param name="revenue_pagination__qtd_of_terms"
														value="${revenue_pagination.quantityOfTermsPerPage}" />
													<c:param name="revenue_pagination_see_all" value="${false}" />
													<c:param name="revenue_pagination__center_page"
														value="${revenue_pagination.centerOfPagesListed}" />
												</c:url>
												<a href="${url_pagFinalE}"><c:out value="...última" /></a>
												<!-- END "LAST" PAGE EXPENSE PARAMETERS -->
												<br> Partidos por Página:
												<!-- "ORIGINAL" PAGINATION SIZE EXPENSE PARAMETERS -->
												<c:url var="url_tamanhoOriginalE" value="/mvc">
													<c:param name="logic"
														value="RequestFinancialTransactionOfCandidate" />
													<c:param name="totalExpenseCalculatedValue"
														value="${totalExpenseCalculatedValue}" />
													<c:param name="totalRevenueCalculatedValue"
														value="${totalRevenueCalculatedValue}" />
													<c:param name="campaignCandidateNumber"
														value="${campaign.campaignCandidateNumber}" />
													<c:param name="campaignYear"
														value="${campaign.campaignYear}" />
													<c:param name="campaignPosition"
														value="${campaign.campaignPosition.positionCode}" />
													<c:param name="campaignCountryState"
														value="${campaign.campaignCountryState}" />
													<c:param name="expense_pagination__first_page" value="${0}" />
													<c:param name="expense_pagination__qtd_of_terms"
														value="${10}" />
													<c:param name="expense_pagination__see_all"
														value="${false}" />
													<c:param name="expense_pagination__center_page"
														value="${1}" />
													<c:param name="revenue_pagination__first_page"
														value="${revenue_pagination.firstPageOfTheList}" />
													<c:param name="revenue_pagination__qtd_of_terms"
														value="${revenue_pagination.quantityOfTermsPerPage}" />
													<c:param name="revenue_pagination_see_all" value="${false}" />
													<c:param name="revenue_pagination__center_page"
														value="${revenue_pagination.centerOfPagesListed}" />
												</c:url>
												<a href="${url_tamanhoOriginalE}"> ${10}</a>
												<!-- END "ORIGINAL" PAGINATION SIZE EXPENSE PARAMETERS -->
												<!-- PAGINATION SIZES EXPENSE PARAMETERS -->
												<c:forEach var="i" begin="1"
													end="${expense_pagination.quantityOfPagesListedInThePage}">
													<c:url var="url_tamanhosE" value="/mvc">
														<c:param name="logic"
															value="RequestFinancialTransactionOfCandidate" />
														<c:param name="totalExpenseCalculatedValue"
															value="${totalExpenseCalculatedValue}" />
														<c:param name="totalRevenueCalculatedValue"
															value="${totalRevenueCalculatedValue}" />
														<c:param name="campaignCandidateNumber"
															value="${campaign.campaignCandidateNumber}" />
														<c:param name="campaignYear"
															value="${campaign.campaignYear}" />
														<c:param name="campaignPosition"
															value="${campaign.campaignPosition.positionCode}" />
														<c:param name="campaignCountryState"
															value="${campaign.campaignCountryState}" />
														<c:param name="expense_pagination__first_page"
															value="${0}" />
														<c:choose>
															<c:when test="${i == 5}">
																<c:param name="expense_pagination__qtd_of_terms"
																	value="${250}" />
															</c:when>
															<c:when test="${i == 6 }">
																<c:param name="expense_pagination__qtd_of_terms"
																	value="${500}" />
															</c:when>
															<c:when test="${i == 7 }">
																<c:param name="expense_pagination__qtd_of_terms"
																	value="${1000}" />
															</c:when>
															<c:when test="${i == 8 }">
																<c:param name="expense_pagination__qtd_of_terms"
																	value="${2000}" />
															</c:when>
															<c:otherwise>
																<c:param name="expense_pagination__qtd_of_terms"
																	value="${i*25}" />
															</c:otherwise>
														</c:choose>
														<c:param name="expense_pagination__see_all"
															value="${false}" />
														<c:param name="expense_pagination__center_page"
															value="${1}" />
														<c:param name="revenue_pagination__first_page"
															value="${revenue_pagination.firstPageOfTheList}" />
														<c:param name="revenue_pagination__qtd_of_terms"
															value="${revenue_pagination.quantityOfTermsPerPage}" />
														<c:param name="revenue_pagination_see_all"
															value="${false}" />
														<c:param name="revenue_pagination__center_page"
															value="${revenue_pagination.centerOfPagesListed}" />
													</c:url>
													<a href="${url_tamanhosE}"> <c:choose>
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
												<!-- END PAGINATION SIZES EXPENSE PARAMETERS -->
												<!-- "SEE ALL" PAGINATION SIZE EXPENSE PARAMETERS -->
												<c:url var="url_todosE" value="/mvc">
													<c:param name="logic"
														value="RequestFinancialTransactionOfCandidate" />
													<c:param name="totalExpenseCalculatedValue"
														value="${totalExpenseCalculatedValue}" />
													<c:param name="totalRevenueCalculatedValue"
														value="${totalRevenueCalculatedValue}" />
													<c:param name="campaignCandidateNumber"
														value="${campaign.campaignCandidateNumber}" />
													<c:param name="campaignYear"
														value="${campaign.campaignYear}" />
													<c:param name="campaignPosition"
														value="${campaign.campaignPosition.positionCode}" />
													<c:param name="campaignCountryState"
														value="${campaign.campaignCountryState}" />
													<c:param name="expense_pagination__first_page" value="${0}" />
													<c:param name="expense_pagination__qtd_of_terms"
														value="${0}" />
													<c:param name="expense_pagination__see_all" value="${true}" />
													<c:param name="expense_pagination__center_page"
														value="${1}" />
													<c:param name="revenue_pagination__first_page"
														value="${revenue_pagination.firstPageOfTheList}" />
													<c:param name="revenue_pagination__qtd_of_terms"
														value="${revenue_pagination.quantityOfTermsPerPage}" />
													<c:param name="revenue_pagination_see_all" value="${false}" />
													<c:param name="revenue_pagination__center_page"
														value="${revenue_pagination.centerOfPagesListed}" />
												</c:url>
												<a href="${url_todosE}"> Ver Todos</a>
												<!-- END "SEE ALL" PAGINATION SIZE EXPENSE PARAMETERS -->
											</center></td>
									</tr>
									<!-- END PAGINATION EXPENSE LOGIC -->
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