package pro.sky.java.course2.string_list_class.service;

import pro.sky.java.course2.string_list_class.exception.ArrayIsFullException;
import pro.sky.java.course2.string_list_class.exception.ElementNotFound;
import pro.sky.java.course2.string_list_class.exception.NullArgumentException;

import java.util.Arrays;

public class StringListImpl implements StringList {

    private String[] arr;
    private int size;
    private final boolean extension;

    public StringListImpl(int size, boolean extension) {
        arr = new String[size];
        this.size = 0;
        this.extension = extension;
    }

    public StringListImpl(int size) {
        arr = new String[size];
        this.size = 0;
        this.extension = false;
    }

    @Override
    public void printList() {
        System.out.println(Arrays.toString(arr));
    }

    @Override
    public String add(String item) {

        if (item == null) throw new NullArgumentException();
        if (size >= arr.length && !extension) throw new ArrayIsFullException();
        if (size >= arr.length && extension) extendArray();

        arr[size++] = item;
        return arr[size - 1];

    }

    @Override
    public String add(int index, String item) {

        if (item == null) throw new NullArgumentException();
        if (index > size || index < 0) throw new IndexOutOfBoundsException();
        if (size >= arr.length && !extension) throw new ArrayIsFullException();
        if (size >= arr.length && extension) extendArray();

        for (int i = size; i >= index; i--) {
            arr[i+1] = arr[i];
        }
        arr[index] = item;
        size++;
        return arr[index];
    }

    @Override
    public String set(int index, String item) {

        if (item == null) throw new NullArgumentException();
        if (index > size - 1 || index < 0) throw new IndexOutOfBoundsException();

        arr[index] = item;
        return arr[index];
    }

    @Override
    public String remove(String item) {

        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFound();
        } else {
            for (int i = index; i < size; i++) {
                arr[i] = arr[i+1];
            }
            size--;
            return item;
        }
    }

    @Override
    public String remove(int index) {

        if (index > size - 1 || index < 0) throw new IndexOutOfBoundsException();

        String item = arr[index];
        for (int i = index; i < size; i++) {
            arr[i] = arr[i+1];
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(String item) {
        return !(indexOf(item) == -1);
    }

    @Override
    public int indexOf(String item) {

        if (item == null) throw new NullArgumentException();

        for (int i = 0; i < size; i++) {
            if (arr[i] == item) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {

        if (item == null) throw new NullArgumentException();

        for (int i = size; i > 0; --i) {
            if (arr[i] == item) return i;
        }
        return -1;
    }

    @Override
    public String get(int index) {

        if (index > size - 1 || index < 0) throw new IndexOutOfBoundsException();

        return arr[index];

    }

    @Override
    public boolean equals(StringList otherList) {

        if (otherList == null) throw new NullArgumentException();
        if (size != otherList.size()) return false;
        for (int i = 0; i < size; i++) {
            if (arr[i] != otherList.get(i)) return false;
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            arr[i] = null;
        }
        size = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(arr,size);
    }

    private void extendArray() {
        String[] arrTemp = Arrays.copyOf(arr, size);
        arr = Arrays.copyOf(arrTemp, size * 10);
    }
}
