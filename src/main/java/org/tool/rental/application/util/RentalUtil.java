package org.tool.rental.application.util;

import org.apache.commons.lang3.StringUtils;
import org.tool.rental.application.dtos.RentalAgreementDTO;
import org.tool.rental.application.entity.RentalAgreement;
import org.tool.rental.application.entity.Tool;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentalUtil {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    /**
     *
     * @param checkoutDate
     * @param dueDate
     * @param tool
     * @return it will return the charges days for  tools.
     */
    public static int calculateChargeDays(LocalDate checkoutDate, LocalDate dueDate, Tool tool) {
        int chargeDays = 0;
        LocalDate currentDate = checkoutDate.plusDays(1);
        LocalDate dueDates = dueDate;
        while (!currentDate.isAfter(dueDates)) {
            if (isChargeableDay(currentDate,tool)) {
                chargeDays++;
            }
            currentDate = currentDate.plusDays(1);
        }
        return chargeDays;
    }

    /**
     *
     * @param date
     * @param tool
     * @return it will check the holiday and weekend days.
     */
    private static boolean isChargeableDay(LocalDate date, Tool tool) {
        boolean isWeekDay = isWeekDay(date);
        boolean isHoliday = isHoliday(date);
        if (isWeekDay) {
            if (isHoliday && !tool.getToolTypeCharges().isHolidayCharge()) {
                return false;
            }
            return tool.getToolTypeCharges().isWeekdayCharge();
        } else {
            return tool.getToolTypeCharges().isWeekendCharge();
        }
    }

    /**
     *
     * @param date
     * @return the boolean value for weekday
     */
    private static boolean isWeekDay(LocalDate date) {
        return !date.getDayOfWeek().equals(DayOfWeek.SATURDAY) && !date.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

    /**
     *
     * @param date
     * @return the boolean value for holiday.
     */
    private static boolean isHoliday(LocalDate date) {
        // Labor Day (First Monday in September) as holiday
        boolean isLaborDay = (date.getMonthValue() == 9 && date.getDayOfWeek().equals(DayOfWeek.MONDAY) && date.getDayOfMonth() <= 7);
        if (isLaborDay) {
            return true;
        }
        // Independence Day 4th July (on weekdays) as holiday
        // If Independence Day is on Saturday then Friday is holiday
        // If Independence Day is on Sunday then Monday is holiday
        boolean isWeekDay = isWeekDay(date);
        boolean isIndependenceDay = (date.getMonthValue() == 7 && date.getDayOfMonth() == 4 && isWeekDay) ||
                (date.getMonthValue() == 7 && date.getDayOfMonth() == 3 && date.getDayOfWeek().equals(DayOfWeek.FRIDAY)) ||
                (date.getMonthValue() == 7 && date.getDayOfMonth() == 5 && date.getDayOfWeek().equals(DayOfWeek.MONDAY));
        return isIndependenceDay;
    }

    /**
     *
     * @param rentalAgreement
     * @param discount
     * @return the rental agreement
     */
    public static RentalAgreementDTO convertToDto(RentalAgreement rentalAgreement, int discount) {
        return new RentalAgreementDTO(rentalAgreement.getTool().getToolCode(), rentalAgreement.getTool().getToolTypeCharges().getToolType(),
                rentalAgreement.getTool().getBrand(), rentalAgreement.getRentalDays(), rentalAgreement.getCheckoutDate().format(formatter),
                rentalAgreement.getDueDate().format(formatter), rentalAgreement.getTool().getToolTypeCharges().getDailyCharge(),
                rentalAgreement.getChargeDays(), rentalAgreement.getPreDiscountCharge(), discount,
                rentalAgreement.getDiscountAmount(), rentalAgreement.getFinalCharge());
    }

    /**
     *
     * @param rentalDays
     * @param discountPercentage
     * @param toolcode
     */
    public static void validateCheckoutRequest(int rentalDays, int discountPercentage, String toolcode) {
        if (rentalDays <= 0) {
            throw new IllegalArgumentException("Rental day count must be greater than 0.");
        }
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Discount percent must be between 0 and 100.");
        }
        if (StringUtils.isBlank(toolcode)) {
            throw new IllegalArgumentException("Tool code must be provided");
        }
    }

}
