


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
<script>
	$.datepicker.regional['es'] = {
		closeText : 'Cerrar',
		prevText : '<Ant',
 nextText: 'Sig>',
		currentText : 'Hoy',
		monthNames : [ 'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
				'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre',
				'Diciembre' ],
		monthNamesShort : [ 'Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul',
				'Ago', 'Sep', 'Oct', 'Nov', 'Dic' ],
		dayNames : [ 'Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves',
				'Viernes', 'Sábado' ],
		dayNamesShort : [ 'Dom', 'Lun', 'Mar', 'Mié', 'Juv', 'Vie', 'Sáb' ],
		dayNamesMin : [ 'Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sá' ],
		weekHeader : 'Sm',
		dateFormat : 'dd/mm/yy',
		firstDay : 1,
		isRTL : false,
		showMonthAfterYear : false,
		yearSuffix : ''
	};
	$.datepicker.setDefaults($.datepicker.regional['es']);
	$(function() {
		$("#datepicker").datepicker();
		$("#datepicka").datepicker();
	});
</script>


<div>

	<form:form action="${actionURL}" modelAttribute="survey">
		<fieldset class="crear">
			<legend>
				<spring:message code="survey.create" />
			</legend>
			<div class="texto">
				<form:hidden path="id" />
				<form:hidden path="version" />
				<form:hidden path="questions" />
				<form:hidden path="usernameCreator" />
				<div style="text-align: center">
					<table class="tablacrear">

						<tr>
							<td class="tdnombre"><spring:message code="survey.title" />:</td>
							<td style="text-align: center;"><form:input path="title" />
								<form:errors cssClass="error" path="title" /></td>
						</tr>
						<tr>
							<td class="tdnombre"><spring:message
									code="survey.description" />:</td>
							<td style="text-align: center;"><form:input
									path="description" /> <form:errors cssClass="error"
									path="description" /></td>
						</tr>
						<tr>
							<td class="tdnombre"><spring:message code="survey.startDate" />:</td>
							<td style="text-align: center;"><form:input path="startDate"
									id="datepicker" /> <form:errors cssClass="error"
									path="startDate" /></td>
						</tr>
						<tr>
							<td class="tdnombre"><spring:message code="survey.endDate" />:</td>
							<td style="text-align: center;"><form:input path="endDate"
									id="datepicka" /> <form:errors cssClass="error" path="endDate" /></td>
						</tr>
						<tr>
							<td class="tdnombre"><spring:message
									code="survey.postalCode" />:</td>
							<td style="text-align: center;"><form:input
									path="postalCode" /> <form:errors cssClass="error"
									path="title" /></td>
						</tr>
						<tr>
							<td class="tdnombre"><spring:message code="survey.tipo" />:</td>
							<td style="text-align: center;"><form:select path="tipo">
									<option value="Abierto" selected>
										<spring:message code="survey.tipo.abierto"></spring:message>
									</option>
									<option value="Cerrado">
										<spring:message code="survey.tipo.cerrado"></spring:message>
									</option>
								</form:select> <form:errors cssClass="error" path="tipo" /></td>
						</tr>
					</table>
				</div>


				<jstl:if test="${survey.id != 0 and survey.questions.size()>0}">
					<jstl:forEach var="question" items="${survey.questions}">
						<label><spring:message code="survey.question" /></label>
						<jstl:out value="${question.text}" />
						<br>
					</jstl:forEach>
				</jstl:if>
				<br /> <input type="submit" name="addQuestion"
					value="<spring:message code="survey.add.question" />" /> &nbsp; <input
					type="button" name="cancel"
					value="<spring:message code="survey.cancel" />"
					onclick="javascript: window.location.replace('survey/list.do');" />
				<br />
			</div>
		</fieldset>
	</form:form>


</div>
<br />
<br />
<br />
