package am.serializer.json.model;

import am.serializer.json.annotation.PropertyName;

public class Address {

  @PropertyName("country_name")
  private String country;

  @PropertyName("city_name")
  private String city;

  private String street;

  @PropertyName("zip_code")
  private String zipCode;

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }
}
