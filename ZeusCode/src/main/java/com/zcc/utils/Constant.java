package com.zcc.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @ProjectName: ZeusCode
 * @ClassName: com.zcc.utils
 * @author: zcc
 * @date: 2022/8/30 14:36
 * @version:
 * @Describe:
 */
public class Constant {
    public enum DateStr {
        YEAR_MONTH("yyyy-MM"),
        YEAR_MONTH_DAY("yyyy-MM-dd"),
        YEAR_MONTH_DAY_TIME("yyyy-MM-dd HH:mm:ss");
        private String pattern;

        DateStr(String pattern) {
            this.pattern = pattern;
        }

        public String getPattern() {
            return pattern;
        }
    }

    public enum NumberEnum {
        ZERO(0, "0"), ONE(1, "1"), TWO(2, "2"),
        THREE(3, "3"), FOUR(4, "4"),
        FIVE(5, "5"), SIX(6, "6"), SEVEN(7, "7"),
        XLS_MAX_ROW(65535, "65535"),
        SYSTEM_USER_ID(999999999, "999999999"),
        SYSTEM_COMPANY_ID(999999999, "999999999");
        private int number;
        private String numberStr;

        private NumberEnum(int number, String numberStr) {
            this.number = number;
            this.numberStr = numberStr;
        }

        public int getNumber() {
            return number;
        }

        public String getNumberStr() {
            return numberStr;
        }

        /**
         * 通过value取枚举
         *
         * @param number
         * @return
         */
        public static NumberEnum getTypeByValue(String number) {
            if (StringUtils.isEmpty(number)) {
                return null;
            }

            int valueKey = Integer.parseInt(number);

            for (NumberEnum enums : NumberEnum.values()) {
                if (enums.getNumber() == valueKey) {
                    return enums;
                }
            }
            return null;
        }

    }
}
