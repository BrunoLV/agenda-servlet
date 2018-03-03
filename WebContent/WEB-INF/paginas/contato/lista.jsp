<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Contatos</title>
</head>
<body>
	<c:url var="urlNovo" value="/mvc">
		<c:param name="command" value="cadastrarNovoContato" />
	</c:url>
	<a href="${urlNovo}"> <c:out value="Novo" />
	</a>
	<br />
	<br />
	<c:if test="${empty contatos}">
		<c:out value="Não há registros para visualização" />
	</c:if>
	<c:if test="${not empty contatos }">
		<table>
			<thead>
				<tr>
					<th><c:out value="ID" /></th>
					<th><c:out value="NOME"></c:out></th>
					<th colspan="2">Ações</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${contatos}" var="c">
					<tr>
						<td><c:out value="${c.id}" /></td>
						<td><c:out value="${c.nome}" /></td>
						<td><c:url var="urlEditar" value="/mvc">
								<c:param name="command" value="editarContato" />
								<c:param name="id" value="${c.id}" />
							</c:url> <a href="${urlEditar}"> <c:out value="Editar" />
						</a></td>
						<td><c:url var="urlExcluir" value="/mvc">
								<c:param name="command" value="excluirContato" />
								<c:param name="id" value="${c.id}" />
							</c:url> <a href="${urlExcluir}"> <c:out value="Excluir" />
						</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>