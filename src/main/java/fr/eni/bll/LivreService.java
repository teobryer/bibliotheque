package fr.eni.bll;

import fr.eni.bo.Livre;
import fr.eni.dal.LivreDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivreService implements ILivreService{

    @Autowired
    LivreDAO livreDAO;

    @Override
    public Livre ajouterUnLivre(Livre livre) {
        livre.setId(0);



        livreDAO.save(livre);

        return  livre;

    }

    @Override
    public void modifierUnLivre(Livre livre) throws Exception {

        recupererLivreParId(livre.getId());
        
        livreDAO.save(livre);
    }

    @Override
    public void supprimerUnLivre(Livre livre) {
        livreDAO.delete(livre);
    }

    @Override
    public List<Livre> recupererTousLesLivres() {
        return  livreDAO.findAll();
    }

    @Override
    public Livre recupererLivreParId(long id) throws Exception {


       Optional<Livre> livre = livreDAO.findById(id);

        if(livre.isPresent() ){
          return livre.get();
        }
        else{
            throw new Exception("Aucun livre ne correspond");
        }

    }
}
