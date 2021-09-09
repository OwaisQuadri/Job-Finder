/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jobfinder;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import java.util.*;
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
    private int numOfJobs = 0;

    //constructor
    public JobScraper() {
    }

    private void pageInfo() {
        System.out.println("Title: " + driver.getTitle());
        System.out.println("URL: " + driver.getCurrentUrl());
        System.out.println("Connection made!");
    }

    public void getJobInfo(String query, String where) {
        System.out.println("Establishihng Connection . . .");
        //go to new page
        String urlQuery = "";
        String whereQuery = "";
        //modify query to insert into URL
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
        Map links = new Hashtable();
        boolean nxtPage = true;
        //check num of jobs
        url = "https://ca.indeed.com/jobs?q=" + urlQuery + "&l=" + whereQuery + "&start=0";
        driver.get(url);
//        Thread.sleep(100);
//        pageInfo();
        String numJobString = driver.findElement(By.id("searchCountPages")).getText();
        String[] nums = numJobString.split(" ")[3].split(",");
        //bring all seperated numbers into one 
        String num = "";
        for (String n : nums) {
            num += n;
        }
        //display current page info
        pageInfo();
        int pages = (Integer.parseInt(num) + 1) / 10;
        if (pages>125)pages=125;
        //output number of jobs being searched through
        System.out.println("Scanning through ~" + pages*10 + " jobs");
        for (int page = 0; page < pages; page++) {
            //url = "https://ca.indeed.com/jobs?q=" + urlQuery + "&l=" + whereQuery + "&start=" + page * 10;
            //url = "https://ca.indeed.com/jobs?as_and=" + urlQuery + "&as_phr=&as_any=&as_not=&as_ttl=&as_cmp=&jt=all&st=&salary=&radius=25&l="+whereQuery+"&fromage=any&limit=100&sort=&psf=advsrch&from=advancedsearch";
            //go directly to the search URL
            driver.get(url);
//            Thread.sleep(100);

            //make a list of each Job's independent link
            List<WebElement> titles = driver.findElements(By.className("result"));
            //visit each link
            for (var title : titles) {
                //make sure there are no duplicates
                String tempLink = title.getAttribute("href");
                links.put(tempLink, "");

            }
        }
        //get the array of links out of the map
        String[] link = links.keySet().toString().replace("[", "").replace("]", "").split(",");
        numOfJobs = link.length;
//        System.out.println(numOfJobs);
        for (String l : link) {
            driver.get(l);
//            Thread.sleep(100);
            boolean deleteLink=false;
            try {
                driver.findElement(By.className("jobsearch-JobInfoHeader-title"));
            } catch (Exception e) {
                //System.out.println("link deleted");
                deleteLink = true;
            }
            if (!deleteLink) {
                jobURL.add(l);//add link to jobURL
                String title = driver.findElement(By.className("jobsearch-JobInfoHeader-title")).getText().toLowerCase();//get jobtitle
//            System.out.println(title);//output
                jobTitle.add(title);//put into list
                String company = driver.findElement(By.cssSelector("div.jobsearch-DesktopStickyContainer-companyrating div")).getText().toLowerCase();//get company
//            System.out.println(company);//output
                jobCompany.add(company);//put into list
                String desc = driver.findElement(By.cssSelector("div#jobDescriptionText")).getText().toLowerCase();//get desc
//            System.out.println(desc);//output
                jobDesc.add(desc);//put into list
            }
        }
    }

    public LinkedList<String> getJobTitle() {
        return jobTitle;
    }

    public LinkedList<String> getJobCompany() {
        return jobCompany;
    }

    public LinkedList<String> getJobDesc() {
        return jobDesc;
    }

    public LinkedList<String> getJobURL() {
        return jobURL;
    }

    public int getNumOfJobs() {
        return numOfJobs;
    }
}
