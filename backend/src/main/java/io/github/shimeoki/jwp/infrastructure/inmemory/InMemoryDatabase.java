package io.github.shimeoki.jwp.infrastructure.inmemory;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import io.github.shimeoki.jwp.domain.entities.Source;
import io.github.shimeoki.jwp.domain.entities.Tag;
import io.github.shimeoki.jwp.domain.entities.Wallpaper;
import io.github.shimeoki.jwp.domain.values.Hash;
import io.github.shimeoki.jwp.domain.values.ID;
import io.github.shimeoki.jwp.domain.values.Name;

public final class InMemoryDatabase {

    private final Map<ID, Tag> tagsByID;
    private final Map<Name, Tag> tagsByName;

    private final Map<ID, Source> sourcesByID;

    private final Map<ID, Wallpaper> wallpapersByID;
    private final Map<Hash, Wallpaper> wallpapersByHash;

    private final Map<ID, Set<ID>> tagWallpapers;
    private final Map<ID, Set<ID>> sourceWallpapers;

    public InMemoryDatabase() {
        this.tagsByID = new HashMap<>();
        this.tagsByName = new HashMap<>();

        this.sourcesByID = new HashMap<>();

        this.wallpapersByID = new HashMap<>();
        this.wallpapersByHash = new HashMap<>();

        this.tagWallpapers = new HashMap<>();
        this.sourceWallpapers = new HashMap<>();
    }

    public void addTag(final Tag t) {
        final var cloned = t.clone();
        final var id = t.getID();

        final var stored = this.tagsByID.get(id);
        if (stored != null) {
            this.tagsByName.remove(stored.getName());
        }

        this.tagsByID.put(id, cloned);
        this.tagsByName.put(t.getName(), cloned);

        var walls = this.tagWallpapers.get(id);
        if (walls == null) {
            walls = new HashSet<>();
            this.tagWallpapers.put(id, walls);
        }

        // refresh wallpapers with this tag
        for (final var wid : walls) {
            this.addWallpaper(this.wallpapersByID.get(wid));
        }
    }

    public void removeTag(final ID id) {
        // remove this tag from linked wallpapers
        for (final var wid : this.tagWallpapers.get(id)) {
            this.wallpapersByID.get(wid).removeTag(id);
        }

        this.tagsByName.remove(this.tagsByID.get(id).getName());
        this.tagsByID.remove(id);
        this.tagWallpapers.remove(id);
    }

    public Tag getTagByID(final ID id) {
        return this.tagsByID.get(id).clone();
    }

    public Tag getTagByName(final Name n) {
        return this.tagsByName.get(n).clone();
    }

    public Collection<Tag> getAllTags() {
        return this.tagsByID.values().stream()
                .map(Tag::clone).collect(Collectors.toList());
    }

    public int getTagCount() {
        return this.tagsByID.size();
    }

    public void addSource(final Source s) {
        final var cloned = s.clone();
        final var id = s.getID();

        this.sourcesByID.put(id, cloned);

        var walls = this.sourceWallpapers.get(id);
        if (walls == null) {
            walls = new HashSet<>();
            this.sourceWallpapers.put(id, walls);
        }

        // refresh wallpapers with this source
        for (final var wid : walls) {
            this.addWallpaper(this.wallpapersByID.get(wid));
        }
    }

    public void removeSource(final ID id) {
        // remove this source from linked wallpapers
        for (final var wid : this.sourceWallpapers.get(id)) {
            this.wallpapersByID.get(wid).removeSource(id);
        }

        this.sourcesByID.remove(id);
        this.sourceWallpapers.remove(id);
    }

    public Source getSourceByID(final ID id) {
        return this.sourcesByID.get(id).clone();
    }

    public Collection<Source> getAllSources() {
        return this.sourcesByID.values().stream()
                .map(Source::clone).collect(Collectors.toList());
    }

    public int getSourceCount() {
        return this.sourcesByID.size();
    }

    public void addWallpaper(final Wallpaper w) {
        final var tags = new HashMap<ID, Tag>();
        final var sources = new HashMap<ID, Source>();

        final var id = w.getID();
        final var hash = w.getHash();
        final var wallpaper = new Wallpaper(
                id,
                w.getFormat(),
                hash,
                sources,
                tags,
                w.getCreatedAt(),
                w.getUpdatedAt());

        this.joinTags(w, tags);
        this.joinSources(w, sources);

        final var stored = this.wallpapersByID.get(id);
        if (stored != null) {
            this.wallpapersByHash.remove(stored.getHash());
        }

        this.wallpapersByID.put(id, wallpaper);
        this.wallpapersByHash.put(hash, wallpaper);
    }

    public void removeWallpaper(final ID id) {
        final var wallpaper = this.wallpapersByID.get(id);

        this.removeTags(wallpaper);
        this.removeSources(wallpaper);

        this.wallpapersByID.remove(id);
        this.wallpapersByHash.remove(wallpaper.getHash());
    }

    public Wallpaper getWallpaperByID(final ID id) {
        return this.wallpapersByID.get(id).clone();
    }

    public Wallpaper getWallpaperByHash(final Hash h) {
        return this.wallpapersByHash.get(h).clone();
    }

    public Collection<Wallpaper> getAllWallpapers() {
        return this.wallpapersByID.values().stream()
                .map(Wallpaper::clone).collect(Collectors.toList());
    }

    public int getWallpaperCount() {
        return this.wallpapersByID.size();
    }

    private void removeTags(final Wallpaper w) {
        final var id = w.getID();
        for (final var tag : w.getTags()) {
            this.tagWallpapers.get(tag.getID()).remove(id);
        }
    }

    private void joinTags(final Wallpaper w, final Map<ID, Tag> tags) {
        final var id = w.getID();

        // delete previous relations if present
        final var stored = this.wallpapersByID.get(id);
        if (stored != null) {
            this.removeTags(stored);
        }

        // add new relations
        for (final var tag : w.getTags()) {
            final var tid = tag.getID();

            final var t = this.tagsByID.get(tid);
            if (t == null) {
                throw new IllegalArgumentException("invalid tag relation");
            }

            tags.put(tid, t);
            this.tagWallpapers.get(tid).add(id);
        }
    }

    private void removeSources(final Wallpaper w) {
        final var id = w.getID();
        for (final var source : w.getSources()) {
            this.sourceWallpapers.get(source.getID()).remove(id);
        }
    }

    private void joinSources(final Wallpaper w, final Map<ID, Source> sources) {
        final var id = w.getID();

        // delete previous relations if present
        final var stored = this.wallpapersByID.get(id);
        if (stored != null) {
            this.removeSources(stored);
        }

        // add new relations
        for (final var source : w.getSources()) {
            final var sid = source.getID();

            final var s = this.sourcesByID.get(sid);
            if (s == null) {
                throw new IllegalArgumentException("invalid source relation");
            }

            sources.put(sid, s);
            this.sourceWallpapers.get(sid).add(id);
        }
    }
}
