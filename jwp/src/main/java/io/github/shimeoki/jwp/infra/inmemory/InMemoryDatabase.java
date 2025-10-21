package io.github.shimeoki.jwp.infra.inmemory;

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

    private static InMemoryDatabase instance;

    private final Map<ID, Tag> tagsByID;
    private final Map<Name, Tag> tagsByName;

    private final Map<ID, Source> sourcesByID;

    private final Map<ID, Wallpaper> wallpapersByID;
    private final Map<Hash, Wallpaper> wallpapersByHash;

    private final Map<ID, Set<ID>> tagWallpapers;
    private final Map<ID, Set<ID>> sourceWallpapers;

    private InMemoryDatabase() {
        tagsByID = new HashMap<>();
        tagsByName = new HashMap<>();

        sourcesByID = new HashMap<>();

        wallpapersByID = new HashMap<>();
        wallpapersByHash = new HashMap<>();

        tagWallpapers = new HashMap<>();
        sourceWallpapers = new HashMap<>();
    }

    public static InMemoryDatabase open() {
        if (instance == null) {
            instance = new InMemoryDatabase();
        }

        return instance;
    }

    public void addTag(final Tag t) {
        final var cloned = t.clone();
        final var id = t.id();

        final var stored = tagsByID.get(id);
        if (stored != null) {
            tagsByName.remove(stored.name());
        }

        tagsByID.put(id, cloned);
        tagsByName.put(t.name(), cloned);

        var walls = tagWallpapers.get(id);
        if (walls == null) {
            walls = new HashSet<>();
            tagWallpapers.put(id, walls);
        }

        // refresh wallpapers with this tag
        for (final var wid : walls) {
            addWallpaper(wallpapersByID.get(wid));
        }
    }

    public void removeTag(final ID id) {
        // remove this tag from linked wallpapers
        for (final var wid : tagWallpapers.get(id)) {
            wallpapersByID.get(wid).removeTag(id);
        }

        tagsByName.remove(tagsByID.get(id).name());
        tagsByID.remove(id);
        tagWallpapers.remove(id);
    }

    public Tag getTagByID(final ID id) {
        final var tag = tagsByID.get(id);
        if (tag == null) {
            return null;
        }

        return tag.clone();
    }

    public Tag getTagByName(final Name n) {
        final var tag = tagsByName.get(n);
        if (tag == null) {
            return null;
        }

        return tag.clone();
    }

    public Collection<Tag> getAllTags() {
        return tagsByID.values().stream()
                .map(Tag::clone).collect(Collectors.toList());
    }

    public int getTagCount() {
        return tagsByID.size();
    }

    public void addSource(final Source s) {
        final var cloned = s.clone();
        final var id = s.id();

        sourcesByID.put(id, cloned);

        var walls = sourceWallpapers.get(id);
        if (walls == null) {
            walls = new HashSet<>();
            sourceWallpapers.put(id, walls);
        }

        // refresh wallpapers with this source
        for (final var wid : walls) {
            addWallpaper(wallpapersByID.get(wid));
        }
    }

    public void removeSource(final ID id) {
        // remove this source from linked wallpapers
        for (final var wid : sourceWallpapers.get(id)) {
            wallpapersByID.get(wid).removeSource(id);
        }

        sourcesByID.remove(id);
        sourceWallpapers.remove(id);
    }

    public Source getSourceByID(final ID id) {
        final var source = sourcesByID.get(id);
        if (source == null) {
            return null;
        }

        return source.clone();
    }

    public Collection<Source> getAllSources() {
        return sourcesByID.values().stream()
                .map(Source::clone).collect(Collectors.toList());
    }

    public int getSourceCount() {
        return sourcesByID.size();
    }

    public void addWallpaper(final Wallpaper w) {
        final var tags = new HashMap<ID, Tag>();
        final var sources = new HashMap<ID, Source>();

        final var id = w.id();
        final var hash = w.hash();
        final var wallpaper = new Wallpaper(
                id,
                w.format(),
                hash,
                sources,
                tags,
                w.createdAt(),
                w.updatedAt());

        joinTags(w, tags);
        joinSources(w, sources);

        final var stored = wallpapersByID.get(id);
        if (stored != null) {
            wallpapersByHash.remove(stored.hash());
        }

        wallpapersByID.put(id, wallpaper);
        wallpapersByHash.put(hash, wallpaper);
    }

    public void removeWallpaper(final ID id) {
        final var wallpaper = wallpapersByID.get(id);

        removeTags(wallpaper);
        removeSources(wallpaper);

        wallpapersByID.remove(id);
        wallpapersByHash.remove(wallpaper.hash());
    }

    public Wallpaper getWallpaperByID(final ID id) {
        final var wallpaper = wallpapersByID.get(id);
        if (wallpaper == null) {
            return null;
        }

        return wallpaper.clone();
    }

    public Wallpaper getWallpaperByHash(final Hash h) {
        final var wallpaper = wallpapersByHash.get(h);
        if (wallpaper == null) {
            return null;
        }

        return wallpaper.clone();
    }

    public Collection<Wallpaper> getAllWallpapers() {
        return wallpapersByID.values().stream()
                .map(Wallpaper::clone).collect(Collectors.toList());
    }

    public int getWallpaperCount() {
        return wallpapersByID.size();
    }

    private void removeTags(final Wallpaper w) {
        final var id = w.id();
        for (final var tag : w.tags()) {
            tagWallpapers.get(tag.id()).remove(id);
        }
    }

    private void joinTags(final Wallpaper w, final Map<ID, Tag> tags) {
        final var id = w.id();

        // delete previous relations if present
        final var stored = wallpapersByID.get(id);
        if (stored != null) {
            removeTags(stored);
        }

        // add new relations
        for (final var tag : w.tags()) {
            final var tid = tag.id();

            final var t = tagsByID.get(tid);
            if (t == null) {
                throw new IllegalArgumentException("invalid tag relation");
            }

            tags.put(tid, t);
            tagWallpapers.get(tid).add(id);
        }
    }

    private void removeSources(final Wallpaper w) {
        final var id = w.id();
        for (final var source : w.sources()) {
            sourceWallpapers.get(source.id()).remove(id);
        }
    }

    private void joinSources(final Wallpaper w, final Map<ID, Source> sources) {
        final var id = w.id();

        // delete previous relations if present
        final var stored = wallpapersByID.get(id);
        if (stored != null) {
            removeSources(stored);
        }

        // add new relations
        for (final var source : w.sources()) {
            final var sid = source.id();

            final var s = sourcesByID.get(sid);
            if (s == null) {
                throw new IllegalArgumentException("invalid source relation");
            }

            sources.put(sid, s);
            sourceWallpapers.get(sid).add(id);
        }
    }
}
