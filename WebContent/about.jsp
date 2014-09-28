<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Informações - Campanhas-ON</title>
<link rel="shortcut icon" href=img/favicon.ico type="image/x-icon" />
<link href="css/layout.css" rel="stylesheet" type="text/css" media="all">
<link href="css/menu.css" rel="stylesheet" type="text/css" media="all">
<link href="css/rodape.css" rel="stylesheet" type="text/css" media="all">
<link href="css/conteudoInformacoes.css" rel="stylesheet"
	type="text/css" media="all">
</head>
<body>

	<!--  CABEÇALHO -->
	<%@include file="imports/cabecalho.jsp"%>
	<!-- FIM CABEÇALHO -->

	<!-- CONTEUDO DA PAGINA DE INFORMAÇÕES -->
	<div id="pagina">
		<div class="titulo_topo" id="tt_info">
			<h3>Informações</h3>
		</div>
		<div id="conteudo_informacoes">
			<div class="informacoes">
				<h4>-- Quem Somos?</h4>
				<p></p>
				<p>	Somos alunos das disciplinas de Métodos de Desenvolvimento de Software e Gestão de Portifólios e Projetos de Software do curso de Engenharia de Software
					da Universidade de Brasília. Composto por cinco desenvolvedores: Augusto Modesto, Jonathan Moraes, Matheus Ferraz, Rafael Valença e Yuri Loiola; dois
					gestores: Luciano Prestes e Macártur de Sousa; e sob orientação de dois <i>coaches</i>: João Henrique e Maria Luciene.
				</p>
				<p>	O projeto é parte da temática de ambas as disciplinas, conceituada e aplicada pelo Prof. Msc. Hilmer Rodrigues Neri. O tema exercido envolve a criação de um 
					sistema que extraia informações de Dados Abertos do Governo Federal e os traduza para informações acessíveis e inteligíveis. Tal metodologia não só nos
					ensina métodos de desenvolvimento e gestão de produtos de software, como nos permite aprender a atuar de forma consciente na sociedade em que vivemos.
					Além de um projeto de software, é uma iniciativa que promove a cidadania.
				</p>
				<h4>-- O que é o Projeto?</h4>
				<p></p>
				<p>	Trata-se de uma solução de software que busca tornar os dados relativos às receitas e despesas de campanhas eleitorais de 2002 a 2010 mais acessíveis e
					legíveis. Esses dados são abertos, disponibilizados pelo Tribunal Superior Eleitoral (TSE) com intervalos de quatro anos, de campanhas presidenciais
					e outros pleitos do Distrito Federal, em arquivos com a extensão ".txt".
					Conforme dispõem os artigos 28 e 32 da Lei nº 9.504/97, os candidatos, partidos políticos e comitês financeiros devem fornecer estes dados à Justiça Eleitoral
					até o trigésimo dia posterior ao término das eleições.
				</p>
				<p>	Contudo, mesmo esses dados sendo públicos, não existe uma forma simplificada e concisa que permita uma real abstração de informação a partir deles.
					Assim, o <b>Campanhas-ON</b> pode ser visto como uma forma integrada de acesso a todos os dados referentes às contas eleitorais, disponibilizados pelo TSE.
					Além disso, é uma ferramenta que objetiva agregar valor aos dados através de gráficos, comparações e relatórios, não sendo, portanto, um mero buscador de
					informação.
				</p> 
				<h4>-- Como Utilizar o <i>Web Site</i>?</h4>
				<p></p>
				<p>	Navegue pelo menu no cabeçalho da página para acessar informações referentes à candidatos e partidos. Tais páginas terão informações complementares que
					auxiliam no uso do software.
				</p>
				<p> <b>Campanhas-ON</b> está em seu segundo <i>release</i>. Novas páginas, tratamento de dados e tecnologias serão incluídas em seus próximos <i>releases</i>.
				</p>
				<br>
				<br>
			</div>
		</div>
		<!-- content  -->
	</div>
	<!-- FIM CONTEUDO-->

	<!-- RODAPÉ -->
	<%@include file="imports/rodape.jsp"%>
	<!-- FIM RODAPÉ -->

</body>
</html>
