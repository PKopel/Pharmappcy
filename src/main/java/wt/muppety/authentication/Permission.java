package wt.muppety.authentication;

public enum Permission {
    BUY(0),
    SELL(1),
    ADDUSER(2),
    EDITUSER(3),
    DELETEUSER(4),
    ADDPRODUCT(5),
    EDITPRODUCT(6),
    DELETEPRODUCT(7),
    ADDCATEGORY(8),
    ;

    private final int val;
    private Permission(int val){
        this.val=val;
    }

    public int value(){
        return this.val;
    }
}
