/**
 *
 */
package br.com.valhala.agenda.web.commands.contato;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.valhala.agenda.db.FabricaConexoes;
import br.com.valhala.agenda.db.dao.ContatoDao;
import br.com.valhala.agenda.exceptions.WebAppException;
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
    public void execute(HttpServletRequest requisicao, HttpServletResponse resposta) throws WebAppException {

        try (Connection conexao = FabricaConexoes.getIntance().getConexao()) {
            ContatoDao contatoDao = new ContatoDao(conexao);
            Collection<Contato> contatos = contatoDao.lista();
            requisicao.setAttribute("contatos", contatos);
            requisicao.getRequestDispatcher("/WEB-INF/paginas/contato/lista.jsp").forward(requisicao, resposta);
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro na integracao com banco de dados. Erro: " + e.getMessage());
            throw new WebAppException(e);
        } catch (ServletException e) {
            System.out.println("Ocorreu um erro na execucao da aplicacao web. Erro: " + e.getMessage());
            throw new WebAppException(e);
        } catch (IOException e) {
            System.out.println("Ocorreu um erro na execucao da aplicacao web. Erro: " + e.getMessage());
            throw new WebAppException(e);
        }

    }

}
