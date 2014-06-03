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
</head>
<body>

	<%@include file="imports/cabecalhopartidos.jsp"%>

	<div id="pagina">
		<div class="titulo_topo">
			<h3>Perfil</h3>
		</div>
		<div id="conteudo_informacoes">
			<div class="informacoes">
				<p>
					Abaixo o Perfil do <b>Partido</b> selecionado.
				</p>

				<table>
					<tr>
						<td>Nome</td>
						<td>${partido.nome}</td>
					</tr>
					
					<tr>
						<td>Sigla:</td>
						<td>${partido.sigla}</td>
					</tr>
				</table>
				<br />
				
				<c:forEach var ="ano" items ="${anos}" >
					<table border="2" width="300">
					<tr><td>
						<c:url var="AnoUrl" value="/VisualizarCandidatosPartido">
							<c:param name="sigla" value="${partido.sigla}" />						
							<c:param name="ano" value="${ano}" />
						</c:url>
						<a href="${AnoUrl}">${ano}</a>
					</td></tr>
					</table><br />
				</c:forEach>

				<br>
			</div>
		</div>
	</div>

	<%@include file="imports/rodape.jsp"%>


</body>
</html>