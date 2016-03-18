package fr.afcepf.al26.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.afcepf.al26.qualite.data.api.IDaoPersonne;
import fr.afcepf.al26.qualite.entity.Personne;

/**
 * Classe pour tester la recherche de {@link Personne}
 * du Dao.
 * @author Stagiaire
 */
public class TestDaoRecherchePersonneAvecEasyMock {
    /**
     * Service a tester.
     */
    private static IDaoPersonne dao;
    /**
     * pour la recherche qui retourne une valeur.
     */
    private static String nomExiste;
    /**
     * pour la recherche qui ne retourne rien.
     */
    private static String nomExistePas;
    /**
     * retour pour la recherche qui retourne une valeur.
     */
    private static List<Personne> retourNominal = new ArrayList<>();
    /**
     * retour pour la recherche qui ne retourne rien.
     */
    private static List<Personne> retourAlternatif = new ArrayList<>();
    /**
     * pour les comparaisons de Date.
     */
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    /**
     * taille de la liste de retour nominal.
     */
    private static int tailleListeNominale = 1;
    /**
     * taille de la liste de retour alternatif.
     */
    private static int tailleListeAlternative = 0;
    /**
     * Personne de la liste nominale.
     */
    private static Personne personneNominale;
    /**
     * Identifiant de la personne retourn�e.
     */
    private static final int ID_PERSONNE_NOMINALE = 5;
    /**
     * Initialisation des tests.
     */
    @BeforeClass
    public static void initialisationDesTests() {
        try {
            nomExiste = "user";
            nomExistePas = "nimporte";
            personneNominale = new Personne(ID_PERSONNE_NOMINALE,
                    "user", "user", "user@afcepf.fr", "user",
                    sdf.parse("21/12/2012"));
            retourNominal.add(personneNominale);
            dao = EasyMock.createMock(IDaoPersonne.class);
            EasyMock.expect(dao.rechercher(nomExiste))
                    .andReturn(retourNominal);
            EasyMock.expect(dao.rechercher(nomExistePas))
                    .andReturn(retourAlternatif);
            EasyMock.replay(dao);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    /**
     * Après tous les tests.
     */
    @AfterClass
    public static void finDesTests() {
        EasyMock.verify(dao);
    }
    /**
     * Test d'une recherche qui retourne une valeur.
     */
    @Test
    public void testRechercheNominal() {
        List<Personne> retour = dao.rechercher(nomExiste);
        Assert.assertNotNull(retour);
        Assert.assertEquals(tailleListeNominale, retour.size());
        Personne p = retour.get(0);
        Assert.assertNotNull(p);
        Assert.assertNotNull(p.getId());
        Assert.assertNotNull(p.getNom());
        Assert.assertNotNull(p.getPrenom());
        Assert.assertNotNull(p.getMail());
        Assert.assertNotNull(p.getMdp());
        Assert.assertNotNull(p.getDateNaissance());
        Assert.assertEquals(personneNominale.getId(),
                p.getId());
        Assert.assertEquals(personneNominale.getNom(),
                p.getNom());
        Assert.assertEquals(personneNominale.getPrenom(),
                p.getPrenom());
        Assert.assertEquals(personneNominale.getMail(),
                p.getMail());
        Assert.assertEquals(personneNominale.getMdp(),
                p.getMdp());
        Assert.assertEquals(
                sdf.format(personneNominale.getDateNaissance()),
                sdf.format(p.getDateNaissance()));
    }
    /**
     * Test d'une recherche qui retourne pas de valeur.
     */
    @Test
    public void testRechercheAlternatif() {
        List<Personne> retour = dao.rechercher(nomExistePas);
        Assert.assertNotNull(retour);
        Assert.assertEquals(tailleListeAlternative, retour.size());
    }
}
