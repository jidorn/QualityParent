package fr.afcepf.al26.qualite.data.api;

import java.util.List;

import fr.afcepf.al26.qualite.entity.Message;
import fr.afcepf.al26.qualite.entity.Personne;
import fr.afcepf.al26.qualite.exception.SocialException;

/**
 * D�finition des services d'accès aux données pour
 * l'entit�e {@link Message}.
 * @author Stagiaire
 */
public interface IDaoMessage {
    /**
     * Méthode permettant d'ajouter une entit�e {@link Message}
     * dans la BDD.
     * @param paramMsg le {@link Message} à créer.
     * @return le {@link Message}.
     * @throws SocialException si une erreur se produit
     * lors de la création du {@link Message}.
     * <ul>
     *      <li>pas de destinataire</li>
     *      <li>pas de contenu</li>
     *      <li>trop de contenu</li>
     *      <li>le serveur ne repond pas</li>
     *      <li>...</li>
     * </ul>
     */
    Message ajouter(Message paramMsg) throws SocialException;
    /**
     * Méhode permettant de rechercher tout les {@link Message}(s)
     * destinés à une {@link Personne} donnée.
     * @param paramPersonne la {@link Personne} destinataire des
     * {@link Message}.
     * @return une {@link List} de {@link Message} pour cette
     * {@link Personne}.
     */
    List<Message> rechercher(Personne paramPersonne);
}
