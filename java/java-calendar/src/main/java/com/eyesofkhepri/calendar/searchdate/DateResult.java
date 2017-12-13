package com.eyesofkhepri.calendar.searchdate;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import lombok.Data;

@Data
public class DateResult {
    private String startDate;
    private String endDate;

    public DateResult(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
