/*
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//SISTEMA : ALIBABÁ Y LOS CIENTO UN MIL LADRONES
//MODULO : Jugador
//PROCESO : Perfil
//DESCRIPCION : En el código se presentara los atributos del jugador
//ANALISTAS : Ing. Mauro Rosas
//PROGRAMADOR : Saúl Alpala, Doménica Erazo, Samantha Jara
//FECHA CREACION, MODIFICACION:23 de agosto 2020, 20 de septiembre 2020
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 

 */
package Clases;

import java.io.Serializable;

public class Jugador implements Comparable<Jugador>, Serializable {

    private String nombre;
    private String contrasena;
    private String edad;
    private int jugadas;
    private int ganadas;
    private int perdidas;
    private double record;

    
    private String avatar;

  

    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + ", contrasena=" + contrasena + ", edad=" + edad + ", jugadas=" + jugadas + ", ganadas=" + ganadas + ", perdidas=" + perdidas + ", record=" + record + ", avatar=" + avatar + '}';
    }

 

    public Jugador(String nombre, String edad, String contrasena, int jugadas, int ganadas, int perdidas, double record, String avatar) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.edad = edad;
        this.jugadas = jugadas;
        this.ganadas = ganadas;
        this.perdidas = perdidas;
        this.record = record;
        this.avatar = avatar;
    }
         

    public Jugador() {

    }



    public void mostrarDatos(Jugador jugador[]) {
        for (Jugador lista : jugador) {
            System.out.println(lista);
        }
    }

    public void buscarRecord(double[] record) {
        int posicion = 0;
        double mayor = record[0];
        for (int i = 0; i < record.length; i++) {
            if (record[i] > mayor) {
                mayor = record[i];
                posicion = i;
            }
        }
        System.out.println("\nEl mejor record es\n" + record[posicion]);
    }

    @Override
    public int compareTo(Jugador o) {
        return nombre.compareTo(o.getNombre());

    }

    

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getEdad() {
        return edad;
    }

    public int getJugadas() {
        return jugadas;
    }

    public int getGanadas() {
        return ganadas;
    }

    public int getPerdidas() {
        return perdidas;
    }
    public double getRecord() {
        return record;
    }
      public String getAvatar() {
        return avatar;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public void setJugadas(int jugadas) {
        this.jugadas = jugadas;
    }

    public void setGanadas(int ganadas) {
        this.ganadas = ganadas;
    }

    public void setPerdidas(int perdidas) {
        this.perdidas = perdidas;
    }

    public void setRecord(double record) {
        this.record = record;
    }
     

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
