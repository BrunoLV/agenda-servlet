package br.com.valhala.agenda.web.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.valhala.agenda.exceptions.WebAppException;

public interface Command {

    void execute(HttpServletRequest requisicao, HttpServletResponse resposta) throws WebAppException;

}
