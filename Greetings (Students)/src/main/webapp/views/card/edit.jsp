<%--
 * edit.jsp
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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="card/customer/edit.do" modelAttribute="card">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="customer" />
	<div>
		<p><spring:message code="card.title" /></p>
		<form:input path="title" />
		<form:errors cssClass="error" path="title"/>
	</div>
	<div>
		<p><spring:message code="card.text" /></p>
		<form:textarea path="text" />
		<form:errors cssClass="error" path="text"/>
	</div>
	<div>
		<p><spring:message code="card.background" /></p>
		<form:select id="backgrounds" path="background">
			<form:option value="0" label="----" />
			<form:options items="${backgrounds}" itemValue="id" itemLabel="title" />
		</form:select>
	</div>
	<div>
		<input type="submit" name="save"
			value="<spring:message code="card.save" />" />;
		<jstl:if test="${card.id != 0}">
			<input type="submit" name="delete"
				value="<spring:message code="card.delete" />"
				onclick="return confirm('<spring:message code="card.confirm.delete" />')" />&nbsp;
	</jstl:if>
		<input type="button" name="cancel"
			value="<spring:message code="card.cancel" />"
			onclick="relativeRedir('card/customer/list.do')" /> <br />
	</div>



</form:form>