package kata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentAggregatorTest {

  @Test
  @DisplayName("should return empty map given empty list")
  void shouldReturnEmptyMapGivenEmptyList() {
    PaymentAggregator paymentAggregator = new PaymentAggregator();
    List<Payment> paymentList = Collections.emptyList();
    Map<Category, BigDecimal> aggregatedPayments = paymentAggregator.aggregate(paymentList);

    assertEquals(Collections.emptyMap(), aggregatedPayments);
  }

  @Test
  @DisplayName("should return map with one entry given list with one entry")
  void shouldReturnMapWithOneEntryGivenListWithOneEntry() {
    PaymentAggregator paymentAggregator = new PaymentAggregator();

    BigDecimal price = new BigDecimal("300");
    Category category = Category.TRAVEL;
    List<Payment> paymentList = List.of(new Payment(price, "whatever", category));
    Map<Category, BigDecimal> aggregatedPayments = paymentAggregator.aggregate(paymentList);

    assertEquals(Map.of(category, price), aggregatedPayments);
  }


  @Test
  @DisplayName("should return map with one entry given aggregating the price for the same category")
  void shouldReturnMapWithOneEntryGivenAggregatingThePriceForTheSameCategory() {
    PaymentAggregator paymentAggregator = new PaymentAggregator();

    BigDecimal price = new BigDecimal("600");
    Category category = Category.TRAVEL;
    List<Payment> paymentList = List.of(
        new Payment(new BigDecimal("300"), "whatever", category),
        new Payment(new BigDecimal("300"), "whatever", category)
    );
    Map<Category, BigDecimal> aggregatedPayments = paymentAggregator.aggregate(paymentList);

    assertEquals(Map.of(category, price), aggregatedPayments);
  }


}