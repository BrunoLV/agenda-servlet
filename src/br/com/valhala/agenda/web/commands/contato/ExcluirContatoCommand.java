/**
 *
 */
package br.com.valhala.agenda.web.commands.contato;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.valhala.agenda.db.FabricaConexoes;
import br.com.valhala.agenda.db.dao.ContatoDao;
import br.com.valhala.agenda.erro.WebAppException;
import br.com.valhala.agenda.web.commands.Command;

/**
 * @author bruno
 */
public class ExcluirContatoCommand implements Command {

    private static final String URL_ACAO_LISTAGEM = "/mvc?command=listarContatos";

    /*
     * (non-Javadoc)
     * @see br.com.valhala.agenda.web.commands.Command#execute(javax.servlet.http.
     * HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void execute(HttpServletRequest requisicao, HttpServletResponse resposta) throws ServletException {
        try (Connection conexao = FabricaConexoes.getIntance().getConexao()) {
            Long id = Long.parseLong(requisicao.getParameter("id"));
            try {
                ContatoDao contatoDao = new ContatoDao(conexao);
                contatoDao.excluir(id);
                conexao.commit();
                resposta.sendRedirect(requisicao.getContextPath() + URL_ACAO_LISTAGEM);
            } catch (Exception e) {
                conexao.rollback();
            }
        } catch (SQLException e) {
            throw new WebAppException(e.getMessage(), e);
        }
    }

}
