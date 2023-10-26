import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {

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
	public void testeTextField() {
		// Assert.assertEquals("Google", driver.getTitle());
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");

		Assert.assertEquals("Teste de escrita", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));

	}

	@Test
	public void testeDeveInteragirComTextArea() {

		driver.findElement(By.id("elementosForm:sugestoes"))
				.sendKeys("Teste do campo\n Teste do campo, segunda linha \n Teste do campo, terceira linha");
		Assert.assertEquals("Teste do campo\n Teste do campo, segunda linha \n Teste do campo, terceira linha",
				driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));

	}

	@Test
	public void testeDeveInteragirComRadioButton() {

		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		// Assert.assertEquals(driver.findElement(By.id("elementosForm:sexo:0")).isSelected(),
		// true);

	}

	@Test
	public void testeDeveInteragirComCheckBox() {

		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
		Assert.assertEquals(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected(), true);

	}

	@Test
	public void testeDeveInteragirComCombo() {

		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));

		Select combo = new Select(element);

		// combo.selectByIndex(2);
		// combo.selectByValue("superior");
		combo.selectByVisibleText("2o grau completo");

		Assert.assertEquals("2o grau completo", combo.getFirstSelectedOption().getText());

	}

	@Test
	public void testeDeveVerificarValoresCombo() {

		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));

		Select combo = new Select(element);

		List<WebElement> options = combo.getOptions();

		Assert.assertEquals(8, options.size());

		boolean encontrou = false;

		for (WebElement option : options) {
			if (option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		}

		Assert.assertTrue(encontrou);

	}

	@Test
	public void testeDeveVerificarValoresComboMultiplo() {

		WebElement element = driver.findElement(By.id("elementosForm:esportes"));

		Select combo = new Select(element);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");

		List<WebElement> options = combo.getAllSelectedOptions();
		Assert.assertEquals(3, options.size());

		combo.deselectByVisibleText("Corrida");
		options = combo.getAllSelectedOptions();
		Assert.assertEquals(2, options.size());

	}

	@Test
	public void testDeveInteragirComBotoes() {

		WebElement botao = driver.findElement(By.id("buttonSimple"));
		botao.click();

		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));

	}

	@Test
	public void testDeveInteragirLink() {

		driver.findElement(By.linkText("Voltar")).click();

		Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());

	}

}
