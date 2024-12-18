// ? může být vystaven BUILDER PATTERN místo STATIC FACTORY METHOD

package cz.spse.bajer.pg3;

import java.time.LocalDate;

import org.mindrot.jbcrypt.BCrypt;

public class User {
  private String title;
  private String gender;
  private String firstName;
  private String surname;
  private LocalDate birthDate;
  private String birthNumber;
  private String email;
  private String phone;
  private String hashedPassword;
  private boolean isAdmin;

  private User(String title, String gender, String firstName, String surname, LocalDate birthDate,
      String birthNumber,
      String phone, String email, String hashedPassword, boolean isAdmin) {
    this.title = title;
    this.gender = gender;
    this.firstName = firstName;
    this.surname = surname;
    this.birthDate = birthDate;
    this.birthNumber = birthNumber;
    this.phone = phone;
    this.birthNumber = birthNumber;
    this.phone = phone;
    this.email = email;
    this.hashedPassword = hashedPassword;
    this.isAdmin = isAdmin;
  }

  public static User createUser(
      String title, String gender, String firstName, String surname, LocalDate birthDate,
      String birthNumber, String phone, String email, String password, boolean isAdmin) {
    InputFormatCheck.checkAll(title, gender, firstName, surname, birthDate.format(UserManager.dateFormat),
        birthNumber, phone, email, password);
    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
    return new User(title, gender, firstName, surname, birthDate, birthNumber, phone, email, hashedPassword,
        isAdmin);
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getGender() {
    return this.gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getSurname() {
    return this.surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public LocalDate getBirthDate() {
    return this.birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public String getBirthNumber() {
    return this.birthNumber;
  }

  public void setBirthNumber(String birthNumber) {
    this.birthNumber = birthNumber;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return this.phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getHashedPassword() {
    return this.hashedPassword;
  }

  public void setPassword(String password) {
    this.hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
  }

  public boolean getIsAdmin() {
    return this.isAdmin;
  }

  public void setIsAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }

}
