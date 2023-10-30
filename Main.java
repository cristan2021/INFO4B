/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MoteurRecherche;

import java.util.ArrayList;

/**
 *
 * @author crist
 */
public class Main {
    public static void main(String[] args){
        ArrayList<WebCrawler> bots = new ArrayList<>();
        bots.add(new WebCrawler("https://abcnews.go.com",1));
        bots.add(new WebCrawler("https://www.npr.org",2));
        bots.add(new WebCrawler("https://www.nytimes.com",3));
        
        for(WebCrawler w : bots){
            try{
                w.getThread().join();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
