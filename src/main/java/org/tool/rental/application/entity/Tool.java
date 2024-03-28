package org.tool.rental.application.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tool {
    private String toolCode;
    private ToolTypeCharges toolTypeCharges;
    private String brand;
}
