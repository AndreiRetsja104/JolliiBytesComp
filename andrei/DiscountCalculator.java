package andrei;


public class DiscountCalculator {
        public float calculateDiscount(float totalPrice, boolean isMember) {
            float discount = 0f;
            if (isMember) {
                if (totalPrice >= 100) {
                    discount = totalPrice * 0.1f; // 10% discount for member orders over 100 euros
                } else if (totalPrice >= 50) {
                    discount = totalPrice * 0.05f; // 5% discount for member orders over 50 euros
                }
            } else {
            if (totalPrice >= 100) {
                // No discount for non-members for orders over 100 euros
                discount = 0f;
            } else if (totalPrice >= 50) {
                discount = totalPrice * 0.05f; // 5% discount for non-members for orders over 50 euros
            }
        }
        return discount;
    }

    public float calculateNonMemberDiscount(float totalPrice) {
        return calculateDiscount(totalPrice, false);
    }
}
