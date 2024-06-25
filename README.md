# 목표
- 목표 : 3 * 1 + (1 - (4 * 1 - (1 - 1)))

1. ```java
   
    @Test
    @DisplayName("1 + 1 == 2")
    void t1() {
        assertThat(Calc.run("1 + 1")).isEqualTo(2);
    }
    ```
2. ```java
    @Test
    @DisplayName("2 + 1 == 3")
    void t2() {
        assertThat(Calc.run("2 + 1")).isEqualTo(3);
    }
    ```
3. ```java
    @Test
    @DisplayName("2 + 2 == 4")
    void t3() {
        assertThat(Calc.run("2 + 2")).isEqualTo(4);
    }
    ```
4. ```java
    @Test
    @DisplayName("1000 + 200 == 1200")
    void t4() {
        assertThat(Calc.run("1000 + 200")).isEqualTo(1200);
    }
    ```
5. ```java
    @Test
    @DisplayName("2 - 1 == 1")
    void t5() {
        assertThat(Calc.run("2 - 1")).isEqualTo(1);
    }
    ```
6. ```java
    @Test
    @DisplayName("3 - 1 == 2")
    void t6() {
        assertThat(Calc.run("3 - 1")).isEqualTo(2);
    }
    ```
7. ```java
    @Test
    @DisplayName("100 - 20 == 80")
    void t7() {
        assertThat(Calc.run("100 - 20")).isEqualTo(80);
    }
    ```
8. ```java
    @Test
    @DisplayName("10 + 20 + 30 == 60")
    void t8() {
        assertThat(Calc.run("10 + 20 + 30")).isEqualTo(60);
    }
    ```
9. ```java
    @Test
    @DisplayName("10 + 20 + 30 + 40 == 100")
    void t9() {
        assertThat(Calc.run("10 + 20 + 30 + 40")).isEqualTo(100);
    }
    ```
10. ```java
    @Test
    @DisplayName("10 - 20 + 30 == 20")
    void t10() {
        assertThat(Calc.run("10 - 20 + 30")).isEqualTo(20);
    }
    ```
11. ```java
    @Test
    @DisplayName("10 - 10 - 10 - 10 + 10 + 10 - 10 == -10")
    void t11() {
        assertThat(Calc.run("10 - 10 - 10 - 10 + 10 + 10 - 10")).isEqualTo(-10);
    }
    ```
12. ```java
    @Test
    @DisplayName("10 - 10 - 10 - 10 == -20")
    void t12() {
        assertThat(Calc.run("10 - 10 - 10 - 10")).isEqualTo(-20);
    }
    ```
13. ```java
    @Test
    @DisplayName("10 + 20 + 30 - 10 + 60 == 110")
    void t13() {
        assertThat(Calc.run("10 + 20 + 30 - 10 + 60")).isEqualTo(110);
    }
    ```
14. ```java
    @Test
    @DisplayName("10 * 10 == 100")
    void t14() {
        assertThat(Calc.run("10 * 10")).isEqualTo(100);
    }
    ```
15. ```java
    @Test
    @DisplayName("10 * -10 == -100")
    void t15() {
        assertThat(Calc.run("10 * -10")).isEqualTo(-100);
    }
    ```
16. ```java
    @Test
    @DisplayName("10 * 10 * 10 == 1000")
    void t16() {
        assertThat(Calc.run("10 * 10 * 10")).isEqualTo(1000);
    }
    ```
17. ```java
    @Test
    @DisplayName("10 + 5 * 2 == 20")
    void t17() {
        assertThat(Calc.run("10 + 5 * 2")).isEqualTo(20);
    }
    ```
18. ```java
    @Test
    @DisplayName("20 + 10 + 5 * 2 == 40")
    void t18() {
        assertThat(Calc.run("20 + 10 + 5 * 2")).isEqualTo(40);
    }
    ```
19. ```java
    @Test
    @DisplayName("10 * 20 + 10 + 5 * 2 == 220")
    void t19() {
        assertThat(Calc.run("10 * 20 + 10 + 5 * 2")).isEqualTo(220);
    }
    ```
20. ```java
    @Test
    @DisplayName("(10 + 20) == 30")
    void t20() {
        assertThat(Calc.run("(10 + 20)")).isEqualTo(30);
    }
    ```
21. ```java
    @Test
    @DisplayName("((10 + 20)) == 30")
    void t21() {
        assertThat(Calc.run("((10 + 20))")).isEqualTo(30);
    }
    ```
22. ```java
    @Test
    @DisplayName("(((10 + 20))) == 30")
    void t22() {
        assertThat(Calc.run("(((10 + 20)))")).isEqualTo(30);
    }
    ```
23. ```java
    @Test
    @DisplayName("(20 + 20) + 20 == 60")
    void t23() {
        assertThat(Calc.run("(20 + 20) + 20")).isEqualTo(60);
    }
    ```
24. ```java
    @Test
    @DisplayName("(10 + 20) * 3 == 90")
    void t24() {
    assertThat(Calc.run("(10 + 20) * 3")).isEqualTo(90);
    }
    ```
