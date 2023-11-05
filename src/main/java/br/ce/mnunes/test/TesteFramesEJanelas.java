package br.ce.mnunes.test;
import static br.ce.mnunes.core.DriverFactory.getDriver;
import static br.ce.mnunes.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.ce.mnunes.core.DSL;

public class TesteFramesEJanelas {

	private DSL dsl;

	@Before
	public void inicializa() {
		
		getDriver().get(System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
		dsl = new DSL();
	}

	@After
	public void finaliza() {
		killDriver();
	}

	@Test
	public void testeDeveInteragirComFrames() {

		/*
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();

		Alert alert = driver.switchTo().alert();

		String text = alert.getText();

		Assert.assertEquals("Frame OK!", text);

		alert.accept();

		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(text);
		*/
		
		dsl.entrarFrame("frame1");
		dsl.clicarBotao("frameButton");
		
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);
		
		dsl.sairFrame();
		dsl.escrever("elementosForm:nome", msg);
	}
	
	@Test
	public void deveInteragirComFrameEscondido() {
		WebElement frame = getDriver().findElement(By.id("frame2"));
		dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		dsl.entrarFrame("frame2");		
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);
	}

	@Test
	public void testeDeveInteragirComJanelas() {

		/*
		String windowHandle = driver.getWindowHandle();
		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.close();

		driver.switchTo().window(windowHandle);

		driver.findElement(By.tagName("textarea")).sendKeys("e agora?");
		*/
		
		dsl.clicarBotao("buttonPopUpEasy");
		dsl.trocarJanela("Popup");
		dsl.escrever(By.tagName("textarea"), "Deu certo?");
		getDriver().close();
		dsl.trocarJanela("");
		dsl.escrever(By.tagName("textarea"), "e agora?");
	}

	@Test
	public void testeDeveInteragirComJanelasSemTitulo() {

		/*
		String windowHandle = driver.getWindowHandle();
		driver.findElement(By.id("buttonPopUpHard")).click();
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getWindowHandles());
		driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.switchTo().window(windowHandle);

		driver.findElement(By.tagName("textarea")).sendKeys("e agora?");
		*/
		
		dsl.clicarBotao("buttonPopUpHard");
		System.out.println(getDriver().getWindowHandle());
		System.out.println(getDriver().getWindowHandles());
		dsl.trocarJanela((String) getDriver().getWindowHandles().toArray()[1]);
		dsl.escrever(By.tagName("textarea"), "Deu certo?");
		dsl.trocarJanela((String) getDriver().getWindowHandles().toArray()[0]);
		dsl.escrever(By.tagName("textarea"), "e agora?");
		
	}
}
