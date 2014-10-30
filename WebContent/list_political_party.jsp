<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Partidos - Campanhas-ON</title>
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

	<!-- HEADER IMPORT -->
	<%@include file="imports/header_party.jsp"%>
	<!-- END HEADER IMPORT -->

	<!-- CONTENT -->
	<div id="pagina">
		<div class="titulo_topo" id="tt_pesquisa">
			<h3>Listagem</h3>
		</div>
		<div id="conteudo_informacoes">
			<div class="informacoes">
				<center>
					<h3>Selecione um Partido abaixo para visitar seu Perfil</h3>
				</center>

				<center>

					<table class="sortable" id="gradient-style"
						summary="Meeting Results">
						<thead>
							<tr>
								<th scope="col">Nome</th>
								<th scope="col">Sigla</th>
							</tr>

						</thead>

						<!-- PAGINATION LOGIC -->
						<c:forEach var="party" items="${politicalPartyList}"
							begin="${pagination.firstPageOfTheList}"
							end="${pagination.firstPageOfTheList+(pagination.quantityOfTermsPerPage-1)}"
							varStatus="listagem">
							<tr>
								<td><c:url var="url_party" value="/mvc">
										<c:param name="logic" value="SelectPoliticalParty" />
										<c:param name="partyAcronym" value="${party.partyAcronym}" />
									</c:url> <a href="${url_party}"> ${party.partyName} </a></td>
								<td>${party.partyAcronym}</td>
							</tr>
						</c:forEach>
						<tfoot>
							<tr>
								<td colspan="4"><center>
										Páginas:
										<!-- "FIRST" PAGE PARAMETERS -->
										<c:url var="url_pagInicial" value="/mvc">
											<c:param name="logic" value="RequestPoliticalParty" />
											<c:param name="pagination__first_page" value="${0}" />
											<c:param name="pagination__qtd_of_terms"
												value="${pagination.quantityOfTermsPerPage}" />
											<c:param name="pagination__see_all" value="${false}" />
											<c:param name="pagination__center_page" value="${1}" />
										</c:url>
										<a href="${url_pagInicial}"><c:out value="primeira... " /></a>
										<!-- END "FIRST" PAGE PARAMETERS -->
										<!-- PAGES PARAMETERS -->
										<c:forEach var="i"
											begin="${pagination.minimumRadiusOfPagesListed}"
											end="${pagination.maximumRadiusOfPagesListed}">
											<c:url var="url_pag" value="/mvc">
												<c:param name="logic" value="RequestPoliticalParty" />
												<c:param name="pagination__first_page"
													value="${(i-1)*pagination.quantityOfTermsPerPage}" />
												<c:param name="pagination__qtd_of_terms"
													value="${pagination.quantityOfTermsPerPage}" />
												<c:param name="pagination__see_all" value="${false}" />
												<c:param name="pagination__center_page" value="${i}" />
											</c:url>
											<c:choose>
												<c:when test="${i == pagination.centerOfPagesListed}">[ ${i} ]</c:when>
												<c:otherwise>
													<a href="${url_pag}"><c:out value="${i}" /></a>
												</c:otherwise>
											</c:choose>
										</c:forEach>
										<!-- END PAGES PARAMETERS -->
										<!-- "LAST" PAGE PARAMETERS -->
										<c:url var="url_pagFinal" value="/mvc">
											<c:param name="logic" value="RequestPoliticalParty" />
											<c:param name="pagination__first_page"
												value="${(pagination.indexOfPages-1)*pagination.quantityOfTermsPerPage}" />
											<c:param name="pagination__qtd_of_terms"
												value="${pagination.quantityOfTermsPerPage}" />
											<c:param name="pagination__see_all" value="${false}" />
											<c:param name="pagination__center_page"
												value="${pagination.indexOfPages}" />
										</c:url>
										<a href="${url_pagFinal}"><c:out value="...última" /></a>
										<!-- END "LAST" PAGE PARAMETERS -->
										<br> Partidos por Página:
										<!-- "ORIGINAL" PAGINATION SIZE PARAMETERS -->
										<c:url var="url_tamanhoOriginal" value="/mvc">
											<c:param name="logic" value="RequestPoliticalParty" />
											<c:param name="pagination__first_page" value="${0}" />
											<c:param name="pagination__qtd_of_terms" value="${10}" />
											<c:param name="pagination__see_all" value="${false}" />
											<c:param name="pagination__center_page" value="${1}" />
										</c:url>
										<a href="${url_tamanhoOriginal}"> ${10}</a>
										<!-- END "ORIGINAL" PAGINATION SIZE PARAMETERS -->
										<!-- PAGINATION SIZES PARAMETERS -->
										<c:forEach var="i" begin="1"
											end="${pagination.quantityOfPagesListedInThePage}">
											<c:url var="url_tamanhos" value="/mvc">
												<c:param name="logic" value="RequestPoliticalParty" />
												<c:param name="pagination__first_page" value="${0}" />
												<c:choose>
													<c:when test="${i == 5}">
														<c:param name="pagination__qtd_of_terms" value="${250}" />
													</c:when>
													<c:when test="${i == 6 }">
														<c:param name="pagination__qtd_of_terms" value="${500}" />
													</c:when>
													<c:when test="${i == 7 }">
														<c:param name="pagination__qtd_of_terms" value="${1000}" />
													</c:when>
													<c:when test="${i == 8 }">
														<c:param name="pagination__qtd_of_terms" value="${2000}" />
													</c:when>
													<c:otherwise>
														<c:param name="pagination__qtd_of_terms" value="${i*25}" />
													</c:otherwise>
												</c:choose>
												<c:param name="pagination__see_all" value="${false}" />
												<c:param name="pagination__center_page" value="${1}" />
											</c:url>
											<a href="${url_tamanhos}"> <c:choose>
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
										<!-- END PAGINATION SIZES PARAMETERS -->
										<!-- "SEE ALL" PAGINATION SIZE PARAMETERS -->
										<c:url var="url_todos" value="/mvc">
											<c:param name="logic" value="RequestPoliticalParty" />
											<c:param name="pagination__first_page" value="${0}" />
											<c:param name="pagination__qtd_of_terms" value="${0}" />
											<c:param name="pagination__see_all" value="${true}" />
											<c:param name="pagination__center_page" value="${1}" />
										</c:url>
										<a href="${url_todos}"> Ver Todos</a>
										<!-- END "SEE ALL" PAGINATION SIZE PARAMETERS -->
									</center></td>
							</tr>
							<!-- END PAGINATION LOGIC -->
							<tr>
								<td colspan="4">Os nomes e as siglas dos Partidos Políticos
									estão exibidos da forma em que foram registrados no TSE.</td>
							</tr>
						</tfoot>
					</table>
				</center>
				<br>
			</div>
		</div>
	</div>
	<!-- END CONTENT -->

	<!-- FOOTER IMPORT -->
	<%@include file="imports/rodape.jsp"%>
	<!-- END FOOTER IMPORT -->

</body>
</html>