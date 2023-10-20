package unidad1.CPs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.charset.StandardCharsets;


public class CPs_front_banking {
    //Atributos
    WebDriver driver;

    @BeforeEach
    public void preCondiciones(){
        //String ruta del driver
        String rutaDriver = "C:\\Users\\domingo.saavedra\\Documents\\SeleniumRepasoPrueba1\\src\\test\\resources\\drivers\\chromedriver.exe";

        //Enlazar el driver como property de windows para el manejo
        //Del navegador
        System.setProperty("webdriver.chrome.driver",rutaDriver);

        //Instanciar un objeto de tipo driver de chrome
        driver = new ChromeDriver();
        driver.get("https://sitio.consorcio.cl/");

        //maximizar el browser
        driver.manage().window().maximize();
    }

    @AfterEach
    public void posCondiciones(){
        //pos condicion
        driver.quit();
    }

    @Test
    public void CP002_Error_creacion_Cta_rut_invalido() throws InterruptedException {
        By locatorBtnPortate = By.xpath("//button[@id='hi_header_login_a']");

        WebElement btnPortate = driver.findElement(locatorBtnPortate);

        btnPortate.click();

        Thread.sleep(2000); //ESPERA 2 SEGUNDOS

        By locatorLinkHazteCliente = By.id("header_hazte_cliente_banco");

        WebElement linkHazteCliente = driver.findElement(locatorLinkHazteCliente);

        linkHazteCliente.click();

        Thread.sleep(2000); //ESPERA 2 SEGUNDOS

        driver.findElement(By.name("rut")).sendKeys("302432107");

        Thread.sleep(2000); //ESPERA 2 SEGUNDOS

        driver.findElement(By.xpath("//input[@id='nacionalidadChileno']")).click();

        driver.findElement(By.id("otraNacionalidadNo")).click();

        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("correovalido@algo.com");

        driver.findElement(By.xpath("//input[@placeholder='Ej: 1234 5678']")).sendKeys("87645876");

        Thread.sleep(2000); //ESPERA 2 SEGUNDOS

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollBy(0,300)"); //Scroll vertically down by 300 pixels

        WebElement btnContinuar = driver.findElement(By.xpath("//cns-button[@label='Continuar']"));

        Thread.sleep(2000); //ESPERA 2 SEGUNDOS


        Assertions.assertFalse(!btnContinuar.isEnabled());

        Thread.sleep(10000);

        String resultadoActual = corregirFormatoTexto(driver.findElement(By.xpath("//span[contains(text(),'El Rut ingresado')]")).getText());
        String resultadoEsperado = "El Rut ingresado no es válido";

        Assertions.assertEquals(resultadoEsperado,resultadoActual);
    }

    @Test
    public void CP003_Seleccionar_nacionalidad_Extranjera() throws InterruptedException {
        By locatorBtnPortate = By.xpath("//button[@id='hi_header_login_a']");

        WebElement btnPortate = driver.findElement(locatorBtnPortate);

        btnPortate.click();

        Thread.sleep(2000); //ESPERA 2 SEGUNDOS

        By locatorLinkHazteCliente = By.id("header_hazte_cliente_banco");

        WebElement linkHazteCliente = driver.findElement(locatorLinkHazteCliente);

        linkHazteCliente.click();

        Thread.sleep(2000); //ESPERA 2 SEGUNDOS

        driver.findElement(By.name("rut")).sendKeys("302432107");

        Thread.sleep(2000); //ESPERA 2 SEGUNDOS

        driver.findElement(By.xpath("//input[@id='nacinalidadExtranjero']")).click();

        By localorNacionalidad = By.id("search-text-Nacionalidad");

        By localorSeleccionNacionalidadVacio = By.xpath("//div[contains(text(),'Selecciona tu nacionalidad')]");

        driver.findElement(localorNacionalidad).click();
        Thread.sleep(5000); //ESPERA 2 SEGUNDOS
        //driver.findElement(localorNacionalidad).sendKeys("Cubana");
        driver.findElement(localorSeleccionNacionalidadVacio).click();
        Thread.sleep(5000); //ESPERA 2 SEGUNDOS
        //driver.findElement(localorNacionalidad).click();


    }

    @Test
    public void CP001_Error_creacion_Cta_problema_tecnico() throws InterruptedException {
        By locatorBtnPortate = By.xpath("//button[@id='hi_header_login_a']");

        WebElement btnPortate = driver.findElement(locatorBtnPortate);

        btnPortate.click();

        Thread.sleep(2000); //ESPERA 2 SEGUNDOS

        By locatorLinkHazteCliente = By.id("header_hazte_cliente_banco");

        WebElement linkHazteCliente = driver.findElement(locatorLinkHazteCliente);

        linkHazteCliente.click();

        Thread.sleep(2000); //ESPERA 2 SEGUNDOS

        driver.findElement(By.name("rut")).sendKeys("41177071-0");

        Thread.sleep(2000); //ESPERA 2 SEGUNDOS

        driver.findElement(By.xpath("//input[@id='nacionalidadChileno']")).click();

        driver.findElement(By.id("otraNacionalidadNo")).click();

        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("correovalido@algo.com");

        driver.findElement(By.xpath("//input[@placeholder='Ej: 1234 5678']")).sendKeys("87645876");

        Thread.sleep(2000); //ESPERA 2 SEGUNDOS

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollBy(0,300)"); //Scroll vertically down by 300 pixels

        WebElement btnContinuar = driver.findElement(By.xpath("//cns-button[@label='Continuar']"));

        Thread.sleep(2000); //ESPERA 2 SEGUNDOS

        if(btnContinuar.isEnabled()){
            btnContinuar.click();
        }
        Thread.sleep(10000);

        String resultadoActual = corregirFormatoTexto(driver.findElement(By.className("title")).getText());
        String resultadoEsperado = "Tenemos un problema técnico";

        Assertions.assertEquals(resultadoEsperado,resultadoActual);
    }

    public static String corregirFormatoTexto(String textoIncorrecto){
        byte[] bytes = textoIncorrecto.getBytes(StandardCharsets.UTF_8);
        String textoCorrejido = new String(bytes, StandardCharsets.ISO_8859_1);

        return textoCorrejido;
    }
}
