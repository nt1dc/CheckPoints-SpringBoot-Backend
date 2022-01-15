package se.itmo.checkpointsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntryReqDto {
    private double x;
    private double y;
    private double r;
}
