



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

<div class="centro">

	<fieldset class="crear">
		<legend>
			<spring:message code="survey.details" />
		</legend>
		<div class="texto">
			<table class="tablacrear">

				<tr>
					<td style="width: 50%;"><spring:message code="survey.title" />
						:</td>
					<td style="text-align: center;"><jstl:out
							value="${survey.title}"></jstl:out></td>
				</tr>
				<tr>
					<td style="width: 50%;"><spring:message
							code="survey.description" /> :</td>
					<td style="text-align: center;"><jstl:out
							value="${survey.description}"></jstl:out></td>
				</tr>
				<tr>
					<td style="width: 50%;"><spring:message
							code="survey.startDate" /> :</td>
					<td style="text-align: center;"><fmt:formatDate
							value="${survey.startDate}" pattern="dd/MM/yyyy" /></td>
				</tr>
				<tr>
					<td style="width: 50%;"><spring:message code="survey.endDate" />
						:</td>
					<td style="text-align: center;"><fmt:formatDate
							value="${survey.endDate}" pattern="dd/MM/yyyy" /></td>
				</tr>
				<tr>
					<td style="width: 50%;"><spring:message code="survey.tipo" />
						:</td>
					<td style="text-align: center;"><jstl:if
							test="${survey.tipo eq 'Abierto'}">
							<spring:message code="survey.tipo.abierto" />
						</jstl:if> <jstl:if test="${survey.tipo eq 'Cerrado'}">
							<spring:message code="survey.tipo.cerrado" />
						</jstl:if></td>
				</tr>

				<jstl:if test="${survey.questions.size()>0}">

					<jstl:forEach var="question" items="${survey.questions}">
						<tr>
							<td style="width: 50%;"><spring:message
									code="survey.question" /></td>
							<td style="text-align: center;"><jstl:out
									value="${question.text}" /></td>
						</tr>
					</jstl:forEach>

				</jstl:if>
			</table>

			<input type="button" name="addQuestion"
				value="<spring:message code="survey.add.question" />"
				onclick="window.location='survey/addQuestion.do?surveyId=${param.surveyId}';" />
			<input type="button" name="cancel"
				value="<spring:message code="survey.back" />"
				onclick="javascript: window.location.replace('survey/list.do');" />
			<br />
		</div>
	</fieldset>

</div>
<br />
<br />
<br />
<br />