package cd.esforca.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cd.esforca.web.model.Produit;
import cd.esforca.web.service.ProduitService;

import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/produits")
public class ProduitController {

    @Autowired
    private ProduitService service;

    @GetMapping("")
    public String index(Model model){
        Iterable<Produit> produits = service.getProduits();
        model.addAttribute("produits", produits);
        model.addAttribute("pageTitle", "Produits");
        return "produits/index";
    }

    @GetMapping("/createProduit")
	public String createProduit(Model model) {
		Produit p = new Produit();
		model.addAttribute("produit", p);
        model.addAttribute("pageTitle", "Nouveau Produit");
        model.addAttribute("titre", "Ajout d'un nouveau produit");
		return "produits/produit";
	}

    @PostMapping("/saveProduit")
    public ModelAndView saveProduit(@ModelAttribute Produit produit){
        service.saveProduit(produit);
        return new ModelAndView("redirect:/produits");
    }

    @GetMapping("/updateProduit/{reference}")
	public String updateProduit(@PathVariable("reference") final String reference, Model model) {
		Produit p = service.getProduit(reference);	
		model.addAttribute("produit", p);
        model.addAttribute("pageTitle", "Mise à jour d'un produit");	
        model.addAttribute("titre", "Mise à jour d'un produit");
		return "produits/produit";		
	}

    @GetMapping("/deleteProduit/{reference}")
    public ModelAndView deleteProduit(@PathVariable("reference") final String reference) {
        service.deleteProduit(reference);
        return new ModelAndView("redirect:/produits");
    }
    
}