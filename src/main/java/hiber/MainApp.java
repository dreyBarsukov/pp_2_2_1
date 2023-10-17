package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      Car car1 = new Car("Tesla s", 4);
      Car car2 = new Car("Tesla x", 3);
      Car car3 = new Car("Tesla y", 2);
      Car car4 = new Car("Cybertruck", 1);

      User user1 = new User(car1, "Ivan", "Ivanov", "ivan@gmail.com");
      car1.setUser(user1);
      User user2 = new User(car2, "Petr", "Petrov", "petr@gmail.com");
      car2.setUser(user2);
      User user3 = new User(car3, "Sveta", "Svetikova", "sveta@gmail.com");
      car3.setUser(user3);
      User user4 = new User(car4, "Elon", "Musk", "press-release@forbes.ru");
      car4.setUser(user4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users2 = userService.listUsers();
      for (User user : users2) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      User cybertruck = userService.getUserByModelAndSeriesOfCar("Tesla s", 4);
      System.out.println(cybertruck);
      System.out.println("Id = "+cybertruck.getId());
      System.out.println("First Name = "+cybertruck.getFirstName());
      System.out.println("Last Name = "+cybertruck.getLastName());
      System.out.println("Email = "+cybertruck.getEmail());
      System.out.println("Car model = "+cybertruck.getCar().getModel());
      System.out.println("Car series = "+cybertruck.getCar().getSeries());
      System.out.println();

      User user5 = userService.getUserByModelAndSeriesOfCar("Cybertruck", 1);
      System.out.println("Id = "+user5.getId());
      System.out.println("First Name = "+user5.getFirstName());
      System.out.println("Last Name = "+user5.getLastName());
      System.out.println("Email = "+user5.getEmail());
      System.out.println("Car model = "+user5.getCar().getModel());
      System.out.println("Car series = "+user5.getCar().getSeries());
      System.out.println();


      context.close();
   }
}
