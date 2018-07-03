package org.web3j.tx;

import java.io.IOException;
import java.math.BigInteger;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.tx.response.TransactionReceiptProcessor;

/**
 * TransactionManager implementation for using an Ethereum node to transact.
 *
 * <p><b>Note</b>: accounts must be unlocked on the node for transactions to be successful.
 */
public class ClientTransactionManager extends TransactionManager {

    private final Web3j web3j;

    public ClientTransactionManager(
            Web3j web3j, String fromAddress) {
        super(web3j, fromAddress);
        this.web3j = web3j;
    }

    public ClientTransactionManager(
            Web3j web3j, String fromAddress, int attempts, int sleepDuration) {
        super(web3j, attempts, sleepDuration, fromAddress);
        this.web3j = web3j;
    }

    public ClientTransactionManager(
            Web3j web3j, String fromAddress,
            TransactionReceiptProcessor transactionReceiptProcessor) {
        super(transactionReceiptProcessor, fromAddress);
        this.web3j = web3j;
    }

    @Override
    public EthSendTransaction sendTransaction(
            BigInteger gasPrice, BigInteger gasLimit, String to,
            String data, BigInteger value)
            throws IOException {

        // Note: useless for use, just for compile
        Transaction transaction = new Transaction(
                to, BigInteger.valueOf(1), 1000000, 99, 0, data);

        return web3j.ethSendTransaction(transaction)
                .send();
    }
}
