package apk.win.first.secondgoogaut;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Details implements Serializable {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("damage_type")
    @Expose
    private String damageType;
    @SerializedName("min_power")
    @Expose
    private Integer minPower;
    @SerializedName("max_power")
    @Expose
    private Integer maxPower;
    @SerializedName("defense")
    @Expose
    private Integer defense;
    @SerializedName("infusion_slots")
    @Expose
    private List<Object> infusionSlots = null;
    @SerializedName("infix_upgrade")
    @Expose
    private InfixUpgrade infixUpgrade;
    @SerializedName("secondary_suffix_item_id")
    @Expose
    private String secondarySuffixItemId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public Integer getMinPower() {
        return minPower;
    }

    public void setMinPower(Integer minPower) {
        this.minPower = minPower;
    }

    public Integer getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(Integer maxPower) {
        this.maxPower = maxPower;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public List<Object> getInfusionSlots() {
        return infusionSlots;
    }

    public void setInfusionSlots(List<Object> infusionSlots) {
        this.infusionSlots = infusionSlots;
    }

    public InfixUpgrade getInfixUpgrade() {
        return infixUpgrade;
    }

    public void setInfixUpgrade(InfixUpgrade infixUpgrade) {
        this.infixUpgrade = infixUpgrade;
    }

    public String getSecondarySuffixItemId() {
        return secondarySuffixItemId;
    }

    public void setSecondarySuffixItemId(String secondarySuffixItemId) {
        this.secondarySuffixItemId = secondarySuffixItemId;
    }

}
