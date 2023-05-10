package smartstore.menu;

import smartstore.customer.Customer;
import smartstore.customer.Customers;
import smartstore.exception.EmptyArrayException;
import smartstore.exception.InputEndException;
import smartstore.exception.InputRangeException;
import smartstore.group.Groups;
import smartstore.util.Message;


public class CustomerMenu implements Menu {
    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();
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
        for (int i = 0; i < allCustomers.size(); i++){
            if (allCustomers.get(i).getCusId().equals(id)){
                System.out.println("ID already exists");
                return;
            }
        }
        System.out.println("Enter total time");
        int time;
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

        System.out.println("Enter total pay");
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
        allCustomers.add(customer);
        allCustomers.refresh(customer);
        System.out.println("** Add SUCCESS **" + customer);

    }
    public void viewCustomer() {
        try {
            for (int i = 0; i < allCustomers.size(); i++){
                System.out.println(allCustomers.get(i).toString());
            }
        } catch (EmptyArrayException e){
            System.out.println(Message.ERR_MSG_NULL_ARR_ELEMENT);
        }
    }

    public void updateCustomer(){
        Customer customer = null;

            System.out.println("Enter customer ID: ");
            String id = scanner.nextLine();

            for (int i = 0; i < allCustomers.size(); i++) {
                if (id != null && allCustomers.get(i).getCusId().equals(id)) {
                    customer = allCustomers.get(i);
                    System.out.println(customer.toString());
                    break;
                }
            }
            if (customer == null) {
                System.out.println("cutomer ID does not exist");
                return;
            }


            System.out.println("***** Choose Update *****");
            while (true) {
                try {
                    int choice = chooseMenu(new String[]{
                            "Customer Name",
                            "Customer Total Time",
                            "Customer Total Pay",
                            "Back"});

                    if (choice == 1) {
                        System.out.println("Update customer Name : ");
                        String setName = scanner.nextLine();
                        customer.setCusName(setName);
                    }
                    else if (choice == 2) {
                        System.out.println("Update customer Total time : ");
                        int setTime = Integer.parseInt(scanner.nextLine());
                        customer.setCusTotalTime(setTime);
                    } else if (choice == 3) {
                        System.out.println("Update customer Total Pay : ");
                        int setPay = Integer.parseInt(scanner.nextLine());
                        customer.setCusTotalPay(setPay);
                    } else break;
                    System.out.println(customer.toString());
                } catch (InputEndException e){
                    System.out.println(Message.ERR_MSG_INPUT_END);
                }

            }
            allCustomers.refresh(allGroups);
        }


    public void deleteCustomer() {

            System.out.println("Enter customer ID : ");
            String id = scanner.nextLine();
            int index = -1;
            try {
                for (int i = 0; i < allCustomers.size(); i++) {
                    if (id != null && allCustomers.get(i).getCusId().equals(id)) {
                        index = i;
                        break;
                    }
                }
            } catch (EmptyArrayException e) {
                System.out.println(Message.ERR_MSG_INVALID_ARR_EMPTY);
            }

            if (index == -1) {
                System.out.println("customer ID does not exist");

            }
            try {
                System.out.println("**Delete Success**" + allCustomers.pop(index));
            } catch (IndexOutOfBoundsException e) {
                System.out.println(Message.ERR_MSG_INVALID_ARR_INDEX);
            }

    }
}
