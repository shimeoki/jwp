package io.github.shimeoki.jwp.infra.store;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import io.github.shimeoki.jwp.domain.repositories.Store;
import io.github.shimeoki.jwp.domain.values.Hash;

public final class LocalStore implements Store {

    private final Hasher hasher;
    private final Path root;

    public LocalStore(final String path, final Hasher h) throws IOException {
        hasher = Objects.requireNonNull(h);
        root = Paths.get(path);

        if (!Files.exists(root)) {
            Files.createDirectories(root);
        } else if (!Files.isDirectory(root)) {
            throw new IOException("path is not a directory");
        }
    }

    private Path path(final Hash h) {
        return root.resolve(h.toString());
    }

    @Override
    public InputStream get(final Hash h) throws IOException {
        final var path = path(h);

        if (!Files.exists(path)) {
            throw new IOException("image doesn't exist");
        }

        if (!Files.isRegularFile(path)) {
            throw new IOException("expected image, found something else");
        }

        return Files.newInputStream(path);
    }

    @Override
    public Hash create(final InputStream img) throws IOException {
        final var tmp = Files.createTempFile("jwp-local-store", null);
        Hash hash;

        try (final var out = Files.newOutputStream(tmp);
                final var tee = new TeeInputStream(img, out)) {
            hash = hasher.compute(tee);
        } catch (final Exception e) {
            Files.deleteIfExists(tmp);
            throw e;
        }

        Files.move(tmp, path(hash));
        return hash;
    }

    @Override
    public void delete(final Hash h) throws IOException {
        Files.delete(path(h));
    }

    // TODO: check for hash names for every file in both methods below

    @Override
    public long count() throws IOException {
        try (final var files = Files.list(root)) {
            return files.count();
        }
    }

    public void clear() throws IOException {
        try (final var files = Files.list(root)) {
            files.forEach((f) -> f.toFile().delete());
        }
    }
}
