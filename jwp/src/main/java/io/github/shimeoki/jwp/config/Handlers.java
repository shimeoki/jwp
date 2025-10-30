package io.github.shimeoki.jwp.config;

import io.github.shimeoki.jwp.app.Worker;
import io.github.shimeoki.jwp.app.actions.aliasadd.AddAliasHandler;
import io.github.shimeoki.jwp.app.actions.aliaslist.ListAliasesHandler;
import io.github.shimeoki.jwp.app.actions.aliasremove.RemoveAliasHandler;
import io.github.shimeoki.jwp.app.actions.sourceadd.AddSourceHandler;
import io.github.shimeoki.jwp.app.actions.sourcecreate.CreateSourceHandler;
import io.github.shimeoki.jwp.app.actions.sourcedelete.DeleteSourceHandler;
import io.github.shimeoki.jwp.app.actions.sourcelist.ListSourcesHandler;
import io.github.shimeoki.jwp.app.actions.sourceremove.RemoveSourceHandler;
import io.github.shimeoki.jwp.app.actions.tagadd.AddTagHandler;
import io.github.shimeoki.jwp.app.actions.tagcreate.CreateTagHandler;
import io.github.shimeoki.jwp.app.actions.tagdelete.DeleteTagHandler;
import io.github.shimeoki.jwp.app.actions.taglist.ListTagsHandler;
import io.github.shimeoki.jwp.app.actions.tagremove.RemoveTagHandler;
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
        AddTagHandler addTag,
        RemoveTagHandler removeTag,
        CreateWallpaperHandler createWallpaper,
        DeleteWallpaperHandler deleteWallpaper,
        ShowWallpaperHandler showWallpaper,
        FindWallpaperHandler findWallpaper,
        CreateSourceHandler createSource,
        DeleteSourceHandler deleteSource,
        AddSourceHandler addSource,
        RemoveSourceHandler removeSource,
        ListSourcesHandler listSources,
        AddAliasHandler addAlias,
        RemoveAliasHandler removeAlias,
        ListAliasesHandler listAliases) {

    public static Handlers fromWorker(final Worker<Provider> w) {
        return new Handlers(
                new CreateTagHandler(w::work),
                new DeleteTagHandler(w::work),
                new ListTagsHandler(w::work),
                new RenameTagHandler(w::work),
                new AddTagHandler(w::work),
                new RemoveTagHandler(w::work),
                new CreateWallpaperHandler(w::work),
                new DeleteWallpaperHandler(w::work),
                new ShowWallpaperHandler(w::work),
                new FindWallpaperHandler(w::work),
                new CreateSourceHandler(w::work),
                new DeleteSourceHandler(w::work),
                new AddSourceHandler(w::work),
                new RemoveSourceHandler(w::work),
                new ListSourcesHandler(w::work),
                new AddAliasHandler(w::work),
                new RemoveAliasHandler(w::work),
                new ListAliasesHandler(w::work));
    }
}
