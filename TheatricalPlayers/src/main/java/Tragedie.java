public class Tragedie extends Play{

    public Tragedie(String name)
    {
        setName(name);
    }

    @Override
    public float calculateAmount(Integer audience)
    {
        float amount = 400;
        if (audience > 30) {
            amount += 10 * (audience - 30);
        }
        return amount;
    }
}
