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
<body background="/css/IMG_6142.jpg">
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
					<li class="pushy-link"><a
						href="/ingelogd/activiteitWijzigGezinslid.jsp">Activiteiten wijzigen GezinsLid</a></li>
					<li class="pushy-link"><a
						href="/ingelogd/activiteitVerwijderGezinslid.jsp">Activiteiten verwijderen GezinsLid</a></li>
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
		<table>
			<c:choose>
				<c:when test="${loggedGezinslid.activiteiten.size() < 1}">
					<tr>
						<th>Geen activiteiten gevonden!</th>
					</tr>

				</c:when>
				<c:otherwise>
					<tr>
						<th>ActiviteitID</th>
						<th>ActiviteitNaam</th>
						<th>Omschrijving</th>
						<th>Status</th>
						<th>Gezinslid</th>
					</tr>
				</c:otherwise>
			</c:choose>
			<c:forEach var="Activiteit" items="${loggedGezinslid.activiteiten}">
				<tr>
					<td>${Activiteit.activiteitID}</td>
					<td>${Activiteit.activiteitNaam}</td>
					<td>${Activiteit.omschrijving}</td>
					<td>${Activiteit.status}</td>
					<td>${loggedGezinslid.gezinslidNaam}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<!-- Pushy JS -->
	<script src="/js/pushy.min.js"></script>

</body>
</html>