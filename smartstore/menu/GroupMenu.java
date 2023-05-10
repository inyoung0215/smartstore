package smartstore.menu;


import smartstore.customer.Customers;
import smartstore.exception.EmptyArrayException;
import smartstore.exception.InputEndException;
import smartstore.group.Group;
import smartstore.group.GroupType;
import smartstore.group.Groups;
import smartstore.group.Parameter;
import smartstore.util.Message;

public class GroupMenu implements Menu {
    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();
    // singleton
    private static GroupMenu groupMenu;

    public static GroupMenu getInstance() {
        if (groupMenu == null) {
            groupMenu = new GroupMenu();
        }
        return groupMenu;
    }

    private GroupMenu() {}

    @Override
    public void manage() {
        while ( true ) { // 서브 메뉴 페이지를 유지하기 위한 while
            int choice = chooseMenu(new String[]{
                    "Set Parameter",
                    "View Parameter",
                    "Update Parameter",
                    "Back"});

            if (choice == 1) setParameter();
            else if (choice == 2) {viewParameter();}
            else if (choice == 3) {updateParameter();}
            else break;
        }
    }

    public GroupType chooseGroup() {
        while ( true ) {
            try {
                System.out.print("Which group (GENERAL (G), VIP (V), VVIP (VV))? ");
                String choice = nextLine(Message.END_MSG);

                GroupType groupType = GroupType.valueOf(choice).replaceFullName();
                return groupType;
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                return null;
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }


    public void setParameter() { // 초기화할 때만 호출 가능
        while ( true ) {

                GroupType groupType = chooseGroup();
                Group group = allGroups.find(groupType);

                if (group != null && group.getParameter() != null) { // group.getParameter()이 null이 아니면 이미 초기화됨
                    System.out.println("\n" + group.getGroupType() + " group already exists.");
                    System.out.println("\n" + group);
                } else {
                    Parameter parameter = new Parameter();
                    if(group == null){
                        group = new Group(groupType);
                        allGroups.add(group);
                    }

                    group.setParameter(parameter);
                    allCustomers.refresh(allGroups);
                }

        }
    }

    public void viewParameter(){
        while (true){
            try {
                System.out.println(allGroups.getInstance());
            }catch (EmptyArrayException e){
                System.out.println(Message.ERR_MSG_INVALID_ARR_EMPTY);
            }
        }
    }

    public void updateParameter() {
        while (true) {
            GroupType groupType = chooseGroup();
            if (groupType == null) {
                break;
            }

            Group group = allGroups.find(groupType);
            if (group == null || group.getParameter() == null) {
                System.out.println("\n" + groupType + " group does not exist or parameter is not set.");
            } else {
                System.out.println("\nCurrent parameter: " + group.getParameter());

                while (true) {
                    try {
                        System.out.print("Enter minimum time (integer): ");
                        int minTime = Integer.parseInt(nextLine(Message.END_MSG));

                        System.out.print("Enter minimum pay (integer): ");
                        int minPay = Integer.parseInt(nextLine(Message.END_MSG));

                        group.getParameter().setMinTime(minTime);
                        group.getParameter().setMinPay(minPay);

                        allCustomers.refresh(allGroups);

                        System.out.println("\nUpdated parameter: " + group.getParameter());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
                    } catch (InputEndException e) {
                        System.out.println(Message.ERR_MSG_INPUT_END);
                        break;
                    }
                }
            }
        }
    }
}
