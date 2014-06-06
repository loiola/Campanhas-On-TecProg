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

	<%@include file="imports/cabecalho.jsp"%>

	<div id="pagina">
		<div class="titulo_topo">
			<h3>Perfil</h3>
		</div>
		<div id="conteudo_informacoes">
			<div class="informacoes">
				<p>
					Abaixo o Perfil do <b>Candidato</b> selecionado.
				</p>

				<!-- Identificacao do candidato -->
				<table>
					<tr>
						<td>Nome:</td>
						<td><c:out value="${candidato.nome}" /></td>
					</tr>
					<tr>
						<td>Título Eleitoral:</td>
						<td><c:out value="${candidato.tituloEleitoral}" /></td>
					</tr>
				</table>

				<!-- Anos em que ele concorreu -->
				<c:forEach var="campanha" items="${campanhas}">

					<table border="2" width="600">
						<tbody>
							<tr>
								<td rowspan="2">
									<c:url var="AnoUrl" value="/requisitarMovimentacoes">
										<c:param name="tabela" value="campanha"></c:param>
										<c:param name="numero" value="${campanha.numeroCandidato}"></c:param>
										<c:param name="ano" value="${campanha.ano}"></c:param>
									</c:url>
									<a href="${AnoUrl}">${campanha.ano}</a></td>
								<td>
									<c:url var="partidoUrl" value="/SelecionarPartido">
										<c:param name="sigla" value="${campanha.partido.sigla}"></c:param>
									</c:url> Partido: <a href="${partidoUrl}">${campanha.partido.sigla}</a>
								</td>
								<td>Cargo Pleiteado: ${campanha.cargo.descricao}</td>
							</tr>
							<tr>
								<td>UF: ${campanha.uf}</td>
								<td>Número: ${campanha.numeroCandidato}</td>
							</tr>
						</tbody>
					</table>
					<br>
				</c:forEach>
				<br>
			</div>
		</div>
	</div>
	<!-- FIM CONTEUDO-->

	<%@include file="imports/rodape.jsp"%>


</body>
</html>