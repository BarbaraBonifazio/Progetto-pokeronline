<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="it.pokeronline.model.tavolo.Tavolo"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Cerca Partite</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/jqueryUI/jquery-ui.min.css" rel="stylesheet" type="text/css">

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
		</div>
		
		<div class="alert alert-danger ${not empty tavoloErrors?'':'d-none' }" role="alert">
		<c:forEach var = "errorItem" items="${tavoloErrors }">
        	<ul>
				<li> ${errorItem }</li>	
			</ul>
      	</c:forEach>
		</div>

		<div class='card'>
			<div class='card-header'>
				<h5>Cerca Partite</h5>
			</div>
			<div class='card-body'>
				
				<a Style="color:green !important" class="text-right"
					href="${pageContext.request.contextPath}/play/PreparePlayManagementServlet"
					class='btn btn-outline-secondary' style='width: 80px'> <i
					class='fa fa-chevron-left'></i> Indietro
				</a>
				<hr>
				<form method="get"
					action="${pageContext.request.contextPath}/play/ExecuteSearchPartiteServlet"
					novalidate>
							
					<div class="form-group col-md-6">
						<label>Denominazione</label> 
						<input type="text" name="denominazione" id="denominazione" class="form-control"
							placeholder="Inserire almeno i primi tre caratteri" value="${tavoloAttribute.denominazione }" required>
					</div>
					
					<div class="form-group col-md-6">
						<label>Data Creazione</label> 
						<input type="date" name="data" id="dataCreazione" class="form-control" 
							value="${tavoloAttribute.dataCreazione }"required>
					</div>

					<div class="form-group col-md-6">
						<label>Puntata Minima</label> 
						<input type="number" name="cifraMin" min="0" id="cifraMinima" class="form-control" 
							placeholder="Inserire Puntata Minima" value="${tavoloAttribute.cifraMin }"required
							min="0" max="99999" maxlength="5" 
							oninput="this.value=this.value.slice(0,this.maxLength||1/1);this.value=(this.value < 1) ? (0) : this.value;">
						<div class="invalid-feedback" id="errorCifraMin"> Il campo Puntata Minima non è valido!</div>
					</div>
					
					<div class="form-group col-md-6">
						<label class="control-label col-sm-5" for="creatoreInputId">Creatore</label> 
						<input type="text" name="creatoreInput" id="creatoreInputId" class="form-control" value="${creatoreInput}">
							<input type="hidden" name="creatore" id="creatoreId">
					</div>

					<div class="form-group col-md-6">
						<label class="control-label col-sm-5" for="partecipanteInputId">Partecipante</label> 
						<input type="text" name="partecipanteInput" id="partecipanteInputId" class="form-control" value="${partecipanteInput}">
							<input type="hidden" name="partecipante" id="partecipanteId">
					</div>

					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary" Style="background-color:green; border-color:#327827">Cerca</button>
					
					
<script	src="${pageContext.request.contextPath}/assets/js/jqueryUI/jquery-ui.min.js"></script>
					
			<%-- FUNZIONE JQUERY UI CON AJAX PER AUTOCOMPLETE CREATORI--%> 
			<script>
			
				$( "#creatoreInputId" ).autocomplete({ 
					 source: function(request, response) { 
					        $.ajax({ 
					            url: "SearchPartiteAjaxServlet?indice=x", 
					            datatype: "json", 
					            data: { 
					                term: request.term, 
					            },
					            success: function(data) {
					                response($.map(data, function(item) {
					                    return {
						                    label: item.label,
						                    value: item.value
					                    }
					                }))
					            }
						            
					        })
					    },
					//quando seleziono la voce nel campo deve valorizzarsi la descrizione
				    focus: function(event, ui) {
				        $("#creatoreInputId").val(ui.item.label)
				        return false
				    },
				    minLength: 2,
				    //quando seleziono la voce nel campo hidden deve valorizzarsi l'id
				    select: function( event, ui ) {
				    	$('#creatoreId').val(ui.item.value);
				    	console.log($('#creatoreId').val())
				        return false;
				    },
				});
				
				
				<%-- FUNZIONE JQUERY UI CON AJAX PER AUTOCOMPLETE PARTECIPANTI--%> 
				$( "#partecipanteInputId" ).autocomplete({ 
					 source: function(request, response) { 
					        $.ajax({ 
					            url: "SearchPartiteAjaxServlet?indice=y", 
					            datatype: "json", 
					            data: { 
					                term: request.term, 
					            },
					            success: function(data) {
					                response($.map(data, function(item) {
					                    return {
						                    label: item.label,
						                    value: item.value
					                    }
					                }))
					            }
						            
					        })
					    },
					//quando seleziono la voce nel campo deve valorizzarsi la descrizione
				    focus: function(event, ui) {
				        $("#partecipanteInputId").val(ui.item.label)
				        return false
				    },
				    minLength: 2,
				    //quando seleziono la voce nel campo hidden deve valorizzarsi l'id
				    select: function( event, ui ) {
				    	$('#partecipanteId').val(ui.item.value);
				    	console.log($('#partecipanteId').val())
				        return false;
				    },
				});
				
				
			</script>
					
				</form>
			
				<!-- end card-body -->
			</div>

		</div>
		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />
</body>
</html>
