public enum ElementType{
  //Basic
  CARBON("Carbon", 2, 0, 0),
  HYDROGEN("Hydrogen", 6, 0, 0),
  OXYGEN("Oxygen", 9, 0, 0),
  NITROGEN("Nitrogen", 7, 0, 0),
  HELIUM("Helium", 5, 0, 0),

  //Common
  SODIUM("Sodium", 4, 1, 1),
  ALUMINUM("Aluminum", 0, 0, 1),
  POTASSIUM("Potassium", 0, 1, 1),
  CHLORINE("Chlorine", 4, 0, 1),
  SILICON("Silicon", 2, 1, 1),
  PHOSPHORUS("Phosphorus", 1, 2, 1),
  SILVER("Silver", 2, 2, 1),
  GOLD("Gold", 3, 2, 1),
  CALCIUM("Calcium", 5, 2, 1),

  //Uncommon
  WATER("Water", 7, 1, 2),
  AMMONIA("Ammonia", 1, 0, 2),
  SALT("Salt", 1, 1, 2),
  ORGANIC("Organic", 8, 0, 2),
  PHOSPHATE("Phosphate", 0, 2, 2),

  //Rare
  CARBONWATER("Carbonated Water", 3, 0, 3),
  SUGAR("Sugar", 5, 1, 3),
  CELL("Cell", 8, 1, 3),
  DNA("DNA", 4, 2, 3),
  BONE("Bone", 7, 2, 3),

  //Ultra Rare
  TONIC("Tonic", 6, 1, 4),
  SODA("Soda", 3, 1, 4),
  ORGANISM("Organism", 9, 1, 4),

  //Legendary
  VERTABRATE("Vertabrate", 6, 2, 4),

  //Producers
  PCARBON("Carbon Factory", 9, 2, 0),
  PHYDRO("Hydrogen Mine", 8, 2, 0),
  POXY("Oxygen Shrine", 0, 3, 0),
  PNITRO("Nitrogen-erator", 2, 3, 0),
  PHELIUM("Helium Pump", 1, 3, 0);


  private final String name;
  private final int spriteX;
  private final int spriteY;
  private final int rarity;

  ElementType(String name, int x, int y, int r){
    this.name = name;
    this.spriteX = x;
    this.spriteY = y;
    this.rarity = r;
  }

  public String getName(){
    return name;
  }

  public int getRarity(){
    return rarity;
  }

  public int getY(){
    return spriteY*128;
  }

  public int getX(){
    return spriteX*128;
  }

}
