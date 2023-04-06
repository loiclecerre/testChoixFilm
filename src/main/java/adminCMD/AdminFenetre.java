package adminCMD;

import SQL.Connexion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

public class AdminFenetre extends JPanel {
    private final JTextField queryField;
    private final JTextArea resultArea;
    private final ArrayList<String> resultRequete = new ArrayList<>();
    private final ArrayList<String> requeteColonne = new ArrayList<>();
    private final Connexion maConnexion;

    public AdminFenetre(Connexion maConnexion) {
        this.maConnexion = maConnexion;
        setLayout(new BorderLayout());

        // Créer le champ de texte pour la requête SQL
        queryField = new JTextField();
        queryField.setBackground(Color.BLACK);
        queryField.setForeground(Color.WHITE);
        queryField.setText("> ");

        queryField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resultArea.setEditable(true);
                resultArea.setText("");
                resultArea.setEditable(false);

                String requete = queryField.getText();
                requete = requete.replace("> ", "");

                try {
                    typeRequete(requete);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                queryField.setText("> ");
                requeteColonne.clear();
                resultRequete.clear();
            }
        });

        // Créer la zone de texte pour afficher les résultats de la requête SQL
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setBackground(Color.BLACK);
        resultArea.setForeground(Color.WHITE);
        resultArea.setText("Bienvenue dans l'invite de commandes Admin.\n\n");
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Ajouter les composants à la fenêtre
        add(queryField, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void lancement(){
        // Créer une nouvelle fenêtre pour la requête SQL
        JFrame frame = new JFrame("Invite de commandes Admin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);

        // Ajouter le panneau de requête SQL à la fenêtre
        AdminFenetre panel = new AdminFenetre(this.maConnexion);
        frame.getContentPane().add(panel);

        // Afficher la fenêtre
        frame.setVisible(true);
    }

    private void typeRequete (String requete) throws SQLException {
        String[] mots = requete.split(" ");
        mots[0] = mots[0].toUpperCase(Locale.ROOT);

        switch (mots[0]) {
            case "UPDATE" -> {
                resultArea.append("Affichage des données de la table associée avant la requête : " + requete + "\n\n");
                selectData("select * from " + mots[1].toUpperCase(Locale.ROOT));
                requeteColonne.clear();
                resultRequete.clear();

                maConnexion.executeUpdate(requete);

                resultArea.append("Affichage des données de la table associée après la requête : " + requete + "\n\n");
                selectData("select * from " + mots[1].toUpperCase(Locale.ROOT));
            }
            case "INSERT", "DELETE", "ALTER" -> {
                resultArea.append("Affichage des données de la table associée avant la requête : " + requete + "\n\n");
                selectData("select * from " + mots[2].toUpperCase(Locale.ROOT));
                requeteColonne.clear();
                resultRequete.clear();

                maConnexion.executeUpdate(requete);

                resultArea.append("Affichage des données de la table associée après la requête : " + requete + "\n\n");
                selectData("select * from " + mots[2].toUpperCase(Locale.ROOT));
            }
            case "SELECT" -> {
                resultArea.append("Affichage des données de la table associée après la requête : " + requete + "\n\n");
                selectData(requete);
            }
        }
    }

    private void selectData (String requete) throws SQLException {
        resultRequete.addAll(maConnexion.remplirChampsRequete(requete));
        requeteColonne.addAll(maConnexion.remplirChampsTable(requete));

        //Afficher chaque colonne en lien avec le resultat de la requete
        for (String item : requeteColonne) {
            resultArea.append(item+ " \n");
        }
        // Afficher chaque élément du resultat de la requete
        for (String item : resultRequete) {
            resultArea.append(item + " \n\n");
        }
        resultArea.append("\n\n");
    }
}