package demoblazetest;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class ShopNavigationHelper {

	public static String SHOP_URL = "https://www.demoblaze.com/index.html";
	
	public static String PHONE_LINK = "Samsung galaxy s6";
	public static String LAPTOP_LINK = "Sony vaio i5";
	public static String MONITOR_LINK = "Apple monitor 24";
	
	public static String LAPTOP_SONY_VAIO_I5_AMOUNT = "790";
	
	public static String CART_LINK = "Cart";
	public static String HOME_LINK = "Home (current)";
	
	public static String PLACE_ORDER_BUTTON = "Place Order";
	public static String PURCHASE_BUTTON = "Purchase";
	public static String OK_BUTTON = "OK";
	
	public static String ADD_TO_CART_ALERT = "Product added";
	
	public void clickLink(WebDriver driver, String link) {
		// wait link
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText(link)));
		// locate link element
        WebElement element = driver.findElement(By.linkText(link));
        // click element
        element.click();
	}
	
	public void clickButton(WebDriver driver, String button) {
		// wait button
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'" + button + "')]")));
		
		// locate button element
        WebElement element = driver.findElement(By.xpath("//button[contains(text(),'" + button + "')]"));
        // click element
        element.click();
	}
	
	public void deleteFromCart(WebDriver driver, String item) {
		// wait delete link
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr/td[contains(text(),'" + item + "')]/..//a")));
		// locate link element
        WebElement element = driver.findElement(By.xpath("//tr/td[contains(text(),'" + item + "')]/..//a"));
        // click element
        element.click();
	}
	
	public void assertProductIsInCart(WebDriver driver, String item) {
		// wait delete link
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr/td[contains(text(),'" + item + "')]/..//a")));
		// locate link element
        driver.findElement(By.xpath("//tr/td[contains(text(),'" + item + "')]/..//a"));
	}
	
	public void assertProductIsNotInCart(WebDriver driver, String item) {
		// wait delete link
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//tr/td[contains(text(),'" + item + "')]/..//a"), 0));
		// locate link element
        assertEquals(0, driver.findElements(By.xpath("//tr/td[contains(text(),'" + item + "')]/..//a")).size()); 
        
	}
	
	public boolean assertProductCategoryIsVisible(WebDriver driver, String category) {
		
		Boolean result = false;
		switch (category) {
			case "Phones":
				result = assertProductIsVisible(driver, PHONE_LINK);
				break;
			case "Laptops":
				result = assertProductIsVisible(driver, LAPTOP_LINK);
				break;
			case "Monitors":
				result = assertProductIsVisible(driver, MONITOR_LINK);
				break;
			default:
				break;
			}
		
		return result;
	}
	
	public boolean assertProductIsVisible(WebDriver driver, String link) {
		// finds links to product
        if(driver.findElements(By.linkText(link)).isEmpty()) {
        	return false;
        } else {
        	return true;
        }
	}
	
}
