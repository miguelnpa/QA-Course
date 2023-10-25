import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteFramesEJanelas {
	@Test
	public void testeDeveInteragirComFrames() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get(System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
		
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		
		Alert alert = driver.switchTo().alert();
		
		String text = alert.getText();
		
		Assert.assertEquals("Frame OK!", text);
		
		alert.accept();
		
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(text);
		
		
		driver.quit();
	}
	
	@Test
	public void testeDeveInteragirComJanelas() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get(System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
		
		String windowHandle = driver.getWindowHandle();
		driver.findElement(By.id("buttonPopUpEasy")).click();;
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.close();
		
		driver.switchTo().window(windowHandle);
		
		driver.findElement(By.tagName("textarea")).sendKeys("e agora?");
		
		driver.quit();
	}
}
