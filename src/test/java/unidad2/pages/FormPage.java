package unidad2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import unidad2.utils.BaseClass;

public class FormPage extends BaseClass {

    public FormPage(WebDriver driver) {
        super(driver);
    }

    //Centralizar los By
    By locatorTxtRut = By.name("rut");
    By locatorNacChilena = By.xpath("//input[@id='nacionalidadChileno']");
    By locatorNacExtranjero = By.xpath("//input[@id='nacinalidadExtranjero']");
    By locatorOtraNacNO = By.id("otraNacionalidadNo");
    By locatorNacExtranjeraDetalle = By.id("search-text-Nacionalidad");

    By locatorMail = By.xpath("//input[@name='email']");
    By locatorTelefono = By.xpath("//input[@placeholder='Ej: 1234 5678']");
    By locatorBtnContinuar = By.id("step1_btn");
    By locatorErrorRutInvalido = By.xpath("//span[contains(text(),'El Rut ingresado')]");
    By locatorErrorProblemaTecnico = By.className("title");

    //Crear los métodos genericos que representen las acciones de la página
    public void ingresarRut(String rut){
        agregarTexto(esperarPorPresenciaDeElemento(locatorTxtRut),rut);
    }

    public void seleccionarNacChilena(boolean nacAdicional){
        click(esperarPorElementoWebAClickear(locatorNacChilena));
        if(!nacAdicional){
            click(esperarPorElementoWebAClickear(locatorOtraNacNO));
        }else{
            //agregar logica para el caso...
        }
    }

    public void seleccionarNacExtranjera(String pais){//Selecciona tu nacionalidad
        By locatorSeleccionNacionalidadVacio = By.xpath("//div[contains(text(),'"+pais+"')]");
        click(esperarPorElementoWebAClickear(locatorNacExtranjero));
        click(esperarPorPresenciaDeElemento(locatorNacExtranjeraDetalle));
        click(esperarPorPresenciaDeElemento(locatorSeleccionNacionalidadVacio));
    }

    public void agregarCorreo(String correo){
        agregarTexto(esperarPorPresenciaDeElemento(locatorMail),correo);
    }

    public void agregarTelefono(String telefono){
        agregarTexto(esperarPorPresenciaDeElemento(locatorTelefono),telefono);
    }

    public void continuar(){

        click(esperarPorElementoWebAClickear(locatorBtnContinuar));
    }

    public String obtenerErrorProblemaTecnico(){
        return obtenerTexto(esperarPorPresenciaDeElemento(locatorErrorProblemaTecnico));
    }

    public String obtenerErrorRutInvalido(){
        return obtenerTexto(esperarPorPresenciaDeElemento(locatorErrorRutInvalido));
    }



}
