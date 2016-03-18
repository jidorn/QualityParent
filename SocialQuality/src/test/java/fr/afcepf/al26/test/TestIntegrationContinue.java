package fr.afcepf.al26.test;

import java.util.Date;

import javax.ejb.EJB;

import org.junit.Assert;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePath;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.afcepf.al26.qualite.data.api.IDaoPersonne;
import fr.afcepf.al26.qualite.data.impl.DaoPersonneJPA;
import fr.afcepf.al26.qualite.entity.Message;
import fr.afcepf.al26.qualite.entity.Personne;
import fr.afcepf.al26.qualite.exception.SocialException;

@RunWith(Arquillian.class)
public class TestIntegrationContinue {
    @Deployment
    public static JavaArchive createArchive() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class,
                "nimportecomment.jar");
        jar.addClass(DaoPersonneJPA.class)
           .addClass(IDaoPersonne.class)
           .addClass(Personne.class)
           .addClass(Message.class)
           .addClass(SocialException.class);
        jar.addAsManifestResource("persistence-test.xml",
                ArchivePaths.create("persistence.xml"));
        jar.addAsResource("import.sql");
        // POUR java CDI
        jar.addAsManifestResource(EmptyAsset.INSTANCE ,"beans.xml");
        return jar;
    }
    @EJB
    private IDaoPersonne dao;
    @Test
    public void testAjoutNominal() {
        try {
            Personne retour = dao.ajouter(
                new Personne(null, "paramNom",
                    "paramPrenom", "paramMail",
                    "paramMdp", new Date()));
            Assert.assertNotNull(retour);
            Assert.assertNotNull(retour.getId());
            Assert.assertEquals(6, retour.getId().intValue());
            // et tout le reste pour etre rigoureux!!!
        } catch (SocialException e) {
            e.printStackTrace();
        }
    }
}
