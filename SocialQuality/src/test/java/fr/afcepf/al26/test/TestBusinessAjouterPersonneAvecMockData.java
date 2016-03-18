package fr.afcepf.al26.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.easymock.EasyMock;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.afcepf.al26.qualite.business.api.ISocialBusiness;
import fr.afcepf.al26.qualite.business.impl.SocialBusinessImpl;
import fr.afcepf.al26.qualite.data.api.IDaoPersonne;
import fr.afcepf.al26.qualite.entity.Personne;
import fr.afcepf.al26.qualite.exception.SocialException;

/**
 * Classe de test pour le service AjouterPersonne de
 * {@link ISocialBusiness}.
 * @author Stagiaire
 *
 */
public class TestBusinessAjouterPersonneAvecMockData {
    /**
     * Service a tester.
     */
    private static ISocialBusiness business;
    /**
     * dependence a mocker.
     */
    private static IDaoPersonne mockData;
    /**
     * Personne pour le test nominal.
     */
    private static Personne persNominale;
    /**
     * Personne pour le test avec un champs null.
     */
    private static Personne persNomNull;
    /**
     * Personne pour le test avec un attribut depassant
     * la capacit� du S.I.
     */
    private static Personne persMailTropLong;
    /**
     * Personen pour le test avec un mail qui est déjà
     * dans le S.I.
     */
    private static Personne persMailExisteDeja;
    /**
     * retour pour tester l'ajout nominal.
     */
    private static Personne retourNominal;
    /**
     * Pour la gestion des Dates.
     */
    private static SimpleDateFormat sdf =
            new SimpleDateFormat("dd/MM/yyyy");
    /**
     * Dernier identifiant de la personne pour le
     * cas nominal.
     */
    private static final int LAST_ID = 6;
    /**
     * Initialisation des tests.
     */
    @BeforeClass
    public static void init() {
        try {
            persNominale = new Personne(null, "nom",
                    "prenom", "mail@afcepf.fr", "mdp",
                    sdf.parse("21/12/2012"));
            persNomNull = new Personne(null, null,
                    "prenom", "mail@afcepf.fr", "mdp",
                    sdf.parse("21/12/2012"));
            persMailTropLong = new Personne(null, "nom",
                    "prenom", "mail@afcepf.fr", "mdp",
                    sdf.parse("21/12/2012"));
            for (int i = 0; i < LAST_ID; i++) {
                persMailTropLong.setMail(
                        persMailTropLong.getMail()
                      + persMailTropLong.getMail());
            }
            persMailExisteDeja = new Personne(null, "nom",
                    "prenom", "user@afcepf.fr", "mdp",
                    sdf.parse("21/12/2012"));
            retourNominal = new Personne(LAST_ID, "nom",
                    "prenom", "mail@afcepf.fr", "mdp",
                    sdf.parse("21/12/2012"));
            business = new SocialBusinessImpl();
            mockData = EasyMock.createMock(IDaoPersonne.class);
            EasyMock.expect(mockData.verifMail(persNominale.getMail()))
                    .andReturn(false);
            EasyMock.expect(mockData.verifMail(persNomNull.getMail()))
                    .andReturn(false);
            EasyMock.expect(mockData.verifMail(persMailTropLong.getMail()))
                    .andReturn(false);
            EasyMock.expect(mockData.verifMail(persMailExisteDeja.getMail()))
                    .andReturn(true);
            EasyMock.expect(mockData.ajouter(persNominale))
                    .andReturn(retourNominal);
            EasyMock.expect(mockData.ajouter(persMailTropLong))
                    .andThrow(new SocialException(""));
            EasyMock.expect(mockData.ajouter(persNomNull))
                    .andThrow(new SocialException(""));
            EasyMock.replay(mockData);
            ((SocialBusinessImpl) business).setDao(mockData);
        } catch (ParseException | SocialException e) {
            e.printStackTrace();
        }
    }
    /**
     * Verification des Expects.
     */
    @AfterClass
    public static void finDesTests() {
        EasyMock.verify(mockData);
    }
    /**
     * Test Nominal.
     */
    @Test
    public void testAjoutNominal() {
        try {
            Personne retour = business.ajouter(persNominale);
            Assert.assertNotNull(retour);
            Assert.assertNotNull(retour.getId());
            Assert.assertNotNull(retour.getMail());
            Assert.assertNotNull(retour.getMdp());
            Assert.assertNotNull(retour.getNom());
            Assert.assertNotNull(retour.getPrenom());
            Assert.assertNotNull(retour.getDateNaissance());
            Assert.assertEquals(retourNominal.getId(),
                                retour.getId());
            Assert.assertEquals(retourNominal.getNom(),
                    retour.getNom());
            Assert.assertEquals(retourNominal.getPrenom(),
                    retour.getPrenom());
            Assert.assertEquals(retourNominal.getMail(),
                    retour.getMail());
            Assert.assertEquals(retourNominal.getMdp(),
                    retour.getMdp());
            Assert.assertEquals(
                    sdf.format(retourNominal.getDateNaissance()),
                    sdf.format(retour.getDateNaissance()));
        } catch (SocialException e) {
            e.printStackTrace();
        }
    }
    /**
     * Test Exception null.
     * @throws SocialException exception levée.
     */
    @Test(expected = SocialException.class)
    public void testAjoutNomNull() throws SocialException {
        business.ajouter(persNomNull);
    }
    /**
     * Test Exception trop long.
     * @throws SocialException exception levée.
     */
    @Test(expected = SocialException.class)
    public void testAjoutMailTropLong() throws SocialException {
        business.ajouter(persMailTropLong);
    }
    /**
     * Test Exception mail existe.
     * @throws SocialException exception levée.
     */
    @Test(expected = SocialException.class)
    public void testAjoutMailExiste() throws SocialException {
        business.ajouter(persMailExisteDeja);
    }
}
