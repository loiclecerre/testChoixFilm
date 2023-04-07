package SQL;

import java.sql.*;
import java.util.ArrayList;

public class Connexion {

    private final Statement stmt;
    private ResultSet rset;
    private ResultSetMetaData rsetMeta;

    public Connexion() throws SQLException, ClassNotFoundException{
        // chargement driver "com.mysql.jdbc.Driver"
        Class.forName("com.mysql.jdbc.Driver");

        String urlDatabase = "jdbc:mysql://localhost:3306/netflix";
        //création d'une connexion JDBC à la base
        Connection conn = DriverManager.getConnection(urlDatabase, "root", "");

        // création d'un ordre SQL (statement)
        stmt = conn.createStatement();

    }

    public ArrayList remplirChampsTable(String table) throws SQLException {
        // récupération de l'ordre de la requete
        //rset = stmt.executeQuery("select * from " + table);
        rset = stmt.executeQuery(table);

        // récupération du résultat de l'ordre
        rsetMeta = rset.getMetaData();

        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();

        // creation d'une ArrayList de String
        ArrayList<String> liste;
        liste = new ArrayList<>();
        StringBuilder champs = new StringBuilder();
        // Ajouter tous les champs du resultat dans l'ArrayList
        for (int i = 0; i < nbColonne; i++) {
            champs.append(" ").append(rsetMeta.getColumnLabel(i + 1));
        }

        // ajouter un "\n" à la ligne des champs
        champs.append("\n");

        // ajouter les champs de la ligne dans l'ArrayList
        liste.add(champs.toString());

        // Retourner l'ArrayList
        return liste;
    }

    public ArrayList<String> remplirChampsRequete(String requete) throws SQLException {
        // récupération de l'ordre de la requete
        rset = stmt.executeQuery(requete);

        // récupération du résultat de l'ordre
        rsetMeta = rset.getMetaData();

        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();

        // creation d'une ArrayList de String
        ArrayList<String> liste;
        liste = new ArrayList<>();
        int i;
        // tant qu'il reste une ligne
        while (rset.next()) {
            StringBuilder champs;
            champs = new StringBuilder(rset.getString(1)); // ajouter premier champ

            // Concatener les champs de la ligne séparés par
            for (i = 1; i < nbColonne; i++) {
                champs.append(", ").append(rset.getString(i + 1));
            }

            // ajouter un "\n" à la ligne des champs
            //champs = champs + "\n";

            // ajouter les champs de la ligne dans l'ArrayList
            liste.add(champs.toString());
        }

        // Retourner l'ArrayList
        return liste;
    }

    public void executeUpdate(String requeteMaj) throws SQLException {
        stmt.executeUpdate(requeteMaj);
    }

    public String remplirChampsRequeteString(String requete) throws SQLException {
        rset = stmt.executeQuery(requete);
        String s = "";
        while (rset.next())
        {
            s = s + rset.getString(1) +", ";
        }
        return s;
    }

    public String remplirChampsRequete1String(String requete) throws SQLException {
        // récupération de l'ordre de la requete
        rset = stmt.executeQuery(requete);
        rset.next();
        return rset.getString(1);
    }

    public String remplirChampsRequete1Int(String requete) throws SQLException {
        // récupération de l'ordre de la requete
        rset = stmt.executeQuery(requete);
        rset.next();
        return rset.getString(1);
    }

    public Boolean remplirChampsRequete1Boolean(String requete) throws SQLException {
        // récupération de l'ordre de la requete
        rset = stmt.executeQuery(requete);
        rset.next();
        return rset.getBoolean("admin");
    }

    public static String concatenateArrayList(ArrayList<String> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(";");
            }
        }
        return sb.toString();
    }

    public boolean isSubstring(String mainString, String subString) {
        return mainString.contains(subString);
    }

    //Si email deja présente return false
    public boolean verifEmailValidity(String email) throws SQLException {
        rset = stmt.executeQuery("SELECT COUNT(*) AS count FROM compte WHERE email = '"+email+"';");
        rset.next();
        int count = rset.getInt("count");
        return count == 0;
    }

    public boolean verifNameValidity(String email, String name) throws SQLException {
        rset = stmt.executeQuery("SELECT COUNT(*) AS count FROM users WHERE email = '"+email+"' and name ='"+name+"';");
        rset.next();
        int count = rset.getInt("count");
        return count == 0;
    }

    public void createCompte (String email, String motDePasse, String name, int age) throws SQLException {
        executeUpdate("insert into compte" +
                "(email, motDePasse, admin, nombreUsers) values" +
                "('"+email+"', '"+motDePasse+"', 0, 1)");
        createUsers(email, name, age);
    }

    public void createUsers (String email, String name, int age) throws SQLException {
        executeUpdate("insert into users" +
                "(email, name, age) values" +
                "('"+email+"', '"+name+"', "+age+" )");
    }

    public ArrayList<Film> loadALlFilm () throws SQLException, ClassNotFoundException {
        ArrayList<String> nomFilm;
        ArrayList<Film> film = new ArrayList<>();

        nomFilm = remplirChampsRequete("SELECT titre FROM film;");

        for(String s : nomFilm)
            film.add(new Film(s));
        return film;
    }
}
