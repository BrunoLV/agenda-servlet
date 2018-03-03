/**
 *
 */
package br.com.valhala.agenda.web.commands.contato;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.valhala.agenda.erro.WebAppException;
import br.com.valhala.agenda.modelo.Contato;
import br.com.valhala.agenda.web.commands.Command;

/**
 * @author bruno
 */
public class NovoContatoCommand implements Command {

    /*
     * (non-Javadoc)
     * @see br.com.valhala.agenda.web.commands.Command#execute(javax.servlet.http.
     * HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void execute(HttpServletRequest requisicao, HttpServletResponse resposta) throws ServletException {
        try {
            Contato contato = new Contato.Builder().build();
            requisicao.setAttribute("contato", contato);
            requisicao.getRequestDispatcher("/WEB-INF/paginas/contato/novo.jsp").forward(requisicao, resposta);
        } catch (IOException e) {
            throw new WebAppException(e.getMessage(), e);
        }
    }

}
