package cd.esforca.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProduitController {

    @Autowired
    private ProduitService service;
}
