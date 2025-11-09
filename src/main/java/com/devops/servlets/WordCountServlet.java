package com.devops.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "WordCountServlet", urlPatterns = {"/wordcount"})
public class WordCountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String text = req.getParameter("text");
        if (text == null) text = "";

        int wordCount = 0;
        int numberCount = 0;
        int spaceCount = 0;

        // Split text and count words/numbers
        String[] parts = text.trim().split("\\s+");
        for (String part : parts) {
            if (part.matches("\\d+")) {
                numberCount++;
            } else if (!part.isEmpty()) {
                wordCount++;
            }
        }

        // Count spaces
        for (char c : text.toCharArray()) {
            if (Character.isWhitespace(c)) {
                spaceCount++;
            }
        }

        // Prepare response
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Word Counter Result</title>");
        out.println("<style>");
        out.println("body {font-family: 'Segoe UI', Tahoma, sans-serif; background: linear-gradient(135deg, #8f94fb, #4e54c8); height: 100vh; display: flex; align-items: center; justify-content: center; margin: 0;}");
        out.println(".result-container {background: white; padding: 30px; border-radius: 16px; width: 400px; text-align: center; box-shadow: 0 8px 16px rgba(0,0,0,0.2); animation: fadeIn 0.8s ease;}");
        out.println("@keyframes fadeIn {from {opacity:0; transform:translateY(-20px);} to {opacity:1; transform:translateY(0);}}");
        out.println("h1 {color: #4e54c8; margin-bottom: 10px;}");
        out.println(".result-box {text-align:left; margin-top:20px; font-size:16px;}");
        out.println(".result-box p {background:#f7f8ff; padding:8px 12px; border-radius:8px; margin:6px 0;}");
        out.println(".btn {display:inline-block; margin-top:20px; padding:10px 20px; border:none; border-radius:25px; background:linear-gradient(135deg,#4e54c8,#8f94fb); color:white; font-weight:bold; text-decoration:none; transition:0.3s;}");
        out.println(".btn:hover {transform:scale(1.05); box-shadow:0 4px 12px rgba(78,84,200,0.4);}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='result-container'>");
        out.println("<h1>Word Counter Result</h1>");
        out.println("<div class='result-box'>");
        out.println("<p><strong>Input:</strong> " + text + "</p>");
        out.println("<p><strong>Words:</strong> " + wordCount + "</p>");
        out.println("<p><strong>Numbers:</strong> " + numberCount + "</p>");
        out.println("<p><strong>Spaces:</strong> " + spaceCount + "</p>");
        out.println("</div>");
        out.println("<a href='/JavaApp/' class='btn'>⬅ Back to Home</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
