package modelo;

public class GeneradorPrefijado implements Generador {
    private final int[] valores;
    private int indice;


    public GeneradorPrefijado(String patron) {
        valores = new int[patron.length()];
        for (int i = 0; i < patron.length(); i++) {
            valores[i] = patron.charAt(i) == '1' ? 1 : -1;
        }
        indice = 0;
    }

    @Override
    public int nextInt() {
        if (indice >= valores.length) {
            return -1;
        }
        return valores[indice++];
    }
}
