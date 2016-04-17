package com.edwise.pocs.java8collectingandthen;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class CollectorsCollectingAndThenTest {
    private static final String GERALT = "Geralt";
    private static final String TRISS = "Triss";
    private static final String YENNEFER = "Yennefer";
    private static final String CIRILLA = "Cirilla";
    private static final String DANDELION = "Dandelion";

    private Stream<String> charactersStream;

    @Before
    public void setUp() {
        charactersStream = Stream.of(GERALT, TRISS, YENNEFER, CIRILLA);
    }

    @Test
    public void toListModificableList() {
        List<String> characters = charactersStream.collect(Collectors.toList());

        assertThat(characters).hasSize(4);
        assertThat(characters).containsExactly(GERALT, TRISS, YENNEFER, CIRILLA);
    }

    @Test
    public void toListAndUnmodificableList() {
        List<String> characters = charactersStream.collect(Collectors.toList());
        List<String> charactersImmutable = Collections.unmodifiableList(characters);

        assertThat(charactersImmutable).hasSize(4);
        assertThat(charactersImmutable).containsExactly(GERALT, TRISS, YENNEFER, CIRILLA);
        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(() -> charactersImmutable.add(DANDELION));
    }

    @Test
    public void collectingAndThenToListAndThenUnModificableList() {
        List<String> charactersImmutable = charactersStream
                .collect(Collectors.collectingAndThen(Collectors.toList(), c -> Collections.unmodifiableList(c)));

        assertThat(charactersImmutable).hasSize(4);
        assertThat(charactersImmutable).containsExactly(GERALT, TRISS, YENNEFER, CIRILLA);
        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(() -> charactersImmutable.add(DANDELION));
    }

    @Test
    public void collectingAndThenToListAndThenUnModificableListMethodReference() {
        List<String> charactersImmutable = charactersStream
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));

        assertThat(charactersImmutable).hasSize(4);
        assertThat(charactersImmutable).containsExactly(GERALT, TRISS, YENNEFER, CIRILLA);
        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(() -> charactersImmutable.add(DANDELION));
    }
}