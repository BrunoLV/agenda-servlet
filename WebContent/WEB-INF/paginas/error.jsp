<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" type="image/x-icon" href="${pageContext.servletContext.contextPath}/resources/images/favicon.ico"/>
<title>Erro</title>
</head>
<body>
	<p>Ocorreu um erro na requisicao.</p>
	<p>Passe as informações abaixo para o administrador do sistema.</p>
	<table border="1">
		<thead>
			<tr>
				<th colspan="2">Informações sobre o erro.</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>Status code</th>
				<td><c:out value="${erro.statusCode}" /></td>
			</tr>
			<tr>
				<th>URI</th>
				<td><c:out value="${erro.uri}" /></td>
			</tr>
			<tr>
				<th>Mensagem</th>
				<td><c:out value="${erro.mensagem}" /></td>
			</tr>
		</tbody>
	</table>
	<br />
	<c:url var="home" value="/mvc">
		<c:param name="command" value="listarContatos" />
	</c:url>
	<a href="${home}">Volta para o Lista de Contatos</a>
</body>
</html>