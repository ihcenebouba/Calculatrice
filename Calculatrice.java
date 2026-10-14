package calculatrice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculatrice extends JFrame implements ActionListener {

    JTextField ecran;
    JButton[] boutons = new JButton[16];

    String[] textes = {
        "7", "8", "9", "/",
        "4", "5", "6", "*",
        "1", "2", "3", "-",
        "C", "0", "=", "+"
    };

    double nombre1, nombre2, resultat;
    String operateur = "";

    public Calculatrice() {

        // Fenêtre
        setTitle("Ma calculatrice");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Écran
        ecran = new JTextField();
        ecran.setFont(new Font("Arial", Font.BOLD, 28));
        ecran.setHorizontalAlignment(JTextField.RIGHT);
        ecran.setEditable(false);
        add(ecran, BorderLayout.NORTH);

        // Panneau des boutons
        JPanel panneau = new JPanel();
        panneau.setLayout(new GridLayout(4, 4, 8, 8));
        panneau.setBackground(Color.DARK_GRAY);

        // Création des boutons
        for (int i = 0; i < textes.length; i++) {

            boutons[i] = new JButton(textes[i]);
            boutons[i].setFont(new Font("Arial", Font.BOLD, 20));
            boutons[i].addActionListener(this);

            if ("/*-+".contains(textes[i])) {
                boutons[i].setBackground(new Color(255, 165, 0));
            } else if (textes[i].equals("=")) {
                boutons[i].setBackground(new Color(50, 180, 90));
            } else if (textes[i].equals("C")) {
                boutons[i].setBackground(Color.RED);
            } else {
                boutons[i].setBackground(new Color(230, 230, 230));
            }

            panneau.add(boutons[i]);
        }

        add(panneau, BorderLayout.CENTER);
        getContentPane().setBackground(Color.DARK_GRAY);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String commande = e.getActionCommand();

        if (commande.matches("[0-9]")) {
            ecran.setText(ecran.getText() + commande);

        } else if (commande.equals("C")) {
            ecran.setText("");
            nombre1 = 0;
            nombre2 = 0;
            operateur = "";

        } else if (commande.matches("[+\\-*/]")) {
            if (!ecran.getText().isEmpty()) {
                nombre1 = Double.parseDouble(ecran.getText());
                operateur = commande;
                ecran.setText("");
            }

        } else if (commande.equals("=")) {

            if (ecran.getText().isEmpty() || operateur.isEmpty()) {
                return;
            }

            nombre2 = Double.parseDouble(ecran.getText());

            switch (operateur) {
                case "+":
                    resultat = nombre1 + nombre2;
                    break;
                case "-":
                    resultat = nombre1 - nombre2;
                    break;
                case "*":
                    resultat = nombre1 * nombre2;
                    break;
                case "/":
                    if (nombre2 == 0) {
                        ecran.setText("Erreur");
                        operateur = "";
                        return;
                    }
                    resultat = nombre1 / nombre2;
                    break;
            }

            ecran.setText(String.valueOf(resultat));
            operateur = "";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculatrice());
    }
}