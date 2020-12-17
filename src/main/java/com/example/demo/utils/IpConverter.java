package com.example.demo.utils;


import org.springframework.stereotype.Component;

@Component
public class IpConverter {


    public long ipToLong(String ipAddress) {

        // ipAddressInArray[0] = 192
        String[] ipAddressInArray = ipAddress.split("\\.");

        long result = 0;
        for (int i = 0; i < ipAddressInArray.length; i++) {

            int power = 3 - i;
            int ip = Integer.parseInt(ipAddressInArray[i]);

            // 1. 192 * 256^3
            // 2. 168 * 256^2
            // 3. 1 * 256^1
            // 4. 2 * 256^0
            result += ip * Math.pow(256, power);

        }
        return result;
    }

    public String longToIp(long i) {

        return ((i >> 24) & 0xFF) +
                "." + ((i >> 16) & 0xFF) +
                "." + ((i >> 8) & 0xFF) +
                "." + (i & 0xFF);

    }
}
