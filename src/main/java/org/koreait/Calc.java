package org.koreait;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Calc {
    public static int run(String exp) {
        // 전처리 과정 - 괄호 제거
        exp = stripOuterBrackets(exp);  // 괄호제거 메서드

        if (!exp.contains(" ")) {
            // 공백이 없는경우. => 단일항이 들어오면 바로 리턴
            return Integer.parseInt(exp);
        }

        boolean needToMulti = exp.contains(" * ");
        boolean needToPlus = exp.contains(" + ") || exp.contains(" - ");

        boolean needToCompound = needToMulti && needToPlus;

        String[] bits;

        if (needToCompound) {
            bits = exp.split(" \\+ ");
            String newExp = Arrays.stream(bits)
                    .mapToInt(Calc::run)
                    .mapToObj(e -> e + "")
                    .collect(Collectors.joining(" + "));
            return run(newExp);
        }

        exp = exp.replaceAll("- ", "+ -");
//        exp = exp.replaceAll("\\(", "");
//        exp = exp.replaceAll("\\)", "");

        int sum = 0;
        int mul = 1;

         if (needToPlus) {
            bits = exp.split(" \\+ ");
            for (int i = 0; i < bits.length; i++) {
                sum += Integer.parseInt(bits[i]);
            }
            return sum;
        } else if (needToMulti) {
            bits = exp.split(" \\* ");
            for (int i = 0; i < bits.length; i++) {
                mul *= Integer.parseInt(bits[i]);
            }
            return mul;
        }
        throw new RuntimeException("해석 불가 : 올바른 계산식이 아닙니다.");

    }

    private static String stripOuterBrackets(String exp) {

        for (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) == '(') {
                exp = exp.replace("(", "");
            } else if (exp.charAt(i) == ')') {
                exp = exp.replace(")", "");
            }
        }
//        if (exp.charAt(0) == '(' && exp.charAt(exp.length() - 1) == ')') {
//            exp = exp.substring(1, exp.length() - 1);
//        }
        return exp;
    }
}
