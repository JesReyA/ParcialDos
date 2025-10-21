package fes.aragon.ejercicios.parcialdos.cola;

import unam.fes.aragon.dinamicas.pilas.Pila;

import java.io.BufferedReader;
import java.io.FileReader;

public class GananciaCapital {
    public static void main(String[] args) {
        Acciones compraVenta = new Acciones();
        Pila <Acciones> gananciaPerdida = new Pila<>();

        ManejoArchivos <String> archivoReporte = new ManejoArchivos<>();
        FileReader archivo;
        BufferedReader lector;
        String cadena;
        String [] partes = null;

        try{
            archivo = new FileReader("acciones.csv");
            lector= new BufferedReader(archivo);
            while((cadena = lector.readLine()) != null){
                //Division de la cadena
                partes = cadena.split(";");
                //compraVenta('C', 300, 20, 1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
