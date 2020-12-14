package wt.muppety.model;

import wt.muppety.authentication.Permission;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.BitSet;

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

    public Permissions() {
    }

    public BitSet asBitSet(){
        BitSet ret = new BitSet(Permission.values().length);
        for (Field f : this.getClass().getDeclaredFields()){
            ret.set(Permission.valueOf(f.getName()).value());
        }
        return ret;
    }

}
