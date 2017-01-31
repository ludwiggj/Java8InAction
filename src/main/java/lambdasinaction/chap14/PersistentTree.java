package lambdasinaction.chap14;

public class PersistentTree {

  private String key;
  private int val;
  private PersistentTree left, right;

  public PersistentTree(String k, int v, PersistentTree l, PersistentTree r) {
    key = k;
    val = v;
    left = l;
    right = r;
  }

  public static int lookup(String k, int defaultVal, PersistentTree t) {
    if (t == null)
      return defaultVal;
    if (k.equals(t.key))
      return t.val;
    return lookup(k, defaultVal, k.compareTo(t.key) < 0 ? t.left : t.right);
  }

  public static PersistentTree update(String k, int newVal, PersistentTree t) {
    if (t == null)
      t = new PersistentTree(k, newVal, null, null);
    else if (k.equals(t.key))
      t.val = newVal;
    else if (k.compareTo(t.key) < 0)
      t.left = update(k, newVal, t.left);
    else
      t.right = update(k, newVal, t.right);
    return t;
  }

  public static PersistentTree fupdate(String k, int newval, PersistentTree t) {
    return (t == null) ?
        new PersistentTree(k, newval, null, null) :
        k.equals(t.key) ?
            new PersistentTree(k, newval, t.left, t.right) :
            k.compareTo(t.key) < 0 ?
                new PersistentTree(t.key, t.val, fupdate(k, newval, t.left), t.right) :
                new PersistentTree(t.key, t.val, t.left, fupdate(k, newval, t.right));
  }
}