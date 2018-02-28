package br.com.valhala.agenda.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class FabricaConexoes {

    private static final FabricaConexoes instance = new FabricaConexoes();

    public static FabricaConexoes getIntance() {
        return instance;
    }

    private DataSource dataSource;

    protected FabricaConexoes() {
        try {
            Context contextInicial = new InitialContext();
            Context contextoAmbiente = (Context) contextInicial.lookup("java:/comp/env");
            dataSource = (DataSource) contextoAmbiente.lookup("jdbc/agenda");
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Connection getConexao() {
        Connection conexao = null;
        try {
            conexao = dataSource.getConnection();
            conexao.setAutoCommit(false);
            return conexao;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conexao;
    }

}
