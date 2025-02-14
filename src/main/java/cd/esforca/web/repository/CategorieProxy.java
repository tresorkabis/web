package cd.esforca.web.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import cd.esforca.web.CustomProperties;
import cd.esforca.web.model.Categorie;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CategorieProxy {

    @Autowired
    private CustomProperties props;

    public Iterable<Categorie> getCategories(){
        String baseApiUrl = props.getApiUrl();
        String getCategoriesUrl = baseApiUrl + "/categories";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Iterable<Categorie>> response = restTemplate.exchange(
            getCategoriesUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Iterable<Categorie>>() {}
            );

        return response.getBody();
    }

    public Categorie createCategories(Categorie c){
        String baseApiUrl = props.getApiUrl();
        String createCategoriesUrl = baseApiUrl + "/categories";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Categorie> request = new HttpEntity<Categorie>(c);
        ResponseEntity<Categorie> response = restTemplate.exchange(
            createCategoriesUrl,
            HttpMethod.POST,
            request,
            Categorie.class
            );
        log.debug("Creation OK "+response.getStatusCode().toString());
        return response.getBody();
    }

    public Categorie getCategorie(String code) {
		String baseApiUrl = props.getApiUrl();
		String getCategorieUrl = baseApiUrl + "/categories/" + code;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Categorie> response = restTemplate.exchange(
				getCategorieUrl, 
				HttpMethod.GET, 
				null,
				Categorie.class
			);
		
		log.debug("Get Categorie call " + response.getStatusCode().toString());
		
		return response.getBody();
	}

    public Categorie updateCategorie(Categorie c) {
		String baseApiUrl = props.getApiUrl();
		String updateCategorieUrl = baseApiUrl + "/categories/" + c.getCode();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Categorie> request = new HttpEntity<Categorie>(c);
		ResponseEntity<Categorie> response = restTemplate.exchange(
				updateCategorieUrl, 
				HttpMethod.PUT, 
				request, 
				Categorie.class);
		
		log.debug("Update Categorie call " + response.getStatusCode().toString());
		
		return response.getBody();
	}

    public void deleteCategorie(String code) {
		String baseApiUrl = props.getApiUrl();
		String deleteCategorieUrl = baseApiUrl + "/categories/" + code;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteCategorieUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
		log.debug("Delete Categorie call " + response.getStatusCode().toString());
	}
}
