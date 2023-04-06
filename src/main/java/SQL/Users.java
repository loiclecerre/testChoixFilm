package SQL;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Users extends Connexion {
    protected int idUsers;
    protected String name;
    protected String email;
    protected String motDePasse;
    protected Double age;
    protected ArrayList<String> filmVu;
    protected ArrayList<String> filmAVoir;
    protected ArrayList<String> filmEnCours;
    protected ArrayList<String> filmAime;
    protected boolean admin;
    protected ImageIcon photo;

    public Users (String email, String name) throws SQLException, ClassNotFoundException {
        super();

        ///Chargement des données utiles depuis la table compte
        this.email = remplirChampsRequete1String("SELECT email FROM compte WHERE email='"+email+"';");
        this.motDePasse = remplirChampsRequete1String("SELECT motDePasse FROM compte WHERE email='"+email+"';");
        this.admin = remplirChampsRequete1Boolean("SELECT admin FROM compte WHERE email='"+email+"';");

        ///Chargement des données utiles depuis la table users
        this.idUsers = Integer.parseInt(remplirChampsRequete1Int("SELECT idUsers FROM users WHERE name='"+name+"' and email='"+email+"';"));
        this.name = remplirChampsRequete1String("SELECT name FROM users WHERE name='"+name+"' and email='"+email+"';");
        this.age = Double.parseDouble(remplirChampsRequete1Int("SELECT age FROM users WHERE idUsers='"+this.idUsers+"';"));
        this.photo = new ImageIcon("Images/"+remplirChampsRequete1String("SELECT photo FROM users WHERE idUsers='"+this.idUsers+"';"));
        this.filmVu = remplirChampsRequete("SELECT filmVu FROM users WHERE idUsers='"+this.idUsers+"';");
        this.filmAVoir = remplirChampsRequete("SELECT filmAVoir FROM users WHERE idUsers='"+this.idUsers+"';");
        this.filmEnCours = remplirChampsRequete("SELECT filmEnCours FROM users WHERE idUsers='"+this.idUsers+"';");
        this.filmAime = remplirChampsRequete("SELECT filmAime FROM users WHERE idUsers='"+this.idUsers+"';");
    }

    public int getIdUsers() {
        return idUsers;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public Double getAge() {
        return age;
    }

    public ArrayList<String> getFilmVu() {
        return filmVu;
    }

    public ArrayList<String> getFilmAVoir() {
        return filmAVoir;
    }

    public ArrayList<String> getFilmEnCours() {
        return filmEnCours;
    }

    public ArrayList<String> getFilmAime() {
        return filmAime;
    }

    public void setName(String name) throws SQLException {
        executeUpdate("UPDATE users SET name='"+name+"'WHERE idUsers="+this.idUsers+";");
        this.name = name;
    }

    public void setMotDePasse(String motDePasse) throws SQLException {
        executeUpdate("UPDATE users SET motDePasse="+motDePasse+"'WHERE idUsers="+this.idUsers+";");
        this.motDePasse = motDePasse;
    }

    public void setAge(Double age) throws SQLException {
        executeUpdate("UPDATE users SET age='"+age+" WHERE idUsers="+this.idUsers+";");
        this.age = age;
    }

    public void setfilmVu(ArrayList<String> filmVu) throws SQLException {
        executeUpdate("UPDATE users SET filmVu='"+ Connexion.concatenateArrayList(filmVu)+"'WHERE idUsers="+this.idUsers+";");
        this.filmVu = filmVu;
    }

    public void setfilmAVoir(ArrayList<String> filmAVoir) throws SQLException {
        executeUpdate("UPDATE film SET filmAVoir='"+ Connexion.concatenateArrayList(filmAVoir)+"'WHERE idUsers="+this.idUsers+";");
        this.filmAVoir = filmAVoir;
    }

    public void setfilmEnCours(ArrayList<String> filmEnCours) throws SQLException {
        executeUpdate("UPDATE users SET filmEnCours='"+ Connexion.concatenateArrayList(filmEnCours)+"'WHERE idUsers="+this.idUsers+";");
        this.filmEnCours = filmEnCours;
    }

    public void setfilmAime(ArrayList<String> filmAime) throws SQLException {
        executeUpdate("UPDATE users SET filmAime='"+ Connexion.concatenateArrayList(filmAime)+"'WHERE idUsers="+this.idUsers+";");
        this.filmAime = filmAime;
    }

    public void setPhoto(ImageIcon photo) throws SQLException {
        executeUpdate("UPDATE users SET photo='"+photo+"'WHERE idUsers="+this.photo+";");
        this.photo = new ImageIcon("Images/"+photo);
    }

    public void display_Users() {

        System.out.println("ID : " + idUsers);
        System.out.println("admin : " + admin);
        System.out.println("name : " + name);
        System.out.println("email : " + email);
        System.out.println("motDePasse : " + motDePasse);
        System.out.println("age : " + age);
        System.out.print("filmVu : ");
        for(String list1 : filmVu)
            System.out.print(list1);
        System.out.println();
        System.out.print("filmAVoir : ");
        for(String list1 : filmAVoir)
            System.out.print(list1);
        System.out.println();
        System.out.print("filmEnCours : ");
        for(String list1 : filmEnCours)
            System.out.print(list1);
        System.out.println();
        System.out.print("filmAime : ");
        for(String list1 : filmAime)
            System.out.print(list1);
        System.out.println();
        System.out.println();
    }
}
