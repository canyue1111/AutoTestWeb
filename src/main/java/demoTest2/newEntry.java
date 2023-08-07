package demoTest2;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;


public class newEntry {

	//check Title Address Book - All Entries
	public boolean checkTitle(WebDriver driver, String title) {
		 return driver.getTitle().equals(title);
	}
	
	//Fill in the blank with numbers and long char
	public void FillInAll(WebDriver driver, String str) {
		for(int i = 2; i< 24; i++) {
			if (i != 15 && i != 17 && i != 19){
			String path =  "//table[1]/tbody/tr[" + i + "]/td[2]/input";
			WebElement inputField = driver.findElement(By.xpath(path));
			inputField.sendKeys(i+" "+str);}
			}
		ScrollChoose(driver);
	}
	
	//Fill in the blank according to CSV file
	public void FillInSepate(WebDriver driver, String[] str) {
		int temp = 0;
		for(int i = 2; i< 24; i++) {
			if (i != 15 && i != 17 && i != 19){
			String path =  "//table[1]/tbody/tr[" + i + "]/td[2]/input";
			WebElement inputField = driver.findElement(By.xpath(path));
			inputField.sendKeys(str[temp]);
			temp ++; }
			}
		ScrollChoose(driver);
	}
	
	//Randomly generate the scroll choice
	public void ScrollChoose(WebDriver driver) {
		List<String> options1 = Arrays.asList("Other", "Business", "Friend", "Family");
	    String selectedOption = options1.get(ThreadLocalRandom.current().nextInt(options1.size()));
	    new Select(driver.findElement(By.id("addr_type"))).selectByVisibleText(selectedOption);

	    List<String> options2 = Arrays.asList("Home", "Home Fax", "Work", "Work Fax", "Mobile");
	    String selectedOption2 = options2.get(ThreadLocalRandom.current().nextInt(options2.size()));
	    String selectedOption3 = options2.get(ThreadLocalRandom.current().nextInt(options2.size()));
	    String selectedOption4 = options2.get(ThreadLocalRandom.current().nextInt(options2.size()));
	    //System.out.println(selectedOption + selectedOption2 + selectedOption3 + selectedOption4);

	    new Select(driver.findElement(By.id("addr_phone_1_type"))).selectByVisibleText(selectedOption2);
	    new Select(driver.findElement(By.id("addr_phone_2_type"))).selectByVisibleText(selectedOption3);
	    new Select(driver.findElement(By.id("addr_phone_3_type"))).selectByVisibleText(selectedOption4);
	}
	
	//clear form button
	public void clearForm(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"reset_button\"]")).click();
	}
	
	public boolean checkFormCleared(WebDriver driver) {
		boolean equal = true;
		for (int i = 1; i < 24; i++) {
		    if (i != 1 &&i != 15 && i != 17 && i != 19) {
		        String path = "//table[1]/tbody/tr[" + i + "]/td[2]/input";
		        WebElement inputField = driver.findElement(By.xpath(path));
		        String value = inputField.getAttribute("value");
		        if (!value.isEmpty()) {
		            equal = false;
		        }
		    }
		    else if(i == 1)
		    {
		    	if(!driver.findElement(By.xpath("//*[@id=\"addr_type\"]/option[1]")).getText().equals("Family")) {
		    		System.out.println("Not default family");
		    		equal = false;
		    	}
		    }
		    else if(i == 15 && i == 17 && i == 19) {
		    	if(!driver.findElement(By.xpath("//*[@id=\"addr_type\"]/option[1]")).getText().equals("Home")) {
		    		System.out.println("Not default Home");
		    		equal = false;
		    	}
		    }
		}
		return equal;		
	}

	//get list of maximum length
	public List<Integer> getIntList(WebDriver driver) 
	{
	    List<Integer> integerList = new ArrayList<>();
	    integerList.add(null);	    
	    for (int i = 2; i < 24; i++) {
	        if (i != 15 && i != 17 && i != 19) {
	            String path = "//table[1]/tbody/tr[" + i + "]/td[2]/input";
	            int currentMaxLength = Integer.parseInt(driver.findElement(By.xpath(path)).getAttribute("maxlength"));
	            if (i == 16) {
	                int previousLength = driver.findElement(By.xpath("//*[@id='addr_phone_1_type']")).getText().length();
	                integerList.add(previousLength + currentMaxLength);
	            } else if (i == 18) {
	                int previousLength = driver.findElement(By.xpath("//*[@id='addr_phone_2_type']")).getText().length();
	                integerList.add(previousLength + currentMaxLength);}
	            else if (i == 20) {
		                int previousLength = driver.findElement(By.xpath("//*[@id='addr_phone_3_type']")).getText().length();
		                integerList.add(previousLength + currentMaxLength);
	            } else {
	                integerList.add(currentMaxLength);
	                }
	        }
	    }
	    return integerList;
	}
	
	//click save changes button
	public void SaveChangesButton(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"submit_button\"]")).click();
	}
	
	public List<String> AllColunmNames(WebDriver driver){
		List<String> strList = new ArrayList<>();
	    for (int i = 1; i < 24; i++) {
	            strList.add(driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[1]")).getText());
	        }
	    return strList;
	}
	
	public String successSubmit(WebDriver driver) {
		String successMesage = driver.findElement(By.xpath("//body/form")).getText();
		driver.findElement(By.xpath("//*[@type=\"submit\"]")).click();
		return successMesage;
	}
	
	public void ReturnButtonNewEntry(WebDriver driver)
	{
		driver.findElement( By.xpath("//tr[contains(.,'Return to Menu')]")).click();
	}
}


