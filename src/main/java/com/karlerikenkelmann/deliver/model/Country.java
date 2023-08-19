package com.karlerikenkelmann.deliver.model;

public enum Country {
    USA("United States of America"),
    ARGENTINA("Argentina"),
    AUSTRIA("Austria"),
    BELGIUM("Belgium"),
    DENMARK("Denmark"),
    FRANCE("France"),
    GERMANY("Germany"),
    ITALY("Italy"),
    LUXEMBOURG("Luxembourg"),
    NETHERLANDS("Netherlands"),
    NORWAY("Norway"),
    POLAND("Poland"),
    PORTUGAL("Portugal"),
    SPAIN("Spain"),
    SWEDEN("Sweden"),
    SWITZERLAND("Switzerland"),
    UNITED_KINGDOM("United Kingdom");


    private String country;

    Country(String country) {
        this.country = country;
    }
    public String getName() {
        return country;
    }

}

