package kata;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PaymentAggregator {

  public Map<Category, BigDecimal> aggregate(List<Payment> paymentList) {
    return paymentList
      .stream()
      .collect(Collectors.toMap(
        Payment::getCategory,
        Payment::getPrice
      ))
      ;
  }
}
