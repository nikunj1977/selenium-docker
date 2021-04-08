package com.newtours.tests;

import com.newtours.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.nio.file.Path;
import java.nio.file.Paths;

public class BookFlightTest extends BaseTest {

    //private WebDriver driver;
    private String noOfPassengers;
    private String expectedPrice;


   /* Path currentRelativePath = Paths.get("");
    private String BaseDir = currentRelativePath.toAbsolutePath().toString();*/


    @BeforeTest
    @Parameters({"noOfPassengers", "expectedPrice"})
    public void setupParameters(String noOfPassengers,String expectedPrice){
        this.noOfPassengers = noOfPassengers;
        this.expectedPrice = expectedPrice;
        //System.setProperty("webdriver.chrome.driver",BaseDir + "/driver/chromedriver.exe");
        //this.driver = new ChromeDriver();
    }

    @Test
    public void registrationPage(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo();
        registrationPage.enterUserDetails("selenium","docker");
        registrationPage.enterUserCredentials("selenium","docker");
        registrationPage.submit();
    }
    @Test(dependsOnMethods = "registrationPage")
    public void registrationConfirmationPage(){
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        registrationConfirmationPage.gpToFlightDetailsPage();
    }

    @Test(dependsOnMethods = "registrationConfirmationPage")
    public void flightDetailsPage(){
        FlightDetailsPage flightDetailsPage = new FlightDetailsPage(driver);
        flightDetailsPage.selectPassengers(noOfPassengers);
        flightDetailsPage.goToFindFlightConfirmationPage();
    }

    @Test(dependsOnMethods = "flightDetailsPage")
    public void findFlightPage(){
        FindFlightPage findFlightPage = new FindFlightPage(driver);
        findFlightPage.submitFindFlightPage();
        findFlightPage.goToorderConfirmationPage();
    }

    @Test(dependsOnMethods = "findFlightPage")
    public void FlightconfirmationPage(){
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        String actualPrice = flightConfirmationPage.getPrice();
        Assert.assertEquals(actualPrice,expectedPrice);
    }



}
