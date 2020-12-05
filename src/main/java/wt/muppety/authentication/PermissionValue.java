package wt.muppety.authentication;

public enum PermissionValue {
    BUY(0),
    SELL(1),

    ADDUSER(2),
    DELETEUSER(3),
    EDITUSER(4),

    ADDPRODUCT(5),
    EDITPRODUCT(6),
    DELETEPRODUCT(7),

    ADDCATEGORY(8),
    ;

    private final int val;

    PermissionValue(int val){
        this.val=val;
    }

    public int value(){
        return this.val;
    }

}
