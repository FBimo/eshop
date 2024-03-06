package enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {

    PAYMENT_BY_BANK_TRANSFER("Payment by Bank Transfer"), PAYMENT_BY_VOUCHER("Payment by Voucher");

    private final String value;
    private PaymentMethod(String value){
        this.value = value;
    }
    public static boolean contains(String params){
        for(PaymentMethod method: PaymentMethod.values()){
            if(method.getValue().equals(params)){
                return true;
            }
        }
        return false;
    }
}
