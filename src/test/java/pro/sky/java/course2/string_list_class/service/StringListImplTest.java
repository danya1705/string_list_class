package pro.sky.java.course2.string_list_class.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.string_list_class.exception.ArrayIsFullException;
import pro.sky.java.course2.string_list_class.exception.ElementNotFound;
import pro.sky.java.course2.string_list_class.exception.NullArgumentException;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class StringListImplTest {

    public static final int TEST_ARRAY_SIZE = 10;

    StringList stringList = new StringListImpl(TEST_ARRAY_SIZE);

    @BeforeEach
    void checkArrayIsClear() {
        assertThat(stringList.size()).isEqualTo(0);
    }

    @AfterEach
    void clearStringList() {
        stringList.clear();
    }

    @Test
    void shouldReturnNullArgumentException() {
        assertThatExceptionOfType(NullArgumentException.class).isThrownBy(() -> stringList.add(null));
        assertThatExceptionOfType(NullArgumentException.class).isThrownBy(() -> stringList.add(0, null));
        assertThatExceptionOfType(NullArgumentException.class).isThrownBy(() -> stringList.set(0, null));
        assertThatExceptionOfType(NullArgumentException.class).isThrownBy(() -> stringList.remove(null));
        assertThatExceptionOfType(NullArgumentException.class).isThrownBy(() -> stringList.contains(null));
        assertThatExceptionOfType(NullArgumentException.class).isThrownBy(() -> stringList.indexOf(null));
        assertThatExceptionOfType(NullArgumentException.class).isThrownBy(() -> stringList.lastIndexOf(null));
        assertThatExceptionOfType(NullArgumentException.class).isThrownBy(() -> stringList.equals(null));
    }

    @Test
    void shouldReturnIndexOutOfBoundsException() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> stringList.add(-1, "test"));
        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> stringList.add(stringList.size() + 1, "test"));
        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> stringList.set(-1, "test"));
        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> stringList.set(stringList.size() + 1, "test"));
        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> stringList.remove(-1));
        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> stringList.remove(stringList.size() + 1));
        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> stringList.get(-1));
        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> stringList.get(stringList.size() + 1));
    }

    @Test
    void shouldReturnArrayIsFullExceptionWhenAdd() {

        for (int i = 0; i < TEST_ARRAY_SIZE; i++) {
            stringList.add("test");
        }
        assertThat(stringList.size()).isEqualTo(TEST_ARRAY_SIZE);

        assertThatExceptionOfType(ArrayIsFullException.class).isThrownBy(() -> stringList.add("test"));
        assertThatExceptionOfType(ArrayIsFullException.class).isThrownBy(() -> stringList.add(0, "test"));
    }

    @Test
    void shouldReturnElementNotFoundWhenRemove() {

        stringList.add("test");
        assertThat(stringList.size()).isEqualTo(1);
        assertThat(stringList.get(0)).isEqualTo("test");

        assertThatExceptionOfType(ElementNotFound.class).isThrownBy(() -> stringList.remove("not test"));
    }

    @Test
    void addPositiveTest() {
        stringList.add("test");
        assertThat(stringList.size()).isEqualTo(1);
        assertThat(stringList.get(0)).isEqualTo("test");
    }

    @Test
    void addToPositionPositiveTest() {
        stringList.add("test1");
        stringList.add("test2");
        stringList.add("test3");
        assertThat(stringList.size()).isEqualTo(3);
        stringList.add(2, "test2/3");
        assertThat(stringList.size()).isEqualTo(4);
        String[] expected = new String[]{"test1", "test2", "test2/3", "test3"};
        assertThat(stringList.toArray()).isEqualTo(expected);
    }

    @Test
    void setPositiveTest() {
        stringList.add("test1");
        stringList.add("test2");
        stringList.add("test3");
        assertThat(stringList.size()).isEqualTo(3);
        stringList.set(1, "test2 new");
        assertThat(stringList.size()).isEqualTo(3);
        String[] expected = new String[]{"test1", "test2 new", "test3"};
        assertThat(stringList.toArray()).isEqualTo(expected);
    }

    @Test
    void removePositiveTest() {
        stringList.add("test1");
        stringList.add("test2");
        stringList.add("test3");
        assertThat(stringList.size()).isEqualTo(3);
        stringList.remove("test2");
        assertThat(stringList.size()).isEqualTo(2);
        String[] expected = new String[]{"test1", "test3"};
        assertThat(stringList.toArray()).isEqualTo(expected);
    }

    @Test
    void testRemovePositiveTest() {
        stringList.add("test1");
        stringList.add("test2");
        stringList.add("test3");
        assertThat(stringList.size()).isEqualTo(3);
        stringList.remove(1);
        assertThat(stringList.size()).isEqualTo(2);
        String[] expected = new String[]{"test1", "test3"};
        assertThat(stringList.toArray()).isEqualTo(expected);
    }

    @Test
    void containsPositiveTest() {
        stringList.add("test");
        assertThat(stringList.contains("test")).isEqualTo(true);
    }

    @Test
    void indexOfPositiveTest() {
        stringList.add("test");
        stringList.add("test");
        assertThat(stringList.indexOf("test")).isEqualTo(0);
        assertThat(stringList.indexOf("not test")).isEqualTo(-1);
    }

    @Test
    void lastIndexOfPositiveTest() {
        stringList.add("test");
        stringList.add("test");
        assertThat(stringList.lastIndexOf("test")).isEqualTo(1);
        assertThat(stringList.lastIndexOf("not test")).isEqualTo(-1);
    }

    @Test
    void getPositiveTest() {
        stringList.add("test");
        assertThat(stringList.get(0)).isEqualTo("test");
    }

    @Test
    void testEqualsPositiveTest() {
        stringList.add("test1");
        stringList.add("test2");
        stringList.add("test3");

        StringList expected = new StringListImpl(3);
        expected.add("test1");
        expected.add("test2");
        expected.add("test3");
        assertThat(stringList.equals(expected)).isEqualTo(true);

        expected.set(0,"test666");
        assertThat(stringList.equals(expected)).isEqualTo(false);
    }

    @Test
    void sizePositiveTest() {
        stringList.add("test");
        assertThat(stringList.size()).isEqualTo(1);
        stringList.add("test");
        stringList.add("test");
        assertThat(stringList.size()).isEqualTo(3);
    }

    @Test
    void isEmptyPositiveTest() {
        assertThat(stringList.isEmpty()).isEqualTo(true);
        stringList.add("test");
        assertThat(stringList.isEmpty()).isEqualTo(false);
    }

    @Test
    void clearPositiveTest() {
        stringList.add("test1");
        stringList.add("test2");
        stringList.add("test3");
        assertThat(stringList.toArray()).isNotEmpty();
        stringList.clear();
        assertThat(stringList.toArray()).isEmpty();
        assertThat(stringList.size()).isEqualTo(0);
    }

    @Test
    void toArrayPositiveTest() {
        stringList.add("test1");
        stringList.add("test2");
        stringList.add("test3");
        String[] expected = new String[]{"test1", "test2", "test3"};
        assertThat(stringList.toArray()).isEqualTo(expected);
    }

    @Test
    void extendArrayTest() {
        StringList stringListExtended = new StringListImpl(TEST_ARRAY_SIZE,true);
        for (int i = 0; i < TEST_ARRAY_SIZE; i++) {
            stringListExtended.add("test");
        }
        stringListExtended.add("test");
        assertThat(stringListExtended.size()).isGreaterThan(TEST_ARRAY_SIZE);
    }
}