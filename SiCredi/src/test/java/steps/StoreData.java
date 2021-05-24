package steps;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.BootStrap4Page;
import pages.DataPage;

public class StoreData {

	String url;
	WebDriver driver;
	BootStrap4Page bootstrap4page; // objeto bootstrap herdando a classe Bootstrap
	DataPage datapage;
	
	

	@Before
	public void start() {
		url = "https://www.grocerycrud.com/demo/bootstrap_theme";

		System.setProperty("webdriver.chrome.driver", "Chromedriver88.04/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);

		bootstrap4page = new BootStrap4Page(driver); // instanciar a classe bootstap
		datapage = new DataPage(driver);

	}

	@After
	public void finalizar() {

		//driver.quit();
	}
	
	@Given("^acess database Url$")
	public void acess_database_Url() throws InterruptedException  {
	    
		driver.get(url);
		Thread.sleep(2000);
	    
	}

	@Given("^Select Bootstrap V4 Theme$")
	public void select_Bootstrap_V_Theme()  {
	    
		bootstrap4page.selectBootStrap4();
	    
	}

	@Given("^add costumer$")
	public void add_costumer()  {
	    
		bootstrap4page.addcostumer();
	}

	@When("^enter with data user informations$")
	public void enter_with_data_user_informations() throws InterruptedException  {
	    
		 datapage.addcostumerdata();
	}

	@When("^save$")
	public void save() throws InterruptedException  {
	    
		datapage.saveclick();
		
		Thread.sleep(4000);
	}
	
	
	@Then("^check data stored sucessfully$")
	public void check_data_stored_sucessfully()  {
	    
		WebElement example = driver.findElement(By.xpath("//a[@class='go-to-edit-form']"));
		String val = example.getAttribute("innerText");
		assertEquals("Edit Customer", val);
		
		}

	
	@When("^click save and go back to list$")
	public void click_save_and_go_back_to_list() throws InterruptedException  {
	    
	     datapage.backlist();
	     
	}

	@When("^search for Teste Sicred$")
	public void search_for_Teste_Sicred() throws InterruptedException  {
	    
		bootstrap4page.searchname();
	}

	@When("^Click in actions$")
	public void click_in_actions()  {
	    
	    
	}

	@When("^Delete data$")
	public void delete_data()  {
	    
		//bootstrap4page.deletedata();
	}

	@Then("^user confirm if delete item$")
	public void user_confirm_if_delete_item()  {
	    
	    
	}
	
	
}
