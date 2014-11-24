<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Partidos - Campanhas-ON</title>
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

	<%@include file="imports/header_party.jsp"%>

	<div id="pagina">
		<div class="titulo_topo" id="tt_partido">
			<center>
				<h3>Partido</h3>
			</center>
		</div>
		<div id="conteudo_informacoes">
			<div class="informacoes">
				<div id="perfil_partido">
					<center>
						<h1>${party.partyName}</h1>
					</center>
					<br />

					<div id="informacoes_partido">
						<div id="logo_partido">
							<a
								href="http://www.tse.jus.br/partidos/partidos-politicos/${linktse}"
								target="_blank"> <img src="img/partidos/${siglaUnder}.jpg"
								width="140" height="150" />
							</a>
						</div>
						<center>
							<table>
								<tr>
									<td><b>Sigla:</b></td>
									<td>${party.partyAcronym}</td>
								</tr>
								<tr>
									<td><b>Número:</b></td>
									<td>${party.partyNumber}</td>
								</tr>
								<c:choose>
									<c:when test="${not empty party.partyConcession}">
										<tr>
											<td><b>Deferimento:</b></td>
											<td>${party.partyConcession}</td>
										</tr>
										<tr>
											<td><b>Mais informações:</b></td>
											<td><span id="perfilPartido"><a
													href="http://www.tse.jus.br/partidos/partidos-politicos/${linktse}"
													target="_blank"> clique aqui</a></span></td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr>
											<br />
										</tr>
										<tr>
											<br />
										</tr>
									</c:otherwise>
								</c:choose>
							</table>
						</center>
						<div id="ano_partido">
							<h2 align="center">Consulta de Candidatos</h2>
							<center>
								<p>
									<i>Clique em um dos anos para acessar <br /> a lista de
										candidatos deste Partido.
									</i>
								</p>
								<table id="gradient-style-perfilPartido"
									summary="Meeting Results">
									<thead>
										<tr>
											<th scope="col"><center>Ano</center></th>
										</tr>
									</thead>

									<c:forEach var="year" items="${years}">
										<tbody>
											<tr>
												<td><c:url var="YearUrl" value="/mvc">
														<c:param name="logic"
															value="VisualizeCandidateOfPoliticalParty" />
														<c:param name="partyAcronym" value="${party.partyAcronym}" />
														<c:param name="year" value="${year}" />

														<!-- PAGINATION LOGIC PARAMETERS -->
														<c:param name="pagination__first_page" value="${0}" />
														<c:param name="pagination__qtd_of_terms" value="${10}" />
														<c:param name="pagination__see_all" value="${false}" />
														<c:param name="pagination__center_page" value="${1}"></c:param>
														<!-- END PAGINATION LOGIC PARAMETERS -->
													</c:url>
													<center>
														<a href="${YearUrl}">${year}</a>
													</center></td>
											</tr>
										</tbody>
									</c:forEach>
								</table>
							</center>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@include file="imports/rodape.jsp"%>
</body>
</html>