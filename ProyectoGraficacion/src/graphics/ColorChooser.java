/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

/**
 *
 * @author guajardo
 */
public class ColorChooser extends JPanel {

    private JButton changeColorJButton;
    private Color color = Color.LIGHT_GRAY;

    public ColorChooser() {

        // boton y eventHandler
        changeColorJButton = new JButton("Cambiar Color");
        changeColorJButton.addActionListener(
                new ActionListener() {
            // mostrar colorchooser al hacer click
            @Override
            public void actionPerformed(ActionEvent e) {

                color = JColorChooser.showDialog(
                        ColorChooser.this,
                        "Elige un color",
                        color);

                if (color == null) {
                    color = Color.LIGHT_GRAY;
                }
            }// end of actionPerformed
        }// end of ActionListener
        );// end of addAction

        add(changeColorJButton, BorderLayout.SOUTH);
        setSize(150, 100);
        setVisible(true);
    }

}
