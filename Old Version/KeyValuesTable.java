import java.util.*;

class KeyValuesTable {

  private ArrayList<Integer> keys = new ArrayList<Integer>();
  private ArrayList<ArrayList<Integer>> values = new ArrayList<ArrayList<Integer>>();

  public void addKeyAndValue(int k, int v) {
    boolean found = false;
    for (int i = 0; i < keys.size(); i++) {
      if (keys.get(i) == k) {
        values.get(i).add(v);
        found = true;
      }
    }
    if (!found) {
      keys.add(k);
      ArrayList<Integer> newl = new ArrayList<Integer>();
      newl.add(v);
      values.add(newl);
    }
  }

  public boolean hasValue(int v) {
    for (int i = 0; i < values.size(); i++) {
      for (int j = 0; j < values.get(i).size(); j++) {
        if (values.get(i).get(j) == v) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean hasKey(int k) {
    for (int i = 0; i < keys.size(); i++) {
      if (keys.get(i) == k) {
        return true;
      }
    }
    return false;
  }

  public ArrayList<Integer> getValues(int k) {
    for (int i = 0; i < keys.size(); i++) {
      //System.out.println(Arrays.toString(keys.toArray()));
      if (keys.get(i) == k) {
        return values.get(i);
      }
    }
    return new ArrayList<Integer>();
  }

  public int getValueCount(int k) {
    for (int i = 0; i < keys.size(); i++) {
      if (keys.get(i) == k) {
        return values.get(i).size();
      }
    }
    return -1;
  }

  public ArrayList<Integer> getKeys() {
    return keys;
  }

  public int totalCombination() {
    int tot = 0;
    int sum;
    for (int i = 0; i < values.size(); i++) {
        sum = 0;
        for (int j = 1; j <= values.get(i).size()-1; j++) {
            sum += j;
        }
        tot += sum;
    }
    return tot;
  }

  public void reset() {
    keys = new ArrayList<Integer>();
    values = new ArrayList<ArrayList<Integer>>();
  }
}
