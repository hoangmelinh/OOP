package model;

public class VipUser extends User implements IUser{
    private int bonus;

    public VipUser(String userId, String username, String password, String name, String address, String contact,boolean vipCheck, int bonus) {
        super(userId, username, password, name, address, contact, vipCheck);
        this.bonus = bonus;
    }

    @Override
    public int getBonus() {
        return bonus;
    }
}
