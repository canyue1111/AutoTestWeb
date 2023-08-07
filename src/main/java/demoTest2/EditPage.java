package demoTest2;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class EditPage {

	public void changeEdit(WebDriver driver, String str) {
	    for (int i = 2; i < 24; i++) {
            String path = "//table[1]/tbody/tr[" + i + "]/td[2]/input";
	        if (i != 15 && i != 17 && i != 19) {
	            WebElement inputField = driver.findElement(By.xpath(path));
	            inputField.clear();
	            inputField.sendKeys(i+" "+str);
	        }
	    }
	}
	
	public void clearNames(WebDriver driver) {
		for (int i = 2; i < 5; i++) {
			 WebElement inputField = driver.findElement(By.xpath("//table[1]/tbody/tr[" + i + "]/td[2]/input"));
	         inputField.clear();}
	}

	
	public void clearOther(WebDriver driver, String str) {
		for(int i = 2; i< 5; i++) {
			String path =  "//table[1]/tbody/tr[" + i + "]/td[2]/input";
			WebElement inputField = driver.findElement(By.xpath(path));
			inputField.sendKeys(i+" "+str);
			}
		for (int i = 5; i < 24; i++) {
			if (i != 15 && i != 17 && i != 19){
			 WebElement inputField = driver.findElement(By.xpath("//table[1]/tbody/tr[" + i + "]/td[2]/input"));
	         inputField.clear();}
		}
	}
	
	
	public void clearAll(WebDriver driver) {
		for (int i = 2; i < 24; i++) {
			if (i != 15 && i != 17 && i != 19){
			 WebElement inputField = driver.findElement(By.xpath("//table[1]/tbody/tr[" + i + "]/td[2]/input"));
	         inputField.clear();}
		}
	}
	
	public String ErrorMessage(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"submit_button\"]")).click();
		List<WebElement> textElements = driver.findElements(By.xpath("//body/p"));
		List<String> textContentList = new ArrayList<>();

		for (WebElement textElement : textElements) {
		    textContentList.add(textElement.getText());
		}
		
		return String.join(",",textContentList);
	}
	
	public void clearForm(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"reset_button\"]")).click();
	}
	
	public void SaveChangesButton(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"submit_button\"]")).click();
	}
	
	public boolean checkClearRecovery(WebDriver driver) {
		List<String> stringList = new ArrayList<>();
		List<String> stringList2 = new ArrayList<>();
		for (int i = 1; i < 24; i++) {
		    if (i != 1 &&i != 15 && i != 17 && i != 19) {
		        String path = "//table[1]/tbody/tr[" + i + "]/td[2]/input";
		        WebElement inputField = driver.findElement(By.xpath(path));
		        stringList.add(inputField.getAttribute("value"));}}
		//System.out.println(stringList);
		clearAll(driver);
		clearForm(driver);
		for (int i = 1; i < 24; i++) {
		    if (i != 1 &&i != 15 && i != 17 && i != 19) {
		        String path = "//table[1]/tbody/tr[" + i + "]/td[2]/input";
		        WebElement inputField = driver.findElement(By.xpath(path));
		        stringList2.add(inputField.getAttribute("value"));}}
		//System.out.println(stringList2);
		return stringList.equals(stringList2);
	}
	
	public boolean checkFormUpdated(WebDriver driver, String str) {
		boolean equal = true;		
		for (int i = 2; i < 24; i++) {
			String path = "//table[1]/tbody/tr[" + i + "]/td[2]/input";
		    if (i != 15 && i != 17 && i != 19) {

		        WebElement inputField = driver.findElement(By.xpath(path));
		        String value = inputField.getAttribute("value").toString();
		        if (!value.equals(str)) {
		        	System.out.println("The poistion " + i + "is " + value);
		            equal = false;
		        }
		    }
		}
		return equal;		
	}

	public void ReturnButtonEditPage(WebDriver driver)
	{
		driver.findElement( By.xpath("//tr[contains(.,'Return (Cancel)')]")).click();
	}
}
