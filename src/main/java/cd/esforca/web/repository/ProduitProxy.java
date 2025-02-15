package cd.esforca.web.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import cd.esforca.web.CustomProperties;
import cd.esforca.web.model.Produit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProduitProxy {

    @Autowired
    private CustomProperties props;

    public Iterable<Produit> getProduits(){
        String baseApiUrl = props.getApiUrl();
        String getProduitsUrl = baseApiUrl + "/produits";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Iterable<Produit>> response = restTemplate.exchange(
            getProduitsUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Iterable<Produit>>() {}
            );

        return response.getBody();
    }

    public Produit createProduits(Produit p){
        String baseApiUrl = props.getApiUrl();
        String createProduitsUrl = baseApiUrl + "/produits";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Produit> request = new HttpEntity<Produit>(p);
        ResponseEntity<Produit> response = restTemplate.exchange(
            createProduitsUrl,
            HttpMethod.POST,
            request,
            Produit.class
            );
        log.debug("Creation OK "+response.getStatusCode().toString());
        return response.getBody();
    }

    public Produit getProduit(String reference) {
		String baseApiUrl = props.getApiUrl();
		String getProduitUrl = baseApiUrl + "/produits/" + reference;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Produit> response = restTemplate.exchange(
				getProduitUrl, 
				HttpMethod.GET, 
				null,
				Produit.class
			);
		
		log.debug("Get Produit call " + response.getStatusCode().toString());
		
		return response.getBody();
	}

    public Produit updateProduit(Produit p) {
		String baseApiUrl = props.getApiUrl();
		String updateProduitUrl = baseApiUrl + "/produits/" + p.getReference();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Produit> request = new HttpEntity<Produit>(p);
		ResponseEntity<Produit> response = restTemplate.exchange(
				updateProduitUrl, 
				HttpMethod.PUT, 
				request, 
				Produit.class);
		
		log.debug("Update Produit call " + response.getStatusCode().toString());
		
		return response.getBody();
	}

    public void deleteProduit(String reference) {
		String baseApiUrl = props.getApiUrl();
		String deleteProduitUrl = baseApiUrl + "/produits/" + reference;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteProduitUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
		log.debug("Delete Produit call " + response.getStatusCode().toString());
	}
}
