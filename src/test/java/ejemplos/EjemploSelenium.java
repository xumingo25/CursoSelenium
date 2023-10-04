package ejemplos;

import org.openqa.selenium.By; //crear estrategias de busqueda (ID,Name, Xpath, etc)
import org.openqa.selenium.JavascriptExecutor; //Scrolling
import org.openqa.selenium.WebDriver; //Emular el browser
import org.openqa.selenium.WebElement; //Elementos web
import org.openqa.selenium.chrome.ChromeDriver; //Instancia el browser como navegador de google chrome

import java.nio.charset.StandardCharsets;


public class EjemploSelenium {
    public static void main(String[] args) throws InterruptedException {

        //String ruta del driver
        String rutaDriver = "C:\\Users\\domingo.saavedra\\Desktop\\SeleniumPrimerosPasos\\src\\test\\resources\\drivers\\chromedriver.exe";

        //Enlazar el driver como property de windows para el manejo
        //Del navegador
        System.setProperty("webdriver.chrome.driver",rutaDriver);

        //Instanciar un objeto de tipo driver de chrome
        WebDriver driver = new ChromeDriver();

        //Cargar la pagina de google
        driver.get("https://www.google.cl");

        System.out.println("El titulo de la pagina es: "+driver.getTitle());

        System.out.println("La url del sitio es: "+driver.getCurrentUrl());

        System.out.println("--------------------------------------------------");
        //Redirigir el sitio a la pagina de consorcio
        driver.navigate().to("https://sitio.consorcio.cl/");

        System.out.println("El titulo de la pagina es: "+driver.getTitle());

        System.out.println("La url del sitio es: "+driver.getCurrentUrl());


        //maximizar el browser
        driver.manage().window().maximize();

        //Estrategia de busqueda (By)
        By locatorBtnPortate = By.xpath("//button[@id='hi_header_login_a']");

        //Crear el boton(WebElement)
        WebElement btnPortate = driver.findElement(locatorBtnPortate);

        //Click al boton
        btnPortate.click();

        Thread.sleep(2000); //ESPERA 2 SEGUNDOS

        //Estrategia de busqueda (By)
        By locatorLinkHazteCliente = By.id("header_hazte_cliente_banco");

        //Crear el boton(WebElement)
        WebElement linkHazteCliente = driver.findElement(locatorLinkHazteCliente);

        //Click al boton
        linkHazteCliente.click();

        Thread.sleep(2000); //ESPERA 2 SEGUNDOS

        driver.findElement(By.name("rut")).sendKeys("41177071-0");

        Thread.sleep(2000); //ESPERA 2 SEGUNDOS

        driver.findElement(By.xpath("//input[@id='nacionalidadChileno']")).click();

        driver.findElement(By.id("otraNacionalidadNo")).click();
        //driver.findElement(By.xpath("//input[@name='otraNacionalidad'][@value='No']")).click();

        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("correovalido@algo.com");

        driver.findElement(By.xpath("//input[@placeholder='Ej: 1234 5678']")).sendKeys("87645876");

        //driver.findElement(By.xpath("//cns-button[@label='Continuar']")).click();
        Thread.sleep(2000); //ESPERA 2 SEGUNDOS

        //Clase JS Executor
        JavascriptExecutor js = (JavascriptExecutor) driver;


        js.executeScript("window.scrollBy(0,300)"); //Scroll vertically down by 300 pixels

        WebElement btnContinuar = driver.findElement(By.xpath("//cns-button[@label='Continuar']"));

        //This will scroll the page till the element is found
        //js.executeScript("arguments[0].scrollIntoView();", btnContinuar);
        Thread.sleep(2000); //ESPERA 2 SEGUNDOS

        if(btnContinuar.isEnabled()){
            btnContinuar.click();
        }

        Thread.sleep(10000);

        String resultadoActual = corregirFormatoTexto(driver.findElement(By.className("title")).getText());
        String resultadoEsperado = "Tenemos un problema técnico";

        if(resultadoActual.equalsIgnoreCase(resultadoEsperado)){
            System.out.println("OK");
            System.out.println("Resultado esperado: "+resultadoEsperado);
            System.out.println("Resultado actual: "+resultadoActual);
        }else{
            System.out.println("NO ok");
            System.out.println("Resultado esperado: "+resultadoEsperado);
            System.out.println("Resultado actual: "+resultadoActual);
        }
    }

    public static String corregirFormatoTexto(String textoIncorrecto){
        byte[] bytes = textoIncorrecto.getBytes(StandardCharsets.UTF_8);
        String textoCorrejido = new String(bytes, StandardCharsets.ISO_8859_1);

        return textoCorrejido;
    }
}
