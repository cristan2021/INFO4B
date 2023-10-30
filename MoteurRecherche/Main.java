/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MoteurRecherche;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.EditorKit;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author crist
 */
public class Main {
    public static void main(String[] args) throws MalformedURLException, IOException, BadLocationException{
   
            //Charger la page
            ArrayList<WebCrawler> bots = new ArrayList<>();
            URL url = new URL("http://www.google.com/search?q=");
            URLConnection uconnection = url.openConnection();
            Reader rd = new InputStreamReader(uconnection.getInputStream());
            //lire le document HTML
            EditorKit kit = new HTMLEditorKit();
            HTMLDocument doc = (HTMLDocument) kit.createDefaultDocument();
            doc.putProperty("IgnoreCharsetDirective", new Boolean(true));
            kit.read(rd, doc, 0);
            //Parcourir la balise lien
            HTMLDocument.Iterator it = doc.getIterator(HTML.Tag.A);
            while (it.isValid()) {
                SimpleAttributeSet s = (SimpleAttributeSet) it.getAttributes();
                String link = (String) s.getAttribute(HTML.Attribute.HREF);
                if (link != null) {
                    //Afficher le lien trouvé
                    System.out.println(link);
                     bots.add(new WebCrawler(link,1));
                      for(WebCrawler w : bots){
                        try{
                            w.getThread().join();
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                }
                it.next();
            }
        
       
        }
    }
}
