/**
 * Defines the wiring for the whole application.
 *
 * <p>
 * This package defines a {@link Config} that the {@link App} should use. This
 * app then should be passed to any UI to get or modify the configuration and
 * retrieve the handlers to use them via the interface IO.
 *
 * <p>
 * The app should be initialized only once in the context of a single app. If
 * the configuration needs to change, drastically and this change cannot be
 * handled easily, then the app should be closed and then opened with a new
 * configuration.
 */
package io.github.shimeoki.jwp.config;
