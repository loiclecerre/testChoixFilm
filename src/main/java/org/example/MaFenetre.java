package org.example;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;

public class MaFenetre extends JFrame {

    public MaFenetre() {
        super("Ma fenetre avec images");

        // Ajouter une barre de recherche
        JPanel searchPanel = new JPanel();
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Rechercher");
        searchPanel.setBackground(Color.BLACK);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Ajouter les images dans une grille 1x3
        JPanel imagePanelR1 = new JPanel(new GridLayout(1, 3, 0, 0)); // espacement de 50 pixels entre les images
        JPanel imagePanelR2 = new JPanel(new GridLayout(1, 3, 0, 0)); // espacement de 50 pixels entre les images
        JPanel imagePanelR3 = new JPanel(new GridLayout(1, 3, 0, 0)); // espacement de 50 pixels entre les images

        imagePanelR1.setBackground(Color.BLACK);
        imagePanelR2.setBackground(Color.BLACK);
        imagePanelR3.setBackground(Color.BLACK);

        File folder = new File("ImagesFilm");
        File[] files = folder.listFiles();
        int count =0;
        assert files != null;
        for (File file : files) {
            try {
                Image img = ImageIO.read(file);
                JLabel imgLabel = new JLabel(new ImageIcon(img));
                imgLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
                // Ajouter le titre prédéfini sous chaque image
                String title = file.getName().replace(".png", ""); // utiliser le nom du fichier sans l'extension
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
                        // Ajouter le bouton avant le panel d'image
                        JButton beforeButton = new JButton("Avant");
                        beforeButton.setSize(new Dimension(50, 20)); // largeur de 50 pixels et hauteur de 30 pixels
                        beforeButton.addActionListener(e -> System.out.println("Avant"));
                        panel.add(beforeButton, BorderLayout.WEST);
                    }
                    else if (count ==2) {
                        // Ajouter le bouton après le panel d'image
                        JButton afterButton = new JButton("Après");
                        afterButton.setSize(new Dimension(50, 20)); // largeur de 50 pixels et hauteur de 30 pixels
                        afterButton.addActionListener(e -> System.out.println("Après"));
                        panel.add(afterButton, BorderLayout.EAST);
                    }
                    imagePanelR1.add(panel);
                }
                else if(count<6) {
                    if(count == 3) {
                        // Ajouter le bouton avant le panel d'image
                        JButton beforeButton = new JButton("Avant");
                        beforeButton.setSize(new Dimension(50, 20)); // largeur de 50 pixels et hauteur de 30 pixels
                        beforeButton.addActionListener(e -> System.out.println("Avant"));
                        panel.add(beforeButton, BorderLayout.WEST);
                    }
                    else if (count ==5) {
                        // Ajouter le bouton après le panel d'image
                        JButton afterButton = new JButton("Après");
                        afterButton.setSize(new Dimension(50, 20)); // largeur de 50 pixels et hauteur de 30 pixels
                        afterButton.addActionListener(e -> System.out.println("Après"));
                        panel.add(afterButton, BorderLayout.EAST);
                    }
                    imagePanelR2.add(panel);
                }
                else if (count<9) {
                    if(count == 6) {
                        // Ajouter le bouton avant le panel d'image
                        JButton beforeButton = new JButton("Avant");
                        beforeButton.setSize(new Dimension(50, 20)); // largeur de 50 pixels et hauteur de 30 pixels
                        beforeButton.addActionListener(e -> System.out.println("Avant"));
                        panel.add(beforeButton, BorderLayout.WEST);
                    }
                    else if (count ==8) {
                        // Ajouter le bouton après le panel d'image
                        JButton afterButton = new JButton("Après");
                        afterButton.setSize(new Dimension(50, 20)); // largeur de 50 pixels et hauteur de 30 pixels
                        afterButton.addActionListener(e -> System.out.println("Après"));
                        panel.add(afterButton, BorderLayout.EAST);
                    }
                    imagePanelR3.add(panel);
                }
                else {
                    break;
                }
                count++;
            } catch (IOException ex) {
                System.out.println("Erreur de lecture de l'image : " + ex.getMessage());
            }
        }
        // Ajouter les panels dans la fenetre
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(searchPanel);
        add(imagePanelR1);
        add(imagePanelR2);
        add(imagePanelR3);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        MaFenetre fenetre = new MaFenetre();
    }
}

