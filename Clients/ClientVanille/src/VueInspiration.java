import accesseur.cache.PenseeDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.Pensee;

import java.util.ArrayList;
import java.util.List;

public class VueInspiration extends Application {
	public VueInspiration()
	{
		VueInspiration.instance = this;
	}

	protected static VueInspiration instance = null;

	public List<String> afficherListePensees(PenseeDAO list){
		List<String> res = new ArrayList<>();
		for (Pensee a: list.listerPensees()) {
			String obj = a.getAuteur()+a.getMessage();
			res.add(obj);
		}
		return res;
	}

	public static VueInspiration getInstance() {
		return VueInspiration.instance;
	}
	
	protected Scene scene = null;

	@Override
	public void start(Stage stade) throws Exception {
		Parent racine = FXMLLoader.load(getClass().getResource("Inspiration.fxml"));
		scene = new Scene(racine, 800, 600);
		stade.setScene(scene);
		stade.show();
		ControleurInspiration.getInstance().initialiser();
	}

	public void ecrirePensee(String e)
	{
		TextArea ta = (TextArea) scene.lookup("TextArea");
		ta.setText(e);
	}
}
