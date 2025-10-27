package io.github.shimeoki.jwp.config;

import java.util.Objects;

import io.github.shimeoki.jwp.app.actions.sourcecreate.CreateSourceProvider;
import io.github.shimeoki.jwp.app.actions.sourcedelete.DeleteSourceProvider;
import io.github.shimeoki.jwp.app.actions.sourcelist.ListSourcesProvider;
import io.github.shimeoki.jwp.app.actions.tagcreate.CreateTagProvider;
import io.github.shimeoki.jwp.app.actions.tagdelete.DeleteTagProvider;
import io.github.shimeoki.jwp.app.actions.taglist.ListTagsProvider;
import io.github.shimeoki.jwp.app.actions.tagrename.RenameTagProvider;
import io.github.shimeoki.jwp.app.actions.wallpapercreate.CreateWallpaperProvider;
import io.github.shimeoki.jwp.app.actions.wallpaperdelete.DeleteWallpaperProvider;
import io.github.shimeoki.jwp.app.actions.wallpaperfind.FindWallpaperProvider;
import io.github.shimeoki.jwp.app.actions.wallpapershow.ShowWallpaperProvider;
import io.github.shimeoki.jwp.domain.repositories.SourceRepository;
import io.github.shimeoki.jwp.domain.repositories.Store;
import io.github.shimeoki.jwp.domain.repositories.TagRepository;
import io.github.shimeoki.jwp.domain.repositories.WallpaperRepository;

public final class Provider implements
        RenameTagProvider,
        ListTagsProvider,
        CreateTagProvider,
        DeleteTagProvider,
        CreateWallpaperProvider,
        DeleteWallpaperProvider,
        ShowWallpaperProvider,
        FindWallpaperProvider,
        CreateSourceProvider,
        DeleteSourceProvider,
        ListSourcesProvider {

    private final Storage storage;
    private final Transaction tx; // right now nulls are allowed

    public Provider(final Storage s, final Transaction tx) {
        this.storage = Objects.requireNonNull(s);
        this.tx = tx;
    }

    @Override
    public void commit() {
        if (tx != null) {
            tx.commit();
        }
    }

    @Override
    public void close() throws Exception {
        if (tx != null) {
            tx.rollback();
        }
    }

    @Override
    public WallpaperRepository wallpaperRepository() {
        return storage.wallpapers();
    }

    @Override
    public TagRepository tagRepository() {
        return storage.tags();
    }

    @Override
    public Store store() {
        return storage.store();
    }

    @Override
    public SourceRepository sourceRepository() {
        return storage.sources();
    }
}
