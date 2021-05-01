/*//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//SISTEMA : ALIBABÁ Y LOS CIENTO UN MIL LADRONES
//MODULO : Pregunta
//PROCESO : Interfaz
//DESCRIPCION : En el código se encuentra validad para realizar pregunta de advertencia
//ANALISTAS : Ing. Mauro Rosas
//PROGRAMADOR : Saúl Alpala, Doménica Erazo, Samantha Jara
//FECHA CREACION, MODIFICACION:23 de agosto 2020, 20 de septiembre 2020
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 

*/
package Clases;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Pregunta {
    
    Icon n = new ImageIcon(getClass().getResource("/imagenes/1intentos.png"));
    
    public String preguntaConfirmacion(String strMensaje) {                           
        
        int seleccion = JOptionPane.showOptionDialog(
                null,
                strMensaje,
                "Selecione una opción",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                n, //icono por defecto 
                new Object[]{"Si", "No"},
                "Si");
        if (seleccion != -1) {
            if ((seleccion + 1) == 1) {
                return "SI";
            } else {
                return "NO";
            }
        }
        return null;
    }
    
}
