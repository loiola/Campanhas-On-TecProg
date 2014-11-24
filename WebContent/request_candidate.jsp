<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Candidatos - Campanhas-ON</title>
<link rel="shortcut icon" href=img/favicon.ico type="image/x-icon" />
<link href="css/layout.css" rel="stylesheet" type="text/css" media="all">
<link href="css/menu.css" rel="stylesheet" type="text/css" media="all">
<link href="css/rodape.css" rel="stylesheet" type="text/css" media="all">
<link href="css/conteudoInformacoes.css" rel="stylesheet"
	type="text/css" media="all">
</head>
<body>

	<%@include file="imports/header_candidate.jsp"%>

	<!-- CONTEUDO DA PAGINA DE INFORMAÇÕES -->
	<div id="pagina">
		<div class="titulo_topo" id="tt_pesquisa">
			<h3>Pesquisar</h3>
		</div>
		<div id="conteudo_informacoes">
			<div class="informacoes">

				<div id="pesquisaRapida">
					<center>
						<h4>Busca Rápida</h4>
						<p>
							Entre com uma parte do nome do <b>Candidato</b> ou nome de urna
							que deseja buscar, e em seguida, clique em buscar.
						</p>

						<c:url var="link_search" value="/mvc">
							<c:param name="logic"
								value="VisualizeResultOfSearchCandidateList"></c:param>
								
							<!-- PAGINATION LOGIC PARAMETERS -->
							<c:param name="pagination__first_page" value="${0}"></c:param>
							<c:param name="pagination__qtd_of_terms" value="${10}"></c:param>
							<c:param name="pagination__see_all" value="${false}"></c:param>
							<c:param name="pagination__center_page" value="${1}"></c:param>
							<!-- END PAGINATION LOGIC PARAMETERS -->
							
						</c:url>

						<form action="${link_search}" method="post">
							<br> <input class="campoRapido" type="text"
								name="nameOfCandidateSearch" required> <br> <br>
							<br> <input id="botao" type="submit" value="Buscar" />
						</form>
					</center>
				</div>
			</div>
		</div>
	</div>
	<!-- FIM CONTEUDO-->

	<%@include file="imports/rodape.jsp"%>

</body>
</html>
