package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 2);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void whenCubeNumberOfVertices8() {
        Box box = new Box(8, 2);
        int rsl = box.getNumberOfVertices();
        assertThat(rsl).isEqualTo(8);
    }

    @Test
    void whenTetrahedronNumberOfVertices4() {
        Box box = new Box(4, 2);
        int rsl = box.getNumberOfVertices();
        assertThat(rsl).isEqualTo(4);
    }

    @Test
    void isExist() {
        Box box = new Box(8, 2);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void isNotExist() {
        Box box = new Box(7, 2);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void whenCubeAreaIs() {
        Box box = new Box(8, 2);
        assertThat(box.getArea()).isCloseTo(24d, withPrecision(0.01d));
    }

    @Test
    void whenSphereAreaIs() {
        Box box = new Box(0, 1);
        assertThat(box.getArea()).isLessThan(12.6d);
    }

}