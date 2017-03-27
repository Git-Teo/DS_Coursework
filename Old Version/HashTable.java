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

  public void generateHashTables() {
    InnerHashTable iht;
    for (int i = 0; i < slots.size(); i++) {
      m = getKeyCount(slots.indexOf(i));
      iht = new InnerHashTable(m);
      while (iht.hasCollisions(keys.get(slots.indexOf(i)))) {
        iht.newHashFunction();
      }
      hashtables.set(slots.indexOf(i), iht);
    }
  }

  public void printHashTable() {
    InnerHashTable tablerow;
    for (int i = 0; i < m; i++) {
      if (slots.contains(i)) {
        System.out.print("operation slot "+i+":"+" ");
        tablerow = hashtables.get(slots.indexOf(i));
        tablerow.printKeys();
        System.out.println();
      }
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

  //Hash Table Operations
  public int[] insert(int n) {
    op = hash.getHashValue(n);
    ip = hashtables.get(slots.indexOf(op)).insert(n);
    return new int[]{op, ip};
  }

  public int[] locate(int n) {
    op = hash.getHashValue(n);

    return new int[]{1,2};
  }

  public int[] delete(int n) {
    return new int[]{1,2};
  }

  //Hash Function functions
  public int getHashValue(int n) {
    return hash.getHashValue(n);
  }

  public void newHashFunction() {
    hash.newHashFunction();
  }

  public void printParams(String io) {
    hash.printParams(io);
  }

  public void addKeyToSlot(int hv, int k) {
    boolean found = false;
    for (int i = 0; i < slots.size(); i++) {
      if (slots.contains(hv)) {
        keys.get(slots.indexOf(hv)).add(k);
        found = true;
      }
    }
    if (!found) {
      slots.add(hv);
      ArrayList<Integer> newl = new ArrayList<Integer>();
      newl.add(k);
      keys.add(newl);
    }
  }

  public boolean hasSlot(int s) {
    if (slots.contains(s)) {
      return true;
    }
    return false;
  }

  public boolean hasKey(int k) {
    for (int i = 0; i < keys.size(); i++) {
      if (keys.get(i).contains(k)) {
        return true;
      }
    }
    return false;
  }

  public ArrayList<Integer> getSlots() {
    return slots;
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


  public int totalCombination() {
    int tot = 0;
    int sum;
    for (int i = 0; i < keys.size(); i++) {
        sum = 0;
        for (int j = 1; j <= keys.get(i).size()-1; j++) {
            sum += j;
        }
        tot += sum;
    }
    return tot;
  }

  public void reset() {
    slots = new ArrayList<Integer>();
    keys = new ArrayList<ArrayList<Integer>>();
    hashtables = new ArrayList<InnerHashTable>();
  }
}
