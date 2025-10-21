package fes.aragon.ejercicios.parcialdos.pilas;

import unam.fes.aragon.dinamicas.pilas.Pila;

public class ConversionInterfijaPostfijaParentesis {

    private static int obtenerPrecedencia (String op){
        return switch (op) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "^" -> 3;
            default -> 0;
        };
    }
    public static void main (String[]args){
        String interfija = "( 8 ^ 5 ) * 3 / ( 4 * 5 ) - 3";
        StringBuilder postfija = new StringBuilder();
        Pila<String> operadores = new Pila<>();
        String[] simbolos = interfija.split(" ");

        for (int i=0; i< simbolos.length; i++) {
            String simbolo = simbolos[i];
            if (!simbolo.equals("+") && !simbolo.equals("-")
                    && !simbolo.equals("*") && !simbolo.equals("/") && !simbolo.equals("^") && !simbolo.equals("(") && !simbolo.equals(")")) {

                postfija.append(simbolo).append(" ");

            }else if(simbolo.equals("(")){
                operadores.insertar(simbolo);

            }else if(simbolo.equals(")")){
                while (!operadores.estaVacia() &&
                        !operadores.elementoSuperior().equals(("("))) {

                    postfija.append(operadores.extraer()).append(" ");
                }
                if(!operadores.estaVacia() &&
                        operadores.elementoSuperior().equals(("("))){
                    operadores.extraer();
                }

            }else {
                while (!operadores.estaVacia() &&
                        obtenerPrecedencia(operadores.elementoSuperior()) >= obtenerPrecedencia(simbolo)) {

                    postfija.append(operadores.extraer()).append(" ");
                }

                operadores.insertar(simbolo);
            }
        }

        while (!operadores.estaVacia()) {
            postfija.append(operadores.extraer()).append(" ");
        }

        System.out.println(postfija.toString().trim());
    }
}
