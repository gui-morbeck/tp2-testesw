package ui;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MusicPlayerTest {

	public static String browser = "Chrome";
	public static WebDriver driver;

	@BeforeClass
	public static void setUpTest() {
		if (browser.equals("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		driver.get("https://0b8c-45-71-230-248.sa.ngrok.io"); 
		
		new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/main/div/div/section[1]/div/footer/button")));
		
		String title = driver.getTitle();
		System.out.println(title);
		
		if (title.startsWith("ERR_NGROK_6024")) {
		driver.findElement(By.xpath("/html/body/div/div/main/div/div/section[1]/div/footer/button")).click();
		}
		driver.manage().window().maximize();
		
		
	}

	@AfterClass
	public static void tearDownTest() {
		driver.close();
	}
	
	
	@Test
	public void signUpTest() {

		new WebDriverWait(driver, Duration.ofSeconds(2));
		
		driver.get("https://0b8c-45-71-230-248.sa.ngrok.io/authentication/signup/");
		
		new WebDriverWait(driver, Duration.ofSeconds(2));
		
		driver.findElement(By.name("username")).sendKeys("UserTest7");
		driver.findElement(By.name("password1")).sendKeys("trabalho22022");
		driver.findElement(By.name("password2")).sendKeys("trabalho22022");

		driver.findElement(By.xpath("/html/body/main/form/button")).click();

		String urlLogin = driver.getCurrentUrl();

		assertEquals("https://0b8c-45-71-230-248.sa.ngrok.io/authentication/login/", urlLogin);

	}
	
	
	@Test
	public void loginTest() {

		new WebDriverWait(driver, Duration.ofSeconds(2));
		
		driver.get("https://0b8c-45-71-230-248.sa.ngrok.io/authentication/login/");

		new WebDriverWait(driver, Duration.ofSeconds(2));
		
		driver.findElement(By.name("username")).sendKeys("UserTest7");
		new WebDriverWait(driver, Duration.ofSeconds(2));
		driver.findElement(By.name("password")).sendKeys("trabalho22022");
		new WebDriverWait(driver, Duration.ofSeconds(2));
		driver.findElement(By.xpath("/html/body/main/form/button")).click();
		
		new WebDriverWait(driver, Duration.ofSeconds(2));

		String userHeader = driver.findElement(By.xpath("/html/body/nav/div/div[2]/h5")).getText();

		assertEquals("Hi, UserTest7", userHeader);
	}
	
	
	
	@Test
	public void loginWrongPasswordTest() {

		driver.get("https://0b8c-45-71-230-248.sa.ngrok.io/authentication/login/");

		driver.findElement(By.name("username")).sendKeys("UserTest");
		driver.findElement(By.name("password")).sendKeys("wrongpassword");
		driver.findElement(By.xpath("/html/body/main/form/button")).click();
		
		String messageError = driver.findElement(By.xpath("/html/body/main/form/div[1]/ul/li")).getText();

		assertEquals("This user does not exist!", messageError);

	}
	
	
	
	@Test
	public void loginWrongUsernameTest() {

		new WebDriverWait(driver, Duration.ofSeconds(2));
		
		driver.get("https://0b8c-45-71-230-248.sa.ngrok.io/authentication/login/");

		new WebDriverWait(driver, Duration.ofSeconds(2));
		
		driver.findElement(By.name("username")).sendKeys("UserTest");
		driver.findElement(By.name("password")).sendKeys("wrongpassword");
		driver.findElement(By.xpath("/html/body/main/form/button")).click();

		new WebDriverWait(driver, Duration.ofSeconds(2));
		
		String messageError = driver.findElement(By.xpath("/html/body/main/form/div[1]/ul/li")).getText();

		System.out.println(messageError);
		
		new WebDriverWait(driver, Duration.ofSeconds(2));
		
		assertEquals("This user does not exist!", messageError);

	}
	
	
	@Test
	public void searchAndFavoriteMusic() {

		driver.get("https://0b8c-45-71-230-248.sa.ngrok.io/authentication/login/");

		driver.findElement(By.name("username")).sendKeys("UserTest2");
		driver.findElement(By.name("password")).sendKeys("trabalho22022");
		driver.findElement(By.xpath("/html/body/main/form/button")).click();
		
		new WebDriverWait(driver, Duration.ofSeconds(2));
		
		driver.findElement(By.name("q")).sendKeys("A SKY FULL");
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/form/div/div/span/button")).click();
		
		new WebDriverWait(driver, Duration.ofSeconds(2));
		
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/a[2]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(2));
		driver.findElement(By.name("add-fav")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10));
		
		String result = driver.findElement(By.xpath("/html/body/ul/div/strong")).getText();
		
		assertEquals("Success!", result);
	}
	
	@Test
	public void createPlaylist() {

		driver.get("https://0b8c-45-71-230-248.sa.ngrok.io/authentication/login/");

		driver.findElement(By.name("username")).sendKeys("UserTest7");
		driver.findElement(By.name("password")).sendKeys("trabalho22022");
		driver.findElement(By.xpath("/html/body/main/form/button")).click();

		
		driver.get("https://0b8c-45-71-230-248.sa.ngrok.io/12");
		
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/form[1]/button")).click();
		
		new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div/div[2]/form[1]/div/div/div/div[2]/div/input")));
		
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/form[1]/div/div/div/div[2]/div/input")).sendKeys("PlaylistTest");
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/form[1]/div/div/div/div[3]/button[2]")).click();
		
		String result = driver.findElement(By.xpath("/html/body/ul/div/strong")).getText();
		
		assertEquals("Success!", result);
		
	}
	
}
