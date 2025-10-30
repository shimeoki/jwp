/**
 * Defines the repositories for domain entities.
 *
 * <p>
 * Repository is an abstraction for a collection that manages entities together.
 * The repository implementation shouldn't check for the entity validity (they
 * are already doing it), but for the validity of the collection: are entities
 * allowed to be together.
 *
 * <p>
 * General repositories should extend the base generic {@link Repository}
 * interface. The unique case is {@link Store}: it should be used in pair with
 * {@link WallpaperRepository}, so it has a different signature and logic.
 *
 * <p>
 * Because entities already have an ID, the repositories have a combined
 * create/update method: {@code save}. If an entity with the same ID is already
 * stored, then it should be updated; otherwise - created.
 */
package io.github.shimeoki.jwp.domain.repositories;
