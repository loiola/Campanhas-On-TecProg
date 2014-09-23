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

	<%@include file="imports/cabecalhopartidos.jsp"%>

	<!-- CONTEUDO DA PAGINA DE INFORMAÇÕES -->
	<div id="pagina">
		<div class="titulo_topo" id="tt_partido">
			<h3>Candidatos</h3>
		</div>
		<div id="conteudo_informacoes">
			<div class="informacoes">
				<h1>
					<c:url var="url_party" value="/mvc">
						<c:param name="logic" value="SelectPoliticalParty"></c:param>
						<c:param name="partyAcronym" value="${party.partyAcronym}"></c:param>
					</c:url>
					<center>
						<span id="perfilPartido"><a href="${url_party}">${party.partyName}</a></span>
					</center>
				</h1>
				<center>
					<p>
						Listagem de <b>Candidatos</b> do ano de <b>${ano}</b>. Clique no
						candidato desejado para visualizar mais informações.
					</p>
				</center>
				<center>
					<table class="sortable" id="gradient-style-listaCandidatoPartido"
						summary="Meeting Results">
						<thead>
							<tr>
								<th scope="col"><center>Candidato</center></th>
								<th scope="col"><center>Nome de Urna</center></th>
								<th scope="col"><center>Cargo</center></th>
								<th scope="col"><center>Número de Eleição</center></th>
							</tr>
						</thead>

						<c:forEach var="campaign" items="${listCampaign}" begin="${first}"
							end="${first+(quantityPerPage-1)}" varStatus="listagem">
							<tr>
								<td><c:url var="candidateUrl" value="/mvc">
										<c:param name="logic" value="SelectCandidate"></c:param>
										<c:param name="electoralTitle"
											value="${campaign.campaignCandidate.candidateElectoralTitle}"></c:param>
									</c:url> <a href="${candidateUrl}">${campaign.campaignCandidate.candidateName}</a>
									<br></td>
								<td>
									<center>${campaign.campaignNameOfUrn}</center>
								</td>
								<td>
									<center>${campaign.campaignPosition.positionDescription}</center>
								</td>
								<td>
									<center>${campaign.campaignCandidateNumber}</center>
								</td>
							</tr>
						</c:forEach>

						<tfoot>
							<tr>
								<td colspan="4"><center>
										Páginas:
										<c:forEach var="i" begin="1" end="${index}">
											<c:url var="url_pag" value="/mvc">
												<c:param name="logic"
													value="VisualizeCandidateOfPoliticalParty"></c:param>
												<c:param name="partyAcronym" value="${partyAcronym}"></c:param>
												<c:param name="year" value="${year}"></c:param>
												<c:param name="first" value="${(i-1)*quantityPerPage}"></c:param>
												<c:param name="quantityPerPage" value="${quantityPerPage}"></c:param>
												<c:param name="seeAllPoliticalParties" value="${false}"></c:param>
											</c:url>
											<c:choose>
												<c:when test="${i == actual}">[ ${i} ]</c:when>
												<c:otherwise>
													<a href="${url_pag}"><c:out value="${i}" /></a>
												</c:otherwise>
											</c:choose>
										</c:forEach>
										<br> Partidos por Página:
										<c:url var="url_tamanhoOriginal" value="/mvc">
											<c:param name="logic"
												value="VisualizeCandidateOfPoliticalParty"></c:param>
											<c:param name="partyAcronym" value="${partyAcronym}"></c:param>
											<c:param name="year" value="${year}"></c:param>
											<c:param name="first" value="${0}"></c:param>
											<c:param name="quantityPerPage" value="${10}"></c:param>
											<c:param name="seeAllPoliticalParties" value="${false}"></c:param>
										</c:url>
										<a href="${url_tamanhoOriginal}"> ${10}</a>
										<c:forEach var="i" begin="1" end="${quantityOfPP}">
											<c:url var="url_tamanhos" value="/mvc">
												<c:param name="logic"
													value="VisualizeCandidateOfPoliticalParty"></c:param>
												<c:param name="partyAcronym" value="${partyAcronym}"></c:param>
												<c:param name="year" value="${year}"></c:param>
												<c:param name="first" value="${0}"></c:param>
												<c:param name="quantityPerPage" value="${i*25}"></c:param>
												<c:param name="seeAllPoliticalParties" value="${false}"></c:param>
											</c:url>
											<a href="${url_tamanhos}"> ${i*25}</a>
										</c:forEach>
										<c:url var="url_todos" value="/mvc">
											<c:param name="logic"
												value="VisualizeCandidateOfPoliticalParty"></c:param>
											<c:param name="partyAcronym" value="${partyAcronym}"></c:param>
											<c:param name="year" value="${year}"></c:param>
											<c:param name="first" value="${0}"></c:param>
											<c:param name="quantityPerPage" value="${0}"></c:param>
											<c:param name="seeAllPoliticalParties" value="${true}"></c:param>
										</c:url>
										<a href="${url_todos}"> Ver Todos</a>
									</center></td>
							</tr>
							<tr>
								<td colspan="4">Dados de acordo com os arquivos disponíveis
									no site de <a
									href="http://dados.gov.br/dataset/prestacao-de-contas-das-campanhas-eleitorais"
									target="_blank"> Dados Abertos do Governo Federal</a>
								</td>
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