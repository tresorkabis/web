package cd.esforca.web.model;

import lombok.Data;

@Data
public class Produit {

    private String reference;
    
    private String designation;

    private Integer qte;

    private Integer prix;

    private Categorie categorie;
}
