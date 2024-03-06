package id.ac.ui.cs.advprog.eshop.model;


import enums.PaymentMethod;
import enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentTest {
    private List<Product> products;
    private Map<String, String> paymentData;
    private Order order;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setItemId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setItemName("Sampo Cap Bambang");
        product1.setItemQuantity(2);
        Product product2 = new Product();
        product2.setItemId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setItemName("Sabun Cap Usep");
        product2.setItemQuantity(1);
        this.products.add(product1);
        this.products.add(product2);

        this.order = new Order("a1b23c", products, 17085000L, "Dekdepe");

        this.paymentData = new HashMap<>();
        this.paymentData.put("voucherCode", "ESHOP1234ABC5678");
    }

    @Test
    public void testGetPayment() {
        Map<String, String> paymentData = new HashMap<>();
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.PAYMENT_BY_BANK_TRANSFER.getValue(), new HashMap<>());
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals(PaymentMethod.PAYMENT_BY_BANK_TRANSFER.getValue(), payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
    }
    @Test
    void testSetPaymentStatusToInvalidStatus (){
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",  PaymentMethod.PAYMENT_BY_BANK_TRANSFER.getValue(), new HashMap<>());
        assertThrows(IllegalArgumentException.class, ()-> payment.setStatus("MEOW"));
    }

    @Test
    void testCreatePaymentInvalidStatus(){
        assertThrows(IllegalArgumentException.class, ()-> {
            new Payment("13652556-012a-4c07-b546-54eb1396d79b",  PaymentMethod.PAYMENT_BY_BANK_TRANSFER.getValue(), "MEOW", new HashMap<>());
        });
    }

    @Test
    void testVoucherFieldIsNull(){
        // Voucher code is null
        Payment paymentWithNullVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.PAYMENT_BY_VOUCHER.getValue(), new HashMap<>());
        assertEquals(PaymentStatus.REJECTED.getValue(), paymentWithNullVoucherCode.getStatus());
    }


    @Test
    void testVoucherCodeIsNotValid(){
        String[] voucherCodeList= {"","A", "ESHOPEFGHIJKLMN541", "EFGHIJKLMN54234B", "ESHOP1284ADC567C"};
        // Empty, <16, M>16, No 8 Numericals character
        Map<String,String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", voucherCodeList[0]);
        Payment payment= new Payment("13652556-012a-4c07-b546-54eb1396d79d", PaymentMethod.PAYMENT_BY_VOUCHER.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());

        Map<String,String> paymentData2 = new HashMap<>();
        paymentData2.put("voucherCode", voucherCodeList[1]);
        Payment payment2= new Payment("13652556-012a-4c07-b546-54eb1396d79d", PaymentMethod.PAYMENT_BY_VOUCHER.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment2.getStatus());

        Map<String,String> paymentData3 = new HashMap<>();
        paymentData3.put("voucherCode", voucherCodeList[2]);
        Payment payment3= new Payment("13652556-012a-4c07-b546-54eb1396d79d", PaymentMethod.PAYMENT_BY_VOUCHER.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment3.getStatus());

        Map<String,String> paymentData4 = new HashMap<>();
        paymentData4.put("voucherCode", voucherCodeList[3]);
        Payment payment4= new Payment("13652556-012a-4c07-b546-54eb1396d79d", PaymentMethod.PAYMENT_BY_VOUCHER.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment4.getStatus());

        Map<String,String> paymentData5 = new HashMap<>();
        paymentData5.put("voucherCode", voucherCodeList[4]);
        Payment payment5= new Payment("13652556-012a-4c07-b546-54eb1396d79d", PaymentMethod.PAYMENT_BY_VOUCHER.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment5.getStatus());
    }

    @Test
    void testCreatePaymentWithInvalidPaymentMethod (){
        assertThrows(IllegalArgumentException.class, ()-> {
            new Payment("13652556-012a-4c07-b546-54eb1396d79b",  "Invalid Method", PaymentStatus.SUCCESS.getValue(), new HashMap<>());
        });
    }

    @Test
    void testCreatePaymentWithValidPaymentMethod(){
        assertDoesNotThrow(()-> {
            new Payment("13652556-012a-4c07-b546-54eb1396d79b",  PaymentMethod.PAYMENT_BY_VOUCHER.getValue(), PaymentStatus.SUCCESS.getValue(), new HashMap<>());
        });;
    }

    @Test
    void testVoucherCodeIsValid(){
        Map<String,String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.PAYMENT_BY_VOUCHER.getValue(), paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testBankTransferPaymentInvalid(){
        Map<String,String> paymentData = new HashMap<>();
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.PAYMENT_BY_BANK_TRANSFER.getValue(), paymentData);
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals(PaymentMethod.PAYMENT_BY_BANK_TRANSFER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());

        Map<String,String> paymentData2 = new HashMap<>();
        paymentData2.put("address", "");
        paymentData2.put("deliveryFee", "");
        Payment payment2 = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.COD.getValue(), paymentData2);
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment2.getId());
        assertEquals(PaymentMethod.PAYMENT_BY_BANK_TRANSFER.getValue(), payment2.getMethod());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment2.getStatus());
    }

    @Test
    void testBankTransferPaymentValid(){
        Map<String,String> paymentData = new HashMap<>();
        paymentData.put("address", "Depok, Jawa Barat, Indonesia");
        paymentData.put("deliveryFee", "20000");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.PAYMENT_BY_BANK_TRANSFER.getValue(), paymentData);

        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals(PaymentMethod.PAYMENT_BY_BANK_TRANSFER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testEmptyPaymentData(){
        Map<String,String> paymentData = null;
        assertThrows(IllegalArgumentException.class, ()-> new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                PaymentMethod.PAYMENT_BY_BANK_TRANSFER.getValue(),paymentData));
    }
}
