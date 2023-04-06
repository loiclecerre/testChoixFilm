package adminCMD;

import SQL.Connexion;

import java.sql.SQLException;

public class ControleurAdminCMD {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        AdminFenetre adminFenetre = new AdminFenetre(new Connexion());
        adminFenetre.lancement();
    }
}