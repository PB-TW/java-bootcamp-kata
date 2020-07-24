package kata;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class Payment {
  BigDecimal price;
  String description;
  Category category;
}
