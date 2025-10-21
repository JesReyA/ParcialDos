package fes.aragon.ejercicios.parcialdos.pilas;

import unam.fes.aragon.dinamicas.pilas.Pila;

public class MensajeEncriptado {
    public static void main(String[] args) {
        String mensaje = "Olimpia(am(nfor)I ed (da))tica";
        StringBuilder tmp = new StringBuilder();
        String mensajeTmp;
        Pila<Character> letrasInvertidas = new Pila<>();
        StringBuilder mensajeDesencriptado = new StringBuilder();

        for( char c : mensaje.toCharArray()) {
            if(c != ')'){
                letrasInvertidas.insertar(c);
            }else {
                while(!letrasInvertidas.estaVacia() && letrasInvertidas.elementoSuperior() != '('){
                    tmp.append(letrasInvertidas.extraer());
                }
                if(letrasInvertidas.elementoSuperior().equals('(')){
                    letrasInvertidas.extraer();
                }

                mensajeTmp = tmp.toString();
                for (char m : mensajeTmp.toCharArray()){
                    letrasInvertidas.insertar(m);
                }
                int tamaño = tmp.length();
                tmp.delete(0, tamaño);
            }
        }


        while(!letrasInvertidas.estaVacia()){
            mensajeDesencriptado.append(letrasInvertidas.extraer());
        }
        System.out.println(mensajeDesencriptado.reverse());
    }
}
