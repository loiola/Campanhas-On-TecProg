<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- HEADER AND MENU -->
<div id="topo">
	<div id="cabecalho">
		<div id="logo">
			<a href="index.jsp"> <img src="img/logo.png" width="257"
				height="58" alt="Logo" title="CampanhasOn">
			</a> <span class="titulo">Informe-se JÁ!</span>
		</div>

		<!-- MENU -->
		<div id="menu">
			<ul>
				<li><a href="index.jsp" class="home">Home</a></li>
				<li><a href="request_candidate.jsp" class="candidatos">Candidatos</a>
				</li>
				<li><c:url var="url_partido" value="/mvc">
						<c:param name="logic" value="RequestPoliticalParty"></c:param>

						<!-- PAGINATION LOGIC PARAMETERS -->
						<c:param name="pagination__first_page" value="${0}"></c:param>
						<c:param name="pagination__qtd_of_terms" value="${10}"></c:param>
						<c:param name="pagination__see_all" value="${false}"></c:param>
						<c:param name="pagination__center_page" value="${1}"></c:param>
						<!-- END PAGINATION LOGIC PARAMETERS -->

					</c:url> <a href="${url_partido}" class="partidos">Partidos</a></li>
				<li><a href="top_five.jsp" class="top5">TOP 5</a></li>
			</ul>
		</div>
		<!-- END MENU -->

	</div>
</div>
<!-- END HEADERS AND MENU -->