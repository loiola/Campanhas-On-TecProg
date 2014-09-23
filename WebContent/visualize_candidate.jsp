<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Candidatos - Campanhas-ON</title>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<link rel="shortcut icon" href=img/favicon.ico type="image/x-icon" />
<link href="css/layout.css" rel="stylesheet" type="text/css" media="all">
<link href="css/menu.css" rel="stylesheet" type="text/css" media="all">
<link href="css/rodape.css" rel="stylesheet" type="text/css" media="all">
<link href="css/conteudoInformacoes.css" rel="stylesheet"
	type="text/css" media="all">
<link href="css/tabelas.css" rel="stylesheet" type="text/css" media="all">	
<!-- Google Chart -->
<script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
    	  
        var data = google.visualization.arrayToDataTable([                                                
          ['Ano',	'Despesa Máxima Declarada', 'Despesa Total Calculada', 'Receita Total Calculada'],
          ['2002',	${expense1}, ${expense01}, ${revenue1}],
          ['2006',	${expense2}, ${expense02}, ${revenue2}],
          ['2010',	${expense3}, ${expense03}, ${revenue3}],
        ]);

        var options = {
          title: 'Gráfico de Despesa Máxima Declarada por Campanha Disputada',
          hAxis: {title: 'Ano'}
        };

        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
<!-- END Google Chart -->
</head>
<body>

	<%@include file="imports/cabecalhocandidatos.jsp"%>

	<div id="pagina">
		<div class="titulo_topo" id="tt_candidato">
			<h3>Perfil</h3>
		</div>
		<div id="conteudo_informacoes">
			<div class="informacoes">
				<div id="perfil_partido">
					<center>
						<h1>
							<c:out value="${candidate.candidateName}" />
						</h1>
					</center>
					<br />

					<!-- Identificacao do candidato -->
					<div id="informacoes_partido">
						<div id="logo_partido">
							<img src="img/perfil.jpg" width="140" height="150" />
						</div>
						<center>
							<table>
								<tr>
									<td><b>Nome:</b></td>
									<td><c:out value="${candidate.candidateName}" /></td>

								</tr>
								<tr>
									<td><b>Título Eleitoral:</b></td>
									<td><c:out value="${candidate.candidateElectoralTitle}" /></td>
								</tr>

							</table>
							<center>
								<p>
									<i>Clique no ano para o qual deseja visualizar a <br />
										prestação de contas deste candidato:
									</i>
								</p>
							</center>

							<div id="ano_partido">
								<h2 align="center">Consulta à Movimentação</h2>
								<!-- Anos em que ele concorreu -->
								<c:forEach var="campaign" items="${campaigns}">
									<table id="gradient-style-perfilCandidato"
										summary="Meeting Results">
										<thead>
											<tr>
												<th scope="col" colspan="4"><c:url var="YearUrl"
														value="/mvc">
														<c:param name="logic"
															value="RequestFinancialTransactionOfCandidate" />
														<c:param name="totalExpenseCalculatedValue"
															value="${campaign.campaignTotalExpenseCalculated}" />
														<c:param name="totalRevenueCalculatedValue"
															value="${campaign.campaignTotalRevenueCalculated}" />
														<c:param name="candidateNumber"
															value="${campaign.campaignCandidateNumber}" />
														<c:param name="year" value="${campaign.campaignYear}" />
														<c:param name="codeOfPosition" value="${campaign.campaignPosition.positionCode}" />
														<c:param name="uf" value="${campaign.campaignCountryState}" />
														<c:param name="firstRevenue" value="${0}" />
														<c:param name="quantityRevenuePerPage" value="${10}" />
														<c:param name="seeAllRevenues" value="${false}" />
														<c:param name="centerOfRevenue" value="${1}" />
														<c:param name="firstExpense" value="${0}" />
														<c:param name="quantityExpensePerPage" value="${10}" />
														<c:param name="seeAllExpenses" value="${false}" />
														<c:param name="centerOfExpense" value="${1}" />
													</c:url> <a href="${YearUrl}"><center>Campanha de ${campaign.campaignYear}</center></a></th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td width=""><c:url var="PartyUrl" value="/mvc">
														<c:param name="logic" value="SelectPoliticalParty" />
														<c:param name="partyAcronym" value="${campaign.campaignParty.partyAcronym}" />
													</c:url> <b>Partido:</b> <a href="${PartyUrl}">${campaign.campaignParty.partyAcronym}</a>
												</td>
												<td width="250"><b>Cargo Pleiteado:</b> <br>${campaign.campaignPosition.positionDescription}</td>
												<td><b>UF:</b> ${campaign.campaignCountryState}</td>
												<td><b>Número:</b> ${campaign.campaignCandidateNumber}</td>
											</tr>
										</tbody>
									</table>
								</c:forEach>
							</div>
						</center>
						<br>
					</div>
				</div>
			</div>
			<br><br>
			<center>
				<div id="chart_div" style="width: 900px; height: 500px;"></div>
			</center>
		</div>
	</div>
	<!-- FIM CONTEUDO-->

	<%@include file="imports/rodape.jsp"%>
</body>
</html>