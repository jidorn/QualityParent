package fr.afcepf.al26.qualite.data.impl;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.al26.qualite.data.api.IDaoPersonne;
import fr.afcepf.al26.qualite.entity.Personne;
import fr.afcepf.al26.qualite.exception.SocialException;

/**
 * Implementation avec EJB + JPA.
 * @author Stagiaire
 *
 */
@Remote(IDaoPersonne.class)
@Stateless
public class DaoPersonneJPA implements IDaoPersonne {
    /**
     * Pour JPA.
     */
    @PersistenceContext(unitName="SocialQuality")
    private EntityManager em;
    @Override
    public Personne ajouter(Personne paramPersonne) throws SocialException {
        try {
            em.persist(paramPersonne);
            em.flush();
        } catch (Exception e) {
            throw new SocialException(e.getMessage(),
                    SocialException.ErrorCode.CA_MARCHE_PAS);
        }
        return paramPersonne;
    }

    @Override
    public boolean verifMail(String paramMail) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Personne seConnecter(String paramMail, String paramMdp)
            throws SocialException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Personne> rechercher(String paramNom) {
        // TODO Auto-generated method stub
        return null;
    }

}
