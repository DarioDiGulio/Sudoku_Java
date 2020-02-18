package Tablero;

public class Cuadrante implements Cuadrantes {
    private int[] filas;
    private int[] columnas;
    private int numeroCuadrante;
    private int[] valoresDelCuadrante;
    private int ultimaPosicionValorAgregado;

    public Cuadrante(int fila, int columna, int[][] tablero) {
        setFilas(fila);
        setColumna(columna);
        setCuadrante();
        this.valoresDelCuadrante = new int[9];
        this.ultimaPosicionValorAgregado = 0;
        setValoresCuadrante(tablero);
    }

    private void setValoresCuadrante(int[][] tablero) {
        for (int i = this.filas[0]; i < this.filas[2]; i++) {
            for (int j = this.columnas[0]; j < this.columnas[2]; j++) {
                this.valoresDelCuadrante[ultimaPosicionValorAgregado] = tablero[i][j];
                ultimaPosicionValorAgregado++;
            }
        }
    }

    private void setCuadrante() {
        esPrimerCuadrante();
        esSegundoCuadrante();
        esTercerCuadrante();
        esCuartoCuadrante();
        esQuintoCuadrante();
        esSextoCuadrante();
        esSeptimoCuadrante();
        esOctavoCuadrante();
        esNovenoCuadrante();
    }

    private void esNovenoCuadrante() {
        if (sumarEnterosDeArreglo(this.filas) == 24 && sumarEnterosDeArreglo(this.columnas) == 24) {
            this.numeroCuadrante = 9;
        }
    }

    private void esOctavoCuadrante() {
        if (sumarEnterosDeArreglo(this.filas) == 24 && sumarEnterosDeArreglo(this.columnas) == 15) {
            this.numeroCuadrante = 8;
        }
    }

    private void esSeptimoCuadrante() {
        if (sumarEnterosDeArreglo(this.filas) == 24 && sumarEnterosDeArreglo(this.columnas) == 6) {
            this.numeroCuadrante = 7;
        }
    }

    private void esSextoCuadrante() {
        if (sumarEnterosDeArreglo(this.filas) == 15 && sumarEnterosDeArreglo(this.columnas) == 24) {
            this.numeroCuadrante = 6;
        }
    }

    private void esQuintoCuadrante() {
        if (sumarEnterosDeArreglo(this.filas) == 15 && sumarEnterosDeArreglo(this.columnas) == 15) {
            this.numeroCuadrante = 5;
        }
    }

    private void esCuartoCuadrante() {
        if (sumarEnterosDeArreglo(this.filas) == 15 && sumarEnterosDeArreglo(this.columnas) == 6) {
            this.numeroCuadrante = 4;
        }
    }

    private void esTercerCuadrante() {
        if (sumarEnterosDeArreglo(this.filas) == 6 && sumarEnterosDeArreglo(this.columnas) == 24) {
            this.numeroCuadrante = 3;
        }
    }

    private void esSegundoCuadrante() {
        if (sumarEnterosDeArreglo(this.filas) == 6 && sumarEnterosDeArreglo(this.columnas) == 15) {
            this.numeroCuadrante = 2;
        }
    }

    private void esPrimerCuadrante() {
        if (sumarEnterosDeArreglo(this.filas) == 6 && sumarEnterosDeArreglo(this.columnas) == 6) {
            this.numeroCuadrante = 1;
        }
    }

    private void setColumna(int columna) {
        this.columnas = new int[3];
        esPrimerColumna(columna);
        esSegundaColumna(columna);
        esTercerColumna(columna);
    }

    private void esTercerColumna(int columna) {
        if (columna >= MINIMO_COLUMNAS_TERCER_FILA && columna <= MAXIMO_COLUMNAS_TERCER_FILA) {
            for (int i = 0; i < COLUMNAS_POR_CUADRANTE; i++) {
                this.columnas[i] = COLUMNAS_TERCER_FILA[i];
            }
        }
    }

    private void esSegundaColumna(int columna) {
        if (columna >= MINIMO_COLUMNAS_SEGUNDA_FILA && columna <= MAXIMO_COLUMNAS_SEGUNDA_FILA) {
            for (int i = 0; i < COLUMNAS_POR_CUADRANTE; i++) {
                this.columnas[i] = COLUMNAS_SEGUNDA_FILA[i];
            }
        }
    }

    private void esPrimerColumna(int columna) {
        if (columna >= MINIMO_COLUMNAS_PRIMER_FILA && columna <= MAXIMO_COLUMNAS_PRIMER_FILA) {
            for (int i = 0; i < COLUMNAS_POR_CUADRANTE; i++) {
                this.columnas[i] = COLUMNAS_PRIMER_FILA[i];
            }
        }
    }

    private void setFilas(int fila) {
        this.filas = new int[3];
        esPrimerFila(fila);
        esSegundaFila(fila);
        esTercerFila(fila);
    }

    private void esTercerFila(int fila) {
        if (fila >= MINIMO_FILAS_TERCER_COLUMNA && fila <= MAXIMO_FILAS_TERCER_COLUMNA) {
            for (int i = 0; i < FILAS_POR_CUADRANTE; i++) {
                this.filas[i] = FILAS_TERCER_COLUMNA[i];
            }
        }
    }

    private void esSegundaFila(int fila) {
        if (fila >= MINIMO_FILAS_SEGUNDA_COLUMNA && fila <= MAXIMO_FILAS_SEGUNDA_COLUMNA) {
            for (int i = 0; i < FILAS_POR_CUADRANTE; i++) {
                this.filas[i] = FILAS_SEGUNDA_COLUMNA[i];
            }
        }
    }

    private void esPrimerFila(int fila) {
        if (fila >= MINIMO_FILAS_PRIMER_COLUMNA && fila <= MAXIMO_FILAS_PRIMER_COLUMNA) {
            for (int i = 0; i < FILAS_POR_CUADRANTE; i++) {
                this.filas[i] = FILAS_PRIMER_COLUMNA[i];
            }
        }
    }

    public int[] getFilas() {
        return filas;
    }

    public int[] getColumnas() {
        return columnas;
    }

    public int getNumeroCuadrante() {
        return numeroCuadrante;
    }

    public int sumarEnterosDeArreglo(int[] arreglo) {
        int total = 0;
        for (int value : arreglo) {
            total += value;
        }
        return total;
    }

    public int[] getValoresDelCuadrante() {
        return valoresDelCuadrante;
    }

    public boolean contains (int valor){
        boolean estaValor = false;
        for (int i = 0; i < this.valoresDelCuadrante.length - 1; i++) {
            if (this.valoresDelCuadrante[i] == valor){
                estaValor = true;
            }
        }
        return estaValor;
    }
}
