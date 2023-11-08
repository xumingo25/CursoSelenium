package unidad2.utils;

import java.nio.charset.StandardCharsets;

public class ManejoEncoding {
    public static String corregirFormatoTexto(String textoIncorrecto){
        byte[] bytes = textoIncorrecto.getBytes(StandardCharsets.ISO_8859_1);
        String textoCorrejido = new String(bytes, StandardCharsets.UTF_8);

        return textoCorrejido;
    }

    public static void main(String[] args) {
       String texto=  corregirFormatoTexto("Tenemos un problema técnico");
        System.out.println(texto);
    }
}
