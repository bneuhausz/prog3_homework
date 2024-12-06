/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import alapOsztalyok.Global;
import alapOsztalyok.Jatekos;
import alapOsztalyok.Sarkany;
import java.awt.Image;
import javax.swing.ImageIcon;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nbx
 */
public class nebsabpUnitTeszt {
    
    /**
     *Leteszteli, hogy jól működik-e a Jatekos osztály pontotKap metódusa és a Sarkany osztály talalat metódusa.
     */
    @Test
    public void testPontotKap(){
        Jatekos jatekos = new Jatekos("tesztJatekos",0);
        jatekos.pontotKap();
        int eredmeny = jatekos.getPontSzam();
        assertEquals(1, eredmeny);
        
        Image kep = new ImageIcon(this.getClass().getResource(Global.SARKANY_KEPUT)).getImage();
        Sarkany sarkany = new Sarkany(0,0,40,40,kep,false,300,300,null,40);
        sarkany.talalat(0,0);
        assertTrue(sarkany.isEltalaltak());
    }
}
