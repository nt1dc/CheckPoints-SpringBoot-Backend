package se.itmo.checkpointsbackend.model;

import org.springframework.stereotype.Component;
import se.itmo.checkpointsbackend.dto.EntryReqDto;
import se.itmo.checkpointsbackend.entities.Entry;
import se.itmo.checkpointsbackend.exeptions.NotIncludedInTheRangeException;

import java.util.Date;

@Component
public class AreaChecker {

    public Entry checkEntry(EntryReqDto entryReq, long date) throws NotIncludedInTheRangeException {
        double x = entryReq.getX();
        double y = entryReq.getY();
        double r = entryReq.getR();

        if (r > 5 || r < -3) {
            throw new NotIncludedInTheRangeException("r in (-3;5)");
        } else if (y < -3 || y > 3) {
            throw new NotIncludedInTheRangeException("y in (-3;3)");
        } else if (x > 5 || x < -3) {
            throw new NotIncludedInTheRangeException("x in (-3;5)");
        }
        boolean entryValue = checkGetInto(entryReq.getX(), entryReq.getY(), entryReq.getR());
        long now = new Date().getTime();
        return new Entry(entryReq.getX(), entryReq.getY(), entryReq.getR(), entryValue, (now - date));
    }


    public boolean checkGetInto(double x, double y, double r) {
        return checkIntoTriangle(x, y, r) || checkIntoRectangle(x, y, r) || checkIntoCircle(x, y, r);
    }

    public boolean checkIntoTriangle(double x, double y, double r) {
        return 2 * x - y <= r && x >= 0 && x <= r / 2 && y <= 0 && y <= r;
    }

    public boolean checkIntoRectangle(double x, double y, double r) {
        return (x <= 0 && x >= -r / 2) && (y <= 0 && y >= -r / 2);
    }

    public boolean checkIntoCircle(double x, double y, double r) {
        return ((x <= r / 2 && x >= 0) && (y >= 0 && y <= r / 2) && (x * x + y * y) <= r * r);
    }
}