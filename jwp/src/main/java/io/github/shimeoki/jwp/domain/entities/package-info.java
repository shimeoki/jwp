/**
 * Defines the rich domain entities for this project.
 *
 * <p>
 * Entity is a class that has an identity, opposed to value objects, which act
 * as primitives. All entities have validation. This validation is on the level
 * of a single entity (we assume it's the only entity that exists in the whole
 * application).
 *
 * <p>
 * The entities should be created in repository implementations using the "full"
 * constructor to reconstruct already existing entities, while actions should
 * use the other to make unique entities with unconditional ID generation.
 *
 * <p>
 * All classes from this package shouldn't be exposed to any user interface.
 * DTOs should be used to encapsulate the creation and/or retrieval.
 */
package io.github.shimeoki.jwp.domain.entities;
