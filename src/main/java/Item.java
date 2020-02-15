public class Item {
    String name;
    int index;
    int price;
    String attunement;
    String rarity;
    String description;

    public Item(String initName, int initIndex, int initPrice, String initAttune, String initRare, String initDesc){
        name=initName;
        index=initIndex;
        price=initPrice;
        attunement=initAttune;
        rarity=initRare;
        description=initDesc;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public String getAttunement() {
        return attunement;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getRarity() {
        return rarity;
    }

}
