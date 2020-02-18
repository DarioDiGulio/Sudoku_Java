package Tablero;

import java.util.Scanner;

public class Tablero implements Cuadrantes {
    final static int FILAS = 9;
    final static int COLUMNAS = 9;
    final static int POSICIONES_TOTALES = 81;
    private int[][] tablero;
    private Nivel nivel;
    private int errores;
    private int valoresCargados;

    public Tablero() {
        this.tablero = new int[FILAS][COLUMNAS];
        this.nivel = preguntarNivel();
        this.valoresCargados = 0;
        try {
            cargarTablero();
        } catch (StackOverflowError stackOverflowError) {
            System.out.println("No se pudieron cargas más números.");
        }
        this.errores = 0;
        iniciar();
    }

    private Nivel preguntarNivel() {
        System.out.print("Ingresa el nivel {Principiante/Intermedio/Avanzado}: ");
        String nivelIngresado = input.nextLine();

        Nivel nivel;
        if (nivelIngresado.equalsIgnoreCase("Principiante")) {
            nivel = Nivel.Principiante;
        } else if (nivelIngresado.equalsIgnoreCase("Intermedio")) {
            nivel = Nivel.Intermedio;
        } else if (nivelIngresado.equalsIgnoreCase("Avanzado")) {
            nivel = Nivel.Avanzado;
        } else {
            System.out.print("No ingresaste un valor correcto.\n");
            nivel = this.preguntarNivel();
        }

        return nivel;
    }

    public static Scanner input = new Scanner(System.in);

    private void cargarTablero() {
        for (int i = 0; i < POSICIONES_TOTALES; i++) {
            insertarValorEnPosicionAleatorea();
        }
        for (int i = 0; i < getPosicionesAVaciar(); i++) {
            sacarValorDePosicionAleatorea();
        }
    }

    private int getPosicionesAVaciar() {
        return POSICIONES_TOTALES - this.nivel.getNumerosPrecargados();
    }

    private void sacarValorDePosicionAleatorea() {
        int filaAleatorea = numeroAleatoreoEntre(0, 8);
        int columnaAleatorea = numeroAleatoreoEntre(0, 8);
        tablero[filaAleatorea][columnaAleatorea] = 0;
    }

    private void insertarValorEnPosicionAleatorea() {
        int filaAleatorea = numeroAleatoreoEntre(0, 8);
        int columnaAleatorea = numeroAleatoreoEntre(0, 8);
        int valor = numeroAleatoreoEntre(1, 9);
        if (posicionVacia(filaAleatorea, columnaAleatorea) && valorValidoEnPosicion(filaAleatorea, columnaAleatorea, valor)) {
            tablero[filaAleatorea][columnaAleatorea] = valor;
            System.out.println("Cargué el valor número: " + valoresCargados);
            valoresCargados++;
        } else {
            System.out.println("No se pudo cargar el valor.");
            insertarValorEnPosicionAleatorea();
        }
    }

    private boolean valorValidoEnPosicion(int filaAleatorea, int columnaAleatorea, int valor) {
        boolean valorValido = false;
        if (valorValidoEnFila(filaAleatorea, valor)) {
            if (valorValidoEnColumna(columnaAleatorea, valor)) {
                if (valorValidoEnCuadrante(filaAleatorea, columnaAleatorea, valor)) {
                    valorValido = true;
                }
            }
        }
        return valorValido;
    }

    private boolean valorValidoEnCuadrante(int filaAleatorea, int columnaAleatorea, int valor) {
        Cuadrante cuadrante = new Cuadrante(filaAleatorea, columnaAleatorea, this.tablero);
        return !cuadrante.contains(valor);
    }

    private boolean valorValidoEnColumna(int columnaAleatorea, int valor) {
        boolean valorValido = true;
        for (int i = 0; i < COLUMNAS; i++) {
            if (this.tablero[i][columnaAleatorea] == valor) {
                valorValido = false;
            }
        }
        return valorValido;
    }

    private boolean valorValidoEnFila(int filaAleatorea, int valor) {
        boolean valorValido = true;
        for (int i = 0; i < FILAS; i++) {
            if (this.tablero[filaAleatorea][i] == valor) {
                valorValido = false;
            }
        }
        return valorValido;
    }

    private int numeroAleatoreoEntre(int minimo, int maximo) {
        return (int) Math.floor(Math.random() * (maximo - minimo + 1) + minimo);
    }

    private void iniciar() {
        jugar();
        mostrarResultado();
        volverAEmpezar();
    }

    private void volverAEmpezar() {
        System.out.print("¿Te gustaría volver a intentarlo? S/N");
        String respuesta = input.nextLine();
        if (respuesta.equals("S")) {
            new Tablero();
        } else {
            System.out.println("Gracias por participar.");
        }
    }

    private void jugar() {
        while (!tableroCompleto() && erroresNoSuperados()) {
            mostrarTablero();
            pedirRonda();
        }
    }

    private boolean erroresNoSuperados() {
        return this.errores < this.nivel.getNumerosPrecargados() / 10;
    }

    private void mostrarResultado() {
        if (erroresNoSuperados()) {
            System.out.println("¡Ganaste!");
        } else {
            System.out.println("¡Perdiste!");
        }
    }

    private void mostrarTablero() {
        System.out.println(this.toString());
    }

    private boolean tableroCompleto() {
        int celdasVacias = 0;
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (this.tablero[i][j] != 0) {
                    celdasVacias++;
                }
            }
        }
        return celdasVacias == 0;
    }

    private void pedirRonda() {
        Ronda ronda = new Ronda();
        if (posicionVacia(ronda.getFila(), ronda.getColumna())) {
            if (valorValidoEnPosicion(ronda.getFila(), ronda.getColumna(), ronda.getValor())) {
                tablero[ronda.getFila()][ronda.getColumna()] = ronda.getValor();
            } else {
                System.out.println("Indicaste un valor incorrecto para la posición pretendida.");
                this.errores++;
                this.jugar();
            }
        } else {
            System.out.println("La posición pretendida no está vacía.");
            this.jugar();
        }
    }

    private boolean posicionVacia(int fila, int columna) {
        return this.tablero[fila][columna] == 0;
    }

    public String toString() {
        StringBuilder tablero = new StringBuilder();
        int contadorFilas = 0;

        tablero.append("----------Tablero----------\n");
        tablero.append("Tenés ").append(this.errores).append("/").append(this.nivel.getNumerosPrecargados() / 10).append(" errores disponibles\n");
        tablero.append(" coord  |123|456|789|\n");
        tablero.append("        ------------\n");

        for (int i = 0; i < FILAS; i++) {
            tablero.append("       ").append(i + 1).append("|");
            int contadorColumnas = 0;
            for (int j = 0; j < COLUMNAS; j++) {
                tablero.append(this.tablero[i][j]);
                contadorColumnas++;
                if (contadorColumnas == 3) {
                    tablero.append("|");
                    contadorColumnas = 0;
                }
            }
            contadorFilas++;
            if (contadorFilas == 3) {
                tablero.append("\n        ------------");
                contadorFilas = 0;
            }
            tablero.append("\n");
        }

        tablero.append("-----Nivel ").append(this.nivel.toString()).append("-----\n\n");
        return tablero.toString();
    }

}