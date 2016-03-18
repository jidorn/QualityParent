package fr.afcepf.al26.test;

import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * Test d'execution de la re-génération de la BDD pour les tests.
 * @author Stagiaire
 *
 */
public final class TestDotBat {
    /**
     * Default private Constructor for Util Class.
     */
    private TestDotBat() {
    }
    /**
     * Logger for syso.
     */
    private static Logger log = Logger.getLogger(TestDotBat.class);
    /**
     * Application Entry Point.
     * @param args args for the Application.
     */
    public static void main(String[] args) {
        log.info("Début du test ...");
        String pathDotBat = Thread.currentThread()
            .getContextClassLoader().getResource("crebase.bat")
            .getPath();
        log.info(pathDotBat);
        pathDotBat = pathDotBat.replaceAll("%20", " ");
        try {
            Process proc = Runtime.getRuntime().exec(pathDotBat);
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        log.info("fin du test ...");
    }
}
