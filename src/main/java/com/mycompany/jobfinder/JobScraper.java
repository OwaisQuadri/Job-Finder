/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jobfinder;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import java.util.LinkedList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 *
 * @author Owais
 */
public class JobScraper {

    //init variables
    private String url = "https://ca.indeed.com/";
    WebDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME);
    
    //lists: titles, locations, descriptions
    private LinkedList<String> jobTitle = new LinkedList<>();
    private LinkedList<String> jobCompany = new LinkedList<>();
    private LinkedList<String> jobDesc = new LinkedList<>();
    private LinkedList<String> jobURL = new LinkedList<>();
    //constructor
    public JobScraper() {
    }

    private void pageInfo() {
        System.out.println("Title: " + driver.getTitle());
        System.out.println("URL: " + driver.getCurrentUrl());
    }

    public void getJobInfo(String query,String where) {
        System.out.println("Going...");
        //go to new page
        String urlQuery = "";
        String whereQuery = "";
        for (String q : where.split(" ")) {
            whereQuery += q + "+";
        }
        if (whereQuery.equals("+")) {
            urlQuery = "";
        }
        for (String q : query.split(" ")) {
            urlQuery += q + "+";
        }
        if (urlQuery.equals("+")) {
            urlQuery = "jobs";
        }
        url = "https://ca.indeed.com/jobs?as_and=" + urlQuery + "&as_phr=&as_any=&as_not=&as_ttl=&as_cmp=&jt=all&st=&salary=&radius=25&l="+whereQuery+"&fromage=any&limit=10&sort=&psf=advsrch&from=advancedsearch";
        driver.get(url);
        //display page info
        pageInfo();

        //make a list of each Job's independent link
        List<WebElement> titles = driver.findElements(By.className("result"));
        //visit each link
        LinkedList<String> links=new LinkedList<>();
        for (var title: titles){
            links.add(title.getAttribute("href"));
        }
        System.out.println(links.size());
        for(var link: links){
            System.out.println(link);
            driver.get(link);
            jobURL.add(link);//add link to jobURL
            String title=driver.findElement(By.className("jobsearch-JobInfoHeader-title")).getText();//get jobtitle
            System.out.println(title);//output
            jobTitle.add(title);//put into list
            String company=driver.findElement(By.cssSelector("div.jobsearch-DesktopStickyContainer-companyrating div")).getText();//get company
            System.out.println(company);//output
            jobCompany.add(company);//put into list
            String desc=driver.findElement(By.cssSelector("div#jobDescriptionText")).getText();//get desc
            System.out.println(desc);//output
            jobDesc.add(desc);//put into list
            System.out.println("\n");
        }
    }
    LinkedList<String> getJobTitle() {
        return jobTitle;
    }
    LinkedList<String> getJobCompany() {
        return jobCompany;
    }
    LinkedList<String> getJobDesc() {
        return jobDesc;
    }
    LinkedList<String> getJobURL() {
        return jobURL;
    }
}
