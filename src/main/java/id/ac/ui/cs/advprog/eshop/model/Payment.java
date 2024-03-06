package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentMethod;
import enums.PaymentStatus;

import java.util.List;
import java.util.Map;

public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;

    public Payment(String id, String method, Map<String,String> paymentData){
        if(paymentData == null){
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.paymentData = paymentData;
        setMethod(method);
        _assignStatusBasedMethod();
    }
    public Payment(String id, String method, String status ,Map<String,String> paymentData){
        this(id, method, paymentData);
        setStatus(status);
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setMethod(String method) {
        if(PaymentMethod.contains(method)){
            this.method = method;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    private void _assignStatusBasedMethod(){
        if(this.method.equals(PaymentMethod.PAYMENT_BY_VOUCHER.getValue())){
            _setStatusBasedPaymentByVoucherCodeMethod();
        }
        else if(this.method.equals(PaymentMethod.PAYMENT_BY_BANK_TRANSFER.getValue())){
            _setStatusBasedPaymentTRF();
        }
    }

    private boolean _isPaymentTRFValid(){
        String bankName = this.paymentData.get("bankName");
        String referenceCode = this.paymentData.get("referenceCode");
        boolean isAddressValid = bankName != null && !bankName.trim().isEmpty();
        boolean isDeliveryFeeValid = referenceCode != null && !referenceCode.trim().isEmpty();
        return isAddressValid && isDeliveryFeeValid;
    }

    private void _setStatusBasedPaymentTRF(){
        if(_isPaymentTRFValid()){
            _setStatus(PaymentStatus.SUCCESS.getValue());
        }
        else{
            _setStatus(PaymentStatus.REJECTED.getValue());
        }
    }


    private boolean _isPaymentVoucherValid(){
        String code = paymentData.get("voucherCode");
        boolean isCodeExist = code != null;
        boolean isCodeLength16 = code == null? false: code.trim().length() == 16;
        boolean isStartWithEshop = code == null? false:  code.startsWith("ESHOP");
        @SuppressWarnings("null")
        boolean isDigitCount8 = code == null? false: code.chars().mapToObj(c -> (char) c).filter(Character::isDigit).count() == 8;
        return isCodeExist && isCodeLength16 && isStartWithEshop && isDigitCount8;
    }

    private void _setStatusBasedPaymentByVoucherCodeMethod(){
        if(_isPaymentVoucherValid()){
            _setStatus(PaymentStatus.SUCCESS.getValue());
        }
        else{
            _setStatus(PaymentStatus.REJECTED.getValue());
        }
    }

    public void setPaymentData(Map<String, String> paymentData) {
        this.paymentData = paymentData;
    }

    private void _setStatus(String status){
        this.status = status;
    }

    public void setStatus(String status) {
        if(PaymentStatus.contains(status)){
            if(PaymentMethod.PAYMENT_BY_VOUCHER.getValue().equals(this.method) || PaymentMethod.PAYMENT_BY_BANK_TRANSFER.getValue().equals(this.method)){
                _assignStatusBasedMethod();
            }
            else{
                throw new IllegalArgumentException("NPM genap");
            }
        }
        else{
            throw new IllegalArgumentException();
        }

    }
    public String getId() {
        return id;
    }
    public String getMethod() {
        return method;
    }
    public Map<String, String> getPaymentData() {
        return paymentData;
    }
    public String getStatus() {
        return status;
    }
}
