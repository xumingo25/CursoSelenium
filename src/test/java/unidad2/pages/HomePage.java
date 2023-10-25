package unidad2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import unidad2.utils.BaseClass;

public class HomePage extends BaseClass {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    //Centralizar los By
    By locatorBtnPortate = By.xpath("//button[@id='hi_header_login_a']");

    By locatorLinkHazteCliente = By.id("header_hazte_cliente_banco");



    //Crear los métodos genericos que representen las acciones de la página
    public void clickPortateHazteCliente(){
        click(esperarPorElementoWebAClickear(locatorBtnPortate));
    }

    public void clickLinkHazteCliente(){
        click(esperarPorElementoWebAClickear(locatorLinkHazteCliente));
    }

    /*
    public void hacerseClienteBanco(){
        click(esperarPorElementoWebAClickear(locatorBtnPortate));
        click(esperarPorElementoWebAClickear(locatorLinkHazteCliente));
    }*/
}
