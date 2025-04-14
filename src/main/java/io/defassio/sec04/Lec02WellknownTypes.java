package io.defassio.sec04;

import com.google.protobuf.Int32Value;
import com.google.protobuf.Timestamp;
import io.defassio.proto.models.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class Lec02WellknownTypes {

    private static final Logger log = LoggerFactory.getLogger(Lec02WellknownTypes.class);

    public static void main(String[] args) {
        Sample sample = Sample.newBuilder()
                .setAge(Int32Value.of(12))
                .setLoginTime(Timestamp.newBuilder().setSeconds(Instant.now().getEpochSecond()).build())
                .build();

        log.info("time -> {} age -> {}", sample.getLoginTime(), sample.getAge());

    }

}
