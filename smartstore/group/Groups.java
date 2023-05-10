package smartstore.group;


import smartstore.arrays.DArray;

public class Groups extends DArray<Group> {
    // singleton
    private static Groups allGroups;

    public static Groups getInstance() {
        if (allGroups == null) {
            allGroups = new Groups();
        }
        return allGroups;
    }

    private Groups() {}

    public Group find(GroupType groupType) {
        for (int i = 0; i < allGroups.size(); i++) {
            if (allGroups.get(i).getGroupType() == groupType) {
                return allGroups.get(i);
            }
        }
        return  null;
    }
}
