package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.Random;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("user with username {string} with password {string} is successfully created")
    public void UserIsGivenAndLoginIsSelected(String username, String password) {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click(); 
        createNewUser(username, password);   
    }
    
    @Given("user with username {string} and password {string} is tried to be created")
    public void BadUserIsGivenAndIsNotSuccessfullyCreated(String username, String password) {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click(); 
        createNewUser(username, password);   
    }
    
    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();   
    }    
    
    @Given("command new user is selected")
    public void newUserIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();   
    }
   
   
    
    @When("too short username {string} and too bad password {string} are given")
    public void badUserNameBadPasswordareGiven(String username, String password) {
        logInWith(username, password);
    }  
    
    @When("too short username {string} and valid password {string} is given")
    public void tooShortUsername(String username, String password){
        createNewUser(username, password);
    }
    
    @When("correct username {string} and password {string} confirmations fails")
    public void correctUserNameFailedPasswordConfirmation(String username, String password){
        failedRegistered(username, password);
    }
    
    @When("correct username {string} and too short password {string} is given")
    public void correctUsernameTooShortPassword(String username, String password){
        createNewUser(username, password);
    }
    
    @Then("user is not created and error {string} is reported")
    public void UserHasTooShortPassword(String error) {
        pageHasContent(error);
    }
    
    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }
    
    
    @When("a valid username {string} and password {string} and matching password confirmation are entered")
    public void validUserNameAndPasswordAreGiven(String username, String password) {
        createNewUser(username, password);
    }
    
    @When("username {string} and password {string} are entered")
    public void loggingInWithGivenUserNameAndPassword(String username, String password) {
        logInWith(username, password);
    }
    
    @Then("user has logged in")
    public void userLoggedInWithGivenUNAndPass() {
        pageHasContent("Ohtu Application main page");
    }    
    
    
    @Then("a new user is created")
    public void newUserIsLoggedIn() {
        pageHasContent("Welcome to Ohtu Application!");
    }
    
    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }    
 
    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }  
     
    @When("nonexistent username {string} and password {string} are given")
    public void nonExistentUsernameAndPasswordGiven(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }    
    
    @When("username {string} and password {string} are given")
    public void usernameAndPasswordAreGiven(String username, String password) throws Throwable {
        logInWith(username, password);
    }
   
    
    @Then("system will respond {string}")
    public void systemWillRespond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 
    
    private void createNewUser(String username, String password) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
    
    private void failedRegistered (String username, String password) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("vaaraconffa");
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
}
