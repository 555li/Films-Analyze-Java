package com.neusoft.xlm.entity.indexData;

public class MainMoviesDataDTO {
    int len;
    double rate;
    String cast;
    String country;
    String lang;
    int typeCount;

    public int getTypeCount() {
        return typeCount;
    }

    public void setTypeCount(int typeCount) {
        this.typeCount = typeCount;
    }

    public MainMoviesDataDTO(int len, double rate, String cast, String country, String lang, int typeCount) {
        this.len = len;
        this.rate = rate;
        this.cast = cast;
        this.country = country;
        this.lang = lang;
        this.typeCount=typeCount;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
