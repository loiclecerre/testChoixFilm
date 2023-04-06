package SQL;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Film extends Oeuvre {
    private ArrayList<String> nomActeurs;
    private String summary;
    private String filmLocalisationPC;
    private boolean recompense;
    private double etoile;
    private String sous_titreLocalisationPC;
    private double vitesse;
    private int resolution;
    private boolean playPause;
    private String langue;

    public Film (String titre) throws SQLException, ClassNotFoundException {
        super(titre);

        this.nomActeurs = remplirChampsRequete("SELECT nomActeurs FROM film WHERE titre='"+titre+"';");
        this.summary = remplirChampsRequete1String("SELECT summary FROM film WHERE titre='"+titre+"';");
        this.filmLocalisationPC = remplirChampsRequete1String("SELECT filmLocalisationPC FROM film WHERE titre='"+titre+"';");
        this.recompense = Integer.parseInt(remplirChampsRequete1Int("SELECT recompense FROM film WHERE titre='"+titre+"';")) == 1;
        this.etoile = Double.parseDouble(remplirChampsRequete1Int("SELECT etoile FROM film WHERE titre='"+titre+"';"));
        this.sous_titreLocalisationPC = remplirChampsRequete1String("SELECT sous_titreLocalisationPC FROM film WHERE titre='"+titre+"';");
        this.vitesse=1;
        this.resolution=1080;
        this.playPause=true;//1=play 0=pause
        this.langue="francais";
    }

    public void setNomActeurs(ArrayList<String> nomActeurs) throws SQLException {
        executeUpdate("UPDATE film SET nomActeurs='"+ Connexion.concatenateArrayList(nomActeurs)+"' WHERE titre='"+titre+";");
        this.nomActeurs = nomActeurs;
    }

    public void setSummary(String summary) throws SQLException {
        executeUpdate("UPDATE film SET summary='"+summary+"' WHERE titre='"+titre+";");
        this.summary = summary;
    }

    public void setPhoto(String photoLocalisationPC) throws SQLException {
        executeUpdate("UPDATE film SET photo='"+photo+"' WHERE titre='"+titre+";");
        this.photo = new ImageIcon("Images/"+photo);
    }

    public void setFilmLocalisationPC(String filmLocalisationPC) throws SQLException {
        executeUpdate("UPDATE film SET filmLocalisationPC='"+filmLocalisationPC+"' WHERE titre='"+titre+";");
        this.filmLocalisationPC = filmLocalisationPC;
    }

    public void setRecompense(boolean recompense) throws SQLException {
        executeUpdate("UPDATE film SET recompense= "+recompense+" WHERE titre='"+titre+";");
        this.recompense = recompense;
    }

    public void setEtoile(double etoile) throws SQLException {
        executeUpdate("UPDATE film SET etoile="+etoile+" WHERE titre='"+titre+";");
        this.etoile = etoile;
    }

    public void setSous_titreLocalisationPC(String sous_titreLocalisationPC) throws SQLException {
        executeUpdate("UPDATE film SET sous_titreLocalisationPC='"+sous_titreLocalisationPC+"' WHERE titre='"+titre+";");
        this.sous_titreLocalisationPC = sous_titreLocalisationPC;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public void setPlayPause(boolean playPause) {
        this.playPause = playPause;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    ///getter
    public ArrayList<String> getNomActeurs() {
        return nomActeurs;
    }

    public String getSummary() {
        return summary;
    }

    public String getFilmLocalisationPC() {
        return filmLocalisationPC;
    }

    public boolean isRecompense() {
        return recompense;
    }

    public double getEtoile() {
        return etoile;
    }

    public String getSous_titreLocalisationPC() {
        return sous_titreLocalisationPC;
    }

    public double getVitesse() {
        return vitesse;
    }

    public int getResolution() {
        return resolution;
    }

    public boolean isPlayPause() {
        return playPause;
    }

    public String getLangue() {
        return langue;
    }

    public void display_Film() {
        System.out.println("titre : " + titre);
        System.out.println("annee : " + annee);
        System.out.println("duree : " + duree);
        System.out.print("categorie : ");
        for(String list1 : categorie)
            System.out.print(list1);
        System.out.println();
        System.out.print("nomRealisateur : ");
        for(String list1 : nomRealisateur)
            System.out.print(list1);
        System.out.println();
        System.out.print("nomActeurs : ");
        for(String list1 : nomActeurs)
            System.out.print(list1);
        System.out.println();
        System.out.println("summary : " + summary);
        System.out.println("filmLocalisationPC : " + filmLocalisationPC);
        System.out.println("recompense : " + recompense);
        System.out.println("etoile : " + etoile);
        System.out.println("sous_titreLocalisationPC : " + sous_titreLocalisationPC);
        System.out.println("vitesse : " + vitesse);
        System.out.println("resolution : " + resolution);
        System.out.println("play : " + playPause);
        System.out.println("langue : " + langue);
        System.out.println();
    }
}
