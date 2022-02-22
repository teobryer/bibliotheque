package fr.eni.bll;

import fr.eni.bo.Editeur;
import fr.eni.dal.EditeurDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditeurService implements IEditeurService{

    @Autowired
    EditeurDAO editeurDAO;

    @Override
    public Editeur ajouterUnEditeur(Editeur editeur) {
        editeur.setId(0);

        editeurDAO.save(editeur);

        return  editeur;

    }

    @Override
    public void modifierUnEditeur(Editeur editeur) throws Exception {

        recupererEditeurParId(editeur.getId());

        editeurDAO.save(editeur);
    }

    @Override
    public void supprimerUnEditeur(Editeur editeur) {
        editeurDAO.delete(editeur);
    }

    @Override
    public List<Editeur> recupererTousLesEditeurs() {
        return  editeurDAO.findAll();
    }

    @Override
    public Editeur recupererEditeurParId(long id) throws Exception {


        Optional<Editeur> editeur = editeurDAO.findById(id);

        if(editeur.isPresent() ){
            return editeur.get();
        }
        else{
            throw new Exception("Aucun editeur ne correspond");
        }

    }
}
