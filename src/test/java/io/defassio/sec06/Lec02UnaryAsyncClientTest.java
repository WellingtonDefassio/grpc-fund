package io.defassio.sec06;

import com.google.protobuf.Empty;
import io.defassio.common.ResponseObserver;
import io.defassio.proto.models.sec06.AccountBalance;
import io.defassio.proto.models.sec06.AllAccountsResponse;
import io.defassio.proto.models.sec06.BalanceCheckRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec02UnaryAsyncClientTest extends AbstractTest {

    private static final Logger logger = LoggerFactory.getLogger(Lec02UnaryAsyncClientTest.class);

    @Test
    public void getBalanceTest() {

        var request = BalanceCheckRequest.newBuilder().setAccountNumber(1).build();
        var observer = ResponseObserver.<AccountBalance>create();
        this.stub.getAccountBalance(request, observer);
        observer.await();

        Assertions.assertEquals(1, observer.getItems().size());
        Assertions.assertEquals(100, observer.getItems().getFirst().getBalance());
        Assertions.assertNull(observer.getThrowable());
    }

    @Test
    public void getAllAccountsTest() {
        var observer = ResponseObserver.<AllAccountsResponse>create();
        this.stub.getAllAccounts(Empty.getDefaultInstance(), observer);

        observer.await();

        Assertions.assertEquals(1, observer.getItems().size());
        Assertions.assertEquals(10, observer.getItems().getFirst().getAccountsBalanceCount());
        Assertions.assertNull(observer.getThrowable());
    }

}
