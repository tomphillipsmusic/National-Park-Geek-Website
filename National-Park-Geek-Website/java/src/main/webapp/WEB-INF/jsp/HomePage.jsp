<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/jsp/common/header.jsp">
	<c:param name="title" value="Home" />
</c:import>

<section>
	<p style="text-align: center">
		<iframe allow="encrypted-media" allowtransparency="true"
			frameborder="0" height="200"
			src="https://open.spotify.com/embed/user/b22p0b151hkrofplj7c2zi9uo/playlist/0d6dyOaAfT7x4QkdaAJiLA"
			width="700"></iframe>
	</p>
	<div class="container">
		<c:forEach var="park" items="${parks}">

			<div class="row">
				<div class="col-md">
					<c:url var="parkDetailsUrl" value="parkDetails">
						<c:param name="parkCode" value="${ park.parkCode }" />
					</c:url>
					<a href="${ parkDetailsUrl }"> <img class="homepage-img"
						src="img/parks/${park.parkCode.toLowerCase() }.jpg" />
					</a>
				</div>
				<div class="col-md">
					<h2>

						<a href="${ parkDetailsUrl }"><c:out value="${park.name }" /></a>
					</h2>
					<p>
						<c:out value="${park.description }" />
					</p>
				</div>
			</div>

		</c:forEach>
	</div>
</section>
</body>
</html>