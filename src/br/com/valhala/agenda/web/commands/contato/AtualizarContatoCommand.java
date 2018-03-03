/**
 *
 */
package br.com.valhala.agenda.web.commands.contato;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.valhala.agenda.db.FabricaConexoes;
import br.com.valhala.agenda.db.dao.ContatoDao;
import br.com.valhala.agenda.erro.AppException;
import br.com.valhala.agenda.modelo.Contato;
import br.com.valhala.agenda.web.commands.Command;

/**
 * @author bruno
 */
public class AtualizarContatoCommand implements Command {

    private static final String ATRIBUTO_CONTATO  = "contato";
    private static final String PARAMETRO_ID      = "id";
    private static final String URL_PAGINA_EDICAO = "/WEB-INF/paginas/contato/atualiza.jsp";

    /*
     * (non-Javadoc)
     * @see br.com.valhala.agenda.web.commands.Command#execute(javax.servlet.http.
     * HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void execute(HttpServletRequest requisicao, HttpServletResponse resposta) throws ServletException {
        try (Connection conexao = FabricaConexoes.getIntance().getConexao()) {
            Long id = Long.parseLong(requisicao.getParameter(PARAMETRO_ID));
            ContatoDao contatoDao = new ContatoDao(conexao);
            Contato contato = contatoDao.buscaPorId(id);
            requisicao.setAttribute(ATRIBUTO_CONTATO, contato);
            requisicao.getRequestDispatcher(URL_PAGINA_EDICAO).forward(requisicao, resposta);
        } catch (SQLException e) {
            throw new AppException(e.getMessage(), e);
        } catch (IOException e) {
            throw new AppException(e.getMessage(), e);
        }
    }

}
