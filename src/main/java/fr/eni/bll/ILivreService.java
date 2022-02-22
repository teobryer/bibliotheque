package fr.eni.bll;

import fr.eni.bo.Livre;

import java.util.List;

public interface ILivreService {

    Livre ajouterUnLivre(Livre livre);
    void modifierUnLivre(Livre livre) throws Exception;

    void supprimerUnLivre(Livre livre);

    List<Livre> recupererTousLesLivres();
    Livre recupererLivreParId(long id) throws Exception;
}
