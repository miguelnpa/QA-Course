import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {
	@Test
	public void testeCadastro() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get(System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Wagner");
		
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Costa");
		
		driver.findElement(By.id("elementosForm:sexo:0")).click();;
		
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();;
		
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		
		Select combo = new Select(element);
		
		combo.selectByValue("mestrado");
		
		WebElement selection = driver.findElement(By.id("elementosForm:esportes"));
		
		Select combo2 = new Select(selection);
		
		combo2.selectByValue("natacao");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		WebElement resultado = driver.findElement(By.id("resultado"));
		
		Assert.assertEquals("Wagner", resultado.findElement(By.id("descNome")).findElement(By.tagName("span")).getText());
		Assert.assertEquals("Costa", resultado.findElement(By.id("descSobrenome")).findElement(By.tagName("span")).getText());
		Assert.assertEquals("Masculino", resultado.findElement(By.id("descSexo")).findElement(By.tagName("span")).getText());
		Assert.assertEquals("Pizza", resultado.findElement(By.id("descComida")).findElement(By.tagName("span")).getText());
		Assert.assertEquals("mestrado", resultado.findElement(By.id("descEscolaridade")).findElement(By.tagName("span")).getText());
		Assert.assertEquals("Natacao", resultado.findElement(By.id("descEsportes")).findElement(By.tagName("span")).getText());
		Assert.assertEquals("", resultado.findElement(By.id("descSugestoes")).findElement(By.tagName("span")).getText());
		
		driver.quit();
	
	}
}
