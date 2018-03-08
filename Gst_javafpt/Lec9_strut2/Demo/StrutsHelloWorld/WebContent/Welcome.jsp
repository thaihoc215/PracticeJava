<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Welcome</title>
</head>

<body>
	<h2>
		Hello,
		<s:property value="username" />
		...!
	</h2>
	<p />
	<s:property value="key1" />
	<p />
	<s:property value="key2" />

	<s:form action="profile" method="post">
		<s:submit key="profile.make" align="center" />
	</s:form>


	<P />
	<b>if and else:</b>
	<s:if test="%{status ==false}">
		<div>Will Not Be Executed</div>
	</s:if>
	<s:elseif test="%{status =true}">
		<div>Will Be Executed</div>
	</s:elseif>
	<s:else>
		<div>Will Not Be Executed</div>
	</s:else>

	<P />
	<B>iterator:</B>
	<s:iterator value="myList1">
		<p>
			day is:
			<s:property />
		</p>
	</s:iterator>

	<p />
	<b> merge:</b>
	<s:merge var="myMergedIterator">
		<s:param value="%{myList1}" />
		<s:param value="%{myList2}" />
		<s:param value="%{myList3}" />
	</s:merge>
	<s:iterator value="%{#myMergedIterator}">
		<s:property />
	</s:iterator>
	<p />
	<b>generator :</b>
	<s:generator separator="-" val="%{'One,Two,Three,Four,Five'}">
		<s:iterator>
			<s:property />
			<br />
		</s:iterator>
	</s:generator>
	<p />
	<b>append:</b>
	<s:append var="myAppendIterator">
		<s:param value="%{myList1}" />
		<s:param value="%{myList2}" />
		<s:param value="%{myList3}" />
	</s:append>
	<s:iterator value="%{#myAppendIterator}">
		<s:property />
	</s:iterator>

	<p/><s:push value="person">
			Name : <s:property value="name"/><br/>
			Address : <s:property value="address"/>
	</s:push>
	
	<select>
	<s:iterator value ="listLion">
	  <option id ='<s:property value ="lionType"/>'>
	    <s:property value ="lionName"/>
	  </option>
	  </s:iterator>
	</select>

 <s:select name="type" list="listLion" listKey="lionType" 
 listValue="lionName" headerKey="0" 
 headerValue="----" label="Select Lion Type" />
</html>