import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {

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
	public void testeCadastro() {

		driver.findElement(By.id("elementosForm:nome")).sendKeys("Wagner");

		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Costa");

		driver.findElement(By.id("elementosForm:sexo:0")).click();
		;

		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		;

		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));

		Select combo = new Select(element);

		combo.selectByValue("mestrado");

		WebElement selection = driver.findElement(By.id("elementosForm:esportes"));

		Select combo2 = new Select(selection);

		combo2.selectByValue("natacao");

		driver.findElement(By.id("elementosForm:cadastrar")).click();

		WebElement resultado = driver.findElement(By.id("resultado"));

		Assert.assertEquals("Wagner",
				resultado.findElement(By.id("descNome")).findElement(By.tagName("span")).getText());
		Assert.assertEquals("Costa",
				resultado.findElement(By.id("descSobrenome")).findElement(By.tagName("span")).getText());
		Assert.assertEquals("Masculino",
				resultado.findElement(By.id("descSexo")).findElement(By.tagName("span")).getText());
		Assert.assertEquals("Pizza",
				resultado.findElement(By.id("descComida")).findElement(By.tagName("span")).getText());
		Assert.assertEquals("mestrado",
				resultado.findElement(By.id("descEscolaridade")).findElement(By.tagName("span")).getText());
		Assert.assertEquals("Natacao",
				resultado.findElement(By.id("descEsportes")).findElement(By.tagName("span")).getText());
		Assert.assertEquals("",
				resultado.findElement(By.id("descSugestoes")).findElement(By.tagName("span")).getText());

	}

	@Test
	public void testeDeveValidarNomeObrigatorio() {

		driver.findElement(By.id("elementosForm:cadastrar")).click();

		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());

	}

	@Test
	public void testeDeveValidarSobrenomeObrigatorio() {

		driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome qualquer");
		driver.findElement(By.id("elementosForm:cadastrar")).click();

		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());

	}

	@Test
	public void testeDeveValidarSexoObrigatorio() {

		driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome qualquer");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Nome qualquer");
		driver.findElement(By.id("elementosForm:cadastrar")).click();

		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());

	}

	@Test
	public void testeDeveValidarComidaVegetariana() {

		driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome qualquer");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Nome qualquer");
		driver.findElement(By.id("elementosForm:sexo:1")).click();

		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();

		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());

	}

	@Test
	public void testeDeveValidarEsportistaIndeciso() {

		driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome qualquer");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Nome qualquer");
		driver.findElement(By.id("elementosForm:sexo:1")).click();

		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();

		Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
		combo.selectByVisibleText("Karate");
		combo.selectByVisibleText("O que eh esporte?");

		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());

	}
}
