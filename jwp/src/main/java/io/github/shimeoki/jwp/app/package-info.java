/**
 * Defines the core interfaces for the application layer.
 *
 * <p>
 * The subpackages should use the abstractions to define providers and
 * actions.
 *
 * <p>
 * Actions are the use cases of the application. They should be divided from
 * each other: no hierarchy allowed. Each action consists of 5 components:
 *
 * <p>
 * <ol>
 * <li>Action - input DTO</li>
 * <li>Result - output DTO</li>
 * <li>Handler - takes the action and gives the result</li>
 * <li>Provider - needed environment to run the handler</li>
 * <li>Worker - factory of providers to get them on demand</li>
 * </ol>
 *
 * <p>
 * Corresponding components should use the interfaces from this package. Each
 * action should define all self-contained components and use them. The handler
 * should be constructed with a worker instance.
 *
 * <p>
 * The providers should be used in the try-with-resources block like this:
 * 
 * <pre>{@code
 * try (final var p = worker.work()) {
 *     // handler logic using provider 'p'
 * }
 * }</pre>
 */
package io.github.shimeoki.jwp.app;
