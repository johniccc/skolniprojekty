package cz.spse.bajer.pg3;

import java.time.LocalDate;

public class InputFormatCheck {

  public static void checkAll(String title, String gender, String firstName, String surname, String birthDate,
      String birthNumber, String phone, String email, String password) {
    validateNotEmpty(title, "Titul");
    validateNotEmpty(firstName, "Jméno");
    validateNotEmpty(surname, "Příjmení");
    validateNotEmpty(birthDate, "Datum narození");
    validateNotEmpty(birthNumber, "Rodné číslo");
    validateNotEmpty(phone, "Telefon");
    validateNotEmpty(email, "Email");
    validateNotEmpty(password, "Heslo");

    checkNames(firstName);
    checkNames(surname);
    checkBirthDate(birthDate);
    checkBirthNumber(birthNumber);
    birthNumberMatchesBirthDate(birthNumber, birthDate, gender);
    checkEmail(email);
    checkPhone(phone);
  }

  private static void validateNotEmpty(String value, String fieldName) {
    if (value == null || value.isEmpty()) {
      throw new IllegalArgumentException("Pole \"" + fieldName + "\" musí být vyplněno.");
    }
  }

  public static void checkNames(String name) {
    if (!(name != null && name.matches("^[A-Za-zÁ-ž]{2,}$")))
      throw new IllegalArgumentException("Neplatný formát jména nebo příjmení. (pouze písmena)");
  }

  public static void checkBirthDate(String birthDate) {
    LocalDate birthDateParsed;
    try {
      birthDateParsed = LocalDate.parse(birthDate, UserManager.dateFormat);
    } catch (Exception e) {
      throw new IllegalArgumentException("Neplatný formát data narození. (d.m.yyyy)");
    }
    if (!checkBirthDate(birthDateParsed)) {
      throw new IllegalArgumentException("Datum narození nemůže být v budoucnosti.");
    }
  }

  public static boolean checkBirthDate(LocalDate birthDate) {
    return birthDate != null && birthDate.isBefore(LocalDate.now());
  }

  public static void checkBirthNumber(String birthNumber) {
    if (!(birthNumber != null && birthNumber.matches("^[0-9]{6}/[0-9]{4}$")))
      throw new IllegalArgumentException("Neplatný formát rodného čísla. (př. 120101/1234)");
  }

  public static void birthNumberMatchesBirthDate(String birthNumber, String birthDate,
      String gender) {
    LocalDate currentDate = LocalDate.now();
    InputFormatCheck.checkBirthDate(birthDate);

    LocalDate birthDateParsed;
    try {
      birthDateParsed = LocalDate.parse(birthDate, UserManager.dateFormat);
    } catch (Exception e) {
      throw new IllegalArgumentException("Neplatný formát data narození. (d.m.yyyy)");
    }

    checkBirthNumber(birthNumber);
    String[] birthNumberSplit = birthNumber.split("/");

    int year;
    int month;
    int day;

    try {
      year = Integer.parseInt(birthNumberSplit[0].substring(0, 2));
      month = Integer.parseInt(birthNumberSplit[0].substring(2, 4)) - (gender.equals("Žena") ? 50 : 0);
      day = Integer.parseInt(birthNumberSplit[0].substring(4, 6));
    } catch (Exception e) {
      throw new IllegalArgumentException("Neplatný formát rodného čísla. (př. 120101/1234)");
    }

    // zajištění rozpoznání předchozího či nynějšího století
    int currentYear = currentDate.getYear();
    year += (year > currentYear % 100 ? currentYear - currentYear % 100 - 100 : currentYear - currentYear % 100);

    LocalDate birthCodeDate;

    try {
      birthCodeDate = LocalDate.of(year, month, day);
    } catch (Exception e) {
      throw new IllegalArgumentException("Z údajů v rodném čísle nelze vytvořit platné datum.");
    }

    if (!(birthDateParsed.isEqual(birthCodeDate)))
      throw new IllegalArgumentException("Kombinace data narození a rodného čísla není možná.");

  }

  public static void checkEmail(String email) {
    if (!(email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,63}$")))
      throw new IllegalArgumentException("Neplatný formát emailové adresy. (př. priklad@priklad.cz)");
  }

  public static void checkPhone(String phone) {
    if (!(phone != null && phone.matches("\\+420[0-9]{9}")))
      throw new IllegalArgumentException("Neplatný formát telefonního čísla. (+420 XXX XXX XXX)");
  }
}
