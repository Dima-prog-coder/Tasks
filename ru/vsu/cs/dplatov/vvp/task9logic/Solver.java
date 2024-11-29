import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solver {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 1, 1, 2, 2, 1);
        System.out.print(isArithmeticProgression(list));
    }

    public static int calculateSequenceDifference(List<Integer> seq) {
        try {
            double d = (double) (findMax(seq) - findMin(seq)) / (seq.size() - 1);
            if ((int) d != d) {
                throw new Exception("Разность прогрессии не целая!");
            }
            return (int) d;
        } catch (Exception e) {
            return -1;
        }
    }

    public static boolean isElInList(List<Integer> list, int el) {
        for (int lstEl : list) {
            if (lstEl == el) {
                return true;
            }
        }
        return false;
    }

    public static List<Integer> isArithmeticProgression(List<Integer> list) {
        int d = calculateSequenceDifference(list);
        if (d < 0) {
            return list;
        }
        int progressionEl = findMin(list);
        int lastProgressionEl = findMax(list);
        while (progressionEl != lastProgressionEl) {
            if (!isElInList(list, progressionEl)) {
                return list;
            }
            progressionEl += d;
        }
        sort(list);
        return list;
    }

    public static int findMax(List<Integer> list) {
        int maxEl = list.get(1);
        for (int el : list) {
            if (maxEl < el) {
                maxEl = el;
            }
        }
        return maxEl;
    }

    public static int findMin(List<Integer> list) {
        int mixEl = list.get(1);
        for (int el : list) {
            if (mixEl > el) {
                mixEl = el;
            }
        }
        return mixEl;
    }

    public static int indexOf(List<Integer> list, int value) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == value) {
                return i;
            }
        }
        return -1;
    }

    public static void sort(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            int minEl = findMin(makeSlice(list, i, list.size()));
            int minElInd = indexOf(list, minEl);
            if (minElInd != i) {
                int curEl = list.get(i);
                list.set(i, minEl);
                list.set(minElInd, curEl);
            }
        }
    }

    public static List<Integer> makeSlice(List<Integer> list, int firstInd, int lastIndExcluded) {
        List<Integer> listSlice = new ArrayList<>();
        for (int i = firstInd; i < lastIndExcluded; i++) {
            listSlice.add(list.get(i));
        }
        return listSlice;
    }

}
