package lambdasinaction.chap14;

public class PersistentTreeFinal {

  private final String key;
  private final int val;
  private final PersistentTreeFinal left, right;

  public PersistentTreeFinal(String k, int v, PersistentTreeFinal l, PersistentTreeFinal r) {
    key = k;
    val = v;
    left = l;
    right = r;
  }

  public static int lookup(String k, int defaultVal, PersistentTreeFinal t) {
    if (t == null)
      return defaultVal;
    if (k.equals(t.key))
      return t.val;
    return lookup(k, defaultVal, k.compareTo(t.key) < 0 ? t.left : t.right);
  }

  public static PersistentTreeFinal fupdate(String k, int newval, PersistentTreeFinal t) {
    return (t == null) ?
        new PersistentTreeFinal(k, newval, null, null) :
        k.equals(t.key) ?
            new PersistentTreeFinal(k, newval, t.left, t.right) :
            k.compareTo(t.key) < 0 ?
                new PersistentTreeFinal(t.key, t.val, fupdate(k, newval, t.left), t.right) :
                new PersistentTreeFinal(t.key, t.val, t.left, fupdate(k, newval, t.right));
  }
}