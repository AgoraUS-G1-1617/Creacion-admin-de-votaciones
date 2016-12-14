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
	<img src="images/vota.png" alt="Creación y Administración de votaciones" />
	<div class="lenguajes">
		<a href="?language=en"><img src="images/en.png" alt="English"
			height="30" width="50" /></a> <a href="?language=es"><img
			src="images/es.png" alt="Español" height="30" width="50" /></a>
	</div>
</div>
<br/>
<div>

	<ul id="jMenu">

		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<!-- <li>/a></li>-->



		<li class="arrow"></li>
		<li><a class="fNiv" href="survey/list.do"><spring:message
					code="master.page.list" /></a></li>



		<li><a href="survey/create.do"><spring:message
					code="master.page.create">
				</spring:message></a></li>

		<li><a href=""><spring:message
					code="master.page.censo">
				</spring:message></a></li>
	</ul>
</div>



