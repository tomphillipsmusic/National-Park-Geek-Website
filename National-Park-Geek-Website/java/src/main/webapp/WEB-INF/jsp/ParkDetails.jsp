<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/jsp/common/header.jsp">
	<c:param name="title" value="${park.name }" />
</c:import>

<div class="container">
	<div class="row">
		<div class="col-lg">
			<img id="backgroundpicture"
				src="img/parks/${park.parkCode.toLowerCase() }.jpg" />
		</div>
	</div>
	<div class="quotebox">
		<div class="quote">
			<p>
				"
				<c:out value="${park.quote }" />
				"
			</p>
		</div>
		<div class="quotesource">
			<p>
				-
				<c:out value="${park.quoteSource }" />
			</p>
		</div>
	</div>

	<h2>
		<c:out value="${park.name }" />
	</h2>
	<p>
		<c:out value="${park.description }" />
	</p>
	<table class="table table-striped">
		<tbody>
			<tr>
				<th scope="row">State:</th>
				<td><c:out value="${park.state }" /></td>
			</tr>
			<tr>
				<th scope="row">Acreage:</th>
				<td><fmt:formatNumber type="number" maxFractionDigits="2"
						value="${park.acreage}" /></td>
			</tr>
			<tr>
				<th scope="row">Elevation (in Feet):</th>
				<td><fmt:formatNumber type="number" maxFractionDigits="2"
						value="${park.elevationInFeet}" /></td>
			</tr>
			<tr>
				<th scope="row">Miles of trail:</th>
				<td><fmt:formatNumber type="number" maxFractionDigits="2"
						value="${park.milesOfTrail}" /></td>
			</tr>
			<tr>
				<th scope="row">Number of Campsites:</th>
				<td><c:out value="${park.numberOfCampsites }" /></td>
			</tr>
			<tr>
				<th scope="row">Climate:</th>
				<td><c:out value="${park.climate}" /></td>
			</tr>
			<tr>
				<th scope="row">Year Founded:</th>
				<td><c:out value="${park.yearFounded }" /></td>
			</tr>
			<tr>
				<th scope="row">Annual Visitor Count:</th>
				<td><fmt:formatNumber type="number" maxFractionDigits="2"
						value="${park.annualVisitorCount}" /></td>
			</tr>
			<tr>
				<th scope="row">Entry Fee:</th>
				<td><fmt:formatNumber value="${park.entryFee}" type="currency" /></td>
			</tr>
			<tr>
				<th scope="row">Number of Animal Species:</th>
				<td><c:out value="${park.numberOfAnimalSpecies }" /></td>
		</tbody>
	</table>






	<p>
	<h2>Five Day Forecast:</h2>

	<div class="today">
		<c:set var="weatherToday" value="${weather.get(0) }" />
		<div class="row">
			<img id="weatherTodayImg"
				src="img/weather/${weatherToday.imageName }" />
		</div>
		<div class="row">
			<div class="col-4"></div>
			<div class="col-4">
				<div class="weatherTodayInfo">
					<c:set var="degreeType" value="F" />
					<c:if test="${ !isFahrenheit }">
						<c:set var="degreeType" value="C" />
					</c:if>
					<H4>Day 1</H4>
					<span style="font-weight: bold; color: red;">High: </span>
					<div class="temperatureHot">
						<c:out value="${weatherToday.highTemperature }" />
						&#176
						<c:out value="${ degreeType }" />
					</div>
					<span style="font-weight: bold; color: blue;">Low: </span>
					<div class="temperatureCold">
						<c:out value="${weatherToday.lowTemperature }" />
						&#176
						<c:out value="${ degreeType }" />
					</div>
					<br>
					<c:if test="${ weatherToday.forecastReccomendation.length() > 0 }">
						<div id="reccomendation">
							<c:out value="${ weatherToday.forecastReccomendation }" />
						</div>
					</c:if>

				</div>
			</div>
			<div class="col-4"></div>
		</div>
	</div>

	<div class="row">
		<c:forEach var="i" begin="1" end="4">
			<div class="col-3 fourDayForecast">

				<c:set var="futureForecast" value="${ weather.get(i) }" />
				<img id="fourDayForecastPhoto"
					src="img/weather/${futureForecast.imageName }" />
				<h6>
					Day
					<c:out value="${i + 1}" />
				</h6>
				<span style="font-weight: bold; color: red;">High: </span>
				<div class="temperatureHot">
					<c:out value="${futureForecast.highTemperature }" />
					&#176
					<c:out value="${ degreeType }" />
				</div>
				<span style="font-weight: bold; color: blue;">Low: </span>
				<div class="temperatureCold">
					<c:out value="${futureForecast.lowTemperature }" />
					&#176
					<c:out value="${ degreeType }" />
				</div>

			</div>
		</c:forEach>
	</div>
	<div class="form">
		<form action="parkDetails" method="POST">

			<p>How would you like the temperature to display?</p>

			Fahrenheit: <input type="radio" name="isFahrenheit" value="true"
				checked><br> Celcius: <input type="radio"
				name="isFahrenheit" value="false"><br> <input
				type="hidden" name="parkCode" value="${park.parkCode }" />
			<button type="submit" value="Submit">Submit</button>
	</div>
</div>

</form>
</body>
</html>