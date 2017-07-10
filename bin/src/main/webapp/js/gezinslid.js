$(document).ready(
		function() {
			$("#opVragen").click(function() {
				var $bsn = $(this).closest("tr").find(".bsn").val();
				if(!!$bsn){
			$("#gezinslidActiviteiten").html("");
			$.get("/gezinslidjQ/" + $bsn, function(data) {
				$("#gezinslidActiviteiten").append(
						"<tr>" +
						"<th>activiteitID</th>" +
						"<th>activiteitNaam</th>" +
						"<th>omschrijving</th>")
				$.each(data, function(i, Activiteit) {
					if(typeof Activiteit.activiteitID != 'undefined'){
					$("#gezinslidActiviteiten").append(
							"<tr><td>" + Activiteit.activiteitID + "</td><td>"
									+ Activiteit.activiteitNaam + "</td><td>"
									+ Activiteit.omschrijving + "</td>")
					} else {
						$("#gezinslidActiviteiten").html("");
						$("#gezinslidActiviteiten").append(
								"<tr><td>Geen activiteiten gevonden!</td><td>")
					}
				});
			});
				} else {
					alert("Vul een geldige BSN in!");
				}
			});
		})