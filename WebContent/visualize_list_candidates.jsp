<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Candidatos - Campanhas-ON</title>
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

	<!-- CONTEUDO DA PAGINA DE INFORMAÇÕES -->
	<div id="pagina">
		<div class="titulo_topo" id="tt_candidato">
			<h3>Candidatos</h3>
		</div>
		<div id="conteudo_informacoes">
			<div class="informacoes">
				<center>
					<p>
						Listagem de <b>Candidatos</b>. Clique no candidato desejado para
						visualizar mais informações.
					</p>
				</center>
				<center>
					<table class="sortable" id="gradient-style-listaCandidatos"
						summary="Meeting Results">
						<thead>
							<tr>
								<th scope="col"><center>Candidato</center></th>
							</tr>
						</thead>

						<c:forEach var="candidates" items="${listCandidates}"
							begin="${firstCandidate}" end="${firstCandidate+(quantityCandidatePerPage-1)}"
							varStatus="listagem">

							<tr>
								<td><c:url var="candidateUrl" value="/mvc">
										<c:param name="logic" value="SelectCandidate" />
										<c:param name="electoralTitle"
											value="${candidates.candidateElectoralTitle}" />
									</c:url>
									<center>
										<a href="${candidateUrl}">${candidates.candidateName}</a> <br>
									</center></td>
							</tr>

						</c:forEach>

						<tfoot>
							<tr>
								<td colspan="4"><center>
										Páginas:
										<c:url var="url_pagInicial" value="/mvc">
											<c:param name="logic"
												value="VisualizeResultOfSearchCandidateList" />
											<c:param name="name" value="${name}" />
											<c:param name="firstCandidate" value="${0}" />
											<c:param name="quantityCandidatePerPage" value="${quantityCandidatePerPage}" />
											<c:param name="seeAllCandidates" value="${false}" />
											<c:param name="centerCandidate" value="${1}" />
										</c:url>
										<a href="${url_pagInicial}"><c:out value="primeira... " /></a>
										<c:forEach var="i" begin="${minimumRadius}" end="${maximumRadius}">
											<c:url var="url_pag" value="/mvc">
												<c:param name="logic"
													value="VisualizeResultOfSearchCandidateList" />
												<c:param name="name" value="${name}" />
												<c:param name="firstCandidate" value="${(i-1)*quantityCandidatePerPage}" />
												<c:param name="quantityCandidatePerPage" value="${quantityCandidatePerPage}" />
												<c:param name="seeAllCandidates" value="${false}" />
												<c:param name="centerCandidate" value="${i}" />
											</c:url>
											<c:choose>
												<c:when test="${i == centerCandidate}">[ ${i} ]</c:when>
												<c:otherwise>
													<a href="${url_pag}"><c:out value="${i}" /></a>
												</c:otherwise>
											</c:choose>
										</c:forEach>
										<c:url var="url_pagFinal" value="/mvc">
											<c:param name="logic"
												value="VisualizeResultOfSearchCandidateList" />
											<c:param name="name" value="${name}" />
											<c:param name="firstCandidate" value="${(index-1)*quantityCandidatePerPage}" />
											<c:param name="quantityCandidatePerPage" value="${quantityCandidatePerPage}" />
											<c:param name="seeAllCandidates" value="${false}" />
											<c:param name="centerCandidate" value="${index}" />
										</c:url>
										<a href="${url_pagFinal}"><c:out value=" ...última" /></a> <br>
										Candidatos por Página:
										<c:url var="url_tamanhoOriginal" value="/mvc">
											<c:param name="logic"
												value="VisualizeResultOfSearchCandidateList" />
											<c:param name="name" value="${name}" />
											<c:param name="firstCandidate" value="${0}" />
											<c:param name="quantityCandidatePerPage" value="${10}" />
											<c:param name="seeAllCandidates" value="${false}" />
											<c:param name="centerCandidate" value="${1}" />
										</c:url>
										<a href="${url_tamanhoOriginal}"> ${10}</a>
										<c:forEach var="i" begin="1" end="${quantityOfPP}">
											<c:url var="url_tamanhos" value="/mvc">
												<c:param name="logic"
													value="VisualizeResultOfSearchCandidateList" />
												<c:param name="name" value="${name}" />
												<c:param name="firstCandidate" value="${0}" />
												<c:choose>
													<c:when test="${i == 5}">
														<c:param name="quantityCandidatePerPage" value="${250}" />
													</c:when>
													<c:when test="${i == 6 }">
														<c:param name="quantityCandidatePerPage" value="${500}" />
													</c:when>
													<c:when test="${i == 7 }">
														<c:param name="quantityCandidatePerPage" value="${1000}" />
													</c:when>
													<c:when test="${i == 8 }">
														<c:param name="quantityCandidatePerPage" value="${2000}" />
													</c:when>
													<c:otherwise>
														<c:param name="quantityCandidatePerPage" value="${i*25}" />
													</c:otherwise>
												</c:choose>
												<c:param name="seeAllCandidates" value="${false}" />
												<c:param name="centerCandidate" value="${1}" />
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
										<c:url var="url_todos" value="/mvc">
											<c:param name="logic"
												value="VisualizeResultOfSearchCandidateList" />
											<c:param name="name" value="${name}" />
											<c:param name="firstCandidate" value="${0}" />
											<c:param name="quantityCandidatePerPage" value="${0}" />
											<c:param name="seeAllCandidates" value="${true}" />
											<c:param name="centerCandidate" value="${1}" />
										</c:url>
										<a href="${url_todos}"> Ver Todos</a>
									</center></td>
							</tr>
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
	<!-- FIM CONTEUDO-->

	<%@include file="imports/rodape.jsp"%>

</body>
</html>