package cd.esforca.web.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cd.esforca.web.model.Categorie;
import cd.esforca.web.model.Produit;
import cd.esforca.web.repository.CategorieProxy;
import cd.esforca.web.service.CategorieService;
import cd.esforca.web.service.ProduitService;

@Controller
public class HomeController {

    @Autowired
    private CategorieService categorieService;

    @Autowired
    private ProduitService produitService;

    @GetMapping("/")
    public String home(Model model){
        int nc = 0, np = 0;

        Iterable<Categorie> categories = categorieService.getCategories();
        nc = ((Collection<?>) categories).size();

        Iterable<Produit> produits = produitService.getProduits();
        np = ((Collection<?>) produits).size();


        model.addAttribute("nc", nc);
        model.addAttribute("np", np);
        model.addAttribute("pageTitle", "Dashboard");
        return "home";
    }
}
