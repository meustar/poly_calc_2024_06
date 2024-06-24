package org.koreait;

import java.util.ArrayList;
import java.util.List;

public class Calc {
    public static int run(String exp) {

        System.out.println(exp);

        exp = exp.replaceAll("- ", "+ -");

        System.out.println(exp);

        boolean needToPlus = exp.contains("+");
//        boolean needToMinus = exp.contains("-");

        List<String> numList = new ArrayList<>();
        numList = List.of(exp.split(" \\+ "));

//        if(needToPlus) {
//            bits = exp.split(" \\+ ");
//        } else if (needToMinus) {
//            bits = exp.split(" \\- ");
//        }

        int a = Integer.parseInt(bits[0]);
        int b = Integer.parseInt(bits[1]);
        int c = 0;

        if (bits.length > 2) {
            c = Integer.parseInt(bits[2]);
        }

        for (int i = 0; i < numList.size(); i++) {

        }


//        if (needToPlus) {
//            return a + b + c;
//        } else if (needToMinus) {
//            return a - b - c;
//        }

        return a + b + c;

//        throw new RuntimeException("해석 불가 : 올바른 계산식이 아닙니다.");

    }
}
