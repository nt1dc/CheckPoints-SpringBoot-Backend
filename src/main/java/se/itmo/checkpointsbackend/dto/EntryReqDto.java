package se.itmo.checkpointsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class EntryReqDto {
    @Min(-4)
    @Max(4)
    @NotNull
    private double x;
    @NotNull
    @Min(-5)
    @Max(3)
    private double y;
    @NotNull
    @Min(1)
    @Max(4)
    private double r;
}
