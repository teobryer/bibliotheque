package fr.eni.ihm;


import fr.eni.bll.IEditeurService;
import fr.eni.bo.Editeur;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("editeur")
public class EditeurController {


    @Autowired
    IEditeurService editeurService;
    private ObjectError e;
    private ObjectError e1;


    @GetMapping("/ajouter")
    public String ajouterediteur(Model model) {
        Editeur editeur = new Editeur();


        model.addAttribute("editeurForm", editeur);
        return "ajouterediteur";


    }

    @PostMapping("/ajouter")
    public String ajouterEditeurPost(@ModelAttribute("editeurForm") @Valid Editeur editeurForm, BindingResult br, Model model) {


        try {

            if (!br.hasErrors()) {
                editeurService.ajouterUnEditeur(editeurForm);
            } else {
                model.addAttribute("monErreur", afficherErreurs(br));
                return "erreur";
            }
        } catch (Exception e) {

            model.addAttribute("monErreur", e.getMessage());
            return "erreur";

        }


        return "redirect:/editeur/liste";
    }


    private String afficherErreurs(BindingResult b) {
        String er = "";

        for (Object e : b.getAllErrors()) {
            if (e instanceof FieldError) {
                er += " " +  StringUtils.capitalize(((FieldError) e).getField().toLowerCase())  +" " + ((FieldError) e).getDefaultMessage() +",";
            }



        }

        er= er.substring(0, er.length() - 1);

        return er;

    }

    @GetMapping("/supprimer/{idediteur}")
    public String supprimerediteur(@PathVariable("idediteur") String idediteur, Model model) {

        try {
            Editeur editeur = editeurService.recupererEditeurParId(Long.parseLong(idediteur));

            editeurService.supprimerUnEditeur(editeur);


            return "redirect:/editeur/liste";
        } catch (Exception e) {

            if (e instanceof DataIntegrityViolationException) {
                model.addAttribute("monErreur", "BAH NON");

                System.out.println(((DataIntegrityViolationException) e).getCause().getClass());
            } else {
                model.addAttribute("monErreur", e.getMessage());
            }

            return "erreur";

        }
    }

    @GetMapping("/modifier/{idediteur}")
    public String modifierediteur(@PathVariable("idediteur") String idediteur, Model model) {
        try {
            Editeur editeur = editeurService.recupererEditeurParId(Long.parseLong(idediteur));

            model.addAttribute("editeurForm", editeur);
            return "modifierEditeur";

        } catch (Exception e) {
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


    @GetMapping({"/liste", "", "/",})
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
