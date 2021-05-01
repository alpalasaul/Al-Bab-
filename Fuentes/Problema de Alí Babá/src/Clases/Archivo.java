/*
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//SISTEMA : ALIBABÁ Y LOS CIENTO UN MIL LADRONES
//MODULO : Archivo
//PROCESO : Usuario
//DESCRIPCION : En el código esta validado para poder guardar, modificar y listar el archivo
//ANALISTAS : Ing. Mauro Rosas
//PROGRAMADOR : Saúl Alpala, Doménica Erazo, Samantha Jara
//FECHA CREACION, MODIFICACION:23 de agosto 2020, 20 de septiembre 2020
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 

 */
package Clases;

import Ventanas.IniciarSesion;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Archivo {

    public void modificar(Object original, Object modificar, ArrayList lista, String direccion) throws FileNotFoundException, IOException {
        int i = 0;
        lista.remove(original);

        lista.add(modificar);

        IniciarSesion.posicion = IniciarSesion.listaJugadores.size() - 1; //-1       

        FileOutputStream file = new FileOutputStream(direccion);
        ObjectOutputStream escribir = new ObjectOutputStream(file);
        MyObjectOutputStream sobrescribir = new MyObjectOutputStream(file);
        for (Object objecto : lista) {
            if (i == 0) {//primera vez
                escribir.writeObject(objecto);
            } else {//segunda vez
                sobrescribir.writeObject(objecto);
            }
            i++;
        }
        escribir.close();
        sobrescribir.close();

    }

    public void guardar(Object object, String direccion, ArrayList lista) throws FileNotFoundException, IOException {
        lista.add(object);
        if (!archivoExiste(direccion)) {
            FileOutputStream file = new FileOutputStream(direccion);
            ObjectOutputStream objeto = new ObjectOutputStream(file);
            objeto.writeObject(object);
            objeto.close();

        } else {
            FileOutputStream file = new FileOutputStream(direccion, true);
            MyObjectOutputStream objeto = new MyObjectOutputStream(file);
            objeto.writeObject(object);
            objeto.close();
        }
    }

    // Comprobar la existencia del archivo
    public boolean archivoExiste(String direccion) {
        File archivo = new File(direccion);//nombre del archivo
        return archivo.exists();
    }

    // Para listar si debemos instaciar a la clase que vamos a usar, que debe impletar Serializable
    public void listar(String direccion, ArrayList lista) throws FileNotFoundException, IOException, ClassNotFoundException {

        lista.clear();

        try {
            FileInputStream file = new FileInputStream(direccion);
            ObjectInputStream objeto = new ObjectInputStream(file);
            Object a = objeto.readObject();

            while (a != null) {
                if (a instanceof Jugador) {//Instanaciar para la clase que nececitemos
                    lista.add((Jugador) a);//aqui igual

                }
                a = objeto.readObject();
            }
        } catch (IOException e) {
        }

    }

}
