package io.github.shimeoki.jwp.app.actions.wallpapercreate;

import java.io.InputStream;

public record CreateWallpaperCommand(InputStream image, String format) {
}
