/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feluletek;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import vezerles.SzalVezerlo;

/**
 *
 * @author nbx
 */
public class JatekPanel extends javax.swing.JPanel {

    private Image hatterKep = new ImageIcon(this.getClass().getResource("/kepek/hatter.jpg")).getImage();
    private SzalVezerlo szalVezerlo;
    
    /**
     * Creates new form JatekPanel
     */
    public JatekPanel() {
        initComponents();
    }

    /**
     *Kirajzol egy képet.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int kezdox = 0, kezdoy = 0, 
            szelesseg = this.getWidth(), 
            magassag = this.getHeight();
	g.drawImage(hatterKep, kezdox, kezdoy, szelesseg, magassag, null); 
        if (szalVezerlo != null) {
            szalVezerlo.rajzol(g);
        }
    }

    /**
     *
     * @param szalVezerlo
     */
    public void setSzalVezerlo(SzalVezerlo szalVezerlo) {
        this.szalVezerlo = szalVezerlo;
    } 
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        szalVezerlo.kattintasVizsgalat(evt.getX(), evt.getY());
    }//GEN-LAST:event_formMousePressed

    /**
     *Eltüntei ezt a framet, majd megjelenít egy másikat.
     */
    public void fomenu(){
        InduloFrame induloFrame = new InduloFrame();
        induloFrame.setVisible(true);
        JFrame frame = (JFrame) SwingUtilities.getRoot(this);
        frame.setVisible(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
