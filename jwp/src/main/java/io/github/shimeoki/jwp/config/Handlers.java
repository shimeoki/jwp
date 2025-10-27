package io.github.shimeoki.jwp.config;

import io.github.shimeoki.jwp.app.Worker;
import io.github.shimeoki.jwp.app.actions.sourcecreate.CreateSourceHandler;
import io.github.shimeoki.jwp.app.actions.sourcedelete.DeleteSourceHandler;
import io.github.shimeoki.jwp.app.actions.sourcelist.ListSourcesHandler;
import io.github.shimeoki.jwp.app.actions.tagcreate.CreateTagHandler;
import io.github.shimeoki.jwp.app.actions.tagdelete.DeleteTagHandler;
import io.github.shimeoki.jwp.app.actions.taglist.ListTagsHandler;
import io.github.shimeoki.jwp.app.actions.tagrename.RenameTagHandler;
import io.github.shimeoki.jwp.app.actions.wallpapercreate.CreateWallpaperHandler;
import io.github.shimeoki.jwp.app.actions.wallpaperdelete.DeleteWallpaperHandler;
import io.github.shimeoki.jwp.app.actions.wallpaperfind.FindWallpaperHandler;
import io.github.shimeoki.jwp.app.actions.wallpapershow.ShowWallpaperHandler;

public record Handlers(
        CreateTagHandler createTag,
        DeleteTagHandler deleteTag,
        ListTagsHandler listTags,
        RenameTagHandler renameTag,
        CreateWallpaperHandler createWallpaper,
        DeleteWallpaperHandler deleteWallpaper,
        ShowWallpaperHandler showWallpaper,
        FindWallpaperHandler findWallpaper,
        CreateSourceHandler createSource,
        DeleteSourceHandler deleteSource,
        ListSourcesHandler listSources) {

    public static Handlers fromWorker(final Worker<Provider> w) {
        return new Handlers(
                new CreateTagHandler(w::work),
                new DeleteTagHandler(w::work),
                new ListTagsHandler(w::work),
                new RenameTagHandler(w::work),
                new CreateWallpaperHandler(w::work),
                new DeleteWallpaperHandler(w::work),
                new ShowWallpaperHandler(w::work),
                new FindWallpaperHandler(w::work),
                new CreateSourceHandler(w::work),
                new DeleteSourceHandler(w::work),
                new ListSourcesHandler(w::work));
    }
}
