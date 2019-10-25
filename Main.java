/**
 * Part 1 of RPG
 *
 * @author Dennis Shelton
 * @version 10/21/19
 */
import java.util.Scanner;

public class Main
{
  public static void main(String[] args) throws InterruptedException {
    Scanner in = new Scanner(System.in);

    /*\ 
     * Defined at the start of the class 
     * the bonuses & hp are calculated using random math each 
     * time the class starts; the armor is the only thing not 
     * remotely random, using two already defined stats with a 
     * default of 10
    \*/
    int luckBonus = (int)((Math.random()*4))-1;
    int strBonus = (int)((Math.random()*4))-1;
    int liveBonus = (int)((Math.random()*4))-1;
    int health = (int)(Math.random()*15)+5+liveBonus;
    int armor = (int)((Math.random()*2));
    int armorClass = 10 + luckBonus + armor;

    // Changed throughout the game
    int luck = 0;
    int str = 0;
    int live = 0;
    int damage = 0;
    int attacked = 0;
    int slow = 0;
    int tempArmor = 0;
    int damageBonus = 0;
    int enemyAc = 10;
    int enemyHealth = 0;
    String choice;
    boolean critical = false;
    boolean hit;

    // Instances completed
    boolean instance1 = false;


    // This is where the fun begins
    System.out.print("This is not a very good RPG. Type start to begin. ");

    String begin = in.next().toLowerCase();

    while (!begin.equals("start")) {
      begin = in.next().toLowerCase();
    }

    System.out.println("Health amount: " + health);
    Thread.sleep(500);
    System.out.println("Luck bonus: " + luckBonus);
    Thread.sleep(500);
    System.out.println("Strength bonus: " + strBonus);
    Thread.sleep(500);
    System.out.println("Constitution bonus: " + liveBonus);
    Thread.sleep(500);
    System.out.println("Armor level: " + armor);
    Thread.sleep(500);
    System.out.println("Armor class: " + armorClass + "\n");
    Thread.sleep(500);

    System.out.println("You walk into a bar, there are two people, one has a knife. Do you jump the person with a knife? (YES or NO)");
    choice = in.next().substring(0,1).toUpperCase();

    while (!choice.equals("Y") && !choice.equals("N")) {
      System.out.println("You're bad at making decisions.");

      Thread.sleep(1000);

      throw new RuntimeException("You're bad, so GAME OVER");
    }

    if (choice.equals("Y")) {
      System.out.println("You attempt to mug a person that has a weapon even though you dont have one yourself. You're kinda stupid.");

      enemyHealth = 10;
      luck = luckRoll(luckBonus);

      if (luck == 20) {
        System.out.println("Lucky bastard. You seamlessly take the knife from the person.\nThey die instantly since you accidentally cut off their head. Good job!");
        Thread.sleep(2000);
        instance1 = true;

      }
      else if (15 <= luck && luck < 20) {
        System.out.println("You're at an advantage as you went for the knife. You didn't grab it successfully,\nbut the other person successfully staggers.");
        Thread.sleep(2000);
      }
      else if (1 < luck && luck < 10) {
        System.out.println("The person jumps back before you get a chance to even grab the knife. The person goes to stab you. Dumbass. ");

        Thread.sleep(2000);
        damage = roll6(0, 1)-armor;
        health -= damage;

        System.out.println("You take " + damage + " damage. You have " + health + " health left.");
        Thread.sleep(1000);
      }
      else if (luck <= 1) {
        System.out.println("You trip, fall, and break your leg, and the person starts stabbing you,\nas they randomly turned sadistic and decided to perform some casual ethnic cleansing.");
        health = 0;
        Thread.sleep(2000);
      }
      else {
        System.out.println("You're both left at a stalemate, as you attempt to grab the knife unsuccessfully, but you managed to leave no obvious openings.");
        Thread.sleep(2000);
      }

      if(health == 0) {
        Thread.sleep(2000);
        throw new RuntimeException("You died, so GAME OVER");
      }

      if (instance1) {
        System.out.print("As the person with the knife lays on the floor, everyone just kinda stares at you while wondering what he heck is going on.\nNice, you probably look like a bufoon right now, since you won this fight out of pure luck.");
        Thread.sleep(2000);
      }
      else {
        System.out.print("You both have a chance to react. What do you do? ");
        choice = in.next().toLowerCase();

        for (slow = 0; !choice.equals("fight") && !choice.equals("attack") && !choice.equals("run") && !choice.equals("leave") && slow < 3; slow++) {
          System.out.print("Oak says no. What do you do (fight or run)? ");
          choice = in.next().toLowerCase();
        }

        if (slow == 3) {
          System.out.print("You're so slow at decision making that the person gains the upper hand and attempts to stab you. ");
          Thread.sleep(1000);

          attacked = (int)(Math.random()*20);
          if (attacked == 1) {
            System.out.println("The attack fails horribly. Figures, this entire situation is dumb, may as well be dumber.\nThe person attempts to knife you, then drops the knife on their foot on accident, causing them to scream in pain.");
            Thread.sleep(2000);
            System.out.println("In the meantime, they manage to trip, slamming their head facefirst into a table.\nAs they roll onto the floor facing up, you see their skull is broken, as you can see brain matter. That was a hard table.");

            instance1 = true;
          }
          else {
            attacked -= armorClass;

            if (attacked <= 10) {

              System.out.print("You were hit by the knife. ");

              damage = roll6(2, 1)-armor;
              health -= damage;

              System.out.println("You take " + damage + " damage. You have " + health + " health left.");
            }
            else {
              System.out.println("The knife misses, you have a chance to attack! Do you? ");
              choice = in.next().substring(0, 1).toUpperCase();

              for (slow = 0; !choice.equals("y") && !choice.equals("n") && slow < 3; slow++) {
                System.out.print("Oak says you need to choose yes or no or you may die. ");
                choice = in.next().toLowerCase();
              }
            }
          }
        }
        
        if (choice.equals("fight") || choice.equals("attack")) {
            
            System.out.println("You choose to fight. Good luck with that.");
            Thread.sleep(1000);

            hit = armorRoll(strBonus, enemyAc);
            
            attacked = (int)(Math.random()*20);
        
            if (hit) {
                    System.out.println("You managed to land a hit on him, knocking him a little.");
                    damage = (int)(Math.random()*5);
                    enemyHealth -= damage;
                    Thread.sleep(1000);
                    System.out.println("You deal " + damage + " damage.");
              }
            else {
              System.out.println("You missed. They go to attack you. ");

              damage = roll6(2, 1)-armor;
              health -= damage;

              Thread.sleep(1000);
              System.out.println("You take " + damage + " damage. You have " + health + " health left.");
            }
          }
          else {
            System.out.println("You get to go outside of the bar for this one.");
            System.out.println("Doesn't work as of right now. Come back later!");
            instance1 = true;
            }
        }
        if (!instance1) {
          System.out.println("At this point, you both are circling around each other, the person with the knife is waiting for you to make your move...");

          while (!instance1) {
            System.out.print("What do you do? ");
            choice = in.next().toLowerCase();   

            if (choice.equals("fight") || choice.equals("attack")) {
            
            System.out.println("You choose to fight again.");
            Thread.sleep(1000);

            hit = armorRoll(strBonus, enemyAc);
            
            attacked = (int)(Math.random()*20);
        
            if (hit) {
                    System.out.println("You managed to land another hit on him. Nice");
                    damage = roll6(0, 1)+strBonus;
                    enemyHealth -= damage;
                    Thread.sleep(1000);
                    System.out.println("You deal " + damage + " damage.");
              }
            else {
              System.out.println("You missed. This feels very one sided...");
              }
              if (enemyHealth <= 0) {
              instance1 = true;
              }
            else {
              damage = roll6(2, 1)-armor;
              health -= damage;

              Thread.sleep(1000);
              System.out.println("You take " + damage + " damage. You have " + health + " health left.");
              }
            }
            else {
              System.out.println("You get to go outside of the bar for this one.");
              System.out.println("Doesn't work as of right now. Come back later!");
              instance1 = true;
              }
            if (health <= 0) {
              throw new RuntimeException("You died, so GAME OVER");
            }
          }
        }

    }
    else {
      System.out.println("You get to go outside of the bar for this one.");
      System.out.println("Doesn't work as of right now. Come back later!");
      instance1 = true;
    }
  }

  public static int luckRoll(int luckBonus) {
    int luck = (int)(Math.random()*20)+1;
      if (luck > 1) {
        luck = luck + luckBonus;
      }
    
    return luck;
  }

  public static boolean armorRoll(int attackBonus, int enemyAc) {

    int attacked = (int)(Math.random()*20)+1;

    if (attacked == 20) {
      return true;
    }
    if (attacked > 1) {
      attacked += attackBonus;
    }

    if (attacked >= enemyAc) {
      return true;
    }
    else {
      return false;
    }
  }

  public static int roll20(int bonus, boolean canFail) {
    int roll = (int)(Math.random()*20)+1;

    if (roll > 1 || canFail) {
      roll += bonus;
    }
    return roll;
  }

  public static int roll6(int bonus, int multiply) {
    int roll = (int)(Math.random()*(6*multiply))+1;

    return roll+bonus;
  }

  public static int roll8(int bonus, int multiply) {
    int roll = (int)(Math.random()*(8*multiply))+1;

    return roll+bonus;
  }

  public static int roll12(int bonus, int multiply) {
    int roll = (int)(Math.random()*(12*multiply))+1;

    return roll+bonus;
  }
}