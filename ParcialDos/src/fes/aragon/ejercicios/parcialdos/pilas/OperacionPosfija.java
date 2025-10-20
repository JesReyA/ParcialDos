package fes.aragon.ejercicios.parcialdos.pilas;

import fes.aragon.dinamica.pilas.Pila;

public class OperacionPosfija {
    public static void main(String[] args) {
        String cadena = "3 4 * 5 6 * +";
        Pila < Double> pila = new Pila<>();
        String [] simbolos = cadena.split(" ");
        int contador = 0;

        while (contador < simbolos.length){
            String simbolo = simbolos[contador];
            if(!simbolo.equals("+") && !simbolo.equals("-") && !simbolo.equals("*") && !simbolo.equals("/")){
                pila.insertar(Double.parseDouble(simbolo));
            }else {
                double operando1 = pila.extraer();
                double operando2 = pila.extraer();
                double valor = 0;

                switch (simbolo){
                    case "+":
                        valor = operando2 + operando1;
                        break;

                    case "-":
                        valor = operando2 - operando1;
                        break;

                    case "*":
                        valor = operando2 * operando1;
                        break;

                    case "/":
                        valor= operando2 / operando1;
                        break;
                }
                pila.insertar(valor);
            }
            contador++;
        }
        System.out.println("Resultado: " + pila.extraer());
    }
}
