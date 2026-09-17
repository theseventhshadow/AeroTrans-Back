package com.aerotrans.ms_aerotrans_transfers.exception;

public class InvalidTransferStatusException extends RuntimeException {
    public InvalidTransferStatusException(String message) {
        super(message);
    }
}