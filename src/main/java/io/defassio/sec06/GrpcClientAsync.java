package io.defassio.sec06;

import io.defassio.proto.models.sec06.AccountBalance;
import io.defassio.proto.models.sec06.BalanceCheckRequest;
import io.defassio.proto.models.sec06.BankServiceGrpc;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class GrpcClientAsync {

    private static final Logger log = LoggerFactory.getLogger(GrpcClientAsync.class);

    public static void main(String[] args) throws InterruptedException {

        var channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();

        var stub = BankServiceGrpc.newStub(channel);

        stub.getAccountBalance(
                BalanceCheckRequest
                        .newBuilder()
                        .setAccountNumber(2)
                        .build(),

                new StreamObserver<AccountBalance>() {
                    @Override
                    public void onNext(AccountBalance accountBalance) {
                        log.info("the account balance is {}", accountBalance);
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onCompleted() {
                        log.info("onCompleted: finish");
                    }
                }
        );

        log.info("done");
        Thread.sleep(Duration.ofSeconds(1));
    }

}
