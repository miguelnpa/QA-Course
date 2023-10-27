import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAlert {

	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get(System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
		dsl = new DSL(driver);

	}

	@After
	public void finaliza() {
		driver.quit();
	}

	@Test
	public void testeDeveInteragirComAlertSimples() {

		/*
		WebElement element = driver.findElement(By.id("alert"));
		element.click();

		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Alert Simples", texto);
		alert.accept();

		WebElement textElement = driver.findElement(By.id("elementosForm:nome"));
		textElement.sendKeys(texto);
		*/
		
		dsl.clicarBotao("alert");
		String texto = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Alert Simples", texto);
		
		dsl.escrever("elementosForm:nome", texto);
	}

	@Test
	public void testeDeveInteragirComAlertConfirm() {

		/*
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
		*/
		
		dsl.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoEAceita());
		Assert.assertEquals("Confirmado", dsl.alertaObterTextoEAceita());
		
		dsl.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoENega());
		Assert.assertEquals("Negado", dsl.alertaObterTextoENega());
		
	}

	@Test
	public void testeDeveInteragirComPrompt() {

		/*
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
		*/
		
		dsl.clicarBotao("prompt");
		Assert.assertEquals("Digite um numero", dsl.alertaObterTexto());
		dsl.alertaEscrever("12");
		Assert.assertEquals("Era 12?", dsl.alertaObterTextoEAceita());
		Assert.assertEquals(":D", dsl.alertaObterTextoEAceita());
	}
}
