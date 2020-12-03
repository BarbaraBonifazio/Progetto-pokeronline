<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>

<jsp:include page="../header.jsp" />

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
<style type="text/css">
body {
	padding-top: 3.5rem;
}
</style>

<title>Gioca</title>
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
				<h2 class="display-5 text-center" > Benvenuto nella tua Area di Gioco!</h2>				
			</div>
		</div>

		 <div class="container text-center">
			<div class="row" Style="padding-top:132px">
				<div class="col-md-6">
					<h2>Compra Credito</h2>
					<p>Descrizione Funzionalit�</p>
					<p>
						<a class="btn btn-secondary" href="${pageContext.request.contextPath}/tavolo/PrepareInsertTavoloServlet?idUser=${user.id}" 
							role="button" Style="background-color:green; border-color:#327827" >Compra &raquo;</a>
					</p>
				</div>
				<c:if test="${user.isInGioco()}">
				<div class="col-md-6">
					<h2>Go To Last Game</h2>
					<p>Hai una partita in corso!</p>
					<p>
						<a class="btn btn-secondary" href="${pageContext.request.contextPath}/tavolo/PrepareSearchTavoliServlet?idUser=${user.id}" 
							role="button" Style="background-color:green; border-color:#327827">Gioca &raquo;</a>
					</p>
				</div>
				</c:if>
				<c:if test="${user.isInGioco() eq 'false'}">
				<div class="col-md-6">
					<h2>Ricerca Partite</h2>
					<p>Inizia a giocare!</p>
					<p>
						<a class="btn btn-secondary" href="${pageContext.request.contextPath}/tavolo/PrepareSearchTavoliServlet?idUser=${user.id}" 
							role="button" Style="background-color:green; border-color:#327827">Ricerca &raquo;</a>
					</p>
				</div>
				</c:if>
			</div>

			<hr>

		</div>
		<!-- /container -->
		
	</main>
</body>
<jsp:include page="../footer.jsp" />
</html>