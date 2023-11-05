package br.ce.mnunes.test;
import static br.ce.mnunes.core.DriverFactory.getDriver;
import static br.ce.mnunes.core.DriverFactory.killDriver;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.ce.mnunes.core.DSL;

public class TesteCampoTreinamento {

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
	public void testeTextField() {
		// Assert.assertEquals("Google", driver.getTitle());
		
		// driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");

		dsl.escrever("elementosForm:nome", "Teste de escrita");
		Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));

	}
	
	@Test
	public void testTextFieldDuplo() {
		dsl.escrever("elementosForm:nome", "Wagner");
		Assert.assertEquals("Wagner", dsl.obterValorCampo("elementosForm:nome"));
		dsl.escrever("elementosForm:nome", "Aquino");
		Assert.assertEquals("Aquino", dsl.obterValorCampo("elementosForm:nome"));
	}

	@Test
	public void testeDeveInteragirComTextArea() {
		//driver.findElement(By.id("elementosForm:sugestoes"))
		//		.sendKeys("Teste do campo\n Teste do campo, segunda linha \n Teste do campo, terceira linha");
		//Assert.assertEquals("Teste do campo\n Teste do campo, segunda linha \n Teste do campo, terceira linha",
		//		driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
		
		dsl.escrever("elementosForm:sugestoes", "Teste do campo\n Teste do campo, segunda linha \n Teste do campo, terceira linha");
		Assert.assertEquals("Teste do campo\n Teste do campo, segunda linha \n Teste do campo, terceira linha",
				dsl.obterValorCampo("elementosForm:sugestoes"));

	}

	@Test
	public void testeDeveInteragirComRadioButton() {

		//driver.findElement(By.id("elementosForm:sexo:0")).click();
		//Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		// Assert.assertEquals(driver.findElement(By.id("elementosForm:sexo:0")).isSelected(),
		// true);
		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));

	}

	@Test
	public void testeDeveInteragirComCheckBox() {

		//driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		//Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
		
		//Assert.assertEquals(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected(), true);
		
		dsl.clicarCheck("elementosForm:comidaFavorita:2");
		Assert.assertTrue(getDriver().findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());

	}

	@Test
	public void testeDeveInteragirComCombo() {

		//WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));

		//Select combo = new Select(element);

		// combo.selectByIndex(2);
		// combo.selectByValue("superior");
		//combo.selectByVisibleText("2o grau completo");
		

		//Assert.assertEquals("2o grau completo", combo.getFirstSelectedOption().getText());
		
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
		Assert.assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));

	}

	@Test
	public void testeDeveVerificarValoresCombo() {

		//WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));

		//Select combo = new Select(element);

		//List<WebElement> options = combo.getOptions();

		//Assert.assertEquals(8, options.size());

		//boolean encontrou = false;

		//for (WebElement option : options) {
		//	if (option.getText().equals("Mestrado")) {
		//		encontrou = true;
		//		break;
		//	}
		//}

		//Assert.assertTrue(encontrou);

		
		
		Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
	}

	@Test
	public void testeDeveVerificarValoresComboMultiplo() {
		
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		
		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());
		
		dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));

		/*
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));

		Select combo = new Select(element);
		//combo.selectByVisibleText("Natacao");
		//combo.selectByVisibleText("Corrida");
		//combo.selectByVisibleText("O que eh esporte?");

		List<WebElement> options = combo.getAllSelectedOptions();
		Assert.assertEquals(3, options.size());

		combo.deselectByVisibleText("Corrida");
		options = combo.getAllSelectedOptions();
		Assert.assertEquals(2, options.size());

        */
	}

	@Test
	public void testDeveInteragirComBotoes() {
		

		//WebElement botao = driver.findElement(By.id("buttonSimple"));
		//botao.click();

		//Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
		
		dsl.clicarBotao("buttonSimple");
		Assert.assertEquals("Obrigado!", dsl.obterValorCampo("buttonSimple"));
		

	}

	@Test
	public void testDeveInteragirLinks() {

		dsl.clicarLink("Voltar");
		//driver.findElement(By.linkText("Voltar")).click();

		//Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));

	}
	
	@Test
	public void deveBuscarTextosNaPagina() {
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
	}

}
