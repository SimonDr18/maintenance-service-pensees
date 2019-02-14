import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ControleurInspiration {

	@FXML protected void enregistrer(ActionEvent evenement) {
    	
    	System.out.println("enregistrer( )");
    	
    	// Singleton obligatoire car le framework de JavaFX cache l'instance
		VueInspiration.getInstance().ecrireUnMotDePasse("coucou");
		VueInspiration.getInstance().ecrireUnMessage("allo");
    }
	
}
