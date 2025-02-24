package cd.esforca.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cd.esforca.web.model.Produit;
import cd.esforca.web.repository.ProduitProxy;
import lombok.Data;

@Data
@Service
public class ProduitService {

    @Autowired
    private ProduitProxy proxy;

    public Produit getProduit(final String reference){
        return proxy.getProduit(reference);
    }

    public Iterable<Produit> getProduits(){
        return proxy.getProduits();
    }

    public void deleteProduit(final String reference){
        proxy.deleteProduit(reference);
    }

    public Produit saveProduit(Produit p){

        Produit savedProduit;

        p.setReference(p.getReference().toUpperCase());
        
        savedProduit = proxy.getProduit(p.getReference());

        if(savedProduit.equals(null)){
            savedProduit = proxy.createProduits(p);
        }else{
            savedProduit = proxy.updateProduit(p);
        }

        return savedProduit;
    }
}
