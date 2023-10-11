package SeleniumTecNM;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class PrimerTestSelenium {
	private WebDriver driver;
	private static final String URL_SITE = "http://localhost:3000/";
	
	@BeforeClass
	public static void setupWebdriver() {
		// Download the driver from https://github.com/mozilla/geckodriver/releases
		//System.setProperty("webdriver.gecko.driver", Path_of_Firefox_Driver); // Setting system properties of FirefoxDriver
		// AQUÍ PON LA RUTA DE TU DRIVER, TAMBIÉN AGREGA EL DRIVER!!
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/chromedriver.exe");
	}
	
	@Before
	public void setup() {
		//driver = new FirefoxDriver();
		driver = new ChromeDriver();
	}
	
	@After
	public void teardown( ) {
		if (driver != null) {
			driver.quit();
		}
	}
	
	@Test
	public void testTituloPresent() throws InterruptedException {
		driver.get(URL_SITE);
		
		WebElement titulo = driver.findElement(By.cssSelector("#root > div > header > h1"));
		WebElement subtitulo = driver.findElement(By.xpath("//*[@id=\"root\"]/div/header/p"));
		WebElement selectTags = driver.findElement(By.cssSelector("#root > div > form > select:nth-child(1)"));
		WebElement inputField = driver.findElement(By.cssSelector("#root > div > form > input.input-text"));
		WebElement selectAssign = driver.findElement(By.cssSelector("#root > div > form > select:nth-child(3)"));
		WebElement button = driver.findElement(By.cssSelector("#root > div > form > input.input-submit"));
		List<WebElement> resultados = driver.findElements(By.cssSelector("#root > div > div > li"));
		
		assertNotNull(titulo);
		assertEquals(titulo.getText(), "Simple Todo App");
		assertNotNull(subtitulo);
		assertEquals(subtitulo.getText(), "Please add to-dos item(s) through the input field");
		assertNotNull(selectTags);
		assertNotNull(inputField);
		assertNotNull(selectAssign);
		assertNotNull(button);
		assertEquals(button.getAttribute("value"), "Submit");
		assertEquals(resultados.size(), 0);
	}
	@Test
	public void testGoogle() throws InterruptedException {
		driver.get("https://www.google.com/");
		
		WebElement cuadroTexto = driver.findElement(By.cssSelector("#APjFqb"));
		cuadroTexto.sendKeys("itm");
		cuadroTexto.submit();
		Thread.sleep(5000);
	}
	
	@Test
	public void testClickAndAddElement() throws InterruptedException {
        driver.get(URL_SITE);
        String textToSend = "Juan Barrera";
        
        WebElement cuadroBusqueda = driver.findElement(By.cssSelector("#root > div > form > input.input-text"));
		cuadroBusqueda.sendKeys(textToSend);
		Select selectTags = new Select(driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/select[1]")));
        selectTags.selectByVisibleText("Home");
		WebElement button = driver.findElement(By.cssSelector("#root > div > form > input.input-submit"));
		button.click();
		//cuadroBusqueda.submit();
		
		WebElement descriptionTask = driver.findElement(By.cssSelector("#root > div > div > li > div.todo-heading > h3"));
		assertEquals(descriptionTask.getText(), textToSend);
	}
}
