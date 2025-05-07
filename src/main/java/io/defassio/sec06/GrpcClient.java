package io.defassio.sec06;

import io.defassio.proto.models.sec06.AccountBalance;
import io.defassio.proto.models.sec06.BalanceCheckRequest;
import io.defassio.proto.models.sec06.BankServiceGrpc;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GrpcClient {

    private static final Logger log = LoggerFactory.getLogger(GrpcClient.class);

    public static void main(String[] args) {

        var channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();

        var stub = BankServiceGrpc.newBlockingStub(channel);

        AccountBalance accountBalance = stub.getAccountBalance(
                BalanceCheckRequest
                        .newBuilder()
                        .setAccountNumber(2)
                        .build());


        log.info("the account balance is {}", accountBalance);
    }

}
