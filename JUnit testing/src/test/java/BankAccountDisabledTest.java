import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Evgenii Zhuravel created on 05.09.2022
 * @version 1.0
 */
@ExtendWith(BankAccountParameterResolver.class)
public class BankAccountDisabledTest {

    @Test
    @Disabled("Temporarily disabled due to maintenance")
    @DisplayName("Deposit 500 successfully")
    public void testDeposit(BankAccount bankAccount) {
        bankAccount.deposit(500);

        assertEquals(500, bankAccount.getBalance());
    }
}
