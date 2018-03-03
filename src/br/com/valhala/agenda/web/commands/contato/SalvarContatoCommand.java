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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.valhala.agenda.db.FabricaConexoes;
import br.com.valhala.agenda.db.dao.ContatoDao;
import br.com.valhala.agenda.erro.WebAppException;
import br.com.valhala.agenda.json.adapters.NumberTypeAdapter;
import br.com.valhala.agenda.modelo.Contato;
import br.com.valhala.agenda.web.commands.Command;

/**
 * @author bruno
 */
public class SalvarContatoCommand implements Command {

    private static final String PARAMETRO_JSON    = "json";
    private static final String URL_ACAO_LISTAGEM = "/mvc?command=listarContatos";

    /*
     * (non-Javadoc)
     * @see br.com.valhala.agenda.web.commands.Command#execute(javax.servlet.http.
     * HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void execute(HttpServletRequest requisicao, HttpServletResponse resposta) throws ServletException {
        try (Connection conexao = FabricaConexoes.getIntance().getConexao()) {
            try {
                ContatoDao contatoDao = new ContatoDao(conexao);
                Gson gson = new GsonBuilder().registerTypeAdapter(Long.class, new NumberTypeAdapter()).create();
                Contato contato = gson.fromJson(requisicao.getParameter(PARAMETRO_JSON), Contato.class);
                if (contato.getId() == null) {
                    Long idContatoGerado = contatoDao.insere(contato);
                    System.out.println("Contato id " + idContatoGerado + " gravado no banco de dados.");
                } else {
                    contatoDao.atualiza(contato);
                    System.out.println("Contato id " + contato.getId() + " atualizado no banco dados.");
                }
                conexao.commit();
                resposta.sendRedirect(requisicao.getContextPath() + URL_ACAO_LISTAGEM);
            } catch (SQLException e) {
                throw new WebAppException(e.getMessage(), e);
            } catch (IOException e) {
                throw new WebAppException(e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new WebAppException(e.getMessage(), e);
        }
    }

}
