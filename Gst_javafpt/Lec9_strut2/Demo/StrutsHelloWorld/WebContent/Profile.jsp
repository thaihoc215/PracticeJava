<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	<s:div>Profile Information Form</s:div>
	<s:text name="Please fill in the form below:" />
	<s:form action="view" method="post" enctype="multipart/form-data">
		<s:textfield key="profile.fullname" name="profile.fullname" />
		<s:textfield key="profile.age" name="profile.age" />
		<s:textfield key="profile.address" name="profile.address" />
		<s:textfield key="profile.description" name="profile.description" />
		<s:submit key="submit" method="" />
	</s:form>

