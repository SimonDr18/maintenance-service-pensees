import accesseur.PenseeDAO;
import modele.Pensee;

import java.util.List;

public class App {

	public static void main(String[] args) {

		PenseeDAO penseeDAO = new PenseeDAO();
		List<Pensee> listePensees = penseeDAO.listerPensees();
	
		for(Pensee pensee : listePensees)
		{
			System.out.println(pensee.getMessage());
		}
		
		Pensee pensee = new Pensee();
		penseeDAO.ajouterPensee(pensee);
	}

}
