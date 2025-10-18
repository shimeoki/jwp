{ inputs, ... }:
{
    perSystem =
        { pkgs, ... }:
        let
            jdk = pkgs.jdk25;
            gradle = pkgs.gradle_9.override { java = jdk; };

            pname = "jwp";
            version = "0.1.0";
            jar = "${pname}-${version}.jar";
        in
        {
            packages.default = pkgs.stdenv.mkDerivation (finalAttrs: {
                inherit pname version;

                src = "${inputs.self}";

                nativeBuildInputs = [
                    pkgs.makeWrapper
                    gradle
                ];

                mitmCache = gradle.fetchDeps {
                    pkg = finalAttrs.finalPackage;
                    data = ./deps.json;
                };

                # for mitm cache
                __darwinAllowLocalNetworking = true;

                installPhase = ''
                    mkdir -p $out/{bin,lib,scripts}
                    cp jwp/build/libs/${jar} $out/lib
                    cp jwp/build/scripts/jwp $out/scripts

                    makeWrapper $out/scripts/jwp $out/bin/jwp \
                        --set JAVA_HOME "${jdk}/lib/openjdk"
                '';
            });
        };
}
