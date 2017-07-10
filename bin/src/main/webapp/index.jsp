<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Inloggen</title>
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
			<li class="pushy-submenu"><a href="/index.jsp">Inloggen</a></li>
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
		<form action="/loginServlet.do" method="post">
			<table>
				<tr>
					<td>BSN:</td>
					<td><input type="text" name="bsn"></td>
				</tr>
				<tr>
					<td>Wachtwoord:</td>
					<td><input type="password" name="wwoord"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Inloggen"></td>
				</tr>
			</table>
		</form>
	</div>

	<!-- Pushy JS -->
	<script src="/js/pushy.min.js"></script>
</body>
</html>
