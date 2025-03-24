package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(id="username")
	private WebElement usernameField;
	
	@FindBy(id="inputPassword")
	private WebElement passwordField;
	
	@FindBy(xpath="//button[text()='Sign In']")
	private WebElement signInBtn;
	
	@FindBy(linkText="Forgot password?")
	private WebElement forgotPswdLink;
	
	@FindBy(linkText="Create Account")
	private WebElement createAccBtn;
	

	public WebElement getUsernameField() {
		return usernameField;
	}

	public WebElement getPasswordField() {
		return passwordField;
	}

	public WebElement getSignInBtn() {
		return signInBtn;
	}

	public WebElement getForgotPswdLink() {
		return forgotPswdLink;
	}

	public WebElement getCreateAccBtn() {
		return createAccBtn;
	}
	public void login(String uname,String password)
	{
		usernameField.sendKeys(uname);
		passwordField.sendKeys(password);
		signInBtn.click();
	}
	
	
}
