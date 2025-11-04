package com.expenses.summary.constants;

import java.math.BigDecimal;

public class ExpensesConstants {

    private ExpensesConstants() {

    }

    //common constants
    public static final BigDecimal ZERO_AMOUNT = BigDecimal.ZERO;
    public static final Long LONG_ZER0 = 0L;


    //Header Constants
    public static final String X_USER_INFO = "x-user-info";
    public static final String X_USER_GROUPS = "x-user-groups";
    public static final String X_USER_UUID = "x-user-uuid";

    //Pattern Matching
    public static final String SAFE_STRING_PATTERN = "^[\\w@.\\-#$&*]+$";
    public static final String SAFE_STRING_ALPHA_NUMERICS = "^[\\w@.-]+$";

    public static final String UUID = "uuid";

}
