package cd.esforca.web.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cd.esforca.web.model.Categorie;
import cd.esforca.web.model.Produit;
import cd.esforca.web.model.ProduitData;
import cd.esforca.web.service.CategorieService;
import cd.esforca.web.service.ProduitService;

import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/produits")
public class ProduitController {

    @Autowired
    private ProduitService service;

    @Autowired
    private CategorieService categorieService;

    @GetMapping("")
    public String index(Model model){
        Iterable<Produit> produits = service.getProduits();
        model.addAttribute("produits", produits);
        model.addAttribute("pageTitle", "Produits");
        return "produits/index";
    }

    @GetMapping("/createProduit")
	public String createProduit(Model model) {
		ProduitData p = new ProduitData();
        Iterable<Categorie> categories = categorieService.getCategories();

		model.addAttribute("produit", p);
        model.addAttribute("categories", categories);
        model.addAttribute("pageTitle", "Nouveau Produit");
        model.addAttribute("titre", "Ajout d'un nouveau produit");
		return "produits/produit";
	}

    @PostMapping("/saveProduit")
    public ModelAndView saveProduit(@ModelAttribute(name = "produit") ProduitData produitData){
        Produit p = produitData.getProduit();
        p.setCategorie(categorieService.getCategorie(produitData.getCodeCategorie()));
        System.out.println("Creation OK "+p.toString());
        service.saveProduit(p);
        return new ModelAndView("redirect:/produits");
    }

    @GetMapping("/updateProduit/{reference}")
	public String updateProduit(@PathVariable("reference") final String reference, Model model) {
		ProduitData p = new ProduitData(service.getProduit(reference));	
        Iterable<Categorie> categories = categorieService.getCategories();

		model.addAttribute("produit", p);
        model.addAttribute("categories", categories);
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