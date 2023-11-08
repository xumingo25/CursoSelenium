package unidad2.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import unidad2.pages.FormPage;
import unidad2.pages.HomePage;
import unidad2.utils.ManejoEncoding;

public class CPs {
    //Atributos
    WebDriver driver;
    HomePage home;
    FormPage form;

    String browser = "chrome";
    String propertyDriver = "webdriver.chrome.driver";
    String rutaDriver = "C:\\Users\\domingo.saavedra\\Documents\\Selenium2Unidad\\src\\test\\resources\\drivers\\chromedriver.exe";
    String url = "https://sitio.consorcio.cl/";

    @BeforeEach //Pre condiciones para cada test
    public void preCondiciones(){
        //Preparar las Page's
        home = new HomePage(driver);
        home.conexionDriver(browser,rutaDriver,propertyDriver); //conectar el driver
        form = new FormPage(home.getDriver());
        home.cargarSitio(url); //Cargar página
        home.maximizarBrowser(); //maximizar el browser
        
    }
    
    @AfterEach //Pos condiciones para cada test
    public void posCondiciones(){
        home.cerrarBrowser();
    }

    @Test
    public void CP001_Error_creacion_Cta_problema_tecnico(){
        home.clickPortateHazteCliente();
        home.esperarXSegundos(1000);
        home.clickLinkHazteCliente();
        form.ingresarRut("41177071-0");
        form.esperarXSegundos(2000);
        form.seleccionarNacChilena(false);
        form.agregarCorreo("correovalido@algo.com");
        form.agregarTelefono("87645876");
        //Scroll
        form.ScrollingByPixelDown(400);
        form.esperarXSegundos(2000);
        form.continuar();
        String resultadoEsperado = ManejoEncoding.corregirFormatoTexto("Tenemos un problema técnico");
        Assertions.assertEquals(resultadoEsperado,form.obtenerErrorProblemaTecnico());
    }
    
    
}
