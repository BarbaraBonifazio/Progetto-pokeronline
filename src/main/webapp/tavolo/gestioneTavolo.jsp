<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>

<jsp:include page="../header.jsp" />

<!-- Custom styles for this template -->
<link href="./assets/css/global.css" rel="stylesheet">
<style type="text/css">
body {
	padding-top: 3.5rem;
}
</style>

<title>Gestione Tavolo</title>
</head>
<body>

	<jsp:include page="../navbar.jsp"></jsp:include>


	<main role="main text-center">

		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<div class="container">
				<div
					class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
					role="alert">
					${errorMessage}
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<h3 class="display-5">Benvenuto alla gestione dei tuoi tavoli da gioco!</h3>				
			</div>
		</div>

		 <div class="container text-center">
			<div class="row" Style="padding-top:132px">
				<div class="col-md-6">
					<h2>Crea Nuovo Tavolo</h2>
					<p>Descrizione Funzionalità</p>
					<p>
						<a class="btn btn-secondary" href="#" role="button" Style="background-color:green" >Crea &raquo;</a>
					</p>
				</div>
				<div class="col-md-6">
					<h2>Ricerca Tavoli Creati</h2>
					<p>Descrizione Funzionalità</p>
					<p>
						<a class="btn btn-secondary" href="${pageContext.request.contextPath}/PrepareSearchTavoliServlet?idUser=${user.id}" 
						role="button" Style="background-color:green">Ricerca &raquo;</a>
					</p>
				</div>
			</div>

			<hr>

		</div>
		<!-- /container -->
		<jsp:include page="../footer.jsp" />
	</main>

	
</body>
</html>