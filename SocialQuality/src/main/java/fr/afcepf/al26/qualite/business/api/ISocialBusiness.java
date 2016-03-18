package fr.afcepf.al26.qualite.business.api;

import java.util.List;

import fr.afcepf.al26.qualite.entity.Message;
import fr.afcepf.al26.qualite.entity.Personne;
import fr.afcepf.al26.qualite.exception.SocialException;

/**
 * Définition des services fournis par la Façade
 * contenant les règles de gestion de l'application.
 * @author Stagiaire
 *
 */
public interface ISocialBusiness {
    /**
     * Méthode permettant d'effectuer un ajout de {@link Personne}.
     * @param paramPersonne la {@link Personne} à ajouter.
     * @return la Personne ajoutée.
     * @throws SocialException
     * <code>
     *    <ul>
     *      <li>Si le mail de la personne existe déjà</li>
     *      <li>Si le <b>nom</b>, <b>prenom</b>,
     *      <b>mail</b>, <b>mdp</b> ou <b>date de naissance</b>
     *      de la personne ne sont pas renseignés.</li>
     *      <li>Si le <b>nom</b>, <b>prenom</b>,
     *      <b>mail</b>, <b>mdp</b> ou <b>date de naissance</b>
     *      de la personne n'ont pas le bon format.</li>
     *      <li>Si le serveur de données ne reponds pas.</li>
     *      <li>...</li>
     *    </ul>
     * </code>
     */
    Personne ajouter(Personne paramPersonne) throws SocialException;
    /**
     * Méthode permettant de rechercher toutes les {@link Personne}(s)
     * dont le nom contient le param�tre nom envoyé à cette
     * methode.
     * @param paramNom le morceau de nom recherché.
     * @return la liste des Personnes contenant ce param�tre.
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
    /**
     * Méthode permettant de vérifier l'existance d'une
     * {@link Personne} via son mail et son Mot de passe.
     * @param paramMail le mail de la {@link Personne}.
     * @param paramMdp le mot de passe de la {@link Personne}.
     * @return la {@link Personne} ayant ce mail et ce mot de passe.
     * @throws SocialException
     * <code>
     *  <b>une {@link Exception} social si il n'est pas
     *  enregistré dans le système.</b>
     * </code>
     */
    Personne seConnecter(String paramMail, String paramMdp)
            throws SocialException;
    /**
     * Méthode permettant d'ajouter une entitée {@link Message}.
     * @param paramMsg le {@link Message} à créer.
     * @return le {@link Message}.
     * @throws SocialException si une erreur se produit
     * lors de la cr�ation du {@link Message}.
     * <ul>
     *      <li>pas de destinataire</li>
     *      <li>pas de contenu</li>
     *      <li>trop de contenu</li>
     *      <li>le serveur ne repond pas</li>
     *      <li>...</li>
     * </ul>
     */
    Message envoyer(Message paramMsg) throws SocialException;
    /**
     * Méthode permettant de rechercher tout les {@link Message}(s)
     * destinés à une {@link Personne} donnée.
     * @param paramPersonne la {@link Personne} destinataire des
     * {@link Message}.
     * @return une {@link List} de {@link Message} pour cette
     * {@link Personne}.
     */
    List<Message> consulterMessage(Personne paramPersonne);
}
