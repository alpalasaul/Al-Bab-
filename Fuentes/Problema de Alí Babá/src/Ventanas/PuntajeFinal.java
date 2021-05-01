/*
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//SISTEMA : ALIBABÁ Y LOS CIENTO UN MIL LADRONES
//MODULO : PuntajeFinal
//PROCESO : Juego
//DESCRIPCION : El codigó cargará el record del jugador despues de cada juego
//ANALISTAS : Ing. Mauro Rosas
//PROGRAMADOR : Saúl Alpala, Doménica Erazo, Samantha Jara
//FECHA CREACION, MODIFICACION:23 de agosto 2020, 20 de septiembre 2020
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 

 */
package Ventanas;

import Clases.Pregunta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class PuntajeFinal extends javax.swing.JFrame {

    Timer t;
    Timer t1;
    Timer t2;
    Timer t3;
    int k = 0;
    int f = 0;
    int g = 0;
    int x = 100;
    public static String icono;

    public PuntajeFinal() {
        initComponents();

        setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
        setLocationRelativeTo(null);
        cargarPuntaje();
        btn_regresar.setOpaque(false);
        btn_regresar.setBorder(BorderFactory.createEmptyBorder());
        btn_regresar.setContentAreaFilled(false);
        btn_regresar.setToolTipText("regresar");
        btn_reintentar.setOpaque(false);
        btn_reintentar.setBorder(BorderFactory.createEmptyBorder());
        btn_reintentar.setContentAreaFilled(false);
        btn_reintentar.setToolTipText("reiniciar partida");
        txt_nombre.setText(IniciarSesion.listaJugadores.get(IniciarSesion.posicion).getNombre());
        avatar1.setIcon(new ImageIcon(getClass().getResource(IniciarSesion.listaJugadores.get(IniciarSesion.posicion).getAvatar())));
        jLabel9.setVisible(false);
        jLabel10.setVisible(false);
        jLabel11.setVisible(false);
        jLabel12.setVisible(false);
        jLabel13.setVisible(false);
        jLabel14.setVisible(false);
        jLabel17.setVisible(false);

    }

    public void cargarPuntaje() {
        t = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (k <= Juego.recordAlforja1) {
                    rSProgressCircle2.setValue(k);

                } else {
                    t.stop();
                    k = 0;
                }

                k++;
            }
        });

        t1 = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (f <= Juego.recordAlforja2) {
                    rSProgressCircle1.setValue(f);

                } else {
                    t1.stop();
                    f = 0;
                    t2 = new Timer(20, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            if (g <= Juego.logroFin) {
                                rSProgressCircle3.setValue(g);

                            } else {
                                t2.stop();
                                g = 0;
                                if (Juego.logroFin >= 70 && Juego.contador != 2) {
                                    jLabel9.setVisible(true);
                                    jLabel10.setVisible(true);
                                    jLabel11.setVisible(true);
                                } else if (Juego.contador == 2) {
                                    jLabel17.setVisible(true);

                                    t3 = new Timer(40, new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {

                                            if (x >= 0) {
                                                rSProgressCircle1.setValue(x);
                                                rSProgressCircle2.setValue(x);
                                                rSProgressCircle3.setValue(x);

                                            } else {
                                                t3.stop();
                                                jLabel12.setVisible(true);
                                                jLabel13.setVisible(true);
                                                jLabel14.setVisible(true);
                                                x = 100;
                                            }

                                            x--;
                                        }
                                    });
                                    t3.start();
                                    t2.stop(); // agregada

                                } else {

                                    jLabel12.setVisible(true);
                                    jLabel13.setVisible(true);
                                    jLabel14.setVisible(true);

                                }
                            }

                            g++;
                        }
                    });
                    t2.start();
                }

                f++;
            }
        });

        t.start();
        t1.start();
    }

    public static void setIcono(String icono) {
        PuntajeFinal.icono = icono;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        btn_reintentar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        avatar1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        rSProgressCircle2 = new rojerusan.componentes.RSProgressCircle();
        rSProgressCircle3 = new rojerusan.componentes.RSProgressCircle();
        txt_nombre = new jtextfieldround.JTextFieldRound();
        jLabel1 = new javax.swing.JLabel();
        rSProgressCircle1 = new rojerusan.componentes.RSProgressCircle();
        btn_regresar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_reintentar.setFont(new java.awt.Font("Lucida Calligraphy", 0, 13)); // NOI18N
        btn_reintentar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Restar.png"))); // NOI18N
        btn_reintentar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_reintentarMouseClicked(evt);
            }
        });
        btn_reintentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reintentarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_reintentar, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 0, 70, 70));

        jLabel12.setFont(new java.awt.Font("Lucida Calligraphy", 1, 48)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("¡Oh no!");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 350, -1, -1));

        jLabel17.setFont(new java.awt.Font("Lucida Calligraphy", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 51, 102));
        jLabel17.setText("¡Las alforjas se han roto!");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(616, 400, 340, -1));

        jLabel13.setFont(new java.awt.Font("Lucida Calligraphy", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Estuviste cerca,");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, -1, -1));

        jLabel14.setFont(new java.awt.Font("Lucida Calligraphy", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("suerte para la proxima");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, -1, -1));
        getContentPane().add(avatar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 160, 160));

        jLabel3.setFont(new java.awt.Font("Lucida Calligraphy", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Primera alforja");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, 170, 20));

        jLabel9.setFont(new java.awt.Font("Lucida Calligraphy", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("un éxito");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 450, -1, -1));

        jLabel10.setFont(new java.awt.Font("Lucida Calligraphy", 1, 48)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Felicidades");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, -1));

        jLabel5.setFont(new java.awt.Font("Lucida Calligraphy", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("alcanzado");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 150, -1, -1));

        jLabel6.setFont(new java.awt.Font("Lucida Calligraphy", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Beneficio ");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 120, -1, -1));

        jLabel11.setFont(new java.awt.Font("Lucida Calligraphy", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("el robo ha sido");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, -1, -1));

        jLabel7.setFont(new java.awt.Font("Lucida Calligraphy", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Segunda alforja");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 450, 190, -1));

        jLabel8.setFont(new java.awt.Font("Lucida Calligraphy", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Record ");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 120, -1, -1));

        rSProgressCircle2.setForeground(new java.awt.Color(255, 204, 0));
        rSProgressCircle2.setValue(0);
        rSProgressCircle2.setColorText(new java.awt.Color(51, 51, 51));
        rSProgressCircle2.setFont(new java.awt.Font("Roboto Bold", 1, 24)); // NOI18N
        getContentPane().add(rSProgressCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 170, 110, 100));

        rSProgressCircle3.setForeground(new java.awt.Color(0, 204, 0));
        rSProgressCircle3.setValue(0);
        rSProgressCircle3.setColorText(new java.awt.Color(51, 51, 51));
        rSProgressCircle3.setFont(new java.awt.Font("Roboto Bold", 1, 24)); // NOI18N
        getContentPane().add(rSProgressCircle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 210, 200, 190));

        txt_nombre.setFont(new java.awt.Font("Lucida Calligraphy", 0, 18)); // NOI18N
        txt_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombreActionPerformed(evt);
            }
        });
        getContentPane().add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 200, 30));

        jLabel1.setFont(new java.awt.Font("Lucida Calligraphy", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Resultado finales");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, -1, -1));

        rSProgressCircle1.setForeground(new java.awt.Color(255, 204, 0));
        rSProgressCircle1.setToolTipText("");
        rSProgressCircle1.setValue(0);
        rSProgressCircle1.setColorText(new java.awt.Color(51, 51, 51));
        rSProgressCircle1.setFont(new java.awt.Font("Roboto Bold", 1, 24)); // NOI18N
        getContentPane().add(rSProgressCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 330, 110, 100));

        btn_regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/atras.png"))); // NOI18N
        btn_regresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_regresarMouseClicked(evt);
            }
        });
        btn_regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_regresarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_regresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, -1));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/prueba41.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regresarActionPerformed

//        Juego.sonar.stop();
//
//        MenuPrincipal bi = new MenuPrincipal();
//        bi.musica();
//        Historia.setPrueba1(0);
//        AccesoCueva.setPrueba3(0);
//        IntermediaSplash.setPrueba3(0);
//        IntermediaMenuPrincipal.setPrueba5(0);
//        AccesoPuerta.setPrueba3(0);
//        Resultados.setPrueba3(0);
//        Genio.setPrueba2(0);
//        Juego.setPrueba3(0);
//        bi.setVisible(true);
//        this.dispose();
//

    }//GEN-LAST:event_btn_regresarActionPerformed

    private void txt_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreActionPerformed

    }//GEN-LAST:event_txt_nombreActionPerformed

    private void btn_reintentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reintentarActionPerformed

       

    }//GEN-LAST:event_btn_reintentarActionPerformed

    private void btn_reintentarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_reintentarMouseClicked
         Juego.sonar.stop();
        Juego v = new Juego();
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_reintentarMouseClicked

    private void btn_regresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_regresarMouseClicked
      String strRespuesta="";
      strRespuesta=(new Pregunta().preguntaConfirmacion("¿Quieres regresar al menú principal?"));
      if(strRespuesta=="SI"){
          Juego.sonar.stop();
          MenuPrincipal bi=new MenuPrincipal();
          bi.musica();
          bi.setVisible(true);
          this.dispose();
  
      }else{
        
    }
    }//GEN-LAST:event_btn_regresarMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PuntajeFinal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel avatar1;
    private javax.swing.JButton btn_regresar;
    private javax.swing.JButton btn_reintentar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private rojerusan.componentes.RSProgressCircle rSProgressCircle1;
    private rojerusan.componentes.RSProgressCircle rSProgressCircle2;
    private rojerusan.componentes.RSProgressCircle rSProgressCircle3;
    private jtextfieldround.JTextFieldRound txt_nombre;
    // End of variables declaration//GEN-END:variables
}
