package br.ce.mnunes.core;
import static br.ce.mnunes.core.DriverFactory.getDriver;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	
	/*************** TextField e TextArea ***************/
	
	public void escrever(By by, String texto) {
		getDriver().findElement(by).clear();
		getDriver().findElement(by).sendKeys(texto);
	}
	
	public void escrever(String id_campo, String texto) {
		escrever(By.id(id_campo), texto);
	}
	
	public String obterValorCampo(String id_campo) {
		
		return getDriver().findElement(By.id(id_campo)).getAttribute("value");
	}
	
	/*************** Radio e Check ***************/
	
	public void clicarRadio(String id) {
		getDriver().findElement(By.id(id)).click();
	}
	
	public Boolean isRadioMarcado(String id) {
		return getDriver().findElement(By.id(id)).isSelected();
	}
	
	public void clicarCheck(String id) {
		getDriver().findElement(By.id(id)).click();
	}
	
	public boolean isCheckMarcado(String id) {
		return getDriver().findElement(By.id(id)).isSelected();
	}
	
	/*************** Combo ***************/
	
	public void selecionarCombo(String id,String valor) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);
	}
	
	public void deselecionarCombo(String id,String valor) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		combo.deselectByVisibleText(valor);
	}
	
	public String obterValorCombo(String id) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public List<String> obterValoresCombo(String id) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> valores = new ArrayList<String>();
		for(WebElement opcao: allSelectedOptions) {
			valores.add(opcao.getText());
		}
		return valores;
	}
	
	public int obterQuantidadeOpcoesCombo(String id) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}
	
	public boolean verificarOpcaoCombo(String id, String opcao) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		for(WebElement option: options) {
			if(option.getText().equals(opcao)) {
				return true;
			}
		}
		return false;
	}
	
	/*************** Botao ***************/
	
	public void clicarBotao(String id_campo) {
		getDriver().findElement(By.id(id_campo)).click();
	}
	
	public String obterValueElemento(String id) {
		return getDriver().findElement(By.id(id)).getAttribute("value");
	}
	
	/*************** Link ***************/
	public void clicarLink(String link) {
		getDriver().findElement(By.linkText(link)).click();
	}
	
	/*************** Textos ***************/
	public String obterTexto(By by) {
		return getDriver().findElement(by).getText();
	}
	public String obterTexto(String id) {
		return obterTexto(By.id(id));
	}
	
	/*************** Alerts ***************/
	
	public String alertaObterTexto() {
		Alert alert = getDriver().switchTo().alert();
		return alert.getText();
	}
	
	public String alertaObterTextoEAceita() {
		Alert alert = getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.accept();
		return valor;
	}
	
	public String alertaObterTextoENega() {
		Alert alert = getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.dismiss();
		return valor;
	}
	
	public void alertaEscrever(String valor) {
		Alert alert = getDriver().switchTo().alert();
		alert.sendKeys(valor);
		alert.accept();
	}
	
	/*************** Alerts ***************/
	
	public void entrarFrame(String id) {
		getDriver().switchTo().frame(id);
	}
	public void sairFrame() {
		getDriver().switchTo().defaultContent();
	}
	
	public void trocarJanela(String id) {
		getDriver().switchTo().window(id);
	}
	
	/*************** JavaScript ***************/
	
	public Object executarJS(String cmd, Object... param) {
		
		JavascriptExecutor js = (JavascriptExecutor) getDriver();	
		return js.executeScript(cmd, param);
	}
}
