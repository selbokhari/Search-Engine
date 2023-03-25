/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solr;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocumentList;

/**
 *
 * @author PC
 */
@WebServlet(name = "controle", urlPatterns = {"/controle"})
public class controle extends HttpServlet {
  public static ArrayList<String> id= new ArrayList<String>();;
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

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html charset=UTF-8");
        String requete = request.getParameter("search");
        PrintWriter out = response.getWriter();

        if (requete.isEmpty()) {

            this.getServletContext().getRequestDispatcher("/index.html").forward(request, response);
        } else {

            SolrClasse objet = new SolrClasse();
            AlKhalil alkhalil = new AlKhalil();
            List<String> l = alkhalil.getLemme(requete);
            SolrDocumentList liste = null;
           id= new ArrayList<String>();
            ArrayList<String> name = new ArrayList<String>();
            long numf = 0;
            if (l.isEmpty()) {
                l.add(requete);
            }
            for (String str : l) {
                try {

                    liste = objet.connecter(str);
                } catch (SolrServerException ex) {
                    Logger.getLogger(controle.class.getName()).log(Level.SEVERE, null, ex);
                }

                numf = numf + liste.getNumFound();

                for (int i = 0; i < liste.size(); i++) {
                    String id1 = (String) liste.get(i).getFieldValue("id");
                    id.add(id1);
                    String name1 = (String) liste.get(i).getFirstValue("name");

                    name.add(name1);

                }
            }

//            request.setAttribute("numf", numf);
//            request.setAttribute("id", id);
//            request.setAttribute("name", name);

            this.getServletContext().getRequestDispatcher("/Result.jsp").forward(request, response);

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
        processRequest(request, response);
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
