package smartstore.menu;

import smartstore.customer.Customer;
import smartstore.customer.Customers;
import smartstore.exception.EmptyArrayException;
import smartstore.exception.InputRangeException;
import smartstore.util.Message;

public class CustomerMenu implements Menu {
    // singleton
    private static CustomerMenu customerMenu;

    public static CustomerMenu getInstance() {
        if (customerMenu == null) {
            customerMenu = new CustomerMenu();
        }
        return customerMenu;
    }

    private CustomerMenu() {}

    @Override
    public void manage() {
        while ( true ) { // 서브 메뉴 페이지를 유지하기 위한 while
            int choice = chooseMenu(new String[]{
                    "Add Customer",
                    "View Customer",
                    "Update Customer",
                    "Delete Customer",
                    "Back"});

            if (choice == 1) addCustomer();
            else if (choice == 2) {viewCustomer();}
            else if (choice == 3) {updateCustomer();}
            else if (choice == 4) {deleteCustomer();
            } else break;

        }
    }

    public void addCustomer(){
        System.out.println("Enter customer name: ");
        String name = nextLine();

        System.out.println("Enter customer ID: ");
        String id = nextLine();

        System.out.println("Enter total time spent by customer: ");
        int time = 0;
        while (true) {
            try {
                time = Integer.parseInt(nextLine());
                if (time < 0) throw new InputRangeException(); // 시간 값이 음수일 경우 예외 처리
                break;
            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }

        System.out.println("Enter total pay made by customer: ");
        int pay;
        while (true) {
            try {
                pay = Integer.parseInt(nextLine());
                if (pay < 0) throw new InputRangeException(); // 결제 값이 음수일 경우 예외 처리
                break;
            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }

        Customer customer = new Customer(name, id, time, pay);
    }
    public void viewCustomer() {
        try {
            System.out.println(Customers.getInstance());
        } catch (EmptyArrayException e){
            System.out.println(Message.ERR_MSG_NULL_ARR_ELEMENT);
        }
    }

    public void updateCustomer(){

    }
    public void deleteCustomer(){

    }
}
