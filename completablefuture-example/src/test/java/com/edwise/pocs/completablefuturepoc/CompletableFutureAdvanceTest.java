package com.edwise.pocs.completablefuturepoc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompletableFutureAdvanceTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(CompletableFutureAdvanceTest.class);

    private ExecutorService executor;

    @Before
    public void setUp() {
        executor = Executors.newFixedThreadPool(5);
    }

    @After
    public void shutDown() throws InterruptedException {
        Sleep.sleepSeconds(5);
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    @Test
    public void testFutureThenApply() {
        CompletableFuture<String> futureAsync = CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Comenzando supplyAsync for thenApply...");
            Sleep.sleepSeconds(2);
            LOGGER.info("Terminado supplyAsync for thenApply!");
            return "Terminado";
        }, executor);

        CompletableFuture<String> futureApply = futureAsync.thenApplyAsync(s -> {
            LOGGER.info("Comenzando applyAsync...");
            Sleep.sleepSeconds(2);
            LOGGER.info("Terminado applyAsync!");
            return s.toUpperCase();
        }, executor);

        futureApply.whenCompleteAsync((s, e) -> LOGGER.info("Resultado applyAsync: {}", s), executor);
        LOGGER.info("Terminado main thread");
    }

    @Test
    public void testFutureThenAccept() {
        CompletableFuture<String> futureAsync = CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Comenzando supplyAsync for thenAccept...");
            Sleep.sleepSeconds(2);
            LOGGER.info("Terminado supplyAsync for thenAccept!");
            return "Terminado";
        }, executor);

        futureAsync.thenAcceptAsync(s -> {
            LOGGER.info("Comenzando thenAccept...");
            Sleep.sleepSeconds(2);
            LOGGER.info("Terminado thenAccept!");
            LOGGER.info("Resultado: {}", s);
        }, executor);
        LOGGER.info("Terminado main thread");
    }

    @Test
    public void testFutureThenRun() {
        CompletableFuture<Void> futureRun = CompletableFuture.runAsync(() -> {
            LOGGER.info("Comenzando runAsync for thenRun...");
            Sleep.sleepSeconds(2);
            LOGGER.info("Terminado runAsync for thenRun!");
        }, executor);

        futureRun.thenRunAsync(() -> {
            LOGGER.info("Comenzando thenRun...");
            Sleep.sleepSeconds(2);
            LOGGER.info("Terminado thenRun!");
        }, executor);
        LOGGER.info("Terminado main thread");
    }

    @Test
    public void testFutureWithExceptionWhenComplete() {
        CompletableFuture<String> futureAsync = CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Comenzando supplyAsync with exception...");
            Sleep.sleepSeconds(2);
            LOGGER.info("Terminado supplyAsync with exception!");
            throw new RuntimeException("Error en el futuro");
        }, executor);

        futureAsync.whenCompleteAsync((s, e) -> {
            if (e != null) {
                LOGGER.error("Resultado con excepción!!", e);
            } else {
                LOGGER.info("Resultado applyAsync: {}", s);
            }
        }, executor);
        LOGGER.info("Terminado main thread");
    }

    @Test
    public void testFutureWithExceptionExceptionally() {
        CompletableFuture<String> futureAsync = CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Comenzando supplyAsync with exception...");
            Sleep.sleepSeconds(2);
            LOGGER.info("Terminado supplyAsync with exception!");
            throw new RuntimeException("Error en el futuro");
        }, executor);

        CompletableFuture<String> futureEx = futureAsync.exceptionally(e -> {
            LOGGER.error("Resultado con excepción!!", e);
            return "StringPorDefecto";
        });

        futureEx.whenCompleteAsync((s, e) -> LOGGER.info("Resultado futureEx: {}", s), executor);
        LOGGER.info("Terminado main thread");
    }

    @Test
    public void testFutureWithExceptionHandle() {
        CompletableFuture<String> futureAsync = CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Comenzando supplyAsync with exception...");
            Sleep.sleepSeconds(2);
            LOGGER.info("Terminado supplyAsync with exception!");
            throw new RuntimeException("Error en el futuro");
        }, executor);

        CompletableFuture<String> handledFuture = futureAsync.handleAsync((s, e) -> {
            if (e != null) {
                LOGGER.error("Resultado con excepción!!", e);
                return "StringPorDefecto";
            } else {
                LOGGER.info("Resultado: {}", s);
                return s;
            }
        }, executor);

        handledFuture.whenCompleteAsync((s, e) -> LOGGER.info("Resultado handle: {}", s), executor);
        LOGGER.info("Terminado main thread");
    }

    @Test
    public void testFutureThenCompose() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Comenzando supplyAsync for thenCompose...");
            Sleep.sleepSeconds(2);
            LOGGER.info("Terminado supplyAsync for thenCompose!");
            return "Terminado";
        }, executor);

        CompletableFuture<String> fCompose =
                future1.thenComposeAsync(s -> CompletableFuture.supplyAsync(() -> {
                            LOGGER.info("Comenzando thenCompose...");
                            Sleep.sleepSeconds(2);
                            LOGGER.info("Terminado thenCompose!");
                            return s.concat(" + Terminado other");
                        }, executor),
                        executor);

        fCompose.whenCompleteAsync((s, e) -> LOGGER.info("Resultado thenCompose: {}", s), executor);
        LOGGER.info("Terminado main thread");
    }

    @Test
    public void testFutureThenCombine() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Comenzando future1 for thenCombine...");
            Sleep.sleepSeconds(2);
            LOGGER.info("Terminado future1 for thenCombine!");
            return "Terminado";
        }, executor);

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Comenzando future2 for thenCombine...");
            Sleep.sleepSeconds(1);
            LOGGER.info("Terminado future2 for thenCombine!");
            return "Terminado other";
        }, executor);

        CompletableFuture<String> fCombine =
                future1.thenCombineAsync(future2, (s1, s2) -> {
                    LOGGER.info("En el thenCombine, recibidos results: {}, {}", s1, s2);
                    return s1 + s2;
                }, executor);

        fCombine.whenCompleteAsync((s, e) -> LOGGER.info("Resultado thenCombine: {}", s), executor);
        LOGGER.info("Terminado main thread");
    }

    @Test
    public void testFutureThenAcceptBoth() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Comenzando future1 for thenAcceptBoth...");
            Sleep.sleepSeconds(2);
            LOGGER.info("Terminado future1 for thenAcceptBoth!");
            return "Terminado";
        }, executor);

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Comenzando future2 for thenAcceptBoth...");
            Sleep.sleepSeconds(1);
            LOGGER.info("Terminado future2 for thenAcceptBoth!");
            return "Terminado other";
        }, executor);

        future1.thenAcceptBothAsync(future2, (s1, s2) ->
                        LOGGER.info("En el thenAcceptBoth, recibidos results: {}, {}", s1, s2)
                , executor);

        LOGGER.info("Terminado main thread");
    }

    @Test
    public void testFutureRunAfterBoth() {
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            LOGGER.info("Comenzando future1 for runAfterBoth...");
            Sleep.sleepSeconds(2);
            LOGGER.info("Terminado future1 for runAfterBoth!");
        }, executor);

        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            LOGGER.info("Comenzando future2 for runAfterBoth...");
            Sleep.sleepSeconds(1);
            LOGGER.info("Terminado future2 for runAfterBoth!");
        }, executor);

        future1.runAfterBothAsync(future2, () -> LOGGER.info("En el runAfterBoth, futuros terminados.")
                , executor);

        LOGGER.info("Terminado main thread");
    }

    @Test
    public void testFutureAcceptEither() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Comenzando future1 for acceptEither...");
            Sleep.sleepSeconds(3);
            LOGGER.info("Terminado future1 for acceptEither!");
            return "Segundo";
        }, executor);

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Comenzando future2 for acceptEither...");
            Sleep.sleepSeconds(1);
            LOGGER.info("Terminado future2 for acceptEither!");
            return "Primero";
        }, executor);

        future1.acceptEitherAsync(future2, (s) ->
                        LOGGER.info("En el acceptEither, recibido el primer resultado: {}", s)
                , executor);

        LOGGER.info("Terminado main thread");
    }

    @Test
    public void testFutureRunAfterEither() {
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            LOGGER.info("Comenzando future1 for runAfterEither...");
            Sleep.sleepSeconds(3);
            LOGGER.info("Terminado future1 for runAfterEither!");
        }, executor);

        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            LOGGER.info("Comenzando future2 for runAfterEither...");
            Sleep.sleepSeconds(1);
            LOGGER.info("Terminado future2 for runAfterEither!");
        }, executor);

        future1.runAfterEitherAsync(future2, () -> LOGGER.info("En el runAfterEither, primero terminado.")
                , executor);

        LOGGER.info("Terminado main thread");
    }

    @Test
    public void testFutureApplyToEither() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Comenzando future1 for applyToEither...");
            Sleep.sleepSeconds(3);
            LOGGER.info("Terminado future1 for applyToEither!");
            return "Segundo";
        }, executor);

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Comenzando future2 for applyToEither...");
            Sleep.sleepSeconds(1);
            LOGGER.info("Terminado future2 for applyToEither!");
            return "Primero";
        }, executor);

        CompletableFuture<String> applyToEitherFuture = future1.applyToEitherAsync(future2, s -> {
            LOGGER.info("Comenzando applyToEither...");
            Sleep.sleepSeconds(1);
            LOGGER.info("Terminado applyToEither!");
            return s.toUpperCase();
        }, executor);

        applyToEitherFuture.whenCompleteAsync((s, e) -> LOGGER.info("Resultado applyToEither: {}", s),
                executor);
        LOGGER.info("Terminado main thread");
    }

    @Test
    public void testFutureAllOf() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Comenzando future1 for allOf...");
            Sleep.sleepSeconds(2);
            LOGGER.info("Terminado future1 for allOf!");
            return "Terminado future1";
        }, executor);

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Comenzando future2 for allOf...");
            Sleep.sleepSeconds(1);
            LOGGER.info("Terminado future2 for allOf!");
            return "Terminado future2";
        }, executor);

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Comenzando future3 for allOf...");
            Sleep.sleepSeconds(3);
            LOGGER.info("Terminado future3 for allOf!");
            return "Terminado future3";
        }, executor);

        CompletableFuture<Void> all = CompletableFuture.allOf(future1, future2, future3);

        all.whenCompleteAsync((s, e) -> LOGGER.info("Resultado all: {}", s), executor);
        LOGGER.info("Terminado main thread");
    }

    @Test
    public void testFutureAnyOf() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Comenzando future1 for allOf...");
            Sleep.sleepSeconds(2);
            LOGGER.info("Terminado future1 for allOf!");
            return "Terminado future1";
        }, executor);

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Comenzando future2 for allOf...");
            Sleep.sleepSeconds(1);
            LOGGER.info("Terminado future2 for allOf!");
            return "Terminado future2";
        }, executor);

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Comenzando future3 for allOf...");
            Sleep.sleepSeconds(3);
            LOGGER.info("Terminado future3 for allOf!");
            return "Terminado future3";
        }, executor);

        CompletableFuture<Object> all = CompletableFuture.anyOf(future1, future2, future3);

        all.whenCompleteAsync((s, e) -> LOGGER.info("Resultado any: {}", s), executor);
        LOGGER.info("Terminado main thread");
    }
}
