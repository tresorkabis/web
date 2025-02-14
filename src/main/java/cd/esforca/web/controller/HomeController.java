package cd.esforca.web.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cd.esforca.web.model.Categorie;
import cd.esforca.web.repository.CategorieProxy;

@Controller
public class HomeController {

    @Autowired
    private CategorieProxy categorieProxy;

    @GetMapping("/")
    public String home(Model model){
        int nc = 0;
        Iterable<Categorie> data = categorieProxy.getCategories();

        if (data instanceof Collection) {
            nc = ((Collection<?>) data).size();
        }

        model.addAttribute("nc", nc);
        return "home";
    }
}
