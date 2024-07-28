class CurrencyAdapter implements INRTransaction {
    private USDTransaction usdTransaction;
    private static final double EXCHANGE_RATE = 74.5; // Example exchange rate

    public CurrencyAdapter(USDTransaction usdTransaction) {
        this.usdTransaction = usdTransaction;
    }

    @Override
    public void processINRPayment(double amount) {
        double amountInUSD = amount / EXCHANGE_RATE;
        usdTransaction.processUSDPayment(amountInUSD);
    }
}