package fr.afcepf.al26.qualite.data.api;

import java.util.List;

import fr.afcepf.al26.qualite.entity.Personne;
import fr.afcepf.al26.qualite.exception.SocialException;

/**
 * Définition des Service d'acc�s auc donn�es de l'application
 * pour l'entitée {@link Personne}.
 * @author Stagiaire
 */
public interface IDaoPersonne {
    /**
     * Méthode permettant d'effectuer un ajout
     * de {@link Personne} dans le serveur de données.
     * @param paramPersonne la {@link Personne} à ajouter.
     * @return la Personne ajoutée.
     * @throws SocialException
     * <code>
     *    <ul>
     *      <li>Si le <b>nom</b>, <b>prenom</b>,
     *      <b>mail</b>, <b>mdp</b> ou <b>date de naissance</b>
     *      de la personne ne sont pas renseignés.</li>
     *      <li>Si le <b>nom</b>, <b>prenom</b>,
     *      <b>mail</b>, <b>mdp</b> ou <b>date de naissance</b>
     *      de la personne n'ont pas le bon format.</li>
     *      <li>Si le serveur de donn�es ne reponds pas.</li>
     *      <li>...</li>
     *    </ul>
     * </code>
     */
    Personne ajouter(Personne paramPersonne)
            throws SocialException;
    /**
     * Méthode permettant de verifier l'existance d'un
     * mail dans la BDD.
     * @param paramMail le mail à vérifier.
     * @return
     * <code>
     *      <ol type='1'>
     *          <li>
     *              <strong>true</strong> si il existe.
     *          </li>
     *          <li>
     *              <strong>false</strong> si il n'existe pas.
     *          </li>
     *      </ol>
     * </code>
     */
    boolean verifMail(String paramMail);
    /**
     * Méthode permettant de vérifier l'existance d'une
     * {@link Personne} via son mail et son Mot de passe.
     * @param paramMail le mail de la {@link Personne}.
     * @param paramMdp le mot de passe de la {@link Personne}.
     * @return la {@link Personne} ayant ce mail et ce mot de passe.
     * @throws SocialException
     * <code>
     *  <b>une {@link Exception} social si il n'est pas
     *  enregistr� dans le système.</b>
     * </code>
     */
    Personne seConnecter(String paramMail, String paramMdp)
        throws SocialException;
    /**
     * Méthode permettant de rechercher toutes les {@link Personne}(s)
     * dont le nom contient le paramètre nom envoyé à cette
     * methode.
     * @param paramNom le morceau de nom recherché.
     * @return la liste des Personnes contenant ce paramètre.
     * <code>
     *      <strong>Exemples : </strong><br />
     *      <ul>
     *          <li>la recherche de 'du' retournerait :
     *              <ol>
     *                  <li>'Du'pond</li>
     *                  <li>'Du'pont</li>
     *                  <li>'Du'rant</li>
     *                  <li>Mon'du'çont</li>
     *                  <li>...</li>
     *              </ol>
     *          </li>
     *      </ul>
     * </code>
     */
    List<Personne> rechercher(String paramNom);
}
