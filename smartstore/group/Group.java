package smartstore.group;

import java.util.Objects;

public class Group {
    private Parameter parameter; // 분류기준
    private GroupType groupType; // 그룹 타입

    public Group(GroupType groupType) {
    }

    public Group(Parameter parameter, GroupType groupType) {
        this.parameter = parameter;
        this.groupType = groupType;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(parameter, group.parameter) && groupType == group.groupType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameter, groupType);
    }

    @Override
    public String toString() {
        return "Group{" +
                "parameter=" + parameter +
                ", groupType=" + groupType +
                '}';
    }
}
