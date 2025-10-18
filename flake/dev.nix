{ inputs, ... }:
{
    perSystem =
        { pkgs, ... }:
        {
            devShells.default = pkgs.mkShell {
                packages = with pkgs; [
                    nushell
                    jdk25
                ];

                shellHook = ''
                    export JAVA_HOME="${pkgs.jdk25}/lib/openjdk"
                    exec nu --execute "alias gw = ${inputs.self}/gradlew"
                '';
            };
        };
}
