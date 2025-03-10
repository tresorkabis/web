package cd.esforca.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cd.esforca.web.model.Categorie;
import cd.esforca.web.service.CategorieService;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/categories")
public class CategorieController {

    @Autowired
    private CategorieService service;

    @GetMapping("")
    public String index(Model model){
        Iterable<Categorie> categories = service.getCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("pageTitle", "Categories");
        return "categories/index";
    }

    @GetMapping("/createCategorie")
	public String createCategorie(Model model) {
		Categorie c = new Categorie();
		model.addAttribute("categorie", c);
        model.addAttribute("pageTitle", "Nouvelle Categorie");
        model.addAttribute("titre", "Ajout d'une nouvelle catégorie");
		return "categories/categorie";
	}

    @PostMapping("/saveCategorie")
    public ModelAndView saveCategorie(@ModelAttribute Categorie categorie){
        service.saveCategorie(categorie);
        return new ModelAndView("redirect:/categories");
    }

    @GetMapping("/updateCategorie/{code}")
	public String updateCategorie(@PathVariable("code") final String code, Model model) {
		Categorie c = service.getCategorie(code);	
		model.addAttribute("categorie", c);
        model.addAttribute("pageTitle", "Mise à jour d'une Categorie");	
        model.addAttribute("titre", "Mise à jour d'une catégorie");
		return "categories/categorie";		
	}

    @GetMapping("/deleteCategorie/{code}")
    public ModelAndView deleteCategorie(@PathVariable("code") final String code) {
        service.deleteCategorie(code);
        return new ModelAndView("redirect:/categories");
    }
    
}
