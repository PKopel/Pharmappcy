package wt.muppety.authentication;

public enum Permission {
    canBuy(0),
    canSell(1),
    canBrowseDB(2),
    canModerateDB(3);

    private final int val;
    Permission(int val){
        this.val=val;
    }

    public int value(){
        return this.val;
    }
}
