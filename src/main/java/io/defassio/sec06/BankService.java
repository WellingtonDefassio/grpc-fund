package io.defassio.sec06;

import io.defassio.proto.models.sec06.AccountBalance;
import io.defassio.proto.models.sec06.BalanceCheckRequest;
import io.defassio.proto.models.sec06.BankServiceGrpc;
import io.defassio.sec06.repository.AccountRepository;
import io.grpc.stub.StreamObserver;

public class BankService extends BankServiceGrpc.BankServiceImplBase {

    @Override
    public void getAccountBalance(BalanceCheckRequest request, StreamObserver<AccountBalance> responseObserver) {
        var accountNumber = request.getAccountNumber();
        var balance = AccountRepository.getBalance(accountNumber);
        var accountBalance = AccountBalance.newBuilder()
                             .setAccountNumber(accountNumber)
                             .setBalance(balance)
                             .build();

        responseObserver.onNext(accountBalance);
        responseObserver.onCompleted();
    }
}
