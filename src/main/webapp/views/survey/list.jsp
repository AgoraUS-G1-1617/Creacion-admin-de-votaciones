

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<br>
	<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="surveys" requestURI="survey/list.do" id="row">
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
		
	
		<display:column titleKey="survey.delete">
			<jstl:if test="${hoy.before(row.startDate)}">
			<a href="survey/delete.do?surveyId=${row.id}" onclick="return confirm('<spring:message code="survey.confirm.delete" />')"> <spring:message
					code="survey.delete"/>
			</a>
			</jstl:if>
		</display:column>
	</display:table>

	<a href="survey/create.do"> <spring:message
			code="survey.create" />
	</a>
