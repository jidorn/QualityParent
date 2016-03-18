package fr.afcepf.al26.qualite.util;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.sql.DataSource;
/**
 * Classe representant l'implementation d'une {@link DataSource}
 * spécifique pour l'accès à notre BDD.
 * @author Stagiaire
 *
 */
public class SocialDataSource
        implements DataSource {
    /**
     * Identifiant de la connection au serveur de données.
     */
    private static String login;
    /**
     * Url (jndi) d'accès au serveur de données.
     */
    private static String url;
    /**
     * Mot de passe d'accès au serveur de données.
     */
    private static String mdp;
    /*
     * lecture du fichier de config de la BDD une
     * seule fois pour l'application.
     */
    static {
        ResourceBundle rb = ResourceBundle.getBundle(
            "fr.afcepf.al26.qualite.resources.socialDS");
        login = rb.getString("login");
        mdp = rb.getString("mdp");
        url = rb.getString("url");
        try {
            Class.forName(rb.getString("driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Connection getConnection() throws SQLException {
        Connection cnx = DriverManager.getConnection(url, login, mdp);
        return cnx;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter paramArg0) throws SQLException {
        // TODO Auto-generated method stub
    }

    @Override
    public void setLoginTimeout(int paramArg0) throws SQLException {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean isWrapperFor(Class<?> paramArg0) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> paramArg0) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Connection getConnection(String paramUsername, String paramPassword)
            throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

}
