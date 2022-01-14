package se.itmo.checkpointsbackend.exeprions;

public class NotIncludedInTheRangeException extends Exception{
    public NotIncludedInTheRangeException(String message) {
        super(message);
    }
}