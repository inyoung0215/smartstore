package smartstore.customer;

import smartstore.arrays.DArray;
import smartstore.group.Group;
import smartstore.group.Groups;

public class Customers extends DArray<Customer> {

    private static Customers allCustomers;

    public static Customers getInstance() {
        if (allCustomers == null) {
            allCustomers = new Customers();
        }
        return allCustomers;
    }

    private Customers() {}
    public void refresh(Groups groups) {
        for (int i = 0; i <allCustomers.size(); i++){
            Customer customer = allCustomers.get(i);
            Group customerGroup = customer.getGroup();
            if (customerGroup == null){
                continue;
            }
            for (int j = 0; j < groups.size(); j++){
                Group group = groups.get(j);
                if (customerGroup.equals(group.getGroupType())) {
                    customer.setGroup(group);
                    break;
                }
            }
        }
    }
}

