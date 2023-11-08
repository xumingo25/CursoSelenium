package unidad2.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BaseClass {
    //Atributos
    private WebDriver driver;
    private WebDriverWait wait;


    //Métodos
    public BaseClass(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    //Funciones que envuelven las funciones de selenium

    public void click(By localizador) {
        this.driver.findElement(localizador).click();
    }

    public void click(WebElement elemento) {
        elemento.click();
    }

    public void maximizarBrowser() {
        this.driver.manage().window().maximize();
    }

    public void cerrarBrowser() {
        this.driver.quit();
    }

    public String obtenerTexto(By localizador) {
        return this.driver.findElement(localizador).getText();
    }

    public String obtenerTexto(WebElement elemento) {
        return elemento.getText();
    }

    public void agregarTexto(By localizador,String texto){
        this.driver.findElement(localizador).sendKeys(texto);
    }

    public void agregarTexto(WebElement elemento,String texto){
        elemento.sendKeys(texto);
    }

    //ESPERAS

    //fija en base a los segundos recibidos
    public void esperarXSegundos(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch (InterruptedException ex){
            System.out.println("Fallo la espera...");
            System.out.println("Error: "+ ex.getLocalizedMessage());
        }
    }

    public WebElement esperarPorElementoWebAClickear(By localizador){
        wait = new WebDriverWait(driver,30);
        return wait.until(ExpectedConditions.elementToBeClickable(localizador));
    }

    public WebElement esperarPorPresenciaDeElemento(By localizador){
        wait = new WebDriverWait(driver,30);
        return wait.until(ExpectedConditions.presenceOfElementLocated(localizador));
    }

    public void cargarSitio(String url){
        this.driver.get(url);
    }

    public List<WebElement> buscarElementosWeb(By localizador){
        return this.driver.findElements(localizador);
    }

    public WebDriver conexionDriver(String nombre,String rutaDriver,String propertyDriver){
        switch (nombre){
            case "chrome":
                System.setProperty(propertyDriver,rutaDriver);
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty(propertyDriver,rutaDriver);
                driver = new FirefoxDriver();
                break;
            case "edge":
                System.setProperty(propertyDriver,rutaDriver);
                driver = new EdgeDriver();
                break;
            case "safari":
                System.setProperty(propertyDriver,rutaDriver);
                driver = new SafariDriver();
                break;
            default:
                driver = null;
                break;
        }
        return driver;
    }

    //Scrolling
    public void ScrollingByPixelDown(int pixeles){
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollBy(0,"+pixeles+")"); //Scroll vertically down by 300 pixels
    }

    public void ScrollingByPixelUP(int pixeles){
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollBy(0,"+pixeles+")"); //Scroll vertically down by 300 pixels
    }


}
