package fr.afcepf.al26.qualite.exception;


/**
 * Exception pour notre reseau Social.
 * @author Stagiaire
 */
public class SocialException extends Exception {
    /**
     * version pour la Serialisation.
     * car la classe {@link Exception} implement
     * Serializable.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Code de l'exception.
     */
    private ErrorCode codeErreur = ErrorCode.CA_MARCHE_PAS;
    /**
     * liste des codes d'erreurs de l'application.
     */
    public enum ErrorCode {
        /**
         * Erreur generique.
         */
        CA_MARCHE_PAS,
        /**
         * Erreur lors de l'ajout d'une personne si
         * le mail existe deja.
         */
        MAIL_EXISTE_DEJA,
        /**
         * si il manque des informations pour ajouter une Personne.
         */
        PERSONNE_INCOMPLETE,
        /**
         * si il manque des informations pour ajouter un Message.
         */
        MESSAGE_INCOMPLET
    }
    /**
     * @param paramMessage le message.
     * @param paramCodeErreur le {@link ErrorCode}
     * de l'{@link Exception}.
     */
    public SocialException(String paramMessage,
                           ErrorCode paramCodeErreur) {
        super(paramMessage);
        codeErreur = paramCodeErreur;
    }
    /**
     * Constructeur d'erreur avec juste un message.
     * @param paramMessage le message.
     */
    public SocialException(String paramMessage) {
        super(paramMessage);
    }
    /**
     * @return the codeErreur
     */
    public ErrorCode getCodeErreur() {
        return codeErreur;
    }
    /**
     * @param paramCodeErreur the codeErreur to set
     */
    public void setCodeErreur(ErrorCode paramCodeErreur) {
        codeErreur = paramCodeErreur;
    }
}
