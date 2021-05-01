/*
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//SISTEMA : ALIBABÁ Y LOS CIENTO UN MIL LADRONES
//MODULO : Juego
//PROCESO : Juego
//DESCRIPCION : El código nos indicara la lista de objetos a robar, esta validado en caso de que las Alforjas se llen o rompan y nos indica el tiempo
//ANALISTAS : Ing. Mauro Rosas
//PROGRAMADOR : Saúl Alpala, Doménica Erazo, Samantha Jara
//FECHA CREACION, MODIFICACION:23 de agosto 2020, 20 de septiembre 2020
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 

 */
package Ventanas;

import Clases.Archivo;
import Clases.Elemento;
import Clases.Jugador;
import Clases.Pregunta;
import Clases.ProblemaAliBaba;
import com.sun.awt.AWTUtilities;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Juego extends javax.swing.JFrame {

    double i = 50, j = 1;
    static Timer time = null;
    static int r = 60;
    public static int prueba6 = 0;

    public static int getPrueba6() {
        return prueba6;
    }

    public static void setPrueba6(int prueba6) {
        Juego.prueba6 = prueba6;
    }
    static ArrayList<Elemento> mejorAlforja1 = new ArrayList<>(); // Algoritmo
    static ArrayList<Elemento> mejorAlforja2 = new ArrayList<>(); // Algoritmo 

    ArrayList<Elemento> primeraAlforja = new ArrayList<>(); // Robada
    ArrayList<Elemento> segundaAlforja = new ArrayList<>(); // Robada 

    public static int prueba3 = 0;
    public static AudioClip sonar;
    static final int pesoAlforja1 = 20;
    static final int pesoAlforja2 = 16;

    static double pesoActual = 0;
    static double beneficioActual = 0;

    static int contador = 0;
    static int k = 0;
    static int cargarLista = 0;

    static double logroFin = 0;

    // Partidas
    int partidaPerdida = 0;
    int partidaGanada = 0;
    static double recordOriginal = IniciarSesion.listaJugadores.get(IniciarSesion.posicion).getRecord();

    // Calcular peso
    static double pesoFinaldAlforja1 = 0;
    static double pesoFinaldAlforja2 = 0;

    // Calcular Beneficio 
    double benificioFinalAlforja1 = 0;
    double benificioFinalAlforja2 = 0;

    // Cacular peso mejor mochila
    static double pesoSolucion1 = 0;
    static double pesoSolucion2 = 0;

    // Calcular beneficio mejor mochila
    static double beneficioSolucion1 = 0;
    static double beneficioSolucion2 = 0;

    // Alforja 1 Tabla de Robo
    static int countTablaAlforja1 = 0;
    String data[][] = new String[24][1];
    int contadorTablaAlforja1 = 0;
    String nombreColumnas[] = {"Nombre"};

    //Enviar record
    static double recordAlforja1 = 0;
    static double recordAlforja2 = 0;

    // Alforja 2 Tabla de Robo
    static int countTablaAlforja2 = 0;
    String data2[][] = new String[24][1];
    int contadorTablaAlforja2 = 0;
    String nombreColumnas2[] = {"Nombre"};

    public Juego() {

        initComponents();
        musica();

        primeraAlforja.clear();
        segundaAlforja.clear();
        mejorAlforja1.clear();
        mejorAlforja2.clear();
        contador = 0;
        pesoActual = 0;
        beneficioActual = 0;
        k = 0;

        recordAlforja1 = 0;
        recordAlforja2 = 0;
        pesoFinaldAlforja1 = 0;
        pesoFinaldAlforja2 = 0;

        pesoSolucion1 = 0;
        pesoSolucion2 = 0;

        benificioFinalAlforja1 = 0;
        benificioFinalAlforja2 = 0;

        beneficioSolucion1 = 0;
        beneficioSolucion2 = 0;

        logroFin = 0;

        botonesTransparentes();

        jLabel2.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        btn_abandonar.setVisible(false);
        btn_terminar.setVisible(false);

        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
        musica();
        ((JPanel) getContentPane()).setOpaque(false);
        AWTUtilities.setWindowOpaque(this, false);

    }

    public void musica() {
        sonar = java.applet.Applet.newAudioClip(getClass().getResource("/sonido/JuegoCancion.wav"));
        sonar.play();
    }

    public static void cargarTesoros() {
        ProblemaAliBaba ali = new ProblemaAliBaba();
        ali.cargarDatos();

    }

    public void logro() {

        beneficioSolucion1 = 0;
        beneficioSolucion2 = 0;
        for (Elemento elemento : mejorAlforja1) {
            beneficioSolucion1 += elemento.getValor();
        }
        for (Elemento elemento : mejorAlforja2) {
            beneficioSolucion2 += elemento.getValor();
        }

        recordAlforja1 = 0;
        recordAlforja2 = 0;

        recordAlforja1 = (benificioFinalAlforja1 * 100) / (beneficioSolucion1);

        recordAlforja2 = (benificioFinalAlforja2 * 100) / (beneficioSolucion2);

        logroFinal();
    }

    public void logroFinal() {
        logroFin = (recordAlforja1 + recordAlforja2) / 2;

        PuntajeFinal ver = new PuntajeFinal();
        ver.setVisible(true);
        this.dispose();

        try {
            guardarRecord();
        } catch (Exception e) {

        }
    }

    public void guardarRecord() throws IOException {

        Archivo archivito = new Archivo();
        String direccion = "C:/AliBabá/Jugadores.txt";

        if (logroFin >= 70 && pesoActual <= pesoAlforja2 && contador != 2 /* contador != 4  */) { // cambio de contador +1
            partidaGanada = 1;

        } else if (logroFin < 70 || pesoActual > pesoAlforja2 || r == 0 || contador == 2) {
            partidaPerdida = 1;

        }

        if (logroFin > recordOriginal && pesoActual <= pesoAlforja2/* && r != 0 */) {
            recordOriginal = logroFin;
        }

        Jugador jugadorModificado = new Jugador(IniciarSesion.listaJugadores.get(IniciarSesion.posicion).getNombre(),
                IniciarSesion.listaJugadores.get(IniciarSesion.posicion).getEdad(),
                IniciarSesion.listaJugadores.get(IniciarSesion.posicion).getContrasena(),
                IniciarSesion.listaJugadores.get(IniciarSesion.posicion).getJugadas() + 1, // Partidas jugadas
                IniciarSesion.listaJugadores.get(IniciarSesion.posicion).getGanadas() + partidaGanada, // Partidas ganadas
                IniciarSesion.listaJugadores.get(IniciarSesion.posicion).getPerdidas() + partidaPerdida, // Partidas perdidas
                recordOriginal, // Nuevo record
                IniciarSesion.listaJugadores.get(IniciarSesion.posicion).getAvatar());
        archivito.modificar(IniciarSesion.listaJugadores.get(IniciarSesion.posicion), jugadorModificado, IniciarSesion.listaJugadores, direccion);

    }

    public void solucion() {

        mejorAlforja1.clear();

        ProblemaAliBaba.resolverProblema(0, mejorAlforja1, pesoAlforja1);

        ProblemaAliBaba.calcular();

        mejorAlforja2.clear();

        ProblemaAliBaba.setContador(1);

        ProblemaAliBaba.resolverProblema(0, mejorAlforja2, pesoAlforja2);

        ProblemaAliBaba.calcular();

    }

    public static void cargarLista() {

        String nombreColumnas[] = {"Nombre", "Valor", "Peso"};
        String data[][] = new String[ProblemaAliBaba.almacen.size()][3];

        for (int k = 0; k < ProblemaAliBaba.almacen.size(); k++) {
            data[k][0] = ProblemaAliBaba.almacen.get(k).getNombre();
            data[k][1] = ProblemaAliBaba.almacen.get(k).getValor() + "";
            data[k][2] = ProblemaAliBaba.almacen.get(k).getPeso() + "";

        }

        tabla_tesoros.setModel(new DefaultTableModel(data, nombreColumnas));

        TableColumnModel columnModel = tabla_tesoros.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(170);
        columnModel.getColumn(1).setPreferredWidth(40);
        columnModel.getColumn(2).setPreferredWidth(40);
        tabla_tesoros.setTableHeader(null);
    }

    public void tablaAlforja2(int posicion) {

    }

    public boolean pesoLimite(int peso) {
        boolean bandera = true;
        if (pesoActual <= peso) {// sumatoria <= peso
            bandera = false;
        }
        return bandera;
    }

    public void sumatoriaPesoBeneficio(double recibirPeso, double recibirBeneficio) {
        pesoActual += recibirPeso;
        beneficioActual += recibirBeneficio;

    }

    public void robarTesoro(int posicion) {

        if (contador == 0) {

            sumatoriaPesoBeneficio(ProblemaAliBaba.almacen.get(posicion).getPeso(), ProblemaAliBaba.almacen.get(posicion).getValor()); // Problema con el peso 

            if (pesoActual <= pesoAlforja1) { //16.8 + 3

                primeraAlforja.add(ProblemaAliBaba.almacen.get(posicion));
                // Obteniedo el peso

                pesoFinaldAlforja1 = pesoActual;

                // Obteniendo el beneficio 
                benificioFinalAlforja1 = beneficioActual;

                textarea_alforja1.append(ProblemaAliBaba.almacen.get(posicion).getNombre() + "\n");

            } else {
                contador++;
                pesoActual = 0; // Crear una varialbe peso para cada alforja
                beneficioActual = 0;
            }
        }

        if (contador == 1) {
            if (k == 0) {
                time.stop();
                Icon n = new ImageIcon(getClass().getResource("/imagenes/1intentos.png"));
                JOptionPane.showMessageDialog(null, "Ten cuidado la alforja uno está muy llena", "Cambio de alforja", JOptionPane.CANCEL_OPTION, n);
                k++;
                time.start();
            }

            sumatoriaPesoBeneficio(ProblemaAliBaba.almacen.get(posicion).getPeso(), ProblemaAliBaba.almacen.get(posicion).getValor());

            if (pesoActual <= pesoAlforja2) {
                segundaAlforja.add(ProblemaAliBaba.almacen.get(posicion));
                // Obteniedo el peso
                pesoFinaldAlforja2 = pesoActual;
                // Obteniedno el beneficio
                benificioFinalAlforja2 = beneficioActual;

                txtarea_alforja2.append(ProblemaAliBaba.almacen.get(posicion).getNombre() + "\n");

                // Fin guardar 2
            } else {

                contador++;
            }
        }
        if (contador == 2) {
            Icon e = new ImageIcon(getClass().getResource("/imagenes/esqueleto.png"));
            JOptionPane.showMessageDialog(null, "¡Oh no! se han roto las alforjas", "PERDISTE", JOptionPane.CANCEL_OPTION, e);

            time.stop();
            logro();

        }
        if (contador == 3) { // Terminar robo

            r = 60;
            logro();

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btn_abandonar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btn_terminar = new javax.swing.JButton();
        btn_lingote = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_tesoros = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btn_alfombra = new javax.swing.JButton();
        btn_lampara = new javax.swing.JButton();
        btn_cofreGrande = new javax.swing.JButton();
        btn_bolsaMediana = new javax.swing.JButton();
        btn_lista = new javax.swing.JButton();
        btn_BolsaGrande = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtarea_alforja2 = new javax.swing.JTextArea();
        btn_bolsaGrande = new javax.swing.JButton();
        btn_cofreTesoro = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textarea_alforja1 = new javax.swing.JTextArea();
        btn_monedas = new javax.swing.JButton();
        btn_vasija = new javax.swing.JButton();
        btn_cofreTesoro2 = new javax.swing.JButton();
        btn_barril = new javax.swing.JButton();
        btn_tridente = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_esmeralda = new javax.swing.JButton();
        btn_bolsaPequena = new javax.swing.JButton();
        btn_tridente2 = new javax.swing.JButton();
        btn_anillo = new javax.swing.JButton();
        btn_tela = new javax.swing.JButton();
        btn_plato = new javax.swing.JButton();
        btn_plato2 = new javax.swing.JButton();
        btn_rubi = new javax.swing.JButton();
        btn_perlas = new javax.swing.JButton();
        btn_diamante = new javax.swing.JButton();
        btn_vaso = new javax.swing.JButton();
        btn_corona = new javax.swing.JButton();
        btn_zafiro = new javax.swing.JButton();
        btn_agata = new javax.swing.JButton();
        btn_agata2 = new javax.swing.JButton();
        btn_esmeralda2 = new javax.swing.JButton();
        btn_cadena = new javax.swing.JButton();
        btn_barril2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Arial", 1, 42)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("00:60");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 50, 110, 40));

        jLabel21.setFont(new java.awt.Font("Lucida Calligraphy", 0, 16)); // NOI18N
        jLabel21.setText("Si se rompen, perderás todo");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 250, 20));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Temporizador.png"))); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, 160, 60));

        jLabel18.setFont(new java.awt.Font("Lucida Calligraphy", 0, 16)); // NOI18N
        jLabel18.setText("¡No lo olvides!");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, -1, 20));

        jLabel19.setFont(new java.awt.Font("Lucida Calligraphy", 0, 16)); // NOI18N
        jLabel19.setText("Las alforjas soportan hasta");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, -1, -1));

        btn_abandonar.setFont(new java.awt.Font("Lucida Calligraphy", 0, 13)); // NOI18N
        btn_abandonar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/AbandonarRobo.png"))); // NOI18N
        btn_abandonar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_abandonarMouseClicked(evt);
            }
        });
        btn_abandonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_abandonarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_abandonar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 120, 40));

        jLabel7.setFont(new java.awt.Font("Lucida Calligraphy", 0, 18)); // NOI18N
        jLabel7.setText("Alforja 2 ");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 300, 140, -1));

        jLabel20.setFont(new java.awt.Font("Lucida Calligraphy", 0, 16)); // NOI18N
        jLabel20.setText("20 kg y 15 kg ");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, -1));

        jLabel8.setFont(new java.awt.Font("Lucida Calligraphy", 0, 18)); // NOI18N
        jLabel8.setText("Alforja 1 ");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 110, 140, -1));

        jLabel11.setFont(new java.awt.Font("Lucida Calligraphy", 0, 16)); // NOI18N
        jLabel11.setText("ayudaría a saber");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        jLabel12.setFont(new java.awt.Font("Lucida Calligraphy", 0, 16)); // NOI18N
        jLabel12.setText("los tesoros");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        jLabel13.setFont(new java.awt.Font("Lucida Calligraphy", 0, 16)); // NOI18N
        jLabel13.setText("¡Vaya!");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jLabel16.setFont(new java.awt.Font("Lucida Calligraphy", 0, 16)); // NOI18N
        jLabel16.setText("¡Son muchos tesoros!");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        jLabel14.setFont(new java.awt.Font("Lucida Calligraphy", 0, 16)); // NOI18N
        jLabel14.setText("¡Busca la lista!");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));

        jLabel22.setFont(new java.awt.Font("Lucida Calligraphy", 0, 16)); // NOI18N
        jLabel22.setText("Roba lo más valioso para ganar ");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, -1, 20));

        jLabel23.setFont(new java.awt.Font("Lucida Calligraphy", 0, 16)); // NOI18N
        jLabel23.setText("de ganancias que robarás");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, -1, 20));

        jLabel24.setFont(new java.awt.Font("Lucida Calligraphy", 0, 16)); // NOI18N
        jLabel24.setText("¡Ten cuidado! ");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, -1, 20));

        jLabel15.setFont(new java.awt.Font("Lucida Calligraphy", 0, 16)); // NOI18N
        jLabel15.setText("Quizás una lista nos");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        jLabel25.setFont(new java.awt.Font("Lucida Calligraphy", 0, 16)); // NOI18N
        jLabel25.setText("Necesitas más del 70% en valor");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, -1, 20));

        jLabel17.setFont(new java.awt.Font("Lucida Calligraphy", 0, 16)); // NOI18N
        jLabel17.setText("que tiene la cueva ");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));

        btn_terminar.setFont(new java.awt.Font("Lucida Calligraphy", 0, 13)); // NOI18N
        btn_terminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FinRoboFinal.png"))); // NOI18N
        btn_terminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_terminarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_terminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 30, 130, 50));

        btn_lingote.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Lingotes.png"))); // NOI18N
        btn_lingote.setEnabled(false);
        btn_lingote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lingoteActionPerformed(evt);
            }
        });
        getContentPane().add(btn_lingote, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 310, 70, 60));

        tabla_tesoros.setFont(new java.awt.Font("Lucida Calligraphy", 0, 13)); // NOI18N
        tabla_tesoros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Valor", "Peso"
            }
        ));
        tabla_tesoros.setEnabled(false);
        tabla_tesoros.setTableHeader(null);
        jScrollPane3.setViewportView(tabla_tesoros);
        if (tabla_tesoros.getColumnModel().getColumnCount() > 0) {
            tabla_tesoros.getColumnModel().getColumn(0).setPreferredWidth(300);
        }

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 300, 390));

        jLabel2.setFont(new java.awt.Font("Lucida Calligraphy", 1, 14)); // NOI18N
        jLabel2.setText("Lista de tesoros");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 140, -1));

        jLabel5.setFont(new java.awt.Font("Lucida Calligraphy", 1, 14)); // NOI18N
        jLabel5.setText("Peso");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, -1, -1));

        jLabel6.setFont(new java.awt.Font("Lucida Calligraphy", 1, 14)); // NOI18N
        jLabel6.setText("Valor");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, -1, -1));

        btn_alfombra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Alfombras.png"))); // NOI18N
        btn_alfombra.setEnabled(false);
        btn_alfombra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_alfombraActionPerformed(evt);
            }
        });
        getContentPane().add(btn_alfombra, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 300, 110, 60));

        btn_lampara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Lampara_1.png"))); // NOI18N
        btn_lampara.setEnabled(false);
        btn_lampara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lamparaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_lampara, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 290, 90, 60));

        btn_cofreGrande.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Cofre_1.png"))); // NOI18N
        btn_cofreGrande.setEnabled(false);
        btn_cofreGrande.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cofreGrandeActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cofreGrande, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 400, 140, 150));

        btn_bolsaMediana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Bolsa1.png"))); // NOI18N
        btn_bolsaMediana.setEnabled(false);
        btn_bolsaMediana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bolsaMedianaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_bolsaMediana, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 470, 110, 70));

        btn_lista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/perga.png"))); // NOI18N
        btn_lista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_listaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_lista, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, 50, 50));

        btn_BolsaGrande.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Bolsa3.png"))); // NOI18N
        btn_BolsaGrande.setEnabled(false);
        btn_BolsaGrande.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BolsaGrandeActionPerformed(evt);
            }
        });
        getContentPane().add(btn_BolsaGrande, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 380, 90, 150));

        txtarea_alforja2.setEditable(false);
        txtarea_alforja2.setColumns(20);
        txtarea_alforja2.setFont(new java.awt.Font("Lucida Calligraphy", 0, 14)); // NOI18N
        txtarea_alforja2.setRows(5);
        jScrollPane5.setViewportView(txtarea_alforja2);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 330, -1, 150));

        btn_bolsaGrande.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bolsa2.png"))); // NOI18N
        btn_bolsaGrande.setEnabled(false);
        btn_bolsaGrande.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bolsaGrandeActionPerformed(evt);
            }
        });
        getContentPane().add(btn_bolsaGrande, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 380, 100, 160));

        btn_cofreTesoro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Cofre2.png"))); // NOI18N
        btn_cofreTesoro.setEnabled(false);
        btn_cofreTesoro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cofreTesoroActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cofreTesoro, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, 80, 100));

        textarea_alforja1.setEditable(false);
        textarea_alforja1.setColumns(20);
        textarea_alforja1.setFont(new java.awt.Font("Lucida Calligraphy", 0, 14)); // NOI18N
        textarea_alforja1.setRows(5);
        jScrollPane1.setViewportView(textarea_alforja1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 140, -1, 150));

        btn_monedas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Monedas.png"))); // NOI18N
        btn_monedas.setEnabled(false);
        btn_monedas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_monedasActionPerformed(evt);
            }
        });
        getContentPane().add(btn_monedas, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 480, 250, 80));

        btn_vasija.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Vasija.png"))); // NOI18N
        btn_vasija.setEnabled(false);
        btn_vasija.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_vasijaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_vasija, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 340, 110, -1));

        btn_cofreTesoro2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Cofre1.png"))); // NOI18N
        btn_cofreTesoro2.setEnabled(false);
        btn_cofreTesoro2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cofreTesoro2ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cofreTesoro2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 240, 110, 90));

        btn_barril.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Barril.png"))); // NOI18N
        btn_barril.setEnabled(false);
        btn_barril.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_barrilActionPerformed(evt);
            }
        });
        getContentPane().add(btn_barril, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 270, 70, 70));

        btn_tridente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Tridente.png"))); // NOI18N
        btn_tridente.setEnabled(false);
        btn_tridente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tridenteActionPerformed(evt);
            }
        });
        getContentPane().add(btn_tridente, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 190, 30, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/PergaminoFinal.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 10, 390, 550));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/PergaminoFinal.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 10, 390, 550));

        btn_esmeralda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Esmeralda.png"))); // NOI18N
        btn_esmeralda.setEnabled(false);
        btn_esmeralda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_esmeraldaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_esmeralda, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 200, 20, 20));

        btn_bolsaPequena.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Bolsa4.png"))); // NOI18N
        btn_bolsaPequena.setEnabled(false);
        btn_bolsaPequena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bolsaPequenaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_bolsaPequena, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 250, 80, 60));

        btn_tridente2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Tridente1.png"))); // NOI18N
        btn_tridente2.setEnabled(false);
        btn_tridente2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tridente2ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_tridente2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 280, 30, 30));

        btn_anillo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Anillo.png"))); // NOI18N
        btn_anillo.setEnabled(false);
        btn_anillo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_anilloActionPerformed(evt);
            }
        });
        getContentPane().add(btn_anillo, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 260, 50, -1));

        btn_tela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Tela.png"))); // NOI18N
        btn_tela.setEnabled(false);
        btn_tela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_telaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_tela, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 230, 90, 60));

        btn_plato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Plato.png"))); // NOI18N
        btn_plato.setEnabled(false);
        btn_plato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_platoActionPerformed(evt);
            }
        });
        getContentPane().add(btn_plato, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 310, 30, 30));

        btn_plato2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/PlatoPerlas.png"))); // NOI18N
        btn_plato2.setEnabled(false);
        btn_plato2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_plato2ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_plato2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 270, 30, 30));

        btn_rubi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Rubi.png"))); // NOI18N
        btn_rubi.setEnabled(false);
        btn_rubi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_rubiActionPerformed(evt);
            }
        });
        getContentPane().add(btn_rubi, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 240, 20, 20));

        btn_perlas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Perlas.png"))); // NOI18N
        btn_perlas.setEnabled(false);
        btn_perlas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_perlasActionPerformed(evt);
            }
        });
        getContentPane().add(btn_perlas, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 320, 80, 70));

        btn_diamante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Diamante.png"))); // NOI18N
        btn_diamante.setEnabled(false);
        btn_diamante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_diamanteActionPerformed(evt);
            }
        });
        getContentPane().add(btn_diamante, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 290, 40, 40));

        btn_vaso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Vaso.png"))); // NOI18N
        btn_vaso.setEnabled(false);
        btn_vaso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_vasoActionPerformed(evt);
            }
        });
        getContentPane().add(btn_vaso, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 460, 60, 50));

        btn_corona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Corona.png"))); // NOI18N
        btn_corona.setEnabled(false);
        btn_corona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_coronaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_corona, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 190, 70, 40));

        btn_zafiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Zafiro1.png"))); // NOI18N
        btn_zafiro.setEnabled(false);
        btn_zafiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_zafiroActionPerformed(evt);
            }
        });
        getContentPane().add(btn_zafiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 260, 30, 40));

        btn_agata.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Zafiro2.png"))); // NOI18N
        btn_agata.setEnabled(false);
        btn_agata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agataActionPerformed(evt);
            }
        });
        getContentPane().add(btn_agata, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 350, 30, 40));

        btn_agata2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Zafiro3.png"))); // NOI18N
        btn_agata2.setEnabled(false);
        btn_agata2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agata2ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_agata2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 310, 30, 30));

        btn_esmeralda2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Esmeralda1.png"))); // NOI18N
        btn_esmeralda2.setEnabled(false);
        btn_esmeralda2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_esmeralda2ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_esmeralda2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 280, 40, 30));

        btn_cadena.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Cadena.png"))); // NOI18N
        btn_cadena.setEnabled(false);
        btn_cadena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cadenaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cadena, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 310, 80, 50));

        btn_barril2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Barril2.png"))); // NOI18N
        btn_barril2.setEnabled(false);
        btn_barril2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_barril2ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_barril2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 220, 70, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FondoJuego.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 970, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_bolsaGrandeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bolsaGrandeActionPerformed
        robarTesoro(18);
        btn_bolsaGrande.setVisible(false);
        btn_BolsaGrande.setVisible(false);

    }//GEN-LAST:event_btn_bolsaGrandeActionPerformed

    private void btn_alfombraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_alfombraActionPerformed
        robarTesoro(9);
        btn_alfombra.setVisible(false);
    }//GEN-LAST:event_btn_alfombraActionPerformed

    private void btn_cofreTesoroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cofreTesoroActionPerformed
        robarTesoro(0);
        btn_cofreTesoro.setVisible(false);
//        jButton10.setVisible(false);
    }//GEN-LAST:event_btn_cofreTesoroActionPerformed

    private void btn_lingoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lingoteActionPerformed
        robarTesoro(12);
//        tablaAlforja1(3);
        btn_lingote.setVisible(false);
    }//GEN-LAST:event_btn_lingoteActionPerformed

    private void btn_bolsaMedianaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bolsaMedianaActionPerformed
        robarTesoro(21);
        btn_bolsaMediana.setVisible(false);
    }//GEN-LAST:event_btn_bolsaMedianaActionPerformed

    private void btn_bolsaPequenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bolsaPequenaActionPerformed
        robarTesoro(23);
        btn_bolsaPequena.setVisible(false);
    }//GEN-LAST:event_btn_bolsaPequenaActionPerformed

    private void btn_esmeraldaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_esmeraldaActionPerformed
        robarTesoro(7);
        btn_esmeralda.setVisible(false);
        btn_esmeralda2.setVisible(false);
    }//GEN-LAST:event_btn_esmeraldaActionPerformed

    private void btn_perlasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_perlasActionPerformed
        robarTesoro(10);
        btn_perlas.setVisible(false);
    }//GEN-LAST:event_btn_perlasActionPerformed

    private void btn_BolsaGrandeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BolsaGrandeActionPerformed
        robarTesoro(18);
        btn_bolsaGrande.setVisible(false);
        btn_BolsaGrande.setVisible(false);
    }//GEN-LAST:event_btn_BolsaGrandeActionPerformed

    private void btn_esmeralda2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_esmeralda2ActionPerformed
        robarTesoro(7);
        btn_esmeralda.setVisible(false);
        btn_esmeralda2.setVisible(false);
    }//GEN-LAST:event_btn_esmeralda2ActionPerformed

    private void btn_anilloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_anilloActionPerformed
        robarTesoro(19);
        btn_anillo.setVisible(false);
    }//GEN-LAST:event_btn_anilloActionPerformed

    private void btn_diamanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_diamanteActionPerformed
        robarTesoro(1);
//        jButton22.setVisible(false);
    }//GEN-LAST:event_btn_diamanteActionPerformed

    private void btn_telaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_telaActionPerformed
        robarTesoro(17);
        btn_tela.setVisible(false);
    }//GEN-LAST:event_btn_telaActionPerformed

    private void btn_vasijaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_vasijaActionPerformed
        robarTesoro(20);
        btn_vasija.setVisible(false);
    }//GEN-LAST:event_btn_vasijaActionPerformed

    private void btn_coronaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_coronaActionPerformed
        robarTesoro(5);
        btn_corona.setVisible(false);
    }//GEN-LAST:event_btn_coronaActionPerformed

    private void btn_cadenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadenaActionPerformed
        robarTesoro(6);
        btn_cadena.setVisible(false);
    }//GEN-LAST:event_btn_cadenaActionPerformed

    private void btn_lamparaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lamparaActionPerformed
        robarTesoro(11);
        btn_lampara.setVisible(false);
    }//GEN-LAST:event_btn_lamparaActionPerformed

    private void btn_cofreTesoro2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cofreTesoro2ActionPerformed
        robarTesoro(0);
        btn_cofreTesoro2.setVisible(false);
//        jButton8.setVisible(false);
    }//GEN-LAST:event_btn_cofreTesoro2ActionPerformed

    private void btn_cofreGrandeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cofreGrandeActionPerformed
        robarTesoro(4);
        btn_cofreGrande.setVisible(false);
    }//GEN-LAST:event_btn_cofreGrandeActionPerformed

    private void btn_rubiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_rubiActionPerformed
        robarTesoro(15);
        btn_rubi.setVisible(false);
    }//GEN-LAST:event_btn_rubiActionPerformed

    private void btn_vasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_vasoActionPerformed
        robarTesoro(22);
        btn_vaso.setVisible(false);
    }//GEN-LAST:event_btn_vasoActionPerformed

    private void btn_zafiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_zafiroActionPerformed
        robarTesoro(16);
//        jButton28.setVisible(false);
//        jButton27.setVisible(false);
        btn_zafiro.setVisible(false);
    }//GEN-LAST:event_btn_zafiroActionPerformed

    private void btn_agataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agataActionPerformed
        robarTesoro(3);
        btn_agata.setVisible(false);
//        jButton27.setVisible(false);
//        jButton26.setVisible(false);
    }//GEN-LAST:event_btn_agataActionPerformed

    private void btn_barrilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_barrilActionPerformed
        robarTesoro(2);
        btn_barril.setVisible(false);
//        jButton31.setVisible(false);
    }//GEN-LAST:event_btn_barrilActionPerformed

    private void btn_tridenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tridenteActionPerformed
        robarTesoro(8);
        btn_tridente.setVisible(false);
        btn_tridente2.setVisible(false);
    }//GEN-LAST:event_btn_tridenteActionPerformed

    private void btn_tridente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tridente2ActionPerformed
        robarTesoro(8);
        btn_tridente.setVisible(false);
        btn_tridente2.setVisible(false);
    }//GEN-LAST:event_btn_tridente2ActionPerformed

    private void btn_plato2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_plato2ActionPerformed
        robarTesoro(13);
        btn_plato.setVisible(false);
        btn_plato2.setVisible(false);
    }//GEN-LAST:event_btn_plato2ActionPerformed

    private void btn_platoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_platoActionPerformed
        robarTesoro(13);
        btn_plato.setVisible(false);
        btn_plato2.setVisible(false);
    }//GEN-LAST:event_btn_platoActionPerformed

    private void btn_listaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_listaActionPerformed

        String strRespuesta = "";
        strRespuesta = (new Pregunta().preguntaConfirmacion("Tendrás 60 segundos para robar y escapar" + "\nRoba todo lo que puedas" + "\n¿Estás listo?"));
        if (strRespuesta == "SI") {

            cargarTesoros(); // Original 

            solucion();

            cargarLista();

            r = 60;
            btn_lista.setVisible(false);
            jLabel2.setVisible(true);
            jLabel5.setVisible(true);
            jLabel6.setVisible(true);
            jLabel11.setVisible(false);
            jLabel12.setVisible(false);
            jLabel13.setVisible(false);
            jLabel14.setVisible(false);
            jLabel15.setVisible(false);
            jLabel16.setVisible(false);
            jLabel17.setVisible(false);
            jLabel18.setVisible(false);
            jLabel19.setVisible(false);
            jLabel20.setVisible(false);
            jLabel21.setVisible(false);
            btn_abandonar.setVisible(true);
            btn_terminar.setVisible(true);
            jLabel22.setVisible(false);
            jLabel23.setVisible(false);
            jLabel24.setVisible(false);
            jLabel25.setVisible(false);
            habilitarBotones();

            time = new Timer(550, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (r < 10) {
                        jLabel9.setText(String.valueOf("00:0" + r));

                    } else {
                        jLabel9.setText(String.valueOf("00:" + r));
                    }
                    if (r == 0) {
                        time.stop();

                        Icon ee = new ImageIcon(getClass().getResource("/imagenes/esqueleto.png"));
                        JOptionPane.showMessageDialog(null, "¡Oh no! se ha terminado el tiempo", "PERDISTE", JOptionPane.CANCEL_OPTION, ee);

//                        contador = 4;
                        logro();

//                        try {
//                            guardarRecord();
//                        } catch (IOException ex) {
//                            Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                        PuntajeFinal ver = new PuntajeFinal();
//                        ver.setVisible(true);
//                        dispose();
                    }
                    r--;
                }
            });

            time.start();

        }

    }//GEN-LAST:event_btn_listaActionPerformed

    private void btn_monedasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_monedasActionPerformed
        robarTesoro(14);
        btn_monedas.setVisible(false);
    }//GEN-LAST:event_btn_monedasActionPerformed

    private void btn_abandonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_abandonarActionPerformed


    }//GEN-LAST:event_btn_abandonarActionPerformed

    private void btn_terminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_terminarActionPerformed
        time.stop();
        String strRespuesta = "";
        strRespuesta = (new Pregunta().preguntaConfirmacion("¿Es hora de irse?"));
        if ("SI".equals(strRespuesta)) {

            // Nueva linea
            solucion();

            contador = 3;

            robarTesoro(10); // Duda

            time.stop();
            r = 60;
        } else {
            time.start();
        }

    }//GEN-LAST:event_btn_terminarActionPerformed

    private void btn_agata2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agata2ActionPerformed
        robarTesoro(3);

        btn_agata2.setVisible(false);

    }//GEN-LAST:event_btn_agata2ActionPerformed

    private void btn_barril2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_barril2ActionPerformed
        robarTesoro(2);
        //        jButton11.setVisible(false);
        btn_barril2.setVisible(false);
    }//GEN-LAST:event_btn_barril2ActionPerformed

    private void btn_abandonarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_abandonarMouseClicked
        time.stop();
        String strRespuesta = "";
        strRespuesta = (new Pregunta().preguntaConfirmacion("¿Estás seguro de abandonar el robo?"));
        if ("SI".equals(strRespuesta)) {
            r = 60;
            sonar.stop();

            MenuPrincipal v = new MenuPrincipal();
            v.setVisible(true);
            v.musica();
            this.dispose();

//            MenuPrincipal play = new MenuPrincipal();
//            play.musica();
        } else {
            time.start();
            sonar.play();
        }
    }//GEN-LAST:event_btn_abandonarMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Juego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_BolsaGrande;
    private javax.swing.JButton btn_abandonar;
    private javax.swing.JButton btn_agata;
    private javax.swing.JButton btn_agata2;
    private javax.swing.JButton btn_alfombra;
    private javax.swing.JButton btn_anillo;
    private javax.swing.JButton btn_barril;
    private javax.swing.JButton btn_barril2;
    private javax.swing.JButton btn_bolsaGrande;
    private javax.swing.JButton btn_bolsaMediana;
    private javax.swing.JButton btn_bolsaPequena;
    private javax.swing.JButton btn_cadena;
    private javax.swing.JButton btn_cofreGrande;
    private javax.swing.JButton btn_cofreTesoro;
    private javax.swing.JButton btn_cofreTesoro2;
    private javax.swing.JButton btn_corona;
    private javax.swing.JButton btn_diamante;
    private javax.swing.JButton btn_esmeralda;
    private javax.swing.JButton btn_esmeralda2;
    private javax.swing.JButton btn_lampara;
    private javax.swing.JButton btn_lingote;
    private javax.swing.JButton btn_lista;
    private javax.swing.JButton btn_monedas;
    private javax.swing.JButton btn_perlas;
    private javax.swing.JButton btn_plato;
    private javax.swing.JButton btn_plato2;
    private javax.swing.JButton btn_rubi;
    private javax.swing.JButton btn_tela;
    private javax.swing.JButton btn_terminar;
    private javax.swing.JButton btn_tridente;
    private javax.swing.JButton btn_tridente2;
    private javax.swing.JButton btn_vasija;
    private javax.swing.JButton btn_vaso;
    private javax.swing.JButton btn_zafiro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    public static javax.swing.JTable tabla_tesoros;
    private javax.swing.JTextArea textarea_alforja1;
    private javax.swing.JTextArea txtarea_alforja2;
    // End of variables declaration//GEN-END:variables

    public void botonesTransparentes() {

        jScrollPane3.setBackground(new Color(0, 0, 0, 0));
        jScrollPane3.getViewport().setOpaque(false);
        jScrollPane3.setBorder(null);
        jScrollPane3.setViewportBorder(null);
        tabla_tesoros.setBorder(null);
        tabla_tesoros.setBackground(new Color(0, 0, 0, 0));

        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.setBorder(null);
        jScrollPane1.setViewportBorder(null);

        textarea_alforja1.setBorder(null);
        textarea_alforja1.setBackground(new Color(0, 0, 0, 0));

        jScrollPane5.setOpaque(false);
        jScrollPane5.getViewport().setOpaque(false);
        jScrollPane5.setBorder(null);
        jScrollPane5.setViewportBorder(null);

        txtarea_alforja2.setBorder(null);
        txtarea_alforja2.setBackground(new Color(0, 0, 0, 0));

        btn_cofreGrande.setOpaque(false);
        btn_cofreGrande.setBorder(BorderFactory.createEmptyBorder());
        btn_cofreGrande.setContentAreaFilled(false);
        btn_bolsaGrande.setOpaque(false);
        btn_bolsaGrande.setBorder(BorderFactory.createEmptyBorder());
        btn_bolsaGrande.setContentAreaFilled(false);
        btn_vasija.setOpaque(false);
        btn_vasija.setBorder(BorderFactory.createEmptyBorder());
        btn_vasija.setContentAreaFilled(false);
        btn_alfombra.setOpaque(false);
        btn_alfombra.setBorder(BorderFactory.createEmptyBorder());
        btn_alfombra.setContentAreaFilled(false);
        btn_anillo.setOpaque(false);
        btn_anillo.setBorder(BorderFactory.createEmptyBorder());
        btn_anillo.setContentAreaFilled(false);
        btn_BolsaGrande.setOpaque(false);
        btn_BolsaGrande.setBorder(BorderFactory.createEmptyBorder());
        btn_BolsaGrande.setContentAreaFilled(false);
        btn_bolsaMediana.setOpaque(false);
        btn_bolsaMediana.setBorder(BorderFactory.createEmptyBorder());
        btn_bolsaMediana.setContentAreaFilled(false);
        btn_cofreTesoro.setOpaque(false);
        btn_cofreTesoro.setBorder(BorderFactory.createEmptyBorder());
        btn_cofreTesoro.setContentAreaFilled(false);
        btn_monedas.setOpaque(false);
        btn_monedas.setBorder(BorderFactory.createEmptyBorder());
        btn_monedas.setContentAreaFilled(false);
        btn_cofreTesoro2.setOpaque(false);
        btn_cofreTesoro2.setBorder(BorderFactory.createEmptyBorder());
        btn_cofreTesoro2.setContentAreaFilled(false);
        btn_barril.setOpaque(false);
        btn_barril.setBorder(BorderFactory.createEmptyBorder());
        btn_barril.setContentAreaFilled(false);
        btn_tridente.setOpaque(false);
        btn_tridente.setBorder(BorderFactory.createEmptyBorder());
        btn_tridente.setContentAreaFilled(false);
        btn_esmeralda.setOpaque(false);
        btn_esmeralda.setBorder(BorderFactory.createEmptyBorder());
        btn_esmeralda.setContentAreaFilled(false);
        btn_bolsaPequena.setOpaque(false);
        btn_bolsaPequena.setBorder(BorderFactory.createEmptyBorder());
        btn_bolsaPequena.setContentAreaFilled(false);
        btn_lingote.setOpaque(false);
        btn_lingote.setBorder(BorderFactory.createEmptyBorder());
        btn_lingote.setContentAreaFilled(false);
        btn_tela.setOpaque(false);
        btn_tela.setBorder(BorderFactory.createEmptyBorder());
        btn_tela.setContentAreaFilled(false);
        btn_tridente2.setOpaque(false);
        btn_tridente2.setBorder(BorderFactory.createEmptyBorder());
        btn_tridente2.setContentAreaFilled(false);
        btn_plato.setOpaque(false);
        btn_plato.setBorder(BorderFactory.createEmptyBorder());
        btn_plato.setContentAreaFilled(false);
        btn_plato2.setOpaque(false);
        btn_plato2.setBorder(BorderFactory.createEmptyBorder());
        btn_plato2.setContentAreaFilled(false);
        btn_rubi.setOpaque(false);
        btn_rubi.setBorder(BorderFactory.createEmptyBorder());
        btn_rubi.setContentAreaFilled(false);
        btn_perlas.setOpaque(false);
        btn_perlas.setBorder(BorderFactory.createEmptyBorder());
        btn_perlas.setContentAreaFilled(false);
        btn_diamante.setOpaque(false);
        btn_diamante.setBorder(BorderFactory.createEmptyBorder());
        btn_diamante.setContentAreaFilled(false);
        btn_lampara.setOpaque(false);
        btn_lampara.setBorder(BorderFactory.createEmptyBorder());
        btn_lampara.setContentAreaFilled(false);
        btn_corona.setOpaque(false);
        btn_corona.setBorder(BorderFactory.createEmptyBorder());
        btn_corona.setContentAreaFilled(false);
        btn_vaso.setOpaque(false);
        btn_vaso.setBorder(BorderFactory.createEmptyBorder());
        btn_vaso.setContentAreaFilled(false);
        btn_zafiro.setOpaque(false);
        btn_zafiro.setBorder(BorderFactory.createEmptyBorder());
        btn_zafiro.setContentAreaFilled(false);
        btn_agata2.setOpaque(false);
        btn_agata2.setBorder(BorderFactory.createEmptyBorder());
        btn_agata2.setContentAreaFilled(false);
        btn_agata.setOpaque(false);
        btn_agata.setBorder(BorderFactory.createEmptyBorder());
        btn_agata.setContentAreaFilled(false);
        btn_esmeralda2.setOpaque(false);
        btn_esmeralda2.setBorder(BorderFactory.createEmptyBorder());
        btn_esmeralda2.setContentAreaFilled(false);
        btn_cadena.setOpaque(false);
        btn_cadena.setBorder(BorderFactory.createEmptyBorder());
        btn_cadena.setContentAreaFilled(false);
        btn_barril2.setOpaque(false);
        btn_barril2.setBorder(BorderFactory.createEmptyBorder());
        btn_barril2.setContentAreaFilled(false);
        btn_lista.setOpaque(false);
        btn_lista.setBorder(BorderFactory.createEmptyBorder());
        btn_lista.setContentAreaFilled(false);
        btn_abandonar.setOpaque(false);
        btn_abandonar.setBorder(BorderFactory.createEmptyBorder());
        btn_abandonar.setContentAreaFilled(false);
        btn_terminar.setOpaque(false);
        btn_terminar.setBorder(BorderFactory.createEmptyBorder());
        btn_terminar.setContentAreaFilled(false);

    }

    public void habilitarBotones() {
        btn_cofreGrande.setEnabled(true);
        btn_cofreGrande.setToolTipText("cofre Grande");
        btn_bolsaGrande.setEnabled(true);
        btn_bolsaGrande.setToolTipText("bolsa Grande");
        btn_vasija.setEnabled(true);
        btn_vasija.setToolTipText("vasija");
        btn_alfombra.setEnabled(true);
        btn_alfombra.setToolTipText("alfombra");
        btn_anillo.setEnabled(true);
        btn_anillo.setToolTipText("anillo");
        btn_BolsaGrande.setEnabled(true);
        btn_BolsaGrande.setToolTipText("bolsa Grande");
        btn_bolsaMediana.setEnabled(true);
        btn_bolsaMediana.setToolTipText("bolsa Mediana");
        btn_cofreTesoro.setEnabled(true);
        btn_cofreTesoro.setToolTipText("cofre Tesoro");
        btn_monedas.setEnabled(true);
        btn_monedas.setToolTipText("monedas");
        btn_cofreTesoro2.setEnabled(true);
        btn_cofreTesoro2.setToolTipText("cofre Tesoro");
        btn_barril.setEnabled(true);
        btn_barril.setToolTipText("barril");
        btn_tridente.setEnabled(true);
        btn_tridente.setToolTipText("tridente");
        btn_esmeralda.setEnabled(true);
        btn_esmeralda.setToolTipText("esmeralda");
        btn_bolsaPequena.setEnabled(true);
        btn_bolsaPequena.setToolTipText("bolsa Pequeña");
        btn_lingote.setEnabled(true);
        btn_lingote.setToolTipText("lingote");
        btn_tela.setEnabled(true);
        btn_tela.setToolTipText("tela");
        btn_tridente2.setEnabled(true);
        btn_tridente2.setToolTipText("tridente");
        btn_plato.setEnabled(true);
        btn_plato.setToolTipText("plato");
        btn_plato2.setEnabled(true);
        btn_plato2.setToolTipText("plato");
        btn_rubi.setEnabled(true);
        btn_rubi.setToolTipText("rubi");
        btn_perlas.setEnabled(true);
        btn_perlas.setToolTipText("abrir");
        btn_diamante.setEnabled(true);
        btn_diamante.setToolTipText("diamante");
        btn_lampara.setEnabled(true);
        btn_lampara.setToolTipText("lampara");
        btn_corona.setEnabled(true);
        btn_corona.setToolTipText("corona");
        btn_vaso.setEnabled(true);
        btn_vaso.setToolTipText("vaso");
        btn_zafiro.setEnabled(true);
        btn_zafiro.setToolTipText("zafiro");
        btn_agata2.setEnabled(true);
        btn_agata2.setToolTipText("agata");
        btn_agata.setEnabled(true);
        btn_agata.setToolTipText("agata");
        btn_esmeralda2.setEnabled(true);
        btn_esmeralda2.setToolTipText("esmeralda");
        btn_cadena.setEnabled(true);
        btn_cadena.setToolTipText("cadena");
        btn_barril2.setEnabled(true);
        btn_barril2.setToolTipText("barril");
    }

    public static void setPrueba3(int prueba3) {
        Juego.prueba3 = prueba3;
    }

}
