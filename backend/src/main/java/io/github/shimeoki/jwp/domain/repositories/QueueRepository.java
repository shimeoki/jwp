package io.github.shimeoki.jwp.domain.repositories;

import java.util.stream.Stream;

import io.github.shimeoki.jwp.domain.entities.Queue;
import io.github.shimeoki.jwp.domain.values.ID;
import io.github.shimeoki.jwp.domain.values.Status;

public interface QueueRepository extends Repository<Queue> {

    Stream<Queue> findByWallpaperID(ID id);

    Stream<Queue> findByStatus(Status s);
}
