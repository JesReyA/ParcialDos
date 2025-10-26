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
        int totalVenta=0, numeroVenta=0;

        if(archivoReporte.archivoExiste("Reporte.txt")){
            archivoReporte.borrarArchivo("Reporte.txt");
        }

        archivoReporte.escribirEnArchivo("Reporte.txt", "Reporte\n");
        archivoReporte.escribirEnArchivo("Reporte.txt", "Reporte de Ganancia o Pérdida en Compra-Venta de Acciones" + "\n\n");

        try{
            archivo = new FileReader("Acciones.csv");
            lector= new BufferedReader(archivo);
            while((cadena = lector.readLine()) != null){
                //Division de la cadena
                partes = cadena.split(";");
                Acciones nuevaAccion = new Acciones(partes[0].charAt(0), Integer.parseInt(partes[1]), Integer.parseInt(partes[2]), Integer.parseInt(partes[3]));
                if(nuevaAccion.getTipo() == 'C'){
                    gananciaPerdida.insertar(nuevaAccion);
                }else{
                    numeroVenta+=1;
                    int ventaAcciones, diferenciaAcciones, cantidadAccionesVendidas = nuevaAccion.getCantidadAcciones();
                    while(cantidadAccionesVendidas != 0){
                        Acciones accionVieja = gananciaPerdida.elementoSuperior();
                        diferenciaAcciones = accionVieja.getCantidadAcciones() - cantidadAccionesVendidas;
                        if(diferenciaAcciones == 0){
                            ventaAcciones = (nuevaAccion.getPrecioAcciones()* nuevaAccion.getCantidadAcciones() -nuevaAccion.getCantidadAcciones()*accionVieja.getPrecioAcciones());
                            totalVenta += ventaAcciones;

                            if(ventaAcciones<0){
                                archivoReporte.escribirEnArchivo("Reporte.txt", "# Venta: " + numeroVenta + "\n");
                                archivoReporte.escribirEnArchivo("Reporte.txt","Pérdida: " + Integer.toString(ventaAcciones * (-1)) + "\n");
                            }else if(ventaAcciones>0){
                                archivoReporte.escribirEnArchivo("Reporte.txt", "# Venta: " + numeroVenta + "\n");
                                archivoReporte.escribirEnArchivo("Reporte.txt","Ganancia: " + Integer.toString(ventaAcciones) + "\n");
                            }else {
                                archivoReporte.escribirEnArchivo("Reporte.txt", "# Venta: " + numeroVenta + "\n");
                                archivoReporte.escribirEnArchivo("Reporte.txt", "No hubo ganancia o pérdida" + "\n");
                            }
                            cantidadAccionesVendidas = 0;
                            gananciaPerdida.extraer();

                        }else if (diferenciaAcciones > 0){
                            gananciaPerdida.elementoSuperior().setCantidadAcciones(diferenciaAcciones);
                            ventaAcciones = (nuevaAccion.getPrecioAcciones()* nuevaAccion.getCantidadAcciones() -nuevaAccion.getCantidadAcciones()*accionVieja.getPrecioAcciones());
                            totalVenta += ventaAcciones;

                            if(ventaAcciones<0){
                                archivoReporte.escribirEnArchivo("Reporte.txt", "# Venta: " + numeroVenta + "\n");
                                archivoReporte.escribirEnArchivo("Reporte.txt","Pérdida: " + Integer.toString(ventaAcciones * (-1)) + "\n");
                            }else if(ventaAcciones>0){
                                archivoReporte.escribirEnArchivo("Reporte.txt", "# Venta: " + numeroVenta + "\n");
                                archivoReporte.escribirEnArchivo("Reporte.txt","Ganancia: " + Integer.toString(ventaAcciones) + "\n");
                            }else {
                                archivoReporte.escribirEnArchivo("Reporte.txt", "# Venta: " + numeroVenta + "\n");
                                archivoReporte.escribirEnArchivo("Reporte.txt", "No hubo ganancia o pérdida" + "\n");
                            }

                            cantidadAccionesVendidas = 0;
                            nuevaAccion.setCantidadAcciones(diferenciaAcciones);
                        }else{
                            ventaAcciones = (nuevaAccion.getPrecioAcciones()* nuevaAccion.getCantidadAcciones() -nuevaAccion.getCantidadAcciones()*accionVieja.getPrecioAcciones());
                            totalVenta += ventaAcciones;

                            if(ventaAcciones<0){
                                archivoReporte.escribirEnArchivo("Reporte.txt", "# Venta: " + numeroVenta + "\n");
                                archivoReporte.escribirEnArchivo("Reporte.txt","Pérdida: " + Integer.toString(ventaAcciones * (-1)) + "\n");
                            }else if(ventaAcciones>0){
                                archivoReporte.escribirEnArchivo("Reporte.txt", "# Venta: " + numeroVenta + "\n");
                                archivoReporte.escribirEnArchivo("Reporte.txt","Ganancia: " + Integer.toString(ventaAcciones) + "\n");
                            }else {
                                archivoReporte.escribirEnArchivo("Reporte.txt", "# Venta: " + numeroVenta + "\n");
                                archivoReporte.escribirEnArchivo("Reporte.txt", "No hubo ganancia o pérdida" + "\n");
                            }

                            cantidadAccionesVendidas = diferenciaAcciones * -1;
                            nuevaAccion.setCantidadAcciones(diferenciaAcciones*-1);
                            gananciaPerdida.extraer();
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        archivoReporte.escribirEnArchivo("Reporte.txt", "\n");
        if(totalVenta >=0){
            archivoReporte.escribirEnArchivo("Reporte.txt", "Ganancia Total: " + totalVenta);
        }else {
            archivoReporte.escribirEnArchivo("Reporte.txt", "Pérdida Total: " + totalVenta);
        }
    }
}
