package io.defassio.sec06.requestHandlers;

import io.defassio.proto.models.sec06.AccountBalance;
import io.defassio.proto.models.sec06.TransferRequest;
import io.defassio.proto.models.sec06.TransferResponse;
import io.defassio.proto.models.sec06.TransferStatus;
import io.defassio.sec06.GrpcClientAsync;
import io.defassio.sec06.repository.AccountRepository;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.defassio.proto.models.sec06.TransferStatus.COMPLETED;
import static io.defassio.proto.models.sec06.TransferStatus.REJECTED;

public class TransferRequestHandler implements StreamObserver<TransferRequest> {

    private static final Logger log = LoggerFactory.getLogger(TransferRequestHandler.class);

    private final StreamObserver<TransferResponse> responseObserver;

    public TransferRequestHandler(StreamObserver<TransferResponse> responseObserver) {
        this.responseObserver = responseObserver;
    }

    @Override
    public void onNext(TransferRequest transferRequest) {
        var status = this.transfer(transferRequest);
        var response = TransferResponse.newBuilder()
                .setFromAccount(this.toAccountBalance(transferRequest.getFromAccount()))
                .setToAccount(this.toAccountBalance(transferRequest.getToAccount()))
                .setStatus(status)
                .build();

        this.responseObserver.onNext(response);
    }

    @Override
    public void onError(Throwable throwable) {
        log.info("client error {}", throwable.getMessage());
    }

    @Override
    public void onCompleted() {
        log.info("transfer request stream completed");
        this.responseObserver.onCompleted();
    }

    private TransferStatus transfer(TransferRequest request) {
        var amount = request.getAmount();
        var from_account = request.getFromAccount();
        var to_account = request.getToAccount();
        var status = REJECTED;
        if (AccountRepository.getBalance(from_account) >= amount && (from_account != to_account)) {
            AccountRepository.deductAmount(from_account, amount);
            AccountRepository.addAmount(to_account, amount);
            status = COMPLETED;
        }
        return status;
    }

    private AccountBalance toAccountBalance(int accountNumber) {
        return AccountBalance.newBuilder()
                .setAccountNumber(accountNumber)
                .setBalance(AccountRepository.getBalance(accountNumber))
                .build();
    }


}
