package org.example.layout;

import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


@Data
public class Tackin {

    private JPanel jPanel;
    JButton jButton1;
    JButton emptyButton;
    GridBagLayout gridBagLayout = new GridBagLayout();
    int numero = 0;

    public Tackin() {

        jPanel = new JPanel();
        jPanel.setLayout(gridBagLayout);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                GridBagConstraints constraints = new GridBagConstraints();
                constraints.gridy = j;
                constraints.gridx = i;
                constraints.weightx = 1;
                constraints.weighty = 1;
                constraints.fill = GridBagConstraints.BOTH;
                JButton button;


                numero++;
                if (!(i == 2 && j == 2)) {
                    JButton jButton1 = new JButton("numero " + numero);
                    jButton1.addActionListener((e) -> { // Ajoute un écouteur d'action au bouton
                        actionButton(e); // Appelle la méthode "actionButton" lorsque le bouton est cliqué
                    });
                    jPanel.add(jButton1, constraints);
                } else {
                    // Si c'est la dernière colonne et la dernière ligne, crée le bouton vide
                    emptyButton = new JButton(" ");
                    jPanel.add(emptyButton, constraints);
                }
            }
        }


    }

    private void actionButton(ActionEvent e) {
        JButton testbouton = (JButton) e.getSource();

        int gridx = ((GridBagLayout) jPanel.getLayout()).getConstraints(testbouton).gridx;
        int gridy = ((GridBagLayout) jPanel.getLayout()).getConstraints(testbouton).gridy;

        int emptygridx = ((GridBagLayout) jPanel.getLayout()).getConstraints(emptyButton).gridx;
        int emptygridy = ((GridBagLayout) jPanel.getLayout()).getConstraints(emptyButton).gridy;

        if (((emptygridx == gridx && (emptygridy == gridy - 1 || emptygridy == gridy + 1)) || (emptygridy == gridy && (emptygridx == gridx - 1 || emptygridx == gridx + 1))) && !testbouton.equals(emptyButton)) {
            // Échange les boutons
            GridBagConstraints emptyConstraints = ((GridBagLayout) jPanel.getLayout()).getConstraints(emptyButton);
            emptyConstraints.gridx = gridx;
            emptyConstraints.gridy = gridy;


            GridBagConstraints clickedConstraints = ((GridBagLayout) jPanel.getLayout()).getConstraints(testbouton);
            clickedConstraints.gridx = emptygridx;
            clickedConstraints.gridy = emptygridy;


            ((GridBagLayout) jPanel.getLayout()).setConstraints(testbouton, emptyConstraints);
            ((GridBagLayout) jPanel.getLayout()).setConstraints(emptyButton, clickedConstraints);


            jPanel.remove(testbouton);
            jPanel.remove(emptyButton);
            jPanel.add(testbouton, clickedConstraints);
            jPanel.add(emptyButton, emptyConstraints);

            jPanel.revalidate();
        }

        System.out.println("Bouton cliqué : i" + gridx + " j" + gridy);
        System.out.println(testbouton.getText());

    }
}
