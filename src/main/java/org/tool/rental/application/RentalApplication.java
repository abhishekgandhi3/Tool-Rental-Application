package org.tool.rental.application;

import org.tool.rental.application.dtos.RentalAgreementDTO;
import org.tool.rental.application.entity.RentalAgreement;
import org.tool.rental.application.entity.Tool;
import org.tool.rental.application.entity.ToolTypeCharges;
import org.tool.rental.application.enums.ToolType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static org.tool.rental.application.util.RentalUtil.convertToDto;
import static org.tool.rental.application.util.RentalUtil.validateCheckoutRequest;

public class RentalApplication {

    private static final Map<String, Tool> tools = new HashMap<>();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");


    static {
        loadStaticData();
    }

    public static void main(String[] args) {
        RentalAgreementDTO rentalAgreementDTO = checkoutTool("LADW", 30, "07/02/2020",10);
        System.out.println(rentalAgreementDTO);
    }

    private static void loadStaticData() {
        ToolTypeCharges ladderCharges = new ToolTypeCharges(ToolType.LADDER, 1.99, true, true, false);
        ToolTypeCharges chainsawCharges = new ToolTypeCharges(ToolType.CHAINSAW, 1.49, true, false, true);
        ToolTypeCharges jackhammerCharges = new ToolTypeCharges(ToolType.JACK_HAMMER, 2.99, true, false, false);

        tools.put("CHNS", new Tool("CHNS", chainsawCharges, "Stihl"));
        tools.put("LADW", new Tool("LADW", ladderCharges, "Werner"));
        tools.put("JAKD", new Tool("JAKD", jackhammerCharges, "DeWalt"));
        tools.put("JAKR", new Tool("JAKR", jackhammerCharges, "Ridgid"));
    }

    private static Tool getToolByCode(String toolCode) {
        return tools.get(toolCode);
    }

    /**
     *
     * @param toolCode
     * @param rentalDays
     * @param checkoutDateStr
     * @param discount
     * @return the rental agreement for the specific tool.
     */
    public static RentalAgreementDTO checkoutTool(String toolCode, int rentalDays, String checkoutDateStr, int discount) {
        validateCheckoutRequest(rentalDays, discount, toolCode);
        Tool tool = getToolByCode(toolCode);
        if (tool == null) {
            throw new IllegalArgumentException("Tool with code : " + toolCode + " not found.");
        }
        LocalDate checkoutDate = LocalDate.parse(checkoutDateStr, formatter);
        RentalAgreement rentalAgreement = new RentalAgreement(tool, rentalDays, checkoutDate, discount);
        return convertToDto(rentalAgreement, discount);
    }

}
