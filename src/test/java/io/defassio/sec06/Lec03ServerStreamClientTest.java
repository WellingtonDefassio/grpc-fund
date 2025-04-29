package io.defassio.sec06;

import com.google.protobuf.Empty;
import io.defassio.common.ResponseObserver;
import io.defassio.proto.models.sec06.AllAccountsResponse;
import io.defassio.proto.models.sec06.Money;
import io.defassio.proto.models.sec06.WithdrawRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec03ServerStreamClientTest extends AbstractTest {

    private static final Logger logger = LoggerFactory.getLogger(Lec03ServerStreamClientTest.class);

    @Test
    public void blockingClientWithdrawTest() {
        var request = WithdrawRequest.newBuilder().setAccountNumber(2).setAmount(20).build();
        var iterator = this.blockingStub.withdraw(request);
        int count = 0;

        while (iterator.hasNext()) {
            logger.info("received money: {}", iterator.next());
            count++;
        }
        Assertions.assertEquals(2, count);
    }

    @Test
    public void asyncClientWithdrawTest(){
        var request = WithdrawRequest.newBuilder().setAccountNumber(2).setAmount(20).build();

        var observer = ResponseObserver.<Money>create();
        this.stub.withdraw(request, observer);

        observer.await();

        Assertions.assertEquals(2, observer.getItems().size());
        Assertions.assertEquals(10, observer.getItems().getFirst().getAmount());
        Assertions.assertNull(observer.getThrowable());
    }

}
