<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>

<jsp:include page="header.jsp" />

<!-- Custom styles for this template -->
<link href="./assets/css/global.css" rel="stylesheet">
<style type="text/css">
body {
	padding-top: 3.5rem;
}
</style>

<title>Poker Online!</title>
</head>
<body>

	<jsp:include page="navbar.jsp"></jsp:include>


	<main role="main">

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
				<h1 class="display-3">Benvenuto a Poker Online!</h1>
				<p>Inizia a giocare! Che aspetti?</p>
	
			</div>
		</div>
		

		<div class="container">
			<div class="row" Style="padding-top:132px">
				<div class="col-md-4">
					<h2>Play Management</h2>
					<p> Descrizione Funzionalità </p>
					<p>
						<a class="btn btn-secondary" href="#" role="button" Style="background-color:green !important">View
							details &raquo;</a>
					</p>
				</div>
				<c:if test="${sessionScope.isSpecialPlayer eq 'true'}">
				<div class="col-md-4">
					<h2>Gestione Tavolo</h2>
					<p> Descrizione Funzionalità </p>
					<p>
						<a class="btn btn-secondary" href="${pageContext.request.contextPath}/PrepareGestioneTavolo" role="button"
						Style="background-color:green !important" >Gestisci &raquo;</a>
					</p>
				</div>
				</c:if>
				<c:if test="${sessionScope.isAdmin eq 'true'}">
					<div class="col-md-4">
						<h2>Gestione Amministrazione</h2>
						<p> Descrizione Funzionalità</p>
						<p>
							<a class="btn btn-secondary" href="#" role="button" Style="background-color:green !important">View
								details &raquo;</a>
						</p>
					</div>
				</c:if>
			</div>

			<hr>

		</div> 
		<!-- /container -->

	</main>

	<jsp:include page="footer.jsp" />
</body>
</html>