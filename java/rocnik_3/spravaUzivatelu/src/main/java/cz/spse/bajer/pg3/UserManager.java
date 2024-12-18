package cz.spse.bajer.pg3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;

public class UserManager {
  private static UserManager instance = null;
  final private Map<String, User> userList;
  public final static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d.M.yyyy");

  private UserManager() {
    this.userList = new HashMap<>();
    this.userList.put("jakub.sedlak",
        User.createUser("Bc.", "Muž", "Jakub", "Sedlák", LocalDate.of(1992, 7, 30), "920730/1234",
            "+420608123456", "jakub.sedlak@seznam.cz", "Jakub2024@", true));

    this.userList.put("petra.horakova",
        User.createUser("Mgr.", "Žena", "Petra", "Horáková", LocalDate.of(1988, 3, 18), "885318/5678",
            "+420607654321", "petra.horakova@centrum.cz", "Petra123!", false));

    this.userList.put("michal.kral",
        User.createUser("Ing.", "Muž", "Michal", "Král", LocalDate.of(1985, 9, 12), "850912/9012",
            "+420606987654", "michal.kral@gmail.com", "Micha1234#", true));

    this.userList.put("lenka.prochazkova",
        User.createUser("PhDr.", "Žena", "Lenka", "Procházková", LocalDate.of(1994, 5, 25), "945525/3456",
            "+420605432198", "lenka.prochazkova@post.cz", "Lenka@2024", false));

    this.userList.put("petr.jelinek",
        User.createUser("Bc.", "Muž", "Petr", "Jelínek", LocalDate.of(1990, 2, 14), "900214/7890",
            "+420604321987", "petr.jelinek@seznam.cz", "P@ssword1", true));

    this.userList.put("ivana.konecna",
        User.createUser("Mgr.", "Žena", "Ivana", "Konečná", LocalDate.of(1987, 11, 22), "876122/4567",
            "+420603123456", "ivana.konecna@centrum.cz", "Ivana#2024", false));

    this.userList.put("radek.bruska",
        User.createUser("Ing.", "Muž", "Radek", "Bruška", LocalDate.of(1991, 6, 7), "910607/2345",
            "+420602987654", "radek.bruska@gmail.com", "Radek2024!", true));

    this.userList.put("nela.martinova",
        User.createUser("Bc.", "Žena", "Nela", "Martinová", LocalDate.of(1993, 8, 10), "935810/6789",
            "+420601234567", "nela.martinova@post.cz", "Nela1234@", false));

    this.userList.put("marek.dvorak",
        User.createUser("Bc.", "Muž", "Marek", "Dvořák", LocalDate.of(1992, 4, 15), "920415/1234",
            "+420608765432", "marek.dvorak@seznam.cz", "Marek2024#", true));

    this.userList.put("anna.benesova",
        User.createUser("Mgr.", "Žena", "Anna", "Benešová", LocalDate.of(1989, 10, 2), "896002/5678",
            "+420607543210", "anna.benesova@centrum.cz", "Anna@2024", false));

    this.userList.put("ondrej.tomas",
        User.createUser("PhDr.", "Muž", "Ondřej", "Tomáš", LocalDate.of(1984, 1, 29), "840129/9012",
            "+420606543210", "ondrej.tomas@gmail.com", "Ondrej123!", true));

    this.userList.put("jana.cernakova",
        User.createUser("Bc.", "Žena", "Jana", "Černáková", LocalDate.of(1995, 3, 14), "955314/3456",
            "+420605432109", "jana.cernakova@post.cz", "Jana2024@", false));

    this.userList.put("vladimir.vlasak",
        User.createUser("Ing.", "Muž", "Vladimír", "Vlašák", LocalDate.of(1980, 12, 22), "801222/2345",
            "+420604321098", "vladimir.vlasak@seznam.cz", "Vlad2024!", true));

    this.userList.put("karolina.soukupova",
        User.createUser("Mgr.", "Žena", "Karolína", "Soukupová", LocalDate.of(1991, 6, 5), "915605/6789",
            "+420603210987", "karolina.soukupova@centrum.cz", "Karolina@2024", false));

    this.userList.put("jiri.fiala",
        User.createUser("Bc.", "Muž", "Jiří", "Fiala", LocalDate.of(1983, 9, 19), "830919/3456",
            "+420602345678", "jiri.fiala@gmail.com", "Jiri1234$", true));

    this.userList.put("martina.machova",
        User.createUser("PhDr.", "Žena", "Martina", "Machová", LocalDate.of(1994, 5, 27), "945527/4567",
            "+420601234890", "martina.machova@post.cz", "Martina2024#", false));

    this.userList.put("testadmin",
        User.createUser("PaedDr.", "Muž", "Jan", "Štefáček", LocalDate.of(2007, 6, 13), "070613/0634",
            "+420773492934", "johnstefacek@seznam.cz", "1234", true));

    this.userList.put("testuser",
        User.createUser("PaedDr.", "Muž", "Jan", "Štefáček", LocalDate.of(2007, 6, 13), "070613/0634",
            "+420773492934", "johnstefacek@seznam.cz", "1234", false));
  }

  public static UserManager getInstance() {
    if (instance == null) {
      instance = new UserManager();
    }
    return instance;
  }

  public String addUser(String username, String title, String gender, String firstName, String surname,
      LocalDate birthDate,
      String birthNumber,
      String phone, String email, String password, boolean isAdmin) {
    try {
      User user = User.createUser(title, gender, firstName, surname, birthDate, birthNumber, phone,
          email,
          password, isAdmin);
      this.userList.put(username, user);
    } catch (Exception e) {
      return e.getMessage();
    }
    return null;
  }

  public boolean removeUser(String username) {
    User user = this.userList.remove(username);
    return user != null;
  }

  public Map<String, User> getUserList() {
    return this.userList;
  }

  public boolean verifyPassword(String password, String hashedPassword) {
    return BCrypt.checkpw(password, hashedPassword);
  }

  public User authenticateLogin(String username, String password) {
    User user = this.userList.get(username);
    if (user != null) {
      if (verifyPassword(password, user.getHashedPassword())) {
        return user;
      }
    }
    return null;
  }
}
