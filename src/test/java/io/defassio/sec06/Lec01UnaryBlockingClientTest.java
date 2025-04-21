package io.defassio.sec06;

import com.google.protobuf.Empty;
import io.defassio.proto.models.sec06.AllAccountsResponse;
import io.defassio.proto.models.sec06.BalanceCheckRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec01UnaryBlockingClientTest extends AbstractTest {

    private static final Logger logger = LoggerFactory.getLogger(Lec01UnaryBlockingClientTest.class);

    @Test
    public void getBalanceTest() {

        var request = BalanceCheckRequest.newBuilder().setAccountNumber(1).build();

        var balance = this.blockingStub.getAccountBalance(request);

        logger.info("unary balance receive: {}", balance);

        Assertions.assertEquals(100, balance.getBalance());
    }

    @Test
    public void allAccountsTest() {
        var allAccounts = this.blockingStub.getAllAccounts(Empty.getDefaultInstance());

        logger.info("all accounts size: {}", allAccounts.getAccountsBalanceCount());

        Assertions.assertEquals(10, allAccounts.getAccountsBalanceCount());
    }


}
