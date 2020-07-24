package kata;

import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentService {

  @SneakyThrows
  public List<Payment> getUserPayments(int userId) {

    return Files.lines(Paths.get(getClass().getResource("/Payments.csv").toURI()))
      .skip(1)
      .map(UserWithPayment::of)
      .filter(userWithPayment -> userWithPayment.userId == userId)
      .map(userWithPayment -> userWithPayment.payment)
      .collect(Collectors.toList());


  }

  private static class UserWithPayment{
    int userId;
    Payment payment;

    public static UserWithPayment of(String line){
      UserWithPayment userWithPayment = new UserWithPayment();
      String[] parts = line.split(",\\s+");
      userWithPayment.userId = Integer.parseInt(parts[0]);
      userWithPayment.payment = new Payment(new BigDecimal(parts[1]), parts[2], Category.valueOf(parts[3]));
      return userWithPayment;
    }
  }
}
