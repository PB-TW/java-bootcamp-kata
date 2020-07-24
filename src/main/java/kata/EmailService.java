package kata;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class EmailService {

  private EmailSender emailSender;

  public void send(Map<Category, BigDecimal> map) {
    if (map.isEmpty()) {
      throw new IllegalArgumentException();
    }

    String spendings = map.entrySet().stream()
      .sorted(byHighestAmount())
      .map(this::getSpendingDescriptionFor)
      .collect(Collectors.joining());

    emailSender.send("Hello card user!\n"
      + "\n"
      + "We have detected unusually high spending on your card in these categories:\n"
      + "\n"
      + spendings
      + "\n"
      + "Love,\n"
      + "\n" + "The Credit Card Company");
  }

  private Comparator<Map.Entry<Category, BigDecimal>> byHighestAmount() {
    return (o1, o2) -> o2.getValue().compareTo(o1.getValue());
  }

  private String getSpendingDescriptionFor(Map.Entry<Category, BigDecimal> entry) {
    return "* You spent $" + entry.getValue() + " on " + entry.getKey().description + "\n";
  }

  public void setEmailSender(EmailSender emailSender) {
    this.emailSender = emailSender;
  }
}
