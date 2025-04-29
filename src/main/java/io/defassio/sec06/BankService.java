package io.defassio.sec06;

import com.google.common.util.concurrent.Uninterruptibles;
import com.google.protobuf.Empty;
import io.defassio.proto.models.sec06.*;
import io.defassio.sec06.repository.AccountRepository;
import io.defassio.sec06.requestHandlers.DepositRequestHandler;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    @Override
    public void withdraw(WithdrawRequest request, StreamObserver<Money> responseObserver) {
        var accountNumber = request.getAccountNumber();
        var reqAmount = request.getAmount();
        var accountBalance = AccountRepository.getBalance(accountNumber);
        log.info("account number {} / reqAmount {}, accountBalance {}", accountNumber, reqAmount, accountBalance);

        if (reqAmount > accountBalance) {
            log.info("no balance");
            responseObserver.onCompleted();
            return;
        }

        for (int i = 0; i < (reqAmount / 10); i++) {
            var money = Money.newBuilder().setAmount(10).build();
            responseObserver.onNext(money);
            log.info("money sent {}", money);
            AccountRepository.deductAmount(accountNumber, 10);
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        }
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<DepositRequest> deposit(StreamObserver<AccountBalance> responseObserver) {
        return new DepositRequestHandler(responseObserver);
    }
}
