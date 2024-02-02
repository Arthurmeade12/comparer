package me.arthurmeade12.comparer;
import java.util.Scanner;
public class main {
  public static void main(String[] args){
    config.evalprops();
    msg.out("Welcome to the comparer!");
    gui.main();
    msg.out_nonewline("Adjective ? : ");
    Scanner scanner = new Scanner(System.in);
    comparer exec = new comparer(scanner.next());
    exec.print(exec.get_array());
    msg.out("Bye!");
  }
}
