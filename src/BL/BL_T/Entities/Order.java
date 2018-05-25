package BL.BL_T.Entities;
import java.util.Map;

/**
 * Created by Naama on 25/04/2018.
 */
public class Order
{
    private String orderNumber;
    private Map<String, Integer> item_quantities;

    public Order(String orderNumber, Map<String, Integer> itemquantities) {
        this.orderNumber = orderNumber;
        this.item_quantities = itemquantities;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public Map<String, Integer> getItem_quantities() {
        return item_quantities;
    }

    @Override
    public String toString() {
        String quans = "{";
        for (Map.Entry entry: item_quantities.entrySet())
            quans = quans + entry.getKey()+": "+entry.getValue()+ ", ";
        if (quans.length()>1)
            quans = quans.substring(0, quans.length()-2);
        return "Order{" +
                "orderNumber =" + orderNumber +
                ", Item quantities =" + item_quantities +
                '}';
    }
}
