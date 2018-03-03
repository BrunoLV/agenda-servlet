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

    /*
     * (non-Javadoc)
     * @see br.com.valhala.agenda.web.commands.Command#execute(javax.servlet.http.
     * HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void execute(HttpServletRequest requisicao, HttpServletResponse resposta) throws ServletException {
        try (Connection conexao = FabricaConexoes.getIntance().getConexao()) {
            try {
                System.out.println(requisicao.getParameter("json"));
                ContatoDao contatoDao = new ContatoDao(conexao);
                Gson gson = new GsonBuilder().registerTypeAdapter(Long.class, new NumberTypeAdapter()).create();
                Contato contato = gson.fromJson(requisicao.getParameter("json"), Contato.class);
                if (contato.getId() == null) {
                    Long idContatoGerado = contatoDao.insere(contato);
                    System.out.println("Contato id " + idContatoGerado + " gravado no banco de dados.");
                } else {
                    contatoDao.atualiza(contato);
                    System.out.println("Contato id " + contato.getId() + " atualizado no banco dados.");
                }
                conexao.commit();
                resposta.sendRedirect(requisicao.getContextPath() + "/mvc?command=listarContatos");
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
