public abstract class Play {

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public abstract float calculateAmount(Integer audience);

  public float calculateBonus(Integer audience)
  {
    return Float.valueOf(Math.max(audience - 30, 0));
  }
}
