package fr.afcepf.al26.qualite.data.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import fr.afcepf.al26.qualite.data.api.IDaoPersonne;
import fr.afcepf.al26.qualite.entity.Personne;
import fr.afcepf.al26.qualite.exception.SocialException;
import fr.afcepf.al26.qualite.util.SocialDataSource;
/**
 * Implementation des service d'accès aux données
 * de l'entitée {@link Personne} de l'application SocialQuality.
 * @author Stagiaire
 *
 */
public class DaoPersonneJDBC implements IDaoPersonne {
    /**
     * Log pour le debug.
     */
    private final Logger log = Logger.getLogger(getClass());
    /**
     * DataSource permettant la connection au serveur de données.
     */
    private DataSource ds = new SocialDataSource();
    /**
     * Requete pour ajouter une {@link Personne} dans la BDD.
     */
    private final String reqAjout =
            "INSERT INTO personne "
                    + "(nom, prenom, mail, mdp, date_naissance) "
                    + "VALUES (?, ?, ?, ?, ?)";
    /**
     * Indice du paramètre 'nom' dans la requete 'reqAjout'.
     */
    private final int indiceNomReqAjout = 1;
    /**
     * Indice du paramètre 'prenom' dans la requete 'reqAjout'.
     */
    private final int indicePrenomReqAjout = 2;
    /**
     * Indice du paramètre 'mail' dans la requete 'reqAjout'.
     */
    private final int indiceMailReqAjout = 3;
    /**
     * Indice du paramètre 'mdp' dans la requete 'reqAjout'.
     */
    private final int indiceMdpReqAjout = 4;
    /**
     * Indice du paramètre 'date_naissance' dans la requete 'reqAjout'.
     */
    private final int indiceNaissanceReqAjout = 5;
    /**
     * Indice de la clé dans le tableau de clés generées.
     */
    private final int indiceCleGeneree = 1;
    @Override
    public Personne ajouter(Personne paramPersonne) throws SocialException {
        Connection cnx = null;
        SocialException se = null;
        try {
            cnx = ds.getConnection();
            PreparedStatement pstmt =
                    cnx.prepareStatement(reqAjout,
                            Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(indiceMailReqAjout, paramPersonne.getMail());
            pstmt.setString(indiceNomReqAjout, paramPersonne.getNom());
            pstmt.setString(indicePrenomReqAjout,
                    paramPersonne.getPrenom());
            pstmt.setDate(indiceNaissanceReqAjout,
                new java.sql.Date(paramPersonne.getDateNaissance()
                        .getTime()));
            pstmt.setString(indiceMdpReqAjout, paramPersonne.getMdp());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            paramPersonne.setId(rs.getInt(indiceCleGeneree));
        } catch (Exception sqle) {
            log.debug("erreur lors de l'ajout");
            se = new SocialException("erreur lors de l'ajout",
                     SocialException.ErrorCode.PERSONNE_INCOMPLETE);
        } finally {
            try {
                cnx.close();
            } catch (Exception e) {
                log.debug("la connexion n'est pas ouverte");
                se = new SocialException("la connexion n'est pas ouverte",
                         SocialException.ErrorCode.CA_MARCHE_PAS);
            }
            if (se != null) {
                throw se;
            }
        }
        return paramPersonne;
    }

    @Override
    public boolean verifMail(String paramMail) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Personne seConnecter(String paramMail, String paramMdp)
            throws SocialException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Personne> rechercher(String paramNom) {
        // TODO Auto-generated method stub
        return null;
    }

}
