package com.datadriven.TripAdvisor;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.datadriven.base.BaseUI;

public class CalTripCost extends BaseUI {
	
	
	@Test
	public void TestOne()throws InterruptedException
	{
	  InvokeBrowser("Chrome");
	  getURL("websiteURl");
	  Thread.sleep(3000);
	  ElementClick("HolidayHomes_Xpath");
	  sendText("valueNairobi_Xpath","Nairobi");
	  Thread.sleep(1000);
	  ClickEnter("valueNairobi_Xpath");
	  Thread.sleep(3000);
	  ElementClick("checkin_Xpath");
	  selectDate("MonthName_Xpath","NextButton_Xpath","month_m","day_d");
	  selectDate("MonthNameout_Xpath","NextButtonout_Xpath","monthout_m","dayout_d");
	  Thread.sleep(2000);
	  ElementClick("TripadvisorSort_Xpath");
	  ElementClick("TravellerRating_Xpath");
	  
	  Thread.sleep(2000);
	  ElementClick("noguest_Xpath");
	  ElementClick("Addbutton_Xpath");
	  ElementClick("Addbutton_Xpath");
	  ElementClick("ApplyButton_Xpath");
	  
	  Thread.sleep(2000);
	  JavascriptExecutor js = ((JavascriptExecutor) driver);
	  js.executeScript("window.scrollBy(0,1000)");
	  
	  ElementClick("showmore_Xpath");
	  ElementClick("ElevatorcheckBox_Xpath");
	  
	  Thread.sleep(2000);
	  DisplayHotel(1,"name1_Xpath","price1_Xpath","total1_Xpath");
	  DisplayHotel(2,"name2_Xpath","price2_Xpath","total2_Xpath");
	  DisplayHotel(3,"name3_Xpath","price3_Xpath","total3_Xpath");
	  
	  Thread.sleep(2000);
	  ElementClick("Cruise_Xpath");
	  ElementClick("norwegian_Xpath");
	  DisplayCruise("OverView_Xpath","Launched_Xpath");
	  
	  
	  
	  
	  
	}

}
