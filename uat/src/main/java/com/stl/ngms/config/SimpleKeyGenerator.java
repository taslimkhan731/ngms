package com.stl.ngms.config;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @author randash
 *
 */
public class SimpleKeyGenerator implements KeyGenerator {

    @Override
    public Key generateKey() {
        return new SecretKeySpec("RAN9658VIR6020DASH48".getBytes(), 0, "RAN9658VIR6020DASH48".getBytes().length, "DES");
    }
}