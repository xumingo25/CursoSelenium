package unidad2.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import unidad2.pages.FormPage;
import unidad2.pages.HomePage;
import unidad2.utils.DataDriven;
import unidad2.utils.ManejoEncoding;
import unidad2.utils.PropertiesManager;

import java.util.ArrayList;

public class CPs {
    //Atributos
    WebDriver driver;
    HomePage home;
    FormPage form;
    ArrayList<String> data; //null

    String browser = PropertiesManager.obtenerProperty("browser");
    String propertyDriver = PropertiesManager.obtenerProperty("propertyDriver");
    String rutaDriver = PropertiesManager.obtenerProperty("rutaDriver");
    String url = PropertiesManager.obtenerProperty("url");

    @BeforeEach //Pre condiciones para cada test
    public void preCondiciones(){
        //Preparar las Page's
        data = new ArrayList<String>(); //arreglo de tamaño 0
        home = new HomePage(driver);
        home.conexionDriver(browser,rutaDriver,propertyDriver); //conectar el driver
        form = new FormPage(home.getDriver());
        home.cargarSitio(url); //Cargar página
        home.maximizarBrowser(); //maximizar el browser
        
    }
    
    @AfterEach //Pos condiciones para cada test
    public void posCondiciones(){
        //home.cerrarBrowser();
    }

    @Test
    public void CP001_Error_creacion_Cta_rut_Invalido(){
        data = DataDriven.getData("CP001_Error_creacion_Cta_problema_tecnico");
        home.clickPortateHazteCliente();
        home.esperarXSegundos(1000);
        home.clickLinkHazteCliente();
        form.ingresarRut(data.get(1));
        form.esperarXSegundos(2000);
        form.seleccionarNacChilena(false);
        form.agregarCorreo(data.get(2));
        form.agregarTelefono(data.get(3));
        //Scroll
        //form.ScrollingByPixelDown(500);
        form.esperarXSegundos(2000);
        //form.continuar();
        String resultadoEsperado = data.get(4);
        Assertions.assertEquals(resultadoEsperado,form.obtenerErrorRutInvalido());
    }
    
    
}
