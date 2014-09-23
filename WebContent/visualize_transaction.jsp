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

	<%@include file="imports/cabecalhocandidatos.jsp"%>

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
													<c:param name="logica"
														value="RequisitarMovimentacoesDeCandidato" />
													<c:param name="despesaTC" value="${totalExpenseCalculatedValue}" />
													<c:param name="receitaTC" value="${totalRevenueCalculatedValue}" />
													<c:param name="numero_cand"
														value="${campaign.campaignCandidateNumber}" />
													<c:param name="ano" value="${campaign.ano}" />
													<c:param name="cargo_cod" value="${campaign.cargo.codigo}" />
													<c:param name="uf" value="${campaign.uf}" />
													<c:param name="inicioR" value="${0}" />
													<c:param name="qtdPorPaginaR" value="${qtdPorPaginaR}" />
													<c:param name="verTodosR" value="${false}" />
													<c:param name="centroR" value="${1}" />
													<c:param name="inicioD" value="${inicioD}" />
													<c:param name="qtdPorPaginaD" value="${qtdPorPaginaD}" />
													<c:param name="verTodosD" value="${verTodosD}" />
													<c:param name="centroD" value="${centroD}" />
												</c:url>
												<a href="${url_pagInicialR}"><c:out value="primeira... " /></a>
												<c:forEach var="i" begin="${minRaioR}" end="${maxRaioR}">
													<c:url var="url_pagR" value="/mvc">
														<c:param name="logica"
															value="RequisitarMovimentacoesDeCandidato" />
														<c:param name="despesaTC" value="${totalExpenseCalculatedValue}" />
														<c:param name="receitaTC" value="${totalRevenueCalculatedValue}" />
														<c:param name="numero_cand"
															value="${campaign.numeroCandidato}" />
														<c:param name="ano" value="${campaign.ano}" />
														<c:param name="cargo_cod" value="${campaign.cargo.codigo}" />
														<c:param name="uf" value="${campaign.uf}" />
														<c:param name="inicioR" value="${(i-1)*qtdPorPaginaR}" />
														<c:param name="qtdPorPaginaR" value="${qtdPorPaginaR}" />
														<c:param name="verTodosR" value="${false}" />
														<c:param name="centroR" value="${i}" />
														<c:param name="inicioD" value="${inicioD}" />
														<c:param name="qtdPorPaginaD" value="${qtdPorPaginaD}" />
														<c:param name="verTodosD" value="${verTodosD}" />
														<c:param name="centroD" value="${centroD}" />
													</c:url>
													<c:choose>
														<c:when test="${i == centroR}">[ ${i} ]</c:when>
														<c:otherwise>
															<a href="${url_pagR}"><c:out value="${i}" /></a>
														</c:otherwise>
													</c:choose>
												</c:forEach>
												<c:url var="url_pagFinalR" value="/mvc">
													<c:param name="logica"
														value="RequisitarMovimentacoesDeCandidato" />
													<c:param name="despesaTC" value="${totalExpenseCalculatedValue}" />
													<c:param name="receitaTC" value="${totalRevenueCalculatedValue}" />
													<c:param name="numero_cand"
														value="${campaign.numeroCandidato}" />
													<c:param name="ano" value="${campaign.ano}" />
													<c:param name="cargo_cod" value="${campaign.cargo.codigo}" />
													<c:param name="uf" value="${campaign.uf}" />
													<c:param name="inicioR"
														value="${(indiceR-1)*qtdPorPaginaR}" />
													<c:param name="qtdPorPaginaR" value="${qtdPorPaginaR}" />
													<c:param name="verTodosR" value="${false}" />
													<c:param name="centroR" value="${indiceR}" />
													<c:param name="inicioD" value="${inicioD}" />
													<c:param name="qtdPorPaginaD" value="${qtdPorPaginaD}" />
													<c:param name="verTodosD" value="${verTodosD}" />
													<c:param name="centroD" value="${centroD}" />
												</c:url>
												<a href="${url_pagFinalR}"><c:out value=" ...última" /></a>
												<br> Receitas por Página:
												<c:url var="url_tamanhoOriginalR" value="/mvc">
													<c:param name="logica"
														value="RequisitarMovimentacoesDeCandidato" />
													<c:param name="despesaTC" value="${totalExpenseCalculatedValue}" />
													<c:param name="receitaTC" value="${totalRevenueCalculatedValue}" />
													<c:param name="numero_cand"
														value="${campaign.numeroCandidato}" />
													<c:param name="ano" value="${campaign.ano}" />
													<c:param name="cargo_cod" value="${campaign.cargo.codigo}" />
													<c:param name="uf" value="${campaign.uf}" />
													<c:param name="inicioR" value="${0}" />
													<c:param name="qtdPorPaginaR" value="${10}" />
													<c:param name="verTodosR" value="${false}" />
													<c:param name="centroR" value="${1}" />
													<c:param name="inicioD" value="${inicioD}" />
													<c:param name="qtdPorPaginaD" value="${qtdPorPaginaD}" />
													<c:param name="verTodosD" value="${verTodosD}" />
													<c:param name="centroD" value="${centroD}" />
												</c:url>
												<a href="${url_tamanhoOriginalR}"> ${10}</a>
												<c:forEach var="i" begin="1" end="${qtdDePPR}">
													<c:url var="url_tamanhosR" value="/mvc">
														<c:param name="logica"
															value="RequisitarMovimentacoesDeCandidato" />
														<c:param name="despesaTC" value="${totalExpenseCalculatedValue}" />
														<c:param name="receitaTC" value="${totalRevenueCalculatedValue}" />
														<c:param name="numero_cand"
															value="${campaign.numeroCandidato}" />
														<c:param name="ano" value="${campaign.ano}" />
														<c:param name="cargo_cod" value="${campaign.cargo.codigo}" />
														<c:param name="uf" value="${campaign.uf}" />
														<c:param name="inicioR" value="${0}" />
														<c:choose>
															<c:when test="${i == 5}">
																<c:param name="qtdPorPaginaR" value="${250}" />
															</c:when>
															<c:when test="${i == 6 }">
																<c:param name="qtdPorPaginaR" value="${500}" />
															</c:when>
															<c:when test="${i == 7 }">
																<c:param name="qtdPorPaginaR" value="${1000}" />
															</c:when>
															<c:when test="${i == 8 }">
																<c:param name="qtdPorPaginaR" value="${2000}" />
															</c:when>
															<c:otherwise>
																<c:param name="qtdPorPaginaR" value="${i*25}" />
															</c:otherwise>
														</c:choose>
														<c:param name="verTodosR" value="${false}" />
														<c:param name="centroR" value="${1}" />
														<c:param name="inicioD" value="${inicioD}" />
														<c:param name="qtdPorPaginaD" value="${qtdPorPaginaD}" />
														<c:param name="verTodosD" value="${verTodosD}" />
														<c:param name="centroD" value="${centroD}" />
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
													<c:param name="despesaTC" value="${totalExpenseCalculatedValue}" />
													<c:param name="receitaTC" value="${totalRevenueCalculatedValue}" />
													<c:param name="numero_cand"
														value="${campaign.numeroCandidato}" />
													<c:param name="ano" value="${campaign.ano}" />
													<c:param name="cargo_cod" value="${campaign.cargo.codigo}" />
													<c:param name="uf" value="${campaign.uf}" />
													<c:param name="inicioR" value="${0}" />
													<c:param name="qtdPorPaginaR" value="${0}" />
													<c:param name="verTodosR" value="${true}" />
													<c:param name="centroR" value="${1}" />
													<c:param name="inicioD" value="${inicioD}" />
													<c:param name="qtdPorPaginaD" value="${qtdPorPaginaD}" />
													<c:param name="verTodosD" value="${verTodosD}" />
													<c:param name="centroD" value="${centroD}" />
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
						<c:when test="${empty listaDespesas}">
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
								<c:forEach var="despesa" items="${listaDespesas}"
									begin="${inicioD}" end="${inicioD+(qtdPorPaginaD-1)}"
									varStatus="listagemD">
									<tr>
										<td>${despesa.data}</td>
										<td><details> <summary>${despesa.tipoMovimentacao}</summary>
											<table border="0">
												<tr>
													<td colspan="2">Nome do Fornecedor: <c:out
															value="${despesa.fornecedor.nome}" />
													</td>
												</tr>
												<tr>
													<td colspan="1">CPJ/CNPJ do Fornecedor: <c:out
															value="${despesa.fornecedor.cpf_cnpj}" />
													</td>
													<td colspan="1">UF do Fornecedor: <c:out
															value="${despesa.fornecedor.uf}" />
													</td>
												</tr>
												<tr>
													<td colspan="1">Número do Documento: <c:out
															value="${despesa.numeroDocumento}" />
													</td>
													<td colspan="1">Situação Cadastral do Fornecedor: <c:out
															value="${despesa.fornecedor.situacaoCadastral}" />
													</td>
												</tr>
												<tr>
													<td colspan="2">Forma de Pagamento: <c:out
															value="${despesa.formaPagamento}" />
													</td>
												</tr>
												<tr>
													<td colspan="2" width="600" align="justify">Descrição:
														<c:out value="${despesa.descricao}" />
													</td>
												</tr>
											</table>
											</details></td>
										<td>${despesa.valor}</td>
									</tr>
								</c:forEach>
								<tr>
									<td colspan="2" align="right">Despesa Total Calculada:</td>
									<td>${despesaTC}</td>
								</tr>
								<tfoot>
									<tr>
										<td colspan="4"><center>
												Páginas:
												<c:url var="url_pagInicialD" value="/mvc">
													<c:param name="logica"
														value="RequisitarMovimentacoesDeCandidato" />
													<c:param name="despesaTC" value="${despesaTC}" />
													<c:param name="receitaTC" value="${receitaTC}" />
													<c:param name="numero_cand"
														value="${campanha.numeroCandidato}" />
													<c:param name="ano" value="${campanha.ano}" />
													<c:param name="cargo_cod" value="${campanha.cargo.codigo}" />
													<c:param name="uf" value="${campanha.uf}" />
													<c:param name="inicioD" value="${0}" />
													<c:param name="qtdPorPaginaD" value="${qtdPorPaginaD}" />
													<c:param name="verTodosD" value="${false}" />
													<c:param name="centroD" value="${1}" />
													<c:param name="inicioR" value="${inicioR}" />
													<c:param name="qtdPorPaginaR" value="${qtdPorPaginaR}" />
													<c:param name="verTodosR" value="${verTodosR}" />
													<c:param name="centroR" value="${centroR}" />
												</c:url>
												<a href="${url_pagInicialD}"><c:out value="primeira... " /></a>
												<c:forEach var="i" begin="${minRaioD}" end="${maxRaioD}">
													<c:url var="url_pagD" value="/mvc">
														<c:param name="logica"
															value="RequisitarMovimentacoesDeCandidato" />
														<c:param name="despesaTC" value="${despesaTC}" />
														<c:param name="receitaTC" value="${receitaTC}" />
														<c:param name="numero_cand"
															value="${campanha.numeroCandidato}" />
														<c:param name="ano" value="${campanha.ano}" />
														<c:param name="cargo_cod" value="${campanha.cargo.codigo}" />
														<c:param name="uf" value="${campanha.uf}" />
														<c:param name="inicioD" value="${(i-1)*qtdPorPaginaD}" />
														<c:param name="qtdPorPaginaD" value="${qtdPorPaginaD}" />
														<c:param name="verTodosD" value="${false}" />
														<c:param name="centroD" value="${i}" />
														<c:param name="inicioR" value="${inicioR}" />
														<c:param name="qtdPorPaginaR" value="${qtdPorPaginaR}" />
														<c:param name="verTodosR" value="${verTodosR}" />
														<c:param name="centroR" value="${centroR}" />
													</c:url>
													<c:choose>
														<c:when test="${i == centroD}">[ ${i} ]</c:when>
														<c:otherwise>
															<a href="${url_pagD}"><c:out value="${i}" /></a>
														</c:otherwise>
													</c:choose>
												</c:forEach>
												<c:url var="url_pagFinalD" value="/mvc">
													<c:param name="logica"
														value="RequisitarMovimentacoesDeCandidato" />
													<c:param name="despesaTC" value="${despesaTC}" />
													<c:param name="receitaTC" value="${receitaTC}" />
													<c:param name="numero_cand"
														value="${campanha.numeroCandidato}" />
													<c:param name="ano" value="${campanha.ano}" />
													<c:param name="cargo_cod" value="${campanha.cargo.codigo}" />
													<c:param name="uf" value="${campanha.uf}" />
													<c:param name="inicioD"
														value="${(indiceD-1)*qtdPorPaginaD}" />
													<c:param name="qtdPorPaginaD" value="${qtdPorPaginaD}" />
													<c:param name="verTodosD" value="${false}" />
													<c:param name="centroD" value="${indiceD}" />
													<c:param name="inicioR" value="${inicioR}" />
													<c:param name="qtdPorPaginaR" value="${qtdPorPaginaR}" />
													<c:param name="verTodosR" value="${verTodosR}" />
													<c:param name="centroR" value="${centroR}" />
												</c:url>
												<a href="${url_pagFinalD}"><c:out value=" ...última" /></a>
												<br> Despesas por Página:
												<c:url var="url_tamanhoOriginalD" value="/mvc">
													<c:param name="logica"
														value="RequisitarMovimentacoesDeCandidato" />
													<c:param name="despesaTC"
														value="${campanha.despesaTotalCalculada}" />
													<c:param name="receitaTC"
														value="${campanha.receitaTotalCalculada}" />
													<c:param name="numero_cand"
														value="${campanha.numeroCandidato}" />
													<c:param name="ano" value="${campanha.ano}" />
													<c:param name="cargo_cod" value="${campanha.cargo.codigo}" />
													<c:param name="uf" value="${campanha.uf}" />
													<c:param name="inicioD" value="${0}" />
													<c:param name="qtdPorPaginaD" value="${10}" />
													<c:param name="verTodosD" value="${false}" />
													<c:param name="centroD" value="${centroD}" />
													<c:param name="inicioR" value="${inicioR}" />
													<c:param name="qtdPorPaginaR" value="${qtdPorPaginaR}" />
													<c:param name="verTodosR" value="${verTodosR}" />
													<c:param name="centroR" value="${centroR}" />
												</c:url>
												<a href="${url_tamanhoOriginalD}"> ${10}</a>
												<c:forEach var="i" begin="1" end="${qtdDePPD}">
													<c:url var="url_tamanhosD" value="/mvc">
														<c:param name="logica"
															value="RequisitarMovimentacoesDeCandidato" />
														<c:param name="logica"
															value="RequisitarMovimentacoesDeCandidato" />
														<c:param name="despesaTC" value="${despesaTC}" />
														<c:param name="receitaTC" value="${receitaTC}" />
														<c:param name="numero_cand"
															value="${campanha.numeroCandidato}" />
														<c:param name="ano" value="${campanha.ano}" />
														<c:param name="cargo_cod" value="${campanha.cargo.codigo}" />
														<c:param name="uf" value="${campanha.uf}" />
														<c:param name="inicioD" value="${0}" />
														<c:choose>
															<c:when test="${i == 5}">
																<c:param name="qtdPorPaginaD" value="${250}" />
															</c:when>
															<c:when test="${i == 6 }">
																<c:param name="qtdPorPaginaD" value="${500}" />
															</c:when>
															<c:when test="${i == 7 }">
																<c:param name="qtdPorPaginaD" value="${1000}" />
															</c:when>
															<c:when test="${i == 8 }">
																<c:param name="qtdPorPaginaD" value="${2000}" />
															</c:when>
															<c:otherwise>
																<c:param name="qtdPorPaginaD" value="${i*25}" />
															</c:otherwise>
														</c:choose>
														<c:param name="verTodosD" value="${false}" />
														<c:param name="centroD" value="${1}" />
														<c:param name="inicioR" value="${inicioR}" />
														<c:param name="qtdPorPaginaR" value="${qtdPorPaginaR}" />
														<c:param name="verTodosR" value="${verTodosR}" />
														<c:param name="centroR" value="${centroR}" />
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
													<c:param name="logica"
														value="RequisitarMovimentacoesDeCandidato" />
													<c:param name="despesaTC" value="${despesaTC}" />
													<c:param name="receitaTC" value="${receitaTC}" />
													<c:param name="numero_cand"
														value="${campanha.numeroCandidato}" />
													<c:param name="ano" value="${campanha.ano}" />
													<c:param name="cargo_cod" value="${campanha.cargo.codigo}" />
													<c:param name="uf" value="${campanha.uf}" />
													<c:param name="inicioD" value="${0}" />
													<c:param name="qtdPorPaginaD" value="${0}" />
													<c:param name="verTodosD" value="${true}" />
													<c:param name="centroD" value="${1}" />
													<c:param name="inicioR" value="${inicioR}" />
													<c:param name="qtdPorPaginaR" value="${qtdPorPaginaR}" />
													<c:param name="verTodosR" value="${verTodosR}" />
													<c:param name="centroR" value="${centroR}" />
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