package fes.aragon.ejercicios.parcialdos.cola;

import unam.fes.aragon.dinamicas.listasimple.ListaSimple;

import java.io.BufferedReader;
import java.io.FileReader;

//Este solo es para guiarse en el llenado del archivo, no es parte del proyecto como tal o de la unidad

public class EjercicioDosPrueba {
    public static void main(String[] args) {
        ListaSimple<Integer> listaDos= new ListaSimple<>();
        ManejoArchivos<String> archivoReporte = new ManejoArchivos<>();
        FileReader archivo;
        BufferedReader lector;
        String cadena;
        String [] partes = null;
        int cantidadRepeticiones, numeroActual;

        try{
            archivo = new FileReader("lista.csv");
            lector= new BufferedReader(archivo);
            while((cadena = lector.readLine()) != null){
                //Division de la cadena
                partes = cadena.split(",");
                for(int i=0; i< partes.length;i++){
                    if(Integer.parseInt(partes[i])>=30 && Integer.parseInt(partes[i])<= 150){
                        listaDos.agregarEnCola(Integer.parseInt(partes[i]));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        //Conteo de veces que aparece cada numero
        if(archivoReporte.archivoExiste("reporte.txt")){
            archivoReporte.borrarArchivo("reporte.txt");
        }

        archivoReporte.escribirEnArchivo("reporte.txt", "Reporte\n");
        archivoReporte.escribirEnArchivo("reporte.txt", "Cantidad de Veces que se repiten los números del 30 al 150 en el archivo \"lista.csv\"");

        for(int i= 0; i< listaDos.getLongitud(); i++){
            if(listaDos.obtenerNodo(i) != null){
                numeroActual = listaDos.obtenerNodo(i);
                cantidadRepeticiones = 1;

                for (int j = i+1; j< listaDos.getLongitud(); j++){
                    if(listaDos.obtenerNodo(j) != null && listaDos.obtenerNodo(j) == numeroActual){
                        cantidadRepeticiones++;
                        listaDos.asignar(null, j);
                    }
                }
                archivoReporte.escribirEnArchivo("reporte.txt","\n");
                archivoReporte.escribirEnArchivo("reporte.txt", Integer.toString(numeroActual) + " : " + Integer.toString(cantidadRepeticiones) );
            }
        }
    }
}
