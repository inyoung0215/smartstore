package smartstore.menu;

public class SummaryMenu implements Menu {
    // singleton
    private static SummaryMenu summaryMenu;

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
            if (choice == 1) sortedByName();
            else if (choice == 2) {
                sortedByTime();
            } else if (choice == 3) {
                sortedByPay();
            } else break;
        }
    }

    public void sortedByName(){

    }

    public void sortedByTime(){

    }

    public void sortedByPay(){

    }
}


