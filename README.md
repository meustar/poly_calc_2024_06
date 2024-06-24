# 목표
- 목표 : 3 * 1 + (1 - (4 * 1 - (1 - 1)))

1. ```java
   
    @Test
    @DisplayName("50 - 30 == 20")
    void t5() {
        assertThat(Calc.run("50 - 30")).isEqualTo(20);
    }
   ```

2. ```java
   
    @Test
    @DisplayName("10 + 20 + 30 == 60")
    void t8() {
        assertThat(Calc.run("10 + 20 + 30")).isEqualTo(60);
    }
   ```
   
3. ```java
    @Test
    @DisplayName("10 - 20 + 30 == 20")
    void t9() {
        assertThat(Calc.run("10 - 20 + 30")).isEqualTo(20);
    }
    ```


