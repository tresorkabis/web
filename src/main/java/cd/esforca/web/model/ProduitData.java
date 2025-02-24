package cd.esforca.web.model;

import org.springframework.beans.factory.annotation.Autowired;

import cd.esforca.web.service.CategorieService;
import lombok.Data;

@Data
public class ProduitData {

    private String reference;

    private String designation;

    private Integer qte;

    private Integer prix;

    private String codeCategorie;


    public ProduitData() {
    }

    public ProduitData(Produit p) {
        this.reference = p.getReference();
        this.designation = p.getDesignation();
        this.qte = p.getQte();
        this.prix = p.getPrix();
        this.codeCategorie = p.getCategorie().getCode();
    }

    public Produit getProduit() {

        Produit p = new Produit();
        p.setReference(reference);
        p.setDesignation(designation);
        p.setQte(qte);
        p.setPrix(prix);
        p.setCategorie(new Categorie());

        return p;
    }

    @Override
    public String toString() {
        return "{" +
                " reference='" + getReference() + "'" +
                ", designation='" + getDesignation() + "'" +
                ", qte='" + getQte() + "'" +
                ", prix='" + getPrix() + "'" +
                ", codeCategorie='" + getCodeCategorie() + "'" +
                ", categorieService='" + getCategorieService() + "'" +
                "}";
    }

}
