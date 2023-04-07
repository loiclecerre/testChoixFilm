package SQL;

import java.sql.SQLException;
import java.util.ArrayList;

public class ControleurSQL {

    public static void main(String[] s) throws SQLException, ClassNotFoundException {
        Connexion c = new Connexion();
        ArrayList<Film> film = c.loadALlFilm();
        System.out.println(film.get(0).getNomActeurs());
        System.out.println(c.verifNameValidity("loic.lecerre@gmail.com", "Loic Lecerre"));
    }
}
