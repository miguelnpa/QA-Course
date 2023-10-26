import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	
	private WebDriver driver;
	
	DSL(WebDriver driver){
		this.driver = driver;
	}
	
	public void escreve(String id_campo, String texto) {
		driver.findElement(By.id(id_campo)).sendKeys(texto);
	}
	
	public String obterValorCampo(String id_campo) {
		return driver.findElement(By.id(id_campo)).getAttribute("value");
	}
	
	public void clicarRadio(String id_campo) {
		driver.findElement(By.id(id_campo)).click();
	}
	
	public Boolean isRadioMarcado(String id_campo) {
		return driver.findElement(By.id(id_campo)).isSelected();
	}
	
	public void selecionarCombo(String id,String valor) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);
	}
	
	public String obterValorCombo(String id,String valor) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);
		return combo.getFirstSelectedOption().getText();
	}
	
	public void clicarBotao(String id_campo) {
		driver.findElement(By.id(id_campo)).click();
	}
	public void clicarLink(String link) {
		driver.findElement(By.linkText(link)).click();
	}
	public String obterTexto(By by) {
		return driver.findElement(by).getText();
	}
	public String obterTexto(String id) {
		return obterTexto(By.id(id));
	}
}
