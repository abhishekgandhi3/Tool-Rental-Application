package org.tool.rental.application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tool.rental.application.enums.ToolType;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToolTypeCharges {

    private ToolType toolType;
    private double dailyCharge;
    private boolean weekdayCharge;
    private boolean weekendCharge;
    private boolean holidayCharge;
}
