package wt.muppety.model;

import javax.persistence.*;

@Embeddable
public class Permissions {


    @Column(name = "canBuy", nullable = false)
    public boolean canBuy = false;
    @Column(name = "canSell", nullable = false)
    public boolean canSell = false;
    @Column(name = "canBrowseDB", nullable = false)
    public boolean canBrowseDB = false;
    @Column(name = "canModerateDB", nullable = false)
    public boolean canModerateDB = false;
    public Permissions() {}

}