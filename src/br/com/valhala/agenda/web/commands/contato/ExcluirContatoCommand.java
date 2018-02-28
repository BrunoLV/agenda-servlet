/**
 *
 */
package br.com.valhala.agenda.web.commands.contato;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.valhala.agenda.db.FabricaConexoes;
import br.com.valhala.agenda.db.dao.ContatoDao;
import br.com.valhala.agenda.web.commands.Command;

/**
 * @author bruno
 */
public class ExcluirContatoCommand implements Command {

    /*
     * (non-Javadoc)
     * @see br.com.valhala.agenda.web.commands.Command#execute(javax.servlet.http.
     * HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void execute(HttpServletRequest requisicao, HttpServletResponse resposta) {
        try (Connection conexao = FabricaConexoes.getIntance().getConexao()) {
            Long id = Long.parseLong(requisicao.getParameter("id"));
            try {
                ContatoDao contatoDao = new ContatoDao(conexao);
                contatoDao.excluir(id);
                conexao.commit();
                resposta.sendRedirect(requisicao.getContextPath() + "/mvc?command=listarContatos");
            } catch (Exception e) {
                conexao.rollback();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
