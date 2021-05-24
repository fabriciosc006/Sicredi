package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DataPage extends PageObject{

	//atributos
	
		
	
	@FindBy(id="field-customerName")
	private WebElement name;
	
	@FindBy(id="field-contactLastName")
	private WebElement lastname;
	
	@FindBy(id="field-contactFirstName")
	private WebElement firstname;
	
	@FindBy(id="field-phone")
	private WebElement phone;
	
	@FindBy(id="field-addressLine1")
	private WebElement addressline1;
	
	@FindBy(id="field-addressLine2")
	private WebElement addressline2;
	
	@FindBy(id="field-city")
	private WebElement city;
	
	@FindBy(id="field-state")
	private WebElement state;
	
	@FindBy(id="field-postalCode")
	private WebElement postalcode;
	
	@FindBy(id="field-country")
	private WebElement country;
	
	@FindBy(id="field_salesRepEmployeeNumber_chosen")
	private WebElement employee;
	
	@FindBy(id="field-creditLimit")
	private WebElement creditlimit;
	
	@FindBy(id="form-button-save")
	private WebElement btn_save;
	
	@FindBy(id="save-and-go-back-button")
	private WebElement btn_backlist;
	
				
	
	//construtor
	
	public DataPage (WebDriver driver) {
		super(driver);
		
	}
	
	//metodos
	
	
	public void addcostumerdata() throws InterruptedException  {
		
		name.clear();
		name.sendKeys("Teste Sicredi");
		lastname.sendKeys("Teste");
		firstname.sendKeys("fabricio");
		phone.sendKeys("51 9999-9999");
		addressline1.sendKeys("Av Assis Brasil, 3970");
		addressline2.sendKeys("Torre D");
		city.sendKeys("Porto Alegre");
		state.sendKeys("RS");
		postalcode.sendKeys("91000-000");
		country.sendKeys("Brasil");
		
		employee.click();
		Thread.sleep(2000);
		
		List<WebElement> listOfElements = driver.findElements(By.xpath(".//*[@class='chosen-results']/li"));
		
		for (WebElement webElement : listOfElements) {
			 if(webElement.getText().trim().equals("Fixter")) {
				 webElement.click();
				 break;
			 }
			
		}
		
		Thread.sleep(500);
		
		creditlimit.sendKeys("200");
		
														
	}
	
	public void saveclick() {
		btn_save.click();
		
	}
	
	public void backlist() throws InterruptedException {
		
		Thread.sleep(4000);
		btn_backlist.click();
		
	}

	
}
