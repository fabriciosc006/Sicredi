package pages;






import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;


public class BootStrap4Page extends PageObject {
	
	
	//atributos
	
			@FindBy(id = "switch-version-select")
			private WebElement cbx_bootstrap;

			
			@FindBy(css="option[value='bootstrap_theme_v4']")
			private WebElement bootstrap4;
			
			@FindBy(css="a[class='btn btn-default btn-outline-dark']")
			private WebElement btn_addcostumer;
			
			@FindBy(css="input[name='customerName']")
			private WebElement txb_searchname;
			
			
			@FindBy(css="button[type='button']")
			private WebElement btn_more;
			
			
						
			
			//construtor
			
			public BootStrap4Page (WebDriver driver) {
				super(driver);
				
			}
			
			//metodos
			
			public void selectBootStrap4()  {
				
				cbx_bootstrap.click();
				bootstrap4.click();
								
			}
		
			public void addcostumer()  {
				
				btn_addcostumer.click();
												
			}

			public void searchname() throws InterruptedException {
				
				driver.findElement(By.cssSelector("input[placeholder='Search Name']")).click();
				Thread.sleep(5000);
				driver.findElement(By.cssSelector("input[placeholder='Search Name']")).sendKeys("Teste Sicredi");
				Thread.sleep(1000);
				driver.findElement(By.cssSelector("input[placeholder='Search Name']")).sendKeys(Keys.ENTER);
			}
			
	        
			public void deletedata() {
				
				
				List<WebElement> props = driver.findElements(By.xpath("//input[@class='select-row']"));				
				   for (WebElement prop : props)
			         if (!prop.isSelected()) {
			             prop.click();
			             break;
				
									
				}
				
								
				//driver.findElement(By.cssSelector("input[class='select-row']")).click();
				
				driver.findElement(By.xpath(".//a[@title='Delete']")).click();
				
				driver.findElement(By.xpath("//button[contains(@class,'delete-multiple')]")).click();
			}

}
