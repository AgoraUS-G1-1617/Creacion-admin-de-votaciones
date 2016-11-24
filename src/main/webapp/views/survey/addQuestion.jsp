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

 <br/>
  <br/>
  <br/>
 <div class ="centro">
<form:form action="${actionURL}" modelAttribute="questio">

<fieldset>
	<legend><spring:message code = "survey.add.question"/></legend>
	<div class="texto">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="surveyId" />
	
	<spring:message code="survey.title" />: <jstl:out
		value="${survey.title}"></jstl:out>
	<br>
	<spring:message code="survey.description" />: <jstl:out
		value="${survey.description}"></jstl:out>
	<br />
	<spring:message code="survey.startDate" />: <fmt:formatDate value="${survey.startDate}" pattern="dd/MM/yyyy"/>
	<br />
	<spring:message code="survey.endDate" />: <fmt:formatDate value="${survey.endDate}" pattern="dd/MM/yyyy"/>
	<br />
	<spring:message code="survey.tipo" />: <jstl:if
		test="${survey.tipo eq 'Abierto'}">
		<spring:message code="survey.tipo.abierto" />
	</jstl:if>
	<jstl:if test="${survey.tipo eq 'Cerrado'}"><spring:message code="survey.tipo.cerrado" /></jstl:if>
	<br />
	<jstl:if test="${survey.questions.size()>0}">
		<jstl:forEach var="question" items="${survey.questions}">
			<label><spring:message code="survey.question" /></label>:
			<jstl:out value="${question.text}" />
			<br />
		</jstl:forEach>
	</jstl:if>
	<br />
	
	<form:label path="text">
		<spring:message code="survey.question" />
	</form:label>
	<form:input path="text" />
	<form:errors cssClass="error" path="text" />
	
	<br>
	<input type="submit" name="addQuestion"
		value="<spring:message code="survey.add.question" />" />
	<br >
	<input type="submit" name="save"
		value="<spring:message code="survey.save" />" />&nbsp; 
	<input type="button" onclick="location.href='vote/cancelSurvey.do?surveyId=${survey.id}'" value="<spring:message code="survey.cancel"/>" />
			</div>
</fieldset>
</form:form>
</div>
 <br/>
  <br/>
  <br/>
  <br/>