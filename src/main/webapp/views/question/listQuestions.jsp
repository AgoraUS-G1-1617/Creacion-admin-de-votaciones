<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
		

<display:table pagesize="5" class="displaytag" keepStatus="true" name="questions" requestURI="question/listQuestions.do" id="row">

		<display:column property="text"  titleKey="survey.question" />
		
		<display:column>
			<a href="question/delete.do?questionId=${row.id}"><spring:message
					code="survey.delete"/></a>
		</display:column>	
			
		
	</display:table>
	
	<jstl:if test="${dodelete}">
   			<script type="text/javascript">
   		  		alert("Acci�n realizada con �xito / Action done successfully");
   			</script>
		</jstl:if>	