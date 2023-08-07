package demoTest2;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ViewDetails {
	
	public void ReturnButton(WebDriver driver) {
		driver.findElement(By.xpath("//body/div[2]/a")).click();
	}
	
	public List<Integer> getActualList(WebDriver driver) {
	    List<Integer> integerList = new ArrayList<>();
	    for (int i = 1; i < 21; i++) {
	        if (i !=1) {
	            integerList.add(driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[2]")).getText().length());
	        }
	        else
	        {
	        	integerList.add(null);
	        }
	    }
	    return integerList;}

	public List<String> AllColunmNames(WebDriver driver){
		List<String> strList = new ArrayList<>();
	    for (int i = 1; i < 21; i++) {
	            strList.add(driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[1]")).getText());
	        }
	    return strList;
	}
	
	
	public boolean UserInputCheck(WebDriver driver){
        int previousNumber = Integer.MIN_VALUE;
        boolean isAscending = true;
		List<String> strList = new ArrayList<>();
	    for (int i = 1; i < 21; i++) {
	            strList.add(driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[2]")).getText());
	        }
        		
        for (String element : strList) {
            Pattern pattern = Pattern.compile("^(\\d+)\\s");
            Matcher matcher = pattern.matcher(element);

            if (matcher.find()) {
                int currentNumber = Integer.parseInt(matcher.group(1));
                if (currentNumber < previousNumber) {
                    isAscending = false;
                    break;
                }
                previousNumber = currentNumber;
            }
        }
        return isAscending;
	}
	
	public boolean Database(WebDriver driver, String str) {
	    for (int i = 2; i < 21; i++) {
	    	String temp = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[2]")).getText();
	        if (i > 17) {
	    		if(!temp.equals((i+3) + " "+ str)) {
	    			System.out.println("Does not match at position " + i);
	    			System.out.println(str + " " + temp);
	    			return false;}
	    		}
	        else if(i < 15){
	    		if(!temp.equals(i + " "+ str)) {
	    			System.out.println("Does not match at position " + i);
	    			System.out.println(str + " " + temp);
	    			return false;}
	        }
	        else{
	        	if(!temp.contains(str)) {
	        		System.out.print("Does not match at position " + i);
	        		System.out.println(str + " " + temp);
	        		return false;}}
	        }
		return true;
	}
	
	
	public boolean SeprateDatabase(WebDriver driver, String[] str) {
	    for (int i = 2; i < 21; i++) {
	    	String temp = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[2]")).getText();
	        String expected = str[i - 2].trim().replaceAll("\\s+", " "); // Trim and normalize whitespace
	        String actual = temp.trim().replaceAll("\\s+", " "); // Trim and normalize whitespace
	        
	        if (!actual.contains(expected)) {
	            System.out.println("Does not match at position " + i);
	            System.out.println(expected + "|||COMPARE|||" + actual);
	            // return false; // You can choose to return false if needed
	        }
	    }
		return true;
	}
}

