<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Struts 2 - Login Application | ViralPatel.net</title>
</head>
 
<body>
<h2>Struts 2 - Login Application with Annotations</h2>

<h1><s:text name="global.heading"/></h1>

   <s:url id="indexEN" namespace="/" action="locale" >
      <s:param name="request_locale" >en</s:param>
   </s:url>
   <s:url id="indexVN" namespace="/" action="locale" >
      <s:param name="request_locale" >vn</s:param>
   </s:url>
   <s:url id="indexFR" namespace="/" action="locale" >
      <s:param name="request_locale" >fr</s:param>
   </s:url>

   <s:a href="%{indexEN}" >English</s:a>
   <s:a href="%{indexVN}" >Tiếng Việt</s:a>
   <s:a href="%{indexFR}" >France</s:a>
    <s:a href="locale.aciton?request_locale=en" >English</s:a>

<s:actionerror />
<s:form action="login" method="post">
    <s:textfield name="username" key="label.username" size="20" />
    <s:password name="password" key="label.password" size="20" />
    
    <s:select name="lion.lionType" list="listLion" listKey="lionType" 
 listValue="lionName" headerKey="0" 
 headerValue="----" label="Select Lion Type" />
 
    <s:submit  key="label.login" align="center" />
</s:form>
</body>
</html>
