<%--
 * list.jsp
 *
 * Copyright (C) 2015 Universidad de Sevilla
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
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- findSurveysFromSevilla. -->
<p>	<span><spring:message code="survey.dashboard.1" />:</span> <br/>
<display:table pagesize="5" class="displaytag" keepStatus="true" name="findSurveysFromSevilla" requestURI="survey/dashboard.do" id="row">

		<display:column titleKey="survey.details">
			<a href="survey/details.do?surveyId=${row.id}"> <spring:message
				code="survey.details" />
			</a>
		</display:column>
		<display:column property="title"  titleKey="survey.title" />
		<display:column property="postalCode" titleKey="survey.postalCode" /> 
		<display:column>
			<a href="question/listQuestions.do?surveyId=${row.id}"> <spring:message
				code="survey.questions" />
		</a>
		</display:column>
		
</display:table>
</p>

<!-- findSurveysAlreadyStarted. -->
<p>	<span><spring:message code="survey.dashboard.2" />:</span> <br/>
<display:table pagesize="5" class="displaytag" keepStatus="true" name="findSurveysAlreadyStarted" requestURI="survey/dashboard.do" id="row">
		<display:column titleKey="survey.details">
			<a href="survey/details.do?surveyId=${row.id}"> <spring:message
				code="survey.details" />
			</a>
		</display:column>
		<display:column property="title"  titleKey="survey.title" />
		<display:column property="postalCode" titleKey="survey.postalCode" /> 
		<display:column>
			<a href="question/listQuestions.do?surveyId=${row.id}"> <spring:message
				code="survey.questions" />
		</a>
		</display:column>
</display:table>
</p>

<!-- ratioOfSurveysWhichHaveNotStartedYet -->
<p>
<spring:message code="survey.dashboard.3" />:&nbsp; <jstl:out value="${ratioOfSurveysWhichHaveNotStartedYet}"></jstl:out><br/>
</p>

<!-- averageOfQuestionsPerSurvey -->
<p>
<spring:message code="survey.dashboard.4" />:&nbsp; <jstl:out value="${averageOfQuestionsPerSurvey}"></jstl:out><br/>
</p>