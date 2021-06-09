package demoblazetest;

import io.cucumber.java.After;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {

    private final WebDriver driver = new FirefoxDriver();
    private ShopNavigationHelper navigation = new ShopNavigationHelper();
    
    @Given("User is on the demoblaze online shop page")
    public void user_visit_demoblaze() {
        driver.get(ShopNavigationHelper.SHOP_URL);
    }

    @When("User navigates through {string}")
    public void navigate_to_category(String linkText) {
    	driver.get(ShopNavigationHelper.SHOP_URL);
    	navigation.clickLink(driver, linkText);
   }

   @Then("the page shows the results of {string}")
   public void checkCategoryProducts(String category) {
       // Wait for the page to load timeout after ten seconds
       new WebDriverWait(driver,10L).until(new ExpectedCondition<Boolean>() {
           public Boolean apply(WebDriver d) {
        	   Boolean productCategoryIsVisible = false;
        	   productCategoryIsVisible = navigation.assertProductCategoryIsVisible(d, category);
               return productCategoryIsVisible;
           }
       });
   }

   @When("adds {string} to cart")
   public void addProductToCart(String product) {
	   navigation.clickLink(driver, product);
	   navigation.clickLink(driver, "Add to cart");
	   
	   // Checks and accept alert
	   WebDriverWait wait = new WebDriverWait(driver, 20);
	   wait.until(ExpectedConditions.alertIsPresent());
	   String alertText = driver.switchTo().alert().getText();
	   alertText.equals(ShopNavigationHelper.ADD_TO_CART_ALERT);
	   driver.switchTo().alert().accept();
	   driver.switchTo().defaultContent();
   }
   
   @Then("product {string} is on the cart")
   public void assertProductIsOnCart(String product) {
	   navigation.clickLink(driver, ShopNavigationHelper.CART_LINK);
	   navigation.assertProductIsInCart(driver, product);
   }
   
   @Then("product {string} can be removed from cart")
   public void removeProductFromCart(String product) {
	   navigation.clickLink(driver, ShopNavigationHelper.CART_LINK);
	   navigation.deleteFromCart(driver, product);
	   navigation.assertProductIsNotInCart(driver, product);
   }
   
   @Then("user can proceed with purchase")
   public void assertPurchaseProcedure() {
	   navigation.clickLink(driver, ShopNavigationHelper.CART_LINK);
	   navigation.clickButton(driver, ShopNavigationHelper.PLACE_ORDER_BUTTON);
	   
	   // Fullfill the form fields
	   WebElement element = driver.findElement(By.id("name"));
       element.sendKeys("Víctor Civitani");
       element = driver.findElement(By.id("country"));
       element.sendKeys("Spain");
       element = driver.findElement(By.id("city"));
       element.sendKeys("Teruel");
       element = driver.findElement(By.id("card"));
       element.sendKeys("1234");
       element = driver.findElement(By.id("month"));
       element.sendKeys("01");
       element = driver.findElement(By.id("year"));
       element.sendKeys("2021");
	   navigation.clickButton(driver, ShopNavigationHelper.PURCHASE_BUTTON);
	   
	   // capture and log purchase Id and Amount
	   WebDriverWait wait = new WebDriverWait(driver, 20);
	   wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'" + ShopNavigationHelper.OK_BUTTON + "')]")));
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(), 'Thank you for your purchase!')]")));
	   
	   String pagesource = driver.getPageSource();
	   
	   String purchaseId = pagesource.split(">Id: ")[1];
	   purchaseId = purchaseId.split("<br>Amount: ")[0];
	   System.out.println("Purchase Id: " + purchaseId);
	   
	   String amount = pagesource.split("<br>Amount: ")[1];
	   amount = amount.split("<br>Card Number:")[0];
	   System.out.println("Amount: " + amount);
	   
	   // Assert purchase amount equals expected
	   Assert.assertEquals(ShopNavigationHelper.LAPTOP_SONY_VAIO_I5_AMOUNT + " USD", amount);
	   
	   // click OK
		navigation.clickButton(driver, ShopNavigationHelper.OK_BUTTON);
   }
   
   @After()
   public void closeBrowser() {
       driver.quit();
   }
}