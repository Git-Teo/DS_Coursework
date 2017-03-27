import java.util.*;

class HashTable {

  //number of keys and therefore size of hash table
  private int m;
  //outer and inner pointers
  private int op;
  private int ip;

  //Outer Hash Table Hash Function
  private HashFunction hash;
  //Slot index, indexes the following Data Structures, keys and innerhashtables
  private ArrayList<Integer> slots = new ArrayList<Integer>();
  //Set of Keys
  private ArrayList<ArrayList<Integer>> keys = new ArrayList<ArrayList<Integer>>();
  //Inner Tables
  private ArrayList<InnerHashTable> hashtables = new ArrayList<InnerHashTable>();

  public HashTable(int n) {
    hash = new HashFunction(n);
    m = n;
  }

  // Build Hash Tables based on slots-keys //
  public void generateHashTables() {
    InnerHashTable iht;
    int n;
    boolean moded;
    for (int i = 0; i < slots.size(); i++) {
      moded = false;
      n = getKeyCount(slots.get(i));
      if (n > 0) {
        iht = new InnerHashTable(n*n);
        while (iht.hasCollisions(keys.get(i))) {
          iht.newHashFunction();
          moded = true;
        }
        // Output new Hash Function for Inner Hash Table //
        if (moded) {
          System.out.print("slot "+slots.get(i)+"; ");
          iht.printParams("MODIFIED INNER");
          System.out.println();
        }
        hashtables.add(iht);
      }
    }
  }

  // Output //
  public void printHashTable() {
    InnerHashTable tablerow;
    for (int i = 0; i < m; i++) {
      System.out.print("operation slot "+i+":"+" ");
      if (slots.contains(i)) {
        tablerow = hashtables.get(slots.indexOf(i));
        tablerow.printKeys();
      }
      System.out.println();
    }
  }

  public void printKeyTable() {
    ArrayList<Integer> keyrow;
    System.out.println("KEYS GROUPED ONTO SLOTS: ");
    for (int i = 0; i < m; i++) {
      if (slots.contains(i)) {
        System.out.print("grouping slot "+i+":"+" ");
        keyrow = keys.get(slots.indexOf(i));
        for (int j=0; j < keyrow.size(); j++) {
            System.out.print(keyrow.get(j)+" ");
        }
        System.out.println();
      }
    }
  }

  // Operations //
  public int[] insert(int n) {
    if (hasKey(n)) {
      System.out.println("PERFORM OPERATION: insert "+n);
      op = hash.getHashValue(n);
      ip = hashtables.get(slots.indexOf(op)).insert(n);
      return new int[]{op, ip};
    }
    return new int[]{-1, 0};
  }

  public int[] locate(int n) {
    if (hasKey(n)) {
      System.out.println("PERFORM OPERATION: locate "+n);
      op = hash.getHashValue(n);
      ip = hashtables.get(slots.indexOf(op)).locate(n);
      return new int[]{op, ip};
    }
    return new int[]{-1, 0};
  }

  public int[] delete(int n) {
    if (hasKey(n)) {
      System.out.println("PERFORM OPERATION: delete "+n);
      op = hash.getHashValue(n);
      ip = hashtables.get(slots.indexOf(op)).delete(n);
      return new int[]{op, ip};
    }
    return new int[]{-1, 0};
  }

  // Hash Function Functions //
  public int getHashValue(int n) {
    return hash.getHashValue(n);
  }

  public void newHashFunction() {
    hash.newHashFunction();
  }

  public void printParams(String io) {
    hash.printParams(io);
  }

  // Slot and Key Functions //
  public void addKeyToSlot(int hv, int k) {
    if (slots.contains(hv)) {
      keys.get(slots.indexOf(hv)).add(k);
    } else {
      slots.add(hv);
      ArrayList<Integer> newl = new ArrayList<Integer>();
      newl.add(k);
      keys.add(newl);
    }
  }

  public boolean hasKey(int k) {
    for (int i = 0; i < keys.size(); i++) {
      if (keys.get(i).contains(k)) {
        return true;
      }
    }
    return false;
  }

  public ArrayList<Integer> getKeys(int s) {
    if (slots.contains(s)) {
      return keys.get(slots.indexOf(s));
    }
    return new ArrayList<Integer>();
  }

  public int getKeyCount(int s) {
    if (slots.contains(s)) {
      return keys.get(slots.indexOf(s)).size();
    }
    return -1;
  }

  // Calculate number of collisions //
  public int totalCombination() {
    int tot = 0;
    int sum;

    //System.out.println(keys.size());
    for (int i = 0; i < keys.size(); i++) {
        sum = 0;
        //System.out.println(keys.get(i).size());
        for (int j = 0; j <= keys.get(i).size()-1; j++) {
            sum += j;
        }
        tot += sum;
    }
    return tot;
  }

  // Clear Objects Datastructures //
  public void reset() {
    slots = new ArrayList<Integer>();
    keys = new ArrayList<ArrayList<Integer>>();
    hashtables = new ArrayList<InnerHashTable>();
  }

}
