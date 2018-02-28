/**
 *
 */
package br.com.valhala.agenda.web.commands.contato;

import java.sql.Connection;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.valhala.agenda.db.FabricaConexoes;
import br.com.valhala.agenda.db.dao.ContatoDao;
import br.com.valhala.agenda.modelo.Contato;
import br.com.valhala.agenda.web.commands.Command;

/**
 * @author bruno
 */
public class ListaContatoCommand implements Command {

    /*
     * (non-Javadoc)
     * @see br.com.valhala.agenda.web.commands.Command#execute(javax.servlet.http.
     * HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void execute(HttpServletRequest requisicao, HttpServletResponse resposta) {

        try (Connection conexao = FabricaConexoes.getIntance().getConexao()) {
            ContatoDao contatoDao = new ContatoDao(conexao);
            Collection<Contato> contatos = contatoDao.lista();
            requisicao.setAttribute("contatos", contatos);
            requisicao.getRequestDispatcher("/WEB-INF/paginas/contato/lista.jsp").forward(requisicao, resposta);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}
