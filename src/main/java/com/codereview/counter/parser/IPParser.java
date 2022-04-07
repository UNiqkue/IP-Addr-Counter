package com.codereview.counter.parser;

import com.codereview.counter.exception.WrongFormatException;

import java.util.Objects;

public class IPParser {

    private static final String DOT_REGEX = "\\.";

    private static final int IP_OCTETS_COUNT = 4;

    private static final int SHIFT_FOR_FIRST_OCTET = 24;

    private static final int SHIFT_FOR_SECOND_OCTET = 16;

    private static final int SHIFT_FOR_THIRD_OCTET = 8;

    /**
     * Parse ip address to byte format in int presentation
     *
     * @param ipAddress - ip format like 192.168.50.1
     * @return int
     */
    public int parseToInt(String ipAddress) {
        if (Objects.isNull(ipAddress) || ipAddress.trim().isEmpty()) {
            throw new WrongFormatException("The ip address can not be null or empty");
        }

        String[] ipOctets = ipAddress.split(DOT_REGEX);

        if (IP_OCTETS_COUNT != ipOctets.length) {
            throw new WrongFormatException("Wrong format of ip address: " + ipAddress);
        }

        return Integer.parseInt(ipOctets[0]) << SHIFT_FOR_FIRST_OCTET |
                Integer.parseInt(ipOctets[1]) << SHIFT_FOR_SECOND_OCTET |
                Integer.parseInt(ipOctets[2]) << SHIFT_FOR_THIRD_OCTET |
                Integer.parseInt(ipOctets[3]);
    }

}
