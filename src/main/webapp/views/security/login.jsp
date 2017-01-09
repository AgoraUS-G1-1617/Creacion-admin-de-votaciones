
<%--
 * login.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="user/loginMake.do" id="form" class="form-horizontal"
	method="post" modelAttribute="userAccount">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<div class="input-group">
		<span class="input-group-addon"><i
			class="glyphicon glyphicon-user"></i></span>
		<form:input code="user.user" path="username" id="user"
			class="form-control" />
		<form:errors class="error" path="username" />
	</div>

	<div class="input-group">
		<span class="input-group-addon"><i
			class="glyphicon glyphicon-lock"></i></span>
		<form:password code="user.password" path="password" id="password"
			class="form-control" />
		<form:errors class="error" path="password" />
	</div>

	<div class="form-group">
		<!-- Button -->
		<div class="col-sm-12 controls">
			<button type="submit" name="save" class="btn btn-primary pull-right">
				<i class="glyphicon glyphicon-log-in"></i>
				<spring:message code="login.login" />
			</button>
		</div>
	</div>

	<jstl:if test="${message != null}">
		<div class="error">
			<spring:message code="login.error" />
		</div>
	</jstl:if>

</form:form>