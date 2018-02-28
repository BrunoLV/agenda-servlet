var moduloNovo = (function() {

    var telefones = [];
    var indiceTelefone = 0;

    function adiciona() {
	var tabelaTelefones = document.getElementById('tabelaTelefones');
	var telefoneAux = {};
	telefoneAux['ddd'] = document.getElementById('ddd').value;
	telefoneAux['numero'] = document.getElementById('telefone').value;
	telefoneAux['tipo'] = document.getElementById('tipo').value;
	telefones[indiceTelefone++] = telefoneAux;
	if (tabelaTelefones == null) {
	    var divTabela = document.getElementById('telefones');
	    divTabela.innerHTML = '<table id=\"tabelaTelefones\"><thead><tr><th>DDD<\/th><th>N\u00FAmero<\/th><th>Tipo<\/th><th colspan=\"2\">A\u00E7\u00F5es<\/th><\/tr><\/thead><\/table>';
	    tabelaTelefones = document.getElementById('tabelaTelefones');
	    tabelaTelefones.createTBody();
	}
	var corpoTabela = tabelaTelefones.tBodies[0];
	var linha = corpoTabela.insertRow(-1);
	var celulaDdd = linha.insertCell(0);
	var celulaNumero = linha.insertCell(1);
	var celulaTipo = linha.insertCell(2);
	var celulaEditar = linha.insertCell(3);
	var celulaExcluir = linha.insertCell(4);
	celulaDdd.innerHTML = telefone.ddd;
	celulaNumero.innerHTML = telefone.numero;
	celulaTipo.innerHTML = telefone.tipo;
	celulaEditar.innerHTML = '<button type="button" onclick="moduloNovo.editaTelefone(this)">Editar<\/button>';
	celulaExcluir.innerHTML = '<button type="button" onclick="moduloNovo.removeTelefone(this)">Remover<\/button>';
	document.getElementById('ddd').value = '';
	document.getElementById('telefone').value = '';
	document.getElementById('tipo').selectedIndex = 0;
    }

    function edita(gatilho) {
	var linha = gatilho.parentNode.parentNode;
	var telefone = telefones[linha.rowIndex - 1]
	console.log(telefone.id);
	document.getElementById('ddd').value = telefone.ddd;
	document.getElementById('telefone').value = telefone.numero;
	document.getElementById('tipo').value = telefone.tipo;
	telefones.splice(linha.rowIndex - 1, 1);
	linha.parentNode.removeChild(linha);
	indiceTelefone--;
    }

    function envia() {
	var tabelaTelefones = document.getElementById('tabelaTelefones');
	var contato = {};
	contato['nome'] = document.getElementById('nome').value;
	var formNovo = document.getElementById('formNovo');
	var inputJson = document.getElementById('inputJson');
	if (inputJson == null) {
	    inputJson = document.createElement('input');
	    inputJson.type = 'hidden';
	    inputJson.name = 'json';
	    inputJson.id = 'inputJson';
	    formNovo.appendChild(inputJson);
	}
	contato['telefones'] = telefones;
	inputJson.value = JSON.stringify(contato);
	console.log('Elemento sendo submetido: ' + inputJson.value);
	formNovo.submit();
    }

    function remove(gatilho) {
	var linha = gatilho.parentNode.parentNode;
	telefones.splice(linha.rowIndex - 1, 1);
	linha.parentNode.removeChild(linha);
	indiceTelefone--;
    }

    return {
	adicionaTelefone : function() {
	    adiciona();
	},
	editaTelefone : function(gatilho) {
	    edita(gatilho);
	},
	enviaContato : function() {
	    envia();
	},
	removeTelefone : function(gatilho) {
	    remove(gatilho);
	}
    }

})();