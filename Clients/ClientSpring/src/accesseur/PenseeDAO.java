package accesseur;

import modele.Pensee;
import modele.reponse.ReponseAjouterPensee;
import modele.reponse.ReponseListePensees;
import org.springframework.web.client.RestTemplate;

import java.util.List;

// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html
public class PenseeDAO {
	
	protected RestTemplate service;
	public PenseeDAO()
	{
		service = new RestTemplate();
	}
	
	public List<Pensee> listerPensees()
	{
		ReponseListePensees reponse = service.getForObject("http://localhost/inspiration/src/pensee/liste/", ReponseListePensees.class);
		System.out.println("La reponse indique " + reponse.getNombre() + " objets");
		
		List<Pensee> listePensees = reponse.getListePensees();
		System.out.println("La reponse a " + listePensees.size() + " objets");
				
		return listePensees;
	}
	
	public void ajouterPensee(Pensee pensee)
	{	
		ReponseAjouterPensee reponse = new ReponseAjouterPensee();
		reponse.setPensee(pensee);
	
		reponse = service.postForObject("http://localhost/inspiration/src/pensee/ajouter/", reponse, ReponseAjouterPensee.class);
		System.out.println("Ajout " + reponse.getSucces() );
	}
	

}
