package fr.eni.bll;

import fr.eni.bo.Editeur;

import java.util.List;

public interface IEditeurService {

    Editeur ajouterUnEditeur(Editeur editeur) throws Exception;
    void modifierUnEditeur(Editeur editeur) throws Exception;

    void supprimerUnEditeur(Editeur editeur);

    List<Editeur> recupererTousLesEditeurs();
    Editeur recupererEditeurParId(long id) throws Exception;
}
