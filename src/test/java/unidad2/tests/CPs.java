package unidad2.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import unidad2.pages.FormPage;
import unidad2.pages.HomePage;

public class CPs {
    //Atributos
    WebDriver driver;
    HomePage home;
    FormPage form;

    String browser = "chrome";
    String propertyDriver = "webdriver.chrome.driver";
    String rutaDriver = "C:\\Users\\domingo.saavedra\\Documents\\SeleniumRepasoPrueba1\\src\\test\\resources\\drivers\\chromedriver.exe";
    String url = "https://sitio.consorcio.cl/";

    @BeforeEach
    public void preCondiciones(){
        //Preparar las Page's
        home = new HomePage(driver);
        home.conexionDriver(browser,rutaDriver,propertyDriver); //conectar el driver
        form = new FormPage(home.getDriver());
        home.cargarSitio(url);
        home.maximizarBrowser();
        
    }
    
    @AfterEach
    public void posCondiciones(){
        home.cerrarBrowser();
    }

    @Test
    public void CP001_Error_creacion_Cta_problema_tecnico(){
        home.clickPortateHazteCliente();
        home.esperarXSegundos(1000);
        home.clickLinkHazteCliente();
        form.ingresarRut("41177071-0");
        home.esperarXSegundos(2000);
        form.seleccionarNacChilena(false);
        form.agregarCorreo("correovalido@algo.com");
        form.agregarTelefono("87645876");
        form.continuar();
        Assertions.assertEquals("Tenemos un problema técnico",form.obtenerErrorProblemaTecnico());
    }
    
    
}
