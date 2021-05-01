/*
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//SISTEMA : ALIBABÁ Y LOS CIENTO UN MIL LADRONES
//MODULO : MenuPrincipal
//PROCESO : Bienvenida
//DESCRIPCION : El código indica el paso a la ventana de  configuraciones o a la ventana del juego
//ANALISTAS : Ing. Mauro Rosas
//PROGRAMADOR : Saúl Alpala, Doménica Erazo, Samantha Jara
//FECHA CREACION, MODIFICACION:23 de agosto 2020, 20 de septiembre 2020
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 

 */
package Ventanas;

import Clases.Pregunta;
import java.applet.AudioClip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class MenuPrincipal extends javax.swing.JFrame {

    public static AudioClip sonar;

    public MenuPrincipal() {
        initComponents();

        setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
        setLocationRelativeTo(null);

        btn_jugar.setOpaque(false);
        btn_jugar.setBorder(BorderFactory.createEmptyBorder());
        btn_jugar.setContentAreaFilled(false);
        btn_salir.setOpaque(false);
        btn_salir.setBorder(BorderFactory.createEmptyBorder());
        btn_salir.setContentAreaFilled(false);
        btn_salir.setToolTipText("salir");
        btn_configuraciones.setOpaque(false);
        btn_configuraciones.setBorder(BorderFactory.createEmptyBorder());
        btn_configuraciones.setContentAreaFilled(false);
        btn_configuraciones.setToolTipText("Ayuda");

    }

    public void musica() {
        sonar = java.applet.Applet.newAudioClip(getClass().getResource("/sonido/aladin.wav"));
        sonar.play();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_salir = new javax.swing.JButton();
        btn_configuraciones = new javax.swing.JButton();
        btn_jugar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/salir.png"))); // NOI18N
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });
        getContentPane().add(btn_salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 0, 70, 70));

        btn_configuraciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/engranaje (1).png"))); // NOI18N
        btn_configuraciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_configuracionesActionPerformed(evt);
            }
        });
        getContentPane().add(btn_configuraciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 80, 80));

        btn_jugar.setBackground(new java.awt.Color(153, 0, 255));
        btn_jugar.setFont(new java.awt.Font("Kristen ITC", 1, 14)); // NOI18N
        btn_jugar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/play.png"))); // NOI18N
        btn_jugar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_jugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_jugarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_jugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 270, 60, 60));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText(" Versión 2.1.75 ©");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, 20));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Lucida Handwriting", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Empieza el Juego");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 330, 180, 30));

        jLabel4.setFont(new java.awt.Font("Lucida Handwriting", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("y los Ciento un Mil Ladrones");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, -1, -1));

        jLabel3.setFont(new java.awt.Font("Lucida Handwriting", 0, 50)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("Alí Babá ");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 320, -1));

        jLabel2.setFont(new java.awt.Font("Lucida Handwriting", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Bienvenido al Juego");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bienvenidaw.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-60, 0, 970, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_jugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_jugarActionPerformed

        IntermediaMenuPrincipal v = new IntermediaMenuPrincipal();
        v.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btn_jugarActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        String strRespuesta = "";
        strRespuesta = (new Pregunta().preguntaConfirmacion("¿Estás seguro que quieres salir del juego?"));
        if ("SI".equals(strRespuesta)) {
            SplashScreen.tf = System.currentTimeMillis();
            SplashScreen.tiempo = SplashScreen.tf - SplashScreen.ti;
            System.out.println("" + SplashScreen.tiempo);
            System.exit(0);
        }

    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_configuracionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_configuracionesActionPerformed
        ConfiguracionGeneral con = new ConfiguracionGeneral();
        con.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_configuracionesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // new Bienvenida().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_configuraciones;
    private javax.swing.JButton btn_jugar;
    private javax.swing.JButton btn_salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
