import java.sql.*;
import java.util.Iterator;
import java.util.List;
import accesseur.cache.PenseeDAO;
import modele.Pensee;
import outils.Journal;

public class App {

	public static void main(String[] args) {

		PenseeDAO cachePenseeDAO = new PenseeDAO();
		cachePenseeDAO.listerPensees();

		//Journal.activer();
		Journal.activerNiveau(0);

		VueInspiration.launch(VueInspiration.class, args);

		cachePenseeDAO.ajouterPensee(new Pensee("Simon","Hello"));

	}
	

}

