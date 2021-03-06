import accesseur.cache.PenseeDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import modele.Pensee;

import java.net.URL;
import java.util.ResourceBundle;

public class ControleurInspiration implements Initializable {
	protected PenseeDAO penseeDAO = new PenseeDAO();

	public ControleurInspiration()
	{
		ControleurInspiration.instance = this;
	}

	protected static ControleurInspiration instance = null;

	public static ControleurInspiration getInstance() {return ControleurInspiration.instance;}

	@FXML protected void trouverInspiration() {
		Pensee pensee = penseeDAO.chargerPenseeAleatoire();
		VueInspiration.getInstance().ecrirePensee(pensee.getMessage()+" - "+pensee.getAuteur()+'('+pensee.getSource()+')');
	}

	public void initialiser()
	{
		Pensee pensee = penseeDAO.chargerPenseeAleatoire();
		VueInspiration.getInstance().ecrirePensee(pensee.getMessage()+" - "+pensee.getAuteur()+'('+pensee.getSource()+')');
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {


	}

}