<%--

 * edit.jsp

 *

 * Copyright (C) 2014 Universidad de Sevilla

 * 

 * The use of this project is hereby constrained to the conditions of the 

 * TDG Licence, a copy of which you may download from 

 * http://www.tdg-seville.info/License.html

 --%>



<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<h1>Su votación acaba de ser generada. Creando preguntas...</h1>

<div class="centro">
	<form:form action="${actionURL}" modelAttribute="questio">

		<fieldset class="crear">
			<legend>
				<spring:message code="survey.add.question" />
			</legend>
			<div class="texto">
				<table class="tablacrear">

					<form:hidden path="id" />
					<form:hidden path="version" />
					<form:hidden path="surveyId" />
					<tr>
						<td class="tdnombre"><spring:message code="survey.title" /> :</td>
						<td style="text-align: center;"><jstl:out value="${survey.title}"></jstl:out></td>
					</tr>
					<tr>
						<td class="tdnombre"><spring:message code="survey.description" /> :</td>
						<td style="text-align: center;"><jstl:out value="${survey.description}"></jstl:out></td>
					</tr>
					<tr>
						<td class="tdnombre"><spring:message code="survey.startDate"/> :</td>
						<td style="text-align: center;"><fmt:formatDate value="${survey.startDate}"
								pattern="dd/MM/yyyy" /></td>
					</tr>
					<tr>
						<td class="tdnombre"><spring:message code="survey.endDate" /> :</td>
						<td style="text-align: center;"><fmt:formatDate value="${survey.endDate}"
								pattern="dd/MM/yyyy" /></td>
					</tr>
					<tr>
						<td class="tdnombre"><spring:message code="survey.tipo" /> :</td>
						<td style="text-align: center;"><jstl:if test="${survey.tipo eq 'Abierto'}">
								<spring:message code="survey.tipo.abierto" />
							</jstl:if> <jstl:if test="${survey.tipo eq 'Cerrado'}">
								<spring:message code="survey.tipo.cerrado" />
							</jstl:if></td>
					</tr>
					<tr>
						<td class="tdnombre"><spring:message code="survey.postalCode" /> :</td>
						<td style="text-align: center;"><jstl:out value="${survey.postalCode}"></jstl:out></td>
					</tr>
					<jstl:if test="${survey.questions.size()>0}">
						<jstl:forEach var="question" items="${survey.questions}">
							<tr>
								<td class="tdnombre"><spring:message code="survey.question" /></td>
								<td style="text-align: center;"><jstl:out value="${question.text}" /></td>
							</tr>
						</jstl:forEach>
					</jstl:if>

					<tr>
						<td class="tdnombre"><spring:message code="survey.question" /></td>
						<td><form:input path="text" /> <form:errors cssClass="error"
								path="text" /></td>
					</tr>

				</table>
				
				<input type="submit" name="save"
					value="<spring:message code="survey.add.question" />" /> <input
					type="button"
					onclick="javascript: window.location.replace('survey/details.do?surveyId=${param.surveyId}');"
					value="<spring:message code="survey.cancel"/>" />
			</div>
		</fieldset>
	</form:form>
</div>
<br />
<br />
<br />
<br />