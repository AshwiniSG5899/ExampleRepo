package GenericWebDriverUtility;

import java.time.Duration;
import java.util.Set;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	public void waitForElementPresent(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void waitForElementToBeClickable(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public void switchToWindow(WebDriver driver,String partialUrl)
	{
		Set<String> windowIds = driver.getWindowHandles();
		for(String Window:windowIds)
		{
			driver.switchTo().window(Window);
		     String actUrl = driver.getCurrentUrl();
		     if(actUrl.contains(partialUrl))
		     {
		    	 break;
		     }
		}
	}
	
}
