/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sebastiannielsen
 */
@WebServlet(name = "Servlet", urlPatterns = {"/api/quote/*"})
public class Servlet extends HttpServlet {

    private int nextId = 6;
    private Map<Integer, String> quotes = new HashMap() {
        {
            put(1, "Friends are kisses blown to us by angels");
            put(2, "Do not take life too seriously. You will never get out of it alive");
            put(3, "Behind every great man, is a woman rolling her eyes");
            put(4, "When you are courting a nice girl an hour seems like a second.\n When you sit on a red-hot cinder a second seems like an hour.\n That's relativity.");
            put(5, "It is nice finding that place where you can just go and relax");
            put(6, "We need to steer clear of this poverty of ambition, where people\n want to drive fancy cars and wear nice clothes and live\n in nice apartments but don't want to work hard to accomplish these things.\n Everyone should try to realize their full potential.");
        }
    };

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String[] parts = request.getRequestURI().split("/");
        String parameter = null;
        if (parts.length == 5) {
            parameter = parts[4];
        }
        
        JsonObject quote = new JsonObject();
        
        if(parameter.equals("random")){
            Random random = new Random();
            int randomkey = random.nextInt(quotes.size())+1;
            quote.addProperty("quote", quotes.get(randomkey));
            quote.addProperty("id", randomkey);
            
        } else {
            int key = Integer.parseInt(parameter); 
            quote.addProperty("quote", quotes.get(key));
            quote.addProperty("id", key);
        }
        String jsonResponse = new Gson().toJson(quote);
        
        response.setContentType("text/json");
        PrintWriter pw = response.getWriter();
        pw.print(jsonResponse);
        pw.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        Scanner jsonScanner = new Scanner(request.getInputStream());
        String json = "";
        while (jsonScanner.hasNext()) {
                 json += jsonScanner.nextLine();
        }
        //Get the quote text from the provided Json
        JsonObject newQuote = new JsonParser().parse(json).getAsJsonObject(); 
        String quote = newQuote.get("quote").getAsString(); 
        quotes.put(nextId++, quote);
        
        JsonObject res = new JsonObject();
        res.addProperty("id", nextId);
        res.addProperty("quote", quotes.get(nextId-1));
        String jsonResponse = new Gson().toJson(res);
        
        PrintWriter pw = response.getWriter();
        pw.print(jsonResponse);
        pw.close();
        
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] parts = req.getRequestURI().split("/");
        String parameter = null;
        if (parts.length == 5) {
            parameter = parts[4];
        }
        
        int key = Integer.parseInt(parameter); 
        
        JsonObject res = new JsonObject();
        res.addProperty("quote", quotes.get(key));
        String jsonResponse = new Gson().toJson(res);
        quotes.remove(key);
        
        PrintWriter pw = resp.getWriter();
        pw.print(jsonResponse);
        pw.close();
        
        
        
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] parts = req.getRequestURI().split("/");
        String parameter = null;
        if (parts.length == 5) {
            parameter = parts[4];
        }
        
        int key = Integer.parseInt(parameter); 
        
        Scanner jsonScanner = new Scanner(req.getInputStream());
        String json = "";
        while (jsonScanner.hasNext()) {
                 json += jsonScanner.nextLine();
        }

        JsonObject newQuote = new JsonParser().parse(json).getAsJsonObject(); 
        String quote = newQuote.get("quote").getAsString(); 
        
        quotes.replace(key, quote);
        
        JsonObject res = new JsonObject();
        res.addProperty("id", key);
        res.addProperty("quote", quotes.get(key));
        String jsonResponse = new Gson().toJson(res);
        
        PrintWriter pw = resp.getWriter();
        pw.print(jsonResponse);
        pw.close();
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
