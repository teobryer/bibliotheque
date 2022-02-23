package fr.eni.bll;

import fr.eni.bo.Editeur;
import fr.eni.dal.EditeurDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class EditeurService implements IEditeurService{

    @Autowired
    EditeurDAO editeurDAO;

    @Override
    public Editeur ajouterUnEditeur(Editeur editeur) throws Exception {
        editeur.setId(0);


       List<Editeur> listEditeur = editeurDAO.findByNameNoCaseSensitive(editeur.getNom().toLowerCase());




       if(null== listEditeur || listEditeur.isEmpty()){

           editeurDAO.save(editeur);

           return  editeur;
       }

       throw new Exception("Cet éditeur existe déjà");



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
