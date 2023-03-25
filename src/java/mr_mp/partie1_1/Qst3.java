/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mr_mp.partie1_1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static mr_mp.partie1_1.Qst2.run;

/**
 *
 * @author pc
 */
public class Qst3 extends JPanel {

    public double constatnte;
    private Color gridColor = new Color(200, 200, 200, 200);
    int[] coordinatesX = {0, 100, 200, 200, 0};
    int[] coordinatesY = {0, 100, 200, 0, 200};
    private static ArrayList<Integer> repetitions = new ArrayList<>();
    double[] pr;
    int[] fr;

    int[] rangs;
    int mar = 80;
    int divisions = 10;
    private Color lineColor = new Color(44, 102, 230, 180);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g1 = (Graphics2D) g;
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();

        int scaleW = (getWidth() - 2 * mar) / divisions;
        int scaleH = (getHeight() - 2 * mar) / divisions;

        int HEIGHT = scaleH * divisions;
        int WIDTH = scaleW * divisions;

        g1.setColor(Color.WHITE);
        g1.fillRect(mar + 2, mar + 2, WIDTH, HEIGHT);
        g1.setColor(Color.BLACK);

        g1.draw(new Line2D.Double(mar, mar, mar, scaleH * divisions + mar));
        g1.draw(new Line2D.Double(mar, scaleH * divisions + mar, scaleW * divisions + mar, scaleH * divisions + mar));

        // repetitions partie
        int total = 0;
        for (int i = 0; i < repetitions.size(); i++) {

            total += i * 10;
        }
        Comparator c = Collections.reverseOrder();
        Collections.sort(repetitions, c);

        pr = new double[repetitions.size()];
        fr = new int[repetitions.size()];
        rangs = new int[repetitions.size()];
        for (int i = 0; i < repetitions.size(); i++) {

            rangs[i] = i + 1;
            fr[i] = repetitions.get(i);
            pr[i] = (double) repetitions.get(i) / total;
        }

        double yScalLabelFr = (double) getMax(fr) / divisions;
        double yScalLabel = (double) getMax(pr) / divisions;
        double xScalLabel = (double) getMax(rangs) / divisions;
        for (int i = 1; i < divisions + 1; i++) {
            g1.setPaint(Color.BLACK);
            g1.draw(new Line2D.Double(mar + scaleW * i, HEIGHT + mar - 4, mar + scaleW * i, HEIGHT + mar));
            g1.draw(new Line2D.Double(mar, HEIGHT + mar - scaleH * i, mar + 4, HEIGHT + mar - scaleH * i));

            DecimalFormat df = new DecimalFormat("#.###");
            String yLabel = df.format(yScalLabel * (divisions - i + 1));

            DecimalFormat f = new DecimalFormat("#");
            String frLaybel = f.format(yScalLabelFr * (divisions - i + 1));

            g1.drawString("(" + yLabel + ") " + frLaybel, mar - 70, mar + scaleH * (i - 1) + 4);
            g1.drawString(rangs[i - 1] * ((int) (rangs.length) / (divisions)) + "", mar + scaleW * i - 4, HEIGHT + mar + 30);

            g1.setColor(gridColor);
            g1.drawLine(mar + scaleW * i, mar, mar + scaleW * i, HEIGHT + mar - 3);
            g1.drawLine(mar + 3, HEIGHT + mar - scaleH * i, WIDTH + mar, HEIGHT + mar - scaleH * i);

        }
        // designons le point  0 
        g1.setColor(Color.BLACK);
        g1.drawString(0 + "", mar - 15, HEIGHT + mar + 15);

        g1.drawString("( Probabilité ) Fréquence ", mar - 65, mar - 30);
        g1.drawString("Rang", WIDTH + mar + 10, HEIGHT + mar);

//        g1.setPaint(Color.BLUE);
//        for (int i = 0; i < coordinatesX.length; i++) {
//            double x1 = mar + coordinatesX[i] * scaleX;
//            double y1 = height - mar - scaleY * coordinatesY[i];
////            g1.fill(new Ellipse2D.Double(x1 - 2, y1 - 2, 4, 4));
//        }
        double maxPr = getMax(fr);
        int maxRangs = getMax(rangs);
        double scaleX = (double) (WIDTH) / getMax(rangs);
        double scaleY = (double) (HEIGHT) / getMax(fr);
        g1.setColor(Color.green);
        int X[] = new int[fr.length];
        int Y[] = new int[fr.length];
        for (int i = 0; i < fr.length; i++) {
            g1.setColor(Color.BLACK);
            double x1 = mar + rangs[i] * scaleX;
            double y1 = HEIGHT + mar - scaleY * fr[i];
            g1.fill(new Ellipse2D.Double(x1 - 2, y1 - 2, 4, 4));

            if (i > 0) {
                g1.setColor(lineColor);
                int X1 = (int) x1;
                int Y1 = (int) y1;

                int X0 = (int) (mar + rangs[i - 1] * scaleX);
                int Y0 = (int) (HEIGHT + mar - scaleY * fr[i - 1]);

                g1.drawLine(X0, Y0, X1, Y1);

            }
        }
//        double cst = pr[0] * rangs[0];
//
//        int Y1 = (int) (HEIGHT + mar - scaleY * fr[0]);
//        int X1 = (int) (mar + rangs[0] * scaleX);
//
//        int X0 = (int) (mar + rangs[rangs.length - 1] * scaleX);
//        int Y0 = (int) (HEIGHT + mar - scaleY * (cst / rangs[rangs.length - 1]));
//        g1.setColor(Color.MAGENTA);
//        g1.drawLine(X1, Y1, X0, Y0);
////        g1.drawString("---------------", WIDTH + mar - 138, mar - 30);
//        g1.drawLine(WIDTH+mar-155,mar - 34,WIDTH+mar-77,mar - 34);
//        g1.setColor(Color.BLACK);
//        g1.drawString(": Constante / Classement ", WIDTH + mar - 70, mar - 30);

    }

  
    public static int convertir(double a, int size, double max) {
        int s = size / 2;
        return (int) (a * (s / max));
    }

    public static int getMax(int[] coordinates) {
        int max = -Integer.MAX_VALUE;
        for (int i = 0; i < coordinates.length; i++) {
            if (coordinates[i] > max) {
                max = coordinates[i];
            }
        }
        return max;
    }

    public static double getMax(double[] coordinates) {
        double max = -Double.MAX_VALUE;
        for (int i = 0; i < coordinates.length; i++) {
            if (coordinates[i] > max) {
                max = coordinates[i];
            }

        }
        return max;
    }

    public static void main(String args[]) throws Exception {
        Qst3 mr = new Qst3();
        mr.courbeDeZipf(new String());

    }

    public void courbeDeZipf(String s) throws Exception {
        s += "ArrayList<MotFrequence> liste = run();<br>";
        ArrayList<MotFrequence> liste = run();

        for (MotFrequence mf : liste) {
            s += "  repetitions.add(mf.frequence" + liste.indexOf(mf) + ");<br>";
            repetitions.add(mf.frequence);

        }
        s += " JFrame frame = new JFrame(\"La coube de la loi de zipF\");<br>";
        JFrame frame = new JFrame("La coube de la loi de zipF");
        s += "frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);<br>";
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        s += " frame.add(new QST3());<br>";
        frame.add(new Qst3());
        s += " frame.setSize(500, 500)<br>";
        frame.setSize(500, 500);
        s += " frame.setLocation(200, 200);<br>";
        frame.setLocation(200, 200);
        s += " frame.setVisible(true);<br>";
        frame.setVisible(true);

    }

    public Qst3() {
    }
}
