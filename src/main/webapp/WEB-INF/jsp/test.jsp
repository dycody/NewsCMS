<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:import url="/resource/menu.xml" charEncoding="UTF-8" var="menu"/>	
<x:parse xml="${menu}" var="output" />
	<x:forEach select="$output/menu/nodes/node" var="item">

		<x:out select="$item/name" />
		<br />
		<x:out select="$item/url" />
		<br />
		<x:if select="$item/children">
			<x:forEach select="$item/children/node" var="citem">
				<x:out select="$citem/name" />
				<br />
				<x:out select="$citem/url" />
				<br />

			</x:forEach>

		</x:if>
	</x:forEach>
	<br /> 123123123123
</body>
</html>