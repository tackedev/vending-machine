package dto;

/**
 * @author tackedev
 */
public enum Money {

    TEN_THOUSAND_VND(10_000),
    TWENTY_THOUSAND_VND(20_000),
    FIFTY_THOUSAND_VND(50_000),
    ONE_HUNDRED_THOUSAND_VND(100_000),
    TWO_HUNDRED_THOUSAND_VND(200_000)
    ;

    private final int value;

    Money(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
