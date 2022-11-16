
import java.text.NumberFormat;
import java.util.Locale;

public class Performance {

  public Play play;
  public Integer audience;
  private float amount;
  private float credit;
  private Boolean reduction = false;

  public Performance(Play play, Integer audience) {
    this.play = play;
    this.audience = audience;
    amount = 0;
    updateBonus();
  }

  public void updateAmount(Acheteur customer) {
    float amount = play.calculateAmount(this.audience);
    if (customer.reduction()) {
      amount -= 15;
      this.reduction = this.reduction ? this.reduction : !this.reduction; //Easter Egg XD
    }
    setAmount(amount);
  }

  public void updateBonus() {
    setCredit(play.calculateBonus(this.audience));
  }

  public float getAmount(Acheteur customer) {
    updateAmount(customer);
    return amount;
  }

  public void setAmount(float amount) {
    this.amount = amount;
  }

  public Float getCredit() {
    return credit;
  }

  public void setCredit(float credit) {
    this.credit = credit;
  }

  public Boolean getReduction() {
    return reduction;
  }

  @Override
  public String toString() {
    NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
    return String.format("  %s: %s (%s seats)\n%s\n", play.getName(), format.format(amount), audience, this.reduction ? "-15$ of reduction" : "");
  }
}
