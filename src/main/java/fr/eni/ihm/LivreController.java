package fr.eni.ihm;


import fr.eni.bll.IEditeurService;
import fr.eni.bll.ILivreService;
import fr.eni.bo.Editeur;
import fr.eni.bo.Livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("livre")
public class LivreController {


    @Autowired
    ILivreService livreService;



    @Autowired
    IEditeurService editeurService;


    @GetMapping("/ajouter")
    public String ajouterlivre(Model model) {
        Livre livre = new Livre();


       List<Editeur> listeEditeurs = editeurService.recupererTousLesEditeurs();

       if(!listeEditeurs.isEmpty()){
           model.addAttribute("livreForm", livre);
           model.addAttribute("allEditeurs", listeEditeurs);
           return "ajouterlivre";
       }

       else{
           model.addAttribute("monErreur", "Veuillez d'abord créer au moins un éditeur pour créer un livre.");
           return "erreur";
       }

    }

    @PostMapping("/ajouter")
    public String ajouterLivrePost(@ModelAttribute("livreForm") Livre livreForm, Model model) {


        try {
            livreService.ajouterUnLivre(livreForm);
        } catch (Exception e) {

            model.addAttribute("monErreur", e.getMessage());
            return "erreur";

        }


        return "redirect:/livre/liste";
    }

    @GetMapping("/supprimer/{idlivre}")
    public String supprimerlivre(@PathVariable("idlivre") String idlivre, Model model) {

        try {
            Livre livre = livreService.recupererLivreParId(Long.parseLong(idlivre));

            livreService.supprimerUnLivre(livre);


             return "redirect:/livre/liste";
        } catch (Exception e) {

            model.addAttribute("monErreur", e.getMessage());
            return "erreur";

        }
    }

    @GetMapping("/modifier/{idlivre}")
    public String modifierlivre(@PathVariable("idlivre") String idlivre, Model model) {
        try {
            Livre livre = livreService.recupererLivreParId(Long.parseLong(idlivre));

            model.addAttribute("livreForm", livre);
            model.addAttribute("allEditeurs", editeurService.recupererTousLesEditeurs());
            return "modifierLivre";

        }
        catch (Exception e){
            model.addAttribute("monErreur", e.getMessage());
            return "erreur";
        }

    }

    @PostMapping("/modifier")
    public String modifierLivrePost(@ModelAttribute("livreForm") Livre livreForm, Model model) {


        try {
            livreService.modifierUnLivre(livreForm);
        } catch (Exception e) {

            model.addAttribute("monErreur", e.getMessage());
            return "erreur";

        }


        return "redirect:/livre/liste";
    }


    @GetMapping({"/liste", "", "/", })
    public String afficherlivres(Model model) {
        model.addAttribute("livres", livreService.recupererTousLesLivres());
        return "listeLivres";
    }

    @GetMapping("/{idlivre}")
    public String detailslivre(@PathVariable("idlivre") String idlivre, Model model) {
        try {
            model.addAttribute("livre", livreService.recupererLivreParId(Integer.parseInt(idlivre)));
            return "livre";
        } catch (Exception e) {

            model.addAttribute("monErreur", e.getMessage());
            return "erreur";
        }


    }

    

  
}
