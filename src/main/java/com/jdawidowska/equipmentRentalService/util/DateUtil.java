package com.jdawidowska.equipmentRentalService.util;

import java.sql.Date;

public class DateUtil {

    public static Date getCurrentDate(){
        return new Date(System.currentTimeMillis());
    }
}