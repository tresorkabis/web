package cd.esforca.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cd.esforca.web.model.Categorie;
import cd.esforca.web.repository.CategorieProxy;
import lombok.Data;

@Data
@Service
public class CategorieService {

    @Autowired
    private CategorieProxy proxy;

    public Categorie getCategorie(final String code){
        return proxy.getCategorie(code);
    }

    public Iterable<Categorie> getCategories(){
        return proxy.getCategories();
    }

    public void deleteCategorie(final String code){
        proxy.deleteCategorie(code);
    }

    public Categorie saveCategorie(Categorie c){

        Categorie savedCategorie;

        c.setCode(c.getCode().toUpperCase());

        try {
            savedCategorie = proxy.getCategorie(c.getCode());    
            savedCategorie = proxy.updateCategorie(c);
        } catch (Exception e) {
            savedCategorie = proxy.createCategories(c);
        }

        return savedCategorie;
    }
}
