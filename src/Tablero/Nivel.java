package Tablero;

public enum Nivel {
    Principiante(50), Intermedio(40), Avanzado(30);

    private int numerosPrecargados;

    Nivel(int numerosPrecargados) {
        this.numerosPrecargados = numerosPrecargados;
    }

    public int getNumerosPrecargados(){
        return this.numerosPrecargados;
    }
}
