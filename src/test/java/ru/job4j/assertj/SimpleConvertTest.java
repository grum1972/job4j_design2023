package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import org.assertj.core.data.Index;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).hasSize(5)
                .containsSequence("second", "three")
                .startsWith("first");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(set).hasSize(5)
                .containsExactlyInAnyOrder("first", "second", "three", "four", "five");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map).hasSize(5)
                .containsKeys("first", "second", "three")
                .containsValues(3, 4)
                .doesNotContainKey("six")
                .doesNotContainValue(5)
                .containsEntry("four", 3);
        assertThat(map.values()).isNotNull()
                .allMatch(e -> e < 10)
                .anyMatch(e -> e == 4)
                .noneMatch(e -> e < 0);
    }
}