/*//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//SISTEMA : ALIBABÁ Y LOS CIENTO UN MIL LADRONES
//MODULO : ProblemaAliBaba
//PROCESO :  Juego
//DESCRIPCION : En el codigo esta validado los objetos a robar y el método con el cual se resolvera el juego
//ANALISTAS : Ing. Mauro Rosas
//PROGRAMADOR : Saúl Alpala, Doménica Erazo, Samantha Jara
//FECHA CREACION, MODIFICACION:23 de agosto 2020, 20 de septiembre 2020
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 

 */
package Clases;

import java.util.*;

public class ProblemaAliBaba {

    public static long getTi() {
        return ti;
    }

    static long ti;
    static long tf;
    static long tiempo;
    public static ArrayList<Elemento> almacen = new ArrayList<Elemento>(); // Original

    static ArrayList<Elemento> mejorMochila1 = new ArrayList<Elemento>();
    static ArrayList<Elemento> mejorMochila2 = new ArrayList<Elemento>();
    static ArrayList<Elemento> eleccionMochila = new ArrayList<Elemento>();

    public static double pesoMochila1 = 0;
    public static double beneficiorMochila1 = 0;

    public static double pesoMochila2 = 0;
    public static double beneficioMochila2 = 0;

    public static int contador = 0;

    public void ProblemaAliBaba() {
    }

    public void cargarDatos() { // anteriormente como public void 
        //Nombre / Valor / Peso

        almacen.clear();

        almacen.add(new Elemento("Bolsa de monedas grande", 200, 10)); //0
        almacen.add(new Elemento("Bolsa de monedas mediana", 100, 6));//1
        almacen.add(new Elemento("Bolsa de monedas pequeña", 50, 3));//2    
        almacen.add(new Elemento("Lingote de oro         ", 500, 3));//3        
        almacen.add(new Elemento("Esmeralda              ", 1000, 2.7));//4        
        almacen.add(new Elemento("Anillo de oro          ", 150, 0.3));//5
        almacen.add(new Elemento("Diamente               ", 5000, 0.2));//6        
        almacen.add(new Elemento("Tela ceda              ", 225, 0.5));//7
        almacen.add(new Elemento("Vasija de oro          ", 150, 1.75));//8
        almacen.add(new Elemento("Corona de oro          ", 1900, 2.2));//9
        almacen.add(new Elemento("Cadena de oro          ", 1700, 1.6));//10
        almacen.add(new Elemento("Lámpara de oro         ", 600, 2.1));//11
        almacen.add(new Elemento("Cofre del tesoro       ", 5020, 7));//12
        almacen.add(new Elemento("Perlas                 ", 680, 3));//13
        almacen.add(new Elemento("Rubí                   ", 400, 0.5));//14
        almacen.add(new Elemento("Vasos                  ", 100, 0.5));//15
        almacen.add(new Elemento("Zafiros                ", 300, 0.5));//16
        almacen.add(new Elemento("Agatas                 ", 2300, 2.3));//17
        almacen.add(new Elemento("Alfombras              ", 690, 3.5));//18
        almacen.add(new Elemento("Cofre grande           ", 2000, 7));//19
        almacen.add(new Elemento("Barril de monedas      ", 3500, 6.5));//20
        almacen.add(new Elemento("Tridente de oro        ", 890, 1));//21
        almacen.add(new Elemento("Plato de oro           ", 500, 0.25));//22
        almacen.add(new Elemento("Monedas de oro         ", 500, 0.10));//23
    }

    public static double[] mostrarMochila(ArrayList<Elemento> solucion, double beneficioMochi, double pesoMochi) {

        double[] calculos = new double[2];

        System.out.println();
        for (Elemento e : solucion) {
            pesoMochi += e.peso;
            beneficioMochi += e.valor;
        }
        calculos[0] = pesoMochi;
        calculos[1] = beneficioMochi;

        return calculos;
    }

    public static void resolverProblema(double pesoMochi, ArrayList<Elemento> solucion, int pesoMax) { //, ArrayList<Elemento> almacen
//        ti = System.currentTimeMillis();
        Comparator cmp = new Comparator<Elemento>() {
            @Override
            public int compare(Elemento x, Elemento y) {
                return (int) (x.valor - y.valor);
            }
        };
        Collections.sort(almacen, cmp);  // ordena usando el comparador anterior
        Collections.reverse(almacen);   // reversa el orden de los elementos

        int posicion = 0;

//        System.out.println(almacen.size());
        while (pesoMochi < pesoMax && posicion < almacen.size()) {
            Elemento tmp = almacen.get(posicion);
            if (pesoMochi + tmp.peso <= pesoMax) {

                solucion.add(tmp);
                if (contador == 0) {
                    mejorMochila1.add(tmp); // Guardando aux
                } else {
                    mejorMochila2.add(tmp); // Guardando aux
                }

                pesoMochi += tmp.peso;
            }
            posicion++;
        }
//        tf = System.currentTimeMillis();
//        tiempo = tf - ti;
//        System.out.println("tiempo " + tiempo);
    }

    public static void setContador(int contador) {
        ProblemaAliBaba.contador = contador;
    }

    public static void calcular() {
        mejorMochila1.stream().map((elemento) -> {
            pesoMochila1 += elemento.peso;
            return elemento;
        }).forEachOrdered((elemento) -> {
            beneficiorMochila1 += elemento.valor;
        });

        if (contador != 0) {
            mejorMochila2.stream().map((elemento) -> {
                pesoMochila2 += elemento.peso;
                return elemento;
            }).forEachOrdered((elemento) -> {
                beneficioMochila2 += elemento.valor;
            });
        }

    }

    public void llenarAlmacen(ArrayList<Elemento> alamacen) {
        int t = 0;
        alamacen.clear();
        for (Elemento elemento : almacen) {
            alamacen.add(almacen.get(t));
            t++;
        }
    }

    public static double compararBeneficio(double beneficioSolucion, double beneficioRobado) {

        double logro = 0;
        logro = (beneficioRobado * 100) / (beneficioSolucion);

        return logro;
    }

    public boolean limitePeso() {
        boolean bandera = false;
        if (pesoMochila2 <= pesoMochila1) {
            bandera = true;
        }

        return bandera;
    }

    public static double getPesoMochila1() {
        return pesoMochila1;
    }

    public static double getBeneficiorMochila1() {
        return beneficiorMochila1;
    }

    public static double getBeneficioMochila2() {
        return beneficioMochila2;
    }

    public static double getPesoMochila2() {
        return pesoMochila2;
    }

    public static void setTi(long ti) {
        ProblemaAliBaba.ti = ti;
    }

    public static long getTf() {
        return tf;
    }

    public static void setTf(long tf) {
        ProblemaAliBaba.tf = tf;
    }

    public static long getTiempo() {
        return tiempo;
    }

    public static void setTiempo(long tiempo) {
        ProblemaAliBaba.tiempo = tiempo;
    }
}
