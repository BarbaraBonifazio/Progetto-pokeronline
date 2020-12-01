<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="it.pokeronline.model.tavolo.Tavolo"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Visualizza Tavolo</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">

</head>

<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

		<div class='card'>
			<div class='card-header'>Visualizza Dettaglio Tavolo</div>

			
			<div class='card-body'>
				<dl class="row">


					<dt class="col-sm-3 text-right">Id Tavolo:</dt>
					<dd class="col-sm-9">${tavoloPerShow.id}</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Esperienza Minima:</dt>
					<dd class="col-sm-9">${tavoloPerShow.expMin}</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Puntata Minima:</dt>
					<dd class="col-sm-9">${tavoloPerShow.cifraMin}</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Denominazione:</dt>
					<dd class="col-sm-9">${tavoloPerShow.denominazione}</dd>
				</dl>	
				
				<dl class="row">
					<dt class="col-sm-3 text-right">Data Creazione:</dt>
					<dd class="col-sm-9">${tavoloPerShow.dataCreazione}</dd>
				</dl>
 			</div>
		

			<div class='card-footer'>
				<a Style="color:green !important" href="${pageContext.request.contextPath}/tavolo/PrepareSearchTavoliServlet?idUser=${idUserPerSearch}"
					class='btn btn-outline-secondary' style='width: 90px'> <i
					class='fa fa-chevron-left'></i> Nuova Ricerca
				</a>
			</div>
		</div> 
		<!-- end main container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>