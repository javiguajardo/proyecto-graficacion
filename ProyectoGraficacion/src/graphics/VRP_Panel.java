/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Jairo
 */
public class VRP_Panel extends JComponent {

    private int xLimit;
    private int yLimit;
    private int proportionX;
    private int proportionY;

    private int lineSeparation = 5;

    List<Client> clientList = new ArrayList<>();

    public VRP_Panel(int width, int height, int xLimit, int yLimit) {
        setSize(width, height);

//        JComponent no pinta su background, JPanel sí
//        setBackground(Color.white);
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        this.xLimit = xLimit;
        this.yLimit = yLimit;

        proportionX = getWidth() / xLimit;
        proportionY = getWidth() / yLimit;

//        Client client = new Client(50, 50, 10, 1);
//        add(client);
        // agregar clientes
        Client client1 = new Client(50, 50, 10, 1);
        add(client1);

        Client client2 = new Client(40, 10, 5, 2);
        add(client2);

        Client client3 = new Client(15, 40, 8, 3);
        add(client3);

        
        clientList.add(client1);
        clientList.add(client2);
        clientList.add(client3);
    }// end of Constructor

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("paintComponent");

        // alternativa a paintBackground
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        //regresar el color a negro para las líneas
        g.setColor(Color.BLACK);

        Graphics2D g2D = (Graphics2D) g;
        Stroke punteada = new BasicStroke(1, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL, 0, new float[]{2}, 0);
        g2D.setStroke(punteada);

        proportionX = getWidth() / xLimit;
        proportionY = getHeight() / yLimit;

        // dibujar lineas verticales
        int inc = lineSeparation * proportionX;
        for (int i = 0; i < getWidth(); i += inc) {
            g2D.drawLine(i, 0, i, getHeight());
        }// end of for horizontal

        // dibujar lineas horizontales
        inc = lineSeparation * proportionY;
        for (int i = getHeight(); i > 0; i -= inc) {
            g2D.drawLine(0, i, getWidth(), i);
        }// end of for horizontal

        // dibujar lineas entre puntos
        dibujarLineasEntrePuntos(g);

        g2D.setStroke(new BasicStroke());
    }//end of paintComponent

    private void dibujarLineasEntrePuntos(Graphics g) {
        
        double distancia = 0.0;
        
        for (int i = 0; i < clientList.size(); i++) {
            // dibujar linea entre clientes
            if (i < (clientList.size() - 1)) {
                distancia += dibujarLineaEntreClientes(i, i + 1, g);

            } else {
                distancia += dibujarLineaEntreClientes(i, 0, g);
            }
        }// end of for
        
        System.out.println(distancia);
    }// end of dibujarLineasEntrePuntos

    public double dibujarLineaEntreClientes(int cliente1, int cliente2, Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        Stroke punteada = new BasicStroke(2, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL, 0, new float[]{30,7,18}, 0);
        g2D.setStroke(punteada);
        g2D.setColor(Color.BLUE);

        // coordenadas del cliente 1
        int xClient1 = clientList.get(cliente1).getX() + Client.CIRCLE_R;
        int yClient1 = clientList.get(cliente1).getY() + Client.CIRCLE_R + Client.CIRCLE_D;
        
        // coordenadas del cliente 2
        int xClient2 = clientList.get(cliente2).getX() + Client.CIRCLE_R;
        int yClient2 = clientList.get(cliente2).getY() + Client.CIRCLE_R + Client.CIRCLE_D;
        
        g.drawLine(xClient1, yClient1, xClient2, yClient2);
        
        // dibujar la distancia entre puntos
        Font bold = new Font(getFont().getName(),
                Font.BOLD,
                getFont().getSize());
        g.setFont(bold);
        g.setColor(Color.BLACK);
        
        // calcular la distancia
        double distancia = calcularDistancia(clientList.get(cliente1),
                clientList.get(cliente2));
        String distanciaS = String.format("%.2f", 
                distancia);
        
        g.drawString(distanciaS, (xClient1 + xClient2)/2,
                (yClient1 + yClient2)/2);
        
        return distancia;
    }
    
    private double calcularDistancia(Client client1, Client client2){
        return Math.sqrt(Math.pow(client1.getxOnMap() - client2.getxOnMap(), 2) 
                + Math.pow(client1.getyOnMap() - client2.getyOnMap(),2));
    }// fin de calcularDistancia

    public int getLineSeparation() {
        return lineSeparation;
    }

    public int getProportionX() {
        return proportionX;
    }

    public int getProportionY() {
        return proportionY;
    }

}// end of class