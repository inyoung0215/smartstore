package smartstore.customer;

import smartstore.arrays.DArray;
import smartstore.group.Group;
import smartstore.group.Groups;

public class Customers extends DArray<Customer> {

    private static Customers allCustomers;
    private final Groups allGroups = Groups.getInstance();

    public static Customers getInstance() {
        if (allCustomers == null) {
            allCustomers = new Customers();
        }
        return allCustomers;
    }

    private Customers() {}
    public void refresh(Customer customer){
        int time = customer.getCusTotalTime();
        int pay = customer.getCusTotalPay();
        for (int i = 0; i < allGroups.size(); i++){
            if (time >= allGroups.get(i).getParameter().getMinTime()
                    && pay >= allGroups.get(i).getParameter().getMinPay()){
                customer.setGroup(allGroups.get(i));
            }
        }
    }
    public void refresh(Groups groups) {
        for (int i = 0; i <allCustomers.size(); i++){
            Customer customer = allCustomers.get(i);
            Group customerGroup = customer.getGroup();
            if (customerGroup == null){
                continue;
            }
            for (int j = 0; j < groups.size(); j++){
                Group group = groups.get(j);
                if(customer.getCusTotalTime() >= group.getParameter().getMinTime()
                        && customer.getCusTotalPay() >= group.getParameter().getMinPay()){
                    customer.setGroup(group);
                    break;
                }
            }
        }
    }
}

