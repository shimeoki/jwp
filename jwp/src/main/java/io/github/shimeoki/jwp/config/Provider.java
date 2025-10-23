package io.github.shimeoki.jwp.config;

import java.util.Objects;

import io.github.shimeoki.jwp.app.actions.tagcreate.CreateTagProvider;
import io.github.shimeoki.jwp.app.actions.tagdelete.DeleteTagProvider;
import io.github.shimeoki.jwp.app.actions.taglist.ListTagsProvider;
import io.github.shimeoki.jwp.app.actions.tagrename.RenameTagProvider;
import io.github.shimeoki.jwp.domain.repositories.TagRepository;
import io.github.shimeoki.jwp.domain.repositories.WallpaperRepository;

public final class Provider implements
        RenameTagProvider,
        ListTagsProvider,
        CreateTagProvider,
        DeleteTagProvider {

    private final Repositories repos;
    private final Transaction tx; // right now nulls are allowed

    public Provider(final Repositories repos, final Transaction tx) {
        this.repos = Objects.requireNonNull(repos);
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
        return repos.wallpapers();
    }

    @Override
    public TagRepository tagRepository() {
        return repos.tags();
    }
}
