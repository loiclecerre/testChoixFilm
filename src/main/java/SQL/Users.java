package SQL;

import javax.swing.*;
import java.sql.SQLException;

public class Users extends Connexion {
    protected int idUsers;
    protected String name;
    protected String email;
    protected String motDePasse;
    protected Double age;
    protected String filmVu;
    protected String filmAVoir;
    protected String filmEnCours;
    protected String filmAime;
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
        this.filmVu = remplirChampsRequete1String("SELECT filmVu FROM users WHERE idUsers='"+this.idUsers+"';");
        this.filmAVoir = remplirChampsRequete1String("SELECT filmAVoir FROM users WHERE idUsers='"+this.idUsers+"';");
        this.filmEnCours = remplirChampsRequete1String("SELECT filmEnCours FROM users WHERE idUsers='"+this.idUsers+"';");
        this.filmAime = remplirChampsRequete1String("SELECT filmAime FROM users WHERE idUsers='"+this.idUsers+"';");
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

    public String getFilmVu() {
        return filmVu;
    }

    public String getFilmAVoir() {
        return filmAVoir;
    }

    public String getFilmEnCours() {
        return filmEnCours;
    }

    public String getFilmAime() {
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

    public void setfilmVu(String filmVu) throws SQLException {
        executeUpdate("UPDATE users SET filmVu='"+ filmVu +"'WHERE idUsers="+this.idUsers+";");
        this.filmVu = filmVu;
    }

    public void setfilmAVoir(String filmAVoir) throws SQLException {
        executeUpdate("UPDATE film SET filmAVoir='"+ filmAVoir +"'WHERE idUsers="+this.idUsers+";");
        this.filmAVoir = filmAVoir;
    }

    public void setfilmEnCours(String filmEnCours) throws SQLException {
        executeUpdate("UPDATE users SET filmEnCours='"+ filmEnCours +"'WHERE idUsers="+this.idUsers+";");
        this.filmEnCours = filmEnCours;
    }

    public void setfilmAime(String filmAime) throws SQLException {
        executeUpdate("UPDATE users SET filmAime='"+ filmAime +"'WHERE idUsers="+this.idUsers+";");
        this.filmAime = filmAime;
    }

    public void setPhoto(ImageIcon photo) throws SQLException {
        executeUpdate("UPDATE users SET photo='"+photo+"'WHERE idUsers="+this.photo+";");
        this.photo = new ImageIcon("Images/"+photo);
    }
}
