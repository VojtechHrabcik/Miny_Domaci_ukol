package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main implements ActionListener {

    private static JLabel labelVyskaPole;
    private static JTextField userTextVyska;
    private static JLabel labelSirkaPole;
    private static JTextField userTextSirka;
    private static JLabel labelPocetMin;
    private static JTextField userTextPocetMin;
    private static JButton tlacitkoVstup;

    public static void main(String[] args) {

        JPanel panel = new JPanel();
        JFrame frame = new JFrame();

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);

//Vyska pole - vstup uzivatele
        labelVyskaPole = new JLabel("Vyska pole");
        labelVyskaPole.setBounds(10, 20, 80, 25);
        panel.add(labelVyskaPole);

        userTextVyska = new JTextField();
        userTextVyska.setBounds(100, 20, 165, 25);
        panel.add(userTextVyska);

//Sirka pole - vstup uzivatele.

        labelSirkaPole = new JLabel("Sirka pole");
        labelSirkaPole.setBounds(10, 50, 80, 25);
        panel.add(labelSirkaPole);

        userTextSirka = new JTextField();
        userTextSirka.setBounds(100, 50, 165, 25);
        panel.add(userTextSirka);

//Pocet min pole - vstup uzivatele.

        labelPocetMin = new JLabel("Pocet Min");
        labelPocetMin.setBounds(10, 80, 80, 25);
        panel.add(labelPocetMin);

        userTextPocetMin = new JTextField();
        userTextPocetMin.setBounds(100, 80, 165, 25);
        panel.add(userTextPocetMin);

//Tlacitko pro vygenerovani pole na zaklade uvedenych parametru.

        tlacitkoVstup = new JButton("Start");
        tlacitkoVstup.setBounds(120, 110, 80, 25);
        panel.add(tlacitkoVstup);
        tlacitkoVstup.addActionListener(new Main());


        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userVyska = userTextVyska.getText();
        String userSirka = userTextSirka.getText();
        String userMiny = userTextPocetMin.getText();

        Integer vyskaPole = Integer.parseInt(userVyska);
        Integer sirkaPole = Integer.parseInt(userSirka);
        Integer pocetMin = Integer.parseInt(userMiny);




        HerniPole pole = new HerniPole(vyskaPole, sirkaPole, pocetMin);

        JFrame kontejner = new JFrame();

        kontejner.setVisible(true);
        kontejner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        kontejner.setSize(sirkaPole * 60, vyskaPole * 60);

        GridLayout layout = new GridLayout(vyskaPole, sirkaPole);

        kontejner.setLayout(layout);

        for (int i = 0; i < sirkaPole; i++) {
            for (int j = 0; j < sirkaPole; j++) {
                JButton tlacitko = new JButton();

                tlacitko.setBackground(Color.GRAY);
                tlacitko.setOpaque(true);
                tlacitko.setText(j + " : " + i);

                int finalSouradniceX = j;
                int finalSouradniceY = i;

                tlacitko.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (SwingUtilities.isLeftMouseButton(e)) {
                            if (tlacitko.getText() != "?") {
                                if (pole.jeTamMina(finalSouradniceX, finalSouradniceY)) {
                                    tlacitko.setBackground(Color.RED);
                                    tlacitko.setText("X");
                                    JOptionPane.showMessageDialog(kontejner, "Prohral jsi!");

                                } else {
                                    tlacitko.setBackground(Color.CYAN);
                                    tlacitko.setText(String.valueOf(pole.kolikSouseduMaMinu(finalSouradniceX, finalSouradniceY)));
                                    pole.snizPocetZbyvajicichPoli();
                                    if (pole.vyhralUzivatel()) {
                                        JOptionPane.showMessageDialog(kontejner, "Gratuluji, vyhral jsi!");
                                    }
                                }
                            }

                        }
                        if (SwingUtilities.isRightMouseButton(e)) {
                            if (tlacitko.getText() != "?") {
                                tlacitko.setText("?");
                                tlacitko.setBackground(Color.GREEN);
                            } else {
                                tlacitko.setText("");
                                tlacitko.setBackground(Color.GRAY);
                            }
                        }

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });

                kontejner.add(tlacitko);
            }
        }
    }
}







