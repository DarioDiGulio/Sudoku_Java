package Tablero;

import java.util.Scanner;

public class Ronda {
    private int fila;
    private int columna;
    private int valor;

    public Ronda() {
        pedirFila();
        pedirColumna();
        pedirValor();
    }

    private void pedirValor() {
        try {
            this.valor = pedirValor("el valor a ubicar en la posición indicada");
        } catch (RuntimeException r) {
            System.out.println("No ingresaste un valor aceptado. Los valores posibles son enteros del 1 al 9.");
            pedirValor();
        }
    }

    private void pedirColumna() {
        try {
            this.columna = pedirValor("la columna");
        } catch (RuntimeException r) {
            System.out.println("No ingresaste un valor aceptado. Los valores posibles son enteros del 1 al 9.");
            pedirColumna();
        }
    }

    private void pedirFila() {
        try {
            this.fila = pedirValor("la fila");
        } catch (RuntimeException r) {
            System.out.println("No ingresaste un valor aceptado. Los valores posibles son enteros del 1 al 9.");
            pedirFila();
        }
    }

    private int pedirValor(String concepto) {
        int valorIngresado = 0;

        try {
            valorIngresado = pedirEntero("Ingrese " + concepto + ": ");
            if (valorIngresado < 1 || valorIngresado > 9) {
                throw new RuntimeException("No ingresaste un valor aceptado. Los valores posibles son enteros del 1 al 9.");
            }
        } catch (RuntimeException r) {
            System.out.println("No ingresaste un valor aceptado. Los valores posibles son enteros del 1 al 9.");
        }

        return valorIngresado;
    }

    public static Scanner input = new Scanner(System.in);

    private int pedirEntero(String mensajeParaPedir) {
        return input(mensajeParaPedir);
    }

    private int input(String mensajeParaPedir) {
        System.out.print(mensajeParaPedir);
        return input.nextInt();
    }

    public int getFila() {
        return fila - 1;
    }

    public int getColumna() {
        return columna - 1;
    }

    public int getValor() {
        return valor;
    }
}
