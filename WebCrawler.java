
package MoteurRecherche;

import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.Connection;
import org.jsoup.nodes.Element;

import java.io.IOException;


   
       
  
  
public class WebCrawler implements Runnable {
    private static final int MAX_DEPTH=3;
    private Thread thread;
    private String first_link;
    private ArrayList<String> visitedlinks=new ArrayList<String>();
    private int ID;
    
    
    public WebCrawler(String link,int num)
    {
        System.out.print("WebCrawler created");
        first_link=link;
        ID=num;
        thread= new Thread(this);
        thread.start();
    
    }
    
    public void run() {
        crawl(1,first_link);
    }
    //permet de verifier  les liens
      private void crawl(int level,String url)
      {
       if(level<=MAX_DEPTH){
        Document doc=request(url);
           if(doc != null){
             for(Element link : doc.select("a[href]")){
             String next_link= link.absUrl("href");
             if(visitedlinks.contains(next_link)==false){
                crawl(level++,next_link);
              }
             }
           }
       }  
    }
     //demande l'accés au lien
    private  Document request(String url)
    {
     try{
       Connection con =Jsoup.connect(url);
       Document doc=con.get();
       
       if(con.response().statusCode()== 200){
          
          System.out.println("\n**Bot ID:" + ID + " Received Webpage at " +url);
          String title=doc.title();
          System.out.println(title);
          visitedlinks.add(url);
          return doc;
       }
       return null;
     }
     catch(IOException e){
         return null;
      }
 
    }
   //permet de récuperer le thread courant
   public Thread getThread(){
      return thread;
   }
    
  } 

