package demoTest2;


import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AllEntries {
	//print all data
	public void PrintTable(WebDriver driver) {
		List<WebElement> pri = driver.findElements(By.xpath("//table//tr"));	
		//print all table include title
		for(WebElement ele:pri){
				String tem = ele.getText();
				System.out.println(tem);}
	}
	
	//row of data
	public int SizeNumber(WebDriver driver) {
		List<WebElement> pri = driver.findElements(By.xpath("//table//tr"));	
		return pri.size()-1;
	}
		
	//return the list of names showing on table based on content search
	public List<String> getNameList(WebDriver driver,String str) {
		By xpath = By.xpath("//tr[./td[contains(text(),'"+str+"')]]");
		WebElement trElement = driver.findElement(xpath);
		String tdText = trElement.findElement(By.xpath("./td[2]")).getText();
		List<String> elements = Arrays.asList(tdText.split("\\r?\\n"));
		return elements;
    }

	//highest number of value, looks like size number
    public int getHighestValue(WebDriver driver) {
        int highestValue = Integer.MIN_VALUE;

        // Find all hidden input elements with the specific XPath
        java.util.List<WebElement> elements = driver.findElements(By.xpath("//input[@type='hidden' and @id='address_id']"));

        // Iterate over the elements and compare their values
        for (WebElement element : elements) {
            String value = element.getAttribute("value");
            int intValue = Integer.parseInt(value);
            if (intValue > highestValue) {
                highestValue = intValue;
            }
        }

        return highestValue;
    }
    
	//click view button
	public void ViewButton(WebDriver driver,String str) {
		String x ="//tr[contains(.,'" + str + "')]//input[@value='View Details']";

		driver.navigate().refresh();
		driver.findElement(By.xpath(x)).submit();

	}


	//click edit button
	public void EditButton(WebDriver driver,String str) {
		String x ="//tr[contains(.,'" + str + "')]//input[@value='Edit Details']";

		driver.navigate().refresh();
		driver.findElement(By.xpath(x)).submit();

		//driver.findElement(By.xpath("//table//tr[contains(.,'" + str + "')]//td[4]//form[2]")).click();
		//driver.findElement(By.xpath("//tr[contains(.,'" + str + "')]/descendant::input[@value='Edit Details']")).submit();
	}
	
	//click back button
	public void BackButton(WebDriver driver) {
		driver.findElement(By.xpath("//tr[contains(.,'Return to Menu')]")).click();
	}
	
	public boolean checkAllInfo(WebDriver driver, String str) {
	    WebElement tableBody = driver.findElement(By.xpath("//table/tbody"));
	    String tableText = tableBody.getText();
	    return tableText.contains(str);
	}

}
