package com.evan.deadmansswitch.util;

import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

public class CustomGasProvider extends StaticGasProvider {
    public static final BigInteger GAS_LIMIT = BigInteger.valueOf(5_000_000);
    //Bare minimum gas price for tx to execute
    public static final BigInteger GAS_PRICE = BigInteger.valueOf(1_000_000_000L);

    public CustomGasProvider() {
        super(GAS_PRICE, GAS_LIMIT);
    }
}
