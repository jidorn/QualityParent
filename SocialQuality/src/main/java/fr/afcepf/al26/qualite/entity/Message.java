package fr.afcepf.al26.qualite.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Classe entitée representant l'entitée Message dans
 * le serveur de données.
 * @author Stagiaire
 */
@Entity
@Table(name="message")
public class Message implements Serializable {
    /**
     * L'identifiant du {@link Message}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * La date d'envoi du {@link Message}.
     */
    @Column(name="date_creation")
    private Date dateCreation;
    /**
     * Le contenu texte du {@link Message}.
     */
    private String contenu;
    /**
     * La {@link Personne} ayant envoy� le {@link Message}.
     */
    @ManyToOne
    @JoinColumn(name="id_expediteur")
    private Personne expediteur;
    /**
     * Pour qui le {@link Message}.
     */
    @ManyToOne
    @JoinColumn(name="id_destinataire")
    private Personne destinataire;
    /**
     * Default Constructor.
     */
    public Message() {
    }
    /**
     * @param paramId the new {@link Message#id}.
     * @param paramDateCreation the new {@link Message#dateCreation}.
     * @param paramContenu the new {@link Message#contenu}.
     * @param paramExpediteur the new {@link Message#expediteur}.
     * @param paramDestinataire the new {@link Message#destinataire}.
     */
    public Message(Integer paramId, Date paramDateCreation,
            String paramContenu, Personne paramExpediteur,
            Personne paramDestinataire) {
        id = paramId;
        dateCreation = paramDateCreation;
        contenu = paramContenu;
        expediteur = paramExpediteur;
        destinataire = paramDestinataire;
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
     * @return the dateCreation
     */
    public Date getDateCreation() {
        return dateCreation;
    }
    /**
     * @param paramDateCreation the dateCreation to set
     */
    public void setDateCreation(Date paramDateCreation) {
        dateCreation = paramDateCreation;
    }
    /**
     * @return the contenu
     */
    public String getContenu() {
        return contenu;
    }
    /**
     * @param paramContenu the contenu to set
     */
    public void setContenu(String paramContenu) {
        contenu = paramContenu;
    }
    /**
     * @return the expediteur
     */
    public Personne getExpediteur() {
        return expediteur;
    }
    /**
     * @param paramExpediteur the expediteur to set
     */
    public void setExpediteur(Personne paramExpediteur) {
        expediteur = paramExpediteur;
    }
    /**
     * @return the destinataire
     */
    public Personne getDestinataire() {
        return destinataire;
    }
    /**
     * @param paramDestinataire the destinataire to set
     */
    public void setDestinataire(Personne paramDestinataire) {
        destinataire = paramDestinataire;
    }
}
