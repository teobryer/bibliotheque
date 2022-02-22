package fr.eni.ihm;


import fr.eni.bll.IEditeurService;
import fr.eni.bo.Editeur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("editeur")
public class EditeurController {


    @Autowired
    IEditeurService editeurService;



    @GetMapping("/ajouter")
    public String ajouterediteur(Model model) {
        Editeur editeur = new Editeur();

        model.addAttribute("editeurForm", editeur);
        return "ajouterediteur";
    }

    @PostMapping("/ajouter")
    public String ajouterEditeurPost(@ModelAttribute("editeurForm") Editeur editeurForm, Model model) {


        try {
            editeurService.ajouterUnEditeur(editeurForm);
        } catch (Exception e) {

            model.addAttribute("monErreur", e.getMessage());
            return "erreur";

        }


        return "redirect:/editeur/liste";
    }

    @GetMapping("/supprimer/{idediteur}")
    public String supprimerediteur(@PathVariable("idediteur") String idediteur, Model model) {

        try {
            Editeur editeur = editeurService.recupererEditeurParId(Long.parseLong(idediteur));

            editeurService.supprimerUnEditeur(editeur);


             return "redirect:/editeur/liste";
        } catch (Exception e) {

            model.addAttribute("monErreur", e.getMessage());
            return "erreur";

        }
    }

    @GetMapping("/modifier/{idediteur}")
    public String modifierediteur(@PathVariable("idediteur") String idediteur, Model model) {
        try {
            Editeur editeur = editeurService.recupererEditeurParId(Long.parseLong(idediteur));

            model.addAttribute("editeurForm", editeur);
            return "modifierEditeur";

        }
        catch (Exception e){
            model.addAttribute("monErreur", e.getMessage());
            return "erreur";
        }

    }

    @PostMapping("/modifier")
    public String modifierEditeurPost(@ModelAttribute("editeurForm") Editeur editeurForm, Model model) {


        try {
            editeurService.modifierUnEditeur(editeurForm);
        } catch (Exception e) {

            model.addAttribute("monErreur", e.getMessage());
            return "erreur";

        }


        return "redirect:/editeur/liste";
    }


    @GetMapping({"/liste", "", "/", })
    public String afficherediteurs(Model model) {
        model.addAttribute("editeurs", editeurService.recupererTousLesEditeurs());
        return "listeEditeurs";
    }

    @GetMapping("/{idediteur}")
    public String detailsediteur(@PathVariable("idediteur") String idediteur, Model model) {
        try {
            model.addAttribute("editeur", editeurService.recupererEditeurParId(Integer.parseInt(idediteur)));
            return "editeur";
        } catch (Exception e) {

            model.addAttribute("monErreur", e.getMessage());
            return "erreur";
        }


    }

    

  
}
