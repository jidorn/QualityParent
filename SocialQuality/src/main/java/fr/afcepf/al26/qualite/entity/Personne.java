package fr.afcepf.al26.qualite.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe entitée representant l'entitée Personne dans
 * le serveur de données.
 * @author Stagiaire
 */
@Entity
@Table(name="personne")
public class Personne implements Serializable {
    /**
     * L'identifiant unique de la {@link Personne}.
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    /**
     * Le nom de la {@link Personne}.
     */
    private String nom;
    /**
     * Le prenom de la {@link Personne}.
     */
    private String prenom;
    /**
     * L'email de la {@link Personne}.
     */
    private String mail;
    /**
     * Le mot de passe de la {@link Personne}.
     */
    private String mdp;
    /**
     * La date de naissance de la {@link Personne}.
     */
    @Column(name="date_naissance")
    private Date dateNaissance;
    /**
     * Default Constructor.
     */
    public Personne() {
    }
    /**
     * @param paramId the new {@link Personne#id}.
     * @param paramNom the new {@link Personne#nom}.
     * @param paramPrenom the new {@link Personne#prenom}.
     * @param paramMail the new {@link Personne#mail}.
     * @param paramMdp the new {@link Personne#mdp}.
     * @param paramDateNaissance the new {@link Personne#dateNaissance}.
     */
    public Personne(Integer paramId, String paramNom, String paramPrenom,
            String paramMail, String paramMdp, Date paramDateNaissance) {
        id = paramId;
        nom = paramNom;
        prenom = paramPrenom;
        mail = paramMail;
        mdp = paramMdp;
        dateNaissance = paramDateNaissance;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    /**
     * @param paramId the id to set
     */
    public void setId(Integer paramId) {
        id = paramId;
    }
    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }
    /**
     * @param paramNom the nom to set
     */
    public void setNom(String paramNom) {
        nom = paramNom;
    }
    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }
    /**
     * @param paramPrenom the prenom to set
     */
    public void setPrenom(String paramPrenom) {
        prenom = paramPrenom;
    }
    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }
    /**
     * @param paramMail the mail to set
     */
    public void setMail(String paramMail) {
        mail = paramMail;
    }
    /**
     * @return the mdp
     */
    public String getMdp() {
        return mdp;
    }
    /**
     * @param paramMdp the mdp to set
     */
    public void setMdp(String paramMdp) {
        mdp = paramMdp;
    }
    /**
     * @return the dateNaissance
     */
    public Date getDateNaissance() {
        return dateNaissance;
    }
    /**
     * @param paramDateNaissance the dateNaissance to set
     */
    public void setDateNaissance(Date paramDateNaissance) {
        dateNaissance = paramDateNaissance;
    }
}
