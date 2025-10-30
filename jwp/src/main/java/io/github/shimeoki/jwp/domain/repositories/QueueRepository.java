package io.github.shimeoki.jwp.domain.repositories;

import java.util.List;

import io.github.shimeoki.jwp.domain.entities.Queue;
import io.github.shimeoki.jwp.domain.values.ID;
import io.github.shimeoki.jwp.domain.values.Status;

public interface QueueRepository extends Repository<Queue> {

    List<Queue> findByWallpaperID(ID id);

    List<Queue> findByStatus(Status s);
}
