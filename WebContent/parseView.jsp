<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Parse - Campanhas-ON</title>
<link rel="shortcut icon" href=img/favicon.ico type="image/x-icon" />
<link href="css/layout.css" rel="stylesheet" type="text/css" media="all">
<link href="css/menu.css" rel="stylesheet" type="text/css" media="all">
<link href="css/rodape.css" rel="stylesheet" type="text/css" media="all">
<link href="css/conteudoInformacoes.css" rel="stylesheet"
	type="text/css" media="all">
</head>
<body>

	<%@include file="imports/cabecalho.jsp"%>

	<!-- CONTEUDO DA PAGINA DE INFORMAÇÕES -->
	<div id="pagina">
		<div class="titulo_topo" id="tt_info">
			<h3>Parse</h3>
		</div>
		<div id="conteudo_informacoes">
			<div class="informacoes">
			
				<!--  PARSE DOS PARTIDOS -->
				<h1>Parse dos Partidos</h1>
				<p>Usar arquivo: utf_partidos_politicos_registrados_tse.txt</p>
				
				<form action="carregarParsePartido" method="POST"
					enctype="multipart/form-data">
					<table>
						<tr>
							<td>Arquivo do Parse:</td>
							<td><input type="file" name="arquivo" size="50" /></td>
						</tr>

						<tr>
							<td></td>
							<td>
								<p align="right">
									<input type="submit" class="botao" value="Carregar" />
								</p>
							</td>
						</tr>
					</table>
				</form>
				
				<!-- PARSE DAS CAMPANHAS -->
				<h1>Parse das Campanhas</h1>
				<p>Usar arquivos: utf_consulta_cand_&ltano&gt_&ltuf&gt.txt </p>
				

				<form action="carregarParseCampanha" method="POST"
					enctype="multipart/form-data">
					<table>
						<tr>
							<td>Arquivo do Parse:</td>
							<td><input type="file" name="arquivo" size="50" /></td>
						</tr>

						<tr>
							<td></td>
							<td>
								<p align="right">
									<input type="submit" class="botao" value="Carregar" />
								</p>
							</td>
						</tr>
					</table>
				</form>
				
				<!-- PARSE DAS MOVIMENTACOES FINANCEIRAS -->
				<h1>Parse das Movimentações Financeiras</h1>
				<p>Usar arquivos: utf_&ltReceita/Despesa&gtCandidatos_&ltano&gt.csv </p>
				
				<form action="carregarParseMovimentacoes" method="POST"
					enctype="multipart/form-data">
					<table>
						<tr>
							<td>Arquivo do Parse:</td>
							<td><input type="file" name="arquivo" size="50" /></td>
						</tr>
						<tr>
							<td>Tipo do Arquivo:</td>
							<td><input type="radio" name="file_type" value="expense"
								checked /> Despesa</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="radio" name="file_type" value="revenue" />
								Receita</td>
						</tr>

						<tr>
							<td>Ano:</td>
							<td><input type="radio" name="file_year" value="2002"
								checked /> 2002</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="radio" name="file_year" value="2006" />
								2006</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="radio" name="file_year" value="2010" />
								2010</td>
						</tr>

						<tr>
							<td></td>
							<td>
								<p align="right">
									<input type="submit" class="botao" value="Carregar" />
								</p>
							</td>
						</tr>
					</table>
				</form>

					
				<br>
			</div>
		</div>
	</div>
	<!-- FIM CONTEUDO-->

	<%@include file="imports/rodape.jsp"%>

</body>
</html>