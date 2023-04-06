package SQL;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

abstract class Oeuvre extends Connexion {
    protected String titre;
    protected double annee;
    protected double duree;
    protected ArrayList<String> categorie;
    protected ArrayList<String> nomRealisateur;
    protected ImageIcon photo;
    protected int nombreDeVue;
    protected int watchTime;

    public Oeuvre(String titre) throws SQLException, ClassNotFoundException {
        super();
        this.titre = remplirChampsRequete1String("SELECT titre FROM oeuvre WHERE titre='"+titre+"';");
        this.annee = Double.parseDouble(remplirChampsRequete1Int("SELECT annee FROM oeuvre WHERE titre='"+titre+"';"));
        this.duree = Double.parseDouble(remplirChampsRequete1Int("SELECT duree FROM oeuvre WHERE titre='"+titre+"';"));
        this.categorie = remplirChampsRequete("SELECT categorie FROM oeuvre WHERE titre='"+titre+"';");
        this.nomRealisateur = remplirChampsRequete("SELECT nomRealisateur FROM oeuvre WHERE titre='"+titre+"';");
        this.photo = new ImageIcon("Images/"+remplirChampsRequete1String("SELECT photo FROM oeuvre WHERE titre='"+titre+"';"));
        this.nombreDeVue = Integer.parseInt(remplirChampsRequete1Int("SELECT nombreDeVue FROM oeuvre WHERE titre='"+titre+"';"));
        this.watchTime = Integer.parseInt(remplirChampsRequete1Int("SELECT watchTime FROM oeuvre WHERE titre='"+titre+"';"));
    }

    public String getTitre() {
        return titre;
    }

    public double getAnnee() {
        return annee;
    }

    public double getDuree() {
        return duree;
    }

    public ArrayList<String> getCategorie() {
        return categorie;
    }

    public ArrayList<String> getNomRealisateur() {
        return nomRealisateur;
    }

    public ImageIcon getPhoto() {
        return photo;
    }

    public int getNombreDeVue(){return nombreDeVue;}

    public int getWatchTime(){return watchTime;}

    public void setTitre(String titre) throws SQLException {
        executeUpdate("UPDATE film SET titre='"+titre+"' WHERE titre ="+this.titre +";");
        this.titre = titre;
    }

    public void setAnnee(double annee) throws SQLException {
        executeUpdate("UPDATE film SET annee="+annee+" WHERE titre ="+this.titre +";");
        this.annee = annee;
    }

    public void setDuree(double duree) throws SQLException {
        executeUpdate("UPDATE film SET duree="+duree+" WHERE titre ="+this.titre +";");
        this.duree = duree;
    }

    public void setCategorie(ArrayList<String> categorie) throws SQLException {
        executeUpdate("UPDATE film SET categorie='"+ Connexion.concatenateArrayList(categorie)+"' WHERE titre="+this.titre+";");
        this.categorie = categorie;
    }

    public void setNomRealisateur(ArrayList<String> nomRealisateur) throws SQLException {
        executeUpdate("UPDATE film SET nomRealisateur='"+ Connexion.concatenateArrayList(nomRealisateur)+"' WHERE titre="+this.titre+";");
        this.nomRealisateur = nomRealisateur;
    }

    public void setPhoto (String photo) throws SQLException {
        executeUpdate("UPDATE film SET photo='"+ photo+"' WHERE titre="+this.titre+";");
        this.photo=new ImageIcon("Images/"+remplirChampsRequete1String("SELECT photo FROM film WHERE titre='"+titre+"';"));
    }

    public void setNombreDeVue (int nombreDeVue) throws SQLException {
        executeUpdate("UPDATE film SET nombreDeVue="+nombreDeVue+" WHERE titre ="+this.titre +";");
        this.nombreDeVue = nombreDeVue;
    }

    public void setWatchTime (int watchTime) throws SQLException {
        executeUpdate("UPDATE film SET watchTime="+watchTime+" WHERE titre ="+this.titre +";");
        this.watchTime = watchTime;
    }
}
