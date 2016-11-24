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

	<ul id="jMenu">

		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<!-- <li>/a></li>-->



				<li class="arrow"></li>
				<li><a class="fNiv" href="vote/list.do"><spring:message
							code="master.page.list" /></a></li>



				<li><a href="vote/create.do"><spring:message
							code="master.page.create">
						</spring:message></a></li>

				<li><a href="../ADMCensus"><spring:message
							code="master.page.censo">
						</spring:message></a></li>
</ul>
</div>
</br>
<a href="?language=en">En</a> | <a href="?language=es">Es</a>



