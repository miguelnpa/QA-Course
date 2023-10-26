import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteFramesEJanelas {
	

	private WebDriver driver;

	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get(System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");

	}

	@After
	public void finaliza() {
		driver.quit();
	}

	
	@Test
	public void testeDeveInteragirComFrames() {
		
		
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		
		Alert alert = driver.switchTo().alert();
		
		String text = alert.getText();
		
		Assert.assertEquals("Frame OK!", text);
		
		alert.accept();
		
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(text);
		
		
	}
	
	@Test
	public void testeDeveInteragirComJanelas() {
		
		
		String windowHandle = driver.getWindowHandle();
		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.close();
		
		driver.switchTo().window(windowHandle);
		
		driver.findElement(By.tagName("textarea")).sendKeys("e agora?");
		
	
	}
	
	@Test
	public void testeDeveInteragirComJanelasSemTitulo() {
	

		String windowHandle = driver.getWindowHandle();
		driver.findElement(By.id("buttonPopUpHard")).click();
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getWindowHandles());
		driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.switchTo().window(windowHandle);

		driver.findElement(By.tagName("textarea")).sendKeys("e agora?");

	}
}
