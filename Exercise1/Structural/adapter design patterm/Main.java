public class Main {
    public static void main(String[] args) {
        USDTransaction usdTransaction = new USDTransaction();
        INRTransaction inrTransaction = new CurrencyAdapter(usdTransaction);
        
        inrTransaction.processINRPayment(7450); // Example amount in INR
    }
}