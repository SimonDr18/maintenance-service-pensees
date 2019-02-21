package accesseur.cache;

import accesseur.PenseeURL;
import modele.DecodeurPenseesXML;
import modele.Pensee;
import outils.Journal;
import outils.JournalDesactivable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PenseeDAO implements PenseeURL {

    private String DSN = "jdbc::sqlite:D:\\MySQLStudio\\inspiration.db";
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

        } catch (SQLException  i) {
            i.printStackTrace();
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