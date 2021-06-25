package com.revature;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CodingameManip {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://www.codingame.com/clashofcode/clash/report/18311843021f3157b3d845795f5e53e2a00874a");
        List<WebElement> rows = driver.findElements(By.cssSelector(".clash-info-container"));
        System.out.println(rows.size());

        try(PrintWriter writer = new PrintWriter("codingame_data.csv")) {
            StringBuilder sb = new StringBuilder();
            sb.append("id");
            sb.append(',');
            sb.append("Name");
            sb.append(',');
            sb.append("Score");
            sb.append(',');
            sb.append("Time");
            sb.append(',');
            sb.append("Language");
            sb.append('\n');

            //writer.write(sb.toString());

            for(int i = 0; i < rows.size(); ++i) {
                String thisName = rows.get(i).findElement(By.xpath("./a")).getText();
                WebElement wrapper = rows.get(i).findElement(By.xpath("./div[@class='info-item-wrapper']"));
                String thisScore = wrapper.findElement(By.xpath("./div[1]//div[@class='info-value']/span")).getText();
                String thisTime = wrapper.findElement(By.xpath("./div[2]//div[@class='info-value']/span")).getText();
                String thisLang = wrapper.findElement(By.xpath("./div[3]//div[@class='info-value']/span")).getText();

                sb.append(i+1);
                sb.append(',');
                sb.append(thisName);
                sb.append(',');
                sb.append(thisScore);
                sb.append(',');
                sb.append(thisTime);
                sb.append(',');
                sb.append(thisLang);
                sb.append('\n');

                //writer.write(sb.toString());
            }

            writer.write(sb.toString());
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
