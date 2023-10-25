import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;



public class TesteAlert {
	@Test
	public void testeDeveInteragirComAlertSimples() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get(System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
		
		WebElement element = driver.findElement(By.id("alert"));
		element.click();
		
		Alert alert = driver.switchTo().alert();	
		String texto = alert.getText();
		Assert.assertEquals("Alert Simples", texto);
		alert.accept();
		
		WebElement textElement = driver.findElement(By.id("elementosForm:nome"));
		textElement.sendKeys(texto);
		
		driver.quit();
	}
	
	@Test
	public void testeDeveInteragirComAlertConfirmSimples() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get(System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
		
		WebElement element = driver.findElement(By.id("confirm"));
		element.click();
		
		
		Alert confirm = driver.switchTo().alert();	
		Assert.assertEquals("Confirm Simples", confirm.getText());
		confirm.accept();
		Assert.assertEquals("Confirmado", confirm.getText());
		confirm.accept();
		
		element.click();
		
		
		confirm = driver.switchTo().alert();	
		Assert.assertEquals("Confirm Simples", confirm.getText());
		confirm.dismiss();
		Assert.assertEquals("Negado", confirm.getText());
		confirm.dismiss();
		
		driver.quit();
	}
	
	@Test
	public void testeDeveInteragirComPrompt() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get(System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
		
		WebElement element = driver.findElement(By.id("prompt"));
		element.click();
		
		String text = "12";
		Alert alert = driver.switchTo().alert();	
		Assert.assertEquals("Digite um numero", alert.getText());
		alert.sendKeys(text);
		alert.accept();
		
		Assert.assertEquals("Era 12?", alert.getText());
		alert.accept();
		
		Assert.assertEquals(":D", alert.getText());
		
		driver.quit();
	}
}
