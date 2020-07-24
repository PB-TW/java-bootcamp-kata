package kata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static kata.Category.TRAVEL;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentServiceTest {

  @Test
  @DisplayName("should return an empty list as user with id 3 does not have any payments")
  void shouldGetNoUserPayments() {
    PaymentService paymentService = new PaymentService();
    int userId = 3;
    assertEquals(paymentService.getUserPayments(userId), Collections.emptyList());
  }

  @Test
  @DisplayName("should return the single payment for user with id 12")
  void shouldGetOneUserPayment() {
    PaymentService paymentService = new PaymentService();
    int userId = 12;
    assertEquals(paymentService.getUserPayments(userId), List.of(new Payment(new BigDecimal(300), "Ticket", TRAVEL)));
  }

}