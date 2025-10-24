package fes.aragon.ejercicios.parcialdos.cola;

import unam.fes.aragon.dinamicas.cola.Cola;

import java.io.BufferedReader;
import java.io.FileReader;

public class GananciaCapital {
    public static void main(String[] args) {
        Cola<Acciones> gananciaPerdida = new Cola<>();

        ManejoArchivos <String> archivoReporte = new ManejoArchivos<>();
        FileReader archivo;
        BufferedReader lector;
        String cadena;
        String [] partes = null;

        try{
            archivo = new FileReader("ParcialDos/Acciones.csv");
            lector= new BufferedReader(archivo);
            while((cadena = lector.readLine()) != null){
                //Division de la cadena
                partes = cadena.split(";");
                Acciones nuevaAccion = new Acciones(partes[0].charAt(0), Integer.parseInt(partes[1]), Integer.parseInt(partes[2]), Integer.parseInt(partes[3]));
                gananciaPerdida.insertar(nuevaAccion);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        Acciones accionActual = gananciaPerdida.elementoSuperior();
        System.out.println( accionActual.getCantidadAcciones());
    }
}
