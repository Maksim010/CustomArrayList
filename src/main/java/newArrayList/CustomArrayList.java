package newArrayList;

import java.util.*;

public class CustomArrayList {
    public static void main(String[] args) {

        ArrayList<String> list2 = new ArrayList<>();
        list2.add(0, "3");
        list2.add(1, "2");
        list2.add(1, "1");
        list2.add(3, "4");

        ArrayListCustom<String> list = new ArrayListCustom<>();
        list.add(0, "Max");
        list.add(1, "Sasha");
        list.add(2, "Niki");
        list.add(3, "Julia");
        list.add(4, "Anton");
        list.add(5, "qwe");

        ArrayListCustom<String> list3 = new ArrayListCustom<>(14);
        System.out.println(list3);


        System.out.println(list.isEmpty());
        list.remove("Max");
        list.remove(0);
        System.out.println(list.get(0));
        list.addAll(list2);
        System.out.println(list2);
        list.sort(String::compareTo);
        list.size();


        System.out.println(Arrays.toString(new ArrayListCustom[]{list}));
    }


    public static class ArrayListCustom<T> {
        private static final int INIT_CAPACITY = 10;
        private Object[] arr ;
        public int size = 0;


        public ArrayListCustom(int capacity) {
            arr=new Object[capacity];
        }

        public ArrayListCustom() {
            arr= new Object[INIT_CAPACITY];
        }

        public int size() {
            return size;
        }

        public T get(int index) {
            return (T) arr[index];
        }

        public void add(int i, T element) {
            if (size == arr.length - 1)
                newArr((int) (arr.length * 1.5 + 1));
            if (i >= 0 && i < arr.length) {

                for (int j = i; j < size; j++) {
                    arr[j + 1] = arr[j];
                }
                size++;
                arr[i] = element;
            }
            if (i > size) {
                arr[size++] = element;
            }
        }

        public void addAll(Collection<? extends T> C) {
            int i = size;
            for (T element : C) {
                add(i, element);
                i++;
            }
        }

        public void clear() {
            for (int i = 0; i < size; i++) {
                arr[i] = null;
            }
            size = 0;
        }

        public void remove(int i) {
            if (i >= 0 && i <= size) {
                for (int j = i; j < size; j++) {
                    arr[j] = arr[j + 1];
                }
                arr[size] = null;
                size--;
            }
        }

        public boolean isEmpty() {
            if (size == 0)
                return true;
            return false;
        }

        public void newArr(int newSize) {
            Object[] newArr = new Object[newSize];
            System.arraycopy(arr, 0, newArr, 0, size);
            arr = newArr;
        }

        public void remove(Object o) {
            for (int i = 0; i <= size; i++) {
                if (o.equals(arr[i])) {
                    for (int j = i; j < size; j++) {
                        arr[j] = arr[j + 1];
                    }
                    arr[size] = null;
                    size--;
                }
            }
        }

        public void sort(Comparator<? super T> C) {
            quickSort((T[]) arr,0,size-1,C);
        }

        private void quickSort(T [] array, int low, int high, Comparator<? super T> comparator) {
            if (low < high ) {
                int splitIndex = split(array, low, high, comparator);
                quickSort(array, low, splitIndex - 1, comparator);
                quickSort(array, splitIndex + 1, high, comparator);
            }
        }

        private int split(T [] array, int low, int high, Comparator<? super T> comparator) {
            T middle = (T) array[high];
            int i = low-1 ;
            for (int j = low; j < high; j++) {
                if (comparator.compare((T) array[j], middle) < 0) {
                    i++;
                    swap(array, i, j);
                }
            }
            swap(array, i+1 , high);
            return i +1;
        }

        private void swap(Object[] array, int i, int j) {
            Object temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }



        @Override
        public String toString() {
            return "arr=" + Arrays.toString(arr) +
                    ", size=" + size ;

        }
    }
}