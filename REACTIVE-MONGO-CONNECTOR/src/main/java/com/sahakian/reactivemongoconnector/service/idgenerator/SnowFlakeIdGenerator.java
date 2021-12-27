package com.sahakian.reactivemongoconnector.service.idgenerator;

import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
public class SnowFlakeIdGenerator implements IdGenerator, InitializingBean {
    private static final long DEFAULT_CUSTOM_EPOCH = Timestamp.valueOf(LocalDateTime.now()).getTime();
    private static final int NODE_ID_BITS = 10;
    private static final int SEQUENCE_BITS = 12;

    private static final long maxNodeId = (1L << NODE_ID_BITS) - 1;
    private static final long maxSequence = (1L << SEQUENCE_BITS) - 1;

    private long nodeId;
    private long customEpoch;

    private volatile long lastTimestamp = -1L;
    private volatile long sequence = 0L;

    @Override
    public void afterPropertiesSet() {
        this.nodeId = createNodeId();
        this.customEpoch = DEFAULT_CUSTOM_EPOCH;
    }

    @Override
    public String getUuid() {
        long id = nextId();

        return String.valueOf(id);
    }

    public synchronized long nextId() {
        long currentTimestamp = timestamp();

        if (currentTimestamp < lastTimestamp) {
            throw new IllegalStateException("Invalid System Clock!");
        }
        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & maxSequence;
            if (sequence == 0) {
                currentTimestamp = waitNextMillis(currentTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = currentTimestamp;

        return currentTimestamp << (NODE_ID_BITS + SEQUENCE_BITS)
               | (nodeId << SEQUENCE_BITS)
               | sequence;
    }

    public long[] parseId(long id) {
        var timestamp = (id >> (NODE_ID_BITS + SEQUENCE_BITS)) + customEpoch;
        var nodeId = (id & (((1L << NODE_ID_BITS) - 1) << SEQUENCE_BITS)) >> SEQUENCE_BITS;
        var sequence = id & ((1L << SEQUENCE_BITS) - 1);

        return new long[]{timestamp, nodeId, sequence};
    }


    private long timestamp() {
        return Instant.now().toEpochMilli() - customEpoch;
    }

    private long waitNextMillis(long currentTimestamp) {
        while (currentTimestamp == lastTimestamp) {
            currentTimestamp = timestamp();
        }
        return currentTimestamp;
    }

    private long createNodeId() {
        long nodeId;
        try {
            var sb = new StringBuilder();
            var networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                var networkInterface = networkInterfaces.nextElement();
                var mac = networkInterface.getHardwareAddress();
                if (mac != null) {
                    for (byte macPort : mac) {
                        sb.append(String.format("%02X", macPort));
                    }
                }
            }
            nodeId = sb.toString().hashCode();
        } catch (Exception ex) {
            nodeId = (new SecureRandom().nextInt());
        }
        nodeId = nodeId & maxNodeId;
        return nodeId;
    }
}
