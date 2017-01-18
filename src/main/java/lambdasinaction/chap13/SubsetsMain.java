package lambdasinaction.chap13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SubsetsMain {

  public static List<List<Integer>> insertAll(Integer first, List<List<Integer>> lists) {
    List<List<Integer>> result = new ArrayList<>();
    for (List<Integer> l : lists) {
      List<Integer> copyList = new ArrayList<>();
      copyList.add(first);
      copyList.addAll(l);
      result.add(copyList);
    }
    return result;
  }

  static List<List<Integer>> concat(List<List<Integer>> a, List<List<Integer>> b) {
    List<List<Integer>> r = new ArrayList<>(a);
    r.addAll(b);
    return r;
  }

  public static List<List<Integer>> subsets(List<Integer> l) {
    System.out.println("subsets: " + l);
    if (l.isEmpty()) {
      List<List<Integer>> ans = new ArrayList<>();
      ans.add(Collections.emptyList());
      return ans;
    }
    Integer first = l.get(0);
    System.out.println("first (" + l + "): " + first);
    List<Integer> rest = l.subList(1, l.size());
    List<List<Integer>> subans = subsets(rest);
    System.out.println("subans (" + l + "): " + subans);
    List<List<Integer>> subans2 = insertAll(first, subans);
    System.out.println("subans2 (" + l + "): " + subans2);
    List<List<Integer>> concat = concat(subans, subans2);
    System.out.println("concat (" + l + "): " + concat);
    return concat;
  }

  public static void main(String[] args) {
    List<List<Integer>> subs = subsets(Arrays.asList(1, 4, 9));
    subs.forEach(System.out::println);
  }
}