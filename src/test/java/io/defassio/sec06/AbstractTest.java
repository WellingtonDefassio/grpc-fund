package io.defassio.sec06;

import io.defassio.common.AbstractChannelTest;
import io.defassio.common.GrpcServer;
import io.defassio.proto.models.sec06.BankServiceGrpc;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class AbstractTest extends AbstractChannelTest {

    private final GrpcServer grpcServer = GrpcServer.create(new BankService());
    protected BankServiceGrpc.BankServiceStub stub;
    protected BankServiceGrpc.BankServiceBlockingStub blockingStub;


    @BeforeAll
    public void setup() {
        this.grpcServer.start();
        this.stub = BankServiceGrpc.newStub(channel);
        this.blockingStub = BankServiceGrpc.newBlockingStub(channel);
    }

    @AfterAll
    public void stop() {
        this.grpcServer.stop();
    }
}
