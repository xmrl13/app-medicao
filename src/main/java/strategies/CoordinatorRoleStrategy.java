package strategies;

public class CoordinatorRoleStrategy implements RoleStrategy {


    @Override
    public boolean canUpdateUser() {
        return false;
    }

    @Override
    public boolean canCreateMeasurement() {
        return false;
    }

    @Override
    public boolean canManageUsers() {
        return false;
    }

    @Override
    public boolean canDeleteWork() {
        return false;
    }

    @Override
    public boolean canViewItems() {
        return false;
    }

    @Override
    public boolean canCreateUser() {
        return true;
    }

}
