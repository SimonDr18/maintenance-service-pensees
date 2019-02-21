package accesseur.cache;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import accesseur.PenseeURL;
import modele.DecodeurPenseesXML;
import modele.Pensee;
import outils.Journal;
import outils.JournalDesactivable;

public class PenseeDAO implements PenseeURL {

    protected DecodeurPenseesXML decodeur = new DecodeurPenseesXML();

    private String DSN = "jdbc::sqlite:D:\\MySQLStudio/inspiration.db";
    private String SQL_ENR = "insert into pensee(auteur,message) values (?,?) ";
    private String SQL_LIST = "Select * from pensee";

    public List<Pensee> listerPensees()
    {
        JournalDesactivable.ecrire("listerPensees()");

        List<Pensee> listePensee = new ArrayList<>();
        Connection bd = null;
        try{
            bd = DriverManager.getConnection(DSN);
            Statement rqList = bd.createStatement();
            ResultSet resList = rqList.executeQuery(SQL_LIST);
            if (resList==null)
                return listePensee;
            while (resList.next()){
                String auteur = resList.getString("auteur");
                String message = resList.getString("message");
                Pensee pensee = new Pensee(auteur, message);
                listePensee.add(pensee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listePensee;
    }

    public void ajouterPensee(Pensee pensee)
    {
        Journal.ecrire(1, "ajouterPensee()");
        Connection bd = null;
        try{
            bd = DriverManager.getConnection(DSN);
            PreparedStatement reqEnr = bd.prepareStatement(SQL_ENR);

            reqEnr.setString(1,pensee.getAuteur());
            reqEnr.setString(2,pensee.getMessage());

            reqEnr.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Pensee chargerPenseeAleatoire()
    {
        JournalDesactivable.ecrire("listerPensees()");
        List<Pensee> listePensee = listerPensees();
        Random rand = new Random();
        Pensee elementRandom = listePensee.get(rand.nextInt(listePensee.size()));
        return elementRandom;
    }
}

/*
 *

Code de réponse 200
ajouterPensee()
stdClass Object
(
    [auteur] => Rossetti
    [message] => Ce qui est plus triste qu’une œuvre inachevée, c’est une œuvre jamais commencée.
    [annee] => 0
)
<?xml version="1.0" encoding="UTF-8"?><action>
	<type>ajouter</type>
	<moment>1523972018</moment>
	<succes>1</succes>
	<message>POST : Array
(
    [auteur] => Rossetti
    [message] => Ce qui est plus triste qu’une œuvre inachevée, c’est une œuvre jamais commencée.
    [annee] => 0
)
</message>
</action>

 *
 */
