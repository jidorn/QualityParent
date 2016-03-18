package fr.afcepf.al26.test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.afcepf.al26.qualite.data.api.IDaoPersonne;
import fr.afcepf.al26.qualite.data.impl.DaoPersonneJDBC;
import fr.afcepf.al26.qualite.entity.Personne;
import fr.afcepf.al26.qualite.exception.SocialException;

/**
 * Classe pour tester la Connection au niveau du DAO.
 * @author Stagiaire
 *
 */
public class TestSeConnecter {
    /**
     * Classe de service a tester.
     */
    // Mock
    private IDaoPersonne dao = new DaoPersonneJDBC() {
        @Override
        public Personne seConnecter(
                String paramMail, String paramMdp) throws SocialException {
            if (paramMail.equals("userInvalide@afcepf.fr")) {
                throw new SocialException("existe pas");
            } else {
                if (paramMail.equals("user@afcepf.fr")
                    && paramMdp.equals("user")) {
                    return retourConnectionNominale;
                }
            }
            return null;
        };
    };
    /**
     * Mail valide dans la BDD de test.
     */
    private String mailValide = "user@afcepf.fr";
    /**
     * Mot de passe valide dans la BDD de test.
     */
    private String mdpValide = "user";
    /**
     * Mail non existant dans la BDD de test.
     */
    private String mailInvalide = "userInvalide@afcepf.fr";
    /**
     * Retour attendu pour le test de connection nominal.
     */
    private Personne retourConnectionNominale;
    /**
     * DateFormat pour les comparaisons de dates et affectations.
     */
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    /**
     * Id de la personne nominale.
     */
    private final int idPersNominale = 5;
    /**
     * Default Constructor.
     */
    public TestSeConnecter() {
        try {
            retourConnectionNominale =
                new Personne(idPersNominale, "user", "user",
                        "user@afcepf.fr",
                        "user", sdf.parse("21/12/2012"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    /**
     * initialisation avant chaque test.
     * ici nous allons re-generer la BDD pour la cohérence de
     * nos tests.
     */
    @Before
    public void setUp() {
        String pathDotBat = Thread.currentThread()
                .getContextClassLoader().getResource("crebase.bat")
                .getPath();
        try {
            Process proc = Runtime.getRuntime().exec(pathDotBat);
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Test du cas nominal; la personne existe.
     */
    @Test
    public void testConnectionNominale() {
        try {
            Personne retour = dao.seConnecter(mailValide, mdpValide);
            Assert.assertNotNull(retour);
            Assert.assertNotNull(retour.getId());
            Assert.assertNotNull(retour.getNom());
            Assert.assertNotNull(retour.getPrenom());
            Assert.assertNotNull(retour.getMail());
            Assert.assertNotNull(retour.getMdp());
            Assert.assertNotNull(retour.getDateNaissance());
            Assert.assertEquals(retourConnectionNominale.getId(),
                    retour.getId());
            Assert.assertEquals(retourConnectionNominale.getNom(),
                    retour.getNom());
            Assert.assertEquals(retourConnectionNominale.getPrenom(),
                    retour.getPrenom());
            Assert.assertEquals(retourConnectionNominale.getMail(),
                    retour.getMail());
            Assert.assertEquals(retourConnectionNominale.getMdp(),
                    retour.getMdp());
            Assert.assertEquals(
                    sdf.format(retourConnectionNominale.getDateNaissance()),
                    sdf.format(retour.getDateNaissance()));
        } catch (SocialException e) {
            Assert.fail("Bad D�v!!!");
        }
    }
    /**
     * Test du cas d'echec; la personne n'existe pas.
     * @throws SocialException exception car le mail existe pas.
     */
    @Test(expected = SocialException.class)
    public void testConnectionException() throws SocialException {
        dao.seConnecter(mailInvalide, mdpValide);
    }
}
