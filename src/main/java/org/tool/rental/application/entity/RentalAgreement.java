package org.tool.rental.application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.time.LocalDate;

import static org.tool.rental.application.util.RentalUtil.calculateChargeDays;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalAgreement {
    private Tool tool;
    private int rentalDays;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private int chargeDays;
    private double preDiscountCharge;
    private double discountAmount;
    private double finalCharge;

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");


    public RentalAgreement(Tool tool, int rentalDays, LocalDate checkoutDate, int discountPercent) {
        this.tool = tool;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = checkoutDate.plusDays(rentalDays);
        this.chargeDays = calculateChargeDays(checkoutDate,dueDate,tool);
        this.preDiscountCharge = tool.getToolTypeCharges().getDailyCharge() * this.chargeDays;
        this.discountAmount = Double.parseDouble(decimalFormat.format(this.preDiscountCharge * (discountPercent / 100.0)));
        this.finalCharge = Double.parseDouble(decimalFormat.format(this.preDiscountCharge - this.discountAmount));
    }

}
