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
						<c:forEach var="party" items="${politicalPartyList}"
							begin="${firstPoliticalParty}" end="${firstPoliticalParty+(quantityPoliticalPartyPerPage-1)}"
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
										<c:forEach var="i" begin="1" end="${index}">
											<c:url var="url_pag" value="/mvc">
												<c:param name="logic" value="RequestPoliticalParty" />
												<c:param name="firstPoliticalParty" value="${(i-1)*quantityPoliticalPartyPerPage}" />
												<c:param name="quantityPoliticalPartyPerPage" value="${quantityPoliticalPartyPerPage}" />
												<c:param name="seeAllPoliticalParties" value="${false}" />
											</c:url>
											<c:choose>
												<c:when test="${i == currentPoliticalParty}">[ ${i} ]</c:when>
												<c:otherwise>
													<a href="${url_pag}"><c:out value="${i}" /></a>
												</c:otherwise>
											</c:choose>
										</c:forEach>
										<br> Partidos por Página:
										<c:url var="url_tamanhoOriginal" value="/mvc">
											<c:param name="logic" value="RequestPoliticalParty" />
											<c:param name="firstPoliticalParty" value="${0}" />
											<c:param name="quantityPoliticalPartyPerPage" value="${10}" />
											<c:param name="seeAllPoliticalParties" value="${false}" />
										</c:url>
										<a href="${url_tamanhoOriginal}"> ${10}</a>
										<c:forEach var="i" begin="1" end="${quantityOfPP}">
											<c:url var="url_tamanhos" value="/mvc">
												<c:param name="logic" value="RequestPoliticalParty" />
												<c:param name="firstPoliticalParty" value="${0}" />
												<c:param name="quantityPoliticalPartyPerPage" value="${i*25}" />
												<c:param name="seeAllPoliticalParties" value="${false}" />
											</c:url>
											<a href="${url_tamanhos}"> ${i*25}</a>
										</c:forEach>
										<c:url var="url_todos" value="/mvc">
											<c:param name="logic" value="RequestPoliticalParty" />
											<c:param name="firstPoliticalParty" value="${0}" />
											<c:param name="quantityPoliticalPartyPerPage" value="${0}" />
											<c:param name="seeAllPoliticalParties" value="${true}" />
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