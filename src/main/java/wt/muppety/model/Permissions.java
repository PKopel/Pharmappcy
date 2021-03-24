package wt.muppety.model;

import wt.muppety.authentication.Permission;

import javax.persistence.Column;
import javax.persistence.Embeddable;
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

    public BitSet asBitSet() {
        BitSet ret = new BitSet(Permission.values().length);
        for (Field f : this.getClass().getDeclaredFields()) {
            try {
                if(f.getBoolean(this))
                ret.set(Permission.valueOf(f.getName()).value());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

}
