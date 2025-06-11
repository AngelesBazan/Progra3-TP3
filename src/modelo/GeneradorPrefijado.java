package modelo;

public class GeneradorPrefijado implements Generador {
    private final int[] valores;
    private int indice;

    /**
     * Constructor que recibe una cadena de '1' y '0'.
     * '1' representa el valor 1.
     * '0' representa el valor -1.
     * Ejemplo: "1010" -> [1, -1, 1, -1]
     */
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
            // Podés lanzar excepción si preferís que no se repita
            return -1;
        }
        return valores[indice++];
    }
}
