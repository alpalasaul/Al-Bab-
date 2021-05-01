/*//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//SISTEMA : ALIBABÁ Y LOS CIENTO UN MIL LADRONES
//MODULO : Elemento
//PROCESO :  Juego
//DESCRIPCION : En el codigo esta validado los atributos de los objetos a robar
//ANALISTAS : Ing. Mauro Rosas
//PROGRAMADOR : Saúl Alpala, Doménica Erazo, Samantha Jara
//FECHA CREACION, MODIFICACION:23 de agosto 2020, 20 de septiembre 2020
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 

*/
package Clases;

public class Elemento {

    String nombre;
    double valor;
    double peso;

    Elemento(String n, double v, double p) {
        nombre = n;
        valor = v;
        peso = p;
    }

    public String toString() {
        return String.format("%-1s %,12.2f %,12.2f", nombre, valor, peso);
    }

    public String getNombre() {
        return nombre;
    }

    public double getValor() {
        return valor;
    }

    public double getPeso() {
        return peso;
    }
}
