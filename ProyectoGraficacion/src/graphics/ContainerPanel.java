package graphics;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JComponent;

/**
 *
 * @author El Javi y Alex
 */
public class ContainerPanel extends JComponent {

    private VRP_Panel vrpPanel;
    private int margen = 20;
    private int width = 700;
    private int height = 400;

    public ContainerPanel(int xLimit, int yLimit) {
        setSize(width, height);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        int innerPanelWidth = getWidth() - margen;
        int innerPanelHeight = getHeight() - margen;

        vrpPanel = new VRP_Panel(innerPanelWidth, innerPanelHeight,
                xLimit, yLimit);
        add(vrpPanel);
    }// end of constructor

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // ya no es necesario establecer el tamaño del vrpPanel aquí
        vrpPanel.setLocation(margen, 0);

        // dibujar numeros eje x
        int inc = vrpPanel.getLineSeparation() * vrpPanel.getProportionX();
        int xCoordinate = 0;
        for (int i = margen; i < getWidth(); i += inc) {
            g.drawString(Integer.toString(xCoordinate), i,
                    vrpPanel.getHeight() + g.getFontMetrics().getHeight());
            xCoordinate += vrpPanel.getLineSeparation();
        }// end of for horizontal

        // dibujar números eje y
        inc = vrpPanel.getLineSeparation() * vrpPanel.getProportionY();
        int yCoordinate = 0;
        for (int i = getHeight() - margen; i > 0; i -= inc) {
            g.drawString(Integer.toString(yCoordinate),
                    margen - g.getFontMetrics().stringWidth(Integer.toString(yCoordinate)) - 2,
                    i);
            yCoordinate += vrpPanel.getLineSeparation();
        }// end of for horizontal
    }// end of paintComponent

    public VRP_Panel getVrpPanel() {
        return vrpPanel;
    }

}// end of class
