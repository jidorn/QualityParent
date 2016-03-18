package fr.afcepf.al26.test;

import java.io.IOException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.afcepf.al26.qualite.data.api.IDaoPersonne;
import fr.afcepf.al26.qualite.data.impl.DaoPersonneJDBC;
import fr.afcepf.al26.qualite.entity.Personne;
import fr.afcepf.al26.qualite.exception.SocialException;

/**
 * Classe de test pour tester l'ajout d'une entitée {@link Personne}
 * dans la BDD.
 * @author Stagiaire
 */
public class TestAjouterPersonneDAO {
    /**
     * constante d'instance
     * pour la date des {@link Personne} à tester.
     */
    private final Date datePourTest = new Date();
    /**
     * constante d'instance
     * Prochain identifiant par rapport à la BDD de tests.
     */
    private final int lastId = 6;
    /**
     * {@link Personne} pour l'envoi à l'ajout Nominal.
     */
    private Personne persNominal =
        new Personne(null, "nom", "prenom", "mail@pas.trop.long",
                     "mdp", datePourTest);
    /**
     * Personne attendue apr�s l'ajout nominal.
     * "What did you expect? ... What else!".
     */
    private Personne persNominalRetour =
        new Personne(lastId, "nom", "prenom", "mail@pas.trop.long",
                     "mdp", datePourTest);
    /**
     * {@link Personne} pour le test de l'ajout avec un nom null.
     */
    private Personne persNomNull =
            new Personne(null, null, "prenom", "mail@pas.trop.long",
            "mdp", datePourTest);
    /**
     * {@link Personne} pour le test avec un nom trop long.
     */
    private Personne persNomTropLong =
            new Personne(null, "nom", "prenom", "mail@pas.trop.long",
            "mdp", datePourTest);
    /**
     * Classe a tester.
     * penser a definir son implementation par la suite.
     */
    private IDaoPersonne dao = new DaoPersonneJDBC();
    /**
     * Compteur pour les tests.
     */
    private static final int DIX = 10;
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
        pathDotBat = pathDotBat.replaceAll("%20", " ");
        try {
            Process proc = Runtime.getRuntime().exec(pathDotBat);
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < DIX; i++) {
            persNomTropLong.setNom(persNomTropLong.getNom()
                                 + persNomTropLong.getNom());
        }
    }
    /**
     * Test du cas nominal.
     */
    @Test
    public void testAjoutNominal() {
        try {
            Personne retour = dao.ajouter(persNominal);
            Assert.assertNotNull(retour);
            Assert.assertNotNull(retour.getMail());
            Assert.assertNotNull(retour.getId());
            Assert.assertNotNull(retour.getPrenom());
            Assert.assertNotNull(retour.getDateNaissance());
            Assert.assertNotNull(retour.getNom());
            Assert.assertNotNull(retour.getMdp());
            Assert.assertEquals(persNominalRetour.getId(),
                    retour.getId());
            Assert.assertEquals(persNominalRetour.getNom(),
                    retour.getNom());
            Assert.assertEquals(persNominalRetour.getPrenom(),
                    retour.getPrenom());
            Assert.assertEquals(persNominalRetour.getMail(),
                    retour.getMail());
            Assert.assertEquals(persNominalRetour.getDateNaissance(),
                    retour.getDateNaissance());
            Assert.assertEquals(persNominalRetour.getMdp(),
                    retour.getMdp());
        } catch (SocialException e) {
            Assert.fail("Bad D�v");
        }
    }
    /**
     * Test d'erreur d'une données trop longue.
     */
    @Test
    public void testAjoutNomTropLong() {
        try {
            dao.ajouter(persNomTropLong);
            Assert.fail("Ca n'aurai jamais du marcher!!");
        } catch (SocialException e) {
            Assert.assertTrue(
                "je plante donc je suis un bon d�v", true);
        }
    }
    /**
     * Test d'erreur d'une données nulle.
     * @throws SocialException l'exception attendue.
     */
    @Test(expected = SocialException.class)
    public void testAjoutNomNull() throws SocialException {
        dao.ajouter(persNomNull);
    }
 }
