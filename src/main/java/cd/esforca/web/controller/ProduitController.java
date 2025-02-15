package cd.esforca.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import cd.esforca.web.model.Produit;
import cd.esforca.web.service.ProduitService;

@Controller
public class ProduitController {

    @Autowired
    private ProduitService service;

    @GetMapping("/produits")
    public String index(Model model){
        Iterable<Produit> produits = service.getProduits();
        model.addAttribute("produits", produits);
        return "produits/index";
    }

    @GetMapping("/createProduit")
	public String createProduit(Model model) {
		Produit p = new Produit();
		model.addAttribute("produit", p);
		return "produits/create";
	}

    @PostMapping("/saveProduit")
    public ModelAndView saveProduit(@ModelAttribute Produit produit){
        System.out.println(produit.toString());
        service.saveProduit(produit, "New");
        return new ModelAndView("redirect:/produits");
    }

    @GetMapping("/updateProduit/{code}")
	public String updateProduit(@PathVariable("code") final String reference, Model model) {
		Produit p = service.getProduit(reference);		
		model.addAttribute("produit", p);	
		return "produits/edit";		
	}

    @GetMapping("/deleteProduit/{reference}")
    public ModelAndView deleteProduit(@PathVariable("reference") final String reference) {
        service.deleteProduit(reference);
        return new ModelAndView("redirect:/produits");
    }
}
