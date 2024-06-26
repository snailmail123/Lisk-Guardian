package com.evan.deadmansswitch.data.validator;


import com.evan.deadmansswitch.data.model.SendToRecipient;

import org.web3j.crypto.WalletUtils;

import java.math.BigDecimal;
import java.util.List;

public class CreateSwitchValidator {

    public void isValidListOfRecipients(List<SendToRecipient> sendToRecipientList, String userWalletAddress) {
        for (SendToRecipient sendToRecipient : sendToRecipientList) {
            String recipientAddress = sendToRecipient.getRecipientAddress();
            String amountLisk = sendToRecipient.getAmountLisk();
            String comments = sendToRecipient.getComments();

            if (!WalletUtils.isValidAddress(recipientAddress)) {
                throw new IllegalArgumentException("Invalid wallet address. Please try again.");
            }

            //EVM wallet addresses are case insensitive
            if (recipientAddress.equalsIgnoreCase(userWalletAddress)) {
                throw new IllegalArgumentException("Cannot send to your own address");
            }

            BigDecimal value;
            try {
                value = new BigDecimal(amountLisk);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Invalid amount LSK. Please try again.");
            }

            if (value.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Amount must be greater than 0. Please try again.");
            }

            if (comments.length() > 300) {
                throw new IllegalArgumentException("Comment must be less than 300 characters. Please try again.");
            }
        }
    }
}
