package io.github.shimeoki.jwp.domain.values;

public enum Format {

    JPEG("jpg"),
    PNG("png");

    private final String value;

    private Format(final String value) {
        this.value = value;
    }

    public static Format fromExtension(final String extension) {
        switch (extension.toLowerCase()) {
            case "jpg", "jpeg":
                return JPEG;
            case "png":
                return PNG;
            default:
                throw new InvalidFormatException(extension);
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
