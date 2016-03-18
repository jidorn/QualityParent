package fr.afcepf.al26.qualite.business.impl;

import java.util.List;

import fr.afcepf.al26.qualite.business.api.ISocialBusiness;
import fr.afcepf.al26.qualite.data.api.IDaoPersonne;
import fr.afcepf.al26.qualite.data.impl.DaoPersonneJDBC;
import fr.afcepf.al26.qualite.entity.Message;
import fr.afcepf.al26.qualite.entity.Personne;
import fr.afcepf.al26.qualite.exception.SocialException;

/**
 * Implementation des règles métier du SocialBusiness.
 * @author Stagiaire
 *
 */
public class SocialBusinessImpl implements ISocialBusiness {
    /**
     * dependence vers la couche DATA.
     */
    private IDaoPersonne dao = new DaoPersonneJDBC();
    @Override
    public Personne ajouter(Personne paramPersonne) throws SocialException {
        if (!dao.verifMail(paramPersonne.getMail())) {
            paramPersonne = dao.ajouter(paramPersonne);
        } else {
            throw new SocialException("mail existe d�j�",
                    SocialException.ErrorCode.MAIL_EXISTE_DEJA);
        }
        return paramPersonne;
    }

    /**
     * @return the dao
     */
    public IDaoPersonne getDao() {
        return dao;
    }


    /**
     * @param paramDao the dao to set
     */
    public void setDao(IDaoPersonne paramDao) {
        dao = paramDao;
    }


    @Override
    public List<Personne> rechercher(String paramNom) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Personne seConnecter(String paramMail, String paramMdp)
            throws SocialException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Message envoyer(Message paramMsg) throws SocialException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Message> consulterMessage(Personne paramPersonne) {
        // TODO Auto-generated method stub
        return null;
    }

}
