package org.example;

import SQL.Connexion;
import SQL.Users;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;

public class Test extends JFrame {

    private JPanel affichePanel1;
    private JPanel affichePanel2;
    private JPanel affichePanel3;
    private JPanel searchPanel;
    private JTextField searchField;
    private JButton searchButton;
    
    ///Compteur pour afficher les afficheFilm
    private int compteurPanel1;
    private int maxSizePanel1;
    private int compteurPanel2;
    private int maxSizePanel2;
    private int compteurPanel3;
    private int maxSizePanel3;

    Connexion maConnexion;
    Users monUsers;

    public Test(Users monUsers) throws SQLException, ClassNotFoundException {
        super("Neteceflix");

        maConnexion = new Connexion();
        this.monUsers = monUsers;

        creationBarreDeRecherche();
        creationAffichePanel();

        ///Créer les 3 panels : liste, enCours, action
        File folder = new File("afficheFilm");
        File[] allAfficheFilm = folder.listFiles();
        int count =0;
        assert allAfficheFilm != null;
        for (File afficheFilm : allAfficheFilm) {
            try {
                //verif film liste, en cours, action
                insertAfficheAndBoutonToPanel(count, afficheFilm);
            } catch (IOException ex) {
                System.out.println("Erreur de lecture de l'image : " + ex.getMessage());
            }
        }
        // Ajouter les panels dans la fenetre
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(searchPanel);
        add(affichePanel1);
        add(affichePanel2);
        add(affichePanel3);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    
    public void creationBarreDeRecherche(){
        // Ajouter une barre de recherche
        this.searchPanel = new JPanel();
        this.searchField = new JTextField(20);
        this.searchButton = new JButton("Rechercher");
        searchPanel.setBackground(Color.BLACK);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
    }
    
    public void creationAffichePanel(){
        // Ajouter les images dans une grille 1x3
        this.affichePanel1 = new JPanel(new GridLayout(1, 3, 0, 0));
        this.affichePanel2 = new JPanel(new GridLayout(1, 3, 0, 0));
        this.affichePanel3 = new JPanel(new GridLayout(1, 3, 0, 0));

        affichePanel1.setBackground(Color.BLACK);
        affichePanel2.setBackground(Color.BLACK);
        affichePanel3.setBackground(Color.BLACK);

        this.compteurPanel1=0;
        this.compteurPanel2=0;
        this.compteurPanel3=0;

        this.maxSizePanel1=0;
        this.maxSizePanel2=0;
        this.maxSizePanel3=0;
    }

    private ArrayList<File> filtrageFilm (ArrayList<String> filmSelectioné)
    {
        ArrayList<File> fileSelectioné = monUsers.getFilmAVoir();

        File folder = new File("afficheFilm");
        File[] allAfficheFilm = folder.listFiles();
        assert allAfficheFilm != null;

        for(File files: allAfficheFilm)
        {
            try {
                for(String filmSelec : filmSelectioné)
                {
                    if()
                }
            } catch (IOException ex) {
                System.out.println("Erreur de lecture de l'image : " + ex.getMessage());
            }
        }

    }

    public void insertAfficheAndBoutonToPanel(int count, File afficheFilm) throws IOException {
        Image img = ImageIO.read(afficheFilm);
        JLabel imgLabel = new JLabel(new ImageIcon(img));
        imgLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // Ajouter le titre prédéfini sous chaque image
        String title = afficheFilm.getName().replace(".png", ""); // utiliser le nom du fichier sans l'extension
        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER); // centrer le texte/
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(imgLabel, BorderLayout.CENTER);
        //panel.add(titleLabel, BorderLayout.SOUTH);
        panel.setBackground(Color.BLACK);


        // Ajouter l'action pour ouvrir une fenêtre avec le titre du film
        imgLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JFrame frame = new JFrame(title);
                frame.setSize(400, 400);
                frame.setVisible(true);
            }
        });

        if(count<3) {
            if(count == 0) {
                // Ajouter l'image avant le panel d'image
                ImageIcon beforeIcon = new ImageIcon("imagesFilm/fleche_gauche.png");
                JLabel beforeLabel = new JLabel(beforeIcon);
                beforeLabel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if(compteurPanel1>0)
                            compteurPanel1--;
                    }
                });
                panel.add(beforeLabel, BorderLayout.WEST);
            }
            else if (count == 2) {
                ImageIcon beforeIcon = new ImageIcon("imagesFilm/fleche_droite.png");
                JLabel beforeLabel = new JLabel(beforeIcon);
                beforeLabel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if(compteurPanel1>0)
                            compteurPanel1++;
                    }
                });
                panel.add(beforeLabel, BorderLayout.EAST);
            }
            affichePanel1.add(panel);
        }
        else if(count<6) {
            if(count == 3) {
                // Ajouter l'image avant le panel d'image
                ImageIcon beforeIcon = new ImageIcon("imagesFilm/fleche_gauche.png");
                JLabel beforeLabel = new JLabel(beforeIcon);
                beforeLabel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("click3");
                    }
                });
                panel.add(beforeLabel, BorderLayout.WEST);
            }
            else if (count ==5) {
                ImageIcon beforeIcon = new ImageIcon("imagesFilm/fleche_droite.png");
                JLabel beforeLabel = new JLabel(beforeIcon);
                beforeLabel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("click4");
                    }
                });
                panel.add(beforeLabel, BorderLayout.EAST);
            }
            affichePanel2.add(panel);
        }
        else if (count<9) {
            if(count == 6) {
                ImageIcon beforeIcon = new ImageIcon("imagesFilm/fleche_gauche.png");
                JLabel beforeLabel = new JLabel(beforeIcon);
                beforeLabel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("click5");
                    }
                });
                panel.add(beforeLabel, BorderLayout.WEST);
            }
            else if (count ==8) {
                // Ajouter l'image avant le panel d'image
                ImageIcon beforeIcon = new ImageIcon("imagesFilm/fleche_droite.png");
                JLabel beforeLabel = new JLabel(beforeIcon);
                beforeLabel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("click6");
                    }
                });
                panel.add(beforeLabel, BorderLayout.EAST);
            }
            affichePanel3.add(panel);
        }
        else {
            break;
        }
        count++;
    }

    public static void main(String[] args) {
        Test fenetre = new Test();
    }
}

