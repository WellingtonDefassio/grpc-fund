package io.defassio.sec06.requestHandlers;

import io.defassio.proto.models.sec06.AccountBalance;
import io.defassio.proto.models.sec06.DepositRequest;
import io.defassio.sec06.BankService;
import io.defassio.sec06.repository.AccountRepository;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DepositRequestHandler implements StreamObserver<DepositRequest> {

    private final StreamObserver<AccountBalance> responseObserver;
    private int accountNumber;

    private static final Logger log = LoggerFactory.getLogger(DepositRequestHandler.class);

    public DepositRequestHandler(StreamObserver<AccountBalance> responseObserver) {
        this.responseObserver = responseObserver;
    }

    @Override
    public void onNext(DepositRequest depositRequest) {
        switch (depositRequest.getRequestCase()) {
            case ACCOUNT_NUMBER -> this.accountNumber = depositRequest.getAccountNumber();
            case MONEY -> AccountRepository.addAmount(this.accountNumber, depositRequest.getMoney().getAmount());
        }
    }

    @Override
    public void onError(Throwable throwable) {
        log.info("client error {}", throwable.getMessage());
    }

    @Override
    public void onCompleted() {
        var accountBalance = AccountBalance.newBuilder()
                .setAccountNumber(this.accountNumber)
                .setBalance(AccountRepository.getBalance(this.accountNumber))
                .build();

        this.responseObserver.onNext(accountBalance);
        this.responseObserver.onCompleted();
    }
}
