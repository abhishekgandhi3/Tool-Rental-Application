package org.tool.rental.application;

import org.junit.Test;
import org.tool.rental.application.dtos.RentalAgreementDTO;
import org.tool.rental.application.enums.ToolType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class RentalApplicationTest {

    @Test
    public void testCheckoutTool1() {
        assertThrows(IllegalArgumentException.class,
                () -> RentalApplication.checkoutTool("JAKR", 5, "09/03/15", 101));
    }

    @Test
    public void testCheckoutTool2() {

        RentalAgreementDTO rentalAgreementDTO = RentalApplication.checkoutTool("LADW", 3, "07/02/20", 10);

        assertEquals("LADW", rentalAgreementDTO.getToolCode());
        assertEquals(ToolType.LADDER.toString(), rentalAgreementDTO.getToolType().name());
        assertEquals("Werner", rentalAgreementDTO.getBrand());
        assertEquals(3, rentalAgreementDTO.getRentalDays());
        assertEquals("07/02/20", rentalAgreementDTO.getCheckoutDate());
        assertEquals("07/05/20", rentalAgreementDTO.getDueDate());
        assertEquals("$1.99", rentalAgreementDTO.getDailyRentalCharge());
        assertEquals(2, rentalAgreementDTO.getChargeDays());
        assertEquals("$3.98", rentalAgreementDTO.getPreDiscountCharge());
        assertEquals(10, rentalAgreementDTO.getDiscountPercent());
        assertEquals("$0.40", rentalAgreementDTO.getDiscountAmount());
        assertEquals("$3.58", rentalAgreementDTO.getFinalCharge());
    }


    @Test
    public void testCheckoutTool3() {
        RentalAgreementDTO rentalAgreementDTO = RentalApplication.checkoutTool("CHNS", 5, "07/02/15", 25);

        assertEquals("CHNS", rentalAgreementDTO.getToolCode());
        assertEquals(ToolType.CHAINSAW.toString(), rentalAgreementDTO.getToolType().name());
        assertEquals("Stihl", rentalAgreementDTO.getBrand());
        assertEquals(5, rentalAgreementDTO.getRentalDays());
        assertEquals("07/02/15", rentalAgreementDTO.getCheckoutDate());
        assertEquals("07/07/15", rentalAgreementDTO.getDueDate());
        assertEquals("$1.49", rentalAgreementDTO.getDailyRentalCharge());
        assertEquals(3, rentalAgreementDTO.getChargeDays());
        assertEquals("$4.47", rentalAgreementDTO.getPreDiscountCharge());
        assertEquals(25, rentalAgreementDTO.getDiscountPercent());
        assertEquals("$1.12", rentalAgreementDTO.getDiscountAmount());
        assertEquals("$3.35", rentalAgreementDTO.getFinalCharge());
    }

    @Test
    public void testCheckoutTool4() {
        RentalAgreementDTO rentalAgreementDTO = RentalApplication.checkoutTool("JAKD", 6, "09/03/15", 0);

        assertEquals("JAKD", rentalAgreementDTO.getToolCode());
        assertEquals(ToolType.JACK_HAMMER.toString(), rentalAgreementDTO.getToolType().name());
        assertEquals("DeWalt", rentalAgreementDTO.getBrand());
        assertEquals(6, rentalAgreementDTO.getRentalDays());
        assertEquals("09/03/15", rentalAgreementDTO.getCheckoutDate());
        assertEquals("09/09/15", rentalAgreementDTO.getDueDate());
        assertEquals("$2.99", rentalAgreementDTO.getDailyRentalCharge());
        assertEquals(3, rentalAgreementDTO.getChargeDays());
        assertEquals("$8.97", rentalAgreementDTO.getPreDiscountCharge());
        assertEquals(0, rentalAgreementDTO.getDiscountPercent());
        assertEquals("$0.00", rentalAgreementDTO.getDiscountAmount());
        assertEquals("$8.97", rentalAgreementDTO.getFinalCharge());
    }

    @Test
    public void testCheckoutTool5() {
        RentalAgreementDTO rentalAgreementDTO = RentalApplication.checkoutTool("JAKR", 9, "07/02/15", 0);

        assertEquals("JAKR", rentalAgreementDTO.getToolCode());
        assertEquals(ToolType.JACK_HAMMER.toString(), rentalAgreementDTO.getToolType().name());
        assertEquals("Ridgid", rentalAgreementDTO.getBrand());
        assertEquals(9, rentalAgreementDTO.getRentalDays());
        assertEquals("07/02/15", rentalAgreementDTO.getCheckoutDate());
        assertEquals("07/11/15", rentalAgreementDTO.getDueDate());
        assertEquals("$2.99", rentalAgreementDTO.getDailyRentalCharge());
        assertEquals(5, rentalAgreementDTO.getChargeDays());
        assertEquals("$14.95", rentalAgreementDTO.getPreDiscountCharge());
        assertEquals(0, rentalAgreementDTO.getDiscountPercent());
        assertEquals("$0.00", rentalAgreementDTO.getDiscountAmount());
        assertEquals("$14.95", rentalAgreementDTO.getFinalCharge());
    }

    @Test
    public void testCheckoutTool6() {
        RentalAgreementDTO rentalAgreementDTO = RentalApplication.checkoutTool("JAKR", 4, "07/02/20", 50);

        assertEquals("JAKR", rentalAgreementDTO.getToolCode());
        assertEquals(ToolType.JACK_HAMMER.toString(), rentalAgreementDTO.getToolType().name());
        assertEquals("Ridgid", rentalAgreementDTO.getBrand());
        assertEquals(4, rentalAgreementDTO.getRentalDays());
        assertEquals("07/02/20", rentalAgreementDTO.getCheckoutDate());
        assertEquals("07/06/20", rentalAgreementDTO.getDueDate());
        assertEquals("$2.99", rentalAgreementDTO.getDailyRentalCharge());
        assertEquals(1, rentalAgreementDTO.getChargeDays());
        assertEquals("$2.99", rentalAgreementDTO.getPreDiscountCharge());
        assertEquals(50, rentalAgreementDTO.getDiscountPercent());
        assertEquals("$1.50", rentalAgreementDTO.getDiscountAmount());
        assertEquals("$1.49", rentalAgreementDTO.getFinalCharge());
    }

}
