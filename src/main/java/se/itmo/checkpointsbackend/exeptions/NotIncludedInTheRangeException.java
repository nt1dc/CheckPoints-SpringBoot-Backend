package se.itmo.checkpointsbackend.exeptions;

public class NotIncludedInTheRangeException extends Exception{
    public NotIncludedInTheRangeException(String message) {
        super(message);
    }
}