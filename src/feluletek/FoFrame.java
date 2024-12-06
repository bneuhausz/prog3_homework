/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feluletek;

import vezerles.SzalVezerlo;

/**
 *
 * @author nbx
 */
public class FoFrame extends javax.swing.JFrame {

    private int szelesseg = 1116;
    private int magassag = 740;
    private String cim = "Programozás 3 vizsgafeladat";
    
    /**
     * Creates new form NewJFrame
     */
    public FoFrame() {
        initComponents();
        this.setSize(szelesseg, magassag);
        this.setTitle(cim);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        beallit();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kijelzoPanel2 = new feluletek.KijelzoPanel();
        jatekosPanel1 = new feluletek.JatekosPanel();
        jatekPanel1 = new feluletek.JatekPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(kijelzoPanel2, java.awt.BorderLayout.PAGE_START);
        getContentPane().add(jatekosPanel1, java.awt.BorderLayout.LINE_END);

        javax.swing.GroupLayout jatekPanel1Layout = new javax.swing.GroupLayout(jatekPanel1);
        jatekPanel1.setLayout(jatekPanel1Layout);
        jatekPanel1Layout.setHorizontalGroup(
            jatekPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 916, Short.MAX_VALUE)
        );
        jatekPanel1Layout.setVerticalGroup(
            jatekPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );

        getContentPane().add(jatekPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FoFrame().start();
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private feluletek.JatekPanel jatekPanel1;
    private feluletek.JatekosPanel jatekosPanel1;
    private feluletek.KijelzoPanel kijelzoPanel2;
    // End of variables declaration//GEN-END:variables

    /**
     *Láthatóvá teszi a framet, majd beállítja a vezérlő és a panelek közötti kapcsolatot.
     */
    private void beallit() {
        setVisible(true);
        SzalVezerlo szalVezerlo = new SzalVezerlo(jatekPanel1, jatekosPanel1, kijelzoPanel2);
        jatekPanel1.setSzalVezerlo(szalVezerlo);
        kijelzoPanel2.setSzalVezerlo(szalVezerlo);
        jatekosPanel1.setSzalVezerlo(szalVezerlo);
        szalVezerlo.beallit();
    }
}