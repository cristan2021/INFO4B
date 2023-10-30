/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MoteurRecherche;

/**
 *
 * @author Guillaume Gauguet
 * utilisé pour voir quel site a le plus de 
 */
public class Vue {
    private Page page;
    private int vues;

    public Vue(Page page, int vues) {
        this.page = page;
        this.vues = vues;
    }

    public void addVues(int vues) {
        this.vues += vues;
    }

    public String getHtml() {
        return this.page.getHtml();
    }

    public int getVues() {
        return vues;
    }
}
