package com.zcc.test;

/**
 * @ProjectName: ZeusCode
 * @ClassName: com.zcc.test
 * @author: zcc
 * @date: 2022/8/16 19:16
 * @version:
 * @Describe:
 */
public class TEst {
    public static void main(String[] args) {
        System.out.println(isIdCardNumber("320321199610050413"));
    }
    /**
     * 身份证号码验证
     * @param idCardNumber 身份证号码
     * @return boolean
     */
    public static boolean isIdCardNumber(String idCardNumber) {
        if (idCardNumber == null || "".equals(idCardNumber)) {
            return false;
        }
        //正则表达式
        String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        boolean matches = idCardNumber.matches(regularExpression);
        //判断第18位校验值
        if (matches) {
            if (idCardNumber.length() == 18) {
                try {
                    char[] charArray = idCardNumber.toCharArray();
                    //前十七位加权因子
                    int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                    //这是除以11后，可能产生的11位余数对应的验证码
                    String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
                    int sum = 0;
                    for (int i = 0; i < idCardWi.length; i++) {
                        int current = Integer.parseInt(String.valueOf(charArray[i]));
                        int count = current * idCardWi[i];
                        sum += count;
                    }
                    char idCardLast = charArray[17];
                    int idCardMod = sum % 11;
                    if (idCardY[idCardMod].equals(String.valueOf(idCardLast).toUpperCase())) {
                        return true;
                    } else {
                        return false;
                    }
                } catch (Exception e) {
                    System.out.println("身份证号规则校验异常，err={"+e.getMessage()+"}");
                    return false;
                }
            }
        }
        return matches;
    }
}
