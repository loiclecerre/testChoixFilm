package mainPanel;

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

    private int compteurPanel1;
    private int maxSizePanel1;
    private int compteurPanel2;
    private int maxSizePanel2;
    private int compteurPanel3;
    private int maxSizePanel3;

    Connexion maConnexion;
    Users monUsers;

    public Test(Users monUsers) throws SQLException, ClassNotFoundException, IOException {
        super("Neteceflix");

        this.maConnexion = new Connexion();
        this.monUsers = monUsers;

        initialisationBarreDeRecherche();
        initialisationAffichePanel();

        creationPanel1();
        creationPanel2();
        creationPanel3();

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
    
    public void initialisationBarreDeRecherche(){
        // Ajouter une barre de recherche
        this.searchPanel = new JPanel();
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Rechercher");
        this.searchPanel.setBackground(Color.BLACK);
        this.searchPanel.add(searchField);
        this.searchPanel.add(searchButton);
    }
    
    public void initialisationAffichePanel(){
        // Ajouter les images dans une grille 1x3
        this.affichePanel1 = new JPanel();
        this.affichePanel2 = new JPanel();
        this.affichePanel3 = new JPanel();

        System.out.println(this.affichePanel1.getSize());

        this.affichePanel1.setBackground(Color.BLACK);
        this.affichePanel2.setBackground(Color.BLACK);
        this.affichePanel3.setBackground(Color.BLACK);

        ///Compteur pour afficher les afficheFilm
        this.compteurPanel1=0;
        this.compteurPanel2=0;
        this.compteurPanel3=0;

        this.maxSizePanel1=0;
        this.maxSizePanel2=0;
        this.maxSizePanel3=0;
    }

    public JLabel creationFlecheGaucheP1(){
        ImageIcon beforeIcon = new ImageIcon("imagesFilm/fleche_gauche.png");
        JLabel beforeLabel = new JLabel(beforeIcon);
        //beforeLabel.setIcon(null);
        beforeLabel.addMouseListener(new MouseAdapter() {
            /*public void mouseEntered(MouseEvent e) {
                beforeLabel.setIcon(beforeIcon);
            }
            public void mouseExited(MouseEvent e) {
                beforeLabel.setIcon(null);
            }*/
            public void mouseClicked(MouseEvent e) {
                if(compteurPanel1>0) {
                    compteurPanel1--;
                    System.out.println("moins");
                }
            }
        });
        //System.out.println(beforeLabel.getX());
        return beforeLabel;
    }

    public JLabel creationFlecheDroiteP1(){
        ImageIcon beforeIcon = new ImageIcon("imagesFilm/fleche_droite.png");
        JLabel beforeLabel = new JLabel(beforeIcon);
        beforeLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(compteurPanel1+1<maxSizePanel1) {
                    compteurPanel1++;
                    System.out.println("plus");
                }
            }
        });
        return beforeLabel;
    }

    public JLabel creationFlecheGaucheP2(){
        ImageIcon beforeIcon = new ImageIcon("imagesFilm/fleche_gauche.png");
        JLabel beforeLabel = new JLabel(beforeIcon);
        beforeLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(compteurPanel2>0) {
                    compteurPanel2--;
                    System.out.println("moins");
                }
            }
        });
        return beforeLabel;
    }

    public JLabel creationFlecheDroiteP2(){
        ImageIcon beforeIcon = new ImageIcon("imagesFilm/fleche_droite.png");
        JLabel beforeLabel = new JLabel(beforeIcon);
        beforeLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(compteurPanel2+1<maxSizePanel2) {
                    compteurPanel2++;
                    System.out.println("plus");
                }
            }
        });
        return beforeLabel;
    }

    public JLabel creationFlecheGaucheP3(){
        ImageIcon beforeIcon = new ImageIcon("imagesFilm/fleche_gauche.png");
        JLabel beforeLabel = new JLabel(beforeIcon);
        beforeLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(compteurPanel3>0) {
                    compteurPanel3--;
                    System.out.println("moins");
                }
            }
        });
        return beforeLabel;
    }

    public JLabel creationFlecheDroiteP3(){
        ImageIcon beforeIcon = new ImageIcon("imagesFilm/fleche_droite.png");
        JLabel beforeLabel = new JLabel(beforeIcon);
        beforeLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(compteurPanel3+1<maxSizePanel3) {
                    compteurPanel3++;
                    System.out.println("plus");
                }
            }
        });
        return beforeLabel;
    }

    public void creationPanel1() throws IOException {
        affichePanel1.add(creationFlecheGaucheP1());

        for(File files : filtrageFilmP1(monUsers.getFilmAVoir()))
        {
            JLabel imgLabel = new JLabel(new ImageIcon(ImageIO.read(files)));
            imgLabel.setBorder(new EmptyBorder(10, 10, 10, 10));

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(imgLabel, BorderLayout.CENTER);
            panel.setBackground(Color.BLACK);

            imgLabel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    //Juste appeler classe fait par Ad avec les paramtres suivants :
                    //maConnexion.getFilmCliké (film en question)
                    //listeAllFilm

                    //A supprimer
                    JFrame frame = new JFrame(files.getName().replace(".png", ""));
                    frame.setSize(400, 400);
                    frame.setVisible(true);
                }
            });
            affichePanel1.add(panel);
        }
        affichePanel1.add(creationFlecheDroiteP1());
    }

    public void creationPanel2() throws IOException {
        affichePanel2.add(creationFlecheGaucheP2());
        for(File files : filtrageFilmP2(monUsers.getFilmEnCours()))
        {
            JLabel imgLabel = new JLabel(new ImageIcon(ImageIO.read(files)));
            imgLabel.setBorder(new EmptyBorder(10, 10, 10, 10));

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(imgLabel, BorderLayout.CENTER);
            panel.setBackground(Color.BLACK);

            imgLabel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    JFrame frame = new JFrame(files.getName().replace(".png", ""));
                    frame.setSize(400, 400);
                    frame.setVisible(true);
                }
            });
            affichePanel2.add(panel);
        }
        affichePanel2.add(creationFlecheDroiteP2());
    }

    public void creationPanel3() throws IOException, SQLException {
        affichePanel3.add(creationFlecheGaucheP3());
        for(File files : filtrageFilmP3(maConnexion.remplirChampsRequeteString("select titre from oeuvre where categorie = 'Science-fiction';")))
        {
            JLabel imgLabel = new JLabel(new ImageIcon(ImageIO.read(files)));
            imgLabel.setBorder(new EmptyBorder(10, 10, 10, 10));

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(imgLabel, BorderLayout.CENTER);
            panel.setBackground(Color.BLACK);

            imgLabel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    JFrame frame = new JFrame(files.getName().replace(".png", ""));
                    frame.setSize(400, 400);
                    frame.setVisible(true);
                }
            });
            affichePanel3.add(panel);
        }
        affichePanel3.add(creationFlecheDroiteP3());
    }

    private ArrayList<File> filtrageFilmP1 (String film){
        ArrayList<File> fileSelectione = new ArrayList<>();

        File folder = new File("afficheFilm");
        File[] allAfficheFilm = folder.listFiles();
        assert allAfficheFilm != null;

        for(File files: allAfficheFilm)
        {
            if(maConnexion.isSubstring(film, files.getName().replace(".png", ""))){
                if(maxSizePanel1<3)
                {
                    fileSelectione.add(files);
                    maxSizePanel1++;
                }
            }
        }
        return fileSelectione;
    }

    private ArrayList<File> filtrageFilmP2 (String film){
        ArrayList<File> fileSelectione = new ArrayList<>();

        File folder = new File("afficheFilm");
        File[] allAfficheFilm = folder.listFiles();
        assert allAfficheFilm != null;

        for(File files: allAfficheFilm)
        {
            if(maConnexion.isSubstring(film, files.getName().replace(".png", ""))){
                if(maxSizePanel2<3)
                {
                    fileSelectione.add(files);
                    maxSizePanel2++;
                }
            }
        }
        return fileSelectione;
    }

    private ArrayList<File> filtrageFilmP3 (String film){
        ArrayList<File> fileSelectione = new ArrayList<>();

        File folder = new File("afficheFilm");
        File[] allAfficheFilm = folder.listFiles();
        assert allAfficheFilm != null;

        for(File files: allAfficheFilm)
        {
            if(maConnexion.isSubstring(film, files.getName().replace(".png", ""))){
                if(maxSizePanel3<3) {
                    fileSelectione.add(files);
                    maxSizePanel3++;
                }
            }
        }
        return fileSelectione;
    }

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        Users tst = new Users("loic.lecerre@gmail.com", "loic Lecerre");
        Test fenetre = new Test(tst);
    }
}

