package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Tackinchatgpt extends JFrame {
    private ArrayList<JButton> boutons = new ArrayList<>();
    private JButton caseVide;


    public Tackinchatgpt() {
        setTitle("Jeu de Taquin");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        // Création des boutons et ajout à la grille
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.addActionListener(new BoutonListener());
            boutons.add(button);
            add(button);
        }

        // Création de la case vide
        caseVide = new JButton("");
        caseVide.addActionListener(new BoutonListener());
        boutons.add(caseVide);
        add(caseVide);

        // Initialisation du taquin
        initialiserTaquin();

        setVisible(true);
    }

    // Méthode pour initialiser le taquin
    private void initialiserTaquin() {
        Collections.shuffle(boutons);
        actualiserPlateau();
    }

    // Méthode pour vérifier si un déplacement est valide
    private boolean estDeplacementValide(int index) {
        int emptyIndex = boutons.indexOf(caseVide);
        return (Math.abs(index - emptyIndex) == 1 && index / 3 == emptyIndex / 3) ||
                (Math.abs(index - emptyIndex) == 3 && index % 3 == emptyIndex % 3);
    }

    // Méthode pour effectuer un déplacement
    private void deplacerPiece(int index) {
        int emptyIndex = boutons.indexOf(caseVide);
        Collections.swap(boutons, index, emptyIndex);
        actualiserPlateau();
        if (estResolu()) {
            JOptionPane.showMessageDialog(this, "Félicitations, vous avez gagné !");
            initialiserTaquin(); // Redémarrer le jeu après avoir gagné
        }
    }

    // Méthode pour vérifier si le taquin est résolu
    private boolean estResolu() {
        for (int i = 0; i < boutons.size() - 1; i++) {
            if (!boutons.get(i).getText().equals(String.valueOf(i + 1))) {
                return false;
            }
        }
        return true;
    }

    // Méthode pour mettre à jour l'affichage du plateau
    private void actualiserPlateau() {
        getContentPane().removeAll();
        for (JButton bouton : boutons) {
            add(bouton);
        }
        revalidate();
        repaint();
    }

    // Classe interne pour gérer les événements des boutons
    private class BoutonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton boutonClique = (JButton) e.getSource();
            int indexClique = boutons.indexOf(boutonClique);
            if (estDeplacementValide(indexClique)) {
                deplacerPiece(indexClique);
            }
        }
    }

}