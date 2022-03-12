package com.codereview.counter.utils;

import com.codereview.counter.exception.WrongFormatException;

public class IPParser {

    private static final String DOT_REGEX = "\\.";

//    private static final String IP_REGEX = "^((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)(\\.|$)){4}\\b";

    /**
     * Parse ip address to byte format in int presentation
     *
     * @param ipAddress - ip format like 192.168.50.1
     * @return int
     */
    public int parseToInt(String ipAddress) {
        int ipHash = 0;
        String[] ipSubnets = ipAddress.split(DOT_REGEX);
        if (4 != ipSubnets.length) {
            throw new WrongFormatException("Wrong format of ip address: " + ipAddress);
        }
        for (int i = 0; i < ipSubnets.length; i++) {
            String byteStr = ipSubnets[i];
            int byteInt = Integer.parseInt(byteStr);
            ipHash += byteInt * (int) Math.pow(256, 3 - i);
        }

        return ipHash;
    }

}
