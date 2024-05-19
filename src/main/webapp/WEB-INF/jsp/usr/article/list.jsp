<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="ARTICLE LIST"></c:set>
<%@ include file="../common/head.jspf"%>


<table border="1">
	<thead>
		<tr>
			<th>번호</th>
			<th>날짜</th>
			<th>제목</th>
			<th>작성자</th>
		</tr>
	</thead>
	<tbody>

		<c:forEach var="article" items="${articles }">
			<tr>
				<td>${article.id }</td>
				<td>${article.regDate.substring(0,10) }</td>
				<td><a href="detail?id=${article.id }">${article.title }</a></td>
				<td>${article.memberId }</td>
			</tr>
		</c:forEach>
	</tbody>

</table>



<%@ include file="../common/foot.jspf"%>