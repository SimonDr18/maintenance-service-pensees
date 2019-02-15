import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import accesseur.PenseeDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import modele.Pensee;
import outils.Journal;

public class ControleurInspiration implements Initializable {
	protected PenseeDAO penseeDAO = new PenseeDAO();

	public ControleurInspiration()
	{
		List<Pensee> listePensees = penseeDAO.listerPensees();

		for(Iterator<Pensee> visiteur = listePensees.iterator(); visiteur.hasNext(); )
		{
			Pensee pensee = visiteur.next();
			Journal.ecrire(5, pensee.getMessage() + "(" + pensee.getAuteur() + ")");
		}
		ControleurInspiration.instance = this;
	}
	protected static ControleurInspiration instance = null;

	public static ControleurInspiration getInstance() {return ControleurInspiration.instance;}

	@FXML protected void trouverInspiration() {
		List<Pensee> list = penseeDAO.listerPensees();
		Random rand = new Random();
		Pensee elementRandom = list.get(rand.nextInt(list.size()));
		VueInspiration.getInstance().ecrirePensee(elementRandom.getMessage()+" - "+elementRandom.getAuteur());
	}

	public List<String> initialiser()
	{
		List<String> list = VueInspiration.getInstance().afficherListePensees(penseeDAO);
		return list;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {


	}

}