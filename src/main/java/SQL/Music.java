package SQL;

import java.sql.SQLException;

public class Music extends Oeuvre{

    public Music(String titre) throws SQLException, ClassNotFoundException {
        super(titre);
    }
}
