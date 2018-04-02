package example.object;

/**
 * @author sheol on 4/2/18 at 10:48 PM
 * @project main-project
 */
public class ObjectExample {
  private String power;
  private int level;
  private String addon;

  public int getLevel() {
    return level;
  }

  public String getAddon() {
    return addon;
  }

  public String getPower() {
    return power;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public void setAddon(String addon) {
    this.addon = addon;
  }

  public void setPower(String power) {
    this.power = power;
  }

  public ObjectExample withLevel(int level) {
    setLevel(level);
    return this;
  }

  public ObjectExample withAddon(String addon) {
    setAddon(addon);
    return this;
  }

  public ObjectExample withPower(String power) {
    setPower(power);
    return this;
  }
}
