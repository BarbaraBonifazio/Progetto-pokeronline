<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="it.pokeronline.model.tavolo.Tavolo"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Cerca Utenti</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

		<div class="alert alert-danger alert-dismissible fade show d-none"
			role="alert">
			Operazione fallita!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div
			class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
			role="alert">
			${errorMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div class='card'>
			<div class='card-header'>
				<h5>Cerca Utenti</h5>
			</div>
			<div class='card-body'>
				
				<a Style="color:green !important" class="text-right"
					href="${pageContext.request.contextPath}/home.jsp"
					class='btn btn-outline-secondary' style='width: 80px'> <i
					class='fa fa-chevron-left'></i> Indietro
				</a>
				<hr>
				<form method="get"
					action="${pageContext.request.contextPath}/ExecuteFindUsersServlet"
					novalidate>

					<div class="form-group col-md-6">
						<label>Nome</label> <input type="text" name="nome" id="nomeUser"
							class="form-control"
							placeholder="Inserire almeno i primi tre caratteri">

					</div>

					<div class="form-group col-md-6">
						<label>Cognome</label> <input type="text" name="cognome"
							id="cognomeUser" class="form-control"
							placeholder="Inserire almeno i primi tre caratteri">

					</div>

					<div class="form-group col-md-6">
						<label>Username</label> <input type="text" name="username"
							id="unameUser" class="form-control"
							placeholder="Inserire username">

					</div>

					<div class="form-group col-md-6">
						<label>Stato</label> <select id="statoUser" name="stato"
							class="form-control">
							<option value="${stato.EMPTY}">- Seleziona Stato -</option>
							<c:forEach items="${listaStati}" var="stato">
								<c:if test="${stato != 'EMPTY'}">
									<option value="${stato}">${stato}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>

					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary" Style="background-color:green; border-color:#327827">Cerca</button>

				</form>
			
				<!-- end card-body -->
			</div>

		</div>
		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />
</body>
</html>
