package smartstore.menu;

import smartstore.customer.Customer;
import smartstore.customer.Customers;
import smartstore.exception.EmptyArrayException;
import smartstore.group.Groups;
import smartstore.util.Message;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Pattern;

public class SummaryMenu implements Menu {
    // singleton
    private static SummaryMenu summaryMenu;
    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();

    public static SummaryMenu getInstance() {
        if (summaryMenu == null) {
            summaryMenu = new SummaryMenu();
        }
        return summaryMenu;
    }

    private SummaryMenu() {}

    @Override
    public void manage() {
        while ( true ) { // 서브 메뉴 페이지를 유지하기 위한 while
            int choice = chooseMenu(new String[]{
                    "Summary",
                    "Summary (Sorted By Name)",
                    "Summary (Sorted By Time)",
                    "Summary (Sorted By Pay)",
                    "Back"});
            if (choice == 1) summary();
            else if (choice == 2) sortedByName();
            else if (choice == 3) sortedByTime();
            else if (choice == 4) sortedByPay();
            else break;
        }
    }

    private void summary() {
        try {
            for (int i = 0; i < allCustomers.size(); i++){
                System.out.println(allCustomers.get(i).toString());
            }
        } catch (EmptyArrayException e){
            System.out.println(Message.ERR_MSG_NULL_ARR_ELEMENT);
        }
    }

    public void sortedByName(){
        try {
            Customer[] customers = new Customer[allCustomers.size()];
            for (int i = 0; i < customers.length; i++){
                customers[i] = allCustomers.get(i);
            }
            Arrays.sort(customers);

            for (Customer customer : customers){
                System.out.println(customer.toString());
            }
        } catch (EmptyArrayException e){
            System.out.println(Message.ERR_MSG_INVALID_ARR_EMPTY);
        }

    }

    public void sortedByTime(){
        try {
            Customer[] customers = new Customer[allCustomers.size()];
            for (int i = 0; i < customers.length; i++) {
                customers[i] = allCustomers.get(i);
            }
            Arrays.sort(customers, (o1, o2) -> o1.getCusTotalTime().compareTo(o2.getCusTotalTime()));

            for (Customer customer : customers) {
                System.out.println(customer.toString());
            }
        } catch (EmptyArrayException e){
            System.out.println(Message.ERR_MSG_NULL_ARR_ELEMENT);
        }

    }

    public void sortedByPay(){
        try {
            Customer[] customers = new Customer[allCustomers.size()];
            for (int i = 0; i < customers.length; i++) {
                customers[i] = allCustomers.get(i);
            }
            Arrays.sort(customers, (o1, o2) -> o1.getCusTotalPay().compareTo(o2.getCusTotalPay()));

            for (Customer customer : customers) {
                System.out.println(customer.toString());
            }
        } catch (EmptyArrayException e){
            System.out.println(Message.ERR_MSG_NULL_ARR_ELEMENT);
        }

    }
}


