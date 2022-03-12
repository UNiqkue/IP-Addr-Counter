package com.codereview.counter.parser;

import com.codereview.counter.exception.WrongFormatException;

import java.util.Objects;

public class IPParser {

    private static final String DOT_REGEX = "\\.";

//    регулярное на данный момент не используется, т.к. замедляет время выполнения примерно в 3 раза
//    private static final String IP_REGEX = "^((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)(\\.|$)){4}\\b";

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
        int ipHash = 0;
        String[] ipOctets = ipAddress.split(DOT_REGEX);
        if (4 != ipOctets.length) {
            throw new WrongFormatException("Wrong format of ip address: " + ipAddress);
        }
        for (int i = 0; i < ipOctets.length; i++) {
            String byteStrOctet = ipOctets[i];
            int byteIntOctet = Integer.parseInt(byteStrOctet);
            ipHash += byteIntOctet * (int) Math.pow(256, 3 - i);
        }

        return ipHash;
    }

}
