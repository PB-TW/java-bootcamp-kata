package kata;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EmailServiceTest {

  @Test
  void shouldThrowExceptionForEmptyMap() {
    EmailService emailService = new EmailService();
    assertThrows(
      IllegalArgumentException.class,
      () -> emailService.send(Collections.emptyMap())
    );
  }

  @ParameterizedTest( name = "Should send message for {0} and {1} - {2} " )
  @CsvSource( {
    "GROCERIES, 200,* You spent $200 on groceries",
    "ENTERTAINMENT, 1,* You spent $1 on entertainment",
    "ENTERTAINMENT, 10,* You spent $10 on entertainment",
    "RESTAURANT, 10,* You spent $10 on restaurant" })
  void shouldSendMessageWithSpendingForEachCategory(Category category, BigDecimal amount, String line) {
    EmailService emailService = new EmailService();
    EmailSender emailSender = mock(EmailSender.class);
    emailService.setEmailSender(emailSender);

    Map<Category, BigDecimal> spendings = Map.of(category, amount);

    emailService.send(spendings);
    verify(emailSender).send("Hello card user!\n" +
      "\n" +
      "We have detected unusually high spending on your card in these categories:\n" +
      "\n" +
      line + "\n" +
      "\n" +
      "Love,\n" +
      "\n" +
      "The Credit Card Company");
  }

  @Test
  void shouldSendMessageWithWithMultipleSpendings(){
    EmailService emailService = new EmailService();
    EmailSender emailSender = mock(EmailSender.class);
    emailService.setEmailSender(emailSender);

    Map<Category, BigDecimal> spendings = Map.of(
      Category.GROCERIES, new BigDecimal("200"),
      Category.ENTERTAINMENT,new BigDecimal("1"));

    emailService.send(spendings);

    verify(emailSender).send("Hello card user!\n" +
      "\n" +
      "We have detected unusually high spending on your card in these categories:\n" +
      "\n" +
      "* You spent $200 on groceries" + "\n" +
      "* You spent $1 on entertainment" + "\n" +
      "\n" +
      "Love,\n" +
      "\n" +
      "The Credit Card Company");

  }


}
