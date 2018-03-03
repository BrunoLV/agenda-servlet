<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Novo Contato</title>
</head>
<body>
	<c:url var="urlSalvar" value="/mvc" />
	<form id="formNovo" action="${urlSalvar}" method="post" onsubmit="moduloNovo.enviaContato();">
		<input type="hidden" value="salvarContato" name="command" /> <label for="nome"> <c:out value="Nome: " /></label> <input type="text"
			value="${contato.nome}" name="nome" id="nome"
		> <br /> <br /> <label for="ddd"><c:out value="DDD" /></label> <input type="text" name="ddd" id="ddd" /> <label for="telefone"><c:out
				value="Número"
			/></label> <input type="text" name="telefone" id="telefone" /> <select name="tipo" id="tipo">
			<option label="Celular" value="CELULAR"><c:out value="Celular" /></option>
			<option label="Residencial" value="RESIDENCIAL"><c:out value="Residencial" /></option>
			<option label="Comercial" value="COMERCIAL"><c:out value="Comercial" /></option>
		</select> <input type="button" onclick="moduloNovo.adicionaTelefone();" value="Adicionar"> <br /> <br />
		<div id="telefones"></div>
		<br /> <input type="submit" value="Salvar" />
	</form>
	<script src="${pageContext.servletContext.contextPath}/resources/js/contato/novo.js" type="text/javascript"></script>
</body>
</html>