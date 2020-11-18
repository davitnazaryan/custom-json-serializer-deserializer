package am.serializer.json.model;

import am.serializer.json.annotation.IgnoreField;
import am.serializer.json.annotation.PropertyName;

public class User {

  @PropertyName("first_name")
  private String name = "poxos";

  @PropertyName("last_name")
  private String surname = "poxosyan";
  ;

  private int age = 20;

  private String email = "poxos@poxosyan.com";

  private boolean isMale;

  private int[] bla = {1, 2, 3};

  public int[] getBla() {
    return bla;
  }

  @IgnoreField
  private String password = "password";

  private Address address = new Address();

  public boolean getIsMale() {
    return isMale;
  }

  public void setMale(boolean male) {
    isMale = male;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
