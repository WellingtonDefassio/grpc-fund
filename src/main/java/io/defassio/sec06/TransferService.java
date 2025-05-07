package io.defassio.sec06;

import io.defassio.proto.models.sec06.TransferRequest;
import io.defassio.proto.models.sec06.TransferResponse;
import io.defassio.proto.models.sec06.TransferServiceGrpc;
import io.grpc.stub.StreamObserver;

public class TransferService extends TransferServiceGrpc.TransferServiceImplBase {

    @Override
    public StreamObserver<TransferRequest> transfer(StreamObserver<TransferResponse> responseObserver) {

    }
}
