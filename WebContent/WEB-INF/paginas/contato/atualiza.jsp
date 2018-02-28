<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Atualiza Contato <c:out value="${contato.id}" /></title>
</head>
<body>
	<c:url var="urlSalvar" value="/mvc" />
	<form action="${urlSalvar}" method="post" id="formAtualiza" onsubmit="moduloAtualiza.enviaContato()">
		<input type="hidden" value="salvarContato" name="command" /> <input type="hidden" value="${contato.id}" name="id" id="idContato"> <label
			for="nome"
		> <c:out value="Nome: " /></label> <input type="text" value="${contato.nome}" name="nome" id="nome"> <br /> <br /> <input type="hidden"
			name="id" id="idTelefone"
		> <label for="ddd">DDD</label> <input type="text" name="ddd" id="ddd" /> <label for="telefone">Número</label> <input type="text" name="telefone"
			id="telefone"
		/> <select name="tipo" id="tipo">
			<option label="Celular" value="CELULAR">Celular</option>
			<option label="Residencial" value="RESIDENCIAL">Residencial</option>
			<option label="Comercial" value="COMERCIAL">Comercial</option>
		</select> <input type="button" onclick="moduloAtualiza.adicionaTelefone();" value="Adicionar"> <br /> <br />
		<div id="telefones">
			<c:if test="${not empty contato.telefones}">
				<table id="tabelaTelefones">
					<thead>
						<tr>
							<th>DDD</th>
							<th>Número</th>
							<th>Tipo</th>
							<th colspan="2">Ações</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${contato.telefones}" var="c">
							<tr>
								<input type="hidden" value="${c.id}" />
								<td><c:out value="${c.ddd}" /></td>
								<td><c:out value="${c.numero}" /></td>
								<td><c:out value="${c.tipo}" /></td>
								<td>
									<button type="button" onclick="moduloAtualiza.editaTelefone(this)">Editar</button>
								</td>
								<td>
									<button type="button" onclick="moduloAtualiza.removeTelefone(this)">Remover</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
		<br /> <br /> <input type="submit" value="Salvar" />
	</form>
	<script src="${pageContext.servletContext.contextPath}/assets/js/contato/atualiza.js" type="text/javascript"></script>
</body>
</html>