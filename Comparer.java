package me.arthurmeade12.comparer;
public class Comparer {
  protected boolean firstsecond = false;
  protected boolean neuter = false;
  protected String nominative;
  protected String base;
  public String[][] array = new String[3][2];
  public Comparer(String nom) {
    nominative = nom;
    String droptwo = msg.drop(nom, 2);
    switch (msg.trim(nom, 2)) {
    case "um": // e.g. latum
      neuter = true;
    case "us": // e.g. latus
      firstsecond = true;
    case "is": // e.g. crudelis
      base = droptwo;
      break;
    case "ns": // e.g. diligens
      base = droptwo + "nt";
      break;
    case "er": // e.g. acer & pulcher
      throw new IllegalArgumentException("TODO.");
    default:
      String dropone = msg.drop(nom, 1);
      switch (msg.trim(nom, 1)) {
      case "a": // e.g. lata
        firstsecond = true;
        base = dropone;
        break;
      case "e": // e.g. crudele
        neuter = true;
        base = dropone;
        break;
      case "x": // e.g. atrox
        base = dropone + "c";
        break;
      default:
        throw new IllegalArgumentException("Nominative '" + nom + "' did not fall into a declension.");
      }
      break;
    }
    if (nom.length() < 3) {
      throw new IllegalArgumentException("Nominative '" + nom + "' is too short.");
    }
  }
  private static boolean special_irreg(String base) {
    try {
      switch (msg.trim(base, 3)) {
      case "dic":
      case "fic":
      case "vol":
        return true;
      }
    }
    catch (StringIndexOutOfBoundsException c) {}
    return false;
  }
  public static String pos_adj(String adjective) {
    return adjective;
  }
  public String pos_adj(){
    return nominative;
  }
  public static String comp_adj(String base, boolean neuter) {
    String ending;
    if (neuter) {
      ending = "us";
    } else {
      ending = "or";
    }
    if (special_irreg(base)) {
      return base + "enti" + ending;
    }
    switch (base) {
    case "bon":
      return "meli" + ending;
    case "mal":
      return "pej" + ending;
    case "mult":
      return "plus";
    case "parv":
      return "min" + ending;
    case "magn":
      return "maj" + ending;
    case "dextr":
      return "dexteri" + ending;
    default:
      return base + "i" + ending;
    }
  }
  public String comp_adj() {
    return comp_adj(base, neuter);
  }
  public static String super_adj(String base, boolean neuter, String nominative){
    String ending;
    if (neuter) {
      ending = "um";
    } else {
      ending = "us";
    }
    if (special_irreg(base)) {
      return base + "entissim" + ending;
    }
    switch (base) {
    case "bon":
      return "optim" + ending;
    case "mal":
      return "pessim" + ending;
    case "mult":
      return "plurim" + ending;
    case "parv":
      return "minim" + ending;
    case "magn":
      return "maxim" + ending;
    case "dextr":
      return  "dextim" + ending;
    case "exter":
    case "super":
    case "poster":
      return msg.drop(base, 2) + "rem" + ending;
    case "infer":
      return "infim" + ending;
    case "facil":
    case "difficil":
    case "simil":
    case "dissimil":
    case "gracil":
    case "humil":
      return base + "lim" + ending;
    default:
      if (nominative.endsWith("er")) {
        return nominative + "rim" + ending;
      } else {
        return base + "issim" + ending;
      }
    }
  }
  public String sup_adj(){
    return sup_adj(base, neuter, nominative);
  }
  public static String pos_adv(String base, boolean firstsecond){
    switch (base) {
    case "bon":
      return "bene";
    case "mult":
      return "multum";
    case "parv":
      return "parum";
    case "magn":
      return "magnopere";
    case "facil":
    case "difficil":
      return base + "e";
    }
    if (firstsecond) {
      return base + "e";
    } else {
      if (base.endsWith("nt")){
        return base + "er";
      } else {
        return base + "iter";
      }
    }
  }
  public String pos_adv(){
    return pos_adv(base, firstsecond);
  }
  public static String comp_adv(String base){
    switch (base) {
    case "magn":
      return "magis";
    default:
      return comp_adj(base, true);
    }
  }
  public String comp_adv(){
    return comp_adv(base);
  }
  public static String super_adv(String base, boolean neuter, String nominative){
    return msg.drop(super_adj(base, neuter, nominative), 2) + "e";
  }
  public String super_adv(){
    return super_adj();
  }
  public void set_array(){
    this.array = {
      { this.pos_adj(), this.pos_adv() },
      { this.comp_adj(), this.comp_adv() },
      { this.super_adj(), this.super_adv() }
    };
  }
  protected void print(){
    msg.out("Positive: " + array[0][0] + " " + array[0][1]);
    msg.out("Comparitive: " + array[1][0] + " " + array[1][1]);
    msg.out("Superlative: " + array[2][0] + " " + array[2][1]);
  }
}
