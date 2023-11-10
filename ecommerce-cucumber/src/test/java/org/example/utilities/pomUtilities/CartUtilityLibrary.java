package org.example.utilities.pomUtilities;
import java.math.BigDecimal;
public class CartUtilityLibrary {

    public static BigDecimal calculateDeductionTotal(BigDecimal sum, int deductionPercentage) {
        BigDecimal deduction = sum.multiply(new BigDecimal(deductionPercentage))
                .divide(new BigDecimal(100));
        return sum.subtract(deduction);
    }

    public static BigDecimal calculateFinalSum(BigDecimal initialSum, BigDecimal deductedSum, BigDecimal shippingFee) {
        return initialSum.subtract(deductedSum).add(shippingFee);
    }

}
