package org.koreait;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Calc {
    public static int run(String exp) {

        if (!exp.contains(" ")) {
            // 공백이 없는경우. => 단일항이 들어오면 바로 리턴
            return Integer.parseInt(exp);
        }

        boolean needToMulti = exp.contains("*");
        boolean needToPlus = exp.contains("+");

        boolean needToCompound = needToMulti && needToPlus;

        String[] bits;
//        int num = 0;

        if (needToCompound) {
            bits = exp.split(" \\+ ");
//            for (int i = 0; i < bits.length; i++) {
//                num += Integer.parseInt(bits[i]);
//                if (bits[i].contains("*")) {
//                    run(bits[i]);
//                }
//            }
//            return Integer.parseInt(bits[0]) + Integer.parseInt(bits[1]) + run(bits[2]);
            String newExp = Arrays.stream(bits)
                    .mapToInt(Calc::run)
                    .mapToObj(e -> e + "")
                    .collect(Collectors.joining(" + "));
            return run(newExp);
        }

        exp = exp.replaceAll("- ", "+ -");
        exp = exp.replaceAll("\\(", "");
        exp = exp.replaceAll("\\)", "");

        int sum = 0;
        int mul = 1;

         if (exp.contains("+")) {
            bits = exp.split(" \\+ ");
            for (int i = 0; i < bits.length; i++) {
                sum += Integer.parseInt(bits[i]);
            }
            return sum;
        } else if (exp.contains("*")) {
            bits = exp.split(" \\* ");
            for (int i = 0; i < bits.length; i++) {
                mul *= Integer.parseInt(bits[i]);
            }
            return mul;
        }
        throw new RuntimeException("해석 불가 : 올바른 계산식이 아닙니다.");

    }
}
