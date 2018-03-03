/**
 *
 */
package br.com.valhala.agenda.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author bruno
 */
public class UriAccessFilter implements Filter {

    /**
     *
     */
    public UriAccessFilter() {
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     * javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("UTF-8");
        }

        HttpServletRequest req = (HttpServletRequest) request;

        String url = req.getRequestURL().toString();
        String queryString = req.getQueryString();

        boolean isRecursoEstatico = url.contains("/resources/");

        if (!isRecursoEstatico) {
            System.out.println("Acessando Uri: " + url + (queryString != null ? ("/" + queryString) : "")
                    + " realizada dentro da Sessao " + req.getSession(true).getId());
        }

        chain.doFilter(request, response);
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

}
