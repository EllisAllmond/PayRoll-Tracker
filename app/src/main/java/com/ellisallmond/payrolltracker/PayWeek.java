package com.ellisallmond.payrolltracker;

import android.app.Application;

/**
 * Created by ellisallmond on 7/3/16.
 */
public class PayWeek extends Application{

    private String strHours;
    private String strDollars;
    private String strMon;
    private String strTue;
    private String strWed;
    private String strThu;
    private String strFri;
    private String strSat;
    private String strSun;
    private String strPayRate;
    private String strTaxRate;

    public String getHours(){
        return strHours;
    }
    public void setHours(String hours){
        strHours = hours;
    }


    public String getDollars(){
        return strDollars;
    }
    public void setDollars(String dollars){
        strDollars = dollars;
    }


    public String getMon(){
        return strMon;
    }
    public void setMon(String monday){
        strMon = monday;
    }


    public String getTue(){
        return strTue;
    }
    public void setTue(String tuesday){
        strTue = tuesday;
    }


    public String getWed(){
        return strWed;
    }
    public void setWed(String wednesday){
        strWed = wednesday;
    }


    public String getThu(){
        return strThu;
    }
    public void setThu(String thurday){
        strThu = thurday;
    }


    public String getFri(){
        return strFri;
    }
    public void setFri(String friday){
        strFri = friday;
    }


    public String getSat(){
        return strSat;
    }
    public void setSat(String saturday){
        strSat = saturday;
    }


    public String getSun(){
        return strSun;
    }
    public void setSun(String sunday){
        strSun = sunday;
    }


    public String getPayRate(){
        return strPayRate;
    }
    public void setPayRate(String payRate){
        strPayRate = payRate;
    }


    public String getTaxRate(){
        return strTaxRate;
    }
    public void setTaxRate(String taxRate){
        strTaxRate = taxRate;
    }

}
