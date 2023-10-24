import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteCampoTreinamento {
	@Test
	public void testeTextField() {
		WebDriver driver = new FirefoxDriver();
		
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get(System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
		// Assert.assertEquals("Google", driver.getTitle());
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
		
		
		
		Assert.assertEquals("Teste de escrita", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		driver.quit();
	}
}