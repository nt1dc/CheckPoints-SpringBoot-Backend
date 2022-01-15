package se.itmo.checkpointsbackend.model;

import org.springframework.stereotype.Component;
import se.itmo.checkpointsbackend.dto.EntryReqDto;
import se.itmo.checkpointsbackend.exeprions.NotIncludedInTheRangeException;
import se.itmo.checkpointsbackend.entities.Entry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class AreaChecker {
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public Entry checkEntry(EntryReqDto entryReq) throws NotIncludedInTheRangeException {
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
        long date = new Date().getTime();
        return new Entry(entryReq.getX(), entryReq.getY(), entryReq.getR(), entryValue, date);
        
    }


    public boolean checkGetInto(double x, double y, double r) {
        if (checkIntoTriangle(x, y, r) || checkIntoRectangle(x, y, r) || checkIntoCircle(x, y, r)) {
            return true;
        }
        return false;
    }

    public boolean checkIntoTriangle(double x, double y, double r) {
        if ((x >= 0 && x <= r / 2) && (y <= 0 && y >= -r / 2)) {
            double d = ((x - 0) * (-r / 2 - 0)) - ((0 - (r / 2)) * (y + r / 2));
            //(x - x1) * (y2 - y1) - (x2 - x1) * (y - y1) = 0
            if (d >= 0) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIntoRectangle(double x, double y, double r) {
        if ((x <= 0 && x >= -r) && (y <= 0 && y >= -r)) {
            return true;
        }
        return false;
    }

    public boolean checkIntoCircle(double x, double y, double r) {
        if (((x >= -r && x <= 0) && (y >= 0 && y <= r))) {
            if (((x * x + y * y) <= r * r)) {
                return true;
            }
        }
        return false;
    }
}