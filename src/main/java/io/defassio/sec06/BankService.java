package io.defassio.sec06;

import com.google.protobuf.Empty;
import io.defassio.proto.models.sec06.AccountBalance;
import io.defassio.proto.models.sec06.AllAccountsResponse;
import io.defassio.proto.models.sec06.BalanceCheckRequest;
import io.defassio.proto.models.sec06.BankServiceGrpc;
import io.defassio.sec06.repository.AccountRepository;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class BankService extends BankServiceGrpc.BankServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BankService.class);

    @Override
    public void getAccountBalance(BalanceCheckRequest request, StreamObserver<AccountBalance> responseObserver) {
        var accountNumber = request.getAccountNumber();
        log.info("get balance from account {}", accountNumber);
        var balance = AccountRepository.getBalance(accountNumber);
        log.info("the balance from account {} is {}", accountNumber, balance);
        var accountBalance = AccountBalance.newBuilder()
                .setAccountNumber(accountNumber)
                .setBalance(balance)
                .build();

        responseObserver.onNext(accountBalance);
        responseObserver.onCompleted();
        log.info("completed");
    }

    @Override
    public void getAllAccounts(Empty request, StreamObserver<AllAccountsResponse> responseObserver) {
        Map<Integer, Integer> allAccounts = AccountRepository.getAllAccounts();
        List<AccountBalance> balances = allAccounts
                .entrySet()
                .stream()
                .map(e ->
                        AccountBalance.newBuilder().setAccountNumber(e.getKey()).setBalance(e.getValue()).build()
                ).toList();

        var response = AllAccountsResponse.newBuilder().addAllAccountsBalance(balances).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
