package com.varun.mobile.insight.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class can be replaced by a class which handles encryption and decryption.
 * For now, I am using basic encoding to store password.
 */
public class MIEncoder {

    Logger logger = Logger.getLogger(MIEncoder.class.getName());

    public static MIEncoder encryptor;

    private MIEncoder() {
        //made constructor private to hide initialization from another class.
    }

    public static MIEncoder getInstance() {
        if (encryptor == null) {
            encryptor = new MIEncoder();
        }
        return encryptor;
    }

    public String encode(String input) {
        logger.log(Level.INFO, "Encoding text input");
        return Base64.getEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }

    public String decode(String input) {
        logger.log(Level.INFO, "Decoding text input");
        return new String(Base64.getDecoder().decode(input));
    }

}