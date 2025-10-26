# jwp

An alternative backend in Java for [wp](https://github.com/shimeoki/wp).

## Prerequisites

- JDK 25+

If you use [Nix](https://github.com/NixOS/nix), it's recommended to enable
flakes.

## Installation

### Manual

To build/install the project you need Java. Specifically, JDK 25 or later.

Your JDK should be available in the `JAVA_HOME` environment variable. Then,
clone the repository and run:

```sh
./gradlew build
```

To run the project once, you can use:

```sh
# use instead of tag list anything you need
./gradlew run --args='tag list'
```

But that's pretty inconvenient. The first build command also assembles the
distributions, which can be used to your liking. They are located in
`jwp/build/distributions`.

Extract contents of any archive here and use any `bin/` script to launch the
application.

### Nix

The process of installation with Nix is the same as in the original project. The
only difference is the link of the repository: use `jwp` instead.

## Structure

This project's structure mostly mirrors the original project. For all additional
information besides `wp` homepage, refer to the documentation in the code (e.g.
`package-info.java` files are a good place to start).

## Development

Aside from the required JDK, your setup is freely to your choice.

All instructions are the same as in the original project.

The repository also exposes the [treefmt](https://github.com/numtide/treefmt)
configuration, but there is no Java formatter yet. I used the default
[jdtls](https://github.com/eclipse-jdtls/eclipse.jdt.ls) formatter for now.

### Nix

One issue that you can encounter, that is because of the JDK, your language
server in [Neovim](https://github.com/neovim/neovim) on Nix can refuse to start.
To fix this, use the devshell or expose JDK 25 in `JAVA_HOME`.

Also, the devshell exposes the convenience alias `gw` for the Gradle wrapper for
easier development.
