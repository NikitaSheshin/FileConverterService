package City;

public class Entrance {
    int countOfCitizens;
    int countOfFlats;
    int debt;

    public Entrance(){

    }

    public Entrance(int countOfCitizens, int countOfFlats, int debt){
        this.countOfCitizens = countOfCitizens;
        this.countOfFlats = countOfFlats;
        this.debt = debt;
    }

    public int getCountOfCitizens() {
        return countOfCitizens;
    }

    public void setCountOfCitizens(int countOfCitizens) {
        this.countOfCitizens = countOfCitizens;
    }

    public int getCountOfFlats() {
        return countOfFlats;
    }

    public void setCountOfFlats(int countOfFlats) {
        this.countOfFlats = countOfFlats;
    }

    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }
}
