package fr.eni.ihm;


import fr.eni.bll.ILivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ApplicationController {


    @Autowired
    ILivreService livreService;



    @GetMapping({"/"})
    public String index(Model model) {

        model.addAttribute("livres", livreService.recupererTousLesLivres());
        return "listeLivres";







    }



}
