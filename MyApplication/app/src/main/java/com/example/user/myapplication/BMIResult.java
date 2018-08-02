package com.example.user.myapplication;



public class BMIResult {



    private String height;
    private String weight;
    private String BMI;
    private String Date;
    //TODO also add Date

    public BMIResult(String height, String weight, String BMI, String Date){
        this.height = height;
        this.weight = weight;
        this.BMI = BMI;
        this.Date = Date;
    }

    public String getHeight(){ return height;}
    public void setHeight(String height){ this.height = height;}
    public String getWeight(){ return weight;}
    public void setWeight(String weight){ this.weight = weight;}
    public String getBMI(){ return BMI;}
    public void setBMI(String BMI){ this.BMI = BMI;}
    public String getDate(){ return Date;}
    public void setDate(String Date){ this.Date = Date;}


    public String toCustomString(){ return "Date: "+String.valueOf(getDate())+" Height: "+String.valueOf(getHeight())+" Weight: "+String.valueOf(getWeight())+" BMI: "+String.valueOf(getBMI());}
}
