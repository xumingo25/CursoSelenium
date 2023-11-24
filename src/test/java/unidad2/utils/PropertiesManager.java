package unidad2.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {

    //Atributos
    private static Properties props; //ESTA ACA, pero aun no almacena nada



    //Métodos
    public static String obtenerProperty(String key){
        props = new Properties();

        String rutaProyecto = System.getProperty("user.dir");

        String rutaFile = rutaProyecto+ "\\src\\test\\resources\\properties.properties";


        try{
            InputStream input = new FileInputStream(rutaFile); //Creamos el fichero properties

            props.load(input); //cargamos el archivo como properties
        }catch(Exception ex){
            System.out.println("Ha ocurrido un error cargando fichero properties...");
            System.out.println("Ruta del archivo: "+rutaFile);
            System.out.println(ex.getMessage());
        }

        return props.getProperty(key);
    }

}
