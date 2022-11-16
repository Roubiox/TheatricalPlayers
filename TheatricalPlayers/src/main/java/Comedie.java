
public class Comedie extends Play{

    public Comedie(String name)
    {
        this.setName(name);
    }

    @Override
    public float calculateAmount(Integer audience)
    {
        float amount = 300;
        if (audience > 20) {
            amount += 100 + 5 * (audience - 20);
        }
        amount += 3 * audience;

        return amount;
    }

    public float calculateBonus(Integer audience)
    {
        return super.calculateBonus(audience) + Float.valueOf((float) Math.floor(audience / 5));
    }
}
