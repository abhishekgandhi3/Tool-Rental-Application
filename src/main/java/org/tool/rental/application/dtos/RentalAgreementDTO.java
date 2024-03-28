package org.tool.rental.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tool.rental.application.enums.ToolType;

import java.text.NumberFormat;
import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalAgreementDTO {

    private String toolCode;
    private ToolType toolType;
    private String brand;
    private int rentalDays;
    private String checkoutDate;
    private String dueDate;
    private String dailyRentalCharge;
    private int chargeDays;
    private String preDiscountCharge;
    private int discountPercent;
    private String discountAmount;
    private String finalCharge;

    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);

    public RentalAgreementDTO(String toolCode, ToolType toolType, String brand, int rentalDays, String checkoutDate, String dueDate, double dailyRentalCharge, int chargeDays, double preDiscountCharge, int discountPercent, double discountAmount, double finalCharge) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.dailyRentalCharge = currencyFormatter.format(dailyRentalCharge);
        this.chargeDays = chargeDays;
        this.preDiscountCharge = currencyFormatter.format(preDiscountCharge);
        this.discountPercent = discountPercent;
        this.discountAmount = currencyFormatter.format(discountAmount);
        this.finalCharge = currencyFormatter.format(finalCharge);
    }

    @Override
    public String toString() {
        return "Rental Agreement after Checkout is : \n" + "Tool Code = " + toolCode + "\n" + "Tool Type = " + toolType + "\n"
                + "Brand = " + brand + "\n" + "Rental Days = " + rentalDays + "\n" + "Checkout Date = " + checkoutDate + "\n"
                + "Due Date = " + dueDate + "\n" + "Daily Rental Charge = " + dailyRentalCharge + "\n" + "Charge Days = " + chargeDays + "\n"
                + "Pre Discount Charge = " + preDiscountCharge + "\n" + "Discount Percent = " + discountPercent + "%" + "\n" + "Discount Amount = "
                + discountAmount + "\n" + "Final Charge = " + finalCharge + "\n";
    }
}
