<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/jsp/common/header.jsp">
	<c:param name="title" value="Favorite Parks" />
</c:import>

<h2>Favorite Parks</h2>
<div class="container">
	<c:forEach var="surveyResult" items="${ surveyResults }">

		<div class="row">
			<div class="col-md">
				<img id="results-img"
					src="img/parks/${surveyResult.parkCode.toLowerCase() }.jpg" />
			</div>
			<div class="col-md">
				<div class="center">
					<c:set var="resultsTextClass" value="results-text-silver" />
					<c:if
						test="${surveyResults.get(0).numberOfVotes == surveyResult.numberOfVotes }">
						<c:set var="resultsTextClass" value="results-text-gold" />
					</c:if>
					<div class="${resultsTextClass }">
						<h3>
							<c:out value="${surveyResult.parkName }" />
							:<br>
							<c:out value="${surveyResult.numberOfVotes }" />
							votes
							<c:choose>
								<c:when
									test="${ surveyResults.get(0).numberOfVotes == surveyResult.numberOfVotes }">
							&#127942
							</c:when>
								<c:otherwise>
							&#127941
							</c:otherwise>
							</c:choose>
						</h3>
					</div>
				</div>
			</div>
		</div>


	</c:forEach>
</div>