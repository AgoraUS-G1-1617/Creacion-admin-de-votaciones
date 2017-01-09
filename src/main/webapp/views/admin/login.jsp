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

<form:form action="admin/login.do" modelAttribute="userAccount">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="authorities" />
	<form:label path="userAccount.username">
		<spring:message code="user.username" />:
	</form:label>
	<form:input path="userAccount.username" />
	<form:errors cssClass="error" path="userAccount.username" />
	<br />
	<form:label path="userAccount.password">
		<spring:message code="cook.password" />:
	</form:label>
	<form:password path="userAccount.password" />
	<form:errors cssClass="error" path="userAccount.password" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="admin.login" />" />&nbsp; 
	<input type="button" name="cancel"
		value="<spring:message code="admin.cancel" />"
		onclick="window.history.back()" />
	<br />
</form:form>