package org.koreait;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Calc {

    public static boolean debug = true;
    public static int runCallCount = 0;

    public static int run(String exp) {
        return  _run(exp, 0);
    }

    public static int _run(String exp, int depth) {
        runCallCount++;

        exp = exp.trim(); // 양 옆의 쓸데없는 공백 제거. " 20 " => "20" 단, 가운데의 공백은 건드리지 않는다.
        // 전처리 과정 - 괄호 제거
        exp = stripOuterBrackets(exp);  // 괄호제거 메서드

        // -(10 + 5) == -15 시작.
        // 만약에 -( 패턴이라면, 내가 갖고있는 코드는 해석할 수 없으므로. 해석할 수 있는 형태로 수정
//        if (isCaseMinusBracket(exp)) {
//            exp = exp.substring(1) + " * -1";
//        }
        int[] pos = null;
        while ((pos = findCaseMinusBracket(exp)) != null) {
            exp = changeMinusBracket(exp, pos[0], pos[1]);
        }
        exp = stripOuterBrackets(exp);

        if (debug) {
            System.out.print(" ".repeat(depth * 4));
            System.out.printf("exp(%d) : %s\n", runCallCount, exp);
        }


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
            // 5 - (1 + 5)에서 -를 + -로 바꾼다. => 5 + -(1 + 5)
            exp = exp.replaceAll("- ", "+ -");
            int splitPointIndex = findSplitPointIndex(exp);

            String firstExp = exp.substring(0, splitPointIndex);
            String secondExp = exp.substring(splitPointIndex + 1);

//            // (10 + 20) * 3 내가 만든 방법.
//            if (needToMulti) {
//                return Calc.run(firstExp) * Calc.run(secondExp);
//            }
//            return Calc.run(firstExp) + Calc.run(secondExp);

            // 선생님 방법.
            char operator = exp.charAt(splitPointIndex);
            exp = Calc._run(firstExp,depth + 1) + " " + operator + " " + Calc._run(secondExp, depth + 1);

            return Calc._run(exp, depth + 1);

        } else if (needToCompound) {
            String[] bits = exp.split(" \\+ ");
            String newExp = Arrays.stream(bits)
                    .mapToInt(e -> Calc._run(e, depth + 1))
                    .mapToObj(e -> e + "")
                    .collect(Collectors.joining(" + "));
            return _run(newExp, depth + 1);
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

    private static String changeMinusBracket(String exp, int startPos, int endPos) {
        String head = exp.substring(0, startPos);
        String body = "(" + exp.substring(startPos + 1, endPos + 1) + " * -1)";
        String tail = exp.substring(endPos + 1);

        exp = head + body + tail;

        return exp;
    }

    private static int[] findCaseMinusBracket(String exp) {
        for (int i = 0; i < exp.length() - 1; i++) {
            if (exp.charAt(i) == '-' && exp.charAt(i + 1) == '(') {
                // 발견

                int bracketsCount = 1;

                for (int j = i + 2; j < exp.length(); j++) {
                    char c = exp.charAt(j);

                    if (c == '(') {
                        bracketsCount++;
                    } else if (c == ')') {
                        bracketsCount--;
                    }

                    if (bracketsCount == 0) {
                        return new int[]{i, j};
                    }
                }
            }
        }
        return null;
    }

//    private static boolean isCaseMinusBracket(String exp) {
//        // -( 로 시작하는지 검별.
//        if(exp.startsWith("-(") == false) return false;
//
//        // 괄호로 감싸져 있는지 감별.
//        int bracketsCount = 0;
//
//        for (int i = 1; i < exp.length(); i++) {
//            char c = exp.charAt(i);
//
//            if (c == '(') {
//                bracketsCount++;
//            } else if (c == ')') {
//                bracketsCount--;
//            }
//            if (bracketsCount == 0) {
//                if(exp.length() -1 == i) return  true;
//            }
//
//        }
//
//        return true;
//    }

    private static int findSplitPointIndex(String exp) {
        int index = findSplitPointIndexBy(exp, '+');

        if (index >= 0) return index;

        return findSplitPointIndexBy(exp, '*');
    }

    private static int findSplitPointIndexBy(String exp, char findChar) {
        int bracketsCount = 0;

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (c == '(') {
                bracketsCount++;
            } else if (c == ')') {
                bracketsCount--;
            } else if (c == findChar) {
                if (bracketsCount == 0) return i;
            }
        }
        return -1;
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
//        int outerBracketsCount = 0;
//
//        while (exp.charAt(outerBracketsCount) == '(' && exp.charAt(exp.length() - 1 - outerBracketsCount) == ')') {
//            outerBracketsCount++;
//        }
//        if (outerBracketsCount == 0) return exp;


//        if (exp.charAt(0) == '(' && exp.charAt(exp.length() - 1) == ')') {
//            exp = exp.substring(1, exp.length() - 1);
//        }
//        return exp;

//        return exp.substring(outerBracketsCount, exp.length() - outerBracketsCount);
//    }
        if (exp.charAt(0) == '(' && exp.charAt(exp.length() - 1) == ')') {
            int bracketsCount = 0;

            for (int i = 0; i < exp.length(); i++) {
                if (exp.charAt(i) == '(') {
                    bracketsCount++;
                } else if (exp.charAt(i) == ')') {
                    bracketsCount--;
                }

                if (bracketsCount == 0) {
                    if (exp.length() == i + 1) {
                        return stripOuterBrackets(exp.substring(1, exp.length() - 1));
                    }

                    return exp;
                }
            }
        }

        return exp;
    }
}
