package demoTest2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder (MethodOrderer.MethodName.class)
public class SetUp{
	
	String successMesg = "The new address book entry was added successfully";
	String ErrorNameMesg ="An person's name or business name must be specified";
	String ErrorContactMesg = "At least one of the following must be entered: street/mailing address, email address, phone number or web site url";
    static int PhotoNth = 0;
	static List<Integer> maxiLength;
	List<String> newEntryColuNames;
	int rounds;
    
	WebDriver driver;//= new ChromeDriver();
	EditPage editPage;
	ViewDetails viewDetails;
	newEntry Newentry;
	AllEntries allEntries;
    
	
	public void ScreenShot(WebDriver driver) throws IOException {
		String destinationPath;
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        
		//get Photo Path
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if (stackTraceElements.length > 2) {
        	destinationPath = ".\\photo\\" +PhotoNth + stackTraceElements[2].getMethodName()+ ".png";
        }else {
		destinationPath = ".\\photo\\fsdfs.png";}
        
		PhotoNth++;
		
		File DestinationFile = new File(destinationPath);
		
		// Save the screenshot to the destination path
		FileUtils.copyFile(screenshotFile, DestinationFile);
	}
	
	
    public String[] readRow(String filePath, int rowNth) {
        String str = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 1;

            while ((line = reader.readLine()) != null) {
                if (lineNumber == rowNth) {
                    str = line;
                    break;
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str.split(",");
    }

	public void OpenBrowser(String url,WebDriver driver) {
		//driver= new ChromeDriver();
        driver.get(url);
	}
	
	//based on numbers
    public static boolean checkOrderRule(List<String> myList) {
        int previousNumber = Integer.MIN_VALUE;
        for (String element : myList) {
            String numberString = element.split("\\s+")[0];
            int currentNumber = Integer.parseInt(numberString);

            if (currentNumber < previousNumber) {
                return false; // Rule violation: smaller number appears after a larger number
            }

            previousNumber = currentNumber;
        }

        return true; // The list follows the order rule
    }
    
	
	public static List<Integer> PosOveMax(List<Integer> a, List<Integer>b) {
		List<Integer> largerPositions = new ArrayList<>();

		for (int i = 0; i < a.size(); i++) {
		    if (a.get(i) != null && b.get(i) != null && a.get(i) > b.get(i)) {
		        largerPositions.add(i);
		    }
		}
		return largerPositions;
	}
    
	@BeforeAll
    public void setUpEnvi() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\2023JuneAutoTest\\demoTest1\\target\\chromedriver.exe");
		driver= new ChromeDriver();
		editPage = new EditPage();
		Newentry = new newEntry();
		OpenBrowser("http://localhost/addressbook/NewEntry.php",driver);
		//Newentry.FillInAll(driver, "yF8mH0TpJVOk5co6DbIudS9LEBNA4siqXlZ7F1vQG2rtYjxg3hazRwnPKCvWMX");
        //maxiLength = Newentry.getIntList(driver);
	    newEntryColuNames = Newentry.AllColunmNames(driver);
		Newentry.SaveChangesButton(driver);
		//rounds = 5;//number of records in our csv file
    }
	
	@BeforeEach
	public void SetPage() throws InterruptedException{
		editPage = new EditPage();
		viewDetails = new ViewDetails();
		Newentry = new newEntry();
		allEntries = new AllEntries();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}
	
	@AfterAll
	public void Tear() throws InterruptedException{
		editPage = null;
		viewDetails = null;
		Newentry = null;
		allEntries = null;
		driver.quit();
	}
	

	// -11.check new entry shows in database
	@Test
	public void TestNewEntryUpdateDatabase() throws InterruptedException, IOException {
	    rounds = 5;
		OpenBrowser("http://localhost/addressbook/newEntry.php", driver);
	    //Newentry.FillInSepate(driver, readRow("../demoTest2/dataAug-7-2023.csv", rounds));
	    //Newentry.SaveChangesButton(driver);
		OpenBrowser("http://localhost/addressbook/allList.php", driver);
		allEntries.ViewButton(driver, readRow("../demoTest2/dataAug-7-2023.csv", rounds)[0]); 
		//viewDetails.SeprateDatabase(driver, readRow("../demoTest2/dataAug-7-2023.csv"));
	    ScreenShot(driver);
	    Assert.assertTrue("The clear form function does not work", viewDetails.SeprateDatabase(driver, readRow("../demoTest2/dataAug-7-2023.csv",rounds)));
	}
	

}

