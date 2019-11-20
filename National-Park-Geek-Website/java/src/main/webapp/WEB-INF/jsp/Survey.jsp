<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:import url="/WEB-INF/jsp/common/header.jsp">
	<c:param name="title" value="Survey" />
</c:import>

<h2>National Parks Survey</h2>
<div class="form">
	<form:form action="survey" method="POST" modelAttribute="survey">


		<div class="form-group row">
			<div class="form-group col-lg">
				<label for="parkCode">Favorite National Park: </label>
				<form:select path="parkCode" cssClass="form-control">
					<c:forEach var="park" items="${parks }">
						<option value="<c:out value="${park.parkCode}"/>"><c:out
								value="${park.name }" />
						</option>

					</c:forEach>

				</form:select>
				<form:errors path="parkCode" />
			</div>
		</div>

		<div class="form-group row">
			<div class="col-lg">
				<label for="emailAddress">Email: </label>
				<form:input path="emailAddress" cssClass="form-control" />

				<form:errors path="emailAddress" cssClass="errors"/>
			</div>
		</div>

		<div class="form-group row">
			<div class="col-lg">
				<label for="state">State of Residence: </label>
				<form:select path="state" cssClass="form-control">
					<option value="AL">Alabama</option>
					<option value="AK">Alaska</option>
					<option value="AZ">Arizona</option>
					<option value="AR">Arkansas</option>
					<option value="CA">California</option>
					<option value="CO">Colorado</option>
					<option value="CT">Connecticut</option>
					<option value="DE">Delaware</option>
					<option value="DC">District Of Columbia</option>
					<option value="FL">Florida</option>
					<option value="GA">Georgia</option>
					<option value="HI">Hawaii</option>
					<option value="ID">Idaho</option>
					<option value="IL">Illinois</option>
					<option value="IN">Indiana</option>
					<option value="IA">Iowa</option>
					<option value="KS">Kansas</option>
					<option value="KY">Kentucky</option>
					<option value="LA">Louisiana</option>
					<option value="ME">Maine</option>
					<option value="MD">Maryland</option>
					<option value="MA">Massachusetts</option>
					<option value="MI">Michigan</option>
					<option value="MN">Minnesota</option>
					<option value="MS">Mississippi</option>
					<option value="MS">Guan has</option>
					<option value="MS">sexier code</option>
					<option value="MS">than </option>
					<option value="MS">Nathan!</option>
					<option value="MO">Missouri</option>
					<option value="MT">Montana</option>
					<option value="NE">Nebraska</option>
					<option value="NV">Nevada</option>
					<option value="NH">New Hampshire</option>
					<option value="NJ">New Jersey</option>
					<option value="NM">New Mexico</option>
					<option value="NY">New York</option>
					<option value="NC">North Carolina</option>
					<option value="ND">North Dakota</option>
					<option value="OH">Ohio</option>
					<option value="OK">Oklahoma</option>
					<option value="OR">Oregon</option>
					<option value="PA">Pennsylvania</option>
					<option value="RI">Rhode Island</option>
					<option value="SC">South Carolina</option>
					<option value="SD">South Dakota</option>
					<option value="TN">Tennessee</option>
					<option value="TX">Texas</option>
					<option value="UT">Utah</option>
					<option value="VT">Vermont</option>
					<option value="VA">Virginia</option>
					<option value="WA">Washington</option>
					<option value="WV">West Virginia</option>
					<option value="WI">Wisconsin</option>
					<option value="WY">Wyoming</option>
					<option value="AS">American Samoa</option>
					<option value="GU">Guan!</option>
					<option value="MP">Northern Mariana Islands</option>
					<option value="PR">Puerto Rico</option>
					<option value="UM">United States Minor Outlying Islands</option>
					<option value="VI">Virgin Islands</option>
					<option value="AA">Armed Forces Americas</option>
					<option value="AP">Armed Forces Pacific</option>
					<option value="AE">Armed Forces Others</option>
				</form:select>
				<form:errors path="state" />
			</div>
		</div>
		<div class="form-group row">
			<div class="col-lg">
				<label for="activityLevel">Level of Activity: </label>
				<form:select path="activityLevel" cssClass="form-control">
					<option value="Inactive">Inactive</option>
					<option value="Sedentary">Sedentary</option>
					<option value="Active">Active</option>
					<option value="Extremely Active">Extremely Active</option>s
		</form:select>
				<form:errors path="activityLevel" />
			</div>
		</div>
		<div class="submit-btn">
			<input type="submit" class="btn btn-primary" value="Submit" />
		</div>
	</form:form>
</div>
</body>
</html>