<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Top Five - Campanhas-ON</title>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<link rel="shortcut icon" href=img/favicon.ico type="image/x-icon" />
<link href="css/layout.css" rel="stylesheet" type="text/css" media="all">
<link href="css/menu.css" rel="stylesheet" type="text/css" media="all">
<link href="css/rodape.css" rel="stylesheet" type="text/css" media="all">
<link href="css/top5.css" rel="stylesheet" type="text/css" media="all">
<link href="css/conteudoInformacoes.css" rel="stylesheet"
	type="text/css" media="all">
<!-- Google Chart -->
<script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
    	  
        var data = google.visualization.arrayToDataTable([                                                
          ['Nome do Candidato',	'Despesa Total Declarado'],
          ["${candidate1.campaignNameOfUrn}",	${candidate1.campaignMaximumExpenseDeclared}],
          ["${candidate2.campaignNameOfUrn}",	${candidate2.campaignMaximumExpenseDeclared}],
          ["${candidate3.campaignNameOfUrn}",	${candidate3.campaignMaximumExpenseDeclared}],
          ["${candidate4.campaignNameOfUrn}",	${candidate4.campaignMaximumExpenseDeclared}],
          ["${candidate5.campaignNameOfUrn}",	${candidate5.campaignMaximumExpenseDeclared}],
        ]);

        var options = {
          title: '5 Maiores Despesas Máximas Declaradas para no ano de '+${electionYear}+' : '+"${position}",
          hAxis: {title: 'Candidatos'}
        };

        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
<!-- END Google Chart -->

</head>

<body>

	<%@include file="imports/cabecalhoTop5.jsp"%>

	<div id="pagina">
		<div class="titulo_topo" id="tt_general">
			<h3>Top Five</h3>
		</div>
		<div id="conteudo_informacoes">
			<div class="informacoes">
			
				<div>
					<center>
						<h4>Top Five de Despesas Máximas Declaradas</h4>
						
						Para visualizar o gráfico com as cinco maiores despesas máximas declaradas
						<br>de uma campanha de um determinado pleito e ano basta selecionar as opções
						<br>desejadas nos filtros e clicar em <b>Gerar Gráfico</b>.
					</center>
				</div>
				<p>
				<c:url var="link_submit" value="/mvc">
					<c:param name="logic" value="VisualizeTopFive"></c:param>
				</c:url>
				<br>
				<form action="${link_submit}" method="post">
					<div id="primeiroSelect">
						Ano: <select name="electionYear">
							<option value="2010">2010
							<option value="2006">2006
							<option value="2002">2002
						</select>
					</div>
					<br> <br>
					<div id="segundoSelect">
						Cargo: <select name="position">
							<option value="Presidente">Presidente
							<option value="Governador">Governador
							<option value="Senador">Senador
						</select>
					</div>
					<br><p>
					<input id="botaoTop5" type="submit"
						value="Gerar Gráfico"/>
				</form>
				<br>
				<div id="chart_div" style="width: 900px; height: 500px;"></div>
			</div>
		</div>
	</div>

	<%@include file="imports/rodape.jsp"%>
</body>
</html>