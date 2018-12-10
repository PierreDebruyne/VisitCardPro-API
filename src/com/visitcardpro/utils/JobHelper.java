package com.visitcardpro.utils;

import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;

public class JobHelper {
    public static String getCredentialParam(String credential, int pos) {
        if (credential == null) {
            return null;
        }
        String decodedCredential = null;
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(credential);
            decodedCredential = new String(decodedBytes, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        final StringTokenizer tokenizer = new StringTokenizer(decodedCredential, ":");
        String param1 = "";
        String param2 = "";
        if (tokenizer.hasMoreTokens()) {
            param1 = tokenizer.nextToken();
        } if (tokenizer.hasMoreTokens()) {
            param2 = tokenizer.nextToken();
        }
        if (pos == 1)
            return param1;
        return param2;
    }
}
