<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Overzicht</title>
<meta name="description"
	content="Pushy is an off-canvas navigation menu for your website.">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">

<link rel="stylesheet" href="/css/normalize.css">
<link rel="stylesheet" href="/css/demo.css">
<!-- Pushy CSS -->
<link rel="stylesheet" href="/css/pushy.css">


<!-- jQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

</head>
<body>
	<!-- Pushy Menu -->
	<nav class="pushy pushy-left">
		<ul>
			<li class="pushy-submenu"><a href="#">Gezin</a>
				<ul>
				<li class="pushy-link"><a
						href="/ingelogd/overzicht.jsp">Overzicht</a></li>
					<li class="pushy-link"><a
						href="/ingelogd/gezinslidToevoegen.jsp">Gezinslid toevoegen</a></li>
					<li class="pushy-link"><a
						href="/ingelogd/gezinslidWijzigen.jsp">Gezinslid wijzigen</a></li>
					<li class="pushy-link"><a
						href="/ingelogd/gezinslidVerwijderen.jsp">Gezinslid verwijderen</a></li>
					<li class="pushy-link"><a
						href="/ingelogd/activiteitToevoegen.jsp">Activiteiten toevoegen</a></li>
					<li class="pushy-link"><a
						href="/ingelogd/activiteitWijzigen.jsp">Activiteiten wijzigen</a></li>
					<li class="pushy-link"><a
						href="/ingelogd/activiteitVerwijderen.jsp">Activiteiten verwijderen</a></li>
					<li class="pushy-link"><a
						href="/ingelogd/activiteitToevoegenGezinslid.jsp">Activiteiten toevoegen GezinsLid</a></li>						
				</ul></li>
			<li class="pushy-submenu"><a href="/ingelogd/logoutServlet.do">Uitloggen</a>
			</li>
		</ul>
	</nav>

	<!-- Site Overlay -->
	<div class="site-overlay"></div>

	<!-- Your Content -->
	<div id="container">
		<!-- Menu Button -->
		<div class="menu-btn">&#9776; Menu</div>
		<div id="messagebox" class="e_msgs">
			<%
				Object msgs = request.getAttribute("msgs");
				if (msgs != null) {
					out.println(msgs);
				}
			%>
		</div>
		<form id="voegActToe" action="/ingelogd/activiteitServlet.do"
			method="post">
			<input type="hidden" name="optie" value="koppelActToe">
			<table>
				<tr>
					<td><select id="bsn" name="bsn">
							<c:forEach var="Gezinslid" items="${loggedGezin.gezinsLeden}">
								<option value="${Gezinslid.BSN}">${Gezinslid.gezinslidNaam}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td><select id="aID" name="aID">
							<c:forEach var="Activiteit" items="${activiteiten}">
								<option value="${Activiteit.activiteitID}">${Activiteit.activiteitNaam}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td><input type="submit" value="Toevoegen"></td>
				</tr>
			</table>
		</form>

		<!-- Pushy JS -->
		<script src="/js/pushy.min.js"></script>
</body>
</html>