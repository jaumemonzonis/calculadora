/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaume.calculadora;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.calculadora.Errores;

/**
 *
 * @author a045795512m
 */
public class controller extends HttpServlet {

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
        
        
        response.setContentType("application/jSon");
        PrintWriter out = response.getWriter(); 
        Gson gSon = new Gson();
        Double num1 = null;
        Double num2 = null;
        String op= request.getParameter("sel");
        double res=0;
        
        //comentario etiqueta
        
        
        // validar numerico
        try {
           
           num1 = Double.parseDouble(request.getParameter("num1"));
           num2 = Double.parseDouble(request.getParameter("num2"));
       
            } catch (NumberFormatException nfex) {
            response.setStatus(500);
            Errores error = new Errores();
            error.setMensaje("Por favor, ingrese dos numeros en el formulario");
            error.setNum2(request.getParameter("num1"));
            error.setNum1(request.getParameter("num2"));
            response.getWriter().append(gSon.toJson(error));

        }
          // validar rango
          
          if (!(num1 >= 0 && num1 <= 100) || !(num2 >= 0 && num2 <= 100)) {
            response.setStatus(500);
            Errores error = new Errores();
            error.setMensaje("Numeros entre 1 y 100");
            error.setNum2(request.getParameter("num1"));
            error.setNum1(request.getParameter("num2"));
            response.getWriter().append(gSon.toJson(error));
            
        } else {  
         
            try {  
            
            if (op.equals("sumar") ){
                res=num1+num2;
            } else if (op.equals("restar"))  {
                res=num1-num2;
            } else  if (op.equals("multiplicar"))  {
                res=num1*num2;
            } else  if (op.equals("dividir"))  {
                if (num2 == 0) {
                 response.setStatus(500);
            Errores error = new Errores();
            error.setMensaje("El divisor no puede ser negativo");
            error.setNum2(request.getParameter("num1"));
            error.setNum1(request.getParameter("num2"));
            response.getWriter().append(gSon.toJson(error));
                        } else {
                            res = num1 / num2;
                         }
            }
 
                
                
                
//            out.println("res "+res);
            
            response.getWriter().append(gSon.toJson(res));
            
            
            
            
            
        } finally {
            out.close();
        }
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
