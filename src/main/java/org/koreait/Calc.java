package org.koreait;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Calc {
    public static int run(String exp) {
        exp = exp.trim(); // 양 옆의 쓸데없는 공백 제거. " 20 " => "20" 단, 가운데의 공백은 건드리지 않는다.
        // 전처리 과정 - 괄호 제거
        exp = stripOuterBrackets(exp);  // 괄호제거 메서드

        if (!exp.contains(" ")) {
            // 공백이 없는경우. => 단일항이 들어오면 바로 리턴
            return Integer.parseInt(exp);
        }

        boolean needToMulti = exp.contains(" * ");
        boolean needToPlus = exp.contains(" + ") || exp.contains(" - ");
        // (20 + 20) + 20 == 60 의 ") +"를 기준으로 잘라야 할떄.
        boolean needToSplit = exp.contains("(") || exp.contains(")");

        boolean needToCompound = needToMulti && needToPlus;

        if (needToSplit) {
            int bracketsCount = 0;
            int splitPointIndex = -1;

            for(int i = 0; i < exp.length(); i++) {
                if(exp.charAt(i) == '(') {
                    bracketsCount++;
                } else if (exp.charAt(i)== ')') {
                    bracketsCount--;
                }
                if(bracketsCount == 0) {
                    splitPointIndex = i;
                    break;
                }
            }
            String firstExp = exp.substring(0, splitPointIndex + 1);
            String secondExp = exp.substring(splitPointIndex + 4);

            return Calc.run(firstExp) + Calc.run(secondExp);
        }

//        String[] bits;

        else if (needToCompound) {
            String[] bits; bits = exp.split(" \\+ ");
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
             String[] bits = exp.split(" \\+ ");
            for (int i = 0; i < bits.length; i++) {
                sum += Integer.parseInt(bits[i]);
            }
            return sum;
        } else if (needToMulti) {
             String[] bits = exp.split(" \\* ");
            for (int i = 0; i < bits.length; i++) {
                mul *= Integer.parseInt(bits[i]);
            }
            return mul;
        }
        throw new RuntimeException("해석 불가 : 올바른 계산식이 아닙니다.");

    }

    private static String stripOuterBrackets(String exp) {
//         // 내가 만든 방법.
//        for (int i = 0; i < exp.length(); i++) {
//            if (exp.charAt(i) == '(') {
//                exp = exp.replace("(", "");
//            } else if (exp.charAt(i) == ')') {
//                exp = exp.replace(")", "");
//            }
//        }

        // 선생님 방법.
        int outerBracketsCount = 0;

        while (exp.charAt(outerBracketsCount) == '(' && exp.charAt(exp.length() - 1 - outerBracketsCount) == ')') {
            outerBracketsCount++;
        }
        if (outerBracketsCount == 0) return exp;


//        if (exp.charAt(0) == '(' && exp.charAt(exp.length() - 1) == ')') {
//            exp = exp.substring(1, exp.length() - 1);
//        }
//        return exp;

        return exp.substring(outerBracketsCount, exp.length() - outerBracketsCount);
    }

}
