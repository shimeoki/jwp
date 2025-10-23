package io.github.shimeoki.jwp.infra.store;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

public final class TeeInputStream extends FilterInputStream {

    private final OutputStream out;

    public TeeInputStream(final InputStream in, final OutputStream out) {
        super(in);
        this.out = Objects.requireNonNull(out);
    }

    @Override
    public int read() throws IOException {
        final var read = super.read();

        if (read != -1) {
            out.write(read);
        }

        return read;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        final var read = super.read(b, off, len);

        if (read > 0) {
            out.write(b, off, read);
        }

        return read;
    }

    @Override
    public void close() throws IOException {
        try {
            super.close();
        } finally {
            out.close();
        }
    }
}
