/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taches;

//
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.sort;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mr_mp.partie1_1.MotFrequence;

/**
 *
 * @author pc
 */
@WebServlet(name = "Qst3", urlPatterns = {"/Qst3"})
public class Qst3 extends HttpServlet {

//    public List<String> stopWordsAr = new ArrayList<String>();
//    public String projectPath;
//    public String file = "\\donnees\\";
//    public String Slash = "\\";
//    static public ArrayList<MotFrequence> liste100Mots;
//    public double constatnte;
//    private Color gridColor = new Color(200, 200, 200, 200);
//    public int[] coordinatesX = {0, 100, 200, 200, 0};
//    public int[] coordinatesY = {0, 100, 200, 0, 200};
    public ArrayList<Integer> repetitions = new ArrayList<>();
//    public double[] pr;
//    public int[] fr;
//
//    public int[] rangs;
//    public int mar = 80;
//    public int divisions = 10;
//    public Color lineColor = new Color(44, 102, 230, 180);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>La loi de zipf</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = getServletContext();
        try {
            Qst2 qst2 = new Qst2();
            Qst2.projectPath = Paths.get(context.getRealPath("")).getParent().getParent() + "";
            response.setContentType("image/jpeg");
            int w = 1000, h = 500;
            // Create an Image
            BufferedImage img
                    = new BufferedImage(w, h,
                            BufferedImage.TYPE_INT_RGB);

            // Get the Image's Graphics, and draw.
            Graphics2D g = img.createGraphics();
            g.setBackground(Color.white);
//        ArrayList<MotFrequence> liste = run(context, qst3);
            if (Qst2.liste100Mots == null) {
                Qst2.liste100Mots = qst2.run(context, qst2);
            } else if (Qst2.liste100Mots.isEmpty()) {
                Qst2.liste100Mots = qst2.run(context, qst2);
            }
            Qst3 qst3 = new Qst3();
            if (repetitions.isEmpty()) {
                for (int k = 0; k < 100; k++) {
                    qst3.repetitions.add(Qst2.liste100Mots.get(k).frequence);
                }

            }

            try {
                // In real life this would call some charting software...
                g.setColor(Color.white);
                g.fillRect(0, 0, w, h);
                paintComponent(g, w, h, qst3.repetitions);

            } catch (Exception ex) {
                log(ex.getCause() + "\n");
                log("Boo hoo, failed to call paintComponent");
            }
            // Write the output
            OutputStream os = response.getOutputStream();
            ImageOutputStream ios = ImageIO.createImageOutputStream(os);

            if (!ImageIO.write(img, "jpeg", ios)) {
                log("Boo hoo, failed to write JPEG");
            }
            ios.close();
            os.close();
        } catch (Exception e) {
            log(e.getCause() + "\n");

        }
    }

    public void paintComponent(Graphics2D g1, int w, int h, ArrayList<Integer> repetitions) throws Exception {
        double constatnte;
        Color gridColor = new Color(200, 200, 200, 200);
        int[] coordinatesX = {0, 100, 200, 200, 0};
        int[] coordinatesY = {0, 100, 200, 0, 200};

        double[] pr;
        int[] fr;

        int[] rangs;
        int mar = 80;
        int divisions = 10;
        Color lineColor = new Color(44, 102, 230, 180);

//       Graphics2D g1 = (Graphics2D) g;
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = w;
        int height = h;

        int scaleW = (w - 2 * mar) / divisions;
        int scaleH = (h - 2 * mar) / divisions;

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
        g1.setColor(Color.gray);
        String zipF = "La courbe de la loi de zipf";
        g1.setFont(new Font(Font.SERIF, Font.CENTER_BASELINE, 28));

        g1.drawString(zipF, WIDTH / 2 - mar, mar / 2);
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

    public int convertir(double a, int size, double max) {
        int s = size / 2;
        return (int) (a * (s / max));
    }

    public int getMax(int[] coordinates) {
        int max = -Integer.MAX_VALUE;
        for (int i = 0; i < coordinates.length; i++) {
            if (coordinates[i] > max) {
                max = coordinates[i];
            }
        }
        return max;
    }

    public double getMax(double[] coordinates) {
        double max = -Double.MAX_VALUE;
        for (int i = 0; i < coordinates.length; i++) {
            if (coordinates[i] > max) {
                max = coordinates[i];
            }

        }
        return max;
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
