/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MoteurRecherche;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author Guillaume Gauguet
 */
public class Page {
    private String html="http://corndog.io/";
    private ArrayList<String> mots = new ArrayList<String>();
    private ArrayList<String> liens = new ArrayList<String>();

    public Page(String html) 
    {
        this.html = html;
        this.Refs();
    }
    
    public void setHtml(String html) 
    {
        this.html = html;
    }
    
    private void Refs()
    {
        String mot="mot";
        String lien="lien";
        Document doc=this.request(this.html);
        for(Element link : doc.select("a[href]"))
        {
            mot=link.text();
            lien= link.absUrl("href");
            this.setRef(mot, lien);
        }
    }
    
    public String getHtml() 
    {
        return html;
    }

    public ArrayList<String> getMots() 
    {
        return mots;
    }

    public ArrayList<String> getLiens() 
    {
        return liens;
    }

    
    
    private void setRef(String mot,String lien) 
    {
        this.mots.add(mot);
        this.liens.add(lien);
    }

    private  Document request(String url)
    {
     try{
       Connection con =Jsoup.connect(url);
       Document doc=con.get();
       if(con.response().statusCode()== 200){
          return doc;
       }
       return null;
     }
     catch(IOException e){
         return null;
      }
 
    }
}
