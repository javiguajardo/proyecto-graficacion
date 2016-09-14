package graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import javax.swing.JComponent;

/**
 * 
 *
 * @author El Javi y Alex
 */
public class Client extends JComponent {

    private int xOnMap;
    private int yOnMap;
    private int Width = 70;
    private int Height = 20;

    public static int CIRCLE_D;
    public static int CIRCLE_R;

    public Client(int xOnMap) {
        this.xOnMap = xOnMap;
        this.yOnMap = mathFunction(xOnMap);

        setSize(Width, Height);

        CIRCLE_D = getHeight() / 2 - 1;
        CIRCLE_R = CIRCLE_D / 2;

    }// end of constructor

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        CIRCLE_D = getHeight() / 2 - 1;
        CIRCLE_R = CIRCLE_D / 2;

        // draw circle
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.BLACK);
        Stroke continua = new BasicStroke(1);
        g2D.setStroke(continua);

        g.setColor(Color.GREEN);
        g.fillOval(0, getHeight() / 2, CIRCLE_D, CIRCLE_D);
        g.setColor(Color.BLACK);
        g.drawOval(0, getHeight() / 2, CIRCLE_D, CIRCLE_D);

        // dibujado de texto
        Font bold = new Font(getFont().getName(), Font.BOLD, getFont().getSize());
        g.setFont(bold);
        g.drawString("(" + this.xOnMap + ", " + this.yOnMap + ")", CIRCLE_D, CIRCLE_D);

        VRP_Panel parent = (VRP_Panel) getParent();
        int xProp = parent.getProportionX();
        int yProp = parent.getProportionY();

        // obtener coordenadas absolutas en panel
        int xOnPanel = xOnMap * xProp;
        int yOnPanel = yOnMap * yProp;

        // restar y a altura de ventana pra "voltear" ys
        yOnPanel = getParent().getHeight() - yOnPanel;

        // restar tamaños del componente para centrar perfecto
        xOnPanel = xOnPanel - CIRCLE_R;
        yOnPanel = yOnPanel - CIRCLE_D - CIRCLE_R;

        // restar pixel que nos comimos para dibujar el círculo
        xOnPanel--;
        yOnPanel--;

        setLocation(xOnPanel, yOnPanel);
    }// end of paintComponent

    public int getxOnMap() {
        return xOnMap;
    }

    public int getyOnMap() {
        return yOnMap;
    }

    public int mathFunction(int x) {
        int y;
        y = (2 * (x / 3));

        return y;
    }

}
