<%--
 * header.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<div>

	<a href="/"><img src="images/vota.png"
		alt="Creación y Administración de votaciones" /></a>
	<div class="lenguajes">
		<a href="javascript:void(0)"
			onclick="javascript: link('?language=en')"><img
			src="images/en.png" alt="English" height="30" width="50" /></a> <a
			href="javascript:void(0)" onclick="javascript: link('?language=es')"><img
			src="images/es.png" alt="Español" height="30" width="50" /></a>
	</div>
</div>
<br />

<script>
	function link(link) {
		return window.location.href = (window.location.href.toString().split(
				'?')[0] + link).toString();
	}
</script>
<div>

	<ul id="jMenu">

		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<!-- <li>/a></li>-->



		<li class="arrow"></li>
		<security:authorize access="isAuthenticated()">

			<li><a class="fNiv" href="survey/list.do"><spring:message
						code="master.page.list" /></a></li>



			<li><a href="survey/create.do"><spring:message
						code="master.page.create">
					</spring:message></a></li>



			<li><a href="survey/dashboard.do"><spring:message
						code="master.page.dashboard">
					</spring:message></a></li>

		</security:authorize>
		<li><a href="https://beta.authb.agoraus1.egc.duckdns.org/"><spring:message
					code="master.page.censo">
				</spring:message></a></li>
		<security:authorize access="isAnonymous()">
			<li><a href="admin/login.do"><spring:message
						code="master.page.login">
					</spring:message></a></li>
		</security:authorize>
		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv"> <spring:message
						code="master.page.profile" /> (<security:authentication
						property="principal.username" />)
			</a>
				<ul>
					<li><a href="j_spring_security_logout"><spring:message
								code="master.page.logout" /> </a></li>
				</ul></li>
		</security:authorize>
	</ul>
</div>



