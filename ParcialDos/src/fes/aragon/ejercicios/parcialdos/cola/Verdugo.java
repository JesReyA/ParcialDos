package fes.aragon.ejercicios.parcialdos.cola;

import fes.aragon.dinamica.cola.Cola;

public class Verdugo {
    public static void main(String[] args) {
        int k=4;
        int n=10;
        int amigo=7;
        int posicionInicio=1;
        int posicionesRecorridas=0;
        boolean amigoSalvado= false;
        Cola <Integer> prisioneros = new Cola<>();

        for(int i=1; i<=n; i++){
            prisioneros.insertar(i);
        }

        int cantidadMaxima = n;
        prisioneros.extraer();
        cantidadMaxima-=1;

        while(cantidadMaxima!=1){
            posicionesRecorridas=0;
            while(posicionesRecorridas<=k){
                prisioneros.insertar(prisioneros.extraer());
                posicionesRecorridas+=1;
            }
            int eliminado = prisioneros.extraer();
            if(eliminado == amigo){
                break;
            }
            cantidadMaxima-=1;
        }
        if(cantidadMaxima == 1 && prisioneros.elementoSuperior() == amigo){
            System.out.println("La posición de inicio debe ser: " + posicionInicio);
        }else{
             while(!amigoSalvado){
                 posicionInicio+=1;
                 cantidadMaxima = n;

                 prisioneros.borrar();
                 for(int i=1; i<=n; i++){
                     prisioneros.insertar(i);
                 }

                 posicionesRecorridas=0;
                 while(posicionesRecorridas<posicionInicio-1){
                     prisioneros.insertar(prisioneros.extraer());
                     posicionesRecorridas+=1;
                 }
                 prisioneros.extraer();
                 cantidadMaxima-=1;

                 while(cantidadMaxima!=1){
                     posicionesRecorridas=1;
                     while(posicionesRecorridas<k){
                         prisioneros.insertar(prisioneros.extraer());
                         posicionesRecorridas+=1;
                     }
                     int eliminado = prisioneros.extraer();
                     if(eliminado == amigo){
                         break;
                     }
                     cantidadMaxima-=1;
                 }

                if(cantidadMaxima==1 && prisioneros.elementoSuperior()==amigo){
                    System.out.println("La posición de inicio debe ser: " + posicionInicio);
                    amigoSalvado= true;
                }
            }
        }
    }
}