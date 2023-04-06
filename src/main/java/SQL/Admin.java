package SQL;

import java.sql.SQLException;

public class Admin extends Users{

    public Admin(String email, String name) throws SQLException, ClassNotFoundException {
        super(email, name);
    }

    public void setIdUsers(int idUsers) throws SQLException {
        executeUpdate("UPDATE users SET idUsers="+idUsers+" WHERE idUsers="+ this.getIdUsers() +";");
        this.idUsers=idUsers;
    }

    public void setEmail(String email) throws SQLException {
        executeUpdate("UPDATE users SET email='"+email+"'WHERE email="+this.email+";");
        this.email = email;
    }

    public void delete_Users () throws SQLException {
        executeUpdate("DELETE FROM users WHERE idUsers="+this.getIdUsers()+";");
    }

    public void delete_Film (String titre) throws SQLException {
        executeUpdate("DELETE FROM film WHERE titre="+titre+";");
    }

    ///A changer
    public void copyUsers (int idUsersCopy) throws SQLException {
        int idMax = Integer.parseInt(remplirChampsRequete1Int("SELECT MAX(idUsers) FROM users;"))+1;
        executeUpdate("INSERT INTO users (idUsers, name, email, age, photo, filmVu, filmAVoir, filmEnCours, filmAime) SELECT  " +
                idMax+", name, email, age, photo, filmVu, filmAVoir, filmEnCours, filmAime FROM users WHERE idUsers ="+idUsersCopy+";");
    }
}
